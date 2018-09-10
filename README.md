# Biblioteca

Biblioteca is a console application for a public Library that helps librarians and users to check in and out books in the library.

## Installation

### Requirements
* Java 8

You can download JDK from the official [Oracle Site](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 

## Usage

Main menu from Biblioteca let you choose 2 options:

```Java
Welcome to Biblioteca :)
Please Select an Option
1 List of available books
2 Exit :(             
Please select an option: 
```
You will be asked to select an option using the numbers of each menu item.

_List of available books_ will show available books with 3 options:
```Java
This is a list of available books:
|N |Author              |Book                               |Year |
|1 |Plato               |Republic                           |1984 |
|2 |Camilo Jose Cela    |Colmena                            |1950 |
|3 |Camilo Jose Cela    |Colmena                            |1950 |
Please Select an Option
1 CheckOut a Book     
2 CheckIn a Book      
3 Exit :(         
```
_CheckOut a Book_ will display a dialogue to select a book. Once we enter the number of the book as displayed above, if there was a successful checkout, the following message is displayed

```Java
Thank you! Enjoy the book
```
The main menu will be displayed again.

_CheckIn a Book_ will display a list of books available for checking in, then a dialogue will be displayed asking for a book number. 
```Java
This is a list of books you can return:
|N |Author              |Book                                |Year |
|1 |Plato               |Republic                            |1984 |
|2 |Michel Foucault     |The Order of Things                 |1966 |
Please enter a book number: 
```
If there was a successful checkin, the following message will be displayed followed by the main menu:
```
Thank you for returning the book.
``` 
## License
[Apache License 2.0](https://github.com/sheryluDuck/twu-biblioteca-fernandavega/blob/master/LICENSE)

