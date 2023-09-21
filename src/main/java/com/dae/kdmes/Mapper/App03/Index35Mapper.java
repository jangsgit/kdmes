package com.dae.kdmes.Mapper.App03;

import com.dae.kdmes.DTO.app03.Index35Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index35Mapper {

    public List<Index35Dto> getList(Index35Dto parm) ;


}
