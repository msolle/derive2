package edu.unca.derive;

import java.util.ArrayList;
import java.util.UUID;

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
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			public void onPageSelected(int pos) {
				Derive derive = mDerives.get(pos);
				if(derive.getTitle() != null)
					setTitle(derive.getTitle());
				
			}
					
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			public void onPageScrollStateChanged(int arg0){ }
		});
		
		UUID deriveId = (UUID)getIntent().getSerializableExtra(DeriveFragment.EXTRA_DERIVE_ID);
		for(int i = 0; i < mDerives.size(); i++){
			if(mDerives.get(i).getId().equals(deriveId)) {
				mViewPager.setCurrentItem(i);				
				break;
			}
		}
		
	}
}
