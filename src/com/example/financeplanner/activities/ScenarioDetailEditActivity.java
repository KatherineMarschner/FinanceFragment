package com.example.financeplanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.example.financefragment.R;

public class ScenarioDetailEditActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * Setting this content view seems to cause a problem, as it makes 2
		 * copies of the button appear on the handset. I wanted to change this
		 * to set the view to 'activity_scenario_edit_detail', but that just
		 * causes an error. No idea why. Help me obi-wan, you are my only hope.
		 */
		setContentView(R.layout.activity_scenario_edit_detail);

		/*
		 * savedInstanceState is non-null when there is fragment state saved
		 * from previous configurations of this activity (e.g. when rotating the
		 * screen from portrait to landscape). In this case, the fragment will
		 * automatically be re-added to its container so we don't need to
		 * manually add it. For more information, see the Fragments API guide
		 * at:
		 * 
		 * http://developer.android.com/guide/components/fragments.html
		 */
		if (savedInstanceState == null) {
			/*
			 * Create the detail fragment and add it to the activity using a
			 * fragment transaction
			 */
			Bundle arguments = new Bundle();
			// WHAT DOES THIS DO?!?!
			arguments.putString(
					ScenarioDetailEditFragment.ARG_ITEM_ID,
					getIntent().getStringExtra(
							ScenarioDetailEditFragment.ARG_ITEM_ID));

			ScenarioDetailEditFragment fragment = new ScenarioDetailEditFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.scenario_detail_container, fragment).commit();
		}

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		/*
		 * This ID represents the Home or Up button. In the case of this
		 * activity, the Up button is shown. Use NavUtils to allow users to
		 * navigate up one level in the application structure. For more details,
		 * see the Navigation pattern on Android Design:
		 * 
		 * http://developer.android.com/design/patterns/navigation.html#up-vs-back
		 */
		case android.R.id.home:
			NavUtils.navigateUpTo(this, new Intent(this,
					ScenarioListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
