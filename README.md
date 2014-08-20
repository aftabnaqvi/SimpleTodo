{\rtf1\ansi\ansicpg1252\cocoartf1265\cocoasubrtf190
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\qc

\f0\fs24 \cf0 SimpleTodo App\
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural
\cf0 \
What is it?\
\'97\'97\'97\'97-\
The SimpleTodo App is very basic Android app, which basically maintians the list of todo items. Currently, It persists the items list in text file on Android. You don\'92t need to worry about the items you may have stored in the app.\
\
Features\
\'97\'97\'97\'97\
Currently, \
1- You can add one item at a time in the todo app. \
2- You can modify any existing item by tapping the row. It opens up a new screen with an edit box where you update the item and tap on Save button to update it in the list, as well as it will store in the file.\
3-  You can delete any row by tap+holding the row little bit longer time. This app will store the updated list in the file.\
\
Note: SimpleTodo app doesn\'92t allow duplicate items in the list.\
\
Activities used:\
\'97\'97\'97\'97\'97\'97\'97\
I developed two Activities.\
1- TodoActivity - Responsible for main activity which displays the list of items, you can add, modify and delete the item.\
2- EditItemActivity - responsible for updating the selected item. \
\
Helper classes:\
\'97\'97\'97\'97\'97\'97\'97\
ItemData is a helper class which is responsible for passing the data to/from TodoActivity/EditItemActivity using intents.\
\
Future Improvement:\
\'97\'97\'97\'97\'97\'97\'97\'97\'97\
Planning to use SQLite database in future to maintain the list. Also, polish the UI little bit more.\
}