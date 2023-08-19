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
@RequestMapping(value = "/app03m", method = RequestMethod.POST)
public class App03CrudController {
    private final Index03Service service03;
    UserFormDto userformDto = new UserFormDto();
    protected Log log =  LogFactory.getLog(this.getClass());


    CommonDto CommDto = new CommonDto();
    Index03Dto index03Dto = new Index03Dto();
    List<Index03Dto> index03List = new ArrayList<>();
    @RequestMapping(value="/index03/save")
    public String index03Save(
            @RequestParam("frdate") String frdate
            ,@RequestParam( value =  "jcode[]") List<String> jcode
            ,@RequestParam( value =  "jqty[]") List<String> jqty
            , Model model
            , HttpServletRequest request){

        try {

            HttpSession session = request.getSession();
            UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
            model.addAttribute("userformDto",userformDto);

            String year = frdate.substring(0,4) ;
            String month = frdate.substring(5,7) ;
            String day   = frdate.substring(8,10) ;
            frdate = year + month + day ;

            Boolean result = false;
            if( jcode.size() > 0){
                for(int i = 0; i < jcode.size(); i++){
                    index03Dto.setAcorp("00");
                    index03Dto.setKey1(frdate);
                    index03Dto.setJepm(jcode.get(i));
                    index03Dto.setIjaego_su1(Integer.parseInt(jqty.get(i)));
                    index03Dto.setJepm_size("00");
//                    log.info("frdate Exception =====>" + frdate);
//                    log.info("jcode  Exception =====>" + jcode.get(i));
//                    log.info("jqty  Exception =====>" + jqty.get(i));
                    result = service03.InsertJegoIpgo(index03Dto);
                    if (!result){
                        return "error";
                    }
                }
            }

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "success";
    }


    @RequestMapping(value="/index03/del")
    public String index03Delete(  @RequestParam("ipdate") String ipdate,
                                  Model model,   HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);
        String year = ipdate.substring(0,4) ;
        String month = ipdate.substring(5,7) ;
        String day   = ipdate.substring(8,10) ;
        ipdate = year + month + day ;
        index03Dto.setKey1(ipdate);
        Boolean result = service03.DeleteJaegoIpgo(index03Dto);
        if (!result) {
            return "error";
        }
        return "success";
    }


    //재고실사 리스트
    @GetMapping(value="/index03/list")
    public Object App04List_index(@RequestParam("ipdate") String searchtxt,
                                  Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>재고등록");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            String year = searchtxt.substring(0,4) ;
            String month = searchtxt.substring(5,7) ;
            String day   = searchtxt.substring(8,10) ;
            searchtxt = year + month + day ;
            log.debug("searchtxt =====>" + searchtxt );
            index03Dto.setKey1(searchtxt);
            index03List = service03.SelectJegoIpgo(index03Dto);
            model.addAttribute("index03List",index03List);

        } catch (Exception ex) {
            log.info("App02List_index Exception =====>" + ex.toString());
        }

        return index03List;
    }

    //재고현황 리스트
    @GetMapping(value="/index03/jaegolist")
    public Object App04JaegoList_index(@RequestParam("searchtxt") String searchtxt,
                                       Model model, HttpServletRequest request) throws Exception{
        CommDto.setMenuTitle("거래처등록");
        CommDto.setMenuUrl("기준정보>재고현항");
        CommDto.setMenuCode("index03");
        HttpSession session = request.getSession();
        UserFormDto userformDto = (UserFormDto) session.getAttribute("userformDto");
        model.addAttribute("userformDto",userformDto);

        try {
            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            log.debug("searchtxt =====>" + searchtxt );
            index03Dto.setJkey(searchtxt);
            index03Dto.setFrdate("2000-01-01");
            index03Dto.setTodate(searchtxt);
            index03List = service03.SelectJegoList(index03Dto);
            model.addAttribute("index03List",index03List);

        } catch (Exception ex) {
            log.info("App04JaegoList_index Exception =====>" + ex.toString());
        }

        return index03List;
    }



}
