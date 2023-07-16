package com.dae.kdmes.Service.App02;

import com.dae.kdmes.DTO.App01.Index03Dto;
import com.dae.kdmes.DTO.App02.Index10Dto;
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


}
