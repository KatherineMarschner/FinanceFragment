package com.android.financeplanner.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.financeplanner.model.Question;
import com.example.financefragment.R;

public class PlannerDefaultQuestionTable {

	// Database table
	public static final String TABLE_PLANNER = "drewPlanner2";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_QUESTION = "question";
	public static final String COLUMN_ANSWER = "answer";
	public static Question[] mQuestionBank = new Question[] {
			new Question(1, R.string.question_netWorth, 5000),
			new Question(2, R.string.question_salary, 50000),
			new Question(3, R.string.question_raise, 5),
			new Question(4, R.string.question_401k, 10),
			new Question(5, R.string.question_mortgage, 5),
			new Question(6, R.string.question_fedTaxRate, 15),
			new Question(7, R.string.question_stateTaxRate, 15),
			new Question(8, R.string.question_carPayment, 300),
			new Question(9, R.string.question_carPayment, 700) };
	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PLANNER + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_QUESTION
			+ " integer, " + COLUMN_ANSWER + " integer);";

	/**
	 * Creates a new database
	 * 
	 * @param database
	 */
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);

		ContentValues values = new ContentValues();
		for (int i = 0; i < mQuestionBank.length; i++) {
			values.put(PlannerDefaultQuestionTable.COLUMN_ID, mQuestionBank[i].getmId());
			values.put(PlannerDefaultQuestionTable.COLUMN_QUESTION,
					String.valueOf(mQuestionBank[i].getmQuestion()));
			values.put(PlannerDefaultQuestionTable.COLUMN_ANSWER,
					String.valueOf(mQuestionBank[i].getmAnswer()));

			// Inserting Row
			database.insert(PlannerDefaultQuestionTable.TABLE_PLANNER, null, values);
		}

	}

	/**
	 * Destroys the old database version and replaces it with the new one,
	 * otherwise creates a new database
	 * 
	 * @param database
	 * @param oldVersion
	 * @param newVersion
	 */
	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(PlannerDefaultQuestionTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANNER);
		onCreate(database);
	}
}
