package com.actas.kdmes.DTO.Elvlrt;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class ElvlrtModel {private int id;
    @NonNull @Builder.Default private String name = "NULL NAME";
    @NonNull @Builder.Default private String email = "NULL EMAIL";
}
