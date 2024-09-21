package com.dae.kdmes.controller;

import com.dae.kdmes.DTO.TBXuserMenuDTO;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.master.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/authcrud", method = RequestMethod.POST)
public class AuthCrudController {
    private final AuthService authService;
    private final RestTemplate restTemplate;
    UserFormDto userformDto = new UserFormDto();
    TBXuserMenuDTO xusermenuDto = new TBXuserMenuDTO();
    List<TBXuserMenuDTO> xusermenuListDto = new ArrayList<>();

    EncryptionController enc = new EncryptionController();



    protected Log log =  LogFactory.getLog(this.getClass());
    //api 관련 메서드-----------
//    public AuthCrudController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
    //---------------------

    @RequestMapping(value="/save")
    public String memberSave(@RequestParam("saupnum") String saupnum
            , @RequestParam("userid") String userid
            , @RequestParam("username") String username
            , @RequestParam("flag") String flag
            , @RequestParam("passwd1") String passwd1
            , @RequestParam("passwd2") String passwd2
            , @RequestParam("phone") String phone
            , @RequestParam("actcd") String actcd
            , Model model,   HttpServletRequest request){

        try {
            userformDto.setCustcd("actas");
            userformDto.setSpjangcd("ZZ");
            userformDto.setUseyn("Y");
            userformDto.setRnum("0");
            userformDto.setFlag(flag);
            userformDto.setSaupnum(saupnum);
            userformDto.setUsername(username);
            userformDto.setUserid(userid);
            userformDto.setPasswd1(passwd1);
            userformDto.setPasswd2(passwd2);
            userformDto.setPhone(phone);


            SecretKey key = enc.getKey();
            IvParameterSpec ivParameterSpec = enc.getIv();
            String specName = "AES/CBC/PKCS5Padding";

            String ecdepw = enc.encrypt(specName, key, ivParameterSpec, userformDto.getPasswd1());

            userformDto.setEncodepw(ecdepw);

            String ls_cltnmInfo = authService.GetClientInfo(userformDto);
            userformDto.setCltcd(ls_cltnmInfo);
            userformDto.setActcd(actcd);

            switch (ls_cltnmInfo){
                case "100534":      //한국엘레텍
                    userformDto.setDbnm("ELV_LRT");
                    break;
                case "100542":      //한양엘리베이터
                    userformDto.setDbnm("hanyangs");
                    break;
                default:
                    break;
            }
            authService.authInsert(userformDto);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "success";
    }


    @RequestMapping(value="/update")
    public void memberUpdate(
            @RequestParam("passwd1") String passwd1,
             @RequestParam("passwd2") String passwd2,
             @RequestParam("phone") String phone,
             @RequestParam("userid") String userid,
             @RequestParam("username") String username,

             Model model,   HttpServletRequest request){

        HttpSession session = request.getSession();
        UserFormDto userformDtoS = (UserFormDto) session.getAttribute("userformDto");

        userformDto.setPasswd1(passwd1);
        userformDto.setPasswd2(passwd2);
        userformDto.setPhone(phone);
        userformDto.setUsername(username);
        userformDto.setUserid(userid);
        userformDto.setCustcd(userformDtoS.getCustcd());
        userformDto.setFlag(userformDtoS.getFlag());

         authService.TB_XUSER_UPDATE(userformDto);

        model.addAttribute("userformDto", userformDto);



    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object memberLoginForm(@RequestParam("loginid") String loginid
            , @RequestParam("logpass") String logpass
            , @RequestParam("ipaddr") String ipaddr
            , Model model
            , HttpServletRequest request) throws Exception{  // 보내려는 API URL
        userformDto.setUserid(loginid);
        userformDto.setPasswd1(logpass);
        UserFormDto userReturnDto = authService.GetUserInfo(userformDto);
//        if(userReturnDto.getWrongnum().equals("3")){
//            return userReturnDto;
//        }
        authService.TB_XUSERS_LOGSUCC(userReturnDto);
        model.addAttribute("UserInfo", userReturnDto );

//        userformDto.setFlag(userReturnDto.getFlag());
//        log.info(userReturnDto.getFlag());
//        log.info(userReturnDto.getUsername());
//        log.info("memberLoginForm Exception ================================================================");
//        log.info(userformDto);
        //userformDto =  authService.GetUserInfoDto(userformDto);

        HttpSession session = request.getSession();
        session.setAttribute("userformDto",userReturnDto);
        if(userReturnDto.getFlag() == null){
            log.info("memberLoginForm Exception =================>");
            userReturnDto = null;
            return userReturnDto;
        }else {
            String ls_return = sendLogData(loginid, "접속", ipaddr);
            log.info("ls_result====>" + ls_return);
            return userReturnDto;
        }
    }
    @RequestMapping(value = "/adminchk", method = RequestMethod.POST)
    public Object memberLoginAdminForm(@RequestParam("loginid") String loginid
            , @RequestParam("logpass") String logpass
            , @RequestParam("flag") String select
            , @RequestParam("ipaddr") String ipaddr
            , Model model
            , HttpServletRequest request) throws Exception{
        userformDto.setUserid(loginid);
        userformDto.setPasswd1(logpass);
        userformDto.setFlag("ZZ");
        userformDto.setCustcd(select);
        UserFormDto userReturnDto = authService.GetAdminInfo(userformDto);
//        UserFormDto custReturnDto = authService.GetCustInfo(userformDto);

        if(userReturnDto.getWrongnum().equals("3")){
            return userReturnDto;
        }

        authService.TB_XUSERS_LOGSUCC(userReturnDto);
        model.addAttribute("UserInfo", userReturnDto );

        HttpSession session = request.getSession();
        session.setAttribute("userformDto",userReturnDto);
        if(!userformDto.getFlag().equals("ZZ")){
            userReturnDto = null;
            return userReturnDto;
        }else {
            String ls_return = sendLogData(loginid, "접속", ipaddr);
            log.info("ls_result====>" + ls_return);
            return userReturnDto;
        }
    }

    @RequestMapping(value = "/useriddupchk", method = RequestMethod.POST)
    public Object AppW016_index(@RequestParam("userid") String userid
            ,Model model, HttpServletRequest request) throws Exception{

        userformDto.setUserid(userid);

        authService.TB_XUSER_DUPCHK(userformDto);
        String ls_userid = authService.TB_XUSER_DUPCHK(userformDto);
        if(ls_userid == null || ls_userid.equals("")){

            return ls_userid;
        }else{
            ls_userid = "error";
            return ls_userid;
        }
    }

    @RequestMapping(value = "/userphdupchk", method = RequestMethod.POST)
    public Object AppW014_index(@RequestParam("phone") String phone, Model model, HttpServletRequest request) throws Exception{

        userformDto.setPhone(phone);

        authService.TB_XUSER_PHDUPCHK(userformDto);
        String ls_userphone = authService.TB_XUSER_PHDUPCHK(userformDto);
        if(ls_userphone == null || ls_userphone.equals("")){
            return ls_userphone;
        }else{
            ls_userphone = "error";
            return ls_userphone;
        }
    }

    @RequestMapping(value = "/searchnum", method = RequestMethod.POST)
    public Object AppW015_index(@RequestParam("saupnum") String saupnum,
                                Model model, HttpServletRequest request)throws  Exception{
            userformDto.setSaupnum(saupnum);

            authService.TB_XCLIENT_SELECT(userformDto);
            String ls_cltnm = authService.TB_XCLIENT_SELECT(userformDto);
            if(ls_cltnm == null || ls_cltnm.equals("")){
                ls_cltnm = "error";
            }
            return ls_cltnm;
    }

    @RequestMapping(value = "/loginlog", method = RequestMethod.POST)
    public void Login_Log(@RequestParam("loginid") String loginid
            , @RequestParam("logpass") String logpass
            , @RequestParam("ipaddr") String ipaddr
            , HttpServletRequest request     )throws Exception {
            System.out.printf("--loginlog 진입");

            UserFormDto user = new UserFormDto();
            HttpSession session = request.getSession();
            UserFormDto userformDtoS = (UserFormDto) session.getAttribute("userformDto");

            user.setUserid(userformDtoS.getUserid());
            user.setPasswd1(userformDtoS.getPasswd1());
            user.setIpaddr(ipaddr);
            user.setCustnm(userformDtoS.getCustnm());
            user.setUsername(userformDtoS.getUsername());

            authService.TB_XLOGIN_INSERT(user);

    }


    @RequestMapping(value = "/logoutlog", method = RequestMethod.POST)
    public void Logout_Log(@RequestParam("loginid") String loginid
                           ,@RequestParam("ipaddr") String ipaddr
            , HttpServletRequest request )throws Exception {


        UserFormDto user = new UserFormDto();

        HttpSession session = request.getSession();
        UserFormDto userformDtoS = (UserFormDto) session.getAttribute("userformDto");

        user.setUserid(userformDtoS.getUserid());
        user.setPasswd1(userformDtoS.getPasswd1());
        user.setUsername(userformDtoS.getUsername());
        user.setCustnm(userformDtoS.getCustnm());
        user.setIpaddr("");
        authService.TB_XLOGOUT_INSERT(user);
        String ls_return = sendLogData(loginid, "종료", ipaddr);
        log.info("ls_result====>" + ls_return);

    }

    @RequestMapping(value = "/wrongpasswd", method = RequestMethod.POST)
    public Object Login_fail(@RequestParam("loginid") String loginid
                             , @RequestParam("logpass") String logpass, Model model)throws Exception {
            System.out.printf("--loginfail 진입");

            UserFormDto user = new UserFormDto();

            user.setUserid(loginid);
            user.setPasswd1(logpass);


            user = authService.GetUserInfoDto(user);
            if(user == null){
                return "null";
            }
            if(user.getWrongnum().equals("3")){

            } else{
                authService.TB_XUSERS_LOGFAIL(user);
                user = authService.GetUserInfo(user);
            }



            return user;

    }


    @RequestMapping(value = "/menulist", method = RequestMethod.POST)
    public Object Login_fail(@RequestParam("searchtext") String userid
            , Model model
            , HttpServletRequest request )throws Exception {

        HttpSession session = request.getSession();
        UserFormDto userformDtoS = (UserFormDto) session.getAttribute("userformDto");

        xusermenuDto.setCustcd(userformDtoS.getCustcd());
        xusermenuDto.setUserid(userid);
        xusermenuListDto = authService.GetXusersMenuList(xusermenuDto);


        return xusermenuListDto;

    }


    public String sendLogData(String arg_id, String  arg_gubun, String arg_ip) {
        String url = "https://log.smart-factory.kr/apisvc/sendLogData.json";  // 보내려는 API URL
        String api_key = "$5$API$V95bfCw5QF8F69zMz7c.kvFICN4Qs5JWCUhJNVfvznA";
        String ls_result = "";
        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");  // Content-Type을 문자열로 설정
        // 현재 날짜와 시간을 "2024-09-21 15:42:21.111" 형식으로 포맷팅
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedLogDt = now.format(formatter);  // 포맷된 시간

        try {
            // JSON 데이터 설정
            Map<String, Object> requestData = new LinkedHashMap<>();
            requestData.put("crtfcKey", api_key);
            requestData.put("logDt",  formattedLogDt);  // 현재 시간 (로그 일시)
            requestData.put("useSe", arg_gubun);
            requestData.put("sysUser", arg_id);
            requestData.put("conectIp", arg_ip);
            requestData.put("dataUsgqty", "0");  // 데이터 사용량 (숫자)
            // HttpEntity에 요청 데이터와 헤더를 담기
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestData, headers);
            // POST 요청 보내기 (postForEntity 사용)
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            ls_result = response.getBody();

        } catch (Exception ex) {
            log.info("sendLogDatax Exception =====>" + ex.toString());
        }
        return ls_result;  // 응답 값 반환
    }
    @RequestMapping(value = "/dbnm", method = RequestMethod.POST)
    public Object AppW018_index(@RequestParam("saupnum") String saupnum, Model model, HttpServletRequest request){

        userformDto.setSaupnum(saupnum);

        authService.TB_XUSER_DBNM(userformDto);
        String ls_dbnm = authService.TB_XUSER_DBNM(userformDto);
        if(ls_dbnm == null || ls_dbnm.equals("")){
            ls_dbnm = "error";
        }
        return ls_dbnm;
    }
}
