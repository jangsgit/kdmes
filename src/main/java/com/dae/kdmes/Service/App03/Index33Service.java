package com.dae.kdmes.Service.App03;

import com.dae.kdmes.DTO.app03.Index33Dto;
import com.dae.kdmes.Mapper.App03.Index33Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index33Service")
public class Index33Service {

    @Autowired
    Index33Mapper indexMapper;

    public List<Index33Dto> getList(Index33Dto parm){
        return indexMapper.getList(parm);
    }


}
