package com.dae.kdmes.Mapper.master;

import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.DTO.TBXLoginDTO;
import com.dae.kdmes.DTO.TBXa012VO;
import com.dae.kdmes.DTO.TBXuserMenuDTO;
import com.dae.kdmes.DTO.UserFormDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthDBMapper {
    public void TBXUSERS_Insert(UserFormDto userinfo);
    public UserFormDto GetUserInfo(UserFormDto userinfo);
    public UserFormDto GetUserInfoDto2(UserFormDto userinfo);

    public UserFormDto GetCustInfo(UserFormDto userinfo);
    public UserFormDto GetAdminInfo(UserFormDto userinfo);

    public UserFormDto GetUserInfoDto(UserFormDto userinfo);

    public String GetClientInfo(UserFormDto userinfo);
    public String GetClientInfoName(UserFormDto userinfo);

    public void UpdateDbInfo(UserFormDto userinfo);

    public String TB_XUSER_DUPCHK(UserFormDto PARM);

    public String TB_XUSER_PHDUPCHK(UserFormDto PARM);

    public String TB_XCLIENT_SELECT(UserFormDto PARM);
    public String TB_XUSER_DBNM(UserFormDto parm);

    public void TB_XLOGIN_INSERT(UserFormDto PARM);
    public void TB_XLOGOUT_INSERT(UserFormDto PARM);

    public void TB_XUSERS_LOGFAIL(UserFormDto Parm);

    public void TB_XUSERS_LOGSUCC(UserFormDto Parm);

    public void TB_XUSER_UPDATE(UserFormDto Parm);


    public List<UserFormDto> GetUserListDto(UserFormDto userinfo);
    public List<TBXLoginDTO> GetLogListDto(UserFormDto userinfo);
    public List<TBXuserMenuDTO> GetXusersMenuList(TBXuserMenuDTO Parm);
    public List<TBXuserMenuDTO> GetXMenuList(TBXuserMenuDTO Parm);

    public int UpdateUserInfo(UserFormDto Parm);
    public int UpdateUserMenuInfo(TBXuserMenuDTO Parm);

    public int Updatepushid(UserFormDto Parm);
    public String TB_GET_PUSHID(PopupDto Parm);


    public int TB_XUSERS_INSERT(UserFormDto Parm);


    public TBXa012VO GetXa012Info(PopupDto parm);

}
