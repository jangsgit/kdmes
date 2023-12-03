package com.dae.kdmes.Mapper.PDA;

import com.dae.kdmes.DTO.PDA.*;
import com.dae.kdmes.DTO.UserFormDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface KdpdaDBMapper {

    public UserFormDto GetUserInfoPDA(UserFormDto userinfo);

    public String getDa036MaxSeq(KosepPopDto parm) ;

    public int InsertDA036(KosepPopDto parm) ;
    public int InsertDa037(KosepPopDto parm) ;
    public int UpdateDA037(KosepPopDto parm) ;
    public int UpdateW020(KosepPopDto parm) ;
    public List<KosepPopDto> GetTBDA037List(KosepPopDto parm) ;







    public List<KosepList01Dto> GetTBDA035List(KosepPopDto parm) ;
    public List<KosepPopDto> GetTBCA510IpStoreList(KosepPopDto parm);
    public  KosepPopDto GetPcodeDataList02(KosepPopDto parm);
    public  KosepPopDto GetPcodeDataList03(KosepPopDto parm);

    public List<KosepList01Dto> GetTBDA037ChulList(KosepPopDto parm) ;

    public KosepDa037Dto GetLotNoData(HashMap<String,String> parm) ;
    public KosepDa037Dto GetLotNoList(KosepPopDto parm) ;


    public String getCa635MaxSeq(KosepCa635Dto parm) ;

   ;
    public int UpdateTBda035(KosepPopDto parm) ;
    public int UpdateDA006PANNEL(KosepPopDto parm) ;
    public int UpdateDA006WINGBADY(KosepPopDto parm) ;


    public int DeleteDA036(KosepPopDto parm) ;
    public int DeleteDA037(KosepPopDto parm) ;
    public int DeleteDA037H(KosepPopDto parm) ;
    public int DeleteDA035(KosepPopDto parm) ;
    public int DeleteDA006PAN(KosepPopDto parm) ;
    public int DeleteDA006WIN(KosepPopDto parm) ;

    public int DeleteCA635(KosepCa636Dto parm) ;
    public int DeleteCA636(KosepCa636Dto parm) ;










}
