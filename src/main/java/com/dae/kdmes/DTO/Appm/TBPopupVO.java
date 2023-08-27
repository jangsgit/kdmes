package com.dae.kdmes.DTO.Appm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TBPopupVO {
    private String custcd;
    private String spjangcd;
    private String wflag;
    private String wrmc;            //설비코드 .
    private String linecd;            //라인코드 .
    private String linenm;            //라인명 .
    private String wflagcd;            //공정코드 .
    private String wflagnm;            //공정명 .
    private String wperid;           //설비작업자 코드
    private String machname;        //설비명칭
    private String wpernm;           //설비작업자명
    private String plan_no;           //작업지시번호
    private String wseq;           //
    private String yymm;           //
    private String sdate;           //
    private String tdate;           //
    private String store;           //
    private String storenm;           //
    private String pcode;
    private String pname;
    private String psize;
    private String punit;
    private String lotno;           //
    private String ostore;           //
    private float weqty ;
    private float wiqty ;
    private float woqty ;
    private float wjqty ;
    private long  uamt ;
    private String wscnt ;
    private String seq ;          // TB_FPLAN_WTIME
    private String aseq ;          // TB_FPLAN_WTIME
    private String wfrdt;           // TB_FPLAN_WTIME
    private String wtrdt;           // TB_FPLAN_WTIME
    private String wopdv;           // TB_FPLAN_WTIME
    private String wdtcd;           // TB_FPLAN_WTIME
    private String wrerm;           // TB_FPLAN_WTIME
    private String wono;           //

    private String wcode;           // 불량코드
    private String wcdnm;           // 불량구분
    private float  wbqty ;

    private String wotdt;
    private long   wqty ;
    private long   qty ;
    private long   bqty ;

    private String wclscode;
    private String wrmcnm;

}

