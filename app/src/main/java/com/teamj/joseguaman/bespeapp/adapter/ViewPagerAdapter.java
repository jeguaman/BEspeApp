package com.teamj.joseguaman.bespeapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.teamj.joseguaman.bespeapp.R;

import java.util.ArrayList;


/**
 * Created by alterbios on 06-Jul-17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();
    private Context context;
    //private ViewPager viewPager;
    //private TabLayout tabLayout;

    public ViewPagerAdapter(FragmentManager manager, Context context) {
        super(manager);
        this.context = context;
        //this.viewPager = viewPager;
        //this.tabLayout = tabLayout;
    }
    /*
    public ViewPagerAdapter(FragmentManager manager, Context context, ViewPager viewPager, TabLayout tabLayout) {
        super(manager);
        this.context = context;
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;
    }*/

    public void addNewFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public View getTabView(final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab_fragment, null);
        TextView txtNombreTab = (TextView) view.findViewById(R.id.txt_nombre_tab);
        //CircleImageView tabItemAvatar = (CircleImageView) view.findViewById(R.id.img_icon_tab);
        txtNombreTab.setText(mFragmentTitleList.get(position));
        txtNombreTab.setTextColor(context.getResources().getColor(android.R.color.black));

        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
