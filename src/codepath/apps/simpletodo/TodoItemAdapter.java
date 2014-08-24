package codepath.apps.simpletodo;

import java.util.ArrayList;
import java.util.Collections;

import codepath.apps.dao.sqlite.TodoDataSource;
import codepath.apps.dao.sqlite.TodoItem;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

@SuppressLint("CutPasteId") public class TodoItemAdapter extends BaseAdapter {

	 private class ViewHolder {
		  CheckBox cbStatus;
		 }
	 
	// declaring our ArrayList of TodoItem
	private ArrayList<TodoItem> objects;
	private Context context;  
	private TodoDataSource datasource;
	/* here we must override the constructor for ArrayAdapter
	* the only variable we care about now is ArrayList<TodoItem> objects,
	* because it is the list of objects we want to display.
	*/
	public TodoItemAdapter(Context context, ArrayList<TodoItem> objects, TodoDataSource datasource) {
		this.objects = objects;
		this.context = context;
		this.datasource = datasource;
	}
	
	// add an item.....
	public void addItem(TodoItem item){
		objects.add(item);
		Collections.sort(objects);
	}
	
	// update an item in the adapter
	public void updateItem(TodoItem todoItem, int currentListSelectedIndex){
		objects.set(currentListSelectedIndex, todoItem);
		Collections.sort(objects);
	}
	
	// update an item in the adapter
	public void removeItem(int currentListSelectedIndex){
		objects.remove(currentListSelectedIndex);
	}
	
	/*
	 * we are overriding the getView method here - this is what defines how each
	 * list item will look.
	 */
	public View getView(final int position, final View convertView, ViewGroup parent){

		// assign the view we are converting to a local variable
		View view = convertView;

		final ViewHolder holder;
		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.todo_list, null);
			holder = new ViewHolder();
			
			holder.cbStatus = (CheckBox) view.findViewById(R.id.cbItemStatus);
			view.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
			
		TodoItem todoItem = objects.get(position);

		if (todoItem != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.
			TextView tvItemSummary = (TextView) view.findViewById(R.id.tvItemSummary);
			CheckBox cbItemStatus = (CheckBox) view.findViewById(R.id.cbItemStatus);
			TextView tvItemPriority = (TextView) view.findViewById(R.id.tvItemPriority);
			TextView tvItemDetail = (TextView) view.findViewById(R.id.tvItemDetail);
			
			if (tvItemSummary != null){
				tvItemSummary.setText(todoItem.getItemSummary());
			}
			if(cbItemStatus != null){
				boolean itemStatus = todoItem.getItemStatus();
//				if(itemStatus == true){
//					view.setBackgroundColor(Color.parseColor("#A4C739"));
//				}
//				else{
//					view.setBackgroundColor(Color.WHITE);	
//				}
				cbItemStatus.setChecked(itemStatus);
			}
			if (tvItemPriority != null){
				tvItemPriority.setText(todoItem.getItemPriority());
			}
			if (tvItemDetail != null){
				tvItemDetail.setText(todoItem.getItemDetail());
			}
		}

		holder.cbStatus.setOnClickListener(new OnClickListener() {
			   public void onClick(View v) {
			    // TODO Auto-generated method stub
				   TodoItem todoItem = ((TodoItem)objects.get(position));
				   if(todoItem != null){
					   todoItem.setItemStatus(holder.cbStatus.isChecked()?1:0);
					   datasource.updateItem(todoItem);
//					   if(convertView != null){
//						   if(todoItem.getItemStatus() == true){
//							   convertView.setBackgroundColor(Color.parseColor("#A4C739"));
//						   }
//						   else{
//							   convertView.setBackgroundColor(Color.WHITE);
//						   }
//					   }
				   }
			   }
			  });
		
		// the view must be returned to our activity
		return view;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objects.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

}