package com.dae.kdmes.Mapper.App01;

import com.dae.kdmes.DTO.App01.Index04Dto;
import com.dae.kdmes.DTO.App01.Pc110Dto;
import com.dae.kdmes.DTO.App01.Pc120Dto;
import com.dae.kdmes.DTO.App01.PcFixDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index08Mapper {

    public List<Pc110Dto> getMachList(Pc110Dto parm) ;
    public List<Pc120Dto> getFacList(Pc120Dto parm) ;
    public List<Pc110Dto> getPringImg(Pc110Dto parm);

    public String getDupleMachchk(Pc110Dto parm) ;

    public String getDupleFachchk(Pc120Dto parm) ;
    public String getDupleFacchk(Pc120Dto parm) ;
    public String SelectMaxFac(Pc120Dto parm) ;


    public Boolean InsertMach(Pc110Dto parm) ;
    public Boolean InsertFac(Pc120Dto parm) ;

    public Boolean UpdateMach(Pc110Dto parm) ;
    public Boolean UpdateFac(Pc120Dto parm) ;

    public Boolean DeleteMach(Pc110Dto parm) ;
    public Boolean DeleteFac(Pc120Dto parm) ;

    public List<PcFixDto> getMachFixList(PcFixDto parm) ;
    public Boolean InsertMachFix(PcFixDto parm) ;

    public Boolean UpdateMachFix(PcFixDto parm) ;

    public Boolean DeleteMachFix(PcFixDto parm) ;

    public Boolean DeleteMachFixAll(Pc110Dto parm) ;


    public Boolean TB_PCFIXLIST(Pc110Dto parm) ;

    public Boolean DeleteGumIMG(Pc110Dto parm) ;

    public Boolean DeleteGumALLIMG(Pc110Dto parm) ;




}
