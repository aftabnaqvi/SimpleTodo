package codepath.apps.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_TODO = "todo";
	public static final String COL_ITEM_ID = "item_id";
	public static final String COL_ITEM_SUMMARY = "item_summary";
	public static final String COL_ITEM_DETAIL = "item_detail";
	public static final String COL_ITEM_PRIORITY = "item_priority";
	public static final String COL_ITEM_STATUS = "item_status";
	
	// Database
	private static final String DATABASE_NAME = "todo.db";
	private static final int DATABASE_VERSION = 3;
	
	// table creation sql statement
	private static final String DATABASE_CREATE = "create table " + TABLE_TODO + "(" + COL_ITEM_ID 
			+ " integer primary key autoincrement, " 
			+ COL_ITEM_SUMMARY + " text not null, " 
			+ COL_ITEM_DETAIL + " text, " 
			+ COL_ITEM_PRIORITY + " text not null, " 
			+ COL_ITEM_STATUS + " integer);";
			
	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		database.execSQL(DATABASE_CREATE);
		System.out.println("SQLiteHelper: onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("SQLiteHelper: onUpgrade");
		Log.w(SQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
		onCreate(db);
	}
}
