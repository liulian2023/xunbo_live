package com.zz.live.ui.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zz.live.ui.fragment.details_fragment.GiftDetailsFragment;
import com.zz.live.ui.fragment.details_fragment.LiveDurationFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailsTabAdapter extends FragmentPagerAdapter {
    private List<String> titleList =new ArrayList<>();
    int version;

    public DetailsTabAdapter(FragmentManager fm, List<String> titleList,int version) {
        super(fm);
        this.titleList = titleList;
        this.version = version;
    }

    @Override
    public Fragment getItem(int i) {
        if(version==1){
            if(i==0){
                return GiftDetailsFragment.newInstance(i);
            }else {
                return LiveDurationFragment.newInstance(i);
            }
        }else if(version==2){
            return LiveDurationFragment.newInstance(i);
        }else {
            return GiftDetailsFragment.newInstance(i);
        }

    }

    @Override
    public int getCount() {
        return titleList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
