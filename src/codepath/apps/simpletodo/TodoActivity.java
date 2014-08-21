package codepath.apps.simpletodo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class TodoActivity extends Activity {

	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;
	
	private static final int REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        
        readItems();
        
        lvItems = (ListView)findViewById(R.id.lvItems);
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        
        setupListViewListener();
        
        // move the contents of the Activity little bit up to avoid overlapping.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        
        lvItems.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
    
    /*
     * setting up the listView listeners. 
     * 
     * */
    private void setupListViewListener(){
    	lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				items.remove(position);
    			itemsAdapter.notifyDataSetInvalidated();
    			saveItems();
    			return true;
			}
    	});
    	
    	lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// position is being used as a key, so when we will get the modified data back from 
				// EditItemActivity, we can identify which item needs to be updated. 
				ItemData itemData = new ItemData(items.get(position), position);
				Intent intent = new Intent(TodoActivity.this, EditItemActivity.class); 
				intent.putExtra("edititem", itemData);

				// you can identify the callback via this code
				startActivityForResult(intent, REQUEST_CODE);
			}
    	});
    }
    
    /**
     * 
     * reading items from file.
     * 
     * 
     * **/
    private void readItems(){
    	File filesDir = getFilesDir();
    	File todoFile = new File(filesDir, "todo.txt");
    	Scanner scanner = null;
    	try{
    		scanner = new Scanner(todoFile);
    		items = new ArrayList<String> ();
    		while(scanner.hasNextLine()){
    			items.add(scanner.nextLine());
    		}
    	}
    	catch(IOException e){
    		items = new ArrayList<String>();
    		e.printStackTrace();
    	}
    }
    	
    /**
     * 
     * saving items into a file.
     * **/
    private void saveItems(){
    	File filesDir = getFilesDir();
    	File todoFile = new File(filesDir, "todo.txt");
    	
    	try{
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(todoFile)));
    		int size = items.size();
    		for(int i=0; i<size; i++){
    			bw.write(items.get(i));
    			bw.newLine();
    		}
    		bw.close();
    	}
    	catch(IOException e){
    		items = new ArrayList<String>();
    		e.printStackTrace();
    	}
    }
  
    /**
     * 
     * Adding an item to a list.
     * 
     * **/
    public void addTodoItem(View v){
    	EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
    	String strItem = etNewItem.getText().toString().trim();
    	int itemIndexFound = items.indexOf(strItem);
    	if(!strItem.isEmpty() && itemIndexFound == -1){
    		itemsAdapter.add(strItem);
    		saveItems();
    	}
    	
    	// improvement.
    	// We should select the row if we find that item already exists in the listView.
    		
    	etNewItem.setText("");
    }
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
    		if ( (data != null) && data.hasExtra("edititem")) {
        	
    			ItemData itemData = (ItemData)data.getSerializableExtra("edititem");
        	
    			// if item is not modified then we will update the item in the list. 
    			if( (itemData != null) && itemData.getItemName().compareTo(items.get(itemData.getItemIndex())) != 0){
	        		// updating our items ArrayList with modified value.
	        		items.set(itemData.getItemIndex(), itemData.getItemName());
	        		
	        		// notify to list adapter.
	        		itemsAdapter.notifyDataSetInvalidated();
	        		
	        		saveItems(); // save items in file.
    			}
    		}
    	}
    } 
}
