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
import javax.servlet.http.HttpSession;
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
    FPLANW010_VO workDto = new FPLANW010_VO();
    FPLANIWORK_VO IworkDto = new FPLANIWORK_VO();
    protected Log log =  LogFactory.getLog(this.getClass());
    Date nowData = new Date();
    SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
    String ToDate = endDate.format(nowData).toString();
    CommonDto CommDto = new CommonDto();
    FPLANW010_VO workDtoDetail = new FPLANW010_VO();
    Boolean result = false;

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
    public Object AppW010OrdList_index(@RequestParam("lotno") String lotno
            ,Model model, HttpServletRequest request) throws Exception {


        CommDto.setMenuTitle("사출공정");  //
        CommDto.setMenuUrl("생산공정>사출공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();

        String fdate = getFrDate();
        String tdate = getToDate();

        fplanDto.setLine("00");
        fplanDto.setWflag("00010");
        fplanDto.setFdate(fdate);
        fplanDto.setTdate(tdate);
        fplanDto.setCltcd("%");
        fplanDto.setPcode("%");
        fplanDto.setLotno(lotno);



        itemDtoList = appcom01Service.GetFPLAN_List(fplanDto);

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
            ,@RequestParam("partcode") String partcode
            ,@RequestParam("workdv") String workdv
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
        workDto.setAseq("0");
        workDto.setWrps(wrps);
        workDto.setWinps(winps);
        workDto.setStore("W01");
        workDto.setPcode(pcode);
        workDto.setWono(wono);
        workDto.setPartcode(partcode);
        if(!workdv.equals("4")){
            workDto.setWorkdv(workdv);
            workDto.setDecision(workdv);
            workDto.setDecision1(workdv);
        }
        if(workdv.equals("0")){
            workDto.setClsflag("3");        // 생산완료
            workDto.setWorkdv("3");
            workDto.setDecision("3");
            workDto.setDecision1("3");
        }else{
            workDto.setClsflag("2");        // 공정중
        }
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

//        workDtoDetail = (FPLANW010_VO) appcom01Service.GetFPLANW010_Detail(workDto);
        if(appcom01Service.GetFPLANW010_Detail(workDto) == null){
            result = appcom01Service.FPLANW010_Insert(workDto);
            if (!result) {
                log.info("error =====> FPLANW010_Insert");
                return "error";
            }
            result = appcom01Service.FPLANWORK_Insert(workDto);
            if (!result) {
                log.info("error =====> FPLANWORK_Insert");
                return "error";
            }
            result = appcom01Service.FPLAN_WTIME_Insert(wtimeDto);
            if (!result) {
                log.info("error =====> FPLAN_WTIME_Insert");
                return "error";
            }

        }else{
            result = appcom01Service.FPLANW010_Update(workDto);
            if (!result) {
                log.info("error =====> FPLANW010_Update");
                return "error";
            }
            result = appcom01Service.FPLANWORK_Update(workDto);
            if (!result) {
                log.info("error =====> FPLANWORK_Update");
                return "error";
            }
            log.info("GetFPLANW010_Detail =====> not null");
        }
        workDto.setEnd_qty(wotqt - wbdqt);
        workDto.setProd_qty(wotqt);

        if(decision.equals("0")){
            wtimeDto.setWtrdt(getToDateTime());
            workDto.setWflag("00020");
            result = appcom01Service.FPLAN_WTIME_Update(wtimeDto);
            if (!result) {
                log.info("error =====> FPLAN_WTIME_Update 02");
                //return "error";
            }
        }
        result = appcom01Service.FPLAN_Update(workDto);
        if (!result) {
            log.info("error =====> FPLAN_Update 02");
            return "error";
        }
        return "TB_FPLAN_W010 UPDATE OK";
    }


    //절단공정삭제
    @RequestMapping(value="/w010del", method = RequestMethod.GET)
    public String Appcom01_delete(@RequestParam("plan_no") String plan_no
                                 ,HttpServletRequest request){
        FPLANW010_VO workDto = new FPLANW010_VO();
        workDto.setPlan_no(plan_no);
        workDto.setWflag("00010");
        workDto.setWseq("01");
        wperDto.setPlan_no(plan_no);
        wperDto.setWseq("01");
        wperDto.setWflag("00010");
        result = appcom01Service.FPLANW010_Delete(workDto);
        if (!result) {
            log.info("error =====> FPLANW010_Delete");
            return "error";
        }
        result = appcom01Service.FPLAN_OWORK_Delete(workDto);
        if (!result) {
            log.info("error =====> FPLAN_OWORK_Delete");
            return "error";
        }
        result = appcom01Service.FPLAN_IWORK_Delete(workDto);
        if (!result) {
            log.info("error =====> FPLAN_IWORK_Delete");
            return "error";
        }
        result = appcom01Service.FPLAN_WORK_Delete(workDto);
        if (!result) {
            log.info("error =====> FPLAN_WORK_Delete");
            return "error";
        }
        result = appcom01Service.FPLAN_WTIME_Delete(workDto);
        if (!result) {
            log.info("error =====> FPLAN_WTIME_Delete");
            return "error";
        }
        result = appcom01Service.FPLAN_WPERID_Delete(wperDto);
        if (!result) {
            log.info("error =====> FPLAN_WPERID_Delete");
            return "error";
        }
        result = appcom01Service.FPLAN_WBAD_Delete(workDto);
        if (!result) {
            log.info("error =====> FPLAN_WBAD_Delete");
            return "error";
        }


        //log.info("w010del plan_no =====>" + plan_no);

        workDto.setDecision("0");
        workDto.setDecision1("0");
        workDto.setClsflag("1");
        result = appcom01Service.FPLAN_Update(workDto);
        if (!result) {
            log.info("error =====> FPLAN_Update");
            return "error";
        }

        var ls_line = "99";
        return "redirect:/appm/list01";
    }



    @RequestMapping(value="/wtimeupd", method = RequestMethod.POST)
    public String AppWTimeUpdate_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("decision") String decision
            ,@RequestParam("workdv") String workdv
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
            result = appcom01Service.FPLAN_WTIME_Update(wtimeDto);
            if (!result) {
                log.info("error =====> FPLAN_WTIME_Update");
                ///return "error";
            }
            wtimeDto.setWtrdt(null);
            result = appcom01Service.FPLAN_WTIME_Insert(wtimeDto);
            if (!result) {
                log.info("error =====> FPLAN_WTIME_Insert");
                return "error";
            }
        }else{                        //작업종료
            wtimeDto.setWtrdt(getToDateTime());
            result = appcom01Service.FPLAN_WTIME_Update(wtimeDto);
            if (!result) {
                log.info("error =====> FPLAN_WTIME_Update");
                //return "error";
            }
        }
        workDto.setPlan_no(plan_no);
        workDto.setWflag(wflag);
        if(!workdv.equals("4")){
            workDto.setWorkdv(workdv);
            workDto.setDecision(workdv);
            workDto.setDecision1(workdv);
        }
        workDto.setWseq("01");
        log.info(" workdv =====>" + workdv);
        result = appcom01Service.FPLAN_Update(workDto);
        if (!result) {
            log.info("error =====> FPLAN_Update");
            return "error";
        }
        result = appcom01Service.FPLANW010_Update(workDto);
        if (!result) {
            log.info("error =====> FPLANW010_Update");
            return "error";
        }
        return "success";
    }



    //작업설비 update 조회
    @RequestMapping(value="/qcsearch", method = RequestMethod.POST)
    public Object AppQcSearch_index(@RequestParam("searchcode") String searchcode
                , Model model, HttpServletRequest request) throws Exception {

        FPLANIWORK_VO fplanDto = new FPLANIWORK_VO();
        List<FPLANIWORK_VO> fplanListDto = new ArrayList<>();
        fplanDto.setLotno(searchcode);
        fplanListDto =   appcom01Service.GetPlanSearch(fplanDto);
        model.addAttribute("fplanListDto",fplanListDto);
        return fplanListDto;

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

                wperDto.setCustcd("KDMES");
                wperDto.setSpjangcd("ZZ");
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
            wbadDto.setIndate(getToDate());
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
            return appPopupService.GetWBadList01(wscntDto);
        }
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


    @ResponseBody
    @RequestMapping(value="/oworkdel", method = RequestMethod.POST)
    public String AppWOwork_Delete(@RequestParam("plan_no") String plan_no
            ,@RequestParam("wseq") String wseq
            ,@RequestParam("seq") String seq
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("wflag") String wflag
    ) throws Exception {

        workDto.setPlan_no(plan_no);
        workDto.setWseq(wseq);
        workDto.setSeq(seq);
        workDto.setPcode(pcode);
        workDto.setWflag(wflag);
        appcom01Service.FPLAN_OWORK_PERDELETE(workDto);
        return "success" ;
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
