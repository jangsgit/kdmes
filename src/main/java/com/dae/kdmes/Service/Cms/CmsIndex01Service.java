package com.dae.kdmes.Service.Cms;

import com.dae.kdmes.DTO.Cms.CmsIndex01Dto;
import com.dae.kdmes.Mapper.Cms.CmsIndex01Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("CmsIndex01Service")
public class CmsIndex01Service {

    @Autowired
    CmsIndex01Mapper  cmsindexMapper;

    public Integer getSHOTDATA_wotqty(CmsIndex01Dto parm){
        return cmsindexMapper.getSHOTDATA_wotqty(parm);
    }

}
