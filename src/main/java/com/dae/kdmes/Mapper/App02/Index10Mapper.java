package com.dae.kdmes.Mapper.App02;

import com.dae.kdmes.DTO.App01.Index03Dto;
import com.dae.kdmes.DTO.App02.Index10Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Index10Mapper {

    public List<Index10Dto> getFplanList(Index10Dto parm) ;

    public List<Index10Dto> GetFplanList(Index10Dto parm) ;

    public Boolean DeleteFplan(Index10Dto parm) ;

    public Boolean InsertFplan(Index10Dto  parm) ;
    public Boolean UpdateFplan(Index10Dto  parm) ;

    public String GetFplanCheck(Index10Dto  parm) ;
    public List<Index10Dto> GetJpumListTot(Index10Dto parm) ;

}
