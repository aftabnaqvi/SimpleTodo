SimpleTodo
==========

The SimpleTodo App is very basic Android app, which basically maintians the list of todo items. Currently, 
It persists the items list in text file on Android. You don’t need to worry about the items you may have stored in the app.

Features:
=========

Currently, 
1- You can add one item at a time in the todo app. 
2- You can modify any existing item by tapping the row. 

It opens up a new screen with an edit box where you update the item and tap on Save button to update it in the list, 
as well as it will store in the file. 3- You can delete any row by tap+holding the row little bit longer time. 
This app will store the updated list in the file.

Note: SimpleTodo app doesn’t allow duplicate items in the list.

Activities used:
================

I developed two Activities. 

1- TodoActivity - Responsible for main activity which displays the list of items, you can add, modify and delete the item. 
2- EditItemActivity - responsible for updating the selected item.

Helper classes:
===============
ItemData is a helper class which is responsible for passing the data to/from TodoActivity/EditItemActivity using intents.

Future Improvement:
===================
Planning to use SQLite database in future to maintain the list. Also, polish the UI little bit more.
