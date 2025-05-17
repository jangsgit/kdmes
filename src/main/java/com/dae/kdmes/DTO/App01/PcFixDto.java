package com.dae.kdmes.DTO.App01;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PcFixDto {
    private int rownum;
    private int fixid;
    private String fixmachcd;
    private String reqdate;
    private String respdate;
    private String reqtext;
    private String fixtext;
    private String fixcltnm;
    private String fixflag;
    private String remark;
    private String indate;
    private String inperid;

}
