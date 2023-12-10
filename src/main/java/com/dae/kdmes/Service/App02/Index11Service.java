package com.dae.kdmes.Service.App02;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.DTO.Appm.FPLAN_VO;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.Mapper.App02.Index11Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index11Service")
public class Index11Service {

    @Autowired
    Index11Mapper indexMapper;

    public List<Index11Dto> getIndex11List(Index11Dto parm){
        return indexMapper.getIndex11List(parm);
    }
    public List<Index11Dto> getIndex12List01(Index11Dto parm){
        return indexMapper.getIndex12List01(parm);
    }
    public List<FPLAN_VO> getIndex12List02(FPLAN_VO parm){
        return indexMapper.getIndex12List02(parm);
    }
    public List<Index11Dto> getIndex13List01(Index11Dto parm){
        return indexMapper.getIndex13List01(parm);
    }
    public List<FPLAN_VO> getIndex13List02(FPLAN_VO parm){
        return indexMapper.getIndex13List02(parm);
    }
    public List<Index11Dto> getIndex14List01(Index11Dto parm){
        return indexMapper.getIndex14List01(parm);
    }
    public List<Index11Dto> getIndex16List01(Index11Dto parm){
        return indexMapper.getIndex16List01(parm);
    }



    public List<Index11Dto> getList(Index11Dto parm){
        return indexMapper.getList(parm);
    }


}
