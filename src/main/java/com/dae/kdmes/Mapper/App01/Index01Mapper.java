package com.dae.kdmes.Mapper.App01;

import com.dae.kdmes.DTO.App01.Index01Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index01Mapper {

    public List<Index01Dto> getComCodeList(Index01Dto parm) ;

    public Boolean InsertComCode(Index01Dto parm) ;
    public Boolean UpdateComCode(Index01Dto  parm) ;
    public Boolean DeleteComCode(Index01Dto  parm) ;

    public List<Index01Dto> GetComcodeDetailList(Index01Dto parm) ;

    public List<Index01Dto> getComCodeLists(Index01Dto parm) ;

    public List<Index01Dto> getWperidlist(Index01Dto parm) ;

    public List<Index01Dto> getWbadlist(Index01Dto parm) ;

    public Boolean InsertComCodeDetail(Index01Dto parm) ;
    public Boolean UpdateComCodeDetail(Index01Dto  parm) ;
    public Boolean DeleteComCodeDetail(Index01Dto  parm) ;

    public String GetComCodeCheck(Index01Dto  parm) ;

    public List<Index01Dto> getCom_rem2_keyList(Index01Dto parm) ;
}
