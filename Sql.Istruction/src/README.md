
Enter password: **********************************
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 21
Server version: 8.0.34 MySQL Community Server - GPL

Copyright (c) 2000, 2023, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> SELECT User from mysql.user;
+------------------+
| User             |
+------------------+
| admin            |
| develhopee       |
| mysql.infoschema |
| mysql.session    |
| mysql.sys        |
| root             |
| viewer           |
| writer           |
+------------------+
8 rows in set (0.00 sec)

mysql> CREATE USER 'developer'@'localhost' IDENTIFIED BY '1122';
Query OK, 0 rows affected (0.01 sec)

mysql> CREATE ROLE 'app_developer' , 'app_read , 'app_write';
    '> END
    '> ^]^C
mysql> CREATE ROLE 'app_developer','app_read','app_write';
Query OK, 0 rows affected (0.01 sec)

mysql> GRANT ALL ON *.* TO 'app_developer';
Query OK, 0 rows affected (0.00 sec)

mysql> GRANT SELECT ON *.* TO 'app_read';
Query OK, 0 rows affected (0.00 sec)

mysql> GRANT INSERT, UPDATE, DELETE ON *.* TO 'app_write';
Query OK, 0 rows affected (0.00 sec)

mysql> GRANT 'app_developer' TO 'developer'@'localhost';
Query OK, 0 rows affected (0.00 sec)

mysql> GRANT 'app_read' TO 'viewer'@localhost';
    '> ^C
mysql> GRANT 'app_read' TO 'viewer'@localhost';
    '> ^C
mysql> GRANT 'app_read' TO 'viewer'@'localhost';
Query OK, 0 rows affected (0.00 sec)

mysql> GRANT 'app_write' TO 'write'@'localhost';
ERROR 3523 (HY000): Unknown authorization ID `write`@`localhost`
mysql> GRANT 'app_write' TO 'writer'@'localhost';
Query OK, 0 rows affected (0.00 sec)

mysql> CREATE DATABASE IF NOT EXISTS newdb;
Query OK, 1 row affected (0.01 sec)

mysql> GRANT ALL PRIVILEGES ON newdb.* TO 'developer'@'localhost';
Query OK, 0 rows affected (0.00 sec)

mysql> GRANT SELECT ON newdb.* TO 'viewer'@'localhost';
Query OK, 0 rows affected (0.00 sec)

mysql> GRANT INSERT, UPDATE, DELETE ON newdb.* TO 'writer'@localhost';
    '> ^C
mysql> GRANT INSERT,UPDATE,DELETE ON newdb.* TO 'writer'@'localhost';
Query OK, 0 rows affected (0.00 sec)
=======
# Sql.Repo
Contenuto riguardante SQL
 c8c65a229480b3a247f8813c39e6b78f9edc82a9
