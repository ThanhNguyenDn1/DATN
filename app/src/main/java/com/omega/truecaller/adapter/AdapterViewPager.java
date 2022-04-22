package com.omega.truecaller.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.omega.truecaller.R;
import com.omega.truecaller.fragment.Important;
import com.omega.truecaller.fragment.Inbox;
import com.omega.truecaller.fragment.Promotions;
import com.omega.truecaller.fragment.Spam;

public class AdapterViewPager extends FragmentStatePagerAdapter {

    private Context context;

    public AdapterViewPager(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Inbox();
                break;
            case 1:
                fragment = new Important();
                break;
            case 2:
                fragment = new Promotions();
                break;
            case 3:
                fragment = new Spam();
                break;


        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = this.context.getString(R.string.inbox);
                break;
            case 1:
                title = this.context.getString(R.string.Important);
                break;
            case 2:
                title = this.context.getString(R.string.Promotions);
                break;
            case 3:
                title = this.context.getString(R.string.Spam);
                break;
        }
        return title;
    }


}
