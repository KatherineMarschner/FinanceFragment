package com.example.financeplanner.activities;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.financeplanner.database.FinanceDataModel;
import com.android.financeplanner.model.Question;
import com.example.financefragment.R;

public class ScenarioDetailEditActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_scenario_edit);
		/*
		 * Set the list view, which will contain the questions and their
		 * modifiable answers
		 */
		final ListView listView = (ListView) findViewById(R.id.listView1);
		/*
		 * Get all of the questions/answers from the database and store them in
		 * a list
		 */
		FinanceDataModel database = new FinanceDataModel(this);
		database.open();
		List<Question> mQuestionBank = database.getAll();
		// show the up button in the action bar
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
					.add(R.id.scenario_detail, fragment).commit();
		}

		QuestionListAdapter adapter = new QuestionListAdapter(this,
				mQuestionBank);

		listView.setAdapter(adapter);

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
