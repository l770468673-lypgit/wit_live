package com.example.administrator.wit_live.main_fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wit_live.Adapter.Home_MessageAdapter;
import com.example.administrator.wit_live.Fragment.huifang_Fragment;
import com.example.administrator.wit_live.Fragment.remen_Fragment;
import com.example.administrator.wit_live.Fragment.zuixin_Fragment;
import com.example.administrator.wit_live.R;

import java.util.ArrayList;
import java.util.List;

public class main_main_activity extends Fragment {



    private TabLayout hometab;
    private ViewPager home_message_vvp;

    public main_main_activity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view= inflater.inflate(R.layout.activity_main_main_activity,container,false);



        initview(view);


        return view;
    }

    private void initview(View view) {
        home_message_vvp = (ViewPager) view.findViewById(R.id.home_message_vvp);
        hometab = (TabLayout) view.findViewById(R.id.hometab);

        List<String> tablist = new ArrayList<>();
        tablist.add("回放");
        tablist.add("热门");
        tablist.add("更新");


        List<Fragment> fragmentslist = new ArrayList<>();
        fragmentslist.add(huifang_Fragment.newInstance());
        fragmentslist.add(remen_Fragment.newInstance());
        fragmentslist.add(zuixin_Fragment.newInstance());

        Home_MessageAdapter adapter = new Home_MessageAdapter(getActivity(), fragmentslist, tablist);

        home_message_vvp.setAdapter(adapter);

        home_message_vvp.setOffscreenPageLimit(2);
        home_message_vvp.setCurrentItem(0);
        // 协同布局
        hometab.setupWithViewPager(home_message_vvp);
        hometab.setTabsFromPagerAdapter(adapter);

    }














}
