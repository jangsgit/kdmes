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
    public Object AppW020_index(@RequestParam("frdate") String frdate,
                                @RequestParam("todate") String todate
            ,Model model, HttpServletRequest request) throws Exception {
        FPLAN_VO fplanDto = new FPLAN_VO();
//        if (searchtxt.equals("") ||  searchtxt == null || searchtxt.length() == 0){
            String searchtxt = "%";
//        }
        fplanDto.setLotno(searchtxt);
        fplanDto.setFrdate(frdate);
        fplanDto.setTodate(todate);
        itemDtoList = appcom01Service.GetFPLAN_List02_REG(fplanDto);

        model.addAttribute("itemDtoList", itemW010List);
        return itemDtoList;
    }


    @RequestMapping(value="/w020ordlist", method = RequestMethod.POST)
    public Object AppW020OrdList_index(@RequestParam(value = "lotno[]") List<String> lotno
                                       ,@RequestParam("flag") String flag
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
        fplanDto.setFdate("20000101");
        fplanDto.setTdate(tdate);
        fplanDto.setCltcd("%");
        fplanDto.setPcode("%");
        if(flag.equals("2") ){
            fplanDto.setLotno(lotno.get(0));
            itemDtoList = appcom01Service.GetFPLAN_List02(fplanDto);
        }else{
            //String[] arrLotno = lotno.split(",");
            HashMap hm = new HashMap();
            hm.put("itemcdArr", lotno) ;
            fplanDto.setCltcd("%");
            fplanDto.setPcode("%");
            itemDtoList = appcom01Service.GetFPLAN_List02Arr(hm);
        }

        return itemDtoList;
    }

    @RequestMapping(value="/w020salist", method = RequestMethod.POST)
    public Object AppW020saList_index( @RequestParam("lotno") String lotno
            ,Model model, HttpServletRequest request) throws Exception {

        CommDto.setMenuTitle("검사공정");  //
        CommDto.setMenuUrl("생산공정>검사공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        fplanDto.setLotno(lotno);
        itemDtoList = appcom01Service.GetFPLAN_List02_salist(fplanDto);
        return itemDtoList;
    }

    @RequestMapping(value="/w030ordlist", method = RequestMethod.POST)
    public Object AppW030OrdList_index(@RequestParam("lotno") String lotno
            ,@RequestParam("flag") String flag
            ,Model model, HttpServletRequest request) throws Exception {


        CommDto.setMenuTitle("조립공정");  //
        CommDto.setMenuUrl("생산공정>조립공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        String fdate = getFrDate();
        String tdate = getToDate();
        fplanDto.setLine("00");
        fplanDto.setWflag("00020");
        fplanDto.setFdate("20000101");
        fplanDto.setTdate(tdate);
        fplanDto.setCltcd("%");
        fplanDto.setPcode("%");
        if(flag.equals("2") ){
            fplanDto.setLotno(lotno);
            itemDtoList = appcom01Service.GetFPLAN_List02_JO(fplanDto);
        }else{
            String[] arrLotno = lotno.split(",");
            HashMap hm = new HashMap();
            hm.put("itemcdArr", arrLotno) ;
            fplanDto.setCltcd("%");
            fplanDto.setPcode("%");
            itemDtoList = appcom01Service.GetFPLAN_List02Arr_JO(hm);
        }

        return itemDtoList;
    }


    @RequestMapping(value="/w020his", method = RequestMethod.POST)
    public Object AppW020HIS_index(@RequestParam("frdate") String frdate,
                                   @RequestParam("todate") String todate,
                                   @RequestParam("searchtxt") String searchtxt,
                                   @RequestParam("inwinps") String inwinps
            ,Model model, HttpServletRequest request) throws Exception {
        CommDto.setMenuTitle("검사공정");  //
        CommDto.setMenuUrl("생산공정>검사공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        String fdate = getFrDate();
        String tdate = getToDate();
        fplanDto.setFdate(frdate);
        fplanDto.setTdate(todate);
        fplanDto.setLotno(searchtxt);
        fplanDto.setWrps(inwinps);
        itemDtoList = appcom01Service.GetFPLAN_List02_HIS(fplanDto);
        return itemDtoList;
    }


    @RequestMapping(value="/w020hismon", method = RequestMethod.POST)
    public Object AppW020HISMON_index(@RequestParam("frdate") String frdate,
                                   @RequestParam("todate") String todate,
                                   @RequestParam("searchtxt") String searchtxt,
                                   @RequestParam("inwinps") String inwinps
            ,Model model, HttpServletRequest request) throws Exception {
        CommDto.setMenuTitle("검사공정");  //
        CommDto.setMenuUrl("생산공정>검사공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        String fdate = getFrDate();
        String tdate = getToDate();
        frdate = frdate.substring(0,6);
        todate = todate.substring(0,6);
        fplanDto.setFdate(frdate);
        fplanDto.setTdate(todate);
        fplanDto.setLotno(searchtxt);
        fplanDto.setWrps(inwinps);
        itemDtoList = appcom01Service.GetFPLAN_List02_HISMON(fplanDto);
        return itemDtoList;
    }

    @RequestMapping(value="/w020per", method = RequestMethod.POST)
    public Object AppW020PER_index(@RequestParam("frdate") String frdate,
                                   @RequestParam("todate") String todate,
                                   @RequestParam("searchtxt") String searchtxt,
                                   @RequestParam("inwinps") String inwinps
            ,Model model, HttpServletRequest request) throws Exception {
        CommDto.setMenuTitle("검사공정");  //
        CommDto.setMenuUrl("생산공정>검사공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        String fdate = getFrDate();
        String tdate = getToDate();
        fplanDto.setFdate(frdate);
        fplanDto.setTdate(todate);
        fplanDto.setLotno(searchtxt);
        fplanDto.setWrps(inwinps);
        itemDtoList = appcom01Service.GetFPLAN_List02_HISPER(fplanDto);
        return itemDtoList;
    }

    @RequestMapping(value="/w030his", method = RequestMethod.POST)
    public Object AppW030HIS_index(@RequestParam("searchtxt") String searchtxt
            ,Model model, HttpServletRequest request) throws Exception {
        CommDto.setMenuTitle("조립공정");  //
        CommDto.setMenuUrl("생산공정>조립공정");
        CommDto.setMenuCode("appcom01");
        FPLAN_VO fplanDto = new FPLAN_VO();
        List<FPLAN_VO> itemDtoList = new ArrayList<>();
        String fdate = getFrDate();
        String tdate = getToDate();
        fplanDto.setFdate(fdate);
        fplanDto.setTdate(tdate);
        fplanDto.setLotno(searchtxt);
        itemDtoList = appcom01Service.GetFPLAN_List02_HIS_JO(fplanDto);
        return itemDtoList;
    }


    @RequestMapping(value="/w030", method = RequestMethod.POST)
    public Object AppW030_index(@RequestParam("frdate") String frdate,
                                @RequestParam("todate") String todate
            ,Model model, HttpServletRequest request) throws Exception {

        CommDto.setMenuTitle("조립공정");  //
        CommDto.setMenuUrl("생산공정>조립공정");
        CommDto.setMenuCode("appcom21");
        String searchtxt = "%";
        fplanDto.setLotno(searchtxt);
        fplanDto.setPcode(searchtxt);
        fplanDto.setFrdate(frdate);
        fplanDto.setTodate(todate);
//        log.info("searchtxt =====>" + searchtxt);
//        log.info("frdate =====>" + frdate);
//        log.info("todate =====>" + todate);
        itemDtoList = appcom01Service.GetFPLAN_List02_REG_JO(fplanDto);
        //itemDtoList = appcom01Service.GetFPLAN_List03(fplanDto);

        model.addAttribute("itemDtoList", itemDtoList);
        return itemDtoList;
    }

    //검사등록
    @RequestMapping(value="/w020upd", method = RequestMethod.POST)
    public String AppW020Update_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("inputdate") String inputdate
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("wremark") String wremark
            ,@RequestParam("wrps") String wrps
            ,@RequestParam("lotno") String lotno
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("winqt") Integer winqt
            ,@RequestParam("wotqt") Integer wotqt
            ,@RequestParam("wqcqt") Integer wqcqt
            ,@RequestParam("wsumqt") Integer wsumqt
            ,@RequestParam("wboxsu") Integer wboxsu
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("salotno") String salotno
            ,@RequestParam("wono") String wono
            ,@RequestParam("inwcode") String inwcode
            ,@RequestParam("inwbdqt") Integer inwbdqt
            ,@RequestParam("demflag") String demflag
            ,@RequestParam("lotnoarr[]") List<String> lotnoarr
            ,@RequestParam("gqtyArr[]") List<Integer> gqtyArr
            ,@RequestParam("planArr[]") List<String> planArr
            ,Model model, HttpServletRequest request) throws Exception {
        FPLANW010_VO workDto = new FPLANW010_VO();
        FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();

//        String ls_yeare = inputdate.substring(0,4);
//        String ls_mm = inputdate.substring(5,7);
//        String ls_dd = inputdate.substring(8,10);
//        inputdate =  ls_yeare + ls_mm + ls_dd;
        String ls_lotno = "";
        String ls_seq = "";
        Integer ll_lotno =  0;
        Integer ll_wotqty = 0;          //투입량 검사량
        Integer ll_winqty = 0;          //검사량
        Integer ll_wboxsu = 0;          //박스수량
        Integer ll_wsumqt = 0;          //누적수량
        Integer ll_inwbdqt = 0;          //불량수량
        Integer ll_inwqcqt = 0;          //완료수량
        if(inwbdqt == null){
            inwbdqt = 0;
        }
        ll_inwbdqt = inwbdqt;
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
        workDto.setWboxsu(wboxsu);
        workDto.setWsumqt(wsumqt);
        workDto.setDemflag("1");

        String wstdt = inputdate;
        String wendt = inputdate;

        workDto.setWstdt(wstdt);
        workDto.setWendt(wendt);
        workDto.setIndate(inputdate);
        workDto.setInperid(wrps);
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
//        검사수량 wotqt
//        박스수량 boxsu1
//        누적수량 sumqt
//        완료수량 wqcqt

        for(int i = 0; i < lotnoarr.size(); i++){
            salotno = lotnoarr.get(i);
            plan_no = planArr.get(i);
            Integer ll_wotqt =  gqtyArr.get(i);
            Integer ll_wqcqt =  gqtyArr.get(i);
            workDto.setLotno(salotno);
            workDto.setPlan_no(plan_no);
            workDto.setWono(wono);
            workDto.setWqty(ll_wotqt);
            workDto.setWotqt(ll_wotqt);
            workDto.setQty(ll_wotqt);
            workDto.setJqty(ll_wotqt);
            workDto.setQcqty(ll_wqcqt);
            workDto.setWflag("00010");
            workDto.setWseq("01");
            workDto.setGlotnono(ls_lotno);
            workDto.setGqty01(ll_wotqt);
            ll_winqty =  winqt;     //투입수량
            ll_wotqty =  ll_wotqt;     //검사수량
//            log.info("glotno =====>" + workDto.getGlotnono() );
//            log.info("gqty01 =====>" + workDto.getGqty01() );
//            log.info("plan_no =====>" + workDto.getPlan_no() );
//            log.info("wrps =====>" + workDto.getWrps() );
//            log.info("otqt =====>" + workDto.getWotqt() );
//            log.info("wsumqt =====>" + workDto.getWsumqt() );

            if(salotno.length() > 0) {
                result = appcom01Service.FPLANW010_Update_GQTY(workDto);
                if (!result) {
                    log.info("error Exception =====> FPLANW010_Update_GQTY");
                    return "error";
                }
            }
            //사출로트가 있다면.
            if(salotno.length() > 0){
                IworkDto.setCustcd("KDMES");
                IworkDto.setSpjangcd("ZZ");
                IworkDto.setPlan_no(plan_no);
                IworkDto.setLotno(salotno);
                IworkDto.setWflag("00010");
                IworkDto.setGlotno(ls_lotno);
                IworkDto.setQty(ll_wotqt);
                IworkDto.setJqty(ll_wqcqt);
                IworkDto.setSqty(wsumqt);
                IworkDto.setBqty(inwbdqt);
                IworkDto.setIndate(getToDate());
                IworkDto.setWseq("01");

                ls_seq = appcom01Service.FPLAN_IWORK_MAXWSEQ(IworkDto);
                if(ls_seq == null || ls_seq.length() == 0 || ls_seq.equals("")){
                    ls_seq = "001";
                }else{
                    int ll_seq = Integer.parseInt(ls_seq) + 1;
                    ls_seq = Integer.toString(ll_seq);
                    if(ls_seq.length() == 1){
                        ls_seq = "00" + ls_seq;
                    }else if(ls_seq.length() == 2){
                        ls_seq = "0" + ls_seq;
                    }
                }

                IworkDto.setSeq(ls_seq);
                result = appcom01Service.FPLANI_IWORK_Insert(IworkDto);
                if (!result){
                    log.info("error Exception =====> FPLANI_IWORK_Insert" );
                    return "error";
                }
                workDto.setWflag("00020");
                workDto.setWflag("00020");
                result = appcom01Service.FPLAN_Update(workDto);
                if (!result){
                    log.info("error Exception =====> FPLAN_Update  " );
                    return "errossr";
                }
                workDto.setWflag("00020");
                result = appcom01Service.FPLAN_Update(workDto);
                if (!result){
                    log.info("error Exception =====> FPLAN_Update  " );
                    return "error";
                }
            }
        }


        workDto.setWqty(wotqt);
        workDto.setWotqt(wotqt);
        workDto.setQty(wotqt);
        workDto.setWinqt(winqt);       //합산검사량이 최종검사량
        workDto.setWsumqt(wsumqt);
        workDto.setWqcqt(wqcqt);
        workDto.setDemflag(demflag);
        workDto.setWcode(inwcode);
        workDto.setWflag("00020");  //검사
        workDto.setWseq("02");
        workDto.setWbdqt(ll_inwbdqt);
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
        //-------------------------------------------- 불량내역등록
        if (ll_inwbdqt > 0){
            wbadDto.setCustcd(custcd);
            wbadDto.setSpjangcd(spjangcd);
            wbadDto.setPlan_no(ls_lotno);       //검사lot로 대체 함.
            wbadDto.setWflag(wflag);
            wbadDto.setWbqty(inwbdqt);
            wbadDto.setWcode(inwcode);
            wbadDto.setPcode(pcode);
            wbadDto.setIndate(getToDate());
            String lsChkseq = appcom01Service.FPLAN_WBAD_SELECT(wbadDto);
            if (lsChkseq == null  || lsChkseq.equals("")){
                String ls_bseq = appcom01Service.FPLAN_WBAD_MAXWSEQ(wbadDto);
                if(ls_bseq == null){
                    ls_bseq = "01";
                }else{
                    int ll_seq =  Integer.parseInt(ls_bseq) + 1;
                    ls_bseq = Integer.toString(ll_seq);
                    if(ls_bseq.length() == 1){ls_bseq = "0" + ls_bseq;}
                }
                wbadDto.setWseq(ls_bseq);
                log.info("custcd=====>" + custcd );
                log.info("spjangcd=====>" + spjangcd );
                log.info("ls_lotno=====>" + ls_lotno );
                log.info("wflag=====>" + wflag );
                log.info("inwbdqt=====>" + inwbdqt );
                log.info("inwcode=====>" + inwcode );
                log.info("pcode=====>" + pcode );
                log.info("getToDate()=====>" + getToDate() );
                log.info("ls_bseq=====>" + ls_bseq );
                appcom01Service.FPLAN_WBAD_Insert(wbadDto) ;
            }else{
                wbadDto.setWseq(lsChkseq);
                appcom01Service.FPLAN_WBAD_Update(wbadDto);
            }
        }

        //--------------------------------------------
        //workDto.setWflag("00090");  //조립완료
        //workDto.setWseq("02");
        //workDto.setClsflag("4");        // 생산완료
        //workDto.setWorkdv("4");
        //workDto.setDecision("4");
       // workDto.setDecision1("4");
//        result = appcom01Service.FPLAN_Update(workDto);
//        if (!result){
//            log.info("error Exception =====> FPLAN_Update  " );
//            return "error";
//        }

        return "success";
    }


    //검사공정삭제
    @RequestMapping(value="/w020del", method = RequestMethod.GET)
    public String Appcom02_delete(@RequestParam("lotno") String lotno
                                  ,@RequestParam("planno") String planno
            ,HttpServletRequest request){
        FPLANW010_VO workDto = new FPLANW010_VO();
        FPLAN_VO _wsumDto = new FPLAN_VO();
        FPLAN_VO _wsumDto_result = new FPLAN_VO();
        workDto.setGlotnono(lotno);
        workDto.setQcqty(0);
        workDto.setQcdate("");
        workDto.setWflag("00020");
        workDto.setWseq("02");
        workDto.setClsflag("3");        // 생산완료
        workDto.setWorkdv("3");
        workDto.setDecision("3");
        workDto.setDecision1("3");
        workDto.setPlan_no(planno);
        result = appcom01Service.FPLAN_Update_GDEL(workDto);
        if (!result) {
           // log.info("error =====> FPLAN_Update_GDEL");
           // return "error";
        }
        workDto.setLotno(lotno);
        result = appcom01Service.FPLANW020_Delete(workDto);
        if (!result) {
            log.info("error =====> FPLANW020_Delete");
            return "error";
        }

        //기존 검사된 사용량 조회하여 업데이트
        _wsumDto.setPlan_no(planno);
        Optional<FPLAN_VO>  optionlResult = Optional.ofNullable(appcom01Service.GetFPLAN_List02_GSUM(_wsumDto));
        optionlResult.ifPresent(result -> {
            workDto.setLotno(result.getLotno());
            workDto.setGqty01(result.getWotqt());
//            log.info("setPlan_no=====>" + _wsumDto.getPlan_no());
//            log.info("getWotqt=====>" + result.getLotno());
//            log.info("getLotno  =====>" + result.getWotqt());
        });
        if(!optionlResult.isPresent()){
            workDto.setLotno(lotno);
            workDto.setGqty01(0);
        }
        result = appcom01Service.FPLANW010_Update_GDEL(workDto);
        if (!result){
            //log.info("error Exception =====> FPLANW010_Update_GDEL" );
            //return "error";
        }



        IworkDto.setCustcd("KDMES");
        IworkDto.setSpjangcd("ZZ");
        IworkDto.setWflag("00010");
        IworkDto.setGlotno(lotno);
        result = appcom01Service.FPLAN_IWORK_Delete(IworkDto);
        if (!result){
            //log.info("error Exception =====> FPLAN_IWORK_Delete" );
            //return "error";
        }

        workDto.setPlan_no(lotno);
        result = appcom01Service.FPLAN_WBAD_Delete(workDto);
        if (!result){
            //log.info("error Exception =====> FPLAN_IWORK_Delete" );
            //return "error";
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


    //검사등록

    @RequestMapping(value="/w030upd", method = RequestMethod.POST)
    public String AppW030Update_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("inputdate") String inputdate
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("wremark") String wremark
            ,@RequestParam("wrps") String wrps
            ,@RequestParam("lotno") String lotno
            ,@RequestParam("plan_no") String plan_no
            ,@RequestParam("winqt") Integer winqt
            ,@RequestParam("wotqt") Integer wotqt
            ,@RequestParam("wqcqt") Integer wqcqt
            ,@RequestParam("wsumqt") Integer wsumqt
            ,@RequestParam("wboxsu") Integer wboxsu
            ,@RequestParam("wflag") String wflag
            ,@RequestParam("salotno") String salotno
            ,@RequestParam("wono") String wono
            ,@RequestParam("inwcode") String inwcode
            ,@RequestParam("inwbdqt") Integer inwbdqt
            ,@RequestParam("demflag") String demflag
            ,Model model, HttpServletRequest request) throws Exception {
        FPLANW010_VO workDto = new FPLANW010_VO();
        FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();
//        String ls_yeare = inputdate.substring(0,4);
//        String ls_mm = inputdate.substring(5,7);
//        String ls_dd = inputdate.substring(8,10);
//        inputdate =  ls_yeare + ls_mm + ls_dd;
        String ls_lotno = "";
        String ls_seq = "";
        Integer ll_lotno =  0;
        Integer ll_wotqty = 0;          //투입량
        Integer ll_winqty = 0;          //검사량
        Integer ll_wboxsu = 0;          //박스수량
        Integer ll_wsumqt = 0;          //누적수량
        Integer ll_inwbdqt = 0;          //불량수량
        Integer ll_inwqcqt = 0;          //불량수량
        if(inwbdqt == null){
            inwbdqt = 0;
        }
        ll_inwbdqt = inwbdqt;
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
        if(wrps == null){
            wrps = "";
        }
        workDto.setWrps(wrps);
        workDto.setWsyul(0);
        workDto.setWboxsu(wboxsu);
        workDto.setWsumqt(wsumqt);
        workDto.setDemflag(demflag);

        String wstdt = inputdate;
        String wendt = inputdate;

        workDto.setWstdt(wstdt);
        workDto.setWendt(wendt);
        workDto.setIndate(inputdate);
        workDto.setInperid(wrps);
        workDto.setWtable("TB_FPLAN_W090");
        if(lotno == null || lotno.length() == 0 || lotno.equals("")) {
            ls_lotno = appcom01Service.FPLAN_W030_MAXLOT(workDto);
            if(ls_lotno == null || ls_lotno.length() == 0){
                ls_lotno = inputdate.substring(0, 6) + "0001J" ;
            }else{
                ll_lotno = Integer.parseInt(ls_lotno) + 1;
                ls_lotno = ll_lotno.toString() + 'J';
            }
        }else{
            ls_lotno = lotno;
        }
        workDto.setLotno(salotno);
        workDto.setPlan_no(plan_no);
        workDto.setWono(wono);
        workDto.setWqty(wotqt);
        workDto.setWotqt(wotqt);
        workDto.setQty(wotqt);
        workDto.setJqty(wotqt);
        workDto.setQcqty(wqcqt);
        workDto.setWflag("00010");
        workDto.setWseq("01");
        workDto.setGlotnono(ls_lotno);
        workDto.setGqty01(wqcqt);
        workDto.setWbdqt(ll_inwbdqt);
        ll_winqty =  winqt;     //투입수량
        ll_wotqty =  wotqt;     //검사수량
        log.info("glotno =====>" + workDto.getGlotnono() );
        log.info("salotno =====>" + workDto.getLotno() );
        log.info("gqty01 =====>" + workDto.getGqty01() );
        log.info("plan_no =====>" + workDto.getPlan_no() );
        log.info("wrps =====>" + workDto.getWrps() );
        log.info("otqt =====>" + workDto.getWotqt() );
        log.info("wsumqt =====>" + workDto.getWsumqt() );
        log.info("ll_inwbdqt =====>" + ll_inwbdqt);

        if(salotno.length() > 0) {
            result = appcom01Service.FPLANW010_Update_JQTY(workDto);
            if (!result) {
                log.info("error Exception =====> FPLANW010_Update_GQTY");
                return "error";
            }
        }
        //사출로트가 있다면.
        if(salotno.length() > 0){
            IworkDto.setCustcd("KDMES");
            IworkDto.setSpjangcd("ZZ");
            IworkDto.setPlan_no(plan_no);
            IworkDto.setLotno(salotno);
            IworkDto.setWflag("00030");
            IworkDto.setGlotno(ls_lotno);
            IworkDto.setQty(wotqt);
            IworkDto.setSqty(0);
            IworkDto.setBqty(ll_inwbdqt);
            IworkDto.setIndate(getToDate());
            IworkDto.setWseq("01");
            ls_seq = appcom01Service.FPLAN_IWORK_MAXWSEQ(IworkDto);
            if(ls_seq == null || ls_seq.length() == 0 || ls_seq.equals("")){
                ls_seq = "001";
            }else{
                int ll_seq = Integer.parseInt(ls_seq) + 1;
                ls_seq = Integer.toString(ll_seq);
                if(ls_seq.length() == 1){
                    ls_seq = "00" + ls_seq;
                }else if(ls_seq.length() == 2){
                    ls_seq = "0" + ls_seq;
                }
            }
            IworkDto.setSeq(ls_seq);
            result = appcom01Service.FPLANI_IWORK_Insert(IworkDto);
            if (!result){
                log.info("error Exception =====> FPLANI_IWORK_Insert" );
                return "error";
            }
        }


        workDto.setWinqt(winqt);       //투입량  합산검사량이 최종검사량
        workDto.setWotqt(winqt);        //투입량
        workDto.setWqcqt(wqcqt);        //검사량
        workDto.setWflag("00030");  //검사
        workDto.setWseq("03");
        if(lotno == null || lotno.length() == 0 || lotno.equals("")) {
            result = appcom01Service.FPLANW030_Insert(workDto);
            if (!result){
                log.info("error Exception =====> FPLANW030_Insert" );
                return "error";
            }
        }else{
            workDto.setLotno(lotno);
            result = appcom01Service.FPLANW030_Update(workDto);
            if (!result){
                log.info("error Exception =====> FPLANW030_Update" );
                return "error";
            }
        }
        if (!result){
            return "error";
        }
        //-------------------------------------------- 불량내역등록
        if (ll_inwbdqt > 0){
            wbadDto.setCustcd(custcd);
            wbadDto.setSpjangcd(spjangcd);
            wbadDto.setPlan_no(ls_lotno);       //검사lot로 대체 함.
            wbadDto.setWflag(wflag);
            wbadDto.setWbqty(inwbdqt);
            wbadDto.setWcode(inwcode);
            wbadDto.setPcode(pcode);
            wbadDto.setIndate(getToDate());
            String lsChkseq = appcom01Service.FPLAN_WBAD_SELECT(wbadDto);
            if (lsChkseq == null  || lsChkseq.equals("")){
                String ls_bseq = appcom01Service.FPLAN_WBAD_MAXWSEQ(wbadDto);
                if(ls_bseq == null){
                    ls_bseq = "01";
                }else{
                    int ll_seq =  Integer.parseInt(ls_bseq) + 1;
                    ls_bseq = Integer.toString(ll_seq);
                    if(ls_bseq.length() == 1){ls_bseq = "0" + ls_bseq;}
                }
                wbadDto.setWseq(ls_bseq);
                log.info("custcd=====>" + custcd );
                log.info("spjangcd=====>" + spjangcd );
                log.info("ls_lotno=====>" + ls_lotno );
                log.info("wflag=====>" + wflag );
                log.info("inwbdqt=====>" + inwbdqt );
                log.info("inwcode=====>" + inwcode );
                log.info("pcode=====>" + pcode );
                log.info("getToDate()=====>" + getToDate() );
                log.info("ls_bseq=====>" + ls_bseq );
                appcom01Service.FPLAN_WBAD_Insert(wbadDto) ;
            }else{
                wbadDto.setWseq(lsChkseq);
                appcom01Service.FPLAN_WBAD_Update(wbadDto);
            }
        }

        //--------------------------------------------
        //workDto.setWflag("00090");  //조립완료
        //workDto.setWseq("02");
        //workDto.setClsflag("4");        // 생산완료
        //workDto.setWorkdv("4");
        //workDto.setDecision("4");
        // workDto.setDecision1("4");
        result = appcom01Service.FPLAN_Update(workDto);
        if (!result){
            log.info("error Exception =====> FPLAN_Update  " );
            return "error";
        }

        return "success";
    }


    //검사공정삭제
    @RequestMapping(value="/w030del", method = RequestMethod.GET)
    public String Appcom03_delete(@RequestParam("lotno") String lotno
            ,@RequestParam("planno") String planno
            ,HttpServletRequest request){
        FPLANW010_VO workDto = new FPLANW010_VO();
        workDto.setGlotnono(lotno);
        workDto.setQcqty(0);
        workDto.setQcdate("");
        workDto.setWflag("00030");
        workDto.setWseq("03");
        workDto.setClsflag("3");        // 생산완료
        workDto.setWorkdv("3");
        workDto.setDecision("3");
        workDto.setDecision1("3");
        workDto.setPlan_no(planno);
        result = appcom01Service.FPLAN_Update_GDEL(workDto);
        if (!result) {
            // log.info("error =====> FPLAN_Update_GDEL");
            // return "error";
        }

        IworkDto.setCustcd("KDMES");
        IworkDto.setSpjangcd("ZZ");
        IworkDto.setWflag("00010");
        IworkDto.setGlotno(lotno);

        result = appcom01Service.FPLANW010_Update_JDEL(workDto);
        if (!result){
            //log.info("error Exception =====> FPLANW010_Update_GDEL" );
            //return "error";
        }
        workDto.setLotno(lotno);
        log.info("lotno =====> " + lotno);
        result = appcom01Service.FPLANW030_Delete(workDto);
        if (!result) {
            log.info("error =====> FPLANW030_Delete");
            return "error";
        }

        IworkDto.setGlotno(lotno);
        result = appcom01Service.FPLAN_IWORK_Delete(IworkDto);
        if (!result){
            //log.info("error Exception =====> FPLAN_IWORK_Delete" );
            //return "error";
        }

        workDto.setPlan_no(lotno);
        result = appcom01Service.FPLAN_WBAD_Delete(workDto);
        if (!result){
            //log.info("error Exception =====> FPLAN_IWORK_Delete" );
            //return "error";
        }
        log.info("w030deln =====> " + result );
        var ls_line = "99";
        return "redirect:/appm/list03";
    }


    @RequestMapping(value="/w030iwkupd", method = RequestMethod.POST)
    public String AppW030IworkUpdate_index(
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




//    @RequestMapping(value="/w030upd", method = RequestMethod.POST)
//    public String AppW030Update_index(@RequestParam("custcd") String custcd
//            ,@RequestParam("spjangcd") String spjangcd
//            ,@RequestParam("inputdate") String inputdate
//            ,@RequestParam("pcode") String pcode
//            ,@RequestParam("wremark") String wremark
//            ,@RequestParam("wrps") String wrps
//            ,@RequestParam("lotno") String lotno
//            ,@RequestParam("plan_no") String plan_no
//            ,@RequestParam("winqt") Integer winqt
//            ,@RequestParam("wotqt") Integer wotqt
//            ,@RequestParam("wqcqt") Integer wqcqt
//            ,@RequestParam("wsumqt") Integer wsumqt
//            ,@RequestParam("wboxsu") Integer wboxsu
//            ,@RequestParam("wflag") String wflag
//            ,@RequestParam("salotno") String salotno
//            ,@RequestParam("wono") String wono
//            ,@RequestParam("inwcode") String inwcode
//            ,@RequestParam("inwbdqt") Integer inwbdqt
//            ,@RequestParam("demflag") String demflag
//            ,Model model, HttpServletRequest request) throws Exception {
//        FPLANW010_VO workDto = new FPLANW010_VO();
//        FPLANWBAD_VO wbadDto = new FPLANWBAD_VO();
////        String ls_yeare = inputdate.substring(0,4);
////        String ls_mm = inputdate.substring(5,7);
////        String ls_dd = inputdate.substring(8,10);
////        inputdate =  ls_yeare + ls_mm + ls_dd;
//        String ls_lotno = "";
//        String ls_seq = "";
//        Integer ll_lotno =  0;
//        Integer ll_wotqty = 0;          //투입량
//        Integer ll_winqty = 0;          //검사량
//        Integer ll_wboxsu = 0;          //박스수량
//        Integer ll_wsumqt = 0;          //누적수량
//        Integer ll_inwbdqt = 0;          //불량수량
//        Integer ll_inwqcqt = 0;          //불량수량
//        if(inwbdqt == null){
//            inwbdqt = 0;
//        }
//        ll_inwbdqt = inwbdqt;
//        workDto.setCustcd(custcd);
//        workDto.setSpjangcd(spjangcd);
//        workDto.setWfrdt(inputdate);
//        workDto.setWtrdt(inputdate);
//        workDto.setDecision("5");
//        workDto.setDecision3("5");
//        workDto.setAseq("0");
//        workDto.setStore("W01");
//        workDto.setBqty(0);
//        workDto.setWbdqt(0);
//        workDto.setWtable("TB_FPLAN_W090");
//        workDto.setQcdate(inputdate);
//        workDto.setWremark(wremark);
//        workDto.setWrps(wrps);
//        workDto.setWsyul(0);
//        workDto.setWboxsu(wboxsu);
//        workDto.setWsumqt(wsumqt);
//        workDto.setDemflag(demflag);
//
//        String wstdt = inputdate;
//        String wendt = inputdate;
//
//        workDto.setWstdt(wstdt);
//        workDto.setWendt(wendt);
//        workDto.setIndate(inputdate);
//        workDto.setInperid(wrps);
//        workDto.setWtable("TB_FPLAN_W090");
//        if(lotno == null || lotno.length() == 0 || lotno.equals("")) {
//            ls_lotno = appcom01Service.FPLAN_W020_MAXLOT(workDto);
//            if(ls_lotno == null || ls_lotno.length() == 0){
//                ls_lotno = inputdate.substring(0, 6) + "0001J" ;
//            }else{
//                ll_lotno = Integer.parseInt(ls_lotno) + 1;
//                ls_lotno = ll_lotno.toString() + 'G';
//            }
//        }else{
//            ls_lotno = lotno;
//        }
//        workDto.setLotno(salotno);
//        workDto.setPlan_no(plan_no);
//        workDto.setWono(wono);
//        workDto.setWqty(wotqt);
//        workDto.setWotqt(wotqt);
//        workDto.setQty(wotqt);
//        workDto.setJqty(wotqt);
//        workDto.setQcqty(wqcqt);
//        workDto.setWflag("00010");
//        workDto.setWseq("01");
//        workDto.setGlotnono(ls_lotno);
//        workDto.setGqty01(wotqt);
//        ll_winqty =  winqt;     //투입수량
//        ll_wotqty =  wotqt;     //검사수량
//        log.info("glotno =====>" + workDto.getGlotnono() );
//        log.info("gqty01 =====>" + workDto.getGqty01() );
//        log.info("plan_no =====>" + workDto.getPlan_no() );
//        log.info("wrps =====>" + workDto.getWrps() );
//        log.info("otqt =====>" + workDto.getWotqt() );
//        log.info("wsumqt =====>" + workDto.getWsumqt() );
//
//        if(salotno.length() > 0) {
//            result = appcom01Service.FPLANW010_Update_GQTY(workDto);
//            if (!result) {
//                log.info("error Exception =====> FPLANW010_Update_GQTY");
//                return "error";
//            }
//        }
//        //사출로트가 있다면.
//        if(salotno.length() > 0){
//            IworkDto.setCustcd("KDMES");
//            IworkDto.setSpjangcd("ZZ");
//            IworkDto.setPlan_no(plan_no);
//            IworkDto.setLotno(salotno);
//            IworkDto.setWflag("00010");
//            IworkDto.setGlotno(ls_lotno);
//            IworkDto.setQty(wotqt);
//            IworkDto.setSqty(0);
//            IworkDto.setBqty(ll_inwbdqt);
//            IworkDto.setIndate(getToDate());
//            IworkDto.setWseq("01");
//            ls_seq = appcom01Service.FPLAN_IWORK_MAXWSEQ(IworkDto);
//            if(ls_seq == null || ls_seq.length() == 0 || ls_seq.equals("")){
//                ls_seq = "001";
//            }else{
//                int ll_seq = Integer.parseInt(ls_seq) + 1;
//                ls_seq = Integer.toString(ll_seq);
//                if(ls_seq.length() == 1){
//                    ls_seq = "00" + ls_seq;
//                }else if(ls_seq.length() == 2){
//                    ls_seq = "0" + ls_seq;
//                }
//            }
//            IworkDto.setSeq(ls_seq);
//            result = appcom01Service.FPLANI_IWORK_Insert(IworkDto);
//            if (!result){
//                log.info("error Exception =====> FPLANI_IWORK_Insert" );
//                return "error";
//            }
//        }
//
//
//        workDto.setWinqt(wsumqt);       //합산검사량이 최종검사량
//        workDto.setWotqt(wsumqt);
//        workDto.setQcqty(wqcqt);
//        workDto.setWflag("00030");  //조립
//        workDto.setWseq("03");
//        if(lotno == null || lotno.length() == 0 || lotno.equals("")) {
//            result = appcom01Service.FPLANW020_Insert(workDto);
//            if (!result){
//                log.info("error Exception =====> FPLANW020_Insert" );
//                return "error";
//            }
//        }else{
//            workDto.setLotno(lotno);
//            result = appcom01Service.FPLANW020_Update(workDto);
//            if (!result){
//                log.info("error Exception =====> FPLANW020_Update" );
//                return "error";
//            }
//        }
//        if (!result){
//            return "error";
//        }
//        //-------------------------------------------- 불량내역등록
//        if (ll_inwbdqt > 0){
//            wbadDto.setCustcd(custcd);
//            wbadDto.setSpjangcd(spjangcd);
//            wbadDto.setPlan_no(ls_lotno);       //검사lot로 대체 함.
//            wbadDto.setWflag(wflag);
//            wbadDto.setWbqty(inwbdqt);
//            wbadDto.setWcode(inwcode);
//            wbadDto.setPcode(pcode);
//            wbadDto.setIndate(getToDate());
//            String lsChkseq = appcom01Service.FPLAN_WBAD_SELECT(wbadDto);
//            if (lsChkseq == null  || lsChkseq.equals("")){
//                String ls_bseq = appcom01Service.FPLAN_WBAD_MAXWSEQ(wbadDto);
//                if(ls_bseq == null){
//                    ls_bseq = "01";
//                }else{
//                    int ll_seq =  Integer.parseInt(ls_bseq) + 1;
//                    ls_bseq = Integer.toString(ll_seq);
//                    if(ls_bseq.length() == 1){ls_bseq = "0" + ls_bseq;}
//                }
//                wbadDto.setWseq(ls_bseq);
//                log.info("custcd=====>" + custcd );
//                log.info("spjangcd=====>" + spjangcd );
//                log.info("ls_lotno=====>" + ls_lotno );
//                log.info("wflag=====>" + wflag );
//                log.info("inwbdqt=====>" + inwbdqt );
//                log.info("inwcode=====>" + inwcode );
//                log.info("pcode=====>" + pcode );
//                log.info("getToDate()=====>" + getToDate() );
//                log.info("ls_bseq=====>" + ls_bseq );
//                appcom01Service.FPLAN_WBAD_Insert(wbadDto) ;
//            }else{
//                wbadDto.setWseq(lsChkseq);
//                appcom01Service.FPLAN_WBAD_Update(wbadDto);
//            }
//        }
//
//        //--------------------------------------------
//        //workDto.setWflag("00090");  //조립완료
//        //workDto.setWseq("02");
//        //workDto.setClsflag("4");        // 생산완료
//        //workDto.setWorkdv("4");
//        //workDto.setDecision("4");
//        // workDto.setDecision1("4");
//        result = appcom01Service.FPLAN_Update(workDto);
//        if (!result){
//            log.info("error Exception =====> FPLAN_Update  " );
//            return "error";
//        }
//
//        return "success";
//    }
//
//    //검사공정삭제
//    @RequestMapping(value="/w030del", method = RequestMethod.GET)
//    public String Appcom03_delete(@RequestParam("lotno") String lotno
//            ,@RequestParam("planno") String planno
//            ,HttpServletRequest request){
//        FPLANW010_VO workDto = new FPLANW010_VO();
//        workDto.setGlotnono(lotno);
//        workDto.setQcqty(0);
//        workDto.setQcdate("");
//        workDto.setWflag("00030");
//        workDto.setWseq("03");
//        workDto.setClsflag("3");        // 생산완료
//        workDto.setWorkdv("3");
//        workDto.setDecision("3");
//        workDto.setDecision1("3");
//        workDto.setPlan_no(planno);
//        result = appcom01Service.FPLAN_Update_GDEL(workDto);
//        if (!result) {
//            // log.info("error =====> FPLAN_Update_GDEL");
//            // return "error";
//        }
//
//        IworkDto.setCustcd("KDMES");
//        IworkDto.setSpjangcd("ZZ");
//        IworkDto.setWflag("00010");
//        IworkDto.setGlotno(lotno);
//
//        result = appcom01Service.FPLANW010_Update_GDEL(workDto);
//        if (!result){
//            //log.info("error Exception =====> FPLANW010_Update_GDEL" );
//            //return "error";
//        }
//        workDto.setLotno(lotno);
//        result = appcom01Service.FPLANW020_Delete(workDto);
//        if (!result) {
//            log.info("error =====> FPLANW030_Delete");
//            return "error";
//        }
//
//        IworkDto.setGlotno(lotno);
//        result = appcom01Service.FPLAN_IWORK_Delete(IworkDto);
//        if (!result){
//            //log.info("error Exception =====> FPLAN_IWORK_Delete" );
//            //return "error";
//        }
//
//        workDto.setPlan_no(lotno);
//        result = appcom01Service.FPLAN_WBAD_Delete(workDto);
//        if (!result){
//            //log.info("error Exception =====> FPLAN_IWORK_Delete" );
//            //return "error";
//        }
//
//        var ls_line = "99";
//        return "redirect:/appm/list02";
//    }


//    @RequestMapping(value="/w030upd", method = RequestMethod.POST)
//    public String AppW030Update_index(@RequestParam("custcd") String custcd
//            ,@RequestParam("spjangcd") String spjangcd
//            ,@RequestParam("inputdate") String inputdate
//            ,@RequestParam("wremark") String wremark
//            ,@RequestParam( value =  "plan_no[]") List<String> plan_no
//            ,@RequestParam( value =  "lotno[]") List<String> lotno
//            ,@RequestParam( value =  "winqt[]") List<Integer> winqt
//            ,@RequestParam( value =  "wono[]") List<String> wono
//            ,Model model, HttpServletRequest request) throws Exception {
//        FPLANW010_VO workDto = new FPLANW010_VO();
//        String ls_yeare = inputdate.substring(0,4);
//        String ls_mm = inputdate.substring(5,7);
//        String ls_dd = inputdate.substring(8,10);
//        inputdate =  ls_yeare + ls_mm + ls_dd;
//        workDto.setCustcd(custcd);
//        workDto.setSpjangcd(spjangcd);
//        workDto.setWseq("03");
//        workDto.setWfrdt(inputdate);
//        workDto.setWtrdt(inputdate);
//        workDto.setWflag("00030");  //조립완료
//        workDto.setDecision("5");
//        workDto.setDecision3("5");
//        workDto.setAseq("0");
//        workDto.setStore("W01");
//        workDto.setBqty(0);
//        workDto.setWbdqt(0);
//        workDto.setWtable("TB_FPLAN_W090");
//        workDto.setQcdate(inputdate);
//        workDto.setWremark(wremark);
//        workDto.setWsyul(0);
//        String wstdt = inputdate;
//        String wendt = inputdate;
//
//        workDto.setWstdt(wstdt);
//        workDto.setWendt(wendt);
//        workDto.setIndate(getToDate());
//
//        if( plan_no.size() > 0){
//            for(int i = 0; i < plan_no.size(); i++){
//                workDto.setPlan_no(plan_no.get(i));
//                workDto.setQty(winqt.get(i));
//                workDto.setPlan_no(plan_no.get(i));
//                workDto.setPlan_no(plan_no.get(i));
//                workDto.setLotno(lotno.get(i));
//                workDto.setWono(wono.get(i));
//                if(appcom01Service.GetFPLANW030_Detail(workDto) == null){
//                    result = appcom01Service.FPLANW030_Insert(workDto);
//                    if (!result){
//                        log.info("error Exception =====> FPLANW030_Insert" );
//                        return "error";
//                    }
//                }else{
//                    result = appcom01Service.FPLANW030_Update(workDto);
//                    if (!result){
//                        log.info("error Exception =====> FPLANW030_Update" );
//                        return "error";
//                    }
//                }
//                if (!result){
//                    return "error";
//                }
//            }
//        }
////        workDto.setWinqt(winqt);
////        workDto.setWqty(wotqt);
////        workDto.setQty(wotqt);
////        workDto.setWotqt(wotqt);
////        workDto.setJqty(wotqt);
////        workDto.setPcode(pcode);
//        result = appcom01Service.FPLAN_Update(workDto);
//        if (!result){
//            log.info("error Exception =====> FPLAN_Update  " );
//            return "error";
//        }
//
//        return "success";
//    }


    //검사공정삭제
//    @RequestMapping(value="/w030del", method = RequestMethod.GET)
//    public String Appcom03_delete(@RequestParam("plan_no") String plan_no
//            ,HttpServletRequest request){
//        FPLANW010_VO workDto = new FPLANW010_VO();
//        workDto.setPlan_no(plan_no);
//        workDto.setWflag("00050");
//        workDto.setWseq("03");
//        result = appcom01Service.FPLANW030_Delete(workDto);
//        if (!result) {
//            log.info("error =====> FPLANW030_Delete");
//            return "error";
//        }
//
//        //log.info("w010del plan_no =====>" + plan_no);
//
//        workDto.setDecision("3");
//        workDto.setDecision3("0");
//        workDto.setClsflag("1");
//        result = appcom01Service.FPLAN_Update(workDto);
//        if (!result) {
//            log.info("error =====> FPLAN_Update");
//            return "error";
//        }
//
//        var ls_line = "99";
//        return "redirect:/appm/list21";
//    }

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
