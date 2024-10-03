package com.dae.kdmes.controller.cms01;

import com.dae.kdmes.DTO.App01.*;
import com.dae.kdmes.DTO.Cms.CmsIndex01Dto;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.App01.Index01Service;
import com.dae.kdmes.Service.App01.Index02Service;
import com.dae.kdmes.Service.App01.Index03Service;
import com.dae.kdmes.Service.App01.Index04Service;
import com.dae.kdmes.Service.App03.Index35Service;
import com.dae.kdmes.Service.Cms.CmsIndex01Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

// @RestController : return을 텍스트로 반환함.
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/cms01", method = RequestMethod.POST)
public class Cms01Controller {
    private final CmsIndex01Service cmsservice01;

    protected Log log =  LogFactory.getLog(this.getClass());
    //공통코드등록
    @GetMapping(value="/getwotqty")
    public String Cms01_Getwotqty_index(@RequestParam("jgumtype") String jgumtype
            ,@RequestParam("wrmc") String wrmc
            ,@RequestParam("lotno") String lotno
            ,@RequestParam("wflag") String wflag
            ,Model model, HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        CmsIndex01Dto cmsdto = new CmsIndex01Dto();
        try {

//            index01ListDto = cmsservice01.getSHOTDATA_wotqty(cmsIndex01Dto);
//
//            log.info("Exception =====>" + index01ListDto);
//            model.addAttribute("com_rem2_keyList",index01ListDto1);
//            model.addAttribute("comcodeList",index01ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App01_index Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "Cms/index01";
    }


}
