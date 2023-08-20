package com.dae.kdmes.Service.App01;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App01.Index02Dto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.Mapper.App01.Index02Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index02Service")
public class Index02Service {

    @Autowired
    Index02Mapper indexMapper;

    public List<Index02Dto> getWflagList(Index02Dto parm){
        return indexMapper.getWflagList(parm);
    }
    public List<Index02Dto> getWrcmList(Index02Dto parm){
        return indexMapper.getWrcmList(parm);
    }

    public List<Index02Dto> GetFplanDetailList(Index02Dto parm){
        return  indexMapper.GetFplanDetailList(parm);
    }

    public Boolean InsertFplanDetail(Index02Dto parm){ return  indexMapper.InsertFplanDetail(parm);}
    public Boolean UpdateFplanDetail(Index02Dto parm){  return  indexMapper.UpdateFplanDetail(parm);}
    public Boolean DeleteFplanDetail(Index02Dto parm){  return  indexMapper.DeleteFplanDetail(parm);}

    public String GetFplanCheck(Index02Dto parm){  return  indexMapper.GetFplanCheck(parm);  }


    public List<Index02Dto> GetCifList(Index02Dto parm){
        return  indexMapper.GetCifList(parm);
    }
    public List<Index02Dto> GetCifListTot(Index02Dto parm){
        return  indexMapper.GetCifListTot(parm);
    }
    public Index02Dto GetCifListAcode(Index02Dto parm){
        return  indexMapper.GetCifListAcode(parm);
    }
    public Index02Dto GetCifBonsa(Index02Dto parm){
        return  indexMapper.GetCifBonsa(parm);
    }

    public String getIndex02MaxSeq(String parm){
        return  indexMapper.getIndex02MaxSeq(parm);
    }
    public Boolean InsertCif(Index02Dto parm){ return  indexMapper.InsertCif(parm);}
    public Boolean UpdateCif(Index02Dto parm){  return  indexMapper.UpdateCif(parm);  }
    public Boolean DeleteCif(Index02Dto parm){  return  indexMapper.DeleteCif(parm);  }
    public List<PopupDto> getCifCodeList(PopupDto parm){
        return  indexMapper.getCifCodeList(parm);
    }

}
