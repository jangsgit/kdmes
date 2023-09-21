package com.dae.kdmes.controller.app03;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.app03.Index34Dto;
import com.dae.kdmes.DTO.app03.Index35Dto;
import com.dae.kdmes.DTO.app03.Index33Dto;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Service.App01.Index01Service;
import com.dae.kdmes.Service.App01.Index03Service;
import com.dae.kdmes.Service.App03.Index35Service;
import com.dae.kdmes.Service.App03.Index34Service;
import com.dae.kdmes.Service.App03.Index33Service;
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
@RequestMapping(value = "/app03", method = RequestMethod.POST)
public class App03Controller {
    private final Index01Service service01;
    private final Index33Service service33;

    private final Index34Service service34;

    private final Index35Service service35;
    CommonDto CommDto = new CommonDto();
    PopupDto popupDto = new PopupDto();

    Index01Dto index01Dto = new Index01Dto();
    Index33Dto index33Dto = new Index33Dto();

    Index34Dto index34Dto = new Index34Dto();

    Index35Dto index35Dto = new Index35Dto();
    List<Index01Dto> index01ListDto = new ArrayList<>();

    List<Index33Dto> index33ListDto = new ArrayList<>();

    List<Index34Dto> index34ListDto = new ArrayList<>();

    List<Index35Dto> index35ListDto = new ArrayList<>();

    protected Log log =  LogFactory.getLog(this.getClass());

    @GetMapping(value="/index33")
    public String App03_index33(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("사원별검사일보");
        CommDto.setMenuUrl("생산현황>사원별검사일보");
        CommDto.setMenuCode("index33");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index33ListDto = service33.getList(index33Dto);

            model.addAttribute("getList",index33ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App03_index Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App03/index33";
    }

    @GetMapping(value="/index34")
    public String App03_index34(Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("일별/유형별불량현황");
        CommDto.setMenuUrl("생산현황>불량현황");
        CommDto.setMenuCode("index34");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            index34ListDto = service34.getList(index34Dto);
           // index01ListDto = service01.getCom_rem2_keyList(index01Dto);

            model.addAttribute("com_rem2_keyList",index01ListDto);

            model.addAttribute("getList",index34ListDto);
        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App03_index Exception =============================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return "App03/index34";
    }

//    @GetMapping(value="/index35")
//    public String App03_index35(Model model, HttpServletRequest request) throws Exception{
//        CommDto.setMenuTitle("작업일지(사출공정일지)");
//        CommDto.setMenuUrl("생산현황>작업일지");
//        CommDto.setMenuCode("index35");
//        HttpSession session = request.getSession();
//        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
//        model.addAttribute("userformDto",userformDto);
//
//        try {
//            index35ListDto = service35.getList(index35Dto);
//
//            model.addAttribute("getList",index35ListDto);
//        } catch (Exception ex) {
////                dispatchException = ex;
//            log.info("App03_index Exception =============================");
//            log.info("Exception =====>" + ex.toString());
////            log.debug("Exception =====>" + ex.toString() );
//        }
//
//        return "App03/index35";
//    }

}
