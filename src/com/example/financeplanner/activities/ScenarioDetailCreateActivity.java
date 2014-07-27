package com.example.financeplanner.activities;

import com.example.financeplanner.fragments.ScenarioDetailCreateFragment;

import android.support.v4.app.Fragment;



public class ScenarioDetailCreateActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment(){
		ScenarioDetailCreateFragment fragment = new ScenarioDetailCreateFragment();
		return fragment;
		
	}
}