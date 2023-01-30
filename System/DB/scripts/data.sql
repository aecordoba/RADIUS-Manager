-- drop User 'radman'@'%';
-- drop User 'rmapp'@'%';
-- drop User 'radius'@'%';
-- flush privileges;

-- CREATE User 'radman'@'%' IDENTIFIED BY 'radman123';
GRANT ALL PRIVILEGES ON `RadiusManager`.* TO 'radman'@'%';
-- CREATE User 'rmapp'@'%' IDENTIFIED BY 'rmapp123';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON `RadiusManager`.* TO 'rmapp'@'%';
GRANT SELECT ON mysql.proc TO 'rmapp'@'%';
-- CREATE User 'radius'@'%' IDENTIFIED BY 'radius123';
GRANT ALL PRIVILEGES ON `RadiusManager`.* TO 'radius'@'%';

flush privileges;

use RadiusManager;


INSERT INTO `RadiusManager`.`Authorities` VALUES (1,'ADMIN'),
                                (2,'USER'),
                                (3,'OBSERVER');

INSERT INTO Users (id,name,password,enabled,first_name,middle_name,last_name) VALUES (1,'admin','$2a$10$kEGwwhWZpV2ApEkEsTXUROFKV4vhkKxaMlZCl7XQ5IfG5Z1GFGVcC',1,'Super','','Administrator');
INSERT INTO Users (id,name,password,enabled,first_name,middle_name,last_name) VALUES (2,'user','$2a$10$1lCpQKdv7FQ8ieFJwAVu0uv.x6nqhnEl3mfFJiFyLG36wBnPFtp4W',1,'User','','User');
INSERT INTO Users (id,name,password,enabled,first_name,middle_name,last_name) VALUES (3,'obs','$2a$10$VR1N3m6r5POy3NZtr056y.y2axn1gmPWzfguHpO3NHKYkqnBY3Irq',1,'Observer','','Observer');

INSERT INTO Users_Authorities(user, authority) VALUES(1, 1);
INSERT INTO Users_Authorities(user, authority) VALUES(2, 2);
INSERT INTO Users_Authorities(user, authority) VALUES(3, 3);

-- RADIUS TEST
INSERT INTO  nas VALUES (NULL ,  '200.85.121.130',  'NAS0',  'other', NULL ,  'NAS0123', NULL , NULL ,  'RADIUS Test Client');

insert into radgroupcheck (groupname,attribute,op,value) values ("priv","Framed-Protocol","==","PPP");
insert into radgroupcheck (groupname,attribute,op,value) values ("inactive","Framed-Protocol","==","PPP");

insert into radgroupreply (groupname,attribute,op,value) values ("priv","Framed-Pool","=","pool3");
insert into radgroupreply (groupname,attribute,op,value) values ("inactive","Framed-Pool","=","Expierd-Pool");

insert into radusergroup (username,groupname,priority) values ("priv_profile", "priv", 10);
insert into radusergroup (username,groupname,priority) values ("inactive_profile", "inactive", 10);

INSERT INTO radcheck (username,attribute,op,value) VALUES ('tbsa','Cleartext-Password',':=','tbsa');
INSERT INTO radcheck (username,attribute,op,value) VALUES ('tbsa','User-Profile',':=','priv_profile');

INSERT INTO radreply VALUES (NULL , 'tbsa', 'Framed-IP-Address', ':=', '192.168.47.47');
