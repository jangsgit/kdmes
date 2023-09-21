package com.dae.kdmes.Service.App02;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.Mapper.App02.Index11Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index11Service")
public class Index11Service {

    @Autowired
    Index11Mapper indexMapper;

    public List<Index11Dto> getWflagList(Index11Dto parm){
        return indexMapper.getWflagList(parm);
    }

    public List<Index11Dto> getList(Index11Dto parm){
        return indexMapper.getList(parm);
    }


}
