package com.dae.kdmes.DTO.Appm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FPLANIWORK_VO {
    private String custcd;          //회사 코드 .
    private String spjangcd;        //사업장코드 .
    private String plan_no;
    private String wseq;
    private String wflag;
    private String seq;
    private String pcode;
    private String pname;
    private String psize;
    private String punit;
    private String lotno;
    private String opcltcd;
    private String opcltnm;
    private String opcono;
    private String opdate;
    private String store;
    private String ostore;
    private String bstore;
    private String indate;
    private String inperid;
    private String wstdt;
    private String wendt;
    private String wtable;
    private String wrps;
    private float   qty;
    private float   jqty;
    private float sqty;
    private float bqty;

    private float weqty;
    private float wiqty;
    private float woqty;
    private float wjqty;

}
