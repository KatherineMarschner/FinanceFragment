package com.example.financeplanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.financeplanner.database.FinanceDataModel;
import com.example.financefragment.R;
import com.example.financeplanner.fragments.ScenarioDetailCreateFragment;
import com.example.financeplanner.fragments.ScenarioDetailEditFragment;
import com.example.financeplanner.fragments.ScenarioDetailGraphFragment;
import com.example.financeplanner.fragments.ScenarioListFragment;

/**
 * An activity representing a list of Scenarios. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ScenarioDetailGraphActivity} representing item details. On tablets,
 * the activity presents the list of items and item details side-by-side using
 * two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ScenarioListFragment} and the item details (if present) is a
 * {@link ScenarioDetailGraphFragment}.
 * <p>
 * This activity also implements the required
 * {@link ScenarioListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class ScenarioListActivity extends FragmentActivity implements
		ScenarioListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenario_list);
		/*
		 * The detail container view will be present only in the large-screen
		 * layouts (res/values-large and res/values-sw600dp). If this view is
		 * present, then the activity should be in two-pane mode.
		 */
		if (findViewById(R.id.scenario_detail_container) != null) {

			mTwoPane = true;

			/*
			 * In two-pane mode, list items should be given the 'activated'
			 * state when touched.
			 */
			((ScenarioListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.scenario_list))
					.setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here. test
	}

	/**
	 * Callback method from {@link ScenarioListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		/*
		 * The id can be 1 or 2 right now depending on which button the user
		 * pushes
		 */
		if (mTwoPane) {
			/*
			 * In two-pane mode, show the detail view in this activity by adding
			 * or replacing the detail fragment using a fragment transaction.
			 */
			// edit
			if (id.equals("1")) {
				Bundle arguments = new Bundle();
				arguments
						.putString(ScenarioDetailGraphFragment.ARG_ITEM_ID, id);
				// might want to see if the fragment has already been created
				// before recreating it
				
				ScenarioDetailEditFragment fragment = new ScenarioDetailEditFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.scenario_detail_container, fragment)
						.commit();
				// create
			} else if (id.equals("2")) {
				Bundle arguments = new Bundle();
			//	arguments.putString(ScenarioDetailEditFragment.ARG_ITEM_ID, id);
				ScenarioDetailCreateFragment fragment = new ScenarioDetailCreateFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.scenario_detail_container, fragment)
						.commit();
			//compare
			} else if (id.equals("3")) {
				Bundle arguments = new Bundle();
				arguments.putString(ScenarioDetailEditFragment.ARG_ITEM_ID, id);
				
				ScenarioDetailGraphFragment fragment = new ScenarioDetailGraphFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.scenario_detail_container, fragment)
						.commit();
			}

		} else {
			/*
			 * In single-pane mode, simply start the detail activity for the
			 * selected item ID.
			 */
		    if (id.equals("1")) {
			Intent editIntent = new Intent(this,
					ScenarioDetailEditActivity.class);
			editIntent.putExtra(ScenarioDetailEditFragment.ARG_ITEM_ID, id);
			startActivity(editIntent);
		    }
			if (id.equals("2")) {
				Intent detailIntent = new Intent(this,
						ScenarioDetailCreateActivity.class);
			//	detailIntent.putExtra(ScenarioDetailCreateFragment.ARG_ITEM_ID,
			//			id);
				startActivity(detailIntent);
			} else if (id.equals("3")) {
				Intent detailIntent = new Intent(this,
						ScenarioDetailGraphActivity.class);
				detailIntent.putExtra(ScenarioDetailGraphFragment.ARG_ITEM_ID,
						id);
				startActivity(detailIntent);
			}

		}
	}
}
