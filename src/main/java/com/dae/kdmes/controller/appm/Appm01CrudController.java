package com.dae.kdmes.controller.appm;

import com.dae.kdmes.DTO.*;

import com.dae.kdmes.DTO.Appm.*;
import com.dae.kdmes.Service.Appm.AppPopupService;
import com.dae.kdmes.Service.Appm.Appcom01Service;
//import com.dae.kdmes.Service.Appcom02Service;
//import com.dae.kdmes.Service.Appcom03Service;
//import com.dae.kdmes.Service.Appcom04Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// @RestController : return을 텍스트로 반환함.
//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/input", method = RequestMethod.POST)
public class Appm01CrudController {
    private final Appcom01Service appcom01Service;
//    private final Appcom02Service appcom02Service;
//    private final Appcom03Service appcom03Service;
//    private final Appcom04Service appcom04Service;
    private final AppPopupService appPopupService;
    FPLANWPERID_VO wperDto = new FPLANWPERID_VO();
    FPLANWTIME_VO wtimeDto = new FPLANWTIME_VO();
    protected Log log =  LogFactory.getLog(this.getClass());
    Date nowData = new Date();
    SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
    String ToDate = endDate.format(nowData).toString();
    CommonDto CommDto = new CommonDto();
    FPLANW010_VO workDtoDetail = new FPLANW010_VO();

    @RequestMapping(value="/w010", method = RequestMethod.POST)
    public Object AppW010_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("wono") String wono
            ,@RequestParam("cltnm") String cltnm
            ,@RequestParam("pname") String pname
            ,@RequestParam("ostore") String ostore
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrmc") String wrmc
            ,Model model, HttpServletRequest request) throws Exception {

        TBPopupVO wscntDto = new TBPopupVO();
        FPLANW010_VO workDto = new FPLANW010_VO();
//        FPLANIWORK_VO iworkDto = new FPLANIWORK_VO();
//        FPLANWTIME_VO wtimeDto = new FPLANWTIME_VO();
//        FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();
//        FPLANWPERID_VO wperDto = new FPLANWPERID_VO();
        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setWono(wono);
        workDto.setPlan_no(plan_no);
        wflag = "00010";
        workDto.setWflag(wflag);
        workDto.setWrmc(wrmc);
        workDto.setWseq("01");
        workDto.setAseq("0");
        workDto.setDecision("1");
        workDto.setDecision1("1");
        workDto.setWtable("TB_FPLAN_W010");
        wscntDto.setPlan_no(plan_no);
        wperDto.setCustcd(custcd);
        wperDto.setSpjangcd(spjangcd);
        wperDto.setWseq("01");
        wperDto.setWflag(wflag);
        wperDto.setSeq("001");
        wperDto.setPlan_no(plan_no);
        wtimeDto.setCustcd(custcd);
        wtimeDto.setSpjangcd(spjangcd);
        wtimeDto.setPlan_no(plan_no);
        wtimeDto.setWseq("01");
        wtimeDto.setSeq("001");
        wtimeDto.setWflag(wflag);

//        String ls_seq = appcom01Service.GetWtimeWseq(wscntDto);
//        wscntDto.setWflag(wflag);
//        if(ls_seq == null){
//            workDto.setWseq("01");
//            appcom01Service.FPLANWORK_Insert(workDto);
//            appcom01Service.FPLANW010_Insert(workDto);
////            appcom01Service.FPLAN_WPERID_Insert(wperDto);
////            appcom01Service.FPLAN_WTIME_Insert(wtimeDto);
//
////            appcom01Service.FPLANI_WORK_Insert(workDto);
////            appcom01Service.FPLAN_WBAD_Insert(wbadDto);
////            appcom01Service.FPLAN_OWORK_Insert(iworkDto);
//        }else{
//            if(ls_seq.length() == 1){ls_seq = "0" + ls_seq;}
//            workDto.setWseq(ls_seq);
//            //appcom01Service.FPLANWORK_Update(workDto);
//            //appcom01Service.FPLANW010_Update(workDto);
//        }
//
//        appcom01Service.FPLAN_Update(workDto);
        return appcom01Service.FPLAN_WPERID_SELECT(workDto);
    }

    @RequestMapping(value="/w010ordlist", method = RequestMethod.POST)
    public Object AppW010OrdList_index(@RequestParam("frdate") String frdate
            ,Model model, HttpServletRequest request) throws Exception {


        CommDto.setMenuTitle("사출공정");  //
        CommDto.setMenuUrl("생산공정>사출공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Calendar frcal = Calendar.getInstance();
        Calendar tocal = Calendar.getInstance();
        Date ldFrdate = df.parse(frdate);
        frcal.setTime(ldFrdate);
        tocal.setTime(ldFrdate);

//            System.out.println("current: " + df.format(cal.getTime()));

        frcal.add(Calendar.DATE, -14);
        tocal.add(Calendar.DATE, 7);
        System.out.println("frdate: " + df.format(frcal.getTime()));
        frdate = df.format(frcal.getTime()).toString();
        String todate = df.format(tocal.getTime()).toString();

        frdate = setDateFormat(frdate);;
        todate = setDateFormat(todate);

        fplanDto.setLine("00");
        fplanDto.setWflag("00010");
        fplanDto.setFdate(frdate);
        fplanDto.setTdate(todate);
        fplanDto.setCltcd("%");
        fplanDto.setPcode("%");
        itemDtoList = appcom01Service.GetFPLAN_List(fplanDto);

        return itemDtoList;
    }



    @RequestMapping(value="/w020", method = RequestMethod.POST)
    public Object AppW020_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("wono") String wono
            ,@RequestParam("cltnm") String cltnm
            ,@RequestParam("pname") String pname
            ,@RequestParam("ostore") String ostore
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrmc") String wrmc
            ,Model model, HttpServletRequest request) throws Exception {

        TBPopupVO wscntDto = new TBPopupVO();
        FPLANW010_VO workDto = new FPLANW010_VO();
        FPLANWPERID_VO wperDto = new FPLANWPERID_VO();
        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setWono(wono);
        workDto.setPlan_no(plan_no);
        wflag = "00020";
        workDto.setWflag(wflag);
        workDto.setWrmc(wrmc);
        workDto.setWseq("02");
        workDto.setAseq("0");
        workDto.setDecision("1");
        workDto.setDecision2("1");
        workDto.setWtable("TB_FPLAN_W020");

        wperDto.setCustcd(custcd);
        wperDto.setSpjangcd(spjangcd);
        wperDto.setWseq("02");
        wperDto.setWflag(wflag);
        wperDto.setSeq("001");
        wperDto.setPlan_no(plan_no);

        wtimeDto.setCustcd(custcd);
        wtimeDto.setSpjangcd(spjangcd);
        wtimeDto.setPlan_no(plan_no);
        wtimeDto.setWseq("02");
        wtimeDto.setSeq("001");
        wtimeDto.setWflag(wflag);
        return null;
       // return appcom02Service.FPLAN_WPERID_SELECT(workDto);
    }


    @RequestMapping(value="/w020ordlist", method = RequestMethod.POST)
    public Object AppW020OrdList_index(@RequestParam("lotno") String lotno
            ,Model model, HttpServletRequest request) throws Exception {


        CommDto.setMenuTitle("검사공정");  //
        CommDto.setMenuUrl("생산공정>검사공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        String fdate = getFrDate();
        String tdate = getToDate();
        fplanDto.setLine("00");
        fplanDto.setWflag("00020");
        fplanDto.setFdate(fdate);
        fplanDto.setTdate(tdate);
        fplanDto.setCltcd("%");
        fplanDto.setPcode("%");
        if(lotno.length() == 0 ){
            log.info("lotno.length() == 0 =====>"  );
            itemDtoList = appcom01Service.GetFPLAN_List02(fplanDto);
        }else{
            String[] arrLotno = lotno.split(",");
            HashMap hm = new HashMap();
            hm.put("itemcdArr", arrLotno) ;
            fplanDto.setCltcd("%");
            fplanDto.setPcode("%");
            itemDtoList = appcom01Service.GetFPLAN_List02Arr(hm);
        }

        return itemDtoList;
    }

    @RequestMapping(value="/w030", method = RequestMethod.POST)
    public Object AppW030_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("wono") String wono
            ,@RequestParam("cltnm") String cltnm
            ,@RequestParam("pname") String pname
            ,@RequestParam("ostore") String ostore
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrmc") String wrmc
            ,Model model, HttpServletRequest request) throws Exception {

        TBPopupVO wscntDto = new TBPopupVO();
        FPLANW010_VO workDto = new FPLANW010_VO();
        FPLANWPERID_VO wperDto = new FPLANWPERID_VO();
        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setWono(wono);
        workDto.setPlan_no(plan_no);
        wflag = "00030";
        workDto.setWflag(wflag);
        workDto.setWrmc(wrmc);
        workDto.setWseq("03");
        workDto.setAseq("0");
        workDto.setDecision("1");
        workDto.setDecision3("1");
        workDto.setWtable("TB_FPLAN_W030");

        wperDto.setCustcd(custcd);
        wperDto.setSpjangcd(spjangcd);
        wperDto.setWseq("03");
        wperDto.setWflag(wflag);
        wperDto.setSeq("001");
        wperDto.setPlan_no(plan_no);

        wtimeDto.setCustcd(custcd);
        wtimeDto.setSpjangcd(spjangcd);
        wtimeDto.setPlan_no(plan_no);
        wtimeDto.setWseq("03");
        wtimeDto.setSeq("001");
        wtimeDto.setWflag(wflag);
        return null;
        //return appcom03Service.FPLAN_WPERID_SELECT(workDto);
    }


    @RequestMapping(value="/w030ordlist", method = RequestMethod.POST)
    public Object AppW030OrdList_index(@RequestParam("frdate") String frdate
            ,Model model, HttpServletRequest request) throws Exception {


        CommDto.setMenuTitle("절단공정");  //
        CommDto.setMenuUrl("생산공정>절단공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Calendar frcal = Calendar.getInstance();
        Calendar tocal = Calendar.getInstance();
        Date ldFrdate = df.parse(frdate);
        frcal.setTime(ldFrdate);
        tocal.setTime(ldFrdate);

//            System.out.println("current: " + df.format(cal.getTime()));

        frcal.add(Calendar.DATE, -14);
        tocal.add(Calendar.DATE, 7);
        System.out.println("frdate: " + df.format(frcal.getTime()));
        frdate = df.format(frcal.getTime()).toString();
        String todate = df.format(tocal.getTime()).toString();

        frdate = setDateFormat(frdate);
        todate = setDateFormat(todate);

        fplanDto.setLine("00");
        fplanDto.setWflag("00010");
        fplanDto.setFdate(frdate);
        fplanDto.setTdate(todate);
        fplanDto.setCltcd("%");
        fplanDto.setPcode("%");
        //itemDtoList = appcom03Service.GetFPLAN_List(fplanDto);

        return itemDtoList;
    }

    @RequestMapping(value="/w040", method = RequestMethod.POST)
    public Object AppW040_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("wono") String wono
            ,@RequestParam("cltnm") String cltnm
            ,@RequestParam("pname") String pname
            ,@RequestParam("ostore") String ostore
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrmc") String wrmc
            ,Model model, HttpServletRequest request) throws Exception {

        TBPopupVO wscntDto = new TBPopupVO();
        FPLANW010_VO workDto = new FPLANW010_VO();
        FPLANWPERID_VO wperDto = new FPLANWPERID_VO();
        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setWono(wono);
        workDto.setPlan_no(plan_no);
        wflag = "00090";
        workDto.setWflag(wflag);
        workDto.setWrmc(wrmc);
        workDto.setWseq("04");
        workDto.setAseq("0");
        workDto.setWtable("TB_FPLAN_W090");

        wperDto.setCustcd(custcd);
        wperDto.setSpjangcd(spjangcd);
        wperDto.setWseq("04");
        wperDto.setWflag(wflag);
        wperDto.setSeq("001");
        workDto.setDecision("1");
        workDto.setDecision4("1");
        wperDto.setPlan_no(plan_no);

        wtimeDto.setCustcd(custcd);
        wtimeDto.setSpjangcd(spjangcd);
        wtimeDto.setPlan_no(plan_no);
        wtimeDto.setWseq("04");
        wtimeDto.setSeq("001");
        wtimeDto.setWflag(wflag);
        return null;
        //return appcom04Service.FPLAN_WPERID_SELECT(workDto);
    }


    @RequestMapping(value="/w040ordlist", method = RequestMethod.POST)
    public Object AppW040OrdList_index(@RequestParam("frdate") String frdate
            ,Model model, HttpServletRequest request) throws Exception {


        CommDto.setMenuTitle("절단공정");  //
        CommDto.setMenuUrl("생산공정>절단공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Calendar frcal = Calendar.getInstance();
        Calendar tocal = Calendar.getInstance();
        Date ldFrdate = df.parse(frdate);
        frcal.setTime(ldFrdate);
        tocal.setTime(ldFrdate);

//            System.out.println("current: " + df.format(cal.getTime()));

        frcal.add(Calendar.DATE, -14);
        tocal.add(Calendar.DATE, 7);
        System.out.println("frdate: " + df.format(frcal.getTime()));
        frdate = df.format(frcal.getTime()).toString();
        String todate = df.format(tocal.getTime()).toString();

        fplanDto.setLine("00");
        fplanDto.setWflag("00010");
        fplanDto.setFdate(setDateFormat(frdate));
        fplanDto.setTdate(setDateFormat(todate));
        fplanDto.setCltcd("%");
        fplanDto.setPcode("%");
        //itemDtoList = appcom04Service.GetFPLAN_List(fplanDto);

        return itemDtoList;
    }


    @RequestMapping(value="/w010upd", method = RequestMethod.POST)
    public String AppW010Update_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("wseq") String wseq
            ,@RequestParam("startDate") String startDate
            ,@RequestParam("endDate") String endDate
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wrmc") String wrmc
            ,@RequestParam("winqt") float winqt
            ,@RequestParam("wbdqt") float wbdqt
            ,@RequestParam("wotqt") float wotqt
            ,@RequestParam("wsyul") float wsyul
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wremark") String wremark
            ,@RequestParam("wrps") String wrps
            ,@RequestParam("winps") Integer winps
            ,@RequestParam("decision") String decision
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("wono") String wono
            ,Model model, HttpServletRequest request) throws Exception {

        String ls_flag = decision;
        if(startDate.equals("") || startDate == null || startDate.equals(" ") || startDate.length() ==0 ){
            startDate = null;
        }
        if(endDate.equals("") || endDate == null || endDate.equals(" ") || endDate.length() ==0  ){
            endDate = null;
        }
        if(decision.equals("1") || decision.equals("4") ){   //공정시작과 저장버튼 클릭
            endDate = null;  //공정완료일경우만 종료일이 입력된다.
            wtimeDto.setWopdv("0");
            decision = "1";
        }else{
            wtimeDto.setWopdv("1"); //비가동
        }

        FPLANW010_VO workDto = new FPLANW010_VO();
        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setWseq("01");
        workDto.setWfrdt(startDate);
        workDto.setWtrdt(endDate);

        workDto.setPlan_no(plan_no);
        workDto.setWrmc(wrmc);
        workDto.setWinqt(winqt);
        workDto.setWqty(winqt);
        workDto.setBqty(wbdqt);
        workDto.setWbdqt(wbdqt);
        workDto.setQty(wotqt);
        workDto.setWotqt(wotqt);
        workDto.setJqty(wotqt - wbdqt);
        workDto.setWflag("00010");
        workDto.setWsyul(wsyul);
        workDto.setWremark(wremark);
        workDto.setDecision(decision);
        workDto.setDecision1(decision);
        workDto.setAseq("0");
        workDto.setWrps(wrps);
        workDto.setWinps(winps);
        workDto.setStore("W01");
        workDto.setPcode(pcode);
        workDto.setWono(wono);
        String ls_lotno = wono.substring(2,11);
        workDto.setLotno(ls_lotno);
        String wstdt = "";
        String wendt = "";
        if(startDate != null){
            wstdt = setDateFormat(startDate);
            wendt = setDateFormat(startDate);
        }else{
            wstdt = "" ;
            wendt = "";
        }

        workDto.setWstdt(wstdt);
        if(decision.equals("0")){
            workDto.setWendt(wendt);
        }else{
            workDto.setWendt(endDate);
        }
        workDto.setIndate(getToDate());
        workDto.setWtable("TB_FPLAN_W010");

        wtimeDto.setCustcd(custcd);
        wtimeDto.setSpjangcd(spjangcd);
        wtimeDto.setPlan_no(plan_no);
        wtimeDto.setWseq("01");
        wtimeDto.setSeq("001");
        wtimeDto.setWflag(wflag);
        wtimeDto.setWfrdt(startDate);
        workDto.setClsflag("2");        // 공정중
//        workDtoDetail = (FPLANW010_VO) appcom01Service.GetFPLANW010_Detail(workDto);
        if(appcom01Service.GetFPLANW010_Detail(workDto) == null){
            appcom01Service.FPLANW010_Insert(workDto);
            appcom01Service.FPLANWORK_Insert(workDto);
            appcom01Service.FPLAN_WTIME_Insert(wtimeDto);
        }else{
            appcom01Service.FPLANW010_Update(workDto);
            appcom01Service.FPLANWORK_Update(workDto);
        }
        //불량수량이 있다면 owork에 업데이트
//        if(wbdqt > 0) {appcom01Service.FPLAN_OWORK_Update(workDto);}
//        if (wotqt > 0) {appcom01Service.FPLAN_OWORK_Insert(workDto);}


        // if (decision == "0"){
        workDto.setEnd_qty(wotqt - wbdqt);
        //}else{
        //    endDate = "";
        //    workDto.setEnd_qty(0);
        //}
//        workDto.setWtrdt(wendt);
        workDto.setProd_qty(wotqt);

//        appcom01Service.FPLANI_IWORK_update(workDto);
//        appcom01Service.FPLAN_WPERID_Delete(wperDto);
//        appcom01Service.FPLAN_WPERID_Insert(wperDto);


        if(decision.equals("0")){
            wtimeDto.setWtrdt(getToDateTime());
            appcom01Service.FPLAN_WTIME_Update(wtimeDto);
            workDto.setWflag("00020");
        }
        appcom01Service.FPLAN_Update(workDto);
        return "TB_FPLAN_W010 UPDATE OK";
    }

    @RequestMapping(value="/wtimeupd", method = RequestMethod.POST)
    public String AppWTimeUpdate_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("decision") String decision
            ,Model model, HttpServletRequest request) throws Exception {

        wtimeDto.setCustcd(custcd);
        wtimeDto.setSpjangcd(spjangcd);
        wtimeDto.setPlan_no(plan_no);
        wtimeDto.setWflag(wflag);
        switch (wflag){
            case "00010":
                wtimeDto.setWseq("01");
                break;
            case "00020":
                wtimeDto.setWseq("02");
                break;
            case "00030":
                wtimeDto.setWseq("03");
                break;
            case "00090":
                wtimeDto.setWseq("04");
                break;
            default:
                break;
        }

        if(decision.equals("2")){     //작업시작
            wtimeDto.setSeq(getWtimeMaxSeq());
            wtimeDto.setWfrdt(getToDateTime());
            wtimeDto.setWtrdt(getToDateTime());
            appcom01Service.FPLAN_WTIME_Update(wtimeDto);
            wtimeDto.setWtrdt(null);
            appcom01Service.FPLAN_WTIME_Insert(wtimeDto);
        }else{                        //작업종료
            wtimeDto.setWtrdt(getToDateTime());
            appcom01Service.FPLAN_WTIME_Update(wtimeDto);
        }
        return "success";
    }


    @RequestMapping(value="/w020upd", method = RequestMethod.POST)
    public String AppW020Update_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("wseq") String wseq
            ,@RequestParam("startDate") String startDate
            ,@RequestParam("endDate") String endDate
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wrmc") String wrmc
            ,@RequestParam("winqt") float winqt
            ,@RequestParam("wbdqt") float wbdqt
            ,@RequestParam("wotqt") float wotqt
            ,@RequestParam("wsyul") float wsyul
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrps") String wrps
            ,@RequestParam("winps") Integer winps
            ,@RequestParam("wremark") String wremark
            ,@RequestParam("decision") String decision
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("wono") String wono
            ,Model model, HttpServletRequest request) throws Exception {


        String ls_flag = decision;
        if(startDate.equals("") || startDate == null || startDate.equals(" ") || startDate.length() ==0 ){
            startDate = null;
        }
        if(endDate.equals("") || endDate == null || endDate.equals(" ") || endDate.length() ==0  ){
            endDate = null;
        }
        if(decision.equals("1") || decision.equals("4") ){   //공정시작과 저장버튼 클릭
            endDate = null;  //공정완료일경우만 종료일이 입력된다.
            wtimeDto.setWopdv("0");
            decision = "1";
        }else{
            wtimeDto.setWopdv("1"); //비가동
        }


        FPLANW010_VO workDto = new FPLANW010_VO();
        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setWseq("02");
        workDto.setWfrdt(startDate);
        workDto.setWtrdt(endDate);
        workDto.setPlan_no(plan_no);
        workDto.setWrmc(wrmc);
        workDto.setWinqt(winqt);
        workDto.setWqty(winqt);
        workDto.setBqty(wbdqt);
        workDto.setWbdqt(wbdqt);
        workDto.setQty(wotqt);
        workDto.setWotqt(wotqt);
        workDto.setJqty(wotqt - wbdqt);
        workDto.setWflag("00020");
        workDto.setWsyul(wsyul);
        workDto.setWremark(wremark);
        workDto.setDecision(decision);
        workDto.setDecision2(decision);
        workDto.setAseq("0");
        workDto.setWrps(wrps);
        workDto.setWinps(winps);
        workDto.setStore("W01");
        workDto.setPcode(pcode);
        workDto.setWono(wono);
        String ls_lotno = wono.substring(2,11);
        workDto.setLotno(ls_lotno);
        String wstdt = "";
        String wendt = "";
        if(startDate != null){
            wstdt = setDateFormat(startDate);
            wendt = setDateFormat(startDate);
        }else{
            wstdt = "" ;
            wendt = "";
        }
        workDto.setWstdt(wstdt);
        if(decision.equals("0")){
            workDto.setWendt(wendt);
        }else{
            workDto.setWendt(endDate);
        }

        workDto.setIndate(getToDate());
        workDto.setWtable("TB_FPLAN_W020");

        wtimeDto.setCustcd(custcd);
        wtimeDto.setSpjangcd(spjangcd);
        wtimeDto.setPlan_no(plan_no);
        wtimeDto.setWseq("02");
        wtimeDto.setSeq("001");
        wtimeDto.setWflag(wflag);
        wtimeDto.setWfrdt(startDate);
        workDto.setClsflag("2");        // 공정중

//        if(appcom02Service.GetFPLANW020_Detail(workDto) == null){
//            appcom02Service.FPLANW020_Insert(workDto);
//            appcom01Service.FPLANWORK_Insert(workDto);
//            appcom01Service.FPLAN_WTIME_Insert(wtimeDto);
//        }else{
//            appcom02Service.FPLANW020_Update(workDto);
//            appcom01Service.FPLANWORK_Update(workDto);
//        }
//
//        String ls_seq = appcom01Service.FPLAN_IWORK_MAXWSEQ(workDto);
//        if(ls_seq == null){
//            ls_seq = "001";
//        }else{
//            int ll_seq =  Integer.parseInt(ls_seq) + 1;
//            ls_seq = Integer.toString(ll_seq);
//            if(ls_seq.length() == 1){
//                ls_seq = "00" + ls_seq;
//            }else if(ls_seq.length() == 2){
//                ls_seq = "0" + ls_seq;
//            }
//        }
//        workDto.setSeq(ls_seq);
//        workDto.setJqty(wotqt);
//        appcom01Service.FPLANI_IWORK_Insert(workDto);
//
//
//        workDto.setEnd_qty(wotqt - wbdqt);
//        workDto.setProd_qty(wotqt);
//
//
//
//        appcom02Service.FPLANI_IWORK_update(workDto);


        if(decision.equals("0")){
            wtimeDto.setWtrdt(getToDateTime());
            appcom01Service.FPLAN_WTIME_Update(wtimeDto);
            workDto.setWflag("00030");
        }
        appcom01Service.FPLAN_Update(workDto);
        return "TB_FPLAN_W020 UPDATE OK";
    }

    @RequestMapping(value="/w030upd", method = RequestMethod.POST)
    public String AppW030Update_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("wseq") String wseq
            ,@RequestParam("startDate") String startDate
            ,@RequestParam("endDate") String endDate
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wrmc") String wrmc
            ,@RequestParam("winqt") float winqt
            ,@RequestParam("wbdqt") float wbdqt
            ,@RequestParam("wotqt") float wotqt
            ,@RequestParam("wsyul") float wsyul
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrps") String wrps
            ,@RequestParam("winps") Integer winps
            ,@RequestParam("wremark") String wremark
            ,@RequestParam("decision") String decision
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("wono") String wono
            ,Model model, HttpServletRequest request) throws Exception {

        String ls_flag = decision;
        if(startDate.equals("") || startDate == null || startDate.equals(" ") || startDate.length() ==0 ){
            startDate = null;
        }
        if(endDate.equals("") || endDate == null || endDate.equals(" ") || endDate.length() ==0  ){
            endDate = null;
        }
        if(decision.equals("1") || decision.equals("4") ){   //공정시작과 저장버튼 클릭
            endDate = null;  //공정완료일경우만 종료일이 입력된다.
            wtimeDto.setWopdv("0");
            decision = "1";
        }else{
            wtimeDto.setWopdv("1"); //비가동
        }



        FPLANW010_VO workDto = new FPLANW010_VO();
        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setWseq("03");
        workDto.setWfrdt(startDate);
        workDto.setWtrdt(endDate);
        workDto.setPlan_no(plan_no);
        workDto.setWrmc(wrmc);

        workDto.setWinqt(winqt);
        workDto.setWqty(winqt);
        workDto.setBqty(wbdqt);
        workDto.setWbdqt(wbdqt);
        workDto.setQty(wotqt - wbdqt);
        workDto.setWotqt(wotqt);
        workDto.setJqty(wotqt - wbdqt);


        workDto.setWflag("00030");
        workDto.setWsyul(wsyul);
        workDto.setWremark(wremark);
        workDto.setDecision(decision);
        workDto.setDecision3(decision);
        workDto.setAseq("0");
        workDto.setWrps(wrps);
        workDto.setWinps(winps);
        workDto.setStore("W01");
        workDto.setPcode(pcode);
        workDto.setWono(wono);
        String ls_lotno = wono.substring(2,11);
        workDto.setLotno(ls_lotno);

        String wstdt = "";
        String wendt = "";
        if(startDate != null){
            wstdt = setDateFormat(startDate);
            wendt = setDateFormat(startDate);
        }else{
            wstdt = "" ;
            wendt = "";
        }

        workDto.setWstdt(wstdt);
        if(decision.equals("0")){
            workDto.setWendt(wendt);
        }else{
            workDto.setWendt(endDate);
        }
        workDto.setIndate(getToDate());
        workDto.setWtable("TB_FPLAN_W030");



        wtimeDto.setCustcd(custcd);
        wtimeDto.setSpjangcd(spjangcd);
        wtimeDto.setPlan_no(plan_no);
        wtimeDto.setWseq("03");
        wtimeDto.setSeq("001");
        wtimeDto.setWflag(wflag);
        wtimeDto.setWfrdt(startDate);
        workDto.setClsflag("2");        // 공정중

//        if(appcom03Service.GetFPLANW030_Detail(workDto) == null){
//            appcom03Service.FPLANW030_Insert(workDto);
//            appcom01Service.FPLANWORK_Insert(workDto);
//            appcom01Service.FPLAN_WTIME_Insert(wtimeDto);
//        }else{
//            appcom03Service.FPLANW030_Update(workDto);
//            appcom01Service.FPLANWORK_Update(workDto);
//        }

        String ls_seq = appcom01Service.FPLAN_IWORK_MAXWSEQ(workDto);
        if(ls_seq == null){
            ls_seq = "001";
        }else{
            int ll_seq =  Integer.parseInt(ls_seq) + 1;
            ls_seq = Integer.toString(ll_seq);
            if(ls_seq.length() == 1){
                ls_seq = "00" + ls_seq;
            }else if(ls_seq.length() == 2){
                ls_seq = "0" + ls_seq;
            }
        }
        workDto.setSeq(ls_seq);
        workDto.setJqty(wotqt);
        appcom01Service.FPLANI_IWORK_Insert(workDto);

        workDto.setEnd_qty(wotqt - wbdqt);
        workDto.setProd_qty(wotqt);




        if(decision.equals("0")){
            wtimeDto.setWtrdt(getToDateTime());
            appcom01Service.FPLAN_WTIME_Update(wtimeDto);
            workDto.setWflag("00090");
        }
        appcom01Service.FPLAN_Update(workDto);
        return "TB_FPLAN_W030 UPDATE OK";
    }

    @RequestMapping(value="/w040upd", method = RequestMethod.POST)
    public String AppW040Update_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("wseq") String wseq
            ,@RequestParam("startDate") String startDate
            ,@RequestParam("endDate") String endDate
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wrmc") String wrmc
            ,@RequestParam("winqt") float winqt
            ,@RequestParam("wbdqt") float wbdqt
            ,@RequestParam("wotqt") float wotqt
            ,@RequestParam("wsyul") float wsyul
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrps") String wrps
            ,@RequestParam("winps") Integer winps
            ,@RequestParam("wremark") String wremark
            ,@RequestParam("decision") String decision
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("wono") String wono
            ,Model model, HttpServletRequest request) throws Exception {

        String ls_flag = decision;
        if(startDate.equals("") || startDate == null || startDate.equals(" ") || startDate.length() ==0 ){
            startDate = null;
        }
        if(endDate.equals("") || endDate == null || endDate.equals(" ") || endDate.length() ==0  ){
            endDate = null;
        }
        if(decision.equals("1") || decision.equals("4") ){   //공정시작과 저장버튼 클릭
            endDate = null;  //공정완료일경우만 종료일이 입력된다.
            wtimeDto.setWopdv("0");
            decision = "1";
        }else{
            wtimeDto.setWopdv("1"); //비가동
        }



        FPLANW010_VO workDto = new FPLANW010_VO();
        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setWseq("04");
        workDto.setWfrdt(startDate);
        workDto.setWtrdt(endDate);
        workDto.setPlan_no(plan_no);
        workDto.setWrmc(wrmc);

        workDto.setWinqt(winqt);
        workDto.setWqty(wotqt - wbdqt);
        workDto.setBqty(wbdqt);
        workDto.setWbdqt(wbdqt);
        workDto.setQty(wotqt - wbdqt);
        workDto.setWotqt(wotqt);
        workDto.setJqty(wotqt - wbdqt);

        workDto.setWflag("00090");
        workDto.setWsyul(wsyul);
        workDto.setWremark(wremark);
        workDto.setDecision(decision);
        workDto.setDecision4(decision);
        workDto.setAseq("0");
        workDto.setWrps(wrps);
        workDto.setWinps(winps);
        workDto.setStore("P01");
        workDto.setPcode(pcode);
        workDto.setWono(wono);
        String ls_lotno = wono.substring(2,11);
        workDto.setLotno(ls_lotno);

        String wstdt = "";
        String wendt = "";
        if(startDate != null){
            wstdt = setDateFormat(startDate);
            wendt = setDateFormat(startDate);
        }else{
            wstdt = null ;
            wendt = null;
        }

        workDto.setWstdt(wstdt);
        if(decision.equals("0")){
            workDto.setWendt(wendt);
        }else{
            workDto.setWendt(endDate);
        }
        workDto.setIndate(getToDate());
        workDto.setWtable("TB_FPLAN_W090");


        wtimeDto.setCustcd(custcd);
        wtimeDto.setSpjangcd(spjangcd);
        wtimeDto.setPlan_no(plan_no);
        wtimeDto.setWseq("04");
        wtimeDto.setSeq("001");
        wtimeDto.setWflag(wflag);
        wtimeDto.setWfrdt(startDate);
        workDto.setClsflag("2");        // 공정중
//        if(appcom04Service.GetFPLANW040_CHKDATA(workDto) == null){
//            appcom04Service.FPLANW040_Insert(workDto);
//            appcom01Service.FPLANWORK_Insert(workDto);
//            appcom01Service.FPLAN_WTIME_Insert(wtimeDto);
//        }else{
//            appcom04Service.FPLANW040_Update(workDto);
//            appcom01Service.FPLANWORK_Update(workDto);
//        }


        String ls_seq = appcom01Service.FPLAN_IWORK_MAXWSEQ(workDto);
        if(ls_seq == null){
            ls_seq = "001";
        }else{
            int ll_seq =  Integer.parseInt(ls_seq) + 1;
            ls_seq = Integer.toString(ll_seq);
            if(ls_seq.length() == 1){
                ls_seq = "00" + ls_seq;
            }else if(ls_seq.length() == 2){
                ls_seq = "0" + ls_seq;
            }
        }
        workDto.setSeq(ls_seq);
        workDto.setJqty(wotqt);
        appcom01Service.FPLANI_IWORK_Insert(workDto);
        workDto.setEnd_qty(wotqt - wbdqt);
        workDto.setProd_qty(wotqt);




        if(decision.equals("0")){
            wtimeDto.setWtrdt(getToDateTime());
            appcom01Service.FPLAN_WTIME_Update(wtimeDto);
            workDto.setClsflag("4");
        }
        appcom01Service.FPLAN_Update(workDto);



        return "TB_FPLAN_W040 UPDATE OK";
    }


    @ResponseBody
    @RequestMapping(value="/w010perid", method = RequestMethod.POST)
    public String AppW010Perid_index(@RequestParam(value = "custcd[]") List<String> custcd
            ,@RequestParam( value =  "spjangcd[]") List<String> spjangcd
            ,@RequestParam( value =  "linecd[]") List<String> linecd
            ,@RequestParam( value =  "linenm[]") List<String> linenm
            ,@RequestParam( value =  "wflagcd[]") List<String> wflagcd
            ,@RequestParam( value =  "wflagnm[]") List<String> wflagnm
            ,@RequestParam( value =  "wperid[]") List<String> wperid
            ,@RequestParam( value =  "wpernm[]") List<String> wpernm
            ,@RequestParam("plan_no") String plan_no) throws Exception {

        FPLANWPERID_VO wperDto = new FPLANWPERID_VO();

        wperDto.setPlan_no(plan_no);
        wperDto.setWseq("01");
        wperDto.setAseq("01");
        wperDto.setWflag("00010");
        String ls_seq = "0";
        appcom01Service.FPLAN_WPERID_Delete(wperDto);
        if( custcd.size() > 0){
            for(int i = 0; i < custcd.size(); i++){

                int ll_seq = Integer.parseInt(ls_seq) + 1;
                ls_seq = Integer.toString(ll_seq);
                if (ls_seq.length() == 1) {
                    ls_seq = "0" + ls_seq;
                }

                wperDto.setCustcd(custcd.get(i));
                wperDto.setSpjangcd(spjangcd.get(i));
                wperDto.setWflag(wflagcd.get(i));
                wperDto.setSeq(ls_seq);
                wperDto.setPerid(wperid.get(i));
                wperDto.setIndate(getToDate());
                appcom01Service.FPLAN_WPERID_Insert(wperDto);
            }

            FPLANW010_VO workDto = new FPLANW010_VO();
            Integer ll_winps = custcd.size();
            workDto.setPlan_no(plan_no);
            workDto.setWflag("00010");
            workDto.setWseq("01");
            workDto.setWinps(ll_winps);
            workDto.setWrps(wperid.get(0));
            appcom01Service.FPLANW010_Update(workDto);
        }
        return "OK" ;
    }

    @ResponseBody
    @RequestMapping(value="/w020perid", method = RequestMethod.POST)
    public String AppW020Perid_index(@RequestParam(value = "custcd[]") List<String> custcd
            ,@RequestParam( value =  "spjangcd[]") List<String> spjangcd
            ,@RequestParam( value =  "linecd[]") List<String> linecd
            ,@RequestParam( value =  "linenm[]") List<String> linenm
            ,@RequestParam( value =  "wflagcd[]") List<String> wflagcd
            ,@RequestParam( value =  "wflagnm[]") List<String> wflagnm
            ,@RequestParam( value =  "wperid[]") List<String> wperid
            ,@RequestParam( value =  "wpernm[]") List<String> wpernm
            ,@RequestParam("plan_no") String plan_no) throws Exception {

        FPLANWPERID_VO wperDto = new FPLANWPERID_VO();

        wperDto.setPlan_no(plan_no);
        wperDto.setWseq("01");
        wperDto.setAseq("01");
        wperDto.setWflag("00020");
        String ls_seq = "0";
//        appcom02Service.FPLAN_WPERID_Delete(wperDto);
//        if( custcd.size() > 0){
//            for(int i = 0; i < custcd.size(); i++){
//
//                int ll_seq = Integer.parseInt(ls_seq) + 1;
//                ls_seq = Integer.toString(ll_seq);
//                if (ls_seq.length() == 1) {
//                    ls_seq = "0" + ls_seq;
//                }
//                wperDto.setCustcd(custcd.get(i));
//                wperDto.setSpjangcd(spjangcd.get(i));
//                wperDto.setWflag(wflagcd.get(i));
//                wperDto.setSeq(ls_seq);
//                wperDto.setPerid(wperid.get(i));
//                wperDto.setIndate(getToDate());
//                appcom02Service.FPLAN_WPERID_Insert(wperDto);
//            }
//            FPLANW010_VO workDto = new FPLANW010_VO();
//            Integer ll_winps = custcd.size();
//            workDto.setPlan_no(plan_no);
//            workDto.setWflag("00020");
//            workDto.setWseq("02");
//            workDto.setWinps(ll_winps);
//            workDto.setWrps(wperid.get(0));
//            appcom02Service.FPLANW020_Update(workDto);
//        }
        return "OK" ;
    }

    @ResponseBody
    @RequestMapping(value="/w030perid", method = RequestMethod.POST)
    public String AppW030Perid_index(@RequestParam(value = "custcd[]") List<String> custcd
            ,@RequestParam( value =  "spjangcd[]") List<String> spjangcd
            ,@RequestParam( value =  "linecd[]") List<String> linecd
            ,@RequestParam( value =  "linenm[]") List<String> linenm
            ,@RequestParam( value =  "wflagcd[]") List<String> wflagcd
            ,@RequestParam( value =  "wflagnm[]") List<String> wflagnm
            ,@RequestParam( value =  "wperid[]") List<String> wperid
            ,@RequestParam( value =  "wpernm[]") List<String> wpernm
            ,@RequestParam("plan_no") String plan_no) throws Exception {

        FPLANWPERID_VO wperDto = new FPLANWPERID_VO();

        wperDto.setPlan_no(plan_no);
        wperDto.setWseq("01");
        wperDto.setAseq("01");
        wperDto.setWflag("00030");
        String ls_seq = "0";
//        appcom03Service.FPLAN_WPERID_Delete(wperDto);
//        if( custcd.size() > 0){
//            for(int i = 0; i < custcd.size(); i++){
//
//                int ll_seq = Integer.parseInt(ls_seq) + 1;
//                ls_seq = Integer.toString(ll_seq);
//                if (ls_seq.length() == 1) {
//                    ls_seq = "0" + ls_seq;
//                }
//                wperDto.setCustcd(custcd.get(i));
//                wperDto.setSpjangcd(spjangcd.get(i));
//                wperDto.setWflag(wflagcd.get(i));
//                wperDto.setSeq(ls_seq);
//                wperDto.setPerid(wperid.get(i));
//                wperDto.setIndate(getToDate());
//                appcom03Service.FPLAN_WPERID_Insert(wperDto);
//            }
//            FPLANW010_VO workDto = new FPLANW010_VO();
//            Integer ll_winps = custcd.size();
//            workDto.setPlan_no(plan_no);
//            workDto.setWflag("00030");
//            workDto.setWseq("03");
//            workDto.setWinps(ll_winps);
//            workDto.setWrps(wperid.get(0));
//            appcom03Service.FPLANW030_Update(workDto);
//        }
        return "OK" ;
    }


    @ResponseBody
    @RequestMapping(value="/w040perid", method = RequestMethod.POST)
    public String AppW040Perid_index(@RequestParam(value = "custcd[]") List<String> custcd
            ,@RequestParam( value =  "spjangcd[]") List<String> spjangcd
            ,@RequestParam( value =  "linecd[]") List<String> linecd
            ,@RequestParam( value =  "linenm[]") List<String> linenm
            ,@RequestParam( value =  "wflagcd[]") List<String> wflagcd
            ,@RequestParam( value =  "wflagnm[]") List<String> wflagnm
            ,@RequestParam( value =  "wperid[]") List<String> wperid
            ,@RequestParam( value =  "wpernm[]") List<String> wpernm
            ,@RequestParam("plan_no") String plan_no) throws Exception {

        FPLANWPERID_VO wperDto = new FPLANWPERID_VO();

        wperDto.setPlan_no(plan_no);
        wperDto.setWseq("01");
        wperDto.setAseq("01");
        wperDto.setWflag("00090");
        String ls_seq = "0";
//        appcom04Service.FPLAN_WPERID_Delete(wperDto);
//        if( custcd.size() > 0){
//            for(int i = 0; i < custcd.size(); i++){
//
//                int ll_seq = Integer.parseInt(ls_seq) + 1;
//                ls_seq = Integer.toString(ll_seq);
//                if (ls_seq.length() == 1) {
//                    ls_seq = "0" + ls_seq;
//                }
//                wperDto.setCustcd(custcd.get(i));
//                wperDto.setSpjangcd(spjangcd.get(i));
//                wperDto.setWflag(wflagcd.get(i));
//                wperDto.setSeq(ls_seq);
//                wperDto.setPerid(wperid.get(i));
//                wperDto.setIndate(getToDate());
//                appcom04Service.FPLAN_WPERID_Insert(wperDto);
//            }
//            FPLANW010_VO workDto = new FPLANW010_VO();
//            Integer ll_winps = custcd.size();
//            workDto.setPlan_no(plan_no);
//            workDto.setWflag("00090");
//            workDto.setWseq("04");
//            workDto.setWinps(ll_winps);
//            workDto.setWrps(wperid.get(0));
//            appcom04Service.FPLANW040_Update(workDto);
//        }
        return "OK" ;
    }

    //작업설비 update 조회
    @RequestMapping(value="/w010wrmc", method = RequestMethod.POST)
    public String AppW010Wrmc_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrmc") String wrmc) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();

        itemDto.setPlan_no(plan_no);
        itemDto.setWflag(wflag);
        itemDto.setWseq("01");
        itemDto.setWrmc(wrmc);
        itemDto.setIndate(getToDate());
        appcom01Service.FPLANW010_WrmcUpdate(itemDto);
        return "UPDATE OK";

    }

    //작업설비 update 조회
    @RequestMapping(value="/w020wrmc", method = RequestMethod.POST)
    public String AppW020Wrmc_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrmc") String wrmc) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();

        itemDto.setPlan_no(plan_no);
        itemDto.setWflag(wflag);
        itemDto.setWseq("01");
        itemDto.setWrmc(wrmc);
        itemDto.setIndate(getToDate());
//        appcom02Service.FPLANW020_WrmcUpdate(itemDto);
        return "UPDATE OK";

    }

    //작업설비 update 조회
    @RequestMapping(value="/w030wrmc", method = RequestMethod.POST)
    public String AppW030Wrmc_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrmc") String wrmc) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();

        itemDto.setPlan_no(plan_no);
        itemDto.setWflag(wflag);
        itemDto.setWseq("01");
        itemDto.setWrmc(wrmc);
        itemDto.setIndate(getToDate());
//        appcom03Service.FPLANW030_WrmcUpdate(itemDto);
        return "UPDATE OK";

    }

    //작업설비 update 조회
    @RequestMapping(value="/w040wrmc", method = RequestMethod.POST)
    public String AppW040Wrmc_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wrmc") String wrmc) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();

        itemDto.setPlan_no(plan_no);
        itemDto.setWflag(wflag);
        itemDto.setWseq("01");
        itemDto.setWrmc(wrmc);
        itemDto.setIndate(getToDate());
//        appcom04Service.FPLANW040_WrmcUpdate(itemDto);
        return "UPDATE OK";

    }

    //IWORK INSERT
    @RequestMapping(value="/w010wiwork", method = RequestMethod.POST)
    public String AppW010WIwork_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("lotno") String lotno
            ,@RequestParam("opcltcd") String opcltcd
            ,@RequestParam("opdate") String opdate
            ,@RequestParam("store") String store
            ,@RequestParam("jqty") float jqty
            ,@RequestParam("qty") float qty
            ,@RequestParam("bqty") float bqty) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();
        TBPopupVO wscntDto = new TBPopupVO();

        itemDto.setCustcd(custcd);
        itemDto.setSpjangcd(spjangcd);
        itemDto.setWflag(wflag);
        itemDto.setPcode(pcode);
        itemDto.setPlan_no(plan_no);
        itemDto.setLotno(lotno);
        itemDto.setOpcltcd(opcltcd);
        itemDto.setOpdate(opdate);
        itemDto.setStore(store);
        itemDto.setQty(qty);
        itemDto.setJqty(jqty);
        itemDto.setBqty(bqty);
        itemDto.setWseq("01");
        itemDto.setAseq("01");
        itemDto.setIndate(getToDate());
        wscntDto.setPlan_no(plan_no);
        wscntDto.setLotno(lotno);
        String ls_wseq = appcom01Service.GetWIworkWseq(wscntDto);
        if(ls_wseq == null) {
            itemDto.setWseq("01");
            itemDto.setSeq("01");
            appcom01Service.FPLANI_IWORK_Insert(itemDto);
        }else{
            itemDto.setWseq(ls_wseq);
            itemDto.setSeq("01");
            appcom01Service.FPLANI_IWORK_update(itemDto);
        }

        return "UPDATE OK";

    }

    //IWORK INSERT
    @RequestMapping(value="/w020wiwork", method = RequestMethod.POST)
    public String AppW020WIwork_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("lotno") String lotno
            ,@RequestParam("opcltcd") String opcltcd
            ,@RequestParam("opdate") String opdate
            ,@RequestParam("store") String store
            ,@RequestParam("jqty") float jqty
            ,@RequestParam("qty") float qty
            ,@RequestParam("bqty") float bqty) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();
        TBPopupVO wscntDto = new TBPopupVO();

        itemDto.setCustcd(custcd);
        itemDto.setSpjangcd(spjangcd);
        itemDto.setWflag(wflag);
        itemDto.setPcode(pcode);
        itemDto.setPlan_no(plan_no);
        itemDto.setLotno(lotno);
        itemDto.setOpcltcd(opcltcd);
        itemDto.setOpdate(opdate);
        itemDto.setStore(store);
        itemDto.setQty(qty);
        itemDto.setJqty(jqty);
        itemDto.setBqty(bqty);
        itemDto.setWseq("01");
        itemDto.setAseq("01");
        itemDto.setIndate(getToDate());
        wscntDto.setPlan_no(plan_no);
        wscntDto.setLotno(lotno);
//        String ls_wseq = appcom02Service.GetWIworkWseq(wscntDto);
//        if(ls_wseq == null) {
//            itemDto.setWseq("01");
//            itemDto.setSeq("01");
//            appcom02Service.FPLANI_IWORK_Insert(itemDto);
//        }else{
//            itemDto.setWseq(ls_wseq);
//            itemDto.setSeq("01");
//            appcom02Service.FPLANI_IWORK_update(itemDto);
//        }

        return "UPDATE OK";

    }

    //IWORK INSERT
    @RequestMapping(value="/w030wiwork", method = RequestMethod.POST)
    public String AppW030WIwork_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("lotno") String lotno
            ,@RequestParam("opcltcd") String opcltcd
            ,@RequestParam("opdate") String opdate
            ,@RequestParam("store") String store
            ,@RequestParam("jqty") float jqty
            ,@RequestParam("qty") float qty
            ,@RequestParam("bqty") float bqty) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();
        TBPopupVO wscntDto = new TBPopupVO();

        itemDto.setCustcd(custcd);
        itemDto.setSpjangcd(spjangcd);
        itemDto.setWflag(wflag);
        itemDto.setPcode(pcode);
        itemDto.setPlan_no(plan_no);
        itemDto.setLotno(lotno);
        itemDto.setOpcltcd(opcltcd);
        itemDto.setOpdate(opdate);
        itemDto.setStore(store);
        itemDto.setQty(qty);
        itemDto.setJqty(jqty);
        itemDto.setBqty(bqty);
        itemDto.setWseq("01");
        itemDto.setAseq("01");
        itemDto.setIndate(getToDate());
        wscntDto.setPlan_no(plan_no);
        wscntDto.setLotno(lotno);
//        String ls_wseq = appcom03Service.GetWIworkWseq(wscntDto);
//        if(ls_wseq == null) {
//            itemDto.setWseq("01");
//            itemDto.setSeq("01");
//            appcom03Service.FPLANI_IWORK_Insert(itemDto);
//        }else{
//            itemDto.setWseq(ls_wseq);
//            itemDto.setSeq("01");
//            appcom03Service.FPLANI_IWORK_update(itemDto);
//        }

        return "UPDATE OK";

    }

    //IWORK INSERT
    @RequestMapping(value="/w040wiwork", method = RequestMethod.POST)
    public String AppW040WIwork_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("lotno") String lotno
            ,@RequestParam("opcltcd") String opcltcd
            ,@RequestParam("opdate") String opdate
            ,@RequestParam("store") String store
            ,@RequestParam("jqty") float jqty
            ,@RequestParam("qty") float qty
            ,@RequestParam("bqty") float bqty) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();
        TBPopupVO wscntDto = new TBPopupVO();

        itemDto.setCustcd(custcd);
        itemDto.setSpjangcd(spjangcd);
        itemDto.setWflag(wflag);
        itemDto.setPcode(pcode);
        itemDto.setPlan_no(plan_no);
        itemDto.setLotno(lotno);
        itemDto.setOpcltcd(opcltcd);
        itemDto.setOpdate(opdate);
        itemDto.setStore(store);
        itemDto.setQty(qty);
        itemDto.setJqty(jqty);
        itemDto.setBqty(bqty);
        itemDto.setWseq("01");
        itemDto.setAseq("01");
        itemDto.setIndate(getToDate());
        wscntDto.setPlan_no(plan_no);
        wscntDto.setLotno(lotno);
//        String ls_wseq = appcom04Service.GetWIworkWseq(wscntDto);
//        if(ls_wseq == null) {
//            itemDto.setWseq("01");
//            itemDto.setSeq("01");
//            appcom04Service.FPLANI_IWORK_Insert(itemDto);
//        }else{
//            itemDto.setWseq(ls_wseq);
//            itemDto.setSeq("01");
//            appcom04Service.FPLANI_IWORK_update(itemDto);
//        }

        return "UPDATE OK";

    }

    //작지번호 조회
    @RequestMapping(value="/SELW010", method = RequestMethod.POST)
    public Object AppW010_SEL(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();

        itemDto.setPlan_no(plan_no);
        itemDto.setWflag(wflag);
        itemDto.setWseq("01");
        return appcom01Service.GetFPLANW010_Detail(itemDto);

    }

    //작지번호 조회
    @RequestMapping(value="/SELW020", method = RequestMethod.POST)
    public Object AppW020_SEL(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();

        itemDto.setPlan_no(plan_no);
        itemDto.setWflag(wflag);
        itemDto.setWseq("02");
        return appcom01Service.GetFPLANW020_Detail(itemDto);

    }

    //작지번호 조회
    @RequestMapping(value="/SELW030", method = RequestMethod.POST)
    public Object AppW030_SEL(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();

        itemDto.setPlan_no(plan_no);
        itemDto.setWflag(wflag);
        itemDto.setWseq("03");
        return null;
//        return appcom03Service.GetFPLANW030_Detail(itemDto);

    }

    //작지번호 조회
    @RequestMapping(value="/SELW040", method = RequestMethod.POST)
    public Object AppW040_SEL(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        FPLANW010_VO itemDto = new FPLANW010_VO();

        itemDto.setPlan_no(plan_no);
        itemDto.setWflag(wflag);
        itemDto.setWseq("04");
        return null;
//        return appcom04Service.GetFPLANW040_Detail(itemDto);

    }

    //lot별 투입 list
    @RequestMapping(value="/SELWIWORK", method = RequestMethod.POST)
    public Object AppWIWORK_SEL(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        TBPopupVO wscntDto = new TBPopupVO();
        FPLANW010_VO itemDto = new FPLANW010_VO();

        wscntDto.setCustcd(custcd);
        wscntDto.setSpjangcd(spjangcd);
        wscntDto.setPlan_no(plan_no);
        wscntDto.setWflag(wflag);
        if(appcom01Service.GetWIworkDetail(wscntDto) == null){
            return appcom01Service.GetWIworkDetail_blank(wscntDto);
        }else{
            return appcom01Service.GetWIworkDetail(wscntDto);
        }
    }

    //lot별 투입 list
    @RequestMapping(value="/SELWIWORK020", method = RequestMethod.POST)
    public Object AppWIWORK_SEL020(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        TBPopupVO wscntDto = new TBPopupVO();
        FPLANW010_VO itemDto = new FPLANW010_VO();

        wscntDto.setCustcd(custcd);
        wscntDto.setSpjangcd(spjangcd);
        wscntDto.setPlan_no(plan_no);
        wscntDto.setWflag(wflag);
//        if(appcom02Service.GetWIworkDetail(wscntDto) == null){
//            return appcom02Service.GetWIworkDetail_blank(wscntDto);
//        }else{
//            return appcom02Service.GetWIworkDetail(wscntDto);
//        }
        return null;
    }

    //lot별 투입 list
    @RequestMapping(value="/SELWIWORK030", method = RequestMethod.POST)
    public Object AppWIWORK_SEL030(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        TBPopupVO wscntDto = new TBPopupVO();
        FPLANW010_VO itemDto = new FPLANW010_VO();

        wscntDto.setCustcd(custcd);
        wscntDto.setSpjangcd(spjangcd);
        wscntDto.setPlan_no(plan_no);
        wscntDto.setWflag(wflag);
//        if(appcom03Service.GetWIworkDetail(wscntDto) == null){
//            return appcom03Service.GetWIworkDetail_blank(wscntDto);
//        }else{
//            return appcom03Service.GetWIworkDetail(wscntDto);
//        }
        return null;
    }

    //lot별 투입 list
    @RequestMapping(value="/SELWIWORK040", method = RequestMethod.POST)
    public Object AppWIWORK_SEL040(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        TBPopupVO wscntDto = new TBPopupVO();
        FPLANW010_VO itemDto = new FPLANW010_VO();

        wscntDto.setCustcd(custcd);
        wscntDto.setSpjangcd(spjangcd);
        wscntDto.setPlan_no(plan_no);
        wscntDto.setWflag(wflag);
//        if(appcom04Service.GetWIworkDetail(wscntDto) == null){
//            return appcom04Service.GetWIworkDetail_blank(wscntDto);
//        }else{
//            return appcom04Service.GetWIworkDetail(wscntDto);
//        }

        return null;
    }


    //불량내역  등록
    @RequestMapping(value="/w010wbad", method = RequestMethod.POST)
    public String AppWBAD_INT(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wbqty") int wbqty
            ,@RequestParam("wcode") String wcode
            ,@RequestParam("wseq") String wseq
            ,@RequestParam("pcode") String pcode) throws Exception {
        Exception dispatchException = null;
        try {
            FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();

            wbadDto.setCustcd(custcd);
            wbadDto.setSpjangcd(spjangcd);
            wbadDto.setPlan_no(plan_no);
            wbadDto.setWflag(wflag);
            wbadDto.setWbqty(wbqty);
            wbadDto.setWcode(wcode);
            wbadDto.setWseq(wseq);
            wbadDto.setPcode(pcode);
            String lsChkseq = appcom01Service.FPLAN_WBAD_SELECT(wbadDto);
            if (lsChkseq == null  || lsChkseq.equals("")){
                String ls_seq = appcom01Service.FPLAN_WBAD_MAXWSEQ(wbadDto);
                if(ls_seq == null){
                    ls_seq = "01";
                }else{
                    int ll_seq =  Integer.parseInt(ls_seq) + 1;
                    ls_seq = Integer.toString(ll_seq);
                    if(ls_seq.length() == 1){ls_seq = "0" + ls_seq;}
                }
                wseq = ls_seq;
                wbadDto.setWseq(ls_seq);
                appcom01Service.FPLAN_WBAD_Insert(wbadDto) ;
            }else{
                wbadDto.setWseq(lsChkseq);
                appcom01Service.FPLAN_WBAD_Update(wbadDto);
            }
            return "TB_FPLAN_WBAD I/U OK";
        } catch (Exception ex) {
            dispatchException = ex;
            return "TB_FPLAN_WBAD ERROR " + dispatchException;
        }
    }


    //불량내역  등록
    @RequestMapping(value="/w020wbad", method = RequestMethod.POST)
    public String AppWBAD_INT020(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wbqty") int wbqty
            ,@RequestParam("wcode") String wcode
            ,@RequestParam("wseq") String wseq
            ,@RequestParam("pcode") String pcode) throws Exception {
        Exception dispatchException = null;
        try {
            FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();

            wbadDto.setCustcd(custcd);
            wbadDto.setSpjangcd(spjangcd);
            wbadDto.setPlan_no(plan_no);
            wbadDto.setWflag(wflag);
            wbadDto.setWbqty(wbqty);
            wbadDto.setWcode(wcode);
            wbadDto.setWseq(wseq);
            wbadDto.setPcode(pcode);
//            String lsChkseq = appcom02Service.FPLAN_WBAD_SELECT(wbadDto);
//            if (lsChkseq == null  || lsChkseq.equals("")){
//                String ls_seq = appcom02Service.FPLAN_WBAD_MAXWSEQ(wbadDto);
//                if(ls_seq == null){
//                    ls_seq = "01";
//                }else{
//                    int ll_seq =  Integer.parseInt(ls_seq) + 1;
//                    ls_seq = Integer.toString(ll_seq);
//                    if(ls_seq.length() == 1){ls_seq = "0" + ls_seq;}
//                }
//                wseq = ls_seq;
//                wbadDto.setWseq(ls_seq);
//                appcom02Service.FPLAN_WBAD_Insert(wbadDto) ;
//            }else{
//                wbadDto.setWseq(lsChkseq);
//                appcom02Service.FPLAN_WBAD_Update(wbadDto);
//            }
            return "TB_FPLAN_WBAD I/U OK";
        } catch (Exception ex) {
            dispatchException = ex;
            return "TB_FPLAN_WBAD ERROR " + dispatchException;
        }
    }

    //불량내역  등록
    @RequestMapping(value="/w030wbad", method = RequestMethod.POST)
    public String AppWBAD_INT030(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wbqty") int wbqty
            ,@RequestParam("wcode") String wcode
            ,@RequestParam("wseq") String wseq
            ,@RequestParam("pcode") String pcode) throws Exception {
        Exception dispatchException = null;
        try {
            FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();

            wbadDto.setCustcd(custcd);
            wbadDto.setSpjangcd(spjangcd);
            wbadDto.setPlan_no(plan_no);
            wbadDto.setWflag(wflag);
            wbadDto.setWbqty(wbqty);
            wbadDto.setWcode(wcode);
            wbadDto.setWseq(wseq);
            wbadDto.setPcode(pcode);
//            String lsChkseq = appcom03Service.FPLAN_WBAD_SELECT(wbadDto);
//            if (lsChkseq == null  || lsChkseq.equals("")){
//                String ls_seq = appcom03Service.FPLAN_WBAD_MAXWSEQ(wbadDto);
//                if(ls_seq == null){
//                    ls_seq = "01";
//                }else{
//                    int ll_seq =  Integer.parseInt(ls_seq) + 1;
//                    ls_seq = Integer.toString(ll_seq);
//                    if(ls_seq.length() == 1){ls_seq = "0" + ls_seq;}
//                }
//                wseq = ls_seq;
//                wbadDto.setWseq(ls_seq);
//                appcom03Service.FPLAN_WBAD_Insert(wbadDto) ;
//            }else{
//                wbadDto.setWseq(lsChkseq);
//                appcom03Service.FPLAN_WBAD_Update(wbadDto);
//            }
            return "TB_FPLAN_WBAD I/U OK";
        } catch (Exception ex) {
            dispatchException = ex;
            return "TB_FPLAN_WBAD ERROR " + dispatchException;
        }
    }

    //불량내역  등록
    @RequestMapping(value="/w040wbad", method = RequestMethod.POST)
    public String AppWBAD_INT040(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wbqty") int wbqty
            ,@RequestParam("wcode") String wcode
            ,@RequestParam("wseq") String wseq
            ,@RequestParam("pcode") String pcode) throws Exception {
        Exception dispatchException = null;
        try {
            FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();

            wbadDto.setCustcd(custcd);
            wbadDto.setSpjangcd(spjangcd);
            wbadDto.setPlan_no(plan_no);
            wbadDto.setWflag(wflag);
            wbadDto.setWbqty(wbqty);
            wbadDto.setWcode(wcode);
            wbadDto.setWseq(wseq);
            wbadDto.setPcode(pcode);
//            String lsChkseq = appcom04Service.FPLAN_WBAD_SELECT(wbadDto);
//            if (lsChkseq == null  || lsChkseq.equals("")){
//                String ls_seq = appcom04Service.FPLAN_WBAD_MAXWSEQ(wbadDto);
//                if(ls_seq == null){
//                    ls_seq = "01";
//                }else{
//                    int ll_seq =  Integer.parseInt(ls_seq) + 1;
//                    ls_seq = Integer.toString(ll_seq);
//                    if(ls_seq.length() == 1){ls_seq = "0" + ls_seq;}
//                }
//                wseq = ls_seq;
//                wbadDto.setWseq(ls_seq);
//                appcom04Service.FPLAN_WBAD_Insert(wbadDto) ;
//            }else{
//                wbadDto.setWseq(lsChkseq);
//                appcom04Service.FPLAN_WBAD_Update(wbadDto);
//            }
            return "TB_FPLAN_WBAD I/U OK";
        } catch (Exception ex) {
            dispatchException = ex;
            return "TB_FPLAN_WBAD ERROR " + dispatchException;
        }
    }



    //불량내역  list  010
    @RequestMapping(value="/SELWBAD", method = RequestMethod.POST)
    public Object AppWBAD_SEL(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();
        TBPopupVO wscntDto = new TBPopupVO();
        String ls_wbadflag = "1";
        switch (wflag){
            case "00010":
                ls_wbadflag = "1";
                break;
            case "00020":
                ls_wbadflag = "2";
                break;
            case "00030":
                ls_wbadflag = "3";
                break;
            case "00090":
                ls_wbadflag = "9";
                break;
            default:
                break;
        }
        wbadDto.setCustcd(custcd);
        wbadDto.setSpjangcd(spjangcd);
        wbadDto.setPlan_no(plan_no);
        wbadDto.setWclscode(ls_wbadflag);
        wbadDto.setWflag(wflag);

        wscntDto.setCustcd(custcd);
        wscntDto.setSpjangcd(spjangcd);
        wscntDto.setPlan_no(plan_no);
        wscntDto.setWclscode(ls_wbadflag);
        wscntDto.setWflag(wflag);
        if(appPopupService.GetWBadList(wscntDto) == null){
            return appcom01Service.FPLAN_WBAD_SELECT_blank(wbadDto);
        }else{
            return appPopupService.GetWBadList(wscntDto);
        }
    }

    //불량내역  list 020
    @RequestMapping(value="/SELWBAD020", method = RequestMethod.POST)
    public Object AppWBAD_SEL020(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();
        TBPopupVO wscntDto = new TBPopupVO();
        String ls_wbadflag = "1";
        switch (wflag){
            case "00010":
                ls_wbadflag = "1";
                break;
            case "00020":
                ls_wbadflag = "2";
                break;
            case "00030":
                ls_wbadflag = "3";
                break;
            case "00090":
                ls_wbadflag = "9";
                break;
            default:
                break;
        }
        wbadDto.setCustcd(custcd);
        wbadDto.setSpjangcd(spjangcd);
        wbadDto.setPlan_no(plan_no);
        wbadDto.setWclscode(ls_wbadflag);
        wbadDto.setWflag(wflag);

        wscntDto.setCustcd(custcd);
        wscntDto.setSpjangcd(spjangcd);
        wscntDto.setPlan_no(plan_no);
        wscntDto.setWclscode(ls_wbadflag);
        wscntDto.setWflag(wflag);
//        if(appPopupService.GetWBadList(wscntDto) == null){
//            return appcom02Service.FPLAN_WBAD_SELECT_blank(wbadDto);
//        }else{
//            return appPopupService.GetWBadList(wscntDto);
//        }
        return null;
    }


    //불량내역  list 030
    @RequestMapping(value="/SELWBAD030", method = RequestMethod.POST)
    public Object AppWBAD_SEL030(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();
        TBPopupVO wscntDto = new TBPopupVO();
        String ls_wbadflag = "1";
        switch (wflag){
            case "00010":
                ls_wbadflag = "1";
                break;
            case "00020":
                ls_wbadflag = "2";
                break;
            case "00030":
                ls_wbadflag = "3";
                break;
            case "00090":
                ls_wbadflag = "9";
                break;
            default:
                break;
        }
        wbadDto.setCustcd(custcd);
        wbadDto.setSpjangcd(spjangcd);
        wbadDto.setPlan_no(plan_no);
        wbadDto.setWclscode(ls_wbadflag);
        wbadDto.setWflag(wflag);

        wscntDto.setCustcd(custcd);
        wscntDto.setSpjangcd(spjangcd);
        wscntDto.setPlan_no(plan_no);
        wscntDto.setWclscode(ls_wbadflag);
        wscntDto.setWflag(wflag);
//        if(appPopupService.GetWBadList(wscntDto) == null){
//            return appcom03Service.FPLAN_WBAD_SELECT_blank(wbadDto);
//        }else{
//            return appPopupService.GetWBadList(wscntDto);
//        }
        return null;
    }

    //불량내역  list 040
    @RequestMapping(value="/SELWBAD040", method = RequestMethod.POST)
    public Object AppWBAD_SEL040(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag) throws Exception {

        FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();
        TBPopupVO wscntDto = new TBPopupVO();
        String ls_wbadflag = "1";
        switch (wflag){
            case "00010":
                ls_wbadflag = "1";
                break;
            case "00020":
                ls_wbadflag = "2";
                break;
            case "00030":
                ls_wbadflag = "3";
                break;
            case "00090":
                ls_wbadflag = "9";
                break;
            default:
                break;
        }
        wbadDto.setCustcd(custcd);
        wbadDto.setSpjangcd(spjangcd);
        wbadDto.setPlan_no(plan_no);
        wbadDto.setWclscode(ls_wbadflag);
        wbadDto.setWflag(wflag);

        wscntDto.setCustcd(custcd);
        wscntDto.setSpjangcd(spjangcd);
        wscntDto.setPlan_no(plan_no);
        wscntDto.setWclscode(ls_wbadflag);
        wscntDto.setWflag(wflag);
//        if(appPopupService.GetWBadList(wscntDto) == null){
//            return appcom04Service.FPLAN_WBAD_SELECT_blank(wbadDto);
//        }else{
//            return appPopupService.GetWBadList(wscntDto);
//        }
        return null;
    }



    //생산량 등록
    @RequestMapping(value="/oworkupd", method = RequestMethod.POST)
    public String AppWOwork_Insert(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("inplan_no") String plan_no
            ,@RequestParam("inpcode") String pcode
            ,@RequestParam("inwono") String wono
            ,@RequestParam("popwotqt") float wotqt
            ,@RequestParam("popwbdqt") float wbdqt
            ,@RequestParam("popwkotDate") String wkokdate
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("wseq") String wseq
            ,@RequestParam("seq") String seq
    ) throws Exception {

        FPLANW010_VO workDto = new FPLANW010_VO();
        FPLANW010_VO workDtoSum = new FPLANW010_VO();
        String ls_indate = getToDate();

        String ls_yeare = wkokdate.substring(0,4);
        String ls_mm = wkokdate.substring(5,7);
        String ls_dd = wkokdate.substring(8,10);
        wkokdate =  ls_yeare + ls_mm + ls_dd;

        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setPlan_no(plan_no);
        workDto.setPcode(pcode);
        workDto.setWono(wono);
        workDto.setWqty(wotqt);
        workDto.setBqty(wbdqt);
        if(wbdqt > 0 ){
            workDto.setQty(wotqt - wbdqt);
        }else{
            workDto.setQty(wotqt);
        }
        workDto.setWotdt(wkokdate);
        workDto.setWflag(wflag);
        switch (wflag){
            case "00010":
                workDto.setWseq("01");
                break;
            case "00020":
                workDto.setWseq("02");
                break;
            case "00030":
                workDto.setWseq("03");
                break;
            case "00090":
                workDto.setWseq("04");
                break;
            default:
                break;
        }

        workDto.setStore("W01");
        workDto.setIndate(getToDate());
        String ls_lotno = wono.substring(2,11);
        workDto.setLotno(ls_lotno);
        if(seq == null || seq.equals("")) {
            String ls_seq = appcom01Service.FPLAN_OWORK_MAXWSEQ(workDto);
            if (ls_seq == null) {
                ls_seq = "001";
            } else {
                int ll_seq = Integer.parseInt(ls_seq) + 1;
                ls_seq = Integer.toString(ll_seq);
                if (ls_seq.length() == 1) {
                    ls_seq = "00" + ls_seq;
                } else if (ls_seq.length() == 2) {
                    ls_seq = "0" + ls_seq;
                }
            }
            workDto.setSeq(ls_seq);
            appcom01Service.FPLAN_OWORK_Insert(workDto);
        }else{
            workDto.setSeq(seq);
            appcom01Service.FPLAN_OWORK_Update(workDto);
        }
        workDtoSum = (FPLANW010_VO) appcom01Service.FPLAN_OWORK_SUMQTY(workDto);
        workDto.setWotqt(workDtoSum.getWotqt());  //생산수량
        workDto.setWbdqt(workDtoSum.getWbdqt());  //불량수량
        switch (wflag){
            case "00010":
                appcom01Service.FPLANWORK_Update(workDto);
                appcom01Service.FPLANW010_Update(workDto);
                break;
            case "00020":
                appcom01Service.FPLANWORK_Update(workDto);
//                appcom02Service.FPLANW020_Update(workDto);
                break;
            case "00030":
                appcom01Service.FPLANWORK_Update(workDto);
//                appcom03Service.FPLANW030_Update(workDto);
                break;
            case "00090":
                appcom01Service.FPLANWORK_Update(workDto);
//                appcom04Service.FPLANW040_Update(workDto);
                break;
            default:
                break;
        }




        return "success";
    }


    private String setDateFormat(String arg){

        String year = arg.substring(0,4) ;
        String month = arg.substring(5,7) ;
        String day   = arg.substring(8,10) ;
        return arg = year + month + day ;
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
    private String getToDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }
    private String getWtimeMaxSeq(){

        String ls_seq = appcom01Service.FPLAN_WTIME_MAXSEQ(wtimeDto);
        if (ls_seq == null) {
            ls_seq = "001";
        } else {
            int ll_seq = Integer.parseInt(ls_seq) + 1;
            ls_seq = Integer.toString(ll_seq);
            if (ls_seq.length() == 1) {
                ls_seq = "00" + ls_seq;
            } else if (ls_seq.length() == 2) {
                ls_seq = "0" + ls_seq;
            }
        }
        return ls_seq;
    }
    ;
}
