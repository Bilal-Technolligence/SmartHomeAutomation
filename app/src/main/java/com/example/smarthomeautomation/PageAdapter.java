package com.example.smarthomeautomation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    int countTab;
    public PageAdapter(@NonNull FragmentManager fm, int countTab) {
        super(fm);
        this.countTab=countTab;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                HomeFragment home=new HomeFragment();
                return home;
            case 1:
                NotificationFragment notificationFragment=new NotificationFragment();
                return notificationFragment;
            case 2:
                SensorsNotificationFragment sensorsNotificationFragment=new SensorsNotificationFragment();
                return sensorsNotificationFragment;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return countTab;
    }
}
