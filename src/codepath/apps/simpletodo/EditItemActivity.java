package codepath.apps.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class EditItemActivity extends Activity {

	private EditText etEditItem = null;
	private ItemData itemData = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		
		etEditItem = (EditText) findViewById(R.id.etEditItem);
		itemData =  (ItemData)getIntent().getSerializableExtra("edititem");
		etEditItem.setText(itemData.getItemName());
		
		// move the contents of the Activity little bit up to avoid overlapping. 
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}
	
	@Override
	public void finish(){
		Intent intent = new Intent(this, TodoActivity.class);
		itemData.setItemName(etEditItem.getText().toString().trim());
		intent.putExtra("edititem", itemData);

		// Activity finished, return the intent
		setResult(RESULT_OK, intent);
		super.finish();
	}
	
	public void onSave(View v){
		finish();
	}
}
