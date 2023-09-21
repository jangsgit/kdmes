package com.dae.kdmes.Mapper.App03;

import com.dae.kdmes.DTO.app03.Index33Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index33Mapper {

    public List<Index33Dto> getList(Index33Dto parm) ;


}
