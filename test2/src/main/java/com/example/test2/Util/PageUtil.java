package com.example.test2.Util;

import com.example.test2.POJO.LogAdminLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class PageUtil<T> {

    public static PageInfo listPage(Object target,String queryMethodName,
                                    Class<?>[] queryCls,
                                    Object[] queryParams,
                                    String countMethodName,
                                    Class<?>[] countCls,
                                    Object[] countParams) {
        Method method= null;
        ArrayList<Object> list=null;
        PageInfo<Object> pageInfo=null;
        try {
            Integer pageSize= (Integer) queryParams[1];
            Integer currentPage= (Integer) queryParams[0];
            queryParams[0]=(currentPage-1)*pageSize;
            method = target.getClass().getDeclaredMethod(queryMethodName,queryCls);
            list = (ArrayList<Object>) method.invoke(target,queryParams);
            pageInfo=new PageInfo<>();
            pageInfo.setList(list);
            pageInfo.setPageSize(pageSize);
            pageInfo.setCurrentPage(currentPage);
            method=target.getClass().getDeclaredMethod(countMethodName,countCls);
            Long totalSize= (Long) method.invoke(target,countParams);
            Integer totalPage= Math.toIntExact(totalSize % pageSize == 0 ? (totalSize / pageSize) : ((totalSize / pageSize) + 1));
            pageInfo.setTotalPage(totalPage);
            pageInfo.setTotalSize(totalSize);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}
