package com.dae.kdmes.controller.appm;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App01.Index02Dto;
import com.dae.kdmes.DTO.App01.Index03Dto;
import com.dae.kdmes.DTO.App01.Index04Dto;
import com.dae.kdmes.DTO.Appm.FPLANBOM_VO;
import com.dae.kdmes.DTO.Appm.FPLANW010_VO;
import com.dae.kdmes.DTO.Appm.FPLAN_VO;
import com.dae.kdmes.DTO.Appm.TBPopupVO;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.App01.Index01Service;
import com.dae.kdmes.Service.App01.Index02Service;
import com.dae.kdmes.Service.App01.Index03Service;
import com.dae.kdmes.Service.App01.Index04Service;
import com.dae.kdmes.Service.Appm.AppPopupService;
import com.dae.kdmes.Service.Appm.Appcom01Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// @RestController : return을 텍스트로 반환함.
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/appm", method = RequestMethod.POST)
public class Appm01Controller {
    private final Appcom01Service appcom01Service;
    private final AppPopupService appPopupService;
    protected Log log =  LogFactory.getLog(this.getClass());
    CommonDto CommDto = new CommonDto();
    FPLANW010_VO workDto = new FPLANW010_VO();
    //공통코드등록
    @GetMapping(value="/mainframemb")
    public String App01_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공통코드등록");
        CommDto.setMenuUrl("기준정보>공통코드등록");
        CommDto.setMenuCode("index01");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        userformDto.setPernm(userformDto.getUsername());
        userformDto.setUsername(userformDto.getUsername());
        userformDto.setUserid(userformDto.getUserid());
        userformDto.setCustcd(userformDto.getCustcd());
        userformDto.setFlag(userformDto.getFlag());

        model.addAttribute("userDto", userformDto );

        return "Appm/mainframemb";
    }


    //사업장정보조회
    @GetMapping(value="/list")
    public String Appcom01_index(@RequestParam("line") String line
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("fdate") String fdate
            ,@RequestParam("tdate") String tdate
            ,@RequestParam("cltcd") String cltcd
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wseq") String wseq
            ,Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("사출공정");  //
        CommDto.setMenuUrl("생산공정>사출공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        FPLANW010_VO itemDto = new FPLANW010_VO();
        FPLANW010_VO itemDtoInput = new FPLANW010_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        TBPopupVO wrmcDto = new TBPopupVO();
        TBPopupVO wperidDto = new TBPopupVO();
        FPLANBOM_VO wbomDto = new FPLANBOM_VO();
        fdate = getFrDate();
        tdate = getToDate();
        if (cltcd == null || cltcd.equals("")){
            cltcd = "%";
        }
        if (pcode == null || pcode.equals("")){
            pcode = "%";
        }
        fplanDto.setLine(line);
        fplanDto.setWflag(wflag);


        fplanDto.setFdate(fdate);
        fplanDto.setTdate(tdate);
        fplanDto.setCltcd(cltcd);
        fplanDto.setPcode(pcode);

        itemDto.setPlan_no(plan_no);
        itemDto.setWseq(wseq);
//        itemDtoList = appcom01Service.GetFPLANW010_List(fplanDto);
        if(line == "99"){
            if(appcom01Service.GetFPLANW010_ListOne() == null){
                model.addAttribute("itemDto", appcom01Service.FPLANW010_Blank());
            }else{
                model.addAttribute("itemDto", appcom01Service.GetFPLANW010_ListOne());
            }
        }else{
            if(appcom01Service.GetFPLANW010_Detail(itemDto) == null){
                model.addAttribute("itemDto", appcom01Service.FPLANW010_Blank());
            }else{
                model.addAttribute("itemDto", appcom01Service.GetFPLANW010_Detail(itemDto));
            }
        }

        if(appcom01Service.GetFPLAN_List(fplanDto) == null){
            model.addAttribute("itemDtoList", appcom01Service.FPLAN_Blank());
        }else{
            model.addAttribute("itemDtoList", appcom01Service.GetFPLAN_List(fplanDto));
        }


        wrmcDto.setMachname("%");
        wperidDto.setWflag("00010");  //첫번째공정
        wrmcDto.setPlan_no("%");      //불량구분 팝업
        wrmcDto.setWseq("%");
        wrmcDto.setWflag("00010");
        wrmcDto.setWclscode("1");
        model.addAttribute("itemDtoInput", itemDtoInput);
        model.addAttribute("CommDto", CommDto);
        model.addAttribute("wrmcDto", appPopupService.GetWrmcList(wrmcDto));
        model.addAttribute("wperidDto", appPopupService.GetWfperidList(wperidDto));
//        wbomDto.setPlan_no("202108120027");
        model.addAttribute("wfbomDto", appPopupService.GetWfbomList_blank());
//        model.addAttribute("wfbomDto", appPopupService.GetWfbomList_blank());
        model.addAttribute("wfiworkDto", appPopupService.GetWfiworkList_blank());

        model.addAttribute("wbadDto", appPopupService.GetWBadList(wrmcDto));

        return "AppCom/LayFPLAN_W010";
    }
    private String getFrDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, -14); // 빼고 싶다면 음수 입력
        Date date      = new Date(cal1.getTimeInMillis());

        return formatter.format(date);
    }
    private String getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }
}
