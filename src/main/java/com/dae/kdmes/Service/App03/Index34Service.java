package com.dae.kdmes.Service.App03;

import com.dae.kdmes.DTO.app03.Index34Dto;
import com.dae.kdmes.Mapper.App03.Index34Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index34Service")
public class Index34Service {

    @Autowired
    Index34Mapper indexMapper;

    public List<Index34Dto> getList(Index34Dto parm){
        return indexMapper.getList(parm);
    }


}
