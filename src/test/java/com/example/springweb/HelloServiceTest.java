package com.example.springweb;

import com.example.springweb.dao.App;
import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Autowired
    HelloService helloService;
    @Test
    public void getUserstest1() {
        List<HelloUser> users = helloService.getUserList();
        System.out.println(users.size());
        assertNotNull("User not null", users);
        assertNotEquals(users.size(), 0);
        assertEquals(users.get(0).getId(), "17186064");
        assertEquals(users.get(1).getId(), "171860670");
        assertEquals(users.get(2).getId(), "171860671");
        assertEquals(users.get(3).getId(), "171860672");
        assertEquals(users.get(0).getName(),"xpf");
        assertEquals(users.get(0).getPassword(),"123456");
    }

    @Test
    public void getUserstest2() {
        Map<String,String> params=new HashMap<>();
        params.put("id","171860663");
        params.put("name","msc");
        params.put("password","yes");
        helloService.InsertUser(params);
        List<HelloUser> users = helloService.getUserList();
        System.out.println(users.size());
        assertNotNull("User not null", users);
        assertEquals(users.size(), 5);
    }


    @Test
    public void testInsert1() throws Exception{
        int size=helloService.getUserList().size();
        Map<String,String> params=new HashMap<>();
        params.put("id","4");
        params.put("name","niha");
        params.put("password","yes");
        helloService.InsertUser(params);
        assertEquals(helloService.getUserList().size(),size+1);
    }

    @Test
    public void testInsert2() throws Exception{
        int size=helloService.getUserList().size();
        Map<String,String> params=new HashMap<>();
        params.put("id","11111");
        params.put("name","ana");
        params.put("password","abc");
        helloService.InsertUser(params);
        assertEquals(helloService.getUserList().size(),size+1);
    }

    @Test
    public void testGetOne1() throws Exception{
        HelloUser helloUser = helloService.getOne("171860671");
        assertEquals(helloUser.getName(),"Mr.quan");
        assertEquals(helloUser.getId(),"171860671");
        assertEquals(helloUser.getPassword(),"carnegie2021");
    }

    @Test
    public void testGetOne2() throws Exception{
        HelloUser helloUser = helloService.getOne("9");
        assertNull(helloUser.getName());
        assertNull(helloUser.getId());
        assertNull(helloUser.getPassword());
    }

    @Test
    public void testUpdate1() throws Exception{
        Map<String,String> params=new HashMap<>();
        params.put("id","171860671");
        params.put("name","yyy");
        helloService.UpdateByID(params);
        assertEquals(helloService.getOne("171860671").getName(),"yyy");
        assertEquals(helloService.getOne("171860671").getPassword(),"carnegie2021");
    }

    @Test
    public void testUpdate2() throws Exception{
        Map<String,String> params=new HashMap<>();
        params.put("id","171860672");
        params.put("password","PKU");
        helloService.UpdateByID(params);
        assertEquals(helloService.getOne("171860672").getName(),"TangJinlin");
        assertEquals(helloService.getOne("171860672").getPassword(),"PKU");
    }

    @Test
    public void testDelete1() throws Exception{
        int size=helloService.getUserList().size();
        helloService.DeleteByID("4");
        assertEquals(helloService.getUserList().size(),size-1);
    }

    @Test
    public void testDelete2() throws Exception{
        int size=helloService.getUserList().size();
        helloService.DeleteByID("000");
        assertEquals(helloService.getUserList().size(),size);
    }

    @Test
    public void testCheckPassword1() {
        boolean res=helloService.CheckPassword("171860671","carnegie2021");
        assertEquals(res,true);
    }
    @Test
    public void testCheckPassword2() {
        boolean res=helloService.CheckPassword("171860671","carnegie");
        assertEquals(res,false);
    }
    @Test
    public void testCheckPassword3() {
        boolean res=helloService.CheckPassword("17186067","carnegie2021");
        assertEquals(res,false);
    }

    @Test
    public void testInsertApp1(){
        Map<String,String> params=new HashMap<>();
        params.put("AppName","火币小助手");
        params.put("userId","171860670");
        params.put("AppType","xxx类");
        params.put("safeLevel","安全等级一");
        params.put("CreateDate","1999-11-21");
        helloService.InsertApp(params);
        List<App> list=helloService.getAppList("171860670");
        assertEquals(list.get(list.size()-1).getUserId(),"171860670");
        assertEquals(list.get(list.size()-1).getAppName(),"火币小助手");
        assertEquals(list.get(list.size()-1).getAppType(),"xxx类");
        assertEquals(list.get(list.size()-1).getsafeLevel(),"安全等级一");
        assertEquals(list.get(list.size()-1).getCreateDate(),"1999-11-21");
    }

    @Test
    public void testInsertApp2(){
        Map<String,String> params=new HashMap<>();
        params.put("AppName","hellobike");
        params.put("userId","171860671");
        params.put("AppType","xxx类");
        params.put("safeLevel","安全等级一");
        params.put("CreateDate","1999-11-21");
        helloService.InsertApp(params);
        List<App> list=helloService.getAppList("171860671");
        assertEquals(list.get(list.size()-1).getUserId(),"171860671");
        assertEquals(list.get(list.size()-1).getAppName(),"hellobike");
        assertEquals(list.get(list.size()-1).getAppType(),"xxx类");
        assertEquals(list.get(list.size()-1).getsafeLevel(),"安全等级一");
        assertEquals(list.get(list.size()-1).getCreateDate(),"1999-11-21");
    }


    @Test
    public void testgetAllApp1(){
        List<App> applist=helloService.getAppList("171860671");
        assertEquals(applist.size(),2);
        assertEquals(applist.get(0).getAppName(),"myApp");
        assertEquals(applist.get(1).getAppName(),"滴滴打车");
    }

    @Test
    public void testgetAllApp2(){
        List<App> applist=helloService.getAppList("1718");
        assertEquals(applist.size(),0);
    }
}
