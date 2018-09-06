/**
 * CREATE Script for init of DB
 */
 
/**
* http://localhost:8080//h2-console/
* JDBC URL: jdbc:h2:mem:testdb
* User Name: sa
* Pasword: <leave this empty>
*/


-- Create user records
insert into user (userid, date_created, username, password, access_token, expire_date, expired) 
values (1, now(), 'yuga', 'yuga123', 'abcd', now(), false);

-- Create gaspump records
insert into gaspump (pumpid, date_created, gas_type, amount_in_liters) values (1, now(), 'REGULAR', 200);
insert into gaspump (pumpid, date_created, gas_type, amount_in_liters) values (2, now(), 'SUPER', 100);
--insert into gaspump (pumpid, date_created, gas_type, amount_in_liters) values (3, now(), 'DIESEL', 300);

-- Create price records
insert into price_list(priceid,date_created,selling_price,pumpid) values(1,now(),20.0,1);
insert into price_list(priceid,date_created,selling_price,pumpid) values(2,now(),10.0,2);

-- Create Sales records
insert into sales_list(salesid,date_created,liters,no_gas,price_exceed,pump_id_fk) values(1,now(),20.0,false,false,1);
insert into sales_list(salesid,date_created,liters,no_gas,price_exceed,pump_id_fk) values(2,now(),5.0,false,false,1);
