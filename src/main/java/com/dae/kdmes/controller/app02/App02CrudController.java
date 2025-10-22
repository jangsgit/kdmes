package com.dae.kdmes.controller.app02;

import com.dae.kdmes.DTO.App01.*;
import com.dae.kdmes.DTO.App02.Index10Dto;
import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.DTO.Appm.FPLANW010_VO;
import com.dae.kdmes.DTO.Appm.FPLAN_VO;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.App02.Index10Service;
import com.dae.kdmes.Service.App02.Index11Service;
import com.dae.kdmes.Service.App01.Index01Service;
import com.dae.kdmes.Service.App01.Index02Service;
import com.dae.kdmes.Service.App01.Index03Service;
import com.dae.kdmes.Service.App01.Index04Service;
import com.dae.kdmes.Service.Appm.Appcom01Service;
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
import java.util.stream.Collectors;

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
    private final Appcom01Service appcom01Service;
    CommonDto CommDto = new CommonDto();
    PopupDto popupDto = new PopupDto();
    Index01Dto index01Dto = new Index01Dto();
    Index02Dto index02Dto = new Index02Dto();
    Index03Dto index03Dto = new Index03Dto();
    Index04Dto index04Dto = new Index04Dto();
    Index10Dto index10Dto = new Index10Dto();
    Index11Dto index11Dto = new Index11Dto();
    List<PopupDto> popupListDto = new ArrayList<>();
    List<IndexCa613Dto> indexCa613ListDto = new ArrayList<>();
    List<IndexCa611Dto> indexCa611ListDto = new ArrayList<>();
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
            index10Dto.setWrmc(searchtxt);
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
                case "wrmc":
                    _index10Dto.setWrmc(values.toString());
                    break;
                case "sachulflag":
                    _index10Dto.setSachulflag(values.toString());
                    break;
                case "sachultxt":
                    _index10Dto.setSachultxt(values.toString());
                    break;
                case "balno":
                    _index10Dto.setBalno(values.toString());
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

    @RequestMapping(value="/index10/savecopy")
    public String index10SaveCopy( @RequestPart(value = "key") Map<String, Object> param
            , Model model
            , HttpServletRequest request){

        Index10Dto _index10Dto = new Index10Dto();
        param.forEach((key, values) -> {
            switch (key) {
                case "plan_no":
                    _index10Dto.setPlan_no(values.toString());
                    break;
                case "inmonth":
                    _index10Dto.setInmonth(values.toString());
                    break;
                case "inweeks":
                    _index10Dto.setInweeks(values.toString());
                    break;
                case "inmonthcp":
                    _index10Dto.setInmonthcp(values.toString());
                    break;
                case "inweekscp":
                    _index10Dto.setInweekscp(values.toString());
                    break;
                default:
                    break;
            }
        });


        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);


        String wono = "";
        Boolean result = false;
        String indate = getToDate();
        _index10Dto.setIndate(indate);
        String plan_new = GetMaxSeq(indate);
        _index10Dto.setPlan_new(plan_new);
        wono = "P" + plan_new;
        _index10Dto.setWono(wono);
        result = service10.InsertFplanCopy(_index10Dto);
        if (!result) {
            return "error";
        }
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
                case "wonflag":
                    _indexCa613Dto.setWonflag(values.toString());
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


    @GetMapping(value="/index15/listmap")
    public Object App15ListMap_index(@RequestParam("searchtxt") String searchtxt,
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
            indexCa613ListDto = service10.SelectCa613ListMapChul(_indexCa613Dto);
            model.addAttribute("index15ListMap",indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App15ListMap_index Exception =====>" + ex.toString());
        }

        return indexCa613ListDto;
    }



    @GetMapping(value="/index19/listmap")
    public Object App19ListMap_index(@RequestParam("searchtxt") String searchtxt,
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
            _indexCa613Dto.setPcode(searchtxt);
            indexCa613ListDto = service10.SelectCa636ListMapChul(_indexCa613Dto);
            model.addAttribute("index19ListMap",indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App19ListMap_index Exception =====>" + ex.toString());
        }

        return indexCa613ListDto;
    }


    @RequestMapping(value="/index19/save")
    public String index19Save( @RequestPart(value = "key") Map<String, Object> param
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
                case "remark":
                    _indexCa613Dto.setRemark(values.toString());
                    break;
                case "perid":
                    _indexCa613Dto.setPerid(values.toString());
                    break;
                case "pernm":
                    _indexCa613Dto.setPernm(values.toString());
                    break;
                case "wonflag":
                    _indexCa613Dto.setWonflag(values.toString());
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
            ibgnum = GetMaxMovnum(_indexCa613Dto.getIbgdate());
            _indexCa613Dto.setIbgnum(ibgnum);
            result = service10.InsertCa636(_indexCa613Dto);
            if (!result) {
                return "error";
            }
        } else {
            result = service10.UpdateCa636(_indexCa613Dto);
            if (!result) {
                return "error";
            }
        }
        return "success";
    }


    @RequestMapping(value="/index19/del")
    public String index19Delete(  @RequestParam("ibgdate") String ibgdate,
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
        Boolean result = service10.DeleteCa636(_indexCa613Dto);
        log.info("result=====>" + result);
        if (!result) {
            return "error";
        }
        return "success";
    }


    @GetMapping(value="/index19/list")
    public Object App19List_index(@RequestParam("searchtxt") String searchtxt,
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
            indexCa613ListDto = service10.SelectCa636List(_indexCa613Dto);
            model.addAttribute("index19List",indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App15List_index Exception =====>" + ex.toString());
        }

        return indexCa613ListDto;
    }


    @GetMapping(value="/index19/jaegolist")
    public Object App19JaegoList_index(@RequestParam("searchtxt") String searchtxt,
                                  @RequestParam("frdate") String frdate,
                                  @RequestParam("todate") String todate,
                                  Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        Index03Dto _index03Dto = new Index03Dto();
        List<Index03Dto> _index03ListDto = new ArrayList<>();

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            _index03Dto.setFrdate(frdate);
            _index03Dto.setTodate(todate);
            _index03Dto.setPname(searchtxt);
            _index03ListDto = service10.SelectCa636StockList(_index03Dto);
            model.addAttribute("index19List",_index03ListDto);

        } catch (Exception ex) {
            log.info("App19JaegoList_index  Exception =====>" + ex.toString());
        }

        return _index03ListDto;
    }



    //출하현황
    @GetMapping(value="/index21/listmap")
    public Object App21ListMap_index(@RequestParam("inmonth") String inmonth,
                                     @RequestParam("inweeks") String inweeks,
                                     @RequestParam("balno") String balno,
                                     @RequestParam("pcode") String pcode,
                                     @RequestParam("demflag") String demflag,
                                     @RequestParam("acode") String acode,
                                     Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        List<IndexCa613Dto> _indexCa613ListDto = new ArrayList<>();

        try {
            _indexCa613Dto.setInmonth(inmonth);
            _indexCa613Dto.setInweeks(inweeks);
            _indexCa613Dto.setBalno(balno);
            _indexCa613Dto.setPcode(pcode);
            _indexCa613Dto.setDemflag(demflag);
            _indexCa613Dto.setCltcd(acode);
            _indexCa613ListDto = service10.SelectDa037List(_indexCa613Dto);
            model.addAttribute("index15ListMap",_indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App15ListMap_index Exception =====>" + ex.toString());
        }

        return _indexCa613ListDto;
    }


    //출하 바코드조회
    @GetMapping(value="/index21/listbarcode")
    public Object App21ListBarcode_index(@RequestParam("frdate") String frdate,
                                     @RequestParam("todate") String todate,
                                     @RequestParam("lotno") String lotno,
                                     @RequestParam("demflag") String demflag,
                                     Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        List<IndexCa613Dto> _indexCa613ListDto = new ArrayList<>();

        try {
            _indexCa613Dto.setFrdate(frdate);
            _indexCa613Dto.setTodate(todate);
            _indexCa613Dto.setLotno(lotno);
            _indexCa613Dto.setDemflag(demflag);
            _indexCa613ListDto = service10.SelectDa037ListBarcd(_indexCa613Dto);
            model.addAttribute("index15ListMap",_indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App21ListBarcode_index Exception =====>" + ex.toString());
        }

        return _indexCa613ListDto;
    }


    //출하 바코드조회
    @GetMapping(value="/index21/listjiyuk")
    public Object App21ListJiyuk_index(@RequestParam("frdate") String frdate,
                                         @RequestParam("todate") String todate,
                                         @RequestParam("remark") String remark,
                                         @RequestParam("demflag") String demflag,
                                         Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        List<IndexCa613Dto> _indexCa613ListDto = new ArrayList<>();

        try {
            _indexCa613Dto.setFrdate(frdate);
            _indexCa613Dto.setTodate(todate);
            _indexCa613Dto.setRemark(remark);
            _indexCa613Dto.setDemflag(demflag);
            _indexCa613ListDto = service10.SelectDa037ListJiyuk(_indexCa613Dto);
            model.addAttribute("index15ListMap",_indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App21ListJiyuk_index Exception =====>" + ex.toString());
        }

        return _indexCa613ListDto;
    }

    @GetMapping(value="/index15/chullist")
    public Object App15ListModal_index(@RequestParam("inibgdate") String ibgdate,
                                  @RequestParam("inibgnum") String ibgnum,
                                  @RequestParam("inltono") String lotno,
                                  Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa613OworkDto ca613OworkDto = new IndexCa613OworkDto();
        List<IndexCa613OworkDto> ca613OworkListDto = new ArrayList<>();

        try {
            ca613OworkDto.setIbgdate(ibgdate);
            ca613OworkDto.setIbgnum(ibgnum);
            ca613OworkDto.setLotno(lotno);
            ca613OworkListDto = service10.SelectCa613ChulList(ca613OworkDto);
            model.addAttribute("index15List",ca613OworkListDto);

        } catch (Exception ex) {
            log.info("App15ListModal_index =====>" + ex.toString());
        }

        return ca613OworkListDto;
    }

    @GetMapping(value="/index15/jglist")
    public Object App15JaegoList_index(@RequestParam("searchtxt") String searchtxt,
                                       @RequestParam("wonflag") String wonflag,
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
            _indexCa613Dto.setWonflag(wonflag);
            _indexCa613ListDto = service10.SelectCa613JaegoList(_indexCa613Dto);
            model.addAttribute("index15List",_indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App15JaegoList_index Exception =====>" + ex.toString());
        }

        return _indexCa613ListDto;
    }


    //생산량 등록
    @RequestMapping(value="/index15/chulsave", method = RequestMethod.POST)
    public String App15ChulSave_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("inibgdate") String ibgdate
            ,@RequestParam("inibgnum") String ibgnum
            ,@RequestParam("inlotno") String inlotno
            ,@RequestParam("inpcode") String pcode
            ,@RequestParam("inpname") String pname
            ,@RequestParam("inpsize") String psize
            ,@RequestParam("popwotqt") Integer wotqt
            ,@RequestParam("popwkotDate") String wkokdate
            ,@RequestParam("seq") String seq
            ,@RequestParam("inwotqt") Integer inwotqt,
            Model model, HttpServletRequest request
    ) throws Exception {

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        IndexCa613OworkDto ca613OworkDto = new IndexCa613OworkDto();
        IndexCa613OworkDto _ca613OworkDto = new IndexCa613OworkDto();
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        boolean result = false;

        if(seq == null || seq.length() == 0 || seq.equals("")) {
            seq = "";
        }
        ca613OworkDto.setCustcd(custcd);
        ca613OworkDto.setSpjangcd(spjangcd);
        ca613OworkDto.setIbgdate(ibgdate);
        ca613OworkDto.setIbgnum(ibgnum);
        ca613OworkDto.setLotno(inlotno);
        ca613OworkDto.setPcode(pcode);
        ca613OworkDto.setPname(pname);
        ca613OworkDto.setPsize(psize);
        ca613OworkDto.setWinqt(inwotqt);
        ca613OworkDto.setWotqt(wotqt);
        ca613OworkDto.setWotdt(wkokdate);
        ca613OworkDto.setOstore("W01");
        ca613OworkDto.setIndate(getToDate());
        ca613OworkDto.setInperid(userformDto.getPerid());
        if(seq == null || seq.equals("")) {
            String ls_seq = service10.CA613_OWORK_MAXWSEQ(ca613OworkDto);
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
            ca613OworkDto.setSeq(ls_seq);
            result = service10.CA613_OWORK_Insert(ca613OworkDto);
        }else{
            ca613OworkDto.setSeq(seq);
            result = service10.CA613_OWORK_Update(ca613OworkDto);
        }
        if (!result) {
            return "error";
        }

        _ca613OworkDto = service10.SelectCa613ChulListSum(ca613OworkDto);
        _indexCa613Dto.setPname(ca613OworkDto.getPname());
        _indexCa613Dto.setCqty(_ca613OworkDto.getWotqt());
        _indexCa613Dto.setIbgdate(ca613OworkDto.getIbgdate());
        _indexCa613Dto.setIbgnum(ca613OworkDto.getIbgnum());
        _indexCa613Dto.setIbgseq("001");
        result = service10.UpdateCa613(_indexCa613Dto);
        if (!result) {
            return "error";
        }
        String ls_wotqt = _ca613OworkDto.getWotqt().toString();
        return ls_wotqt;
    }


    //생산량 등록
    @RequestMapping(value="/index15/chuldel", method = RequestMethod.POST)
    public String App15ChulDel_index(@RequestParam("custcd") String custcd
            ,@RequestParam("spjangcd") String spjangcd
            ,@RequestParam("inibgdate") String ibgdate
            ,@RequestParam("inibgnum") String ibgnum
            ,@RequestParam("inlotno") String inlotno
            ,@RequestParam("seq") String seq,
                                      Model model, HttpServletRequest request
    ) throws Exception {

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        IndexCa613OworkDto ca613OworkDto = new IndexCa613OworkDto();
        IndexCa613OworkDto _ca613OworkDto = new IndexCa613OworkDto();
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        boolean result = false;
        ca613OworkDto.setCustcd(custcd);
        ca613OworkDto.setSpjangcd(spjangcd);
        ca613OworkDto.setIbgdate(ibgdate);
        ca613OworkDto.setIbgnum(ibgnum);
        ca613OworkDto.setLotno(inlotno);
        ca613OworkDto.setSeq(seq);
        service10.CA613_OWORK_Delete(ca613OworkDto);


        _ca613OworkDto = service10.SelectCa613ChulListSum(ca613OworkDto);
        _indexCa613Dto.setLotno(inlotno);
        _indexCa613Dto.setCqty(_ca613OworkDto.getWotqt());
        _indexCa613Dto.setIbgdate(ca613OworkDto.getIbgdate());
        _indexCa613Dto.setIbgnum(ca613OworkDto.getIbgnum());
        _indexCa613Dto.setIbgseq("001");
        result = service10.UpdateCa613(_indexCa613Dto);
        if (!result) {
            return "error";
        }
        String ls_wotqt = _ca613OworkDto.getWotqt().toString();

        return ls_wotqt;
    }


    @RequestMapping(value="/index21/save", method = RequestMethod.POST)
    public String index21Save( @RequestParam("deldate") String deldate
            ,@RequestParam("schdate") String schdate
            ,@RequestParam("delnum") String delnum
            ,@RequestParam("balno") String balno
            ,@RequestParam("lotno") String lotno
            ,@RequestParam("cltcd") String cltcd
            ,@RequestParam("acorp") String acorp
            ,@RequestParam("perid") String perid
            ,@RequestParam("pernm") String pernm
            ,@RequestParam("remark") String remark
            ,@RequestParam("ostore") String ostore
            ,@RequestParam("inmonth") String inmonth
            ,@RequestParam("inweeks") String inweeks
            ,@RequestParam("demflag") String demflag
            ,@RequestParam("pcodeArr[]") List<String> pcodeArr
            ,@RequestParam("pnameArr[]") List<String> pnameArr
            ,@RequestParam("psizeArr[]") List<String> psizeArr
            ,@RequestParam("pqtyArr[]") List<Integer> pqtyArr
            , Model model
            , HttpServletRequest request){

        IndexCa611Dto _indexCa611Dto = new IndexCa611Dto();
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        FPLANW010_VO   _workDto = new FPLANW010_VO();


        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        String ibgnum = "";
        Boolean result = true;
        _indexCa611Dto.setDeldate(deldate);
        _indexCa611Dto.setDelnum(delnum);
        _indexCa611Dto.setSchdate(schdate);
        _indexCa611Dto.setBalno(balno);
        _indexCa611Dto.setLotno(lotno);
        _indexCa611Dto.setCltcd(cltcd);
        _indexCa611Dto.setAcorp(acorp);
        _indexCa611Dto.setPerid(perid);
        _indexCa611Dto.setPernm(pernm);
        _indexCa611Dto.setRemark(remark);
        _indexCa611Dto.setOstore(ostore);
        _indexCa611Dto.setInmonth(inmonth);
        _indexCa611Dto.setInweeks(inweeks);
        _indexCa611Dto.setDemflag(demflag);
        ibgnum = _indexCa611Dto.getDelnum();
        if (ibgnum == null || ibgnum.equals("")) {
            ibgnum = GetMaxDelnum(_indexCa611Dto.getDeldate());
            _indexCa611Dto.setDelnum(ibgnum);
            result = service10.InsertDA036Sch(_indexCa611Dto);
            if (!result) {
                return "error";
            }
        } else {
            result = service10.UpdateDa036(_indexCa611Dto);
            if (!result) {
                return "error";
            }
        }

        //모두 삭제후 재 입력
        result = service10.DeleteDa037(_indexCa611Dto);
        if (!result) {
            //return "error";
        }
        _indexCa613Dto.setDeldate(_indexCa611Dto.getDeldate());
        _indexCa613Dto.setDelnum(_indexCa611Dto.getDelnum());
        String ls_delseq = "001";
        Integer ll_delseq = 0 ;
        for(int i = 0; i < pcodeArr.size(); i++){
            if(psizeArr.get(i) != null || !psizeArr.get(i).equals("")) {
                _indexCa613Dto.setPsize(psizeArr.get(i));
            }else{
                _indexCa613Dto.setPsize("");
            }
            _indexCa613Dto.setDelseq(ls_delseq);
            _indexCa613Dto.setPcode(pcodeArr.get(i));
            _indexCa613Dto.setPname(pnameArr.get(i));
            _indexCa613Dto.setQty(pqtyArr.get(i));
            ll_delseq = Integer.parseInt(ls_delseq) + 1;
            if(ll_delseq < 9){
                ls_delseq = "00" + ll_delseq.toString();
            }else{
                ls_delseq =  "0" + ll_delseq.toString();
            }
            result = service10.InsertDa037(_indexCa613Dto);
            if (!result) {
                return "error";
            }

            //재고계산
            _workDto.setIndate(_indexCa613Dto.getDeldate());
            _workDto.setPcode(pcodeArr.get(i));
            appcom01Service.SelectStockCal(_workDto);
        }


        return "success";
    }


    @RequestMapping(value="/index21/del")
    public String index21Delete(  @RequestParam("deldate") String deldate,
                                  @RequestParam("delnum") String delnum,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa611Dto _indexCa611Dto = new IndexCa611Dto();
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        IndexCa609Dto _indexCa609Dto = new IndexCa609Dto();
        List<IndexCa613Dto> itemCa613List = new ArrayList<>();
        FPLANW010_VO _workDto = new FPLANW010_VO();
        _indexCa611Dto.setDeldate(deldate);
        _indexCa611Dto.setDelnum(delnum);

        //재고계산 출하상세 리스트
        _indexCa613Dto.setDeldate(deldate);
        _indexCa613Dto.setDelnum(delnum);
        itemCa613List = service10.SelectDa037ChulList(_indexCa613Dto);



        Boolean result = service10.DeleteDA036Sch(_indexCa611Dto);
        if (!result) {
            return "error";
        }
        result = service10.DeleteDa037(_indexCa611Dto);
        if (!result) {
            return "error";
        }
        result = service10.UpdateCa609Chul(_indexCa611Dto);


        //재고계산
        for(int i = 0; i < itemCa613List.size(); i++){
            _workDto.setPcode(itemCa613List.get(i).getPcode());
            _workDto.setIndate(getToDate());
            log.info("getPcode Exception =====>" + _workDto.getPcode());
            appcom01Service.SelectStockCal(_workDto);
        }

        return "success";
    }

    //매출하대기
    @GetMapping(value="/index21/list")
    public Object App21List_index(@RequestParam("searchtxt") String searchtxt,
                                  @RequestParam("inmonth") String inmonth,
                                  @RequestParam("inweeks") String inweeks,
                                  Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa611Dto _indexCa611Dto = new IndexCa611Dto();

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            _indexCa611Dto.setInmonth(inmonth);
            _indexCa611Dto.setInweeks(inweeks);
            _indexCa611Dto.setBalno(searchtxt);
            indexCa611ListDto = service10.SelectDa036List(_indexCa611Dto);
            model.addAttribute("index21List",indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App15List_index Exception =====>" + ex.toString());
        }

        return indexCa611ListDto;
    }


    @GetMapping(value="/index21/chullist")
    public Object App21ListModal_index(@RequestParam("indeldate") String deldate,
                                       @RequestParam("indelnum") String delnum,
                                       Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        List<IndexCa613Dto> _indexCa613ListDto = new ArrayList<>();
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        try {
            _indexCa613Dto.setDeldate(deldate);
            _indexCa613Dto.setDelnum(delnum);
            _indexCa613ListDto = service10.SelectDa037ChulList(_indexCa613Dto);
            model.addAttribute("index21List",_indexCa613ListDto);

        } catch (Exception ex) {
            log.info("App21ListModal_index =====>" + ex.toString());
        }

        return _indexCa613ListDto;
    }



    @RequestMapping(value="/index23/save", method = RequestMethod.POST)
    public String index23Save(
            @RequestParam(value =  "cltnm[]") List<String> cltnmArr
            ,@RequestParam(value =  "balno[]") List<String> balnoArr
            ,@RequestParam(value =  "baldate[]") List<String> baldateArr
            ,@RequestParam(value =  "moncls[]") List<String> monclsArr
            ,@RequestParam(value =  "balflag[]") List<String> balflagArr
            ,@RequestParam(value =  "pcode[]") List<String> pcodeArr
            ,@RequestParam(value =  "pname[]") List<String> pnameArr
            ,@RequestParam(value =  "psize[]") List<String> psizeArr
            ,@RequestParam(value =  "punit[]") List<String> punitArr
            ,@RequestParam( value =  "qty[]") List<Integer> qtyArr
            ,@RequestParam( value =  "uamt[]") List<Integer> uamtArr
            ,@RequestParam( value =  "samt[]") List<Integer> samtArr
            ,@RequestParam(value =  "ischnm[]") List<String> ischnmArr
            ,@RequestParam(value =  "ischdate[]") List<String> ischdateArr
            ,@RequestParam(value =  "condi01[]") List<String> condi01Arr
            ,@RequestParam(value =  "condi02[]") List<String> condi02Arr
            ,@RequestParam(value =  "gumsunm[]") List<String> gumsunmArr
            ,@RequestParam(value =  "insunm[]") List<String> insunmArr
            , Model model
            , HttpServletRequest request){

        IndexCa609Dto _indexCa609Dto = new IndexCa609Dto();
        Index02Dto _index02Dto = new Index02Dto();
        Index03Dto _index03Dto = new Index03Dto();

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        String ls_balnum = "";
        String ls_pcode = "";
        String ls_cltcd = "";
        Integer ll_balnum = 0 ;
        Boolean result = true;
        Integer ll_idxkey = 0;
        _indexCa609Dto.setBaldate(baldateArr.get(0));
        ls_balnum = GetMaxBalnum(_indexCa609Dto.getDeldate());


        for(int i = 0; i < baldateArr.size(); i++){
            _index02Dto.setAcorp(cltnmArr.get(i));
            ls_cltcd = service10.GetCltcdCheck(_index02Dto);

            if(ls_cltcd != null  ) {
                _indexCa609Dto.setCltcd(ls_cltcd);
            }else{
                _indexCa609Dto.setCltcd(ls_cltcd);
            }
            _indexCa609Dto.setAcorp(cltnmArr.get(i));
            _indexCa609Dto.setBaldate(baldateArr.get(i));
            _indexCa609Dto.setBalnum(ls_balnum);
            _indexCa609Dto.setAcorp(cltnmArr.get(i));
            _indexCa609Dto.setBalno(balnoArr.get(i));
            _indexCa609Dto.setBalflag(balflagArr.get(i));
            _indexCa609Dto.setMoncls(monclsArr.get(i));
            _indexCa609Dto.setIschdate(ischdateArr.get(i));
            _indexCa609Dto.setIschnm(ischnmArr.get(i));
            _indexCa609Dto.setPunit(punitArr.get(i));
            _indexCa609Dto.setJpumcode(pcodeArr.get(i));
            _indexCa609Dto.setCondi01(condi01Arr.get(i));
            _indexCa609Dto.setCondi02(condi02Arr.get(i));
            _indexCa609Dto.setGumsunm(gumsunmArr.get(i));
            _indexCa609Dto.setInsunm(insunmArr.get(i));

            _index03Dto.setJpum(pnameArr.get(i));
            _index03Dto.setJpumcode(pcodeArr.get(i));
            ls_pcode = service10.GetJcodeCheck(_index03Dto);
            if(ls_pcode != null  ) {
                _indexCa609Dto.setPcode(ls_pcode);
            }else{
                _indexCa609Dto.setPcode("");
            }
            if(pnameArr.get(i) != null || !pnameArr.get(i).equals("")) {
                _indexCa609Dto.setPname(pnameArr.get(i));
            }else{
                _indexCa609Dto.setPname("");
            }
            if(psizeArr.get(i) != null || !psizeArr.get(i).equals("")) {
                _indexCa609Dto.setPsize(psizeArr.get(i));
            }else{
                _indexCa609Dto.setPsize("");
            }
            if(balflagArr.get(i) != null || !balflagArr.get(i).equals("")) {
                _indexCa609Dto.setBalflag(balflagArr.get(i));
            }else{
                _indexCa609Dto.setBalflag("");
            }
            if(monclsArr.get(i) != null || !monclsArr.get(i).equals("")) {
                _indexCa609Dto.setMoncls(monclsArr.get(i));
            }else{
                _indexCa609Dto.setMoncls("");
            }
            if(ischdateArr.get(i) != null || !ischdateArr.get(i).equals("")) {
                _indexCa609Dto.setIschdate(ischdateArr.get(i));
            }else{
                _indexCa609Dto.setIschdate("");
            }
            if(ischnmArr.get(i) != null || !ischnmArr.get(i).equals("")) {
                _indexCa609Dto.setIschnm(ischnmArr.get(i));
            }else{
                _indexCa609Dto.setIschnm("");
            }
            _indexCa609Dto.setBalseq("001");
            _indexCa609Dto.setQty(qtyArr.get(i));
            _indexCa609Dto.setUamt(uamtArr.get(i));
            _indexCa609Dto.setSamt(samtArr.get(i));
            ll_balnum = Integer.parseInt(ls_balnum) + 1;
            if(ll_balnum < 9){
                ls_balnum = "000" + ll_balnum.toString();
            }else if(ll_balnum < 99){
                ls_balnum =  "00" + ll_balnum.toString();
            }
            else{
                ls_balnum =  "0" + ll_balnum.toString();
            }

            ll_idxkey = service10.SelectCheckBalnum(_indexCa609Dto);

            if (ll_idxkey != null  && ll_idxkey > 0  ){
                continue;
            }else{
                result = service10.InsertCA609(_indexCa609Dto);
            }

            if (!result) {
                return "error";
            }
        }

        return "success";
    }


    @RequestMapping(value="/index23/del", method = RequestMethod.POST)
    public String index23Del(
            @RequestParam(value =  "idxkeyArr[]") List<Integer> idxkeyArr
            , Model model
            , HttpServletRequest request){

        IndexCa609Dto _indexCa609Dto = new IndexCa609Dto();

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        Boolean result = false;

        for(int i = 0; i < idxkeyArr.size(); i++){
            _indexCa609Dto.setIdxkey(idxkeyArr.get(i));
            result = service10.DeleteCA609(_indexCa609Dto);
            if (!result) {
                return "error";
            }
        }

        return "success";
    }


    @RequestMapping(value="/index23/chulsave", method = RequestMethod.POST)
    public String index23ChulSave(
            @RequestParam(value =  "idxkeyArr[]") List<Integer> idxkeyArr
            ,@RequestParam(value =  "pcodeArr[]") List<String> pcodeArr
            ,@RequestParam(value =  "chulqtyArr[]") List<String> chulqtyArr
            , Model model
            , HttpServletRequest request){

        IndexCa609Dto _indexCa609Dto = new IndexCa609Dto();
        FPLANW010_VO _workDto = new FPLANW010_VO();

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        Boolean result = false;

        String csv = idxkeyArr.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        String csv02 = chulqtyArr.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        _indexCa609Dto.setIdxkeyArr(csv);
        _indexCa609Dto.setChulqtyArr(csv02);
//        log.info(_indexCa609Dto.getIdxkeyArr());
        result = service10.InsertChulha(_indexCa609Dto);
//        if (!result) {
//            return "error";
//        }


        for(int i = 0; i < pcodeArr.size(); i++){
            //재고계산
            _workDto.setPcode(pcodeArr.get(i));
            _workDto.setIndate(getToDate());
            appcom01Service.SelectStockCal(_workDto);
        }

        return "success";
    }


    @GetMapping(value="/index23/list01")
    public Object App23ListTot_index(@RequestParam("frdate") String frdate,
                                     @RequestParam("todate") String todate,
                                     @RequestParam("acode") String acode,
                                     @RequestParam("balflag") String balflag,
                                     @RequestParam("balno") String balno,
                                     Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("발주등록");
        CommDto.setMenuUrl("기준정보>발주정보");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa609Dto _indexCa609Dto = new IndexCa609Dto();
        List<IndexCa609Dto> _indexCa609ListDto = new ArrayList<>();

        try {
            _indexCa609Dto.setFrdate(frdate);
            _indexCa609Dto.setTodate(todate);
            _indexCa609Dto.setCltcd(acode);
            _indexCa609Dto.setBalflag(balflag);
            _indexCa609Dto.setBalno(balno);
            _indexCa609ListDto = service10.SelectCa609List(_indexCa609Dto);
            model.addAttribute("index23List",_indexCa609ListDto);

        } catch (Exception ex) {
            log.info("App23ListTot_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _indexCa609ListDto;
    }



    @GetMapping(value="/index23/list02")
    public Object App23ListTot02_index(@RequestParam("frdate") String frdate,
                                     @RequestParam("todate") String todate,
                                     @RequestParam("pcode") String pcode,
                                     Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("발주등록");
        CommDto.setMenuUrl("기준정보>발주정보");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        IndexCa609Dto _indexCa609Dto = new IndexCa609Dto();
        List<IndexCa609Dto> _indexCa609ListDto = new ArrayList<>();

        try {
            _indexCa609Dto.setFrdate(frdate);
            _indexCa609Dto.setTodate(todate);
            _indexCa609Dto.setPcode(pcode);
            _indexCa609ListDto = service10.SelectCa609ListGroup(_indexCa609Dto);
            model.addAttribute("index23List",_indexCa609ListDto);

        } catch (Exception ex) {
            log.info("App23ListTot_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _indexCa609ListDto;
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



    public String GetMaxMovnum(String indate){

        String ls_seq = service10.SelectMaxMovnum(indate);

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


    public String GetMaxDelnum(String indate){

        String ls_seq = service10.SelectMaxDelnum(indate);

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
        log.info("GetMaxDelnum  =====>" + ls_seq);
        return ls_seq;
    }

    public String GetMaxBalnum(String indate){

        String ls_seq = service10.SelectMaxBalnum(indate);

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
        log.info("GetMaxBalnum  =====>" + ls_seq);
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

    private String getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }
}
