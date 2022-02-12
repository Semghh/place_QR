package com.example.test2.Mapper.Primary;

import com.example.test2.POJO.AuthorityStoreTable;
import org.apache.ibatis.annotations.Param;

public interface AuthorityStoreTableMapper {

    public int insertAuthorityStoreTable(AuthorityStoreTable authorityStoreTable);

    public AuthorityStoreTable selectAuthorityStoreTableById(@Param("id") Long id);

    public int isExistTableById(Long id);

    public int dynamicUpdate(AuthorityStoreTable storeTable);

    public int insertIncludeId(AuthorityStoreTable storeTable);

}
