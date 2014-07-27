package com.example.financeplanner.fragments;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.financeplanner.database.FinanceDataModel;
import com.android.financeplanner.model.GraphDataCalculator;
import com.android.financeplanner.model.Question;
import com.example.financefragment.R;
import com.example.financefragment.dummy.MenuButtons;
import com.example.financeplanner.activities.ScenarioDetailGraphActivity;
import com.example.financeplanner.activities.ScenarioListActivity;

/**
 * A fragment representing a single Scenario detail screen. This fragment is
 * either contained in a {@link ScenarioListActivity} in two-pane mode (on
 * tablets) or a {@link ScenarioDetailGraphActivity} on handsets. test
 */
public class ScenarioDetailGraphFragment extends Fragment implements
		LoaderManager.LoaderCallbacks<Cursor> {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	private GraphicalView mChartView;
	List<Question> questions = new ArrayList<Question>();
	private int databaseLength = 0;
	private FinanceDataModel datasource;

	/**
	 * The dummy content this fragment is presenting.
	 */
	private MenuButtons.MenuButton mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ScenarioDetailGraphFragment() {

	}

	// public void setDataSource(FinanceDataModel datasource) {
	// this.datasource = datasource;
	//
	// }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_scenario_graph,
				container, false);

		// I do not understand all of this yet. Each view has multiple layouts.
		// Each layout can add views within
		// it I believe. I was passed a container. I expanded the fragment view.
		// Inside the fragment view there
		// was a linear layout. I added the view from the chart to this linear
		// layout. I need to learn more here
		datasource = new FinanceDataModel(getActivity());
		datasource.open();
		databaseLength = datasource.getLength();
		questions = datasource.getAll();

		LinearLayout layout = (LinearLayout) view
				.findViewById(R.id.linearLayout_graphFragment);

		layout.addView(getView());

		return view;

	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub

	}

	public GraphicalView getView() {

		/*
		 * Convert the data into a series object, and then add all of the data
		 * to the series. A TimeSeries object is what is graphed
		 */
		TimeSeries netWorthSeries = new TimeSeries("Net Worth");
		TimeSeries lowerBoundSeries = new TimeSeries("Lower Bound");
		TimeSeries upperBoundSeries = new TimeSeries("Upper Bound");

		/* This just graphs the user answers, which is not what we want */
		// for (int i = 0; i < questions.size(); i++) {
		// mSeries.add(questions.get(i).getmId(), questions.get(i)
		// .getmAnswer());
		// }

		/* Class which can calculate data that we actually want to graph */
		GraphDataCalculator calculator = new GraphDataCalculator(questions);
		int[] netWorthData = calculator.getNetWorthArray();
		int[] lowerBounds = calculator.bounds(netWorthData, 0.05, false);
		int[] upperBounds = calculator.bounds(netWorthData, 0.05, true);
		for (int year = 0; year < netWorthData.length; year++) {
			netWorthSeries.add(year, netWorthData[year]);
			lowerBoundSeries.add(year, lowerBounds[year]);
			upperBoundSeries.add(year, upperBounds[year]);
		}

		/*
		 * This class is a collection of all series and adds them under one
		 * object
		 */
		XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
		mDataset.addSeries(netWorthSeries);
		mDataset.addSeries(lowerBoundSeries);
		mDataset.addSeries(upperBoundSeries);

		/* This class can render the net worth data into a graph */
		XYSeriesRenderer netWorthRenderer = new XYSeriesRenderer();
		netWorthRenderer.setColor(Color.WHITE);
		netWorthRenderer.setLineWidth(10f);

		/* Renderer for the upper and lower bounds of the graph */
		XYSeriesRenderer lowerBoundsRenderer = new XYSeriesRenderer();
		lowerBoundsRenderer.setColor(Color.RED);
		XYSeriesRenderer upperBoundsRenderer = new XYSeriesRenderer();
		upperBoundsRenderer.setColor(Color.BLUE);

		/* This class is a collection of renderers */
		XYMultipleSeriesRenderer mMultiRenderer = new XYMultipleSeriesRenderer();
		mMultiRenderer.addSeriesRenderer(netWorthRenderer);
		mMultiRenderer.addSeriesRenderer(lowerBoundsRenderer);
		mMultiRenderer.addSeriesRenderer(upperBoundsRenderer);
		mMultiRenderer.setApplyBackgroundColor(true);
		mMultiRenderer.setBackgroundColor(Color.BLACK);
		mMultiRenderer.setShowGrid(true);
		mMultiRenderer.setChartTitle("Net Worth vs. Time");
		mMultiRenderer.setXTitle("Time (years)");
		mMultiRenderer.setYTitle("Net Worth");
		mMultiRenderer.setXAxisMin(0.);
		mMultiRenderer.setXAxisMax(10.);
		// get a view of the line chart and return it. It will be displayed in
		// the fragment

		mChartView = ChartFactory.getLineChartView(getActivity(), mDataset,
				mMultiRenderer);

		return mChartView;

	}
}
