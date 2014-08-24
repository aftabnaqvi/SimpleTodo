package codepath.apps.simpletodo;

import java.util.ArrayList;

import codepath.apps.dao.sqlite.TodoDataSource;
import codepath.apps.dao.sqlite.TodoItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ListView;

class Operation{
	public static final String ADD_ITEM = "add_item";
	public static final String EDIT_ITEM = "edit_item";
}

public class TodoActivity extends Activity {
	private TodoItemAdapter itemsAdapter;
	private ListView lvItems;
	private int currentListSelectedIndex = -1;
	private TodoDataSource datasource;
	private CheckBox cbItemStatus = null;
	
	private static final int REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        
        datasource = new TodoDataSource(this);
        datasource.open();

        ArrayList<TodoItem> allTodos = datasource.getAllTodos();
        
        lvItems = (ListView)findViewById(R.id.lvItems);

        itemsAdapter = new TodoItemAdapter(this, allTodos, datasource);
        setupListViewListener();
        lvItems.setAdapter(itemsAdapter);

        // move the contents of the Activity little bit up to avoid overlapping.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        
        lvItems.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
 
    /*
     * setting up the listView listeners. 
     * 
     * */
    private void setupListViewListener(){
    	lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				TodoItem todoItem = (TodoItem)itemsAdapter.getItem(position);
				itemsAdapter.removeItem(position);
    			itemsAdapter.notifyDataSetInvalidated();
    			datasource.deleteItem(todoItem);
    			return true;
			}
    	});
    	
    	lvItems.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TodoItem todoItem = (TodoItem)itemsAdapter.getItem(position);
				Intent intent = new Intent(TodoActivity.this, EditItemActivity.class); 
				intent.putExtra (Operation.EDIT_ITEM, todoItem);
				currentListSelectedIndex = position;
				
				//identify the callback via this code
				startActivityForResult(intent, REQUEST_CODE);
			}
    	});
    }
  
    /**
     * 
     * Adding an item to a list.
     * 
     * **/
    public void addTodoItem(View v){
    	TodoItem todoItem = null;
		Intent intent = new Intent(TodoActivity.this, EditItemActivity.class); 
		intent.putExtra (Operation.ADD_ITEM, todoItem);
		
		//identify the callback via this code
		startActivityForResult(intent, REQUEST_CODE);

    	// improvement.
    	// We should select the row if we find that item already exists in the listView.
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
    		if ( (data != null) && data.hasExtra(Operation.EDIT_ITEM) ) {
    			TodoItem todoItem = (TodoItem)data.getSerializableExtra(Operation.EDIT_ITEM);
    			if( todoItem != null ){
    				// improvement: performance.
    				// we should check if item has really modified before storing in database and adapter.
    				datasource.updateItem(todoItem);
    				itemsAdapter.updateItem(todoItem, currentListSelectedIndex);
    		    	itemsAdapter.notifyDataSetChanged();
    			}
    			else{
    				// log error...
    			}
    		}
    		else if( (data != null) && data.hasExtra(Operation.ADD_ITEM)) {
    			TodoItem todoItem = (TodoItem)data.getSerializableExtra(Operation.ADD_ITEM);
    			if( todoItem != null && !todoItem.getItemSummary().isEmpty() ){
    				datasource.createItem(todoItem);
    				itemsAdapter.addItem(todoItem);
    		    	itemsAdapter.notifyDataSetChanged();
    			}
    			else{
    				// log error...
    			}	
    		}
    	}
    } 

    @Override
	public void finish(){
    	datasource.close();
		super.finish();
	}
}
