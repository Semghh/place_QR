package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.AuthorityStoreTableMapper;
import com.example.test2.POJO.Authority;
import com.example.test2.POJO.AuthorityStoreTable;
import com.example.test2.Service.Exception.AuthorityNotFoundException;
import com.example.test2.Service.Exception.InsertException;
import com.example.test2.Service.PrimaryService.AuthorityStoreTableService;
import com.example.test2.Util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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

    @Override
    public int convertToStore(Authority auth){
        return convertToStore(auth,true);
    }

    @Override
    public int convertToStore(Authority auth, boolean updateIfAbsent) {
        ConvertToStore tool = new ConvertToStore(auth, authorityStoreTableMapper);
        int convert = tool.convert(updateIfAbsent);
        return convert;
    }

    //将Authority内存结构，转化为存储结构，内部工具类
    static class ConvertToStore {

        private Authority authority;
        private int times=0;
        private AuthorityStoreTableMapper authorityStoreTableMapper;

        public ConvertToStore(Authority authority, AuthorityStoreTableMapper authorityStoreTableMapper) {
            this.authority = authority;
            this.authorityStoreTableMapper = authorityStoreTableMapper;
        }

        public int convert(boolean updateIfAbsent){
            times=0;
            recursion(authority,updateIfAbsent);
            return times;
        }

        private Long recursion(Authority cur, boolean updateIfAbsent){
            AuthorityStoreTable storeTable = new AuthorityStoreTable();
            storeTable.setId(cur.getId());
            storeTable.setIcon(cur.getIcon());
            storeTable.setName(cur.getName());
            storeTable.setUrl(cur.getUrl());
            storeTable.setType(cur.getType());

            Authority[] children = cur.getChildren();
            if (children==null || children.length==0){
                invoke(storeTable,updateIfAbsent);
                return cur.getId();
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < children.length;i++) {
                Long l = recursion(children[i],updateIfAbsent);
                sb.append(l);
                sb.append(",");
            }
            String ch = sb.deleteCharAt(sb.length() - 1).toString();
            storeTable.setChildren(ch);
            invoke(storeTable,updateIfAbsent);
            return cur.getId();
        }

        //操作判断
        private int invoke(AuthorityStoreTable storeTable, boolean updateIfExist){
            if (authorityStoreTableMapper.isExistTableById(storeTable.getId())==1) {
                if (updateIfExist){
                    int i = authorityStoreTableMapper.dynamicUpdate(storeTable);
                    if (i==1)times++;
                    return i;
                }
                return 0;
            }
            int i = authorityStoreTableMapper.insertIncludeId(storeTable);
            if (i==1)times++;
            return i;
        }



    }
}
