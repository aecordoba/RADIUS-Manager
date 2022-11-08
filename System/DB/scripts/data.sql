-- drop User 'radman'@'%';
-- drop User 'rmapp'@'%';
-- flush privileges;

CREATE User 'radman'@'%' IDENTIFIED BY 'radman123';
GRANT ALL PRIVILEGES ON `RadiusManager`.* TO 'radman'@'%';
CREATE User 'rmapp'@'%' IDENTIFIED BY 'rmapp123';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON `RadiusManager`.* TO 'rmapp'@'%';
GRANT SELECT ON mysql.proc TO 'rmapp'@'%';

flush privileges;

use RadiusManager;
 
