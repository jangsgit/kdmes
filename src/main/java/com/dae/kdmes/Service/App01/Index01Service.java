package com.dae.kdmes.Service.App01;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.Mapper.App01.Index01Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index01Service")
public class Index01Service {

    @Autowired
    Index01Mapper indexMapper;

    public List<Index01Dto> getComCodeList(Index01Dto parm){
        return indexMapper.getComCodeList(parm);
    }

    public Boolean InsertComCode(Index01Dto parm){ return  indexMapper.InsertComCode(parm);}
    public Boolean UpdateComCode(Index01Dto parm){  return  indexMapper.UpdateComCode(parm);}
    public Boolean DeleteComCode(Index01Dto parm){  return  indexMapper.DeleteComCode(parm);}

    public List<Index01Dto> getComCodeLists(Index01Dto parm){
        return indexMapper.getComCodeLists(parm);
    }

    public List<Index01Dto> GetComcodeDetailList(Index01Dto parm){
        return  indexMapper.GetComcodeDetailList(parm);
    }
    public List<Index01Dto> getWperidlist(Index01Dto parm){
        return  indexMapper.getWperidlist(parm);
    }

    public List<Index01Dto> getWbadlist(Index01Dto parm){
        return  indexMapper.getWbadlist(parm);
    }

    public Boolean InsertComCodeDetail(Index01Dto parm){ return  indexMapper.InsertComCodeDetail(parm);}
    public Boolean UpdateComCodeDetail(Index01Dto parm){  return  indexMapper.UpdateComCodeDetail(parm);}
    public Boolean DeleteComCodeDetail(Index01Dto parm){  return  indexMapper.DeleteComCodeDetail(parm);}

    public String GetComCodeCheck(Index01Dto parm){  return  indexMapper.GetComCodeCheck(parm);  }

    public List<Index01Dto> getCom_rem2_keyList(Index01Dto parm){
        return  indexMapper.getCom_rem2_keyList(parm);
    }

}
