package com.dae.kdmes.Service.App02;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App01.Index03Dto;
import com.dae.kdmes.DTO.App01.IndexCa613Dto;
import com.dae.kdmes.DTO.App02.Index10Dto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.Mapper.App02.Index10Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index10Service")
public class Index10Service {

    @Autowired
    Index10Mapper indexMapper;


    public List<Index10Dto> GetFplanList(Index10Dto parm){
        return indexMapper.GetFplanList(parm);
    }

    public Boolean DeleteFplan(Index10Dto parm){  return  indexMapper.DeleteFplan(parm);  }

    public Boolean InsertFplan(Index10Dto parm){ return  indexMapper.InsertFplan(parm);}
    public Boolean UpdateFplan(Index10Dto parm){  return  indexMapper.UpdateFplan(parm);  }


    public List<IndexCa613Dto> SelectCa613List(IndexCa613Dto parm){
        return indexMapper.SelectCa613List(parm);
    }
    public List<IndexCa613Dto> SelectCa613JaegoList(IndexCa613Dto parm){return indexMapper.SelectCa613JaegoList(parm);}

    public Boolean DeleteCa613(IndexCa613Dto parm){  return  indexMapper.DeleteCa613(parm);  }
    public Boolean InsertCa613(IndexCa613Dto parm){ return  indexMapper.InsertCa613(parm);}
    public Boolean UpdateCa613(IndexCa613Dto parm){  return  indexMapper.UpdateCa613(parm);  }
    public String SelectMaxIbgnum(String parm){
        return  indexMapper.SelectMaxIbgnum(parm);
    }

    public String GetFplanCheck(Index10Dto parm){  return  indexMapper.GetFplanCheck(parm);  }
    public List<Index10Dto> GetJpumListTot(Index10Dto parm){
        return  indexMapper.GetJpumListTot(parm);
    }
    public List<Index10Dto> GetCifListTot(Index10Dto parm){
        return  indexMapper.GetCifListTot(parm);
    }

    /** 사원 리스트 */
    public List<Index10Dto> GetInsaList(Index10Dto parm){
        return  indexMapper.GetInsaList(parm);
    }

    public List<Index01Dto> GetIstorelist(Index01Dto parm){
        return  indexMapper.GetIstorelist(parm);
    }

    public List<Index01Dto> Getostorelist(Index01Dto parm){
        return  indexMapper.GetOstorelist(parm);
    }

    public String SelectCheckIndate(Index10Dto parm){
        return  indexMapper.SelectCheckIndate(parm);
    }

    public String SelectMaxSeq(Index10Dto parm){
        return  indexMapper.SelectMaxSeq(parm);
    }

    public String SelectMaxLot(Index10Dto parm){
        return  indexMapper.SelectMaxLot(parm);
    }

    public List<PopupDto> getCls_flagList(PopupDto parm){
        return  indexMapper.getCls_flagList(parm);
    }
}
