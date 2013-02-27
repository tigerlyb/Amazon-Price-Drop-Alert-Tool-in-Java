Amazon-Price-Drop-Alert-in-Java
===============================

Course Project

This program is designed by using JAVA language. There are two java files, email.java which is a java class using javaMail, and client.java which is a client program for catching price of a web link. The text file, Price Test, contains three web links from Amazon.com, price-thresholds and email addresses. At the beginning, the client program reads the first web link of an item from Amazon.com, catches the price of that item, compares it to the price-threshold read the text file. If the price is lower than the price you expected, the program will send email to the email address read from the text file and then read another web link from the text file. After reading all the three web links, the program will go back to read the text file again after 1 minute. In other words, the program can retrieve the item price once every one minute.
