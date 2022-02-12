package com.example.test2.Util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonResult<T> {

    private Integer state;
    private String message;
    private T data;

    public JsonResult(Integer state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public JsonResult(Throwable e){
        this.message=e.getMessage();
    }

    public JsonResult(Integer state,T data){
        this.state=state;
        this.data=data;
    }

    public static<T> JsonResult getInstance(Integer state, String message, T data){
        return new JsonResult(state,message,data);
    }

    public static<T> JsonResult getInstance(Integer state, Throwable e, T data){
        return new JsonResult(state,e.getMessage(),data);
    }

    public JsonResult(Integer state){
        this.state=state;
    }
}
