package com.dae.kdmes.Service.App01;

import com.dae.kdmes.DTO.App01.Pc110Dto;
import com.dae.kdmes.DTO.App01.Pc120Dto;
import com.dae.kdmes.DTO.App01.PcFixDto;
import com.dae.kdmes.Mapper.App01.Index08Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index08Service")
public class Index08Service {
    @Autowired
    Index08Mapper indexMapper;

    public List<Pc110Dto> getMachList(Pc110Dto parm){
        return  indexMapper.getMachList(parm);
    }
    public List<Pc120Dto> getFacList(Pc120Dto parm){
        return  indexMapper.getFacList(parm);
    }
    public List<Pc110Dto> getPringImg(Pc110Dto parm){
        return  indexMapper.getPringImg(parm);
    }

    public List<PcFixDto> getMachFixList(PcFixDto parm){
        return  indexMapper.getMachFixList(parm);
    }
    public String getDupleMachchk(Pc110Dto parm){
        return  indexMapper.getDupleMachchk(parm);
    }
    public String SelectMaxFac(Pc120Dto parm){
        return  indexMapper.SelectMaxFac(parm);
    }

    public String getDupleFacchk(Pc120Dto parm){
        return  indexMapper.getDupleFacchk(parm);
    }
    public Boolean InsertMach(Pc110Dto parm){
        return  indexMapper.InsertMach(parm);
    }
    public Boolean InsertFac(Pc120Dto parm){
        return  indexMapper.InsertFac(parm);
    }
    public Boolean UpdateMach(Pc110Dto parm){
        return  indexMapper.UpdateMach(parm);
    }
    public Boolean UpdateFac(Pc120Dto parm){
        return  indexMapper.UpdateFac(parm);
    }
    public Boolean DeleteMach(Pc110Dto parm){
        return  indexMapper.DeleteMach(parm);
    }
    public Boolean DeleteFac(Pc120Dto parm){
        return  indexMapper.DeleteFac(parm);
    }

    public Boolean InsertMachFix(PcFixDto parm){
        return  indexMapper.InsertMachFix(parm);
    }
    public Boolean UpdateMachFix(PcFixDto parm){
        return  indexMapper.UpdateMachFix(parm);
    }
    public Boolean DeleteMachFix(PcFixDto parm){
        return  indexMapper.DeleteMachFix(parm);
    }
    public Boolean DeleteMachFixAll(Pc110Dto parm){
        return  indexMapper.DeleteMachFixAll(parm);
    }

    public Boolean TB_PCFIXLIST(Pc110Dto parm){
        return  indexMapper.TB_PCFIXLIST(parm);
    }

    public Boolean DeleteGumIMG(Pc110Dto parm){
        return  indexMapper.DeleteGumIMG(parm);
    }
    public Boolean DeleteGumALLIMG(Pc110Dto parm){
        return  indexMapper.DeleteGumALLIMG(parm);
    }



}
