package com.example.springweb.dao;

import java.io.Serializable;

public class App implements Serializable {
    private String userId;
    private String applyId;
    private String AppName;
    private String AppType;
    private String safeLevel;
    private String CreateDate;
    private String state;

    public App(){
        userId = null;
        applyId = null;
        AppName = null;
        AppType = null;
        safeLevel = null;
        CreateDate = null;
        state = null;
    }

    public App(String userId,String applyId,String AppName,String AppType,String safeLevel,String CreateDate,String state){
        this.userId = userId;
        this.applyId = applyId;
        this.AppName = AppName;
        this.AppType = AppType;
        this.safeLevel = safeLevel;
        this.CreateDate = CreateDate;
        this.state = state;
    }

    public String getUserId(){return userId;}
    public void setUserId(String userid){userId=userid;}
    public String getapplyId(){return applyId;}
    public void setapplyId(String applyid) {applyId=applyid;}
    public String getAppName(){return AppName;}
    public void setAppName(String name){AppName=name;}
    public String getAppType(){return AppType;}
    public void setAppType(String type){AppType=type;}
    public String getsafeLevel(){return safeLevel;}
    public void setsafeLevel(String level){safeLevel=level;}
    public String getCreateDate(){return CreateDate;}
    public void setCreateDate(String date){CreateDate=date;}
    public String getState(){return state;}
    public void setState(String s){state=s;}

    @Override
    public String toString() {
        return userId + ";" + applyId + ";" + AppName+";"+AppType+","+safeLevel+";"+CreateDate+";"+state;
    }
}
