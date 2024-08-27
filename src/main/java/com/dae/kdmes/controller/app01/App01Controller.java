package com.dae.kdmes.controller.app01;

import com.dae.kdmes.DTO.App01.*;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.App03.Index35Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dae.kdmes.Service.App01.Index01Service;
import com.dae.kdmes.Service.App01.Index02Service;
import com.dae.kdmes.Service.App01.Index03Service;
import com.dae.kdmes.Service.App01.Index04Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

// @RestController : return을 텍스트로 반환함.
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/app01", method = RequestMethod.POST)
public class App01Controller {
    private final Index01Service service01;

    private final Index02Service service02;

    private final Index03Service service03;

    private final Index04Service service04;
    private final Index35Service service35;
    CommonDto CommDto = new CommonDto();
    PopupDto popupDto = new PopupDto();

    Index01Dto index01Dto = new Index01Dto();

    Index02Dto index02Dto = new Index02Dto();

    Index03Dto index03Dto = new Index03Dto();

    Index04Dto index04Dto = new Index04Dto();

    List<PopupDto> popupListDto = new ArrayList<>();

    List<PopupDto> popupListDto1 = new ArrayList<>();
    List<Index01Dto> index01ListDto = new ArrayList<>();

    List<Index01Dto> index01ListDto1 = new ArrayList<>();

    List<Index02Dto> index02ListDto = new ArrayList<>();
    List<Index03Dto> index03List = new ArrayList<>();

    List<Index04Dto> index04ListDto = new ArrayList<>();
    List<Index02Dto> index07List = new ArrayList<>();

    Index02Dto index07Dto = new Index02Dto();

    protected Log log =  LogFactory.getLog(this.getClass());
    //공통코드등록
    @GetMapping(value="/index01")
    public String App01_index(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공통코드등록");
        CommDto.setMenuUrl("기준정보>공통코드등록");
        CommDto.setMenuCode("index01");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index01ListDto = service01.getComCodeList(index01Dto);
            index01ListDto1 = service01.getCom_rem2_keyList(index01Dto);

            model.addAttribute("com_rem2_keyList",index01ListDto1);
            model.addAttribute("comcodeList",index01ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App01_index Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index01";
    }

    @GetMapping(value="/index02")
    public String App01_index02(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공정기준등록");
        CommDto.setMenuUrl("기준정보>공정기준등록");
        CommDto.setMenuCode("index02");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index02ListDto = service02.getWflagList(index02Dto);
         //   index02ListDto = service02.getWrcmList(index02Dto);

            model.addAttribute("WflagList",index02ListDto);
        //    model.addAttribute("WrcmList",index02ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App02_index Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index02";
    }


    //재고실사등록
    @GetMapping(value="/index03")
    public String App03_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("재고등록");
        CommDto.setMenuUrl("기준정보>재고등록");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("index03 Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index03";
    }

    //제품등록
    @GetMapping(value="/index05")
    public String App05_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("제품등록");
        CommDto.setMenuUrl("기준정보>제품정보");
        CommDto.setMenuCode("index05");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index03Dto.setJpum("%");
            index03List = service03.GetJpumList(index03Dto);
            popupListDto = service03.getj1_keyList(popupDto);
            popupListDto1 = service03.getj2_keyList(popupDto);

            model.addAttribute("j1_keyList",popupListDto);
            model.addAttribute("j2_keyList",popupListDto1);
            model.addAttribute("index03List",index03List);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App03001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index05";
    }



    @GetMapping(value="/index04")
    public String App01_index04(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("품목그룹등록");
        CommDto.setMenuUrl("기준정보>품목그룹등록");
        CommDto.setMenuCode("index04");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index04ListDto = service04.getPgunList(index04Dto);

            model.addAttribute("PgunList",index04ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App04_index Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index04";
    }



    @GetMapping(value="/index06")
    public String App01_index06(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("소요량등록");
        CommDto.setMenuUrl("기준정보>소요량등록");
        CommDto.setMenuCode("index06");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        List<IndexCa613Dto> _indexCa613ListDto = new ArrayList<>();
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();

        try {
            _indexCa613Dto.setOpcod("%");
            _indexCa613ListDto = service35.SelectBomListTot(_indexCa613Dto);
            //   index02ListDto = service02.getWrcmList(index02Dto);

            model.addAttribute("WflagList",_indexCa613ListDto);
            //    model.addAttribute("WrcmList",index02ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App06_index Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index06";
    }


    @GetMapping(value="/index07")
    public String App01_index07(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>거래처정보");
        CommDto.setMenuCode("index02");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
//        userformDto.setPagetree01("거래처등록");
//        userformDto.setPagenm("본사기준정보");
//        model.addAttribute("CommDto", CommDto);

        try {
            index07Dto.setAcorp("%");
            index07List = service02.GetCifList(index07Dto);
            popupListDto = service02.getCifCodeList(popupDto);

            model.addAttribute("index07List",index07List);
            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App03001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index07";
    }


    @GetMapping(value="/indexds01")
    public String App01_indexds01(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공정기준등록");
        CommDto.setMenuUrl("기준정보>공정기준등록");
        CommDto.setMenuCode("index02");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);


        return "App01/indexds01";
    }

    @GetMapping(value="/indexds02")
    public String App01_indexds02(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공정기준등록");
        CommDto.setMenuUrl("기준정보>공정기준등록");
        CommDto.setMenuCode("index02");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);


        return "App01/indexds02";
    }

    @GetMapping(value="/indexds03")
    public String App01_indexds03(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공정기준등록");
        CommDto.setMenuUrl("기준정보>공정기준등록");
        CommDto.setMenuCode("index02");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);


        return "App01/indexds03";
    }

}
