package com.dae.kdmes.Service.App01;

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

    public Boolean InsertComCode(Index02Dto parm){ return  indexMapper.InsertComCode(parm);}
    public Boolean UpdateComCode(Index02Dto parm){  return  indexMapper.UpdateComCode(parm);}


}
