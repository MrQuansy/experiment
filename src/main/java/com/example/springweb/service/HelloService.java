package com.example.springweb.service;

import com.example.springweb.dao.App;
import com.example.springweb.dao.HelloUser;
import com.example.springweb.mapper.HelloMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HelloService {
    private HelloUser currentUser;
    @Resource
    private HelloMapper helloMapper;

    public HelloUser getCurrentUser(){return currentUser;}
    public void setCurrentUser(HelloUser user){currentUser=user;}
    public List<HelloUser> getUserList() {
        List<HelloUser> list = helloMapper.findAll();
        return list;
    }

    public void InsertUser(Map<String, String> params){
        ObjectMapper objectMapper = new ObjectMapper();
        HelloUser helloUser = objectMapper.convertValue(params, HelloUser.class);
        helloMapper.insert(helloUser);
    }


    public HelloUser getOne(String id){
        //HelloUser result = new HelloUser();
        HelloUser result = helloMapper.getOne(id);
        if (result==null)
        {
            result=new HelloUser();//索引为空的时候，返回null，需要这时候对其getId,getName就会出错。
        }
        System.out.println(result.toString());
        return result;
    }

    public void UpdateByID(Map<String, String> params){
        String id = params.get("id");
        HelloUser temp = helloMapper.getOne(id);
        System.out.println(temp.toString());
        if(params.get("name")!=null)
            temp.setName(params.get("name"));
        if(params.get("password")!=null)
            temp.setPassword((params.get("password")));
        helloMapper.updateByID(temp);
    }


    public void DeleteByID(String id){
        helloMapper.deleteByID(id);
        System.out.println("AfterDelete:"+helloMapper.getOne(id));
    }

    public boolean CheckPassword(String id,String password){
        HelloUser PassUser=helloMapper.getPassword(id);
        if(PassUser==null) return false;
        return PassUser.getPassword().equals(password);
    }

    public void InsertApp(Map<String, String> params){
        App helloApp=new App();
        helloApp.setAppName(params.get("AppName"));
        helloApp.setUserId(params.get("userId"));
        helloApp.setapplyId(String.valueOf(helloMapper.getallApp(helloApp.getUserId()).size()));
        helloApp.setAppType(params.get("AppType"));
        helloApp.setsafeLevel(params.get("safeLevel"));
        helloApp.setCreateDate(params.get("CreateDate"));
        helloApp.setState("未审核");
        //System.out.println(helloApp.toString());
        helloMapper.insertAppInfo(helloApp);
    }

    public List<App> getAppList(String id) {
        List<App> list = helloMapper.getallApp(id);
        return list;
    }

}
