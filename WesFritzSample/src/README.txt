           README.txt

TITLE:
Wes Fritz Sample Application -- Parcel Data with Database

PURPOSE:
An application used to display parcel information, as well as data relating to the owners and sales of the parcels.
To demonstrate skill levels in Java, JavaFX, databases, and SQL.

AUTHOR:
Wesley Fritz

DATE: 24 FEBRUARY 2023

IDE:
IntelliJ IDEA Community Edition 2022.2.3

FULL JDK OF VERSION USED:
JDK 19

JAVAFX VERSION COMPATIBLE WITH JDK VERSION:
JavaFX-SDK-19

DATABASE DRIVER VERSION:
MYSQL Workbench 8.0.31

MYSQL CONNECTOR DRIVER VERSION NUMBER:
mysql-connector-java-8.0.31


***DIRECTIONS ON HOW TO RUN PROGRAM:***
(Please run using IntelliJ.)

THE HOME PAGE:
Users can select from this screen if they wish to look at parcel data by street name and number, or by owners first name.
They can choose between these by clicking either the 'Street Name and Number' button, or the 'Owner's First Name' button.
Users can exit the application by clicking the 'Exit' button, and confirming their exit with the confirmation prompt.

THE BYSTREETNAME PAGE:
Users can view parcel data ordered primarily by the alphabetical order of street names, secondarily ordered by address number.
This view can be handy for meter reading and looking at regions of an area, as the streets are grouped together.
Users can return to the home page by clicking the 'Back to Home Page' button.

THE BYFIRSTNAME PAGE:
Users can view parcel data ordered alphabetically by the first names of parcel owners on this page.
LLCs have been grouped in their own alphabetical order at the bottom of the table, to differentiate between residential and commercial.
This view can prove useful for utilities billing functions, because same people may own separate parcels.
Users can use an attached search bar to assist with ease of function.
Users can return to the home page by clicking the 'Back to Home Page' button.

USING THE SEARCH BAR ON THE BYFIRSTNAME PAGE:
Users can search for owner names either in full, or partially.
********* The search function IS case sensitive! ************
(*****There is one error I couldn't solve, which deals with an invocation exception on line 92 of my byFirstNameController.
It triggers when somebody is searched that doesn't exist in the database, or if lowercase is used instead of uppercase for the search.******)
Once a user has typed what they wish to search for, they need to press 'Enter' on their keyboard to search.
To return all values to the table again, they need to hit the 'Enter' key with the search bar empty.

PERSONAL NOTES:
In a normal project setting, I would ask for a lot more clarification to make sure I was going in the right direction.
For the purposes of you all finding the right fit, I figured it would be more beneficial to you to see what my 'raw' interpretation of the project was.
This is my best shot, using all the resources at my disposal.

I did make small adjustments to the data.
- The entry for 632 LOST RIVER RD contained a dash within it, so I took that out in SQL.
- Several other entries for LOST RIVER RD spaces between their apartment/suite numbers and letter designators, so I removed the space to alphabetize correctly.
- Same issue for a couple of ARNOLD RD entries.
- MISTY CIR had an occasional zero left at the end of a few entries, so I removed those to alphabetize correctly.

I also noticed there were a few duplicate entries in the data. I used the 'DISTINCT' option in my queries to avoid same entries.

I looked high and low for different ways to implement clickable links in IntelliJ tables for the extra credit.
I couldn't find options that would work, so I decided it would be better to submit something completed instead of half-finished.

If you have any pointers or things I could improve upon, feel free to reach out to me via email! I'm always open to learning something new!

Thank you for your time,
Wes