package com.dae.kdmes.Mapper.App02;

import com.dae.kdmes.DTO.App01.IndexCa613Dto;
import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App02.Index10Dto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index10Mapper {


    public List<Index10Dto> GetFplanList(Index10Dto parm) ;

    public Boolean DeleteFplan(Index10Dto parm) ;

    public Boolean InsertFplan(Index10Dto  parm) ;
    public Boolean UpdateFplan(Index10Dto  parm) ;

    public List<IndexCa613Dto> SelectCa613List(IndexCa613Dto parm) ;

    public List<IndexCa613Dto> SelectCa613JaegoList(IndexCa613Dto parm) ;

    public Boolean DeleteCa613(IndexCa613Dto parm) ;

    public Boolean InsertCa613(IndexCa613Dto  parm) ;
    public Boolean UpdateCa613(IndexCa613Dto  parm) ;
    public String SelectMaxIbgnum(String  parm) ;

    public String GetFplanCheck(Index10Dto  parm) ;
    public List<Index10Dto> GetJpumListTot(Index10Dto parm) ;

    public List<Index10Dto> GetCifListTot(Index10Dto parm) ;

    public List<Index10Dto> GetInsaList(Index10Dto parm) ;

    public List<Index01Dto> GetIstorelist(Index01Dto parm) ;

    public List<Index01Dto> GetOstorelist(Index01Dto parm) ;

    public String getIndex10MaxSeq(String  parm) ;

    public String SelectMaxSeq(String parm) ;

    public String SelectMaxLot(Index10Dto parm) ;
    public String SelectCheckIndate(Index10Dto  parm) ;
    public List<PopupDto> getCls_flagList(PopupDto parm) ;
}
