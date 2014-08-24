package codepath.apps.dao.sqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class TodoDataSource {

	  // Database fields
	  private SQLiteDatabase database;
	  private SQLiteHelper dbHelper;
	  private String[] allColumns = { SQLiteHelper.COL_ITEM_ID,
			  SQLiteHelper.COL_ITEM_SUMMARY, SQLiteHelper.COL_ITEM_DETAIL, 
			  SQLiteHelper.COL_ITEM_PRIORITY, SQLiteHelper.COL_ITEM_STATUS };

	  public TodoDataSource(Context context) {
		  dbHelper = new SQLiteHelper(context);
	  }

	  public void open() throws SQLException {
		  database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
		  dbHelper.close();
	  }
		
	  public TodoItem createItem(String itemSummary, String itemDetail, String itemPriority, int itemStatus) {
		  ContentValues values = new ContentValues();
		  values.put(SQLiteHelper.COL_ITEM_SUMMARY, itemSummary);
		  values.put(SQLiteHelper.COL_ITEM_DETAIL, itemDetail);
		  values.put(SQLiteHelper.COL_ITEM_PRIORITY, itemPriority);
		  values.put(SQLiteHelper.COL_ITEM_STATUS, itemStatus);
			
		  long insertId = database.insert(SQLiteHelper.TABLE_TODO, null,
			    values);
		  Cursor cursor = database.query(SQLiteHelper.TABLE_TODO,
				  allColumns, SQLiteHelper.COL_ITEM_ID + " = " + insertId, null,
				  null, null, null);
		    
		  cursor.moveToFirst();
		  TodoItem newTodo = cursorToTodoItem(cursor);
		  cursor.close();
		  return newTodo;
	  }
	  
	  // need to combine createItem methods.
	  public TodoItem createItem(TodoItem todoItem){
		  ContentValues values = new ContentValues();
		  values.put(SQLiteHelper.COL_ITEM_SUMMARY, todoItem.getItemSummary());
		  values.put(SQLiteHelper.COL_ITEM_DETAIL, todoItem.getItemDetail());
		  values.put(SQLiteHelper.COL_ITEM_PRIORITY, todoItem.getItemPriority());
		  values.put(SQLiteHelper.COL_ITEM_STATUS, todoItem.getItemStatus());
			
		  long insertId = database.insert(SQLiteHelper.TABLE_TODO, null,
			    values);
		  Cursor cursor = database.query(SQLiteHelper.TABLE_TODO,
				  allColumns, SQLiteHelper.COL_ITEM_ID + " = " + insertId, null,
				  null, null, null);
		  
		  cursor.moveToFirst();
		  TodoItem newTodo = cursorToTodoItem(cursor);
		  cursor.close();
		  
		  return newTodo;
	  }
	  
	  public void deleteItem(TodoItem todo) {
	    long id = todo.getId();
	    System.out.println("item deleted with id: " + id);
	    database.delete(SQLiteHelper.TABLE_TODO, SQLiteHelper.COL_ITEM_ID
	        + " = " + id, null);
	  }

	  public void updateItem(TodoItem todo) {
	    long id = todo.getId();
	    System.out.println("item updated with id: " + id);
	    ContentValues values = new ContentValues();
		  values.put(SQLiteHelper.COL_ITEM_SUMMARY, todo.getItemSummary());
		  values.put(SQLiteHelper.COL_ITEM_DETAIL, todo.getItemDetail());
		  values.put(SQLiteHelper.COL_ITEM_PRIORITY, todo.getItemPriority());
		  values.put(SQLiteHelper.COL_ITEM_STATUS, todo.getItemStatus());
		  
	    database.update(SQLiteHelper.TABLE_TODO, values, SQLiteHelper.COL_ITEM_ID + " = " + todo.getId(), null);
	  }
	  
	  public ArrayList<TodoItem> getAllTodos() {
		  ArrayList<TodoItem> allTodoItems = new ArrayList<TodoItem>();

	    Cursor cursor = database.query( SQLiteHelper.TABLE_TODO,
	        allColumns, null, null, null, null, SQLiteHelper.COL_ITEM_PRIORITY);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	TodoItem todoItem = cursorToTodoItem(cursor);
	    	allTodoItems.add(todoItem);
	    	cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return allTodoItems;
	  }

	  private TodoItem cursorToTodoItem(Cursor cursor) {
		  TodoItem todoItem = new TodoItem();
		  todoItem.setId(cursor.getLong(0));
		  todoItem.setItemSummary(cursor.getString(1));
		  todoItem.setItemDetail(cursor.getString(2));
		  todoItem.setItemPriority(cursor.getString(3));
		  todoItem.setItemStatus(cursor.getInt(4));
		  
	    return todoItem;
	  }
	} 
