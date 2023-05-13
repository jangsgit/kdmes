package com.dae.kdmes.controller.app01;

import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

// @RestController : return을 텍스트로 반환함.
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/app01", method = RequestMethod.POST)
public class App01Controller {
    CommonDto CommDto = new CommonDto();
    PopupDto popupDto = new PopupDto();
    List<PopupDto> popupListDto = new ArrayList<>();

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
//
//        itemDtoList = appcom01Service.GetXa012List();
//        itemDto.setSpjangcd(spcode);
//        if(appcom01Service.Getbxa012Detail(itemDto) == null){
//            model.addAttribute("itemDto", appcom01Service.GetTBXa012Blank());
//        }else{
//            model.addAttribute("itemDto", appcom01Service.Getbxa012Detail(itemDto));
//        }
//        log.debug("map check=====>" );
//        model.addAttribute("itemDtoList", itemDtoList);
//        model.addAttribute("itemDtoInput", itemDtoInput);
        model.addAttribute("CommDto", CommDto);
        return "App01/index01";
    }


    //공정기준코드등록
    @GetMapping(value="/index02")
    public String App02_index( Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("공정기준정보등록");
        CommDto.setMenuUrl("기준정보>공정기준정보등록");
        CommDto.setMenuCode("index02");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            model.addAttribute("cifcodeList",popupListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App03001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App01/index02";
    }

}
