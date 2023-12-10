package com.dae.kdmes.Mapper.App02;

import com.dae.kdmes.DTO.App01.Index01Dto;
import com.dae.kdmes.DTO.App02.Index11Dto;
import com.dae.kdmes.DTO.Appm.FPLAN_VO;
import com.dae.kdmes.DTO.Popup.PopupDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index11Mapper {

    public List<Index11Dto> getIndex11List(Index11Dto parm) ;
    public List<Index11Dto> getIndex12List01(Index11Dto parm) ;
    public List<FPLAN_VO> getIndex12List02(FPLAN_VO parm) ;

    public List<Index11Dto> getIndex13List01(Index11Dto parm) ;
    public List<FPLAN_VO> getIndex13List02(FPLAN_VO parm) ;

    public List<Index11Dto> getIndex14List01(Index11Dto parm) ;

    public List<Index11Dto> getIndex16List01(Index11Dto parm) ;



    public List<Index11Dto> getList(Index11Dto parm) ;

}
