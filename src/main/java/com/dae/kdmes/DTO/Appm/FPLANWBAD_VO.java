package com.dae.kdmes.DTO.Appm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FPLANWBAD_VO {
    private String custcd;
    private String spjangcd;
    private String plan_no;
    private String wseq;
    private String seq;
    private String wflag;
    private String wclscode;
    private String wcode;
    private float wbqty;
    private float prod_qty;
    private String indate;
    private String inperid;
    private String pcode;
}
