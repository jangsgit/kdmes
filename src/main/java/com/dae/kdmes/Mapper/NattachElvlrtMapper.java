package com.dae.kdmes.Mapper;

import com.dae.kdmes.DTO.App01.Pc110Dto;
import com.dae.kdmes.DTO.App05ElvlrtDto;
import com.dae.kdmes.DTO.AttachDTO;

import java.util.List;

public interface NattachElvlrtMapper {
    public int InsertAttach(List<AttachDTO> attachList);

    public int deleteAttach(Pc110Dto perm);
    public int deleteAttachDetail(AttachDTO perm);
    public AttachDTO selectAttachDeteil(AttachDTO perm);

    public List<AttachDTO> selectAttachList(Pc110Dto perm);

    public int selectAttachTotalCount(String boardIdx);
}
