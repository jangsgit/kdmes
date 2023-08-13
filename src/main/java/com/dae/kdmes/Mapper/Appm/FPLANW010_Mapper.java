package com.dae.kdmes.Mapper.Appm;

import com.dae.kdmes.DTO.*;
import com.dae.kdmes.DTO.Appm.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FPLANW010_Mapper {
     public List<FPLAN_VO> GetFPLAN_List(FPLAN_VO xfplanBoard);           //list
     public FPLANW010_VO GetFPLANW010_ListOne();   //한건 조회
     public FPLANW010_VO GetFPLANW010_Detail(FPLANW010_VO xa012Board);   //조회
     public void FPLANW010_Insert(FPLANW010_VO xa012Board);    //입력
     public void FPLANW010_Update(FPLANW010_VO xa012Board);    //수정
     public void FPLANW010_WrmcUpdate(FPLANW010_VO xa012Board);    //수정
     public void FPLANW010_Delete(FPLANW010_VO parm);       //삭제

     public void FPLAN_OWORK_Delete(FPLANW010_VO parm);         //
     public void FPLAN_IWORK_Delete(FPLANW010_VO parm);         //
     public void FPLAN_WORK_Delete(FPLANW010_VO parm);         //



     public FPLANW010_VO GetFPLANW010_Blank();                //blank data
     public FPLAN_VO GetFPLAN_Blank();                //blank data
     public String GetWtimeWseq(TBPopupVO bankBoard);   //TB_FPLAN_WORK seq가져오기
     public void FPLANWORK_Insert(FPLANW010_VO parm);
     public void FPLANWORK_update(FPLANW010_VO parm);
     public void FPLAN_Update(FPLANW010_VO parm);
     public void FPLAN_OWORK_Insert(FPLANW010_VO parm);     //blank data
     public String FPLAN_OWORK_MAXWSEQ(FPLANW010_VO bankBoard);
     public String FPLAN_IWORK_MAXWSEQ(FPLANW010_VO bankBoard);


     public String GetWIworkWseq(TBPopupVO bankBoard);
     public List<FPLANW010_VO> GetWIworkDetail_blank(TBPopupVO parm);   //조회
     public List<FPLANW010_VO> GetWIworkDetail(TBPopupVO parm);   //조회
     public void FPLANI_IWORK_Insert(FPLANW010_VO parm);
     public void FPLANI_IWORK_update(FPLANW010_VO parm);


     public List<FPLANWPERID_VO> FPLAN_WPERID_SELECT(FPLANW010_VO parm);
     public void FPLAN_WPERID_Insert(FPLANWPERID_VO parm);
     public void FPLAN_WPERID_Delete(FPLANWPERID_VO parm);


     public List<FPLANWBAD_VO> FPLAN_WBAD_SELECT_blank(FPLANWBAD_VO parm);   //조회
     public String FPLAN_WBAD_SELECT(FPLANWBAD_VO parm);   //조회
     public String FPLAN_WBAD_MAXWSEQ(FPLANWBAD_VO bankBoard);   //TB_FPLAN_WORK seq가져오기


     public void FPLAN_WBAD_Insert(FPLANWBAD_VO parm);
     public void FPLAN_WBAD_Update(FPLANWBAD_VO parm);
     public void FPLAN_WBAD_Delete(FPLANWBAD_VO parm);

     public String FPLAN_WTIME_MAXSEQ(FPLANWTIME_VO bankBoard);
     public void FPLAN_WTIME_Insert(FPLANWTIME_VO parm);
     public void FPLAN_WTIME_Delete(FPLANW010_VO parm);
     public void FPLAN_WTIME_Update(FPLANWTIME_VO parm);

     public void FPLAN_OWORK_Update(FPLANW010_VO parm);
     public FPLANW010_VO FPLAN_OWORK_SUMQTY(FPLANW010_VO parm);



}
