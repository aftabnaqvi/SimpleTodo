package codepath.apps.dao.sqlite;

import java.io.Serializable;

public class TodoItem implements Serializable, Comparable<TodoItem>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String itemSummary;
	private String itemDetail;
	private String itemPriority;
	private int itemPriorityInt;
	private boolean itemStatus;
	
	public TodoItem() {
		
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the itemSummary
	 */
	public String getItemSummary() {
		return itemSummary;
	}
	/**
	 * @param itemSummary the itemSummary to set
	 */
	public void setItemSummary(String itemSummary) {
		this.itemSummary = itemSummary;
	}
	
	/**
	 * @return the itemDetails
	 */
	public String getItemDetail() {
		return itemDetail;
	}
	
	/**
	 * @param itemDetails the itemDetails to set
	 */
	public void setItemDetail(String itemDetails) {
		this.itemDetail = itemDetails;
	}
	
	/**
	 * @return the itemPriority
	 */
	public String getItemPriority() {
		return itemPriority;
	}
	
	/**
	 * @return the itemPriorityInt: returns int version of Priority.
	 * Might be removed when we will change the priority in database.
	 */
	public int getItemPriorityInt() {
		return itemPriorityInt;
	}
	
	/**
	 * @param itemPriority the itemPriority to set
	 */
	public void setItemPriority(String itemPriority) {
		this.itemPriority = itemPriority;
		if("High".compareToIgnoreCase(itemPriority) == 0){
			this.itemPriorityInt = 0;
		}
		else if("Med".compareToIgnoreCase(itemPriority) == 0){
			this.itemPriorityInt = 1;
		}
		else if("Low".compareToIgnoreCase(itemPriority) == 0){
			this.itemPriorityInt = 2;
		}
	}
	
	/**
	 * 
	 * @return the itemStatus
	 */
	public boolean getItemStatus() {
		return itemStatus;
	}
	
	/**
	 * @param itemStatus the itemStatus to set
	 */
	public void setItemStatus(int itemStatus) {
		this.itemStatus = itemStatus==0 ? false: true;
	}
	
	@Override
	public int compareTo(TodoItem another) {
		if (another instanceof TodoItem){
			TodoItem anotherTodoItem = (TodoItem)another;
			if(itemPriorityInt == anotherTodoItem.itemPriorityInt){
				return 0;
			}
			else if(itemPriorityInt > anotherTodoItem.itemPriorityInt){
				return 1;
			}
			else if(itemPriorityInt < anotherTodoItem.itemPriorityInt){
				return -1;
			}
		}

		return 0;
	}
}
