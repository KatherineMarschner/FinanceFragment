package com.example.financeplanner.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.financeplanner.database.FinanceDataModel;
import com.android.financeplanner.model.Question;
import com.example.financefragment.R;

public class ScenarioDetailEditFragment extends Fragment {

	public static final String ARG_ITEM_ID = "item_id";

	private FinanceDataModel datasource;

	public ScenarioDetailEditFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Define the view which will be passed by this fragment to the activity
		View view = inflater.inflate(R.layout.fragment_scenario_edit,
				container, false);

		return view;

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		/*
		 * Set the list view, which will contain the questions and their
		 * modifiable answers
		 */
		final ListView listView = (ListView) activity
				.findViewById(R.id.listView1);
		/*
		 * Get all of the questions/answers from the database and store them in
		 * a list
		 */
		FinanceDataModel database = new FinanceDataModel(activity);
		database.open();
		List<Question> mQuestionBank = database.getAll();
		// show the up button in the action bar
		activity.getActionBar().setDisplayHomeAsUpEnabled(true);

		QuestionListAdapter adapter = new QuestionListAdapter(activity,
				mQuestionBank);

		listView.setAdapter(adapter);
	}
}
