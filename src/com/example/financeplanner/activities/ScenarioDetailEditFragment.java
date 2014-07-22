package com.example.financeplanner.activities;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.financeplanner.database.FinanceDataModel;
import com.example.financefragment.R;

public class ScenarioDetailEditFragment extends Fragment implements
		LoaderManager.LoaderCallbacks<Cursor> {

	public static final String ARG_ITEM_ID = "item_id";

	private FinanceDataModel datasource;

	public ScenarioDetailEditFragment() {

	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub

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

		/*
		 * Instantiate the data model which will be used to get data from the
		 * database to fill the list
		 */
		datasource = new FinanceDataModel(getActivity());
		datasource.open();

		return view;

	}

}
