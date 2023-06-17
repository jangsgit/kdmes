package com.dae.kdmes.Service.App01;

import com.dae.kdmes.DTO.App01.Index02Dto;
import com.dae.kdmes.DTO.App01.Index04Dto;
import com.dae.kdmes.Mapper.App01.Index04Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index04Service")
public class Index04Service {
    @Autowired
    Index04Mapper indexMapper;

    public List<Index04Dto> getPgunList(Index04Dto parm){
                return  indexMapper.getPgunList(parm);
    }

    public List<Index04Dto> getHcodList(Index04Dto parm){
        return  indexMapper.getHcodList(parm);
    }

    public Boolean InsertHcod(Index04Dto parm){ return  indexMapper.InsertHcod(parm);}
    public Boolean UpdateHcod(Index04Dto parm){  return  indexMapper.UpdateHcod(parm);}
    public Boolean DeleteHcod(Index04Dto parm){  return  indexMapper.DeleteHcod(parm);}

    public String GetHcodCheck(Index04Dto parm){  return  indexMapper.GetHcodCheck(parm);  }

    public List<Index04Dto> getBgroupList(Index04Dto parm){
        return  indexMapper.getBgroupList(parm);
    }

    public Boolean InsertBgroup(Index04Dto parm){ return  indexMapper.InsertBgroup(parm);}
    public Boolean UpdateBgroup(Index04Dto parm){  return  indexMapper.UpdateBgroup(parm);}
    public Boolean DeleteBgroup(Index04Dto parm){  return  indexMapper.DeleteBgroup(parm);}

    public String GetBgroupCheck(Index04Dto parm){  return  indexMapper.GetBgroupCheck(parm);  }


}
