package com.dae.kdmes.Service.App03;

import com.dae.kdmes.DTO.app03.Index35Dto;
import com.dae.kdmes.Mapper.App03.Index35Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index35Service")
public class Index35Service {

    @Autowired
    Index35Mapper indexMapper;

    public List<Index35Dto> getList(Index35Dto parm){
        return indexMapper.getList(parm);
    }


}
