package com.dae.kdmes.DTO.Cms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Getter
@Setter
public class CmsIndex01Dto {
    private Integer idx;
    private String machine_name;
    private String Additional_Info_1;
    private String Additional_Info_2;
    private String shottime;
    private String shotdate;
    private String frdate;
    private String todate;
    private Timestamp TimeStamp;
    private Timestamp STARTTIME;
    private Timestamp ENDTIME;
    private Integer Shot_Number;
    private Integer wotqty;
    private Integer Cavity;
    private float  Injection_Time;
    private float  Filling_Time;
    private float  Plasticizing_Time;
    private float  Cycle_Time;
    private float  Barrel_Temperature_1;
    private float  Barrel_Temperature_2;
    private float  Mold_Temperature_1;
    private float  Mold_Temperature_2;
    private float  Mold_Temperature_3;
    private float  Mold_Temperature_4;
    private float  Mold_Temperature_5;
    private float  Mold_Temperature_6;
    private float  Mold_Temperature_7;
    private float  Mold_Temperature_8;
    private float  Mold_Temperature_9;
    private float  Mold_Temperature_10;
    private float  Mold_Temperature_11;
    private float  Mold_Temperature_12;
    private float  MT01;
    private float  MT02;
    private float  MT03;
    private float  MT04;
    private float  MT05;
    private float  MT06;

}
