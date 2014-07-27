package com.example.financeplanner.activities;


import com.example.financeplanner.fragments.ScenarioDetailGraphFragment;

import android.support.v4.app.Fragment;


/**
 * An activity representing a single Scenario detail screen. This activity is
 * only used on handset devices. On tablet-size devices, item details are
 * presented side-by-side with a list of items in a {@link ScenarioListActivity}
 * .
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link ScenarioDetailGraphFragment}.
 */
public class ScenarioDetailGraphActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment(){
		ScenarioDetailGraphFragment fragment = new ScenarioDetailGraphFragment();
		return fragment;
		
	}
}
