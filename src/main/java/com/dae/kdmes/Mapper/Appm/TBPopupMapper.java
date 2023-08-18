package com.dae.kdmes.Mapper.Appm;

import com.dae.kdmes.DTO.Appm.FPLANBOM_VO;
import com.dae.kdmes.DTO.Appm.FPLANIWORK_VO;
import com.dae.kdmes.DTO.Appm.TBPopupVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TBPopupMapper {
     public List<TBPopupVO> GetWrmcList(TBPopupVO bankBoard);              //작업설비
     public List<TBPopupVO> GetWrmcList_blank(TBPopupVO bankBoard);           //작업설비
     public List<TBPopupVO> GetWfperidList(TBPopupVO bankBoard);           //작업자
     public List<FPLANBOM_VO> GetWfbomList(FPLANBOM_VO bankBoard);              //BOM
     public List<FPLANBOM_VO> GetWfbomList_blank();                             //BOM
     public List<FPLANIWORK_VO> GetWfiworkList(FPLANIWORK_VO bankBoard);              //투입량
     public List<FPLANIWORK_VO> GetNewWfiworkList(FPLANIWORK_VO bankBoard);              //신규투입량
     public List<FPLANIWORK_VO> GetWfiworkList_blank();                         //투입량
     public List<TBPopupVO> GetWscnt(TBPopupVO bankBoard);                         //현재생산량
     public String GetWtimeSeq(TBPopupVO bankBoard);                         //fplan_wtime seq값가져오기
     public void TB_Fplan_WtimeInsert(TBPopupVO bankBoard);    //입력
     public String GetWtimeEndSeq(TBPopupVO bankBoard);                         //fplan_wtime 종료 seq값가져오기
     public void TB_Fplan_WtimeUpdate(TBPopupVO bankBoard);    //가동종료
     public List<TBPopupVO> GetWBadList(TBPopupVO bankBoard);              //불량구분 리스트
     public List<TBPopupVO> GetWBadList_blank();                         //투입량
     public List<TBPopupVO> FPLAN_OWORK_List(TBPopupVO bankBoard);         //생산량 리스트


     public List<TBPopupVO> GetPernmList(TBPopupVO bankBoard);           //작업자
     public List<TBPopupVO> GetWrmcList01(TBPopupVO bankBoard); //가동종료
     public List<TBPopupVO> GetWBadList01(TBPopupVO bankBoard);


}
