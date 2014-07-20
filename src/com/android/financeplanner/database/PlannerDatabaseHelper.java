package com.android.financeplanner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlannerDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "drewPlanner2.db";
	private static final int DATABASE_VERSION = 1;

	public PlannerDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Method is called during the creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		PlannerDefaultQuestionTable.onCreate(database);
	}

	// Method is called during an upgrade of the database, e.g. if you increase
	// the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		PlannerDefaultQuestionTable.onUpgrade(database, oldVersion, newVersion);
	}

}
