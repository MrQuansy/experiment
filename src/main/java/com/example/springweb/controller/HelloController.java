package com.example.springweb.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.springweb.dao.App;
import com.example.springweb.service.HelloService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/hello")
    public String hello(){
        logger.info("hello logging" + helloService.getUserList());
        return "hello";
    }

    @RequestMapping("/log")
    public String login(Model model, @RequestParam(value="stringid") String stringid, @RequestParam(value="password") String password) {
        if(helloService.CheckPassword(stringid,password)) {
            helloService.setCurrentUser(helloService.getOne(stringid));
            model.addAttribute("user", "welcome, "+helloService.getOne(stringid).getName());
            return "redirect:/MainPage";
        }
        else return "redirect:/";
    }
    @RequestMapping("/MainPage")
    public String entermainpage(Model model){
        return "MainPage";
    }

    @RequestMapping("/change")
    public String EnterinChangePage(Model model) {
        return "change";
    }

    @RequestMapping("/signup")
    public String EnterinSignupPage(Model model) {
        return "signup";
    }

    @RequestMapping("ChangePassword")
    public String changepassword(Model model, @RequestParam(value="stringid") String stringid, @RequestParam(value="password") String password,  @RequestParam(value="new") String newpassword,  @RequestParam(value="confirm") String confirmpassword ) {
        if(helloService.CheckPassword(stringid,password)&&newpassword.equals(confirmpassword)&&(!newpassword.equals(""))) {
            Map<String,String> newuser=new HashMap<String,String>();
            newuser.put("id",stringid);
            newuser.put("password",newpassword);
            newuser.put("name",null);
            helloService.UpdateByID(newuser);
            return "redirect:/";
        }
        else return "redirect:/change";
    }

    @RequestMapping("CreateAccount")
    public String createaccount(Model model, @RequestParam(value="stringid") String stringid, @RequestParam(value="username") String username,  @RequestParam(value="password") String password,  @RequestParam(value="confirm") String confirmpassword ) {
        if((!stringid.equals(""))&&(!username.equals(""))&&(helloService.getOne(stringid).getId()==null)&&(password.equals(confirmpassword))&&(!password.equals(""))){
            Map<String,String> newuser=new HashMap<String,String>();
            newuser.put("id",stringid);
            newuser.put("name",username);
            newuser.put("password",password);
            helloService.InsertUser(newuser);
            return "redirect:/";
        }
        else return "redirect:/signup";
    }

    @RequestMapping("addApp")
    public String addapp(Model model ) {
        return "addApp";
    }

    @RequestMapping("finishApp")
    public String finishApp(Model model,@RequestParam(value="appname") String appname, @RequestParam(value="BusinessAspect") String aspect,  @RequestParam(value="ApplicationRange") String range,  @RequestParam(value="knowledgeType") String knowledge, @RequestParam(value="SaftyLevel") String slevel ) {
        if(!(appname.equals("")||aspect.equals("")||range.equals("")||knowledge.equals("")||slevel.equals(""))) {
            Map<String,String> newapp=new HashMap<String,String>();
            newapp.put("userId",helloService.getCurrentUser().getId());
            newapp.put("applyId","-1");
            newapp.put("AppName",appname);
            newapp.put("AppType",aspect+","+range+","+knowledge);
            newapp.put("safeLevel",slevel);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            newapp.put("CreateDate",simpleDateFormat.format(date));
            newapp.put("state","未审核");
            helloService.InsertApp(newapp);
            return "redirect:/MainPage";
        }
        else {
            return "redirect:/addApp";
        }
    }

    @RequestMapping("AppForm")
    public String lookAppForm(Model model) {
        List<App> temp=helloService.getAppList(helloService.getCurrentUser().getId());
        model.addAttribute("AppList", temp);
        return "AppForm";
    }

}
