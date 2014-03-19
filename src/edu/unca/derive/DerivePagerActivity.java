package edu.unca.derive;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class DerivePagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private ArrayList<Derive> mDerives;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		
		mDerives = DeriveList.get(this).getDerives();
		
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			@Override
			public int getCount() {
				return mDerives.size();
			}
			
			@Override
			public Fragment getItem(int pos){
				Derive derive = mDerives.get(pos);
				return DeriveFragment.newInstance(derive.getId());
			}
			
		});
		
	}
}
