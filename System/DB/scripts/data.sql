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

-- RADIUS TEST
INSERT INTO  nas VALUES (NULL ,  '170.231.179.246',  'nas0',  'other', NULL ,  'nas0123', NULL , NULL ,  'RADIUS Test Client');
INSERT INTO radcheck VALUES (NULL , 'test_user', 'Cleartext-Password', ':=', 'password_test_user');
INSERT INTO radreply VALUES (NULL , 'test_user', 'Framed-IP-Address', ':=', '170.231.179.247');

INSERT INTO `RadiusManager`.`Authorities` VALUES (1,'ADMIN'),
                                (2,'USER'),
                                (3,'OBSERVER');

INSERT INTO Users (id,name,password,enabled,first_name,middle_name,last_name) VALUES (1,'admin','$2a$10$kEGwwhWZpV2ApEkEsTXUROFKV4vhkKxaMlZCl7XQ5IfG5Z1GFGVcC',1,'Super','','Administrator');
INSERT INTO Users (id,name,password,enabled,first_name,middle_name,last_name) VALUES (2,'user','$2a$10$1lCpQKdv7FQ8ieFJwAVu0uv.x6nqhnEl3mfFJiFyLG36wBnPFtp4W',1,'User','','User');
INSERT INTO Users (id,name,password,enabled,first_name,middle_name,last_name) VALUES (3,'obs','$2a$10$VR1N3m6r5POy3NZtr056y.y2axn1gmPWzfguHpO3NHKYkqnBY3Irq',1,'Observer','','Observer');

INSERT INTO Users_Authorities(user, authority) VALUES(1, 1);
INSERT INTO Users_Authorities(user, authority) VALUES(2, 2);
INSERT INTO Users_Authorities(user, authority) VALUES(3, 3);
