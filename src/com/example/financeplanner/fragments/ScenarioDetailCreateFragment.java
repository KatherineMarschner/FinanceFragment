package com.example.financeplanner.fragments;

import java.util.List;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.financeplanner.database.FinanceDataModel;
import com.android.financeplanner.model.Question;
import com.example.financefragment.R;
import com.example.financeplanner.activities.QuestionListAdapter;

public class ScenarioDetailCreateFragment extends Fragment{
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
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
	
		}

		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			// Define the view which will be passed by this fragment to the activity
			View view = inflater.inflate(R.layout.activity_planner,
					container, false);

			datasource = new FinanceDataModel(getActivity());

			datasource.open();

			// mQuestionBank = datasource.getAll();

			/* Set the view widgets to the variables */
			
			
			mQuestionTextView = (TextView) view.findViewById(R.id.question_text_view);
			mAnswerField = (EditText) view.findViewById(R.id.answer_text_view);
			//mScenarioName = (EditText) view.findViewById(R.id.name_text_view);
			mProgressBar = (ProgressBar) view.findViewById(R.id.question_progress_bar);
			/*
			 * Set the max size of the progress bar to the number of questions in
			 * the bank
			 */
			databaseLength = datasource.getLength();
			mProgressBar.setMax(datasource.getLength());
			/* Define the buttons */
			 mNextButton = (Button) view.findViewById(R.id.next_button);
			 mBackButton = (Button) view.findViewById(R.id.back_button);

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

			

			//layout.addView(getView());
			return view;

		}

		


}
