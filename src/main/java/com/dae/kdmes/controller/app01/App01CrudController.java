package com.dae.kdmes.controller.app01;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App01.Index02Dto;
import com.dae.kdmes.DTO.App01.Index03Dto;
import com.dae.kdmes.DTO.App01.Index04Dto;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// @RestController : return을 텍스트로 반환함.
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app01m", method = RequestMethod.POST)
public class App01CrudController {
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

    List<PopupDto> popupListDto = new ArrayList<>();
    List<Index01Dto> index01ListDto = new ArrayList<>();

    List<Index02Dto> index02ListDto = new ArrayList<>();
    List<Index03Dto> index03List = new ArrayList<>();

    List<Index04Dto> index04ListDto = new ArrayList<>();

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
    @GetMapping(value="/index03/list")
    public Object App03List_index(@RequestParam("searchtxt") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>제품정보");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index03Dto.setJpum(searchtxt);
            index03List = service03.GetJpumList(index03Dto);

            model.addAttribute("index03List",index03List);

        } catch (Exception ex) {
            log.info("App02List_index Exception =====>" + ex.toString());
        }

        return index03List;
    }
    @RequestMapping(value="/index03/save")
    public String index03Save(@RequestPart(value = "key") Map<String, Object> param
            , Model model
            , HttpServletRequest request){

        try {
            param.forEach((key, values) -> {
                switch (key) {
                    case "jkey":
                        index03Dto.setJkey(values.toString());
                        break;
                    case "j_dae":
                        index03Dto.setJ_dae(values.toString());
                        break;
                    case "j_jung":
                        index03Dto.setJ_jung(values.toString());
                        break;
                    case "jgugek":
                        index03Dto.setJgugek(values.toString());
                        break;
                    case "jpum":
                        index03Dto.setJpum(values.toString());
                        break;
                    case "jgugek2":
                        index03Dto.setJgugek2(values.toString());
                        break;
                    case "jpumcode":
                        index03Dto.setJpumcode(values.toString());
                        break;
                    case "jsayang":
                        index03Dto.setJsayang(values.toString());
                        break;
                    case "j_net":
                        index03Dto.setJ_net(values.toString());
                        break;
                    case "jchajong":
                        index03Dto.setJchajong(values.toString());
                        break;
                    case "j_sr":
                        index03Dto.setJ_sr(values.toString());
                        break;
                    case "j_ct":
                        index03Dto.setJ_ct(values.toString());
                        break;
                    case "j_cavity":
                        index03Dto.setJ_cavity(values.toString());
                        break;
                    case "jdanwy":
                        index03Dto.setJdanwy(values.toString());
                        break;
                    case "jbigo":
                        index03Dto.setJbigo(values.toString());
                        break;
                    case "w_b_gubn":
                        index03Dto.setW_b_gubn(values.toString());
                        break;
                    case "jchgoga0":
                        index03Dto.setJchgoga0(values.toString());
                        break;
                    case "j_misayong":
                        index03Dto.setJ_misayong(values.toString());
                        break;
                    case "j_sang":
                        index03Dto.setJ_sang(values.toString());
                        break;
                    default:
                        break;
                }
            });
            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);

            String ls_acode = service03.GetJpumCheck(index03Dto);
            Boolean result = false;
            log.info("ls_acode");
            log.info(index03Dto.getJbigo());
            if (ls_acode == null || ls_acode.equals("")) {
                result = service03.InsertJpum(index03Dto);
                log.info("result1");
                log.info(result);
                if (!result) {
                    return "error";
                }
            } else {
                result = service03.UpdateJpum(index03Dto);
                log.info("result2");
                log.info(result);
                if (!result) {
                    return "error";
                }
            }
//            model.addAttribute("userformDto",userformDto);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/index03/del")
    public String index03Delete(  @RequestParam("ascode") String ascode,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        index03Dto.setJkey(ascode);
        Boolean result = service03.DeleteJpum(index03Dto);
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

    @GetMapping(value="/getPgunList")
    public Object App01PgunList_index(@RequestParam("searchtxt") String searchtxt,
                                          Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index04Dto.setCom_cls(searchtxt);
            index04ListDto = service04.getPgunList(index04Dto);

            model.addAttribute("pgunList",index04ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index04ListDto;
    }

    @GetMapping(value="/getHcodList")
    public Object App01HcodList_index(@RequestParam("searchtxt") String searchtxt,
                                      @RequestParam("com_code") String com_code,
                                      Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index04Dto.setPgun(searchtxt);
            index04Dto.setCom_code(com_code);
            index04ListDto = service04.getHcodList(index04Dto);

            model.addAttribute("hcodList",index04ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index04ListDto;
    }

    @RequestMapping(value="/hcodsave")
    public String App01HcodSave_index(  @RequestParam("hcod") String hcod,
                                        @RequestParam("com_code") String com_code,
                                           @RequestParam("hnam") String hnam,
                                           Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index04Dto.setCom_code(com_code);
        index04Dto.setHcod(hcod);
        index04Dto.setHnam(hnam);
        String ls_hcod = service04.GetHcodCheck(index04Dto);
        if(ls_hcod == null || ls_hcod.equals("")){
            result = service04.InsertHcod(index04Dto);
        }else{
            result = service04.UpdateHcod(index04Dto);
        }
        if (!result) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/hcoddel")
    public String App01HcodDel_index(  @RequestParam("hcod") String hcod,
                                       @RequestParam("com_code") String com_code,
                                          @RequestParam("hnam") String hnam,
                                          Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index04Dto.setCom_code(com_code);
        index04Dto.setHcod(hcod);
        index04Dto.setHnam(hnam);
        index04ListDto = service04.getHcodList(index04Dto);
        result = service04.DeleteHcod(index04Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }

    @GetMapping(value="/getBgroupList")
    public Object App01BgroupList_index(@RequestParam("searchtxt") String searchtxt,
                                        @RequestParam("hcod") String hcod,
                                        @RequestParam("com_code") String com_code,
                                      Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            index04Dto.setPgun(searchtxt);
            index04Dto.setHcod(hcod);
            index04Dto.setCom_code(com_code);
            index04ListDto = service04.getBgroupList(index04Dto);

            model.addAttribute("bgroupList",index04ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index04ListDto;
    }

    @RequestMapping(value="/bgroupsave")
    public String App01BgroupSave_index(  @RequestParam("hcod") String hcod,
                                          @RequestParam("bgroup") String bgroup,
                                          @RequestParam("com_code") String com_code,
                                          @RequestParam("bgrpnm") String bgrpnm,
                                        Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index04Dto.setBgroup(bgroup);
        index04Dto.setHcod(hcod);
        index04Dto.setBgrpnm(bgrpnm);
        index04Dto.setCom_code(com_code);
        String ls_bgroup = service04.GetBgroupCheck(index04Dto);
        if(ls_bgroup == null || ls_bgroup.equals("")){
            result = service04.InsertBgroup(index04Dto);
        }else{
            result = service04.UpdateBgroup(index04Dto);
        }
        if (!result) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="/bgroupdel")
    public String App01BgroupDel_index(  @RequestParam("hcod") String hcod,
                                       @RequestParam("bgroup") String bgroup,
                                       @RequestParam("bgrpnm") String bgrpnm,
                                       @RequestParam("com_code") String com_code,
                                       Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        Boolean result = false;
        index04Dto.setBgroup(bgroup);
        index04Dto.setHcod(hcod);
        index04Dto.setBgrpnm(bgrpnm);
        index04Dto.setCom_code(com_code);
        index04ListDto = service04.getBgroupList(index04Dto);
        result = service04.DeleteBgroup(index04Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }
}
