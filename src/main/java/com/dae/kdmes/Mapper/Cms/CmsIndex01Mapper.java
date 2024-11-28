package com.dae.kdmes.Mapper.Cms;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App01.Index02Dto;
import com.dae.kdmes.DTO.Cms.CmsIndex01Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CmsIndex01Mapper {

    public Integer getSHOTDATA_wotqty(CmsIndex01Dto parm) ;
    public List<CmsIndex01Dto> getSHOTDATA_realtime(CmsIndex01Dto parm) ;



}
