package com.example.test2.Controllers;

import com.example.test2.Controllers.Exception.ControllerException;
import com.example.test2.Service.Exception.InsertException;
import com.example.test2.Service.Exception.ServiceException;
import com.example.test2.Util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


public class BaseController {

    public static int OK=200;

    //异常统一处理
    @ExceptionHandler({ServiceException.class, ControllerException.class})
    @ResponseBody
    public JsonResult<Void> handlerException(Throwable e){
        JsonResult jsonResult=new JsonResult(e);
        if(e instanceof InsertException){
            jsonResult.setState(2000);
        }
        else {
            jsonResult.setState(500);
        }
        return jsonResult;
    }

}
