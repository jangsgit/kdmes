package com.dae.kdmes.Mapper.App02;

import com.dae.kdmes.DTO.App01.*;
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

    public Boolean InsertFplanCopy(Index10Dto  parm) ;

    public Boolean UpdateFplan(Index10Dto  parm) ;

    public List<IndexCa613Dto> SelectCa613List(IndexCa613Dto parm) ;
    public List<IndexCa613Dto> SelectDa037List(IndexCa613Dto parm) ;
    public List<IndexCa613Dto> SelectDa037ListBarcd(IndexCa613Dto parm) ;
    public List<IndexCa613Dto> SelectDa037ListJiyuk(IndexCa613Dto parm) ;


    public List<IndexCa611Dto> SelectDa036List(IndexCa611Dto parm) ;
    public List<IndexCa613Dto> SelectCa613ListMapChul(IndexCa613Dto parm) ;

    public List<IndexCa613OworkDto> SelectCa613ChulList(IndexCa613OworkDto parm) ;

    public List<IndexCa613Dto> SelectDa037ChulList(IndexCa613Dto parm);

    public IndexCa613OworkDto SelectCa613ChulListSum(IndexCa613OworkDto parm) ;

    public List<IndexCa609Dto> SelectCa609List(IndexCa609Dto parm) ;



    public List<IndexCa613Dto> SelectCa613JaegoList(IndexCa613Dto parm) ;

    public String GetJcodeCheck(Index03Dto parm);

    public String GetCltcdCheck(Index02Dto parm);



    public Boolean DeleteCa613(IndexCa613Dto parm) ;
    public Boolean DeleteDa037(IndexCa611Dto parm) ;
    public Boolean DeleteDA036Sch(IndexCa611Dto parm) ;

    public Boolean InsertCa613(IndexCa613Dto  parm) ;

    public Boolean InsertDA036Sch(IndexCa611Dto parm) ;

    public Boolean InsertDa037(IndexCa613Dto  parm) ;
    public Boolean UpdateCa613(IndexCa613Dto  parm) ;
    public Boolean UpdateDa036(IndexCa611Dto  parm) ;
    public Boolean UpdateDa037(IndexCa613Dto  parm) ;

    public Boolean InsertCA608(IndexCa608Dto parm) ;
    public Boolean InsertCA609(IndexCa609Dto  parm) ;
    public Boolean DeleteCA608(IndexCa608Dto parm) ;
    public Boolean DeleteCA609(IndexCa609Dto parm) ;

    public String SelectMaxIbgnum(String  parm) ;
    public String SelectMaxDelnum(String  parm) ;
    public String SelectMaxBalnum(String  parm) ;
    public String CA613_OWORK_MAXWSEQ(IndexCa613OworkDto parm) ;

    public Boolean CA613_OWORK_Insert(IndexCa613OworkDto  parm) ;
    public Boolean CA613_OWORK_Update(IndexCa613OworkDto  parm) ;
    public Boolean CA613_OWORK_Delete(IndexCa613OworkDto  parm) ;


    public String GetFplanCheck(Index10Dto  parm) ;
    public String SelectMaxLotno(Index10Dto parm) ;
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
