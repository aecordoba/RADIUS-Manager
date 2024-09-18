------------------------------------------
-- my.cnf file:
-- [mysqld]
-- event_scheduler=ON
-----------------------------------------

USE RadiusManager;

SET GLOBAL event_scheduler=ON;

DELIMITER //
CREATE EVENT clear_db
ON SCHEDULE EVERY 1 MONTH
STARTS '2024-10-01 04:00:00'
DO
BEGIN
DELETE FROM radpostauth WHERE authdate < (DATE_SUB(CURDATE(), INTERVAL 1 YEAR));
DELETE FROM radacct WHERE acctstarttime < (DATE_SUB(CURDATE(), INTERVAL 1 YEAR));
END//
DELIMITER ;
