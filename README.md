"# E-commerce" 
This is a small project with Spring, Hibernate and Maven.  
It shows how does e-shop work. 
With some features that are provided by mentoioned technologies. 
Also SQL-script file is included, it contains neccessary tables and
starting package of 3 users with 3 roles(ROLE_USER, ROLE_ADMIN, ROLE_SUPERADMIN),
all of them have the same BCrypted password (12345).
E.X. 
1 L - maksim-a@tut.by P - 12345
2 L - admin@tut.by P - 12345
3 L - superadmin@tut.by P - 12345
Also project includes java mailing system, so additional methods provided.
Such as 'restore password' and 'confirm creating' of account through email. 
Also there is a possibility to add new items in your shop and copy existing ones,
and upload images.
Activities for ROLE_USER: authentication, authorization, adding items to bucket,
adding items from bucket to order, changing personal information (except for email),
comment news.
Activities for ROLE_ADMIN: authentication, authorization, change status of order,
change user role, adding items, copy existing ones, add news, delete comments.
Activities for ROLE_SUPERADMIN: authentication, authorization, change status of order,
change user role, adding items, copy existing ones, add news, delete comments,
block users, delete users.