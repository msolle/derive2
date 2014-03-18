package edu.unca.derive;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class DeriveActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		UUID deriveID = (UUID)getIntent().getSerializableExtra(DeriveFragment.EXTRA_DERIVE_ID);
		return DeriveFragment.newInstance(deriveID);
	}

}
