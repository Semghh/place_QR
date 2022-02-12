package com.example.test2.Service.PrimaryService;

import com.example.test2.POJO.Authority;
import com.example.test2.POJO.AuthorityStoreTable;

public interface AuthorityStoreTableService {

    void addAuthorityStoreTable(AuthorityStoreTable authorityStoreTable);

    public int convertToStore(Authority auth);

    public int convertToStore(Authority auth,boolean updateIfAbsent);

}
