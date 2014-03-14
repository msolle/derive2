package edu.unca.derive;

import android.support.v4.app.Fragment;

public class DeriveListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new DeriveListFragment();
	}

}
