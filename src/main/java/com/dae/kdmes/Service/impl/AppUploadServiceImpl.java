package com.dae.kdmes.Service.impl;

import com.dae.kdmes.DTO.App01.Pc110Dto;
import com.dae.kdmes.DTO.App05ElvlrtDto;
import com.dae.kdmes.DTO.AttachDTO;
import com.dae.kdmes.Mapper.NattachElvlrtMapper;
import com.dae.kdmes.util.FilsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AppUploadServiceImpl implements  AppUploadService {

    @Autowired
    private NattachElvlrtMapper attachMapper;

    @Autowired
    private FilsUtils fileUtils;

    public boolean registerMNotice(App05ElvlrtDto params, List<AttachDTO> attachDto){
        int queryResult = 1;

        if (CollectionUtils.isEmpty(attachDto) == false) {
            queryResult = attachMapper.InsertAttach(attachDto);
            if(queryResult < 1){
                queryResult = 0;
            }
        }
       return (queryResult > 0);
    }
    public boolean registerMNoticeDel(Pc110Dto parm){
        int queryResult = 1;
        queryResult = attachMapper.deleteAttach(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }
    public boolean MNoticeFileDel(AttachDTO parm){
        int queryResult = 1;
        queryResult = attachMapper.deleteAttachDetail(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }

    public List<AttachDTO> MNoticeFilelist(Pc110Dto perm){
        List<AttachDTO> attachDto = attachMapper.selectAttachList(perm);
        return attachDto;
    }

    public AttachDTO selectAttachDeteil(AttachDTO parm){
        AttachDTO attachDto = attachMapper.selectAttachDeteil(parm);
        return attachDto;
    }

    @Override
    public boolean registerMNotice(App05ElvlrtDto params, MultipartFile[] files) {
        return false;
    }
}
