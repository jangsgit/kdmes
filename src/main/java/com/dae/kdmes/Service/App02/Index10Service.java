package com.dae.kdmes.Service.App02;

import com.dae.kdmes.DTO.App01.Index03Dto;
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

    public List<Index10Dto> getFplanList(Index10Dto parm){
        return indexMapper.getFplanList(parm);
    }

    public List<Index10Dto> GetFplanList(Index10Dto parm){
        return indexMapper.GetFplanList(parm);
    }

    public Boolean DeleteFplan(Index10Dto parm){  return  indexMapper.DeleteFplan(parm);  }

    public Boolean InsertFplan(Index10Dto parm){ return  indexMapper.InsertFplan(parm);}
    public Boolean UpdateFplan(Index10Dto parm){  return  indexMapper.UpdateFplan(parm);  }

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
