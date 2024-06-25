package com.dae.kdmes.controller.app02;

import com.dae.kdmes.DTO.App01.*;
import com.dae.kdmes.DTO.App02.Index10Dto;
import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.DTO.Appm.FPLANW010_VO;
import com.dae.kdmes.DTO.Appm.FPLAN_VO;
import com.dae.kdmes.DTO.Appm.TBPopupVO;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.App01.Index01Service;
import com.dae.kdmes.Service.App01.Index03Service;
import com.dae.kdmes.Service.App02.Index10Service;
import com.dae.kdmes.Service.App02.Index11Service;
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
@RequestMapping(value = "/app02", method = RequestMethod.POST)
public class App02Controller {
    private final Index10Service service10;
    private final AppPopupService appPopupService;
    private final Index03Service service03;
    private final Appcom01Service appcom01Service;

    FPLAN_VO fplanDto = new FPLAN_VO();
    FPLANW010_VO itemDto = new FPLANW010_VO();
    TBPopupVO wperidDto = new TBPopupVO();
    TBPopupVO wrmcDto = new TBPopupVO();
    List<FPLAN_VO> itemDtoList = new ArrayList<>();
    List<FPLAN_VO> itemDtoList02 = new ArrayList<>();
    List<IndexCa613Dto> itemDtoListCa613 = new ArrayList<>();

    private final Index01Service service01;
    CommonDto CommDto = new CommonDto();
    PopupDto popupDto = new PopupDto();
    Index01Dto index01Dto = new Index01Dto();
    Index10Dto index10Dto = new Index10Dto();
    IndexCa613Dto indexCa613Dto = new IndexCa613Dto();

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
            index10ListDto = service10.GetFplanList(index10Dto);
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



    //입고등록
    @GetMapping(value="/index15")
    public String Appcom15_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("입고등록");  //
        CommDto.setMenuUrl("생산공정>입고등록");
        String fdate = getFrDate();
        String tdate = getAddDate();
        String pcode = "%";
        indexCa613Dto.setPname(pcode);
        indexCa613Dto.setFrdate(fdate);
        indexCa613Dto.setTodate(tdate);
        itemDtoListCa613   = service10.SelectCa613List(indexCa613Dto);
        //창고
        wperidDto.setWpernm("%");
        model.addAttribute("wstoreDto", appPopupService.GetStoreList(wperidDto));
        model.addAttribute("itemDtoList", itemDtoListCa613);
        return "App02/index15";
    }


    //출하등록
    @GetMapping(value="/index21")
    public String Appcom21_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("출하등록");  //
        CommDto.setMenuUrl("생산공정>출하등록");
        String fdate = getFrDate();
        String tdate = getAddDate();
        String pcode = "%";
        indexCa613Dto.setPname(pcode);
        indexCa613Dto.setFrdate(fdate);
        indexCa613Dto.setTodate(tdate);

        itemDtoListCa613   = service10.SelectCa613List(indexCa613Dto);
        model.addAttribute("itemDtoList", itemDtoListCa613);
        return "App02/index21";
    }

    //사출등록
    @GetMapping(value="/index31")
    public String Appcom31_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("사출공정");  //
        CommDto.setMenuUrl("생산공정>사출공정");
        CommDto.setMenuCode("appcom01");
        String fdate = getFrDate();
        String tdate = getAddDate();
        String cltcd = "%";
        String pcode = "%";
        fplanDto.setLine("00");
        fplanDto.setWflag("00010");
        fplanDto.setFdate(fdate);
        fplanDto.setTdate(tdate);
        fplanDto.setCltcd(cltcd);
        fplanDto.setPcode(pcode);
        itemDto.setPlan_no("%");
//        itemDto = appcom01Service.FPLANW010_Blank();
        itemDtoList = appcom01Service.GetFPLAN_List(fplanDto);
//        model.addAttribute("itemDto", itemDto);
        model.addAttribute("itemDtoList", itemDtoList);


        wrmcDto.setMachname("%");
        wperidDto.setWflag("00010");  //첫번째공정
        wperidDto.setWpernm("%");
        wrmcDto.setPlan_no("%");      //불량구분 팝업
        wrmcDto.setWseq("%");
        wrmcDto.setWflag("00010");
        wrmcDto.setWclscode("1");
        model.addAttribute("CommDto", CommDto);
        model.addAttribute("wrmcDto", appPopupService.GetWrmcList01(wrmcDto));          //설비명
//        log.info("Exception =====>" + appPopupService.GetPernmList(wperidDto).toString());
        model.addAttribute("wperidDto", appPopupService.GetPernmList(wperidDto));       //작업자
//        wbomDto.setPlan_no("202108120027");
        model.addAttribute("wfbomDto", appPopupService.GetWfbomList_blank());
//        model.addAttribute("wfbomDto", appPopupService.GetWfbomList_blank());
        model.addAttribute("wfiworkDto", appPopupService.GetWfiworkList_blank());

        model.addAttribute("wbadDto", appPopupService.GetWBadList01(wrmcDto));          //불량구분
        return "App02/index31";
    }

    //검사공정
    @GetMapping(value="/index41")
    public String Appcom41_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("검사공정");  //
        CommDto.setMenuUrl("생산공정>검사공정");
        CommDto.setMenuCode("appcom02");
        String fdate = getFrDate();
        String tdate = getAddDate();
        String cltcd = "%";
        String pcode = "%";
        fplanDto.setLine("00");
        fplanDto.setWflag("00020");
        fplanDto.setFdate("20000101");
        fplanDto.setTdate(tdate);
        fplanDto.setCltcd(cltcd);
        fplanDto.setPcode(pcode);
        itemDto.setPlan_no("%");

        wperidDto.setWflag("00020");  //첫번째공정
        wperidDto.setWpernm("%");

        wrmcDto.setMachname("%");
        wrmcDto.setPlan_no("%");      //불량구분 팝업
        wrmcDto.setWseq("%");
        wrmcDto.setWflag("00020");
        wrmcDto.setWclscode("1");



        itemDtoList   = appcom01Service.GetFPLAN_List02(fplanDto);      //사출완료
        itemDtoList02 = appcom01Service.GetFPLAN_List02_REG(fplanDto);      //검사등록완료

        model.addAttribute("itemDtoList", itemDtoList);         //사출완료리스트
        model.addAttribute("itemDtoList02", itemDtoList02);     //검사완료리스트
        model.addAttribute("wperidDto", appPopupService.GetPernmList(wperidDto));       //작업자
        model.addAttribute("wbadDto", appPopupService.GetWBadList01(wrmcDto));
        return "App02/index41";
    }


    //조립공정
    @GetMapping(value="/index45")
    public String Appcom45_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("조립공정");  //
        CommDto.setMenuUrl("생산공정>조립공정");
        CommDto.setMenuCode("appcom02");
        String fdate = getFrDate();
        String tdate = getAddDate();
        String cltcd = "%";
        String pcode = "%";
        fplanDto.setLine("00");
        fplanDto.setWflag("00030");
        fplanDto.setFdate("20000101");
        fplanDto.setTdate(tdate);
        fplanDto.setCltcd(cltcd);
        fplanDto.setPcode(pcode);
        itemDto.setPlan_no("%");

        wperidDto.setWflag("00030");  //첫번째공정
        wperidDto.setWpernm("%");

        wrmcDto.setMachname("%");
        wrmcDto.setPlan_no("%");      //불량구분 팝업
        wrmcDto.setWseq("%");
        wrmcDto.setWflag("00030");
        wrmcDto.setWclscode("1");



        itemDtoList   = appcom01Service.GetFPLAN_List02_JO(fplanDto);      //사출완료
        itemDtoList02 = appcom01Service.GetFPLAN_List02_REG_JO(fplanDto);      //검사등록완료

        model.addAttribute("itemDtoList", itemDtoList);         //사출완료리스트
        model.addAttribute("itemDtoList02", itemDtoList02);     //검사완료리스트
        model.addAttribute("wperidDto", appPopupService.GetPernmList(wperidDto));       //작업자
        model.addAttribute("wbadDto", appPopupService.GetWBadList01(wrmcDto));
        return "App02/index45";
    }
//    public String Appcom45_index( Model model, HttpServletRequest request) throws Exception{
//        CommDto.setMenuTitle("조립공정");  //
//        CommDto.setMenuUrl("생산공정>조립공정");
//        CommDto.setMenuCode("appcom02");
//        String fdate = getFrDate();
//        String tdate = getAddDate();
//        String cltcd = "%";
//        String pcode = "%";
//        fplanDto.setLine("00");
//        fplanDto.setWflag("00030");
//        fplanDto.setFdate("20000101");
//        fplanDto.setTdate(tdate);
//        fplanDto.setCltcd(cltcd);
//        fplanDto.setPcode(pcode);
//        itemDto.setPlan_no("%");
//
//        wperidDto.setWflag("00030");  //첫번째공정
//        wperidDto.setWpernm("%");
//
//        wrmcDto.setMachname("%");
//        wrmcDto.setPlan_no("%");      //불량구분 팝업
//        wrmcDto.setWseq("%");
//        wrmcDto.setWflag("00030");
//        wrmcDto.setWclscode("1");
//
//
//
//        itemDtoList   = appcom01Service.GetFPLAN_List03(fplanDto);      //사출완료
//        itemDtoList02 = appcom01Service.GetFPLAN_List03_REG(fplanDto);      //조립등록완료
//
//        model.addAttribute("itemDtoList", itemDtoList);         //사출완료리스트
//        model.addAttribute("itemDtoList02", itemDtoList02);     //검사완료리스트
//        model.addAttribute("wperidDto", appPopupService.GetPernmList(wperidDto));       //작업자
//        model.addAttribute("wbadDto", appPopupService.GetWBadList01(wrmcDto));
//        return "App02/index45";
//    }


    //검사공정
    @GetMapping(value="/index41list")
    public String Appcom41list_index(@RequestParam("searchtxt") String searchtxt
            ,Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("검사공정");  //
        CommDto.setMenuUrl("생산공정>검사공정");
        CommDto.setMenuCode("appcom02");
        if (searchtxt.equals("") ||  searchtxt == null || searchtxt.length() == 0){
            searchtxt = "%";
        }
        String fdate = getFrDate();
        String tdate = getAddDate();
        String cltcd = "%";
        String pcode = "%";
        fplanDto.setLine("00");
        fplanDto.setWflag("00020");
        fplanDto.setFdate(fdate);
        fplanDto.setTdate(tdate);
        fplanDto.setCltcd(cltcd);
        fplanDto.setPcode(pcode);
        fplanDto.setLotno(searchtxt);
        itemDto.setPlan_no("%");

        wperidDto.setWflag("00020");  //첫번째공정
        wperidDto.setWpernm("%");

        itemDtoList   = appcom01Service.GetFPLAN_List02(fplanDto);      //사출완료
        itemDtoList02 = appcom01Service.GetFPLAN_List02_REG(fplanDto);      //검사등록완료

        model.addAttribute("itemDtoList", itemDtoList);         //사출완료리스트
        model.addAttribute("itemDtoList02", itemDtoList02);     //검사완료리스트
        model.addAttribute("wperidDto", appPopupService.GetPernmList(wperidDto));       //작업자
        return "App01/index41";
    }


    @GetMapping(value="/index51")
    public String App51_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("생산계획현황");
        CommDto.setMenuUrl("생산계획>사출일지조회");
        CommDto.setMenuCode("index51");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App51_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index51";
    }

    @GetMapping(value="/index52")
    public String App52_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("생산계획현황");
        CommDto.setMenuUrl("생산계획>불량현황조회");
        CommDto.setMenuCode("index51");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App52_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index52";
    }

    @GetMapping(value="/index53")
    public String App53_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("생산계획현황");
        CommDto.setMenuUrl("생산계획>생산현황조회");
        CommDto.setMenuCode("index53");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App53_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index53";
    }


    @GetMapping(value="/index54")
    public String App54_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("생산계획현황");
        CommDto.setMenuUrl("생산계획>불량추이도조회");
        CommDto.setMenuCode("index54");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App54_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index54";
    }

    @GetMapping(value="/index55")
    public String App55_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("일별검사일보");
        CommDto.setMenuUrl("생산계획>일별검사일보");
        CommDto.setMenuCode("index54");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App55_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index55";
    }


    @GetMapping(value="/index56")
    public String App56_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("일별검사일보");
        CommDto.setMenuUrl("생산계획>검사불량조회");
        CommDto.setMenuCode("index56");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App56_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index56";
    }

    @GetMapping(value="/index57")
    public String App57_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("일별사원검사일보");
        CommDto.setMenuUrl("생산계획>일별사원검사일보");
        CommDto.setMenuCode("index56");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App57_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index57";
    }


    @GetMapping(value="/index58")
    public String App58_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("출하내역조회");
        CommDto.setMenuUrl("생산계획>출하내역조회");
        CommDto.setMenuCode("index56");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App58_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index58";
    }


    @GetMapping(value="/index59")
    public String App59_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("기초재고");
        CommDto.setMenuUrl("생산계획>기초재고");
        CommDto.setMenuCode("index59");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App59_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index59";
    }


    @GetMapping(value="/index60")
    public String App60_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품별재고현황");
        CommDto.setMenuUrl("생산계획>제품별재고현황");
        CommDto.setMenuCode("index59");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App60_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index60";
    }


    @GetMapping(value="/index61")
    public String App61_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("재고조정");
        CommDto.setMenuUrl("생산계획>재고조정");
        CommDto.setMenuCode("index61");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App61_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index61";
    }

    @GetMapping(value="/index62")
    public String App62_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("재고설정");
        CommDto.setMenuUrl("생산계획>재고설정");
        CommDto.setMenuCode("index61");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App62_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index62";
    }

    @GetMapping(value="/index63")
    public String App63_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품수불원장");
        CommDto.setMenuUrl("생산계획>제품수불원장");
        CommDto.setMenuCode("index61");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App63_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index63";
    }

    @GetMapping(value="/index64")
    public String App64_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("일별조립일보");
        CommDto.setMenuUrl("생산계획>일별조립일보");
        CommDto.setMenuCode("index64");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App64_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App02/index64";
    }

    private String getFrDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, -100); // 빼고 싶다면 음수 입력
        Date date      = new Date(cal1.getTimeInMillis());

        return formatter.format(date);
    }

    private String getAddDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, 14); // 빼고 싶다면 음수 입력
        Date date      = new Date(cal1.getTimeInMillis());

        return formatter.format(date);
    }



}
