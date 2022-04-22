package com.omega.truecaller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.omega.truecaller.R;
import com.omega.truecaller.adapter.AdapterViewPager;


public class Messages extends Fragment {


    private TabLayout tabLayout;
    private ViewPager pager;
    private int[] tabIcons = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,

    };

    public Messages() {
        // Required empty public constructor
    }


    public static Messages newInstance(String param1, String param2) {
        Messages fragment = new Messages();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);
        initView();
    }

    private void initView() {
        AdapterViewPager adapterViewPager=new AdapterViewPager(getActivity().getSupportFragmentManager(),getActivity());
        pager.setAdapter(adapterViewPager);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapterViewPager);//deprecated
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        setupTabIcons();
    }

    private void initData(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        pager = view.findViewById(R.id.pager);
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }
}