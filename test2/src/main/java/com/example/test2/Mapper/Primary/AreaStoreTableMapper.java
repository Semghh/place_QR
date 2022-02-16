package com.example.test2.Mapper.Primary;


import com.example.test2.POJO.AreaStoreTable;
import org.apache.ibatis.annotations.Mapper;

public interface AreaStoreTableMapper {

    public AreaStoreTable getAreaById(Long area_id);

    public int isExistArea(Long area_id);

    public int insertNewAreaStore(AreaStoreTable store);

    public Long getLastId();

    public int dynamicUpdate(AreaStoreTable storeTable);
}
