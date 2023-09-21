package com.dae.kdmes.controller.app03;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App01.Index02Dto;
import com.dae.kdmes.DTO.App01.Index03Dto;
import com.dae.kdmes.DTO.App01.Index04Dto;
import com.dae.kdmes.DTO.App02.Index10Dto;
import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.DTO.CommonDto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.Service.App01.Index01Service;
import com.dae.kdmes.Service.App01.Index02Service;
import com.dae.kdmes.Service.App01.Index03Service;
import com.dae.kdmes.Service.App01.Index04Service;
import com.dae.kdmes.Service.App02.Index10Service;
import com.dae.kdmes.Service.App03.Index33Service;
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
@RequestMapping(value = "/app03n", method = RequestMethod.POST)
public class App03CrudController1 {
    private final Index10Service service10;

    private final Index01Service service01;

    private final Index02Service service02;

    private final Index03Service service03;

    private final Index04Service service04;

    private final Index33Service service33;
    CommonDto CommDto = new CommonDto();
    PopupDto popupDto = new PopupDto();

    Index01Dto index01Dto = new Index01Dto();

    Index02Dto index02Dto = new Index02Dto();

    Index03Dto index03Dto = new Index03Dto();

    Index04Dto index04Dto = new Index04Dto();

    Index10Dto index10Dto = new Index10Dto();

    Index11Dto index11Dto = new Index11Dto();

    List<PopupDto> popupListDto = new ArrayList<>();
    List<Index01Dto> index01ListDto = new ArrayList<>();

    List<Index02Dto> index02ListDto = new ArrayList<>();
    List<Index03Dto> index03List = new ArrayList<>();

    List<Index10Dto> index10ListDto = new ArrayList<>();

    List<Index11Dto> index11ListDto = new ArrayList<>();

    List<Index10Dto> index10List = new ArrayList<>();

    protected Log log =  LogFactory.getLog(this.getClass());



    //주간생산계획현황
    @GetMapping(value="/index33/list")
    public Object getList(@RequestParam("searchtxt") String searchtxt,
                               Model model, HttpServletRequest request) throws Exception{
        try {

            if(searchtxt == null || searchtxt.equals("")){
                searchtxt = "%";
            }
            //index11Dto.setCom_code(searchtxt);
            index02ListDto = service02.getWflagList(index02Dto);

            model.addAttribute("WflagList",index02ListDto);

        } catch (Exception ex) {
//                dispatchException = ex;
            log.info("insalist Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }

        return index02ListDto;
    }
}
