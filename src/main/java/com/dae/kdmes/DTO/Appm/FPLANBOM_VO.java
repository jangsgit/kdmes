package com.dae.kdmes.DTO.Appm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FPLANBOM_VO {
    private String custcd;          //회사 코드 .
    private String spjangcd;        //사업장코드 .
    private String plan_no;
    private String seq;
    private String pcode;
    private String pname;
    private String psize;
    private String punit;
    private String lotnodv;
    private float   qty;
    private float gqty;
    private float tqty;

}
