package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.AuthorityStoreTableMapper;
import com.example.test2.POJO.AuthorityStoreTable;
import com.example.test2.Service.Exception.AuthorityNotFoundException;
import com.example.test2.Service.Exception.InsertException;
import com.example.test2.Service.PrimaryService.AuthorityStoreTableService;
import com.example.test2.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AuthorityStoreTableService")
public class AuthorityStoreTableServiceImpl implements AuthorityStoreTableService {

    @Resource
    private AuthorityStoreTableMapper authorityStoreTableMapper;

    @Override
    public void addAuthorityStoreTable(AuthorityStoreTable authorityStoreTable) {
        Long[] temp= StringUtil.getArray(authorityStoreTable.getChildren());
        for(int i=0;i<temp.length;i++){
            if(authorityStoreTableMapper.selectAuthorityStoreTableById(temp[i])==null){
                throw new AuthorityNotFoundException("权限数据不存在");
            }
        }
        int rows=authorityStoreTableMapper.insertAuthorityStoreTable(authorityStoreTable);
        if(rows!=1){
            throw new InsertException("插入数据未知错误");
        }
    }
}
