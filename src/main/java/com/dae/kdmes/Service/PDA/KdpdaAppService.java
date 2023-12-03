package com.dae.kdmes.Service.PDA;

import com.dae.kdmes.DTO.PDA.*;
import com.dae.kdmes.Mapper.PDA.KdpdaDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service("KosepAppService")
public class KdpdaAppService {
    @Autowired
    KdpdaDBMapper kdpdaMapper;


    public String getDa036MaxSeq(KosepPopDto parm){
        return kdpdaMapper.getDa036MaxSeq(parm);
    }


    public int InsertDA036(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.InsertDA036(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }

    public int InsertDa037(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.InsertDa037(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }

    public int UpdateDA037(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.UpdateDA037(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }


    public int UpdateW020(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.UpdateW020(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }



    public List<KosepPopDto> GetTBDA037List(KosepPopDto parm){
        return kdpdaMapper.GetTBDA037List(parm);
    }



    public  KosepPopDto GetPcodeDataList02(KosepPopDto parm){
        return kdpdaMapper.GetPcodeDataList02(parm);

    }

    public  KosepPopDto GetPcodeDataList03(KosepPopDto parm){
        return kdpdaMapper.GetPcodeDataList03(parm);
    }


    public List<KosepPopDto> GetTBCA510IpStoreList(KosepPopDto parm){
        return kdpdaMapper.GetTBCA510IpStoreList(parm);
    }


    public KosepDa037Dto GetLotNoData(HashMap<String,String> parm){
        return kdpdaMapper.GetLotNoData(parm);
    }
    public KosepDa037Dto GetLotNoList(KosepPopDto parm){
        return kdpdaMapper.GetLotNoList(parm);
    }


    public String getCa635MaxSeq(KosepCa635Dto parm){
        return kdpdaMapper.getCa635MaxSeq(parm);
    }


    public int UpdateTBda035(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.UpdateTBda035(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }
    public int UpdateDA006PANNEL(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.UpdateDA006PANNEL(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }
    public int UpdateDA006WINGBADY(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.UpdateDA006WINGBADY(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }


    public int DeleteDA036(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.DeleteDA036(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }
    public int DeleteDA037(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.DeleteDA037(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }
    public int DeleteDA037H(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.DeleteDA037H(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }


    public int DeleteCA636(KosepCa636Dto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.DeleteCA636(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }

    public int DeleteCA635(KosepCa636Dto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.DeleteCA635(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }
 /*   public int DeleteDA035(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = TheMoonDBMapper.DeleteDA035(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }*/
    public int DeleteDA006PAN(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.DeleteDA006PAN(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }
    public int DeleteDA006WIN(KosepPopDto parm) {
        int queryResult = 1;
        queryResult = kdpdaMapper.DeleteDA006WIN(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }








}
