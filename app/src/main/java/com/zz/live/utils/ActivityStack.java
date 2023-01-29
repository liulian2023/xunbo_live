package com.zz.live.utils;

import android.app.Activity;

import java.util.Vector;

/**
 * Created by Harry on 2018/8/9.
 * desc:
 */

public class ActivityStack {

    private static  ActivityStack instance;
    private Vector<Activity> activityVector=new Vector<>();


    private ActivityStack(){

    }

    public static ActivityStack getInstance(){
        if(instance==null){
            instance=new ActivityStack();
        }
        return instance;
    }

    public void add(Activity activity){
        activityVector.add(activity);
    }

    public void remove(Activity activity){
        if(activityVector.size()>0){
            if(activity!=null){
                activityVector.remove(activity);
            }
        }
    }

    public int getSize(){
        int size = 0;
        if (activityVector!=null){
            size = activityVector.size();
        }
        if (size>0){
            return size;
        }else {
            return 0;
        }

    }

    public Activity getTopActivity(){
        if (getSize()>0){
            activityVector.elementAt(getSize()-1);
        }
        return null;
    }
}
