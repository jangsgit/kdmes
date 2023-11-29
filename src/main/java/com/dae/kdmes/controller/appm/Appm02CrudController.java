package com.dae.kdmes.controller.appm;

import com.dae.kdmes.DTO.Appm.*;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.Appm.AppPopupService;
import com.dae.kdmes.Service.Appm.Appcom01Service;
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
@RequestMapping(value = "/input02", method = RequestMethod.POST)
public class Appm02CrudController {
    private final Appcom01Service appcom01Service;
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
    FPLAN_VO fplanDto = new FPLAN_VO();
    List<FPLAN_VO> itemDtoList = new ArrayList<>();
    List<FPLANW010_VO> itemW010List = new ArrayList<>();
    Boolean result = false;

    @RequestMapping(value="/w020", method = RequestMethod.POST)
    public Object AppW020_index(@RequestParam("searchtxt") String searchtxt
            ,Model model, HttpServletRequest request) throws Exception {
        FPLAN_VO fplanDto = new FPLAN_VO();
        if (searchtxt.equals("") ||  searchtxt == null || searchtxt.length() == 0){
            searchtxt = "%";
        }
        fplanDto.setLotno(searchtxt);
        itemDtoList = appcom01Service.GetFPLAN_List02_REG(fplanDto);

        model.addAttribute("itemDtoList", itemW010List);
        return itemDtoList;
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
    public Object AppW030_index(@RequestParam("searchtxt") String searchtxt
            ,Model model, HttpServletRequest request) throws Exception {

        CommDto.setMenuTitle("조립공정");  //
        CommDto.setMenuUrl("생산공정>조립공정");
        CommDto.setMenuCode("appcom21");
        String fdate = getFrDate();
        String tdate = getAddDate();
        String cltcd = "%";
        fplanDto.setLine("00");
        fplanDto.setWflag("00020");
        fplanDto.setFdate(fdate);
        fplanDto.setTdate(tdate);
        fplanDto.setCltcd(cltcd);
        fplanDto.setPcode(searchtxt);
        fplanDto.setPlan_no("%");
        itemDtoList = appcom01Service.GetFPLAN_List03(fplanDto);

        model.addAttribute("itemDtoList", itemDtoList);
        return itemDtoList;
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


    @RequestMapping(value="/w020upd_pre", method = RequestMethod.POST)
    public String AppW020UpdatePre_index(@RequestParam("custcd") String custcd
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
            ,@RequestParam("workdv") String workdv
            ,@RequestParam( value =  "arrplanno[]") List<String> arrplanno
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

//        log.info("frdate Exception =====>" + arrplanno.size());
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
        workDto.setAseq("0");
        workDto.setWrps(wrps);
        workDto.setWinps(winps);
        workDto.setStore("W01");
        workDto.setPcode(pcode);
        workDto.setWono(wono);
        String ls_lotno = plan_no + "G";
        workDto.setLotno(ls_lotno);
        //저장
        if(!workdv.equals("4")){
            workDto.setWorkdv(workdv);
            workDto.setDecision(workdv);
            workDto.setDecision2(workdv);
        }
        //공정종료
        if(workdv.equals("0")){
            workDto.setClsflag("4");        // 검사완료
            workDto.setWorkdv("4");
            workDto.setDecision("4");
            workDto.setDecision2("4");
        }else{
            workDto.setClsflag("2");        // 공정중
        }
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


        if(appcom01Service.GetFPLANW020_Detail(workDto) == null){
            result = appcom01Service.FPLANW020_Insert(workDto);
            if (!result){
                log.info("error Exception =====> FPLANW020_Insert" );
                return "error";
            }
            result = appcom01Service.FPLANWORK_Insert(workDto);
            if (!result){
                log.info("error Exception =====> FPLANWORK_Insert" );
                return "error";
            }
            result = appcom01Service.FPLAN_WTIME_Insert(wtimeDto);
            if (!result){
                log.info("error Exception =====> FPLAN_WTIME_Insert" );
                return "error";
            }
        }else{
            result = appcom01Service.FPLANW020_Update(workDto);
            if (!result){
                log.info("error Exception =====> FPLANW020_Update" );
                return "error";
            }
            result = appcom01Service.FPLANWORK_Update(workDto);
            if (!result){
                log.info("error Exception =====> FPLANWORK_Update" );
                return "error";
            }
        }
        workDto.setQcdate(wstdt);
        workDto.setQcqty(wotqt - wbdqt);
        workDto.setWqcqt(wotqt - wbdqt);
        if(decision.equals("0")){
            wtimeDto.setWtrdt(getToDateTime());
            result = appcom01Service.FPLAN_WTIME_Update(wtimeDto);
            if (!result){
                log.info("error Exception =====> FPLAN_WTIME_Update" );
            }
            workDto.setWflag("00040");
            workDto.setWtable("TB_FPLAN_W090");
        }
        if( arrplanno.size() > 0 ) {
            if(arrplanno.get(0).equals("0")){
                result = appcom01Service.FPLAN_Update(workDto);
                if (!result){
                    log.info("error Exception =====> FPLAN_Update" );
                    return "error";
                }
            }else{
                for (int i = 0; i < arrplanno.size(); i++) {
                    if (arrplanno.get(i).equals("0")) {break;}
                    workDto.setPlan_no(arrplanno.get(i));
                    result = appcom01Service.FPLAN_Update(workDto);
                    if (!result){
                        log.info("error Exception =====> FPLAN_Update array" );
                        return "error";
                    }
                }
            }
        }else{
            log.info("getDecision2 =====> " + workDto.getDecision2() );
            log.info("getEnd_qty =====> " + workDto.getEnd_qty() );
            result = appcom01Service.FPLAN_Update(workDto);
            if (!result){
                log.info("error Exception =====> FPLAN_Update" );
                return "error";
            }
        }
        return ls_lotno;
    }


    @RequestMapping(value="/w020upd", method = RequestMethod.POST)
    public String AppW020Update_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("inputdate") String inputdate
            ,@RequestParam("wremark") String wremark
            ,@RequestParam("wrps") String wrps
            ,@RequestParam("lotno") String lotno
            ,@RequestParam( value =  "plan_no[]") List<String> plan_no
            ,@RequestParam( value =  "winqt[]") List<Integer> winqt
            ,@RequestParam( value =  "wotqt[]") List<Integer> wotqt
            ,@RequestParam( value =  "wono[]") List<String> wono
            ,Model model, HttpServletRequest request) throws Exception {
        FPLANW010_VO workDto = new FPLANW010_VO();
        String ls_yeare = inputdate.substring(0,4);
        String ls_mm = inputdate.substring(5,7);
        String ls_dd = inputdate.substring(8,10);
        String ls_lotno = "";
        Integer ll_lotno =  0;
        Integer ll_wotqty = 0;          //투입량
        Integer ll_winqty = 0;          //검사량
        inputdate =  ls_yeare + ls_mm + ls_dd;
        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setWfrdt(inputdate);
        workDto.setWtrdt(inputdate);
        workDto.setDecision("5");
        workDto.setDecision3("5");
        workDto.setAseq("0");
        workDto.setStore("W01");
        workDto.setBqty(0);
        workDto.setWbdqt(0);
        workDto.setWtable("TB_FPLAN_W090");
        workDto.setQcdate(inputdate);
        workDto.setWremark(wremark);
        workDto.setWrps(wrps);
        workDto.setWsyul(0);
        String wstdt = inputdate;
        String wendt = inputdate;

        workDto.setWstdt(wstdt);
        workDto.setWendt(wendt);
        workDto.setIndate(getToDate());
        workDto.setWtable("TB_FPLAN_W090");
        if(lotno == null || lotno.length() == 0 || lotno.equals("")) {
            ls_lotno = appcom01Service.FPLAN_W020_MAXLOT(workDto);
            if(ls_lotno == null || ls_lotno.length() == 0){
                ls_lotno = inputdate.substring(0, 6) + "0001G" ;
            }else{
                ll_lotno = Integer.parseInt(ls_lotno) + 1;
                ls_lotno = ll_lotno.toString() + 'G';
            }
        }else{
            ls_lotno = lotno;
        }
        workDto.setLotno(ls_lotno);

        if( plan_no.size() > 0){
            for(int i = 0; i < plan_no.size(); i++){
                workDto.setPlan_no(plan_no.get(i));
                workDto.setWono(wono.get(i));
                workDto.setWqty(wotqt.get(i));
                workDto.setQty(wotqt.get(i));
                workDto.setJqty(wotqt.get(i));
                workDto.setWflag("00010");
                workDto.setWseq("01");
                workDto.setGlotnono(ls_lotno);
                workDto.setGqty01(wotqt.get(i));
                ll_winqty = ll_winqty + winqt.get(i);
                ll_wotqty = ll_wotqty + wotqt.get(i);
                result = appcom01Service.FPLANW010_Update_GQTY(workDto);
                if (!result){
                    log.info("error Exception =====> FPLANW010_Update_GQTY" );
                    return "error";
                }

            }
            workDto.setWinqt(ll_winqty);
            workDto.setWotqt(ll_wotqty);
            workDto.setWflag("00020");  //조립완료
            workDto.setWseq("02");
            if(lotno == null || lotno.length() == 0 || lotno.equals("")) {
                result = appcom01Service.FPLANW020_Insert(workDto);
                if (!result){
                    log.info("error Exception =====> FPLANW020_Insert" );
                    return "error";
                }
            }else{
                workDto.setLotno(lotno);
                result = appcom01Service.FPLANW020_Update(workDto);
                if (!result){
                    log.info("error Exception =====> FPLANW020_Update" );
                    return "error";
                }
            }
            if (!result){
                return "error";
            }
        }
//        workDto.setWinqt(winqt);
//        workDto.setWqty(wotqt);
//        workDto.setQty(wotqt);
//        workDto.setWotqt(wotqt);
//        workDto.setJqty(wotqt);
//        workDto.setPcode(pcode);
        result = appcom01Service.FPLAN_Update(workDto);
        if (!result){
            log.info("error Exception =====> FPLAN_Update  " );
            return "error";
        }

        return "success";
    }


    //검사공정삭제
    @RequestMapping(value="/w020del", method = RequestMethod.GET)
    public String Appcom02_delete(@RequestParam("plan_no") String plan_no
            ,HttpServletRequest request){
        FPLANW010_VO workDto = new FPLANW010_VO();
        workDto.setPlan_no(plan_no);
        workDto.setWflag("00020");
        workDto.setWseq("02");
        wperDto.setPlan_no(plan_no);
        wperDto.setWseq("02");
        wperDto.setWflag("00020");
        result = appcom01Service.FPLANW020_Delete(workDto);
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

        workDto.setDecision("3");
        workDto.setDecision2("0");
        workDto.setClsflag("1");
        workDto.setQcqty(0);
        workDto.setQcdate("");
        result = appcom01Service.FPLAN_Update(workDto);
        if (!result) {
            log.info("error =====> FPLAN_Update");
            return "error";
        }

        var ls_line = "99";
        return "redirect:/appm/list02";
    }


    @RequestMapping(value="/w020iwkupd", method = RequestMethod.POST)
    public String AppW020IworkUpdate_index(
            @RequestParam("startDate") String startDate
            ,@RequestParam("keyplan") String keyplan
            ,@RequestParam("wflag") String wflag
            ,@RequestParam( value =  "plan_no[]") List<String> plan_no
            ,@RequestParam( value =  "lotno[]") List<String> lotno
            ,@RequestParam( value =  "pcode[]") List<String> pcode
            ,@RequestParam( value =  "prodqty[]") List<String> prodqty
            , Model model
            , HttpServletRequest request){

        try {

            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);
            String lsWseq = "";
            String ls_seq = "";
            String ls_wseq = "";
            String year = startDate.substring(0,4) ;
            String month = startDate.substring(5,7) ;
            String day   = startDate.substring(8,10) ;
            startDate = year + month + day ;
            IworkDto.setIndate(startDate);
            Boolean result = false;
            if( lotno.size() > 0){
                for(int i = 0; i < lotno.size(); i++){
                    IworkDto.setCustcd("KDMES");
                    IworkDto.setSpjangcd("ZZ");
                    IworkDto.setPlan_no(keyplan);
                    IworkDto.setLotno(lotno.get(i));
                    IworkDto.setWflag(wflag);
                    IworkDto.setPcode(pcode.get(i));
                    IworkDto.setQty(Integer.parseInt(prodqty.get(i)));
                    IworkDto.setSqty(0);
                    IworkDto.setBqty(0);
                    IworkDto.setIndate(getToDate());
                    IworkDto.setWseq("01");
//                    if (i == 0){ ls_wseq = appcom01Service.GetWIworkWseq(IworkDto); }
//                     String ls_seq = appcom01Service.FPLAN_IWORK_MAXWSEQ(IworkDto);

                        int ll_seq =  i + 1;
                        ls_seq = Integer.toString(ll_seq);
                        if(ls_seq.length() == 1){
                            ls_seq = "00" + ls_seq;
                        }else if(ls_seq.length() == 2){
                            ls_seq = "0" + ls_seq;
                        }
                    IworkDto.setSeq(ls_seq);
                    result = appcom01Service.FPLANI_IWORK_Insert(IworkDto);

//                  result = appcom01Service.FPLANI_IWORK_update(IworkDto);
                    if (!result){
                        log.info("error Exception =====> FPLANI_IWORK_Insert" );
                        return "error";
                    }
//                    log.info("frdate Exception =====>" + frdate);
//                    log.info("jcode  Exception =====>" + jcode.get(i));
//                    log.info("jqty  Exception =====>" + jqty.get(i));
                }
            }


        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }



    @RequestMapping(value="/w030upd", method = RequestMethod.POST)
    public String AppW030Update_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("inputdate") String inputdate
            ,@RequestParam("wremark") String wremark
            ,@RequestParam( value =  "plan_no[]") List<String> plan_no
            ,@RequestParam( value =  "lotno[]") List<String> lotno
            ,@RequestParam( value =  "winqt[]") List<Integer> winqt
            ,@RequestParam( value =  "wono[]") List<String> wono
            ,Model model, HttpServletRequest request) throws Exception {
        FPLANW010_VO workDto = new FPLANW010_VO();
        String ls_yeare = inputdate.substring(0,4);
        String ls_mm = inputdate.substring(5,7);
        String ls_dd = inputdate.substring(8,10);
        inputdate =  ls_yeare + ls_mm + ls_dd;
        workDto.setCustcd(custcd);
        workDto.setSpjangcd(spjangcd);
        workDto.setWseq("03");
        workDto.setWfrdt(inputdate);
        workDto.setWtrdt(inputdate);
        workDto.setWflag("00030");  //조립완료
        workDto.setDecision("5");
        workDto.setDecision3("5");
        workDto.setAseq("0");
        workDto.setStore("W01");
        workDto.setBqty(0);
        workDto.setWbdqt(0);
        workDto.setWtable("TB_FPLAN_W090");
        workDto.setQcdate(inputdate);
        workDto.setWremark(wremark);
        workDto.setWsyul(0);
        String wstdt = inputdate;
        String wendt = inputdate;

        workDto.setWstdt(wstdt);
        workDto.setWendt(wendt);
        workDto.setIndate(getToDate());

        if( plan_no.size() > 0){
            for(int i = 0; i < plan_no.size(); i++){
                workDto.setPlan_no(plan_no.get(i));
                workDto.setQty(winqt.get(i));
                workDto.setPlan_no(plan_no.get(i));
                workDto.setPlan_no(plan_no.get(i));
                workDto.setLotno(lotno.get(i));
                workDto.setWono(wono.get(i));
                if(appcom01Service.GetFPLANW030_Detail(workDto) == null){
                    result = appcom01Service.FPLANW030_Insert(workDto);
                    if (!result){
                        log.info("error Exception =====> FPLANW030_Insert" );
                        return "error";
                    }
                }else{
                    result = appcom01Service.FPLANW030_Update(workDto);
                    if (!result){
                        log.info("error Exception =====> FPLANW030_Update" );
                        return "error";
                    }
                }
                if (!result){
                    return "error";
                }
            }
        }
//        workDto.setWinqt(winqt);
//        workDto.setWqty(wotqt);
//        workDto.setQty(wotqt);
//        workDto.setWotqt(wotqt);
//        workDto.setJqty(wotqt);
//        workDto.setPcode(pcode);
        result = appcom01Service.FPLAN_Update(workDto);
        if (!result){
            log.info("error Exception =====> FPLAN_Update  " );
            return "error";
        }

        return "success";
    }


    //검사공정삭제
    @RequestMapping(value="/w030del", method = RequestMethod.GET)
    public String Appcom03_delete(@RequestParam("plan_no") String plan_no
            ,HttpServletRequest request){
        FPLANW010_VO workDto = new FPLANW010_VO();
        workDto.setPlan_no(plan_no);
        workDto.setWflag("00050");
        workDto.setWseq("03");
        result = appcom01Service.FPLANW030_Delete(workDto);
        if (!result) {
            log.info("error =====> FPLANW030_Delete");
            return "error";
        }

        //log.info("w010del plan_no =====>" + plan_no);

        workDto.setDecision("3");
        workDto.setDecision3("0");
        workDto.setClsflag("1");
        result = appcom01Service.FPLAN_Update(workDto);
        if (!result) {
            log.info("error =====> FPLAN_Update");
            return "error";
        }

        var ls_line = "99";
        return "redirect:/appm/list21";
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



    private String setDateFormat(String arg){

        String year = arg.substring(0,4) ;
        String month = arg.substring(5,7) ;
        String day   = arg.substring(8,10) ;
        return arg = year + month + day ;
    }
    private String getFrDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, -100); // 빼고 싶다면 음수 입력
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
