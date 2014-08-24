package codepath.apps.dao.sqlite;

import java.io.Serializable;

public class TodoItem implements Serializable, Comparable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String itemSummary;
	private String itemDetail;
	private String itemPriority;
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
	 * @param itemPriority the itemPriority to set
	 */
	public void setItemPriority(String itemPriority) {
		this.itemPriority = itemPriority;
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
	public int compareTo(Object another) {
		if (another instanceof TodoItem){
			TodoItem anotherTodoItem = (TodoItem)another;
			if( anotherTodoItem.itemPriority.compareToIgnoreCase(itemPriority) == 0) {
				return 0;
			}
			else if(itemPriority.compareToIgnoreCase(anotherTodoItem.itemPriority) > 0){
				return 1;
			}
			else{
				return -1;
			}
		}
		return 0;
	}
}
