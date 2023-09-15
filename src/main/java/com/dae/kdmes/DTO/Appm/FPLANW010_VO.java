package com.dae.kdmes.DTO.Appm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FPLANW010_VO {
    private String custcd;          //회사 코드 .
    private String spjangcd;        //사업장코드 .
    private String plan_no;
    private String wseq;
    private String aseq;
    private String seq;
    private String wflag;
    private String wfrdt;
    private String wtrdt;
    private String wstdt;
    private String wendt;
    private String wrmc;
    private float   winqt;
    private float wotqt;
    private float wqcqt;
    private float wbdqt;
    private float wscnt;
    private Integer winps;
    private float wsyul;
    private float prod_qty;
    private float end_qty;
    private String wrps;
    private String wremark;
    private String decision;
    private String decision1;
    private String decision2;
    private String decision3;
    private String decision4;
    private String indate;
    private String inperid;       //사업장코드 .

    private String wono;
    private String cltcd;
    private String cltnm;
    private String pcode;
    private String pname;
    private String psize;
    private String wrpsnm;
    private String ostore;
    private String lotno;
    private String store;
    private String opcltcd;
    private String opdate;
    private String phmmdoc;
    private String wotdt;
    private String workdv;

    private float wokqt;
    private float jqty;
    private float wqty;
    private float qty;
    private float bqty;
    private float sqty;

    private String wtable;


    private String machname;
    private String crudflag;
    private String clsflag;
    private String partcode;
}
