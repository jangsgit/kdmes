package com.dae.kdmes.Service.App01;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App01.Index02Dto;
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

    public List<Index02Dto> GetFplanDetailList(Index02Dto parm){
        return  indexMapper.GetFplanDetailList(parm);
    }

    public Boolean InsertFplanDetail(Index02Dto parm){ return  indexMapper.InsertFplanDetail(parm);}
    public Boolean UpdateFplanDetail(Index02Dto parm){  return  indexMapper.UpdateFplanDetail(parm);}
    public Boolean DeleteFplanDetail(Index02Dto parm){  return  indexMapper.DeleteFplanDetail(parm);}

    public String GetFplanCheck(Index02Dto parm){  return  indexMapper.GetFplanCheck(parm);  }


}
