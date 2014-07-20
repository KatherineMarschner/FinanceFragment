package com.android.financeplanner.model;

public class Question {
	private int mQuestion;
	private int mAnswer;
	private int mId;

	public Question(int id, int question, int answer) {
		mQuestion = question;
		mAnswer = answer;
		mId = id;
	}

	public int getmQuestion() {
		return mQuestion;
	}

	public void setmQuestion(int mQuestion) {
		this.mQuestion = mQuestion;
	}

	public int getmAnswer() {
		return mAnswer;
	}

	public void setmAnswer(int mAnswer) {
		this.mAnswer = mAnswer;
	}

	public int getmId() {
		return mId;

	}

	public void setmId(int id) {
		mId = id;

	}
}
