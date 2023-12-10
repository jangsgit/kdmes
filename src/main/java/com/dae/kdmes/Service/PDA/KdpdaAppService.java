package com.dae.kdmes.Service.PDA;

import com.dae.kdmes.DTO.Appm.FPLAN_VO;
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

    public List<KosepList01Dto> getIndex03PDAList(KosepList01Dto parm){
        return kdpdaMapper.getIndex03PDAList(parm);
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





}
