package com.dae.kdmes.controller.pda;

import com.dae.kdmes.DTO.PDA.*;
import com.dae.kdmes.Service.PDA.KdpdaAppService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

// @RestController : return을 텍스트로 반환함.

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/kdmes", method = RequestMethod.POST)
public class Appkdpda01Controller {
    protected Log log =  LogFactory.getLog(this.getClass());
    KosepPopDto popDto = new KosepPopDto();
    KosepPopDto DeFpopDto = new KosepPopDto();
    KosepCa635Dto Ca635Dto = new KosepCa635Dto();
    List<KosepCa636Dto> Ca636ListDto = new ArrayList<>();
    KosepCa636Dto Ca636Dto = new KosepCa636Dto();
    KosepDa037Dto da037LotDto = new KosepDa037Dto();
    KosepDa037HDto da037HDto = new KosepDa037HDto();
    List<KosepPopDto> list01Dto = new ArrayList<>();
    List<KosepPopDto> popDtoList = new ArrayList<>();
    private final KdpdaAppService authService;



//  //출고예정조회
    @RequestMapping(value="/list01",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom01_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
            param.forEach((key, values) -> {
                switch (key){
                    case "dbnm":
                        popDto.setDbnm(values.toString());
                        break;
                    case "todate":
                        popDto.setTodate(values.toString());
                    default:
                        break;
                }
            });
            //list01Dto = authService.GetTBDA035List(popDto);
        return list01Dto;
    }


    //  //출고등록
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class, SQLException.class})
    @RequestMapping(value="/list02",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom02_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        String ls_itemcd = "";
        String ls_itemcd_g = "";
        param.forEach((key, values) -> {
            switch (key){
                case "fdeldate":
                    popDto.setFdeldate(values.toString());
                    break;
                case "fdelnum":
                    popDto.setFdelnum(values.toString());
                    break;
                case "itemcd":
                    popDto.setItemcd(values.toString());
                    break;
                case "itemcd_g":
                    popDto.setItemcd_G(values.toString());
                    break;
                case "userid":
                    popDto.setUserid(values.toString());
                    break;
                default:
                    break;
            }
        });

        ls_itemcd = popDto.getItemcd();
        String[] listBarcode = ls_itemcd.split("[|]");
        HashMap hm01 = new HashMap();
        hm01.put("itemcdArr", listBarcode) ;
        //---------------------- DA006 UPDATE Process---------------------------------//

        ls_itemcd_g = popDto.getItemcd_G();
        String[] listBarcode_G = ls_itemcd_g.split("[|]");
        HashMap hm02 = new HashMap();
        hm02.put("itemcdArr_G", listBarcode_G) ;
        //----------------------------------------------------------------------------//
        int queryResult = 0;
        int ll_num = 0;
        //출고순번
        popDto.setFdelnum(GetDa036MaxNum());
        popDto.setIndate(getToDate());
        popDto.setPerid(popDto.getUserid());

        for ( var item : listBarcode_G) {
            item = item.replaceAll("\n", "");
            popDto.setLotno(item);
            popDto.setFdelseq(GetMaxSeq(ll_num));

            if(ll_num == 0){
                queryResult = authService.InsertDA036(popDto);
                if(queryResult < 1){
                    queryResult = 0;
//                return "UpdateDA006PANNEL ERROR";
                }
            }

            queryResult = authService.InsertDa037(popDto);
            if(queryResult < 1){
                queryResult = 0;
//                return "UpdateDA006WINGBADY ERROR";
            }

            queryResult = authService.UpdateW020(popDto);
            if(queryResult < 1){
                queryResult = 0;
//                return "UpdateDA006PANNEL ERROR";
            }
            ll_num++;
        }

        ll_num = 0;
        for ( var item : listBarcode) {
            item = item.replaceAll("\n", "");
            popDto.setFdeldate(popDto.getFdeldate());
            popDto.setGlasslotno(item);
            popDto.setFdelseq(GetMaxSeq(ll_num));
            queryResult = authService.UpdateDA037(popDto);
            if(queryResult < 1){
                queryResult = 0;
//                return "UpdateDA006PANNEL ERROR";
            }
            ll_num++;
        }


        //log.info("popDto =====> " + popDto);

        return "SUCCESS";

    }

    //  //출고현황 LOT
    @RequestMapping(value="/list03",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom03_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "todate":
                    popDto.setTodate(values.toString());
                default:
                    break;
            }
        });
        list01Dto = authService.GetTBDA037List(popDto);
        return list01Dto;
    }


    //  //출고현황
    @RequestMapping(value="/list03del",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom03DEL_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "barcode":
                    popDto.setLotno(values.toString());
                    break;
                case "deldate":
                    popDto.setDeldate(values.toString());
                    break;
                case "delnum":
                    popDto.setDelnum(values.toString());
                    break;
                case "delseq":
                    popDto.setDelseq(values.toString());
                    break;
                default:
                    break;
            }
        });
        int queryResult = 0;
        queryResult = authService.DeleteDA037(popDto);
        if(queryResult < 1){
//            queryResult = 0;
//            return "DeleteDA006PAN ERROR";
        }

        queryResult = authService.DeleteDA036(popDto);
        if(queryResult < 1){
//            queryResult = 0;
//            return "DeleteDA006WIN ERROR";
        }

        popDto.setDeldate("");
        popDto.setDelnum("");
        popDto.setDelseq("");
        queryResult = authService.UpdateW020(popDto);
        if(queryResult < 1){
            queryResult = 0;
//                return "UpdateDA006PANNEL ERROR";
        }
        return "SUCCESS";

    }



    public String GetDa036MaxNum(){

        String ls_delnum = "";
        ls_delnum = authService.getDa036MaxSeq(popDto);
        if(ls_delnum == null){
            ls_delnum = "0001";
        }else{
            Integer ll_misnum = Integer.parseInt(ls_delnum) + 1;
            ls_delnum = ll_misnum.toString();
            if (ls_delnum.length() == 1){
                ls_delnum = "000" + ls_delnum;
            }else if(ls_delnum.length() == 2){
                ls_delnum = "00" + ls_delnum;
            }else if(ls_delnum.length() == 3){
                ls_delnum = "0" + ls_delnum;
            }
        }

        return ls_delnum;
    }

    public String GetMaxSeq(int ap_Seq){
        String ls_seq = "";
        Integer ll_misnum = ap_Seq + 1;
        ls_seq = ll_misnum.toString();
        if (ls_seq.length() == 1){
            ls_seq = "00" + ls_seq;
        }else if(ls_seq.length() == 2){
            ls_seq = "0" + ls_seq;
        }
        return ls_seq;
    }


    //  //출고현황
    @RequestMapping(value="/list04del",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom04DEL_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    Ca636Dto.setDbnm(values.toString());
                    break;
                case "movdate":
                    Ca636Dto.setMovdate(values.toString());
                    break;
                case "movnum":
                    Ca636Dto.setMovnum(values.toString());
                    break;
                case "movseq":
                    Ca636Dto.setMovseq(values.toString());
                    break;
                default:
                    break;
            }
        });
        int queryResult = 0;
        queryResult = authService.DeleteCA636(Ca636Dto);
        if(queryResult < 1){
//            queryResult = 0;
//            return "DeleteDA006PAN ERROR";
        }

        queryResult = authService.DeleteCA635(Ca636Dto);
        if(queryResult < 1){
//            queryResult = 0;
//            return "DeleteDA006WIN ERROR";
        }
        return "SUCCESS";
    }





    private String getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }



}
