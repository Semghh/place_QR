package com.example.test2;

import com.example.test2.POJO.AdminGroup;
import com.example.test2.POJO.AdminGroupStore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {


    @Test
    public void test(){
        Calendar calendar = Calendar.getInstance();

        calendar.set(2022,0,1);

        Date time = calendar.getTime();
        System.out.println(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(time));

    }
    @Test
    public void test2(){

    }

    @Test
    public void Test01(){
        AdminGroupStore adminGroupStore=new AdminGroupStore();
        adminGroupStore.setId(1L);
        adminGroupStore.setAuthority_collection("1,2,3");
        adminGroupStore.setComment("2333");
        adminGroupStore.setGroup_name("rttr");
        AdminGroup adminGroup= (AdminGroup) storageConversion(adminGroupStore,AdminGroup.class);
        System.out.println(adminGroup);
    }

    private Object storageConversion(Object data,Class cls){
        Object value=null;
        try {
            value=cls.getDeclaredConstructor().newInstance();
            Field[] fields=data.getClass().getDeclaredFields();
            for (Field field:fields){
                String filedName=field.getName();
                Field temp= cls.getDeclaredField(filedName);
                String methodName="set"+filedName.substring(0,1).toUpperCase()+filedName.substring(1);
                Method method= cls.getDeclaredMethod(methodName,temp.getType());
                System.out.println();
                if(temp.getType()==field.getType()){
                    field.setAccessible(true);
                    method.invoke(value,field.get(data));
                }else{
                    method.invoke(value,(Object) null);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}
