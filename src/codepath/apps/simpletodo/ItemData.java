package codepath.apps.simpletodo;

import java.io.Serializable;

/**
 * ItemData class contains the information about the item. 
 * item contains String and its index. This is being used to pass the data about the 
 * item via intent to/from TodoActivity/EditItemActivity. 
 * Index will will help us to avoid searching the String within items ArrayList.
 * 
 * **/
public class ItemData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6709915632077748220L;

	private String itemName;
	private int itemIndex; // itemIndex will be used as a key, so when we will get the modified data back from 
	// EditItemActivity, we can identify which item needs to be updated in items ArrayList. 
	
	ItemData(String itemName, int itemIndex){
		this.setItemName(itemName);
		this.setItemIndex(itemIndex);
	}

	public int getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(int itemIndex) {
		this.itemIndex = itemIndex;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
