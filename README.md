## Introduction

This software will allow an adminisrator to administer and manage internet subscriptions in the residence of "La futaie". The software will be developed with Java using the MVC architecture.

This application will only be avaialble for the administration of the residences. A normal user's interaction will be done through a web interface created with Laravel. The database related to the software respects the laravel naming conventions.

## Roles

- Administrator

## Database Models

As stated above, the software will be developed with the MVC architecture meaning that models containing the data of our database will be used.

**Here is a list of the current list of models the software uses :**

- **User** (User information)
- **Room** (Room information)
- **Establishment** (A room is related to an establishment, **this will be used less often**)
- **Outlet** (Information about the outlets in the room)
- **OperationSystem**(Information regarding the OS the resident is using on his machine)
- **AntiVirus**(information regarding the antivirus the resident is using on his machine)
- **OsAntiVirus**(Information regarding which OS and antivirus the resident has. Modelized in the database in a way that the resident only has one OS and antivirus.)
- **Products** (Information about products that can be sold to the resident)
- **Acquires** (Information about the products the user acquired)
- **(PaymentMethods**((Information about the different payment methods possible for the subscription)
- **Subscribes**(Allows us to know which resident is subscribed to the internet allocation program, which method of payment they used and at what date they got their subscription).

Each row added to the database will have a "created at" and "updated at" column that will update with the use of a trigger automatically.

## Envisioned Functions

This section will describe the different functoinalities that will be developed during the creation of the software.

### Functionnement of the application

This application allows an adminisrator to manage information related to its users. They will be able to modify, create, delete and see the information of a user and change information accordingly.

Upon opening the application the administrator will be met with a home page and a navigation bar. The navigaton bar will contain different menus. The list of the menus are the following :

- Users
- Rooms
- Outlets
- Payment methods
- Orders
- Products
- OS/Antivirus

Upon clicking on each menu they will be lead to an interface allowing them to see all of the existing entities of the interface they clicked on. For example upon clicking on the outlets menu they will be redirected to a page showing them a list of all existing outlets. They will then be able to add, delete

**The detail on the different functions will be found below.**

* * *

### Authentification, session and access levels

This application contains an authentification interface that will only allow users with the adminisrator role to log in.

Upon the creation of the application a admin user will automatically be created with a basic password.

### User management (CRUD)

This function will allow the adminisrator to see all of the existing users in the application and to manipulate all of the information related to a user.

Upon clicking on the "User" menu from the interface the adminisrator will see a list of the users with 3 different buttons on the side, "Delete" to delete a user, "Add" to create a new user and "Detail" to consult the user's information. And on top of that if the user is currently subscribed or not.

Upon pressing the "Detail" button the administrator will be prompted to a new window containing the information of the user (Email, name, room number, renting dates, outlet activated...) and if the user is currently a resident or not. On the right side will have multiple buttons that will allow the user to manipulate and access specific information regarding the user.

- "Orders" button to consult the different orders the user has and its payment methods and price of the order.
- "Hardware/Software" button to consult the different computers the user owns and the antiviruses and OS they use.
- "Products" button to consult which products and orders the user bought from the store.

**note**  
The password of the user will not be shown directly to an adminisrator, it will be in its hased value and the adminisrator will have to generate a randomly generated password that they will not know about.

### Room management (CRUD)

This function allows the administrator to manipulate information related to the different rooms in the residency.

Upon entering the page from the menu the adminisrator will see a list of the existing rooms in the residency and will be able to add modify and see the detail of each room using buttons on the right side of the list.  
Upon clicking on the "Detail" button to consult the information of each room they will be able to consult which residents are currently renting the room, the max capacity of the room and the different outlets in the room.

### Outlet management (CRUD)

This function allows the administrator to manipulate information related to the different outlets in the residency.

Upon entering the page from the menu the administrator will see a list of the existing outlets and will be able to consult add outlets and delete each one of them.

Upon consulting an outlet the administrator will be able to consult the id of the outlet, which room it is attributed to and which subscriptions it was related to and modify this information.

### Payment methods (CRUD)

This function will allow an adminisrator to modify add consult and delete payment methods.

Upon entering the payment method interface from the menus the administrator will be able to see the different payment methods, see the detail of each one modify it and also delete / add new payment methods if needed.

Some payment methods will be pre included upon the installation of the application.

### Product management (CRUD)

This function will allow an administrator to manipulate information regarding the different products sold buy the residency

Upon accessing the products interface from the menu, the administrator will be able to see the different products that are being sold, add and delete products and consult the different information of each product. While consulting the administrator will be able to see how many times it was bought, the history of purchase (which user bought it) and the prices of the orders in which it was related.

### Order management (CRUD)

This function will allow and adminisrator to manipulate information related to the different product orders that were ordered by users.

Upon entering the orders interface from the menu, the administrator will see a list of orders and will be able to consult them delete or add a new one. Upon consulting the the order they will be able to see which producters were bought, for how much and by which user.

If the order is a subscription then the adminisrator will be able to see which pc is related to the subscription and what OS and Antivirus it has.

### Hardware/Software management (CRUD)

This function will allow the adminisrator to manipulate information related to computers OS and Antivruses.

Upon hovering the "Hardware/Software" button in the menu, the administrator will see 3 different tabs, Computers, OS, Antiviruses. Upon clicking on each one they will see the list of the element they chose with the option to add, delete or consult and modify each different entity.
