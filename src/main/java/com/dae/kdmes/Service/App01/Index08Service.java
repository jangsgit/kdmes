package com.dae.kdmes.Service.App01;

import com.dae.kdmes.DTO.App01.Pc110Dto;
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
    public List<PcFixDto> getMachFixList(PcFixDto parm){
        return  indexMapper.getMachFixList(parm);
    }
    public String getDupleMachchk(Pc110Dto parm){
        return  indexMapper.getDupleMachchk(parm);
    }
    public Boolean InsertMach(Pc110Dto parm){
        return  indexMapper.InsertMach(parm);
    }
    public Boolean UpdateMach(Pc110Dto parm){
        return  indexMapper.UpdateMach(parm);
    }
    public Boolean DeleteMach(Pc110Dto parm){
        return  indexMapper.DeleteMach(parm);
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

}
