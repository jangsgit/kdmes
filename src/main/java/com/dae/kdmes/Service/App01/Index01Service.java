package com.dae.kdmes.Service.App01;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.Mapper.App01.Index01Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index01Service")
public class Index01Service {
    @Autowired
    Index01Mapper indexMapper;
    public List<Index01Dto> getComCodeList(Index01Dto parm){ return  indexMapper.getComCodeList(parm);} ;

    public Boolean InsertComCode(Index01Dto parm){ return  indexMapper.InsertComCode(parm);}
    public Boolean UpdateComCode(Index01Dto parm){  return  indexMapper.UpdateComCode(parm);  }
    public Boolean DeleteComCode(Index01Dto parm){  return  indexMapper.DeleteComCode(parm);  }

}
