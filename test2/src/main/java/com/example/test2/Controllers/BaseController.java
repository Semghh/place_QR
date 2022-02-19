package com.example.test2.Controllers;

import com.example.test2.Controllers.Exception.ControllerException;
import com.example.test2.POJO.AdminGroup;
import com.example.test2.Service.Exception.InsertException;
import com.example.test2.Service.Exception.ServiceException;
import com.example.test2.Util.JsonResult;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@ControllerAdvice
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

    protected void putMenuFromServletContext(AdminGroup adminGroup, HttpServletRequest request){
        ServletContext servletContext= request.getServletContext();
        HashMap<Long,AdminGroup> hashMap= (HashMap<Long, AdminGroup>) servletContext.getAttribute("menuMap");
        if(hashMap==null){
            hashMap=new HashMap<>();
        }
        hashMap.put(adminGroup.getId(),adminGroup);
    }

    protected AdminGroup getMenuFromServletContext(Long id,HttpServletRequest request){
        ServletContext servletContext= request.getServletContext();
        HashMap<Long,AdminGroup> hashMap= (HashMap<Long, AdminGroup>) servletContext.getAttribute("menuMap");
        if (hashMap==null){
            return null;
        }
        AdminGroup adminGroup=hashMap.get(id);
        return adminGroup;
    }

    protected void removeMenuFromServletContext(Long id,HttpServletRequest request){
        ServletContext servletContext= request.getServletContext();
        HashMap<Long,AdminGroup> hashMap= (HashMap<Long, AdminGroup>) servletContext.getAttribute("menuMap");
        if (hashMap==null){
            return;
        }
        hashMap.remove(id);
    }
}
