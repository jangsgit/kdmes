package com.dae.kdmes.Service.App02;

import com.dae.kdmes.DTO.App01.*;
import com.dae.kdmes.DTO.App02.Index10Dto;
import com.dae.kdmes.DTO.Appm.FPLANW010_VO;
import com.dae.kdmes.DTO.Popup.PopupDto;
import com.dae.kdmes.Mapper.App02.Index10Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("Index10Service")
public class Index10Service {

    @Autowired
    Index10Mapper indexMapper;


    public List<Index10Dto> GetFplanList(Index10Dto parm){
        return indexMapper.GetFplanList(parm);
    }
    public String SelectMaxLotno(Index10Dto parm){
        return indexMapper.SelectMaxLotno(parm);
    }


    public Boolean DeleteFplan(Index10Dto parm){  return  indexMapper.DeleteFplan(parm);  }

    public Boolean InsertFplan(Index10Dto parm){ return  indexMapper.InsertFplan(parm);}

    public Boolean InsertFplanCopy(Index10Dto parm){ return  indexMapper.InsertFplanCopy(parm);}

    public Boolean UpdateFplan(Index10Dto parm){  return  indexMapper.UpdateFplan(parm);  }


    public List<IndexCa613Dto> SelectCa613List(IndexCa613Dto parm){
        return indexMapper.SelectCa613List(parm);
    }
    public List<IndexCa613Dto> SelectDa037List(IndexCa613Dto parm){
        return indexMapper.SelectDa037List(parm);
    }
    public List<IndexCa613Dto> SelectDa037ListBarcd(IndexCa613Dto parm){
        return indexMapper.SelectDa037ListBarcd(parm);
    }
    public List<IndexCa613Dto> SelectDa037ListJiyuk(IndexCa613Dto parm){
        return indexMapper.SelectDa037ListJiyuk(parm);
    }


    public List<IndexCa611Dto> SelectDa036List(IndexCa611Dto parm){
        return indexMapper.SelectDa036List(parm);
    }

    public List<IndexCa613Dto> SelectCa613ListMapChul(IndexCa613Dto parm){
        return indexMapper.SelectCa613ListMapChul(parm);
    }


    public List<IndexCa613OworkDto> SelectCa613ChulList(IndexCa613OworkDto parm){
        return indexMapper.SelectCa613ChulList(parm);
    }
    public List<IndexCa613Dto> SelectDa037ChulList(IndexCa613Dto parm){
        return indexMapper.SelectDa037ChulList(parm);
    }



    public IndexCa613OworkDto SelectCa613ChulListSum(IndexCa613OworkDto parm){
        return indexMapper.SelectCa613ChulListSum(parm);
    }
    public List<IndexCa613Dto> SelectCa613JaegoList(IndexCa613Dto parm){return indexMapper.SelectCa613JaegoList(parm);}
    public List<IndexCa609Dto> SelectCa609List(IndexCa609Dto parm){return indexMapper.SelectCa609List(parm);}


    public String GetJcodeCheck(Index03Dto parm){  return  indexMapper.GetJcodeCheck(parm);  }
    public String GetCltcdCheck(Index02Dto parm){  return  indexMapper.GetCltcdCheck(parm);  }


    public Boolean DeleteCa613(IndexCa613Dto parm){  return  indexMapper.DeleteCa613(parm);  }
    public Boolean InsertCa613(IndexCa613Dto parm){ return  indexMapper.InsertCa613(parm);}
    public Boolean DeleteDA036Sch(IndexCa611Dto parm){  return  indexMapper.DeleteDA036Sch(parm);  }
    public Boolean DeleteDa037(IndexCa611Dto parm){  return  indexMapper.DeleteDa037(parm);  }
    public Boolean UpdateCa609Chul(IndexCa611Dto parm){  return  indexMapper.UpdateCa609Chul(parm);  }
    public Boolean InsertDA036Sch(IndexCa611Dto parm){ return  indexMapper.InsertDA036Sch(parm);}
    public Boolean InsertDa037(IndexCa613Dto parm){ return  indexMapper.InsertDa037(parm);}
    public Boolean UpdateCa613(IndexCa613Dto parm){  return  indexMapper.UpdateCa613(parm);  }
    public Boolean UpdateDa036(IndexCa611Dto parm){  return  indexMapper.UpdateDa036(parm);  }
    public Boolean UpdateDa037(IndexCa613Dto parm){  return  indexMapper.UpdateDa037(parm);  }
    public Boolean InsertCA608(IndexCa608Dto parm){ return  indexMapper.InsertCA608(parm);}
    public Boolean InsertCA609(IndexCa609Dto parm){ return  indexMapper.InsertCA609(parm);}
    public Boolean DeleteCA608(IndexCa608Dto parm){ return  indexMapper.DeleteCA608(parm);}
    public Boolean DeleteCA609(IndexCa609Dto parm){ return  indexMapper.DeleteCA609(parm);}
    public Boolean InsertChulha(IndexCa609Dto parm){ return  indexMapper.InsertChulha(parm);}
    public String SelectMaxIbgnum(String parm){
        return  indexMapper.SelectMaxIbgnum(parm);
    }
    public String SelectMaxDelnum(String parm){
        return  indexMapper.SelectMaxDelnum(parm);
    }
    public String SelectMaxBalnum(String parm){
        return  indexMapper.SelectMaxBalnum(parm);
    }
    public Integer SelectCheckBalnum(IndexCa609Dto parm){
        return  indexMapper.SelectCheckBalnum(parm);
    }
    public String CA613_OWORK_MAXWSEQ(IndexCa613OworkDto parm){ return indexMapper.CA613_OWORK_MAXWSEQ(parm);}
    public Boolean CA613_OWORK_Insert(IndexCa613OworkDto parm){ return  indexMapper.CA613_OWORK_Insert(parm);}
    public Boolean CA613_OWORK_Update(IndexCa613OworkDto parm){  return  indexMapper.CA613_OWORK_Update(parm);  }
    public Boolean CA613_OWORK_Delete(IndexCa613OworkDto parm){  return  indexMapper.CA613_OWORK_Delete(parm);  }

    public String GetFplanCheck(Index10Dto parm){  return  indexMapper.GetFplanCheck(parm);  }
    public List<Index10Dto> GetJpumListTot(Index10Dto parm){
        return  indexMapper.GetJpumListTot(parm);
    }
    public List<Index10Dto> GetCifListTot(Index10Dto parm){
        return  indexMapper.GetCifListTot(parm);
    }

    /** 사원 리스트 */
    public List<Index10Dto> GetInsaList(Index10Dto parm){
        return  indexMapper.GetInsaList(parm);
    }

    public List<Index01Dto> GetIstorelist(Index01Dto parm){
        return  indexMapper.GetIstorelist(parm);
    }

    public List<Index01Dto> Getostorelist(Index01Dto parm){
        return  indexMapper.GetOstorelist(parm);
    }

    public String SelectCheckIndate(Index10Dto parm){
        return  indexMapper.SelectCheckIndate(parm);
    }

    public String SelectMaxSeq(String parm){
        return  indexMapper.SelectMaxSeq(parm);
    }

    public String SelectMaxLot(Index10Dto parm){
        return  indexMapper.SelectMaxLot(parm);
    }

    public List<PopupDto> getCls_flagList(PopupDto parm){
        return  indexMapper.getCls_flagList(parm);
    }
}
