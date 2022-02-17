package com.example.test2.Util;

import java.lang.reflect.Field;
import java.util.List;

public class ParameterUtil {

    public static Boolean parameterCheck(List<Object> data){
        for (Object o : data){
            if(o==null){
                return false;
            }
        }
        return true;
    }

    public static Boolean parameterCheck(Object o)  {
        Field[] fields=o.getClass().getDeclaredFields();
        try {
            for (Field field:fields){
                field.setAccessible(true);
                if(field.get(o)==null){
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Object[] setParams(List params){
        Object[] res=new Object[params.size()];
        for (int i = 0; i < params.size(); i++) {
            res[i]=params.get(i);
        }
        return res;
    }
}
