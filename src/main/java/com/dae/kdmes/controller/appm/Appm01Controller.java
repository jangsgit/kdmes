package com.dae.kdmes.controller.appm;

import com.dae.kdmes.DTO.Appm.*;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.UserFormDto;
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
    FPLANW010_VO itemDto = new FPLANW010_VO();
    FPLAN_VO fplanDto = new FPLAN_VO();
    List<FPLAN_VO> itemDtoList = new ArrayList<>();
    List<FPLAN_VO> itemDtoList02 = new ArrayList<>();
    TBPopupVO wrmcDto = new TBPopupVO();
    TBPopupVO wperidDto = new TBPopupVO();
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


    //사출공정
    @GetMapping(value="/list01")
    public String Appcom01_index(Model model, HttpServletRequest request) throws Exception{
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

        return "AppCom/LayFPLAN_W010";
    }


    //검사공정
    @GetMapping(value="/list02")
    public String Appcom02_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("검사공정");  //
        CommDto.setMenuUrl("생산공정>검사공정");
        CommDto.setMenuCode("appcom02");
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
        itemDto.setPlan_no("%");
//        itemDto = appcom01Service.FPLANW010_Blank();
        itemDtoList = appcom01Service.GetFPLAN_List02(fplanDto);
        itemDtoList02 = appcom01Service.GetFPLAN_List03(fplanDto);
//        model.addAttribute("itemDto", itemDto);
        model.addAttribute("itemDtoList", itemDtoList);
        model.addAttribute("itemDtoList02", itemDtoList02);


        wrmcDto.setMachname("%");
        wperidDto.setWflag("00020");  //첫번째공정
        wperidDto.setWpernm("%");
        wrmcDto.setPlan_no("%");      //불량구분 팝업
        wrmcDto.setWseq("%");
        wrmcDto.setWflag("00020");
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

        return "AppCom/LayFPLAN_W020";
    }

    //검사공정
    @GetMapping(value="/list03")
    public String Appcom03_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("검사이력조회");  //
        CommDto.setMenuUrl("생산공정>검사이력조회");
        CommDto.setMenuCode("appcom03");

        FPLANIWORK_VO fplanDto = new FPLANIWORK_VO();
        List<FPLANIWORK_VO> fplanListDto = new ArrayList<>();
        fplanDto.setLotno("%");
        fplanListDto =   appcom01Service.GetPlanSearch(fplanDto);
        model.addAttribute("fplanListDto", fplanListDto );
        return "AppCom/LayFPLAN_PlanSearch";
    }
    //생산현황
    @GetMapping(value="/list04")
    public String Appcom04_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("생산현황조회");  //
        CommDto.setMenuUrl("생산공정>생산현황조회");
        CommDto.setMenuCode("appcom04");

        TBFplanNowVO viewDto = new TBFplanNowVO();
        Date nowData = new Date();
        SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
        String startDate = endDate.format(nowData).toString();
        String toDate = endDate.format(nowData).toString();
//        startDate = startDate.substring(0, 4) + "0101";
        startDate = "20210801";

        SimpleDateFormat yymm = new SimpleDateFormat("yyyyMMdd");
        Calendar ci = Calendar.getInstance();
        String strToday = yymm.format(ci.getTime());
        viewDto.setCustcd("KDMES");
        viewDto.setSpjangcd("ZZ");
        viewDto.setFdate(startDate);
        viewDto.setTdate(toDate);
        List<TBFplanNowVO> fplanViewListDto = (List<TBFplanNowVO>) appcom01Service.GetPlanViewnow(viewDto);
        model.addAttribute("fplanViewListDto", fplanViewListDto );

        return "AppCom/LayFPLAN_PlanViewSearch";
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


    private String getAddDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, 14); // 빼고 싶다면 음수 입력
        Date date      = new Date(cal1.getTimeInMillis());

        return formatter.format(date);
    }



}
