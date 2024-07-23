package com.dae.kdmes.controller.app03;

import com.dae.kdmes.DTO.App01.IndexCa613Dto;
import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.DTO.Appm.FPLAN_VO;
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
    FPLAN_VO fplanDto = new FPLAN_VO();
    List<Index11Dto> index11DtoList = new ArrayList<>();
    List<FPLAN_VO> fplanDtoList = new ArrayList<>();

    protected Log log =  LogFactory.getLog(this.getClass());



    //생산계획현황
    @GetMapping(value="/index11/list")
    public Object getList(@RequestParam("frdate") String frdate,
                          @RequestParam("todate") String todate,
                          @RequestParam("pcode") String pcode,
                          @RequestParam("acode") String acode,
                          @RequestParam("perid") String perid,
                               Model model, HttpServletRequest request) throws Exception{
        try {

//            String ls_yeare = frdate.substring(0,4);
//            String ls_mm = frdate.substring(5,7);
//            String ls_dd = frdate.substring(8,10);
//            frdate =  ls_yeare + ls_mm + ls_dd;
//                 ls_yeare = todate.substring(0,4);
//                 ls_mm = todate.substring(5,7);
//                 ls_dd = todate.substring(8,10);
//            todate =  ls_yeare + ls_mm + ls_dd;

            index11Dto.setFrdate(frdate);
            index11Dto.setTodate(todate);
            index11Dto.setCltcd(acode);
            index11Dto.setPcode(pcode);
            index11Dto.setWrps(perid);
            index11DtoList = service11.getIndex11List(index11Dto);

            model.addAttribute("itemList",index11DtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("index11/list Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index11DtoList;
    }



    //사출공정현황
    @GetMapping(value="/index12/list01")
    public Object App12List01_index(@RequestParam("frdate") String frdate,
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

            index11DtoList = service11.getIndex12List01(index11Dto);

            model.addAttribute("itemList",index11DtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("index11/list Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index11DtoList;
    }


    //검사공정현황
    @GetMapping(value="/index12/list02")
    public Object App12List02_index(@RequestParam("frdate") String frdate,
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

            fplanDto.setFrdate(frdate);
            fplanDto.setTodate(todate);
            fplanDto.setLotno(lotno);

            fplanDtoList = service11.getIndex12List02(fplanDto);

            model.addAttribute("itemList",fplanDtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("index11/list Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return fplanDtoList;
    }


    //  사출현황
    @GetMapping(value="/index13/list01")
    public Object App13List01_index(@RequestParam("frdate") String frdate,
                                    @RequestParam("todate") String todate,
                                    @RequestParam("inwrps") String inwrps,
                                    @RequestParam("lotno") String lotno,
                                    Model model, HttpServletRequest request) throws Exception{
        List<Index11Dto> _index11DtoList = new ArrayList<>();
        try {

            Index11Dto _index11Dto = new Index11Dto();

            _index11Dto.setFrdate(frdate);
            _index11Dto.setTodate(todate);
            _index11Dto.setWrps(inwrps);
            _index11Dto.setLotno(lotno);

//            log.info("frdate =====>" + frdate);
//            log.info("todate =====>" + todate);
//            log.info("inwrps =====>" + inwrps);
//            log.info("lotno =====>" + lotno);
            _index11DtoList = service11.getIndex13List01(_index11Dto);

            model.addAttribute("itemList", _index11DtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App13List01 Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _index11DtoList;
    }


    //  사출가동현황
    @GetMapping(value="/index13/list03")
    public Object App13List03_index(@RequestParam("frdate") String frdate,
                                    @RequestParam("todate") String todate,
                                    @RequestParam("lotno") String lotno,
                                    Model model, HttpServletRequest request) throws Exception{
        List<Index11Dto> _index11DtoList = new ArrayList<>();
        try {

            Index11Dto _index11Dto = new Index11Dto();

            _index11Dto.setFrdate(frdate);
            _index11Dto.setTodate(todate);
            _index11Dto.setLotno(lotno);
            _index11DtoList = service11.getIndex13List03(_index11Dto);

            model.addAttribute("itemList", _index11DtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App13List01 Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _index11DtoList;
    }

    //사원별 사출현황
    @GetMapping(value="/index13/list02")
    public Object App13List02_index(@RequestParam("frdate") String frdate,
                                    @RequestParam("todate") String todate,
                                    @RequestParam("inwrps") String inwrps,
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

            fplanDto.setFrdate(frdate);
            fplanDto.setTodate(todate);
            fplanDto.setWrps(inwrps);

            fplanDtoList = service11.getIndex13List02(fplanDto);

            model.addAttribute("itemList",fplanDtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App13List01 Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return fplanDtoList;
    }


    //일별 불량별 사출현황
    @GetMapping(value="/index14/list01")
    public Object App14List01_index(@RequestParam("frdate") String frdate,
                                    @RequestParam("todate") String todate,
                                    @RequestParam("wcode") String wcode,
                                    @RequestParam("lotno") String lotno,
                                    Model model, HttpServletRequest request) throws Exception{
        try {

//            String ls_yeare = frdate.substring(0,4);
//            String ls_mm = frdate.substring(5,7);
//            String ls_dd = frdate.substring(8,10);
//            frdate =  ls_yeare + ls_mm + ls_dd;
//            ls_yeare = todate.substring(0,4);
//            ls_mm = todate.substring(5,7);
//            ls_dd = todate.substring(8,10);
//            todate =  ls_yeare + ls_mm + ls_dd;

            index11Dto.setFrdate(frdate);
            index11Dto.setTodate(todate);
            index11Dto.setWcode(wcode);
            index11Dto.setLotno(lotno);

            index11DtoList = service11.getIndex14List01(index11Dto);

            model.addAttribute("itemList",index11DtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App14List01_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index11DtoList;
    }


    //월별 불량별 사출현황
    @GetMapping(value="/index14/list02")
    public Object App14List02_index(@RequestParam("frdate") String frdate,
                                    @RequestParam("todate") String todate,
                                    @RequestParam("wcode") String wcode,
                                    @RequestParam("lotno") String lotno,
                                    Model model, HttpServletRequest request) throws Exception{
        try {

//            String ls_yeare = frdate.substring(0,4);
//            String ls_mm = frdate.substring(5,7);
//            String ls_dd = frdate.substring(8,10);
//            frdate =  ls_yeare + ls_mm + ls_dd;
//            ls_yeare = todate.substring(0,4);
//            ls_mm = todate.substring(5,7);
//            ls_dd = todate.substring(8,10);
//            todate =  ls_yeare + ls_mm + ls_dd;
            index11Dto.setFrdate(frdate);
            index11Dto.setTodate(todate);
            index11Dto.setWcode(wcode);
            index11Dto.setLotno(lotno);

            index11DtoList = service11.getIndex14List02(index11Dto);

            model.addAttribute("itemList",index11DtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App14List02_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index11DtoList;
    }


    //유형별 불량별 사출현황
    @GetMapping(value="/index14/list03")
    public Object App14List03_index(@RequestParam("frdate") String frdate,
                                    @RequestParam("todate") String todate,
                                    @RequestParam("wcode") String wcode,
                                    @RequestParam("lotno") String lotno,
                                    Model model, HttpServletRequest request) throws Exception{
        try {

//            String ls_yeare = frdate.substring(0,4);
//            String ls_mm = frdate.substring(5,7);
//            String ls_dd = frdate.substring(8,10);
//            frdate =  ls_yeare + ls_mm + ls_dd;
//            ls_yeare = todate.substring(0,4);
//            ls_mm = todate.substring(5,7);
//            ls_dd = todate.substring(8,10);
//            todate =  ls_yeare + ls_mm + ls_dd;
            index11Dto.setFrdate(frdate);
            index11Dto.setTodate(todate);
            index11Dto.setWcode(wcode);
            index11Dto.setLotno(lotno);

            index11DtoList = service11.getIndex14List03(index11Dto);

            model.addAttribute("itemList",index11DtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App14List03_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index11DtoList;
    }


    //출하현황
    @GetMapping(value="/index16/list01")
    public Object App16List01_index(@RequestParam("frdate") String frdate,
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

            index11DtoList = service11.getIndex16List01(index11Dto);

            model.addAttribute("itemList",index11DtoList);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App13List01 Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index11DtoList;
    }


    //생산계획현황
    @GetMapping(value="/index21/janlist")
    public Object App21List01_index(@RequestParam("frdate") String frdate,
                          @RequestParam("todate") String todate,
                          @RequestParam("pcode") String pcode,
                          Model model, HttpServletRequest request) throws Exception{
        IndexCa613Dto _indexCa613Dto = new IndexCa613Dto();
        List<IndexCa613Dto> _indexCa613ListDto = new ArrayList<>();
        try {

//            String ls_yeare = frdate.substring(0,4);
//            String ls_mm = frdate.substring(5,7);
//            String ls_dd = frdate.substring(8,10);
//            frdate =  ls_yeare + ls_mm + ls_dd;
//                 ls_yeare = todate.substring(0,4);
//                 ls_mm = todate.substring(5,7);
//                 ls_dd = todate.substring(8,10);
//            todate =  ls_yeare + ls_mm + ls_dd;

            _indexCa613Dto.setFrdate(frdate);
            _indexCa613Dto.setTodate(todate);
            _indexCa613Dto.setPcode(pcode);

            _indexCa613ListDto = service11.GetIndex21JanList(_indexCa613Dto);

            model.addAttribute("itemList",_indexCa613ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("App21List01_index Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return _indexCa613ListDto;
    }

}
