package com.dae.kdmes.controller.app02;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App01.Index02Dto;
import com.dae.kdmes.DTO.App01.Index03Dto;
import com.dae.kdmes.DTO.App01.Index04Dto;
import com.dae.kdmes.DTO.App02.Index10Dto;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.App02.Index10Service;
import com.dae.kdmes.Service.App01.Index01Service;
import com.dae.kdmes.Service.App01.Index02Service;
import com.dae.kdmes.Service.App01.Index03Service;
import com.dae.kdmes.Service.App01.Index04Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// @RestController : return을 텍스트로 반환함.
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app02m", method = RequestMethod.POST)
public class App02CrudController {
    private final Index10Service service10;

    private final Index01Service service01;

    private final Index02Service service02;

    private final Index03Service service03;

    private final Index04Service service04;
    CommonDto CommDto = new CommonDto();
    PopupDto popupDto = new PopupDto();

    Index01Dto index01Dto = new Index01Dto();

    Index02Dto index02Dto = new Index02Dto();

    Index03Dto index03Dto = new Index03Dto();

    Index04Dto index04Dto = new Index04Dto();

    Index10Dto index10Dto = new Index10Dto();

    List<PopupDto> popupListDto = new ArrayList<>();
    List<Index01Dto> index01ListDto = new ArrayList<>();

    List<Index02Dto> index02ListDto = new ArrayList<>();
    List<Index03Dto> index03List = new ArrayList<>();

    List<Index10Dto> index10ListDto = new ArrayList<>();

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
                                                 @RequestParam("wperid") Integer wperid,
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
                                                @RequestParam("wperid") Integer wperid,
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
            index10Dto.setPcode(searchtxt);
            index10ListDto = service10.GetFplanList(index10Dto);

            model.addAttribute("index10ListDto",index10ListDto);

        } catch (Exception ex) {
            log.info("App02List_index Exception =====>" + ex.toString());
        }

        return index10ListDto;
    }
    @RequestMapping(value="/index10/save")
    public String index10Save( @RequestParam("indate") String indate
            ,@RequestParam("lotno") String lotno
            ,@RequestParam("prod_sdate") String prod_sdate
            ,@RequestParam("prod_edate") String prod_edate
            ,@RequestParam("cltcd") String cltcd
            ,@RequestParam("qcdate") String qcdate
            ,@RequestParam("perid") String perid
            ,@RequestParam("ecltnm") String ecltnm
            ,@RequestParam("workdv") Integer workdv
            ,@RequestParam("pcode") String pcode
            ,@RequestParam("dem_flag") Integer dem_flag
            ,@RequestParam("prod_qty") BigDecimal prod_qty
            ,@RequestParam("istore") String istore
            ,@RequestParam("ostore") String ostore
            ,@RequestParam("rwflag") String rwflag
            ,@RequestParam("remark") String remark
            ,@RequestParam("plan_no") String plan_no

            , Model model
            , HttpServletRequest request){


            index10Dto.setIndate(indate);
            index10Dto.setLotno(lotno);
            index10Dto.setProd_sdate(prod_sdate);
            index10Dto.setProd_edate(prod_edate);
            index10Dto.setCltcd(cltcd);
            index10Dto.setQcdate(qcdate);
            index10Dto.setPerid(perid);
            index10Dto.setEcltnm(ecltnm);
            index10Dto.setWorkdv(workdv);
            index10Dto.setPcode(pcode);
            index10Dto.setDem_flag(dem_flag);
            index10Dto.setProd_qty(prod_qty);
           // index10Dto.setCls_flag(cls_flag);
            index10Dto.setIstore(istore);
            index10Dto.setOstore(ostore);
            index10Dto.setRwflag(rwflag);
            index10Dto.setRemark(remark);

            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);


            log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!" + indate.toString());

            //String ls_pcode = service10.GetFplanCheck(index10Dto);
            //String ls_chknull = service10.SelectCheckIndate(index10Dto);
            Boolean result = false;
            log.info("ls_pcode");
            log.info(index10Dto.getRemark());
            if (plan_no == null || plan_no.equals("")) {
                plan_no = GetMaxSeq(indate);
                index10Dto.setPlan_no(plan_no);
                result = service10.InsertFplan(index10Dto);
                log.info("result1");
                log.info(result);
                if (!result) {
                    return "error";
                }
            } else {
                index10Dto.setPlan_no(plan_no);
                result = service10.UpdateFplan(index10Dto);
                log.info("result2");
                log.info(result);
                if (!result) {
                    return "error";
                }
            }
//            model.addAttribute("userformDto",userformDto);

        return "success";
    }

    @RequestMapping(value="/index10/del")
    public String index10Delete(  @RequestParam("lotno") String lotno,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        index10Dto.setPlan_no(lotno);
        Boolean result = service10.DeleteFplan(index10Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }


    //간편주문 리스트 01 등록
    @GetMapping(value="/index03/ganlist01")
    public Object App03GanList01_index(@RequestParam("jpbgubn") String jkey,
                                       @RequestParam("flag") String flag,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("주문등록");
        CommDto.setMenuUrl("기준정보>주문등록");
        CommDto.setMenuCode("index14");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(jkey == null || jkey.equals("")){
                jkey = "%";
            }
            index03Dto.setJkey(jkey);
            index03List = service03.GetGanListBonsa01(index03Dto);
            model.addAttribute("index03GanList01",index03List);

        } catch (Exception ex) {
            log.info("App03GanList01_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index03List;
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
    public Object App02ListTot_index(@RequestParam("conacorp1") String conacorp1,
                                     @RequestParam("conacorp") String conacorp,
                                     @RequestParam("conagita") String conagita,
                                     @RequestParam("abonsadam1") String abonsadam1,
                                     Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index10");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(conacorp1 == null || conacorp1.equals("")){
                conacorp1 = "%";
            }
            if(conacorp == null || conacorp.equals("")){
                conacorp = "%";
            }
            if(conagita == null || conagita.equals("")){
                conagita = "%";
            }
            if(abonsadam1 == null || abonsadam1.equals("")){
                abonsadam1 = "%";
            }
            index10Dto.setAcorp1(conacorp1);
//            log.info("conacorp1 =====>" + conacorp1);
            index10Dto.setAcorp(conacorp);
            index10Dto.setAgita(conagita);
            index10Dto.setAbonsadam1(abonsadam1);
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

    public String GetMaxSeq(String agDate){

        String ls_seq = service10.SelectMaxSeq(index10Dto);
        if(ls_seq == null){
            ls_seq = agDate + "0001";
        }else{
            Integer ll_misnum = Integer.parseInt(ls_seq) + 1;
            ls_seq = ll_misnum.toString();
        }
        return ls_seq;
    }

}
