package com.dae.kdmes.controller.app01;

import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App01.Index02Dto;
import com.dae.kdmes.DTO.App01.Index04Dto;
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

    private final Index04Service service04;
    CommonDto CommDto = new CommonDto();
    PopupDto popupDto = new PopupDto();

    Index01Dto index01Dto = new Index01Dto();

    Index02Dto index02Dto = new Index02Dto();

    Index04Dto index04Dto = new Index04Dto();

    List<PopupDto> popupListDto = new ArrayList<>();
    List<Index01Dto> index01ListDto = new ArrayList<>();

    List<Index02Dto> index02ListDto = new ArrayList<>();

    List<Index04Dto> index04ListDto = new ArrayList<>();

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

            model.addAttribute("WflagList",index02ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App02_index Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index02";
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

}
