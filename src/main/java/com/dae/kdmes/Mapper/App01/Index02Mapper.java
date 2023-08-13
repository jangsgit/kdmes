package com.dae.kdmes.Mapper.App01;

import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.App01.Index02Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index02Mapper {

    public List<Index02Dto> getWflagList(Index02Dto parm) ;

    public List<Index02Dto> GetFplanDetailList(Index02Dto parm) ;

    public Boolean InsertFplanDetail(Index02Dto parm) ;
    public Boolean UpdateFplanDetail(Index02Dto  parm) ;
    public Boolean DeleteFplanDetail(Index02Dto  parm) ;

    public String GetFplanCheck(Index02Dto  parm) ;



    public List<Index02Dto> GetCifList(Index02Dto parm) ;
    public List<Index02Dto> GetCifListTot(Index02Dto parm) ;

    public Index02Dto GetCifListAcode(Index02Dto parm) ;
    public Index02Dto GetCifBonsa(Index02Dto parm) ;
    public String getIndex02MaxSeq(String  parm) ;
    public Boolean InsertCif(Index02Dto  parm) ;
    public Boolean UpdateCif(Index02Dto  parm) ;
    public Boolean DeleteCif(Index02Dto  parm) ;


    public List<PopupDto> getCifCodeList(PopupDto parm) ;

}
