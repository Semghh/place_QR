package com.example.test2.Util;

import java.util.HashMap;

public class ParamGet {

    public static Object[] fromHashMap(String[] paramName, Class[] clazType, HashMap reqBody,Boolean throwIfAbsent) throws paramException {
        if (!checkNumberEqual(paramName,clazType)) return null;
        if (!checkParamEnough(paramName,reqBody)) return null;

        Object[] res = new Object[paramName.length];
        for (int i = 0; i < paramName.length; i++) {

            String name = paramName[i];
            Class clz = clazType[i];
            Object o = reqBody.get(name);
            if (o==null){
                if (throwIfAbsent) throw new paramException("参数"+name+"缺失");
                continue;
            }
            if (o.getClass()==clz){
                res[i] = o;
                continue;
            }
            if (o.getClass()==String.class){
                res[i]=paresFromString((String)o,clz);
            }

        }
        return res;
    }

    private static Object paresFromString(String s,Class claz){

        if (claz == Integer.class)
            return Integer.parseInt(s);
        else if (claz==Long.class)
            return Long.parseLong(s);
        else if (claz==Float.class)
            return Float.parseFloat(s);
        else if (claz==Double.class)
            return Double.parseDouble(s);
        else if (claz==Boolean.class)
            return Boolean.parseBoolean(s);
        return null;
    }

    public static class paramException extends Exception{

        public paramException(String message) {
            super(message);
        }
    }

    private static boolean checkNumberEqual(String[] p,Class[] clazType){
        return p.length== clazType.length;
    }

    private static boolean checkParamEnough(String[] p,HashMap reqBody){
        return reqBody.size()>=p.length;
    }
}
