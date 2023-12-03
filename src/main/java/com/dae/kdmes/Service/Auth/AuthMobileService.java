package com.dae.kdmes.Service.Auth;

import com.dae.kdmes.DTO.UserFormDto;
import com.dae.kdmes.Mapper.PDA.KdpdaDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("AuthMobileService")
public class AuthMobileService {
    @Autowired
    KdpdaDBMapper kdpdaMapper;

    public UserFormDto GetUserInfoPDA(UserFormDto parm){
         return kdpdaMapper.GetUserInfoPDA(parm);
     }

 }





