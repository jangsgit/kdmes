package com.dae.kdmes.Mapper.App01;

import com.dae.kdmes.DTO.App01.Index02Dto;
import com.dae.kdmes.DTO.App01.Index04Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index04Mapper {

    public List<Index04Dto> getPgunList(Index04Dto parm) ;

    public List<Index04Dto> getHcodList(Index04Dto parm) ;

    public Boolean InsertHcod(Index04Dto parm) ;
    public Boolean UpdateHcod(Index04Dto  parm) ;
    public Boolean DeleteHcod(Index04Dto  parm) ;

    public String GetHcodCheck(Index04Dto  parm) ;

    public List<Index04Dto> getBgroupList(Index04Dto parm) ;

    public Boolean InsertBgroup(Index04Dto parm) ;
    public Boolean UpdateBgroup(Index04Dto  parm) ;
    public Boolean DeleteBgroup(Index04Dto  parm) ;

    public String GetBgroupCheck(Index04Dto  parm) ;




}
