package codepath.apps.simpletodo;

import java.util.ArrayList;
import java.util.List;

import codepath.apps.dao.sqlite.TodoItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/*
 * Initially, I planned to use this class to edit the selected item only. During enhancement, I realized that I can use this class
 * for adding a new item too. For now using for both but later on will change the name of the activity.
 * **/
public class EditItemActivity extends Activity implements OnItemSelectedListener{

	private EditText etItemSummary = null;
	private EditText etItemDetail = null;
	private Spinner spinnerItemPriority = null;
	private TodoItem todoItem = null;
	private int prioritySelectedIndex;
	private String currentOperation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		
		// Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinnerItemPriority);
 
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
 
        // Spinner Drop down elements
        List<String> priorities = new ArrayList<String>();
        priorities.add("High");
        priorities.add("Med");
        priorities.add("Low");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, priorities);
 
        // Drop down layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        
        etItemSummary = (EditText) findViewById(R.id.etItemSummary);
        etItemDetail = (EditText) findViewById(R.id.etItemDetail);
        spinnerItemPriority = (Spinner) findViewById(R.id.spinnerItemPriority);
        
        todoItem =  (TodoItem)getIntent().getSerializableExtra(Operation.EDIT_ITEM);
		if(todoItem != null){
			tvTitle.setText(getString(R.string.edit_item));
			btnSave.setText(getString(R.string.update));
			currentOperation = Operation.EDIT_ITEM;
			etItemSummary.setText(todoItem.getItemSummary());
			etItemDetail.setText(todoItem.getItemDetail());
			//spinnerItemPriority.setTag(todoItem.getItemPriority());
			int spinnerPos = dataAdapter.getPosition(todoItem.getItemPriority());
			spinnerItemPriority.setSelection(spinnerPos);
			return; // we don't need to check for add now.
		}
		else{
			// error log.. something went wrong.
		}
		
		todoItem =  (TodoItem)getIntent().getSerializableExtra(Operation.ADD_ITEM);
		if(todoItem == null){
			tvTitle.setText(getString(R.string.add_item));
			btnSave.setText(getString(R.string.add));
			currentOperation = Operation.ADD_ITEM;
			todoItem = new TodoItem();
			return; // expected null in case of add operation. don't need to check for edit. May need to revisit and clean it. 
		}	
	}
	
	@Override
	public void finish(){
		Intent intent = new Intent(this, TodoActivity.class);
		todoItem.setItemSummary(etItemSummary.getText().toString().trim());
		todoItem.setItemDetail(etItemDetail.getText().toString().trim());
		todoItem.setItemPriority(spinnerItemPriority.getItemAtPosition(prioritySelectedIndex).toString().trim());
		
		intent.putExtra(currentOperation, todoItem);

		// Activity finished, return the intent
		setResult(RESULT_OK, intent);
		super.finish();
	}
	
	public void onSave(View v){
		finish();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        prioritySelectedIndex = position;
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
