/**
 * CREATE Script for init of DB
 */
 
/**
* http://localhost:8080//h2-console/
* JDBC URL: jdbc:h2:mem:testdb
* User Name: sa
* Pasword: <leave this empty>
*/


-- Create 2 user records
insert into user (userid, date_created, username, password, access_token, expire_date, expired) 
values (1, now(), 'yuga', 'yuga123', 'abcd', now(), false);

-- Create 3 gaspump records
--insert into gaspump (pumpid, date_created, gastype, amount_in_liters) values (1, now(), 'REGULAR', 200);
--insert into gaspump (pumpid, date_created, gastype, amount_in_liters) values (2, now(), 'SUPER', 100);
--insert into gaspump (pumpid, date_created, gastype, amount_in_liters) values (3, now(), 'DIESEL', 300);

