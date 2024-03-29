package com.dae.kdmes.controller.app02;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App02.Index10Dto;
import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.DTO.App01.Index02Dto;
import com.dae.kdmes.DTO.App01.Index03Dto;
import com.dae.kdmes.DTO.App01.Index04Dto;
import com.dae.kdmes.DTO.Appm.TBPopupVO;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.App01.Index01Service;
import com.dae.kdmes.Service.App01.Index03Service;
import com.dae.kdmes.Service.App02.Index10Service;
import com.dae.kdmes.Service.App02.Index11Service;
import com.dae.kdmes.Service.Appm.AppPopupService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

// @RestController : return을 텍스트로 반환함.
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/app02", method = RequestMethod.POST)
public class App02Controller {
    private final Index10Service service10;
    private final AppPopupService appPopupService;
    private final Index03Service service03;

    private final Index01Service service01;
    CommonDto CommDto = new CommonDto();
    PopupDto popupDto = new PopupDto();
    Index01Dto index01Dto = new Index01Dto();
    Index10Dto index10Dto = new Index10Dto();

    Index11Dto index11Dto = new Index11Dto();
    List<PopupDto> popupListDto = new ArrayList<>();

    List<PopupDto> popupListDto1 = new ArrayList<>();
    List<Index10Dto> index10ListDto = new ArrayList<>();

    List<Index11Dto> index11ListDto = new ArrayList<>();

    List<Index01Dto> index01ListDto = new ArrayList<>();

    protected Log log =  LogFactory.getLog(this.getClass());
    //공통코드등록
    @GetMapping(value="/index10")
    public String App02_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("생산계획등록");
        CommDto.setMenuUrl("생산계획>생산계획등록");
        CommDto.setMenuCode("index10");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index10ListDto = service10.getFplanList(index10Dto);
            popupListDto = service10.getCls_flagList(popupDto);
            popupListDto = service03.getj1_keyList(popupDto);
            popupListDto1 = service03.getj2_keyList(popupDto);

            model.addAttribute("j1_keyList",popupListDto);
            model.addAttribute("j2_keyList",popupListDto1);

            model.addAttribute("cls_flagList",popupListDto);
            model.addAttribute("fplanList",index10ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App02_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index10";
    }

    @GetMapping(value="/index11")
    public String App11_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("생산계획현황");
        CommDto.setMenuUrl("생산계획>생산계획현황");
        CommDto.setMenuCode("index11");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

            //index11ListDto = service11.getWflagList(index11Dto);
            //index01ListDto = service01.getCom_rem2_keyList(index01Dto);

//            model.addAttribute("com_rem2_keyList",index01ListDto);
//            model.addAttribute("wflagList",index11ListDto);



        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App11_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index11";
    }


    @GetMapping(value="/index12")
    public String App12_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공정현황");
        CommDto.setMenuUrl("생산계획>공정현황");
        CommDto.setMenuCode("index12");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
//            index10ListDto = service10.getFplanList(index10Dto);
//            popupListDto = service10.getCls_flagList(popupDto);
//
//            model.addAttribute("cls_flagList",popupListDto);
//            model.addAttribute("fplanList",index10ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App12_index Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index12";
    }

    @GetMapping(value="/index13")
    public String App13_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("사원별검사현황");
        CommDto.setMenuUrl("생산계획>사원별검사현황");
        CommDto.setMenuCode("index13");
        TBPopupVO wperidDto = new TBPopupVO();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        try {
            wperidDto.setWflag("%");
            wperidDto.setWpernm("%");

            model.addAttribute("wperidDto", appPopupService.GetPernmList(wperidDto));
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("index13 Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index13";
    }


    @GetMapping(value="/index14")
    public String App14_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("불량현황");
        CommDto.setMenuUrl("생산계획>불량현황");
        CommDto.setMenuCode("index14");
        TBPopupVO wrmcDto = new TBPopupVO();
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        try {
            wrmcDto.setMachname("%");
            wrmcDto.setPlan_no("%");      //불량구분 팝업
            wrmcDto.setWseq("%");
            wrmcDto.setWflag("00010");
            wrmcDto.setWclscode("1");

            model.addAttribute("wbadDto", appPopupService.GetWBadList01(wrmcDto));
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("index14 Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index14";
    }


    @GetMapping(value="/index16")
    public String App16_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("출하현황");
        CommDto.setMenuUrl("생산계획>출하현황");
        CommDto.setMenuCode("index16");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        try {



        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("index16 Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index16";
    }

}
