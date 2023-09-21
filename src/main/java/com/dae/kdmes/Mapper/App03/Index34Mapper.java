package com.dae.kdmes.Mapper.App03;

import com.dae.kdmes.DTO.app03.Index34Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index34Mapper {

    public List<Index34Dto> getList(Index34Dto parm) ;


}
