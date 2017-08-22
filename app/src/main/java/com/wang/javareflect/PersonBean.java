package com.wang.javareflect;

import android.util.Log;

/**
 * Created by WangChao on 2017/8/22.
 */

public class PersonBean {
    String name;
    String sex;
    String can;

    public PersonBean() {
    }

    public PersonBean(String name) {
        this.name = name;
    }

    public PersonBean(String name, String sex, String can) {
        this.name = name;
        this.sex = sex;
        this.can = can;
    }
    private void canDoThing(){
         Log.e("CHAO", "我会走路啊!");
    }
    public void cando(){
         Log.e("CHAO","我会吃饭啊!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }
}
