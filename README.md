# library
Чтобы запустить проект, вам нужно:

1)Создать базу данных в PostgreSQL, подключить ее к intellij idea, при подключении запишите URL, password и username. Затем заполнить файл hibernate.properties.origin из папки src/resources и изменить его название на hibernate.properties.
Так же нужно создать таблицы, используя SQL-команды из файла sqlQuery.sql.

2)Настроить запуск приложения в intellij idea.
Для этого добавьте Tomcat Server Local версии ниже 10, далее нажмите 'Fix' и выберите war exploded, в ячейке 'Application context' выставите пустую стоку. Далее нажмите 'Apply', замем 'OK'.

