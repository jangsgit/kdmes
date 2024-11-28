package com.dae.kdmes.controller.cms01;

import com.dae.kdmes.DTO.App01.IndexCa613Dto;
import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.DTO.Appm.FPLAN_VO;
import com.dae.kdmes.DTO.Cms.CmsIndex01Dto;
import com.dae.kdmes.DTO.app03.Index59Dto;
import com.dae.kdmes.Service.App02.Index11Service;
import com.dae.kdmes.Service.App03.Index35Service;
import com.dae.kdmes.Service.Cms.CmsIndex01Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// @RestController : return을 텍스트로 반환함.
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/appcms", method = RequestMethod.POST)
public class CmsListController {
    private final CmsIndex01Service cmsservice01;
    CmsIndex01Dto cmsdto = new CmsIndex01Dto();

    protected Log log =  LogFactory.getLog(this.getClass());



    //생산계획현황
    @GetMapping(value="/reallist")
    public Object getRealCmsList(@RequestParam("frdate") String frdate,
                          @RequestParam("todate") String todate,
                          @RequestParam("machnm") String machnm,
                          @RequestParam("addinfo") String addinfo,
                               Model model, HttpServletRequest request) throws Exception{
        List<CmsIndex01Dto> cms01List = new ArrayList<>();
        try {

            Date nowData = new Date();
            SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
            String indate = endDate.format(nowData).toString();
            cmsdto.setFrdate(indate);
            cmsdto.setTodate(indate);
            cmsdto.setMachine_name("%");
            cmsdto.setAdditional_Info_1("%");
            cms01List = cmsservice01.getSHOTDATA_realtime(cmsdto);

            System.out.println("리스트 데이터:");
            for (CmsIndex01Dto item : cms01List) {
                System.out.println("- " + item.getAdditional_Info_1());
            }
            model.addAttribute("cmsDto", cms01List);

        } catch (Exception ex) {
            log.info("getRealCmsList Exception =====>" + ex.toString());
        }
        return cms01List;
    }



}
