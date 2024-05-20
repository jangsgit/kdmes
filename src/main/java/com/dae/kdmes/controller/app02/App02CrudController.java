package com.dae.kdmes.controller.app02;

import com.dae.kdmes.DTO.App01.*;
import com.dae.kdmes.DTO.App02.Index10Dto;
import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.App02.Index10Service;
import com.dae.kdmes.Service.App02.Index11Service;
import com.dae.kdmes.Service.App01.Index01Service;
import com.dae.kdmes.Service.App01.Index02Service;
import com.dae.kdmes.Service.App01.Index03Service;
import com.dae.kdmes.Service.App01.Index04Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.constraints.Length;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

// @RestController : return을 텍스트로 반환함.
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app02m", method = RequestMethod.POST)
public class App02CrudController {
    private final Index10Service service10;
    private final Index11Service service11;
    private final Index01Service service01;
    private final Index02Service service02;
    private final Index03Service service03;
    CommonDto CommDto = new CommonDto();
    PopupDto popupDto = new PopupDto();
    Index01Dto index01Dto = new Index01Dto();
    Index02Dto index02Dto = new Index02Dto();
    Index03Dto index03Dto = new Index03Dto();
    Index04Dto index04Dto = new Index04Dto();
    Index10Dto index10Dto = new Index10Dto();
    IndexCa613Dto indexCa613Dto = new IndexCa613Dto();
    Index11Dto index11Dto = new Index11Dto();
    List<PopupDto> popupListDto = new ArrayList<>();
    List<IndexCa613Dto> indexCa613ListDto = new ArrayList<>();
    List<Index01Dto> index01ListDto = new ArrayList<>();
    List<Index02Dto> index02ListDto = new ArrayList<>();
    List<Index03Dto> index03List = new ArrayList<>();
    List<Index10Dto> index10ListDto = new ArrayList<>();
    List<Index11Dto> index11ListDto = new ArrayList<>();
    List<Index10Dto> index10List = new ArrayList<>();

    protected Log log =  LogFactory.getLog(this.getClass());



    @RequestMapping(value="/comcodesave")
    public String App01ComCodeSave_index(  @RequestParam("com_cls") String com_cls,
                                           @RequestParam("com_cnam") String com_cnam,
                                           Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index01Dto.setCom_cls(com_cls);
        index01Dto.setCom_cnam(com_cnam);
        index01ListDto = service01.getComCodeList(index01Dto);
        if(index01ListDto.isEmpty() || index01ListDto.size() == 0){
            result = service01.InsertComCode(index01Dto);
        }else{
            result = service01.UpdateComCode(index01Dto);
        }
        if (!result) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/comcodedel")
    public String App01ComCodeDel_index(  @RequestParam("com_cls") String com_cls,
                                          @RequestParam("com_cnam") String com_cnam,
                                          Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index01Dto.setCom_cls(com_cls);
        index01Dto.setCom_cnam(com_cnam);
        index01ListDto = service01.getComCodeList(index01Dto);
        result = service01.DeleteComCode(index01Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }

    //업체분류현황
    @GetMapping(value="/comcodelist")
    public Object App01ComCodeList_index(@RequestParam("searchtxt") String searchtxt,
                                         Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index01Dto.setCom_cls(searchtxt);
            index01ListDto = service01.getComCodeList(index01Dto);

            model.addAttribute("comcodeList",index01ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index01ListDto;
    }

    //업체분류현황
    @GetMapping(value="/comcodelists")
    public Object App01ComCodeLists_index(@RequestParam("searchtxt") String searchtxt,
                                         Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index01Dto.setCom_cls(searchtxt);
            index01ListDto = service01.getComCodeLists(index01Dto);

            model.addAttribute("comcodeLists",index01ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index01ListDto;
    }

    //담당자현황
    @GetMapping(value="/wperidlist")
    public Object App01WperidList_index(@RequestParam("searchtxt") String searchtxt,
                                          Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index01Dto.setCom_cls(searchtxt);
            index01ListDto = service01.getWperidlist(index01Dto);

            model.addAttribute("wperidList",index01ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index01ListDto;
    }

    @RequestMapping(value="/comcodedetailsave")
    public String App01ComCodeDetailSave_index(  @RequestParam("com_cls") String com_cls,
                                           @RequestParam("com_cnam") String com_cnam,
                                           @RequestParam("com_code") String com_code,
                                           @RequestParam("com_rem1") String com_rem1,
                                           @RequestParam("com_rem2") String com_rem2,
                                           @RequestParam("com_work") String com_work,
                                           Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index01Dto.setCom_cls(com_cls);
        index01Dto.setCom_cnam(com_cnam);
        index01Dto.setCom_code(com_code);
        index01Dto.setCom_rem1(com_rem1);
        index01Dto.setCom_rem2(com_rem2);
        index01Dto.setCom_work(com_work);
        String ls_comcode = service01.GetComCodeCheck(index01Dto);
        if(ls_comcode == null || ls_comcode.equals("")){
            result = service01.InsertComCodeDetail(index01Dto);
        }else{
            result = service01.UpdateComCodeDetail(index01Dto);
        }
        if (!result) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/comcodedetaildel")
    public String App01ComCodeDetailDel_index(  @RequestParam("com_cls") String com_cls,
                                          @RequestParam("com_cnam") String com_cnam,
                                          @RequestParam("com_code") String com_code,
                                          Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index01Dto.setCom_cls(com_cls);
        index01Dto.setCom_cnam(com_cnam);
        index01Dto.setCom_code(com_code);
        //index01ListDto = service01.getComCodeDetailList(index01Dto);
        result = service01.DeleteComCodeDetail(index01Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }

    @GetMapping(value="/comcodedetaillist")
    public Object App01ComdodeDetailList_index(@RequestParam("searchtxt") String searchtxt,
                                           @RequestParam("com_cls") String com_cls,
                                           Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공통코드등록");
        CommDto.setMenuUrl("기준정보>공통코드등록");
        CommDto.setMenuCode("index01");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            if(com_cls == null || com_cls.equals("")){
                com_cls = "%";
            }
            log.debug("searchtxt =====>" + searchtxt );

            index01Dto.setCom_cls(com_cls);
            index01Dto.setCom_cnam(searchtxt);;
            index01ListDto = service01.GetComcodeDetailList(index01Dto);
            model.addAttribute("index01ListDto",index01ListDto);

        } catch (Exception ex) {
            log.info("App01ComdodeDetailList_index Exception =====>" + ex.toString());
        }

        return index01ListDto;
    }

    //업체분류현황
    @GetMapping(value="/getWflagList")
    public Object getWflagList(@RequestParam("searchtxt") String searchtxt,
                                          Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index02Dto.setCom_code(searchtxt);
            index02ListDto = service02.getWflagList(index02Dto);

            model.addAttribute("WflagList",index02ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index02ListDto;
    }

    @GetMapping(value="/getFplanDetailList")
    public Object App01getFplanDetailList_index(@RequestParam("searchtxt") String searchtxt,
                                               @RequestParam("com_code") String com_code,
                                               Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공통코드등록");
        CommDto.setMenuUrl("기준정보>공통코드등록");
        CommDto.setMenuCode("index01");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            if(com_code == null || com_code.equals("")){
                com_code = "%";
            }
            log.debug("searchtxt =====>" + searchtxt );

            index02Dto.setCom_cls(com_code);
            index02Dto.setCom_cnam(searchtxt);;
            index02ListDto = service02.GetFplanDetailList(index02Dto);
            model.addAttribute("index02ListDto",index02ListDto);

        } catch (Exception ex) {
            log.info("App01ComdodeDetailList_index Exception =====>" + ex.toString());
        }

        return index02ListDto;
    }

    @RequestMapping(value="/fplandetailsave")
    public String App01FplanDetailSave_index(  @RequestParam("com_code") String com_code,
                                                 @RequestParam("com_work") String com_work,
                                                 @RequestParam("wrmcnm") String wrmcnm,
                                                 @RequestParam("wrmc") String wrmc,
                                                 @RequestParam("wperid") String wperid,
                                                 Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index02Dto.setCom_code(com_code);
        index02Dto.setCom_cnam(com_work);
        index02Dto.setWrmc(wrmc);
        index02Dto.setWperid(wperid);
        index02Dto.setWrmcnm(wrmcnm);
        String ls_fplan = service02.GetFplanCheck(index02Dto);
        if(ls_fplan == null || ls_fplan.equals("")){
            result = service02.InsertFplanDetail(index02Dto);
        }else{
            result = service02.UpdateFplanDetail(index02Dto);
        }
        if (!result) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/fplandetaildel")
    public String App01FplanDetailDel_index(  @RequestParam("wrmc") String wrmc,
                                                @RequestParam("wrmcnm") String wrmcnm,
                                                @RequestParam("wperid") String wperid,
                                                Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index02Dto.setWrmc(wrmc);
        index02Dto.setWrmcnm(wrmcnm);
        index02Dto.setWperid(wperid);
        //index01ListDto = service01.getComCodeDetailList(index01Dto);
        result = service02.DeleteFplanDetail(index02Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }

    @GetMapping(value="/fplandetaillist")
    public Object App01FplanDetailList_index(@RequestParam("searchtxt") String searchtxt,
                                               @RequestParam("com_code") String com_code,
                                               Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공통코드등록");
        CommDto.setMenuUrl("기준정보>공정별설비등록");
        CommDto.setMenuCode("index02");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            if(com_code == null || com_code.equals("")){
                com_code = "%";
            }
            log.debug("searchtxt =====>" + searchtxt );

            index02Dto.setCom_code(com_code);
            index02Dto.setCom_cnam(searchtxt);;
            index02ListDto = service02.GetFplanDetailList(index02Dto);
            model.addAttribute("index02ListDto",index02ListDto);

        } catch (Exception ex) {
            log.info("App01ComdodeDetailList_index Exception =====>" + ex.toString());
        }

        return index02ListDto;
    }
    @GetMapping(value="/index10/list")
    public Object App02List_index(@RequestParam("searchtxt") String searchtxt,
                                  @RequestParam("inmonthtxt") String inmonthtxt,
                                  @RequestParam("inweekstxt") String inweekstxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("생산계획등록");
        CommDto.setMenuUrl("생산계획>제품정보");
        CommDto.setMenuCode("index10");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index10Dto.setLotno(searchtxt);
            index10Dto.setInmonth(inmonthtxt);
            index10Dto.setInweeks(inweekstxt);
            index10ListDto = service10.GetFplanList(index10Dto);

            model.addAttribute("index10ListDto",index10ListDto);

        } catch (Exception ex) {
            log.info("App02List_index Exception =====>" + ex.toString());
        }

        return index10ListDto;
    }
    @RequestMapping(value="/index10/save")
    public String index10Save( @RequestPart(value = "key") Map<String, Object> param
            , Model model
            , HttpServletRequest request){

        Index10Dto _index10Dto = new Index10Dto();
        param.forEach((key, values) -> {
            switch (key) {
                case "indate":
                    _index10Dto.setIndate(values.toString());
                    break;
                case "lotno":
                    _index10Dto.setLotno(values.toString());
                    break;
                case "prod_sdate":
                    _index10Dto.setProd_sdate(values.toString());
                    break;
                case "prod_edate":
                    _index10Dto.setProd_edate(values.toString());
                    break;
                case "cltcd":
                    _index10Dto.setCltcd(values.toString());
                    break;
                case "orddate":
                    _index10Dto.setOrddate(values.toString());
                    break;
                case "perid":
                    _index10Dto.setPerid(values.toString());
                    break;
                case "ecltnm":
                    _index10Dto.setEcltnm(values.toString());
                    break;
                case "workdv":
                    _index10Dto.setWorkdv(values.toString());
                    break;
                case "pcode":
                    _index10Dto.setPcode(values.toString());
                    break;
                case "dem_flag":
                    _index10Dto.setDem_flag(Integer.parseInt(values.toString()));
                    break;
                case "prod_qty":
                    _index10Dto.setProd_qty(Integer.parseInt(values.toString()));
                    break;
                case "istore":
                    _index10Dto.setIstore(values.toString());
                    break;
                case "ostore":
                    _index10Dto.setOstore(values.toString());
                    break;
                case "rwflag":
                    _index10Dto.setRwflag(values.toString());
                    break;
                case "remark":
                    _index10Dto.setRemark(values.toString());
                    break;
                case "plan_no":
                    _index10Dto.setPlan_no(values.toString());
                    break;
                case "qcdate":
                    _index10Dto.setQcdate(values.toString());
                    break;
                case "inmonth":
                    _index10Dto.setInmonth(values.toString());
                    break;
                case "inweeks":
                    _index10Dto.setInweeks(values.toString());
                    break;
                default:
                    break;
            }
        });


            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);


            String wono = "";
            //String ls_chknull = service10.SelectCheckIndate(index10Dto);
            Boolean result = false;
            String plan_no = _index10Dto.getPlan_no();
            String indate = _index10Dto.getIndate();
            String lotno = _index10Dto.getLotno();
            String rwflag = _index10Dto.getRwflag();
            //log.info(index10Dto.getRwflag());
            if (plan_no == null || plan_no.equals("")) {
                plan_no = GetMaxSeq(indate);
                lotno =  ""; //사출공정 중에 로트번호 생성하기로 함.  //indate  + rwflag + plan_no.substring(8,12); //GetMaxSeq1(indate , rwflag);
                _index10Dto.setPlan_no(plan_no);
                _index10Dto.setLotno(lotno);
                wono = "P" + plan_no;
                _index10Dto.setWono(wono);
                result = service10.InsertFplan(_index10Dto);
                //log.info("result1");
                //log.info(result);
                if (!result) {
                    return "error";
                }
            } else {
                result = service10.UpdateFplan(_index10Dto);
                if (!result) {
                    return "error";
                }
            }
//            model.addAttribute("userformDto",userformDto);

        return "success";
    }

    @RequestMapping(value="/index10/del")
    public String index10Delete(  @RequestParam("planno") String planno,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        Index10Dto _index10Dto = new Index10Dto();
        _index10Dto.setPlan_no(planno);
        Boolean result = service10.DeleteFplan(_index10Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/index15/save")
    public String index15Save( @RequestPart(value = "key") Map<String, Object> param
            , Model model
            , HttpServletRequest request){

        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        param.forEach((key, values) -> {
            switch (key) {
                case "ibgdate":
                    _indexCa613Dto.setIbgdate(values.toString());
                    break;
                case "ibgnum":
                    _indexCa613Dto.setIbgnum(values.toString());
                    break;
                case "lotno":
                    _indexCa613Dto.setLotno(values.toString());
                    break;
                case "istore":
                    _indexCa613Dto.setIstore(values.toString());
                    break;
                case "ostore":
                    _indexCa613Dto.setOstore(values.toString());
                    break;
                case "cltcd":
                    _indexCa613Dto.setCltcd(values.toString());
                    break;
                case "acorp":
                    _indexCa613Dto.setAcorp(values.toString());
                    break;
                case "pcode":
                    _indexCa613Dto.setPcode(values.toString());
                    break;
                case "pname":
                    _indexCa613Dto.setPname(values.toString());
                    break;
                case "psize":
                    _indexCa613Dto.setPsize(values.toString());
                    break;
                case "punit":
                    _indexCa613Dto.setPunit(values.toString());
                    break;
                case "qty":
                    _indexCa613Dto.setQty(Integer.parseInt(values.toString()));
                    break;
                case "cqty":
                    _indexCa613Dto.setCqty(Integer.parseInt(values.toString()));
                    break;
                case "uamt":
                    _indexCa613Dto.setUamt(Integer.parseInt(values.toString()));
                    break;
                case "samt":
                    _indexCa613Dto.setSamt(Integer.parseInt(values.toString()));
                    break;
                case "remark":
                    _indexCa613Dto.setRemark(values.toString());
                    break;
                case "perid":
                    _indexCa613Dto.setPerid(values.toString());
                    break;
                case "pernm":
                    _indexCa613Dto.setPernm(values.toString());
                    break;
                default:
                    break;
            }
        });


        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        _indexCa613Dto.setIbgseq("001");
        String ibgnum = "";
        Boolean result = true;
        ibgnum = _indexCa613Dto.getIbgnum();
        if (ibgnum == null || ibgnum.equals("")) {
            ibgnum = GetMaxIbgnum(_indexCa613Dto.getIbgdate());
            _indexCa613Dto.setIbgnum(ibgnum);
            result = service10.InsertCa613(_indexCa613Dto);
            if (!result) {
                return "error";
            }
        } else {
            result = service10.UpdateCa613(_indexCa613Dto);
            if (!result) {
                return "error";
            }
        }
        return "success";
    }


    @RequestMapping(value="/index15/del")
    public String index15Delete(  @RequestParam("ibgdate") String ibgdate,
                                  @RequestParam("ibgnum") String ibgnum,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        _indexCa613Dto.setIbgdate(ibgdate);
        _indexCa613Dto.setIbgnum(ibgnum);
        log.info("ibgdate=====>" + ibgdate);
        log.info("ibgnum=====>" + ibgnum);
        Boolean result = service10.DeleteCa613(_indexCa613Dto);
        log.info("result=====>" + result);
        if (!result) {
            return "error";
        }
        return "success";
    }

    @GetMapping(value="/index15/list")
    public Object App15List_index(@RequestParam("searchtxt") String searchtxt,
                                  @RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                     Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            _indexCa613Dto.setFrdate(frdate);
            _indexCa613Dto.setTodate(todate);
            _indexCa613Dto.setPname(searchtxt);
            indexCa613ListDto = service10.SelectCa613List(_indexCa613Dto);
            model.addAttribute("index15List",indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App15List_index Exception =====>" + ex.toString());
        }

        return indexCa613ListDto;
    }

    @GetMapping(value="/index15/jglist")
    public Object App15JaegoList_index(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        List<IndexCa613Dto> _indexCa613ListDto = new ArrayList<>();
        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            _indexCa613Dto.setPname(searchtxt);
            _indexCa613ListDto = service10.SelectCa613JaegoList(_indexCa613Dto);
            model.addAttribute("index15List",_indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App15JaegoList_index Exception =====>" + ex.toString());
        }

        return _indexCa613ListDto;
    }


    @GetMapping(value="/index10/listtot")
    public Object App03ListTot_index(@RequestParam("jpbgubn") String jpbgubn,
                                     @RequestParam("jmodelcode") String jmodelcode,
                                     @RequestParam("conagita") String conagita,
                                     Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>제품정보");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(jpbgubn == null || jpbgubn.equals("")){
                jpbgubn = "%";
            }
            if(jmodelcode == null || jmodelcode.equals("")){
                jmodelcode = "%";
            }
            if(conagita == null || conagita.equals("")){
                conagita = "%";
            }
            index10Dto.setJpb_gubn(jpbgubn);
//            log.info("jpbgubn =====>" + jpbgubn);
            index10Dto.setJmodel_code(jmodelcode);
            index10Dto.setJpum(conagita);
            index10List = service10.GetJpumListTot(index10Dto);
            model.addAttribute("index10List",index10List);

        } catch (Exception ex) {
            log.info("App02ListTot_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index10List;
    }

    //거래처등록
    @GetMapping(value="/index10/listot")
    public Object App02ListTot_index(@RequestParam("conacorp") String conacorp,
                                     Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index10");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(conacorp == null || conacorp.equals("")){
                conacorp = "%";
            }
            index10Dto.setAcorp(conacorp);
            log.info("conacorp =====>" + index10Dto.getAcorp());
            index10ListDto = service10.GetCifListTot(index10Dto);
            model.addAttribute("index10List",index10ListDto);

        } catch (Exception ex) {
            log.info("App02ListTot_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index10ListDto;
    }
    //사원현황
    @GetMapping(value="/index10/insalist")
    public Object App02List_insalist(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index10Dto.setInname(searchtxt);
            index10Dto.setInsano(searchtxt);
            index10ListDto = service10.GetInsaList(index10Dto);
//            log.info("popupDto =====>" );
//            log.info(  popupDto);
            model.addAttribute("insalistDto",index10ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index10ListDto;
    }



    @GetMapping(value="/index10/tabclist")
    public String App12List_barcode(  @RequestParam("searchtxt") String searchtxt,
                                                Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        result = service01.DeleteComCodeDetail(index01Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }




    public String GetMaxSeq(String indate){

        String ls_seq = service10.SelectMaxSeq(indate);

        if(ls_seq == null){
            ls_seq = indate + "0001";
        }else{
            Integer ll_misnum = Integer.parseInt(ls_seq) + 1;
            ls_seq = ll_misnum.toString();
            switch (ls_seq.length()){
                case 1:
                    ls_seq = "000" + ls_seq;
                    break;
                case 2:
                    ls_seq = "00" + ls_seq;
                    break;
                case 3:
                    ls_seq = "0" + ls_seq;
                    break;
                default:
                    break;
            }

            ls_seq = indate + ls_seq;
        }
        log.info("GetMaxSeq Exception =====>" + ls_seq);
        return ls_seq;
    }

    public String GetMaxIbgnum(String indate){

        String ls_seq = service10.SelectMaxIbgnum(indate);

        if(ls_seq == null){
            ls_seq =  "0001";
        }else{
            Integer ll_misnum = Integer.parseInt(ls_seq) + 1;
            ls_seq = ll_misnum.toString();
            switch (ls_seq.length()){
                case 1:
                    ls_seq = "000" + ls_seq;
                    break;
                case 2:
                    ls_seq = "00" + ls_seq;
                    break;
                case 3:
                    ls_seq = "0" + ls_seq;
                    break;
                default:
                    break;
            }
        }
        log.info("GetMaxIbgnum  =====>" + ls_seq);
        return ls_seq;
    }




    public String GetMaxSeq1(String indate, String rwflag){

        String ls_seq1 = service10.SelectMaxLot(index10Dto);

        if(ls_seq1 == null){
            ls_seq1 = indate + rwflag + "0001";
        }else{
            Integer ll_misnum = Integer.parseInt(ls_seq1) + 1;
            ls_seq1 = ll_misnum.toString();
            switch (ls_seq1.length()){
                case 1:
                    ls_seq1 = "000" + ls_seq1;
                case 2:
                    ls_seq1 = "00" + ls_seq1;
                case 3:
                    ls_seq1 = "0" + ls_seq1;
                default:
                    break;
            }
            ls_seq1 =  indate + rwflag + ls_seq1;
        }
        log.info("GetMaxSeq1 Exception =====>" + ls_seq1);
        return ls_seq1;
    }

    //입고창고현황
    @GetMapping(value="/index10/istorelist")
    public Object App02istoreList_index(@RequestParam("searchtxt") String searchtxt,
                                        Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index01Dto.setCom_cls(searchtxt);
            index01ListDto = service10.GetIstorelist(index01Dto);

            model.addAttribute("istoreList",index01ListDto);
            log.info("istoreList Exception =====>" + searchtxt);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("istoreList Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }
        log.info("istoreList Exception =====>" + index01ListDto);
        return index01ListDto;
    }

    //입고창고현황
    @GetMapping(value="/index10/ostorelist")
    public Object App02ostoreList_index(@RequestParam("searchtxt") String searchtxt,
                                        Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index01Dto.setCom_cls(searchtxt);
            index01ListDto = service10.Getostorelist(index01Dto);

            model.addAttribute("ostoreList",index01ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("ostoreList Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index01ListDto;
    }

    //주간생산계획현황
    @GetMapping(value="/getList")
    public Object getList(@RequestParam("frdate") String frdate,
                          @RequestParam("todate") String todate,
                               Model model, HttpServletRequest request) throws Exception{
        try {

            index11Dto.setFrdate(frdate);
            index11Dto.setTodate(todate);
            index11ListDto = service11.getList(index11Dto);

            model.addAttribute("getList",index11ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index11ListDto;
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
