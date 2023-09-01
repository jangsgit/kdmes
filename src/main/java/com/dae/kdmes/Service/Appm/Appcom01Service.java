package com.dae.kdmes.Service.Appm;

import com.dae.kdmes.DTO.Appm.*;
import com.dae.kdmes.Mapper.Appm.FPLANW010_Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service("Appcom01Service01")
public class Appcom01Service {
    private final FPLANW010_Mapper FPLANW010_mapper;
    protected Log log =  LogFactory.getLog(this.getClass());


    public List<FPLAN_VO> GetFPLAN_List(FPLAN_VO parm){
        return FPLANW010_mapper.GetFPLAN_List(parm);
    }
    public List<FPLAN_VO> GetFPLAN_List02(FPLAN_VO parm){
        return FPLANW010_mapper.GetFPLAN_List02(parm);
    }
    public List<FPLAN_VO> GetFPLAN_List03(FPLAN_VO parm){
        return FPLANW010_mapper.GetFPLAN_List03(parm);
    }
    public List<FPLAN_VO> GetFPLAN_List02Arr(HashMap<String,String> parm){
        return FPLANW010_mapper.GetFPLAN_List02Arr(parm);
    }
    public Object GetFPLANW010_ListOne(){
        return FPLANW010_mapper.GetFPLANW010_ListOne();
    }
    public Object GetFPLANW010_Detail (FPLANW010_VO parm){
        return FPLANW010_mapper.GetFPLANW010_Detail(parm);
    }
    public Object GetFPLANW020_Detail (FPLANW010_VO parm){
        return FPLANW010_mapper.GetFPLANW020_Detail(parm);
    }

    public void FPLANW010_Insert(FPLANW010_VO parm){
        FPLANW010_mapper.FPLANW010_Insert(parm);
    }
    public void FPLANW010_Update(FPLANW010_VO parm){
        FPLANW010_mapper.FPLANW010_Update(parm);
    }

    public void FPLANW020_Insert(FPLANW010_VO parm){
        FPLANW010_mapper.FPLANW020_Insert(parm);
    }
    public void FPLANW020_Update(FPLANW010_VO parm){
        FPLANW010_mapper.FPLANW020_Update(parm);
    }

    public void FPLAN_Update(FPLANW010_VO parm){
        FPLANW010_mapper.FPLAN_Update(parm);
    }
    public void FPLAN_OWORK_Insert(FPLANW010_VO parm){
        FPLANW010_mapper.FPLAN_OWORK_Insert(parm);
    }
    public String FPLAN_OWORK_MAXWSEQ(FPLANW010_VO parm){ return FPLANW010_mapper.FPLAN_OWORK_MAXWSEQ(parm);}
    public String FPLAN_IWORK_MAXWSEQ(FPLANIWORK_VO parm){ return FPLANW010_mapper.FPLAN_IWORK_MAXWSEQ(parm);}

    public void FPLANW010_WrmcUpdate(FPLANW010_VO parm){
        FPLANW010_mapper.FPLANW010_WrmcUpdate(parm);
    }
    public void FPLANW010_Delete(FPLANW010_VO parm){
        FPLANW010_mapper.FPLANW010_Delete(parm);
    }

    public void FPLAN_OWORK_Delete(FPLANW010_VO parm){FPLANW010_mapper.FPLAN_OWORK_Delete(parm);}
    public void FPLAN_IWORK_Delete(FPLANW010_VO parm){FPLANW010_mapper.FPLAN_IWORK_Delete(parm);}
    public void FPLAN_WORK_Delete(FPLANW010_VO parm){FPLANW010_mapper.FPLAN_WORK_Delete(parm);}


    public FPLANW010_VO FPLANW010_Blank(){
        return FPLANW010_mapper.GetFPLANW010_Blank();
    }
    public Object FPLAN_Blank(){
        return FPLANW010_mapper.GetFPLAN_Blank();
    }
    public void FPLANWORK_Insert(FPLANW010_VO parm){ FPLANW010_mapper.FPLANWORK_Insert(parm); }
    public void FPLANWORK_Update(FPLANW010_VO parm){ FPLANW010_mapper.FPLANWORK_update(parm); }
    public String GetWtimeWseq(TBPopupVO parm){
        return FPLANW010_mapper.GetWtimeWseq(parm);
    }


    public String GetWIworkWseq(FPLANIWORK_VO parm){
        return FPLANW010_mapper.GetWIworkWseq(parm);
    }
    public Object GetWIworkDetail(TBPopupVO parm){return FPLANW010_mapper.GetWIworkDetail(parm); }
    public Object GetWIworkDetail_blank(TBPopupVO parm){return FPLANW010_mapper.GetWIworkDetail_blank(parm); }


    public Boolean FPLANI_IWORK_Insert(FPLANIWORK_VO parm){ return FPLANW010_mapper.FPLANI_IWORK_Insert(parm); }
    public Boolean FPLANI_IWORK_update(FPLANIWORK_VO parm){ return FPLANW010_mapper.FPLANI_IWORK_update(parm); }


    public List<FPLANWPERID_VO> FPLAN_WPERID_SELECT (FPLANW010_VO parm){return FPLANW010_mapper.FPLAN_WPERID_SELECT(parm);}
    public void FPLAN_WPERID_Insert(FPLANWPERID_VO parm){ FPLANW010_mapper.FPLAN_WPERID_Insert(parm); }
    public void FPLAN_WPERID_Delete(FPLANWPERID_VO parm){ FPLANW010_mapper.FPLAN_WPERID_Delete(parm); }


    public List<FPLANWBAD_VO> FPLAN_WBAD_SELECT_blank(FPLANWBAD_VO parm){return FPLANW010_mapper.FPLAN_WBAD_SELECT_blank(parm);}
    public String FPLAN_WBAD_SELECT(FPLANWBAD_VO parm){return FPLANW010_mapper.FPLAN_WBAD_SELECT(parm);}

    public String FPLAN_WBAD_MAXWSEQ(FPLANWBAD_VO parm){
        return FPLANW010_mapper.FPLAN_WBAD_MAXWSEQ(parm);
    }
    public void FPLAN_WBAD_Insert(FPLANWBAD_VO parm){ FPLANW010_mapper.FPLAN_WBAD_Insert(parm); }
    public void FPLAN_WBAD_Update(FPLANWBAD_VO parm){ FPLANW010_mapper.FPLAN_WBAD_Update(parm); }

    public void FPLAN_WBAD_Delete(FPLANW010_VO parm){ FPLANW010_mapper.FPLAN_WBAD_Delete(parm); }

    public String FPLAN_WTIME_MAXSEQ(FPLANWTIME_VO parm){ return FPLANW010_mapper.FPLAN_WTIME_MAXSEQ(parm); }
    public void FPLAN_WTIME_Insert(FPLANWTIME_VO parm){ FPLANW010_mapper.FPLAN_WTIME_Insert(parm); }
    public void FPLAN_WTIME_Delete(FPLANW010_VO parm){ FPLANW010_mapper.FPLAN_WTIME_Delete(parm); }
    public void FPLAN_WTIME_Update(FPLANWTIME_VO parm){ FPLANW010_mapper.FPLAN_WTIME_Update(parm); }

    public void FPLAN_OWORK_Update(FPLANW010_VO parm){ FPLANW010_mapper.FPLAN_OWORK_Update(parm); }
    public FPLANW010_VO FPLAN_OWORK_SUMQTY(FPLANW010_VO parm){return FPLANW010_mapper.FPLAN_OWORK_SUMQTY(parm); }


    public List<FPLANIWORK_VO> GetPlanSearch(FPLANIWORK_VO parm){
        return FPLANW010_mapper.GetPlanSearch(parm);
    }
    public List<TBFplanNowVO> GetPlanViewnow(TBFplanNowVO parm){
        return FPLANW010_mapper.GetPlanViewnow(parm);
    }
}
