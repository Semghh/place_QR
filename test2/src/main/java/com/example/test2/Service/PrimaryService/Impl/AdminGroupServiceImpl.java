package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.AdminGroupMapper;
import com.example.test2.Mapper.Primary.AdminMapper;
import com.example.test2.Mapper.Primary.AuthorityStoreTableMapper;
import com.example.test2.POJO.AdminGroup;
import com.example.test2.POJO.AdminGroupStore;
import com.example.test2.POJO.Authority;
import com.example.test2.POJO.AuthorityStoreTable;
import com.example.test2.Service.Exception.*;
import com.example.test2.Service.PrimaryService.AdminGroupService;
import com.example.test2.Util.PageInfo;
import com.example.test2.Util.PageUtil;
import com.example.test2.Util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service(value = "AdminGroupService")
public class AdminGroupServiceImpl implements AdminGroupService {

    @Resource
    private AdminGroupMapper adminGroupMapper;

    @Resource
    private AuthorityStoreTableMapper authorityStoreTableMapper;

    @Resource
    private AdminMapper adminMapper;

    @Value("${page.pageSize}")
    private Integer pageSize;

    @Override
    public void addAdminGroup(AdminGroupStore adminGroup) {
        Long[] temp= StringUtil.getArray(adminGroup.getAuthority_collection());
        for (int i=0;i<temp.length;i++){
            if(authorityStoreTableMapper.selectAuthorityStoreTableById(temp[i])==null){
                throw new AuthorityNotFoundException("权限数据不存在");
            }
        }
        int rows=adminGroupMapper.insertAdminGroup(adminGroup);
        if(rows!=1){
            throw new InsertException("插入数据未知错误");
        }
    }

    @Override
    public void removeAdminGroupById(Long id) {
        AdminGroupStore adminGroupStore=adminGroupMapper.selectAdminGroupById(id);
        if(adminGroupStore==null){
            throw new GroupNotFoundException("分组数据不存在");
        }
        Long count=adminMapper.countAdminByGroupId(id);
        if(count>0){
            throw new GroupIsUsedException("分组正在使用");
        }
        int rows=adminGroupMapper.deleteAdminGroupById(id);
        if(rows!=1){
            throw new DeleteException("删除数据时未知异常");
        }
    }

    public void changeAdminGroupById(AdminGroupStore adminGroupStore){
        AdminGroupStore adminGroupStore1=adminGroupMapper.selectAdminGroupById(adminGroupStore.getId());
        if(adminGroupStore1==null){
            throw new GroupNotFoundException("分组数据不存在");
        }
        int rows=adminGroupMapper.updateAdminGroupById(adminGroupStore);
        if(rows!=1){
            throw new UpdateException("更新数据时未知异常");
        }
    }

    public AdminGroup resultMenu(Long id){
        AdminGroupStore adminGroupStore=adminGroupMapper.selectAdminGroupById(id);
        if(adminGroupStore==null){
            throw new GroupNotFoundException("分组数据不存在");
        }
        Long[] temp=StringUtil.getArray(adminGroupStore.getAuthority_collection());
        AdminGroup adminGroup= (AdminGroup) storageConversion(adminGroupStore,AdminGroup.class);
        //AdminGroup adminGroup=adminGroupStorageConversion(adminGroupStore);
        Authority[] authority_collection=new Authority[temp.length];
        for(int i=0;i<temp.length;i++){
            authority_collection[i]=createMenu(temp[i]);
        }
        adminGroup.setAuthority_collection(authority_collection);
        return adminGroup;
    }

    private Authority createMenu(Long id){
        AuthorityStoreTable authorityStoreTable =authorityStoreTableMapper.selectAuthorityStoreTableById(id);
        if(authorityStoreTable ==null){
            throw new AuthorityNotFoundException("权限数据不存在");
        }
        Authority authority= (Authority) storageConversion(authorityStoreTable,Authority.class);
        //Authority authority =authorityStorageConversion(authorityStoreTable);
        if(authorityStoreTable.getChildren()==null|| authorityStoreTable.getChildren().equals("")){
            authority.setChildren(null);
        }else{
            Long[] temp=StringUtil.getArray(authorityStoreTable.getChildren());
            Authority[] children=new Authority[temp.length];
            for(int i=0;i< temp.length;i++){
                children[i]=createMenu(temp[i]);
            }
            authority.setChildren(children);
        }
        return authority;
    }

    private Object storageConversion(Object target,Class cls){
        Object vaule=null;
        try {
            vaule=cls.getDeclaredConstructor().newInstance();
            Field[] fields= target.getClass().getDeclaredFields();
            String fieldName=null;
            Object fieldValue=null;
            String methodName=null;
            Field clsField=null;
            Method method=null;
            for (Field field:fields){
                fieldName=field.getName();
                clsField=cls.getDeclaredField(fieldName);
                methodName="set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                method=cls.getDeclaredMethod(methodName,clsField.getType());
                if(clsField.getType()==field.getType()){
                    field.setAccessible(true);
                    fieldValue=field.get(target);
                    method.invoke(vaule,fieldValue);
                }else{
                    method.invoke(vaule,(Object) null);
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return vaule;
    }

//    private Authority authorityStorageConversion(AuthorityStoreTable authorityStoreTable){
//        Authority authority =new Authority();
//        authority.setId(authorityStoreTable.getId());
//        authority.setName(authorityStoreTable.getName());
//        authority.setUrl(authorityStoreTable.getUrl());
//        authority.setIcon(authorityStoreTable.getIcon());
//        authority.setType(authorityStoreTable.getType());
//        return authority;
//    }
//
//    private AdminGroup adminGroupStorageConversion(AdminGroupStore adminGroupStore){
//        AdminGroup adminGroup=new AdminGroup();
//        adminGroup.setGroup_name(adminGroupStore.getGroup_name());
//        adminGroup.setComment(adminGroupStore.getComment());
//        adminGroup.setId(adminGroupStore.getId());
//        adminGroup.setAuthority_collection(null);
//        return adminGroup;
//    }

    @Override
    public PageInfo<AdminGroupStore> queryByAll(Integer currentPage, Integer pageSize, Long id) {
        if(pageSize==null){
            pageSize=this.pageSize;
        }
        String queryMethodName="selectByAll";
        String countMethodName="count";
        Object[] queryParams=new Object[3];
        Object[] countParams=new Object[1];
        queryParams[0]=currentPage;
        queryParams[1]=pageSize;
        queryParams[2]=id;
        countParams[0]=id;
        Class<?>[] queryCls=new Class[3];
        Class<?>[] countCls=new Class[1];
        queryCls[0]=Integer.class;
        queryCls[1]=Integer.class;
        queryCls[2]=Long.class;
        countCls[0]=Long.class;
        PageInfo<AdminGroupStore> pageInfo= PageUtil.listPage(adminGroupMapper,queryMethodName,queryCls,queryParams,countMethodName,countCls,countParams);
        return pageInfo;
    }
}
