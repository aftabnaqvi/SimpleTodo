SimpleTodo
==========

The SimpleTodo App is very basic Android app, which basically maintians the list of todo items. Currently, 
It persists the items list in text file on Android. You don’t need to worry about the items you may have stored in the app.

Features:
=========

Currently, App support following features.

1- You can add one item at a time in the todo app. 
2- You can modify any existing item by tapping the row. 

It opens up a new screen with an edit box where you update the item and tap on Save button to update it in the list, 
as well as it will store in the file. 

3- You can delete any row by tap+holding the row for little bit longer time. 
This app will store the updated list in the file.

Note: SimpleTodo app doesn’t allow duplicate items in the list.

Activities used:
================

I developed two Activities. 

1- TodoActivity - Responsible for main activity which displays the list of items, you can add, modify and delete the item. 
2- EditItemActivity - responsible for updating the selected item. Using EditItemActivity for adding a new item too. (Not a clean way but need to change this class to cleanly support both operations)

Helper classes:
===============
ItemData is a helper class which is responsible for passing the data to/from TodoActivity/EditItemActivity using intents.

SQLite:
=======

Added support for SQLite databse. Currenlty, I have only one table todo which stores all the detials about Todo item (id, summary, detail, staus and priority).

Newly added features:
=====================
Added support for priority. It manages the priority now.
Added additional attributes in the listView like item details, display priority and a check box which can tell todo item is completed or not.
Added support for the SQLite. todo list is persisted all the times, including its completed status.

Future Improvement:
===================
Try adding a background color of the completed todo items but there was some bug, comment the code for now, revisit it later on. 
Reused the edit_item activity to add an item as well. (Need some clean up there.)
Improve the performance. e.g. if user edit any todo item, and goes in edit activity, it shouldn't store data if its not modified. It will improve the perforce/battary life of the device. 

Need to store the priority as an integer in SQLite databse to avoid extra over head.

More testing is required too. Specially SQLite database operations related.

Adding support for Unit Testing:
================================
Need to figure out, how can I enable support for unit testing? currently, I have no idea about Android based unit testing.
