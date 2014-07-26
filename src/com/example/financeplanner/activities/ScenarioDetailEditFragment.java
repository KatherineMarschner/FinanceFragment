package com.example.financeplanner.activities;

import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
		

		// I do not understand all of this yet. Each view has multiple layouts.
		// Each layout can add views within
		// it I believe. I was passed a container. I expanded the fragment view.
		// Inside the fragment view there
		// was a linear layout. I added the view from the chart to this linear
		// layout. I need to learn more here
		datasource = new FinanceDataModel(getActivity());
		datasource.open();
		List<Question> mQuestionBank = datasource.getAll();
		QuestionListAdapter adapter = new QuestionListAdapter(getActivity(),
				mQuestionBank);
		
		final ListView listView = (ListView) view
				.findViewById(R.id.listView1);

		listView.setAdapter(adapter);

		LinearLayout layout = (LinearLayout) view
				.findViewById(R.id.linearLayout_editFragment);

		//layout.addView(getView());
		return view;

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		/*
		 * Set the list view, which will contain the questions and their
		 * modifiable answers
		 */
//		final ListView listView = (ListView) activity
//				.findViewById(R.id.listView1);
		/*
		 * Get all of the questions/answers from the database and store them in
		 * a list
		 */
//		FinanceDataModel database = new FinanceDataModel(activity);
//		database.open();
//		List<Question> mQuestionBank = database.getAll();
		// show the up button in the action bar
		activity.getActionBar().setDisplayHomeAsUpEnabled(true);

		//QuestionListAdapter adapter = new QuestionListAdapter(activity,
		//		mQuestionBank);

		//listView.setAdapter(adapter);
	}
}
