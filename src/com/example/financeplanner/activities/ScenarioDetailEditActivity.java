package com.example.financeplanner.activities;

import android.support.v4.app.Fragment;

import com.example.financeplanner.fragments.ScenarioDetailEditFragment;

public class ScenarioDetailEditActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment(){
		ScenarioDetailEditFragment fragment = new ScenarioDetailEditFragment();
		return fragment;
		
	}
}
