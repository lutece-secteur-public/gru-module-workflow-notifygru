
--
-- Structure for table moduleworkflownotifygru_
--

DROP TABLE IF EXISTS moduleworkflownotifygru_;
CREATE TABLE moduleworkflownotifygru_ (
id_notifygru int(6) NOT NULL,
title varchar(50) NOT NULL default '',
id_task int(11) NOT NULL default '0',
PRIMARY KEY (id_notifygru)
);
