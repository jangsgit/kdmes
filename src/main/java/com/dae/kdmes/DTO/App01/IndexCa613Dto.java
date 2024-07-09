package com.dae.kdmes.DTO.App01;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IndexCa613Dto {
    private String cltcd;
    private String ibgdate;
    private String ibgnum;
    private String ibgseq;
    private String deldate;
    private String delnum;
    private String delseq;
    private String schdate;
    private String pcode;
    private String pname;
    private String psize;
    private String punit;
    private Integer qty;
    private Integer cqty;
    private Integer uamt;
    private Integer samt;
    private Integer tamt;
    private Integer mamt;
    private Integer janqty;
    private String indate;
    private String inperid;
    private String frdate;
    private String todate;
    private String acorp;
    private String perid;
    private String pernm;
    private String inname;
    private Integer rownum;
    private String lotno;
    private String istore;
    private String ostore;
    private String remark;
    private String balno;
    private String inmonth;
    private String inweeks;
    private String demflag;
    private Integer boxnum;
    private List<IndexCa613OworkDto> oworks;

}
