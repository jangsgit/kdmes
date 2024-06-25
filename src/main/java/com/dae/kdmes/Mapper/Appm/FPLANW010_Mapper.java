package com.dae.kdmes.Mapper.Appm;

import com.dae.kdmes.DTO.Appm.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface FPLANW010_Mapper {
     public List<FPLAN_VO> GetFPLAN_List(FPLAN_VO xfplanBoard);
     public List<FPLAN_VO> GetFPLAN_List02(FPLAN_VO xfplanBoard);
     public List<FPLAN_VO> GetFPLAN_List02_JO(FPLAN_VO xfplanBoard);
     public List<FPLAN_VO> GetFPLAN_List02_REG(FPLAN_VO xfplanBoard);
     public List<FPLAN_VO> GetFPLAN_List02_REG_JO(FPLAN_VO xfplanBoard);
     public List<FPLAN_VO> GetFPLAN_List02_HIS(FPLAN_VO xfplanBoard);
     public List<FPLAN_VO> GetFPLAN_List02_HIS_JO(FPLAN_VO xfplanBoard);
     public List<FPLAN_VO> GetFPLAN_List02Arr(HashMap<String,String> parm);
     public List<FPLAN_VO> GetFPLAN_List02Arr_JO(HashMap<String,String> parm);

     public List<FPLAN_VO> GetFPLAN_List03(FPLAN_VO xfplanBoard);
     public List<FPLAN_VO> GetFPLAN_List03_REG(FPLAN_VO xfplanBoard);
     public List<FPLAN_VO> GetFPLAN_List03_HIS(FPLAN_VO xfplanBoard);
     public List<FPLAN_VO> GetFPLAN_List03Arr(HashMap<String,String> parm);
     //list
     public FPLANW010_VO GetFPLANW010_ListOne();   //한건 조회
     public FPLANW010_VO GetFPLANW010_Detail(FPLANW010_VO xa012Board);   //
     public FPLANW010_VO GetFPLANW020_Detail(FPLANW010_VO xa012Board);   //
     public FPLANW010_VO GetFPLANW030_Detail(FPLANW010_VO xa012Board);   //
     public List<FPLANW010_VO>  GetFPLANW020_LIST(FPLANW010_VO xa012Board);   //
     // 조회
     public Boolean FPLANW010_Insert(FPLANW010_VO xa012Board);    //입력
     public Boolean FPLANW010_Update(FPLANW010_VO xa012Board);    //입력
     public Boolean FPLANW010_Update_GQTY(FPLANW010_VO xa012Board);    //수정
     public Boolean FPLANW010_Update_GDEL(FPLANW010_VO xa012Board);    //수정
     public Boolean FPLANW010_Update_JQTY(FPLANW010_VO xa012Board);    //수정
     public Boolean FPLANW010_Update_JDEL(FPLANW010_VO xa012Board);    //수정


     public Boolean FPLANW020_Insert(FPLANW010_VO xa012Board);    //입력
     public Boolean FPLANW020_Update(FPLANW010_VO xa012Board);    //수정


     public Boolean FPLANW030_Insert(FPLANW010_VO xa012Board);    //입력
     public Boolean FPLANW030_Update(FPLANW010_VO xa012Board);    //수정

     public Boolean FPLANW010_WrmcUpdate(FPLANW010_VO xa012Board);    //수정
     public Boolean FPLANW010_Delete(FPLANW010_VO parm);       //삭제
     public Boolean FPLANW020_Delete(FPLANW010_VO parm);       //삭제
     public Boolean FPLANW030_Delete(FPLANW010_VO parm);       //삭제

     public Boolean FPLAN_OWORK_Delete(FPLANW010_VO parm);          //
     public Boolean FPLAN_WORK_Delete(FPLANW010_VO parm);         //



     public FPLANW010_VO GetFPLANW010_Blank();                //blank data
     public FPLAN_VO GetFPLAN_Blank();                //blank data
     public String GetWtimeWseq(TBPopupVO bankBoard);   //TB_FPLAN_WORK seq가져오기
     public Boolean FPLANWORK_Insert(FPLANW010_VO parm);
     public Boolean FPLANWORK_update(FPLANW010_VO parm);
     public Boolean FPLAN_Update(FPLANW010_VO parm);
     public Boolean FPLAN_Update_GDEL(FPLANW010_VO parm);

     public Boolean FPLAN_OWORK_Insert(FPLANW010_VO parm);     //blank data
     public String FPLAN_OWORK_MAXWSEQ(FPLANW010_VO bankBoard);
     public String FPLAN_IWORK_MAXWSEQ(FPLANIWORK_VO bankBoard);
     public String FPLAN_W020_MAXLOT(FPLANW010_VO bankBoard);
     public String FPLAN_W030_MAXLOT(FPLANW010_VO bankBoard);


     public String GetWIworkWseq(FPLANIWORK_VO bankBoard);
     public List<FPLANW010_VO> GetWIworkDetail_blank(TBPopupVO parm);   //조회
     public List<FPLANW010_VO> GetWIworkDetail(TBPopupVO parm);   //조회
     public Boolean FPLANI_IWORK_Insert(FPLANIWORK_VO parm);
     public Boolean FPLANI_IWORK_update(FPLANIWORK_VO parm);     //
     public Boolean FPLAN_IWORK_Delete(FPLANIWORK_VO parm);


     public List<FPLANWPERID_VO> FPLAN_WPERID_SELECT(FPLANW010_VO parm);
     public Boolean FPLAN_WPERID_Insert(FPLANWPERID_VO parm);
     public Boolean FPLAN_WPERID_Delete(FPLANWPERID_VO parm);


     public List<FPLANWBAD_VO> FPLAN_WBAD_SELECT_blank(FPLANWBAD_VO parm);   //조회
     public String FPLAN_WBAD_SELECT(FPLANWBAD_VO parm);   //조회
     public String FPLAN_WBAD_MAXWSEQ(FPLANWBAD_VO bankBoard);   //TB_FPLAN_WORK seq가져오기


     public Boolean FPLAN_WBAD_Insert(FPLANWBAD_VO parm);
     public Boolean FPLAN_WBAD_Update(FPLANWBAD_VO parm);
     public Boolean FPLAN_WBAD_Delete(FPLANW010_VO parm);

     public String FPLAN_WTIME_MAXSEQ(FPLANWTIME_VO bankBoard);
     public Boolean FPLAN_WTIME_Insert(FPLANWTIME_VO parm);
     public Boolean FPLAN_WTIME_Delete(FPLANW010_VO parm);
     public Boolean FPLAN_WTIME_Update(FPLANWTIME_VO parm);

     public Boolean FPLAN_OWORK_Update(FPLANW010_VO parm);
     public FPLANW010_VO FPLAN_OWORK_SUMQTY(FPLANW010_VO parm);
     public Boolean FPLAN_OWORK_PERDELETE(FPLANW010_VO parm);

     public List<FPLANIWORK_VO> GetPlanSearch(FPLANIWORK_VO parm);   //
     public List<TBFplanNowVO> GetPlanViewnow(TBFplanNowVO parm);   //조


}
