
# Warmed-Inventory-App
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![NetBeans IDE](https://img.shields.io/badge/NetBeansIDE-1B6AC6.svg?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)

` pbo project ` - simple project based on java, using java swing and mysql for the database
![alt text](https://raw.githubusercontent.com/xXehub/warmed-inventory-app/main/screenshot/panel_user.png)
` about this project ` - group project created by **` sihub `** and **` deru `**. regarding the application to monitor storage at warung madura and also functions to make it easier for cashiers to do their business. 

## Features

- Role Login Admin / Kasir
- Dashboard
- CRUD User
- Kasir


## Library

Library that used on this program

```bash
  mysql.jdbc.driver
```


## Installation

- Install xampp [download here](https://www.apachefriends.org/download.html)
- Create a database **warmed-sakkarepmu** and import sql
- Open this project 
- Install mysql.jdbc.driver library [download here](https://www.cdata.com/kb/tech/mysql-jdbc-netbeans.rst)
- Run 

## Default Login

#### Admin

```http
  if (option.equalsIgnoreCase("Admin") && pilihan1.equalsIgnoreCase("admin")) 
```

| Usernane | Password     | Acces                |
| :-------- | :------- | :------------------------- |
| `admin` | `admin` | **Unlock**. All menu acces  |

#### Kasir 

```http
  else if (option.equalsIgnoreCase("kasir") && pilihan1.equalsIgnoreCase("kasir"))
```

| Username | Password     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `kasir`      | `kasir` | **The admin menu is locked**. only the menu for the cashier can be opened|





## Authors

- [@xXehub](https://www.github.com/xXehub)

