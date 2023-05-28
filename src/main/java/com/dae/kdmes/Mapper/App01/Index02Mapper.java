package com.dae.kdmes.Mapper.App01;

import com.dae.kdmes.DTO.App01.Index02Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index02Mapper {

    public List<Index02Dto> getWflagList(Index02Dto parm) ;

    public Boolean InsertComCode(Index02Dto parm) ;
    public Boolean UpdateComCode(Index02Dto  parm) ;
    public Boolean DeleteComCode(Index02Dto  parm) ;

}
