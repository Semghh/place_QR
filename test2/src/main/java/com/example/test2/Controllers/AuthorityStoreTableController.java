package com.example.test2.Controllers;

import com.example.test2.Controllers.Exception.ParameIsNullException;
import com.example.test2.POJO.AuthorityStoreTable;
import com.example.test2.Service.PrimaryService.AuthorityStoreTableService;
import com.example.test2.Util.JsonResult;
import com.example.test2.Util.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/AuthorityStoreTable")
public class AuthorityStoreTableController extends BaseController{

    @Resource
    private AuthorityStoreTableService authorityStoreTableService;

    @PostMapping(value = "/addAuthorityStoreTable")
    public JsonResult<Void> addAuthorityStoreTable(AuthorityStoreTable authorityStoreTable){
        if(authorityStoreTable.getChildren()==null|| authorityStoreTable.getChildren().equals("")){
            authorityStoreTable.setChildren("-1");
        }
        if(!ParameterUtil.parameterCheck(authorityStoreTable)){
            throw new ParameIsNullException("指定参数不能为空");
        }
        if(authorityStoreTable.getChildren().equals("-1")){
            authorityStoreTable.setChildren(null);
        }
        authorityStoreTableService.addAuthorityStoreTable(authorityStoreTable);
        return new JsonResult<>(OK);
    }

}
