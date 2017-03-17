package com.letrunghung.lazada.View.DangNhap_DangKy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.letrunghung.lazada.Adapter.ViewPagerAdapterDangNhap;
import com.letrunghung.lazada.R;

/**
 * Created by TED on 17/03/2017.
 */

public class DangNhapActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        tabLayout = (TabLayout) findViewById(R.id.tabDangNhap);
        viewPager = (ViewPager) findViewById(R.id.viewPagerDangNhap);
        toolbar = (Toolbar) findViewById(R.id.toolBarDangNhap);

        setSupportActionBar(toolbar);
        ViewPagerAdapterDangNhap viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }
}
