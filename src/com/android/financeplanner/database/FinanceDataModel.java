package com.android.financeplanner.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.financeplanner.model.Question;

public class FinanceDataModel {

	// this is a class for interfacing with the database
	private SQLiteDatabase database;
	// Helper is what creates the database
	private PlannerDatabaseHelper dbHelper;
	// Array containing names of all columns
	private String[] allColumns = { PlannerDefaultQuestionTable.COLUMN_ID,
			PlannerDefaultQuestionTable.COLUMN_QUESTION, PlannerDefaultQuestionTable.COLUMN_ANSWER };
	private Cursor cursor;
	private int length;

	// create a new helper file. The helper file is opens the file if it is not
	// new or creates and opens it if it is new
	public FinanceDataModel(Context context) {
		dbHelper = new PlannerDatabaseHelper(context);

	}

	// get the number or questions in the data base
	public int getLength() {

		database = dbHelper.getWritableDatabase();

		// THis command is a query with out a "filter or where statement". If we
		// wanted to get a subset, we could change a parameter.
		// The cursor acts like a buffer between the database and the user.
		cursor = database.query(PlannerDefaultQuestionTable.TABLE_PLANNER, new String[] {
				PlannerDefaultQuestionTable.COLUMN_ID, PlannerDefaultQuestionTable.COLUMN_QUESTION,
				PlannerDefaultQuestionTable.COLUMN_ANSWER }, null, null, null, null, null);

		length = cursor.getCount();
		cursor.close();
		return length;
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();

		// database.close(); // Closing database connection
	}

	public void close() {
		dbHelper.close();
	}

	public void saveAnswer(int id, int ans) {

		// get the database
		// SQLiteDatabase database = dbHelper.getWritableDatabase();
		// create a structure call contentValues that will contain value pairs
		// that go into a row in a database
		ContentValues values = new ContentValues();
		// create the answer content pair(the column and the value)
		values.put(PlannerDefaultQuestionTable.COLUMN_ANSWER, ans);

		// updating row directly without using a query or a cursor, so far we
		// are using cursors to get out subsets but writing
		// directly to the db. We will simplify as we learn more
		database.update(PlannerDefaultQuestionTable.TABLE_PLANNER, values,
				PlannerDefaultQuestionTable.COLUMN_ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Question getNextQuestion(int id) {
		Question question = null;
		// THis command is a query WITH a "filter or where statement". If we
		// Notice the columnid = id (that was passed in) We are getting one
		// record to pass back
		// The cursor acts like a buffer between the database and the user.
		cursor = database.query(PlannerDefaultQuestionTable.TABLE_PLANNER, new String[] {
				PlannerDefaultQuestionTable.COLUMN_ID, PlannerDefaultQuestionTable.COLUMN_QUESTION,
				PlannerDefaultQuestionTable.COLUMN_ANSWER }, PlannerDefaultQuestionTable.COLUMN_ID + " = "
				+ id, null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			ContentValues values = new ContentValues();
			question = new Question(Integer.parseInt(cursor.getString(0)),
					Integer.parseInt(cursor.getString(1)),
					Integer.parseInt(cursor.getString(2)));
		}
		cursor.close();
		return question;
	}

	// Not being used. It is useful to get an array of all questions
	public List<Question> getAll() {
		List<Question> questions = new ArrayList<Question>();

		Cursor cursor = database.query(PlannerDefaultQuestionTable.TABLE_PLANNER, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Question question = cursorToQuestion(cursor);
			questions.add(question);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return questions;
	}

	// this method takes the question and answer fields from the current row in
	// the result set(cursor structure)
	// and creates a questions. we may want an index eventually
	private Question cursorToQuestion(Cursor cursor) {
		Question question = new Question(0, 0, 0);
		question.setmId(cursor.getInt(0));
		question.setmQuestion(cursor.getInt(1));
		question.setmAnswer(cursor.getInt(2));

		return question;
	}

}
