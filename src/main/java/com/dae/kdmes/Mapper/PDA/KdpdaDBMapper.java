package com.dae.kdmes.Mapper.PDA;

import com.dae.kdmes.DTO.Appm.FPLAN_VO;
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


    public List<KosepList01Dto> getIndex03PDAList(KosepList01Dto parm) ;


    public int DeleteDA036(KosepPopDto parm) ;
    public int DeleteDA037(KosepPopDto parm) ;

    public int DeleteCA635(KosepCa636Dto parm) ;
    public int DeleteCA636(KosepCa636Dto parm) ;










}
