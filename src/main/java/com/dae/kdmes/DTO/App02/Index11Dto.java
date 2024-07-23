package com.dae.kdmes.DTO.App02;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Getter
@Setter
public class Index11Dto {
    private String indate;
    private String prod_sdate;
    private String prod_edate;
    private String ecltnm;
    private String wflagnm;
    private String statuscd;
    private String wrmcnm;
    private String rwflagnm;
    private String lotno;
    private String glotno;
    private String glotnono;
    private String jpum;
    private String jchajong;
    private String jgugek;
    private Integer prod_qty;
    private Integer winqt;
    private Integer wotqt;
    private Integer wbdqt;
    private Integer wjqty;
    private Integer gumqty;

    private String GAPTIME;

    private String frdate;
    private String todate;
    private String wrpsnm;
    private String wrps;
    private String gumwrpsnm;
    private String wfrdt;
    private String wtrdt;
    private String ginputdate;
    private String wbdnm;
    private String wcode;
    private String partcode;


    private String deldate;
    private String delnum;
    private String delseq;
    private String inmonth;
    private String inweeks;
    private String wrerm;
    private float workhr;

    private String cltcd;
    private String pcode;
    private String yearstr;
    private String monthstr;
    private float wbdyul;


}
