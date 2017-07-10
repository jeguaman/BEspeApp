package com.teamj.joseguaman.bespeapp.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by alterbios on 06-Jul-17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();
    private Context context;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ViewPagerAdapter(FragmentManager manager, Context context, ViewPager viewPager, TabLayout tabLayout) {
        super(manager);
        this.context = context;
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;
    }


    public void addNewFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void removeFrag(int position) {
        removeTab(position);
        Fragment fragment = mFragmentList.get(position);
        mFragmentList.remove(fragment);
        mFragmentTitleList.remove(position);
        destroyFragmentView(viewPager, position, fragment);
        notifyDataSetChanged();
    }

    public View getTabView(final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab_fragment, null);
//        TextView tabItemName = (TextView) view.findViewById(R.id.textViewTabItemName);
//        CircleImageView tabItemAvatar = (CircleImageView) view.findViewById(R.id.imageViewTabItemAvatar);
//        ImageButton remove = (ImageButton) view.findViewById(R.id.imageButtonRemove);
//        remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Remove", "Remove");
//                removeFrag(position);
//            }
//        });

//        tabItemName.setText(mFragmentTitleList.get(position));
//        tabItemName.setTextColor(context.getResources().getColor(android.R.color.background_light));

        return view;
    }

    public void destroyFragmentView(ViewGroup container, int position, Object object) {
        FragmentManager manager = ((Fragment) object).getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove((Fragment) object);
        trans.commit();
    }

    public void removeTab(int position) {
        if (tabLayout.getChildCount() > 0) {
            tabLayout.removeTabAt(position);
        }
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
