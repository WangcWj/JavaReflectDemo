package com.wang.javareflect;

import android.util.Log;

/**
 * Created by WangChao on 2017/8/22.
 */

public class XiaoMingBean extends PersonBean {
    private String love;
    public String sing;

    private XiaoMingBean(String love, String sing) {
        this.love = love;
        this.sing = sing;
    }

    public boolean canSing(){
        return  false;
    }
    public String getLove() {
        return love;
    }
    public void init(String can){
         Log.e("CHAO", "我叫小明我会唱歌吗?"+can);
    }
    public void setLove(String love) {
        this.love = love;
    }
}
