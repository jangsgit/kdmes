package com.dae.kdmes.Mapper.App01;

import com.dae.kdmes.DTO.App01.Index03Dto;
import com.dae.kdmes.DTO.Appm.TBPopupVO;
import com.dae.kdmes.DTO.Popup.PopupDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index03Mapper {

    public List<Index03Dto> GetJpumList(Index03Dto parm) ;
    public List<Index03Dto> GetJpumList_BH(Index03Dto parm) ;
    public List<Index03Dto> GetJpumListTot(Index03Dto parm) ;
    public List<Index03Dto> GetGanListBonsa01(Index03Dto parm) ;
    public List<Index03Dto> GetGanListBonsa02(Index03Dto parm) ;
    public List<Index03Dto> GetJcustomCode(Index03Dto parm) ;
    public List<Index03Dto> GetJcustomCodeTot(Index03Dto parm) ;
    public List<Index03Dto> GetJpumCustList(Index03Dto parm) ;
    public List<Index03Dto> GetJpumModelList(Index03Dto parm) ;
    public List<Index03Dto> GetJpumCustJaegoList(Index03Dto parm) ;
    public Index03Dto GetJpumOrderJkey(Index03Dto parm) ;

    public List<PopupDto> getj1_keyList(PopupDto parm) ;

    public List<PopupDto> getj2_keyList(PopupDto parm) ;

    public List<PopupDto> getGumtype_keyList(PopupDto parm) ;


    public Boolean InsertJpum(Index03Dto  parm) ;
    public Boolean UpdateJpum(Index03Dto  parm) ;
    public Boolean DeleteJpum(Index03Dto  parm) ;
    public String GetJpumCheck(Index03Dto  parm) ;
    public String GetMaxJkey(Index03Dto  parm) ;



    public List<Index03Dto> SelectJegoIpgo(Index03Dto parm) ;
    public Boolean InsertJegoIpgo(Index03Dto parm) ;
    public Boolean DeleteJaegoIpgo(Index03Dto parm) ;
    public List<Index03Dto> SelectJegoList(Index03Dto parm) ;

    public List<TBPopupVO> GetJpumComboList(TBPopupVO bankBoard);              //품명
    public List<TBPopupVO> GetDoor1ComboList(TBPopupVO bankBoard);              //도어1
    public List<TBPopupVO> GetDoor2ComboList(TBPopupVO bankBoard);              //도어2
    public List<TBPopupVO> GetFormComboList(TBPopupVO bankBoard);              //유형
    public List<TBPopupVO> GetColorComboList(TBPopupVO bankBoard);              //색상
    public List<TBPopupVO> GetJthickComboList(TBPopupVO bankBoard);              //유리두께

}
