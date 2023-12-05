package com.dae.kdmes.controller.app03;

import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.Service.App02.Index11Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

// @RestController : return을 텍스트로 반환함.
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app03m", method = RequestMethod.POST)
public class App03ListController {


    private final Index11Service service11;
    Index11Dto index11Dto = new Index11Dto();
    List<Index11Dto> index11DtoList = new ArrayList<>();

    protected Log log =  LogFactory.getLog(this.getClass());



    //생산계획현황
    @GetMapping(value="/index11/list")
    public Object getList(@RequestParam("frdate") String frdate,
                          @RequestParam("todate") String todate,
                          @RequestParam("lotno") String lotno,
                               Model model, HttpServletRequest request) throws Exception{
        try {

            String ls_yeare = frdate.substring(0,4);
            String ls_mm = frdate.substring(5,7);
            String ls_dd = frdate.substring(8,10);
            frdate =  ls_yeare + ls_mm + ls_dd;
                 ls_yeare = todate.substring(0,4);
                 ls_mm = todate.substring(5,7);
                 ls_dd = todate.substring(8,10);
            todate =  ls_yeare + ls_mm + ls_dd;

            index11Dto.setFrdate(frdate);
            index11Dto.setTodate(todate);
            index11Dto.setLotno(lotno);

            index11DtoList = service11.getIndex11List(index11Dto);

            model.addAttribute("itemList",index11DtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("index11/list Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index11DtoList;
    }


}
