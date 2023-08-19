package com.dae.kdmes.Service.App01;

import com.dae.kdmes.DTO.App01.Index03Dto;
import com.dae.kdmes.Mapper.App01.Index03Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dae.kdmes.DTO.Popup.PopupDto;

import java.util.List;

@RequiredArgsConstructor
@Service("Index03Service")
public class Index03Service {
    @Autowired
    Index03Mapper appMapper;

    public List<Index03Dto> GetJpumList(Index03Dto parm){
                return  appMapper.GetJpumList(parm);
    }
    public List<Index03Dto> GetJpumListTot(Index03Dto parm){
        return  appMapper.GetJpumListTot(parm);
    }
    public List<Index03Dto> GetGanListBonsa01(Index03Dto parm){
        return  appMapper.GetGanListBonsa01(parm);
    }
    public List<Index03Dto> GetGanListBonsa02(Index03Dto parm){
        return  appMapper.GetGanListBonsa02(parm);
    }
    public List<Index03Dto> GetJcustomCode(Index03Dto parm){
        return  appMapper.GetJcustomCode(parm);
    }
    public List<Index03Dto> GetJcustomCodeTot(Index03Dto parm){
        return  appMapper.GetJcustomCodeTot(parm);
    }
    public List<Index03Dto> GetJpumCustList(Index03Dto parm){
        return  appMapper.GetJpumCustList(parm);
    }
    public List<Index03Dto> GetJpumModelList(Index03Dto parm){
        return  appMapper.GetJpumModelList(parm);
    }
    public List<Index03Dto> GetJpumCustJaegoList(Index03Dto parm){
        return  appMapper.GetJpumCustJaegoList(parm);
    }
    public Index03Dto GetJpumOrderJkey(Index03Dto parm){
        return  appMapper.GetJpumOrderJkey(parm);
    }

    public List<PopupDto> getj1_keyList(PopupDto parm){
        return  appMapper.getj1_keyList(parm);
    }
    public List<PopupDto> getj2_keyList(PopupDto parm){
        return  appMapper.getj2_keyList(parm);
    }




    public Boolean InsertJpum(Index03Dto parm){ return  appMapper.InsertJpum(parm);}
    public Boolean UpdateJpum(Index03Dto parm){  return  appMapper.UpdateJpum(parm);  }
    public Boolean DeleteJpum(Index03Dto parm){  return  appMapper.DeleteJpum(parm);  }
    public String GetJpumCheck(Index03Dto parm){  return  appMapper.GetJpumCheck(parm);  }



    public List<Index03Dto> SelectJegoIpgo(Index03Dto parm){
        return  appMapper.SelectJegoIpgo(parm);
    }
    public Boolean InsertJegoIpgo(Index03Dto parm){ return  appMapper.InsertJegoIpgo(parm);}
    public Boolean DeleteJaegoIpgo(Index03Dto parm){ return  appMapper.DeleteJaegoIpgo(parm);}

    public List<Index03Dto> SelectJegoList(Index03Dto parm){
        return  appMapper.SelectJegoList(parm);
    }


}
