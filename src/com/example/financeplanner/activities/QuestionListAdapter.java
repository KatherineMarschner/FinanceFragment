package com.example.financeplanner.activities;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.financeplanner.model.Question;
import com.example.financefragment.R;

/**
 * Extension of the ArrayAdapter class, used to add question + answer views to
 * the list shown in EditActivity and EditFragment
 * 
 * @author Drew
 */
public class QuestionListAdapter extends ArrayAdapter<Question> {

	public QuestionListAdapter(Context context, List<Question> questions) {
		super(context, R.layout.question_answer_edit_list, questions);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/* Get the question for this position */
		Question question = getItem(position);

		/* Check if an existing view is being reused, otherwise inflate the view */
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.question_answer_edit_list, parent, false);
		}
		/* Create the view for the question and the answer */
		TextView questionString = (TextView) convertView
				.findViewById(R.id.questionText);
		TextView answerField = (TextView) convertView
				.findViewById(R.id.answerField);

		/* Set the values to the views */
		questionString.setText(question.getmQuestion());
		answerField.setText(question.getmAnswer() + "");

		return convertView;
	}
}
