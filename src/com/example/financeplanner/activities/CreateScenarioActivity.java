package com.example.financeplanner.activities;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.financeplanner.database.FinanceDataModel;
import com.android.financeplanner.model.Question;
import com.example.financefragment.R;

public class CreateScenarioActivity extends Activity implements
		LoaderManager.LoaderCallbacks<Cursor> {

	private Button mNextButton;
	private Button mBackButton;
	private Button mGraphButton;

	private TextView mQuestionTextView;
	private EditText mAnswerField;

	private ProgressBar mProgressBar;
	private int mCurrentId = 1;
	private int databaseLength = 0;
	private FinanceDataModel datasource;

	// gets the next question from the MODEL based on the the question id. It
	// increments this id for the next time
	// The question id will be the primary key in the data base for now. The
	// fields set are the fields in the VIEW
	private void getNextQuestion() {

		Question question = datasource.getNextQuestion(mCurrentId);

		if (question != null) {
			mQuestionTextView.setText(question.getmQuestion());
			mAnswerField.setText(question.getmAnswer() + "");
			mProgressBar.setProgress(mCurrentId);
		}
	}

	//
	private void setAnswer() {
		// String name = mScenarioName.getText().toString();
		int ans = Integer.parseInt(mAnswerField.getText().toString());
		datasource.saveAnswer(mCurrentId, ans);

	}

	private void createNetWorthGraph() {
		// setAnswer();
		// NetWorthGraph mNetWorthGraph = new NetWorthGraph();
		// mNetWorthGraph.receiveData(datasource.getAll());
		// Intent graphIntent = mNetWorthGraph.getIntent(this);
		// startActivity(graphIntent);
	}

	private void updateButton(Button button, boolean isNextButton) {
		if (isNextButton && mCurrentId >= databaseLength) {
			button.setEnabled(false);
			mCurrentId = databaseLength;
		} else if (!isNextButton && mCurrentId <= 1) {
			button.setEnabled(false);
			mCurrentId = 1;
		} else {
			button.setEnabled(true);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenario_list);

		datasource = new FinanceDataModel(this);

		datasource.open();

		// mQuestionBank = datasource.getAll();

		/* Set the view widgets to the variables */
		mQuestionTextView = (TextView) findViewById(R.id.scenario_detail);
		mAnswerField = (EditText) findViewById(R.id.scenario_detail);
		// mScenarioName = (EditText) findViewById(R.id.name_text_view);
		mProgressBar = (ProgressBar) findViewById(R.id.scenario_detail);
		/*
		 * Set the max size of the progress bar to the number of questions in
		 * the bank
		 */
		databaseLength = datasource.getLength();
		mProgressBar.setMax(datasource.getLength());
		/* Define the buttons */
		// mNextButton = (Button) findViewById(R.id.next_button);
		// mBackButton = (Button) findViewById(R.id.back_button);

		/* Set the listeners */

		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				setAnswer();
				mCurrentId++;
				updateButton(mNextButton, true);
				updateButton(mBackButton, false);
				getNextQuestion();
			}

		});

		mBackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setAnswer();
				mCurrentId--;
				updateButton(mBackButton, false);
				updateButton(mNextButton, true);
				getNextQuestion();

			}

		});

		/* This loads the first question (before any button is pressed) */
		getNextQuestion();

		// mGraphButton = (Button) findViewById(R.id.graph_button);
		mGraphButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				createNetWorthGraph();
			}
		});
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

}
