INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'Ford Focus', '45');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'Fiat Punto', '40');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'Mazda 3', '50');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'BMW 320', '50');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'Toyota RAV4', '55');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'BMW 528i', '80');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'Suzuki Swift', '60');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'Land Rover Discovery', '120');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'Mercedes E220', '100');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'Yugo 55', '35');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'Lada Niva', '50');
INSERT INTO `sziv`.`tipvozila` (`id`, `ime`, `cena`) VALUES (NULL, 'Suzuki Vitara', '50');

INSERT INTO `sziv`.`vozilo` (`id`, `tipvozila_id`) VALUES ('BG312TR', '1');
INSERT INTO `sziv`.`vozilo` (`id`, `tipvozila_id`) VALUES ('BG558YH', '1');
INSERT INTO `sziv`.`vozilo` (`id`, `tipvozila_id`) VALUES ('BG665OI', '2');
INSERT INTO `sziv`.`vozilo` (`id`, `tipvozila_id`) VALUES ('BG332PP', '2');
INSERT INTO `sziv`.`vozilo` (`id`, `tipvozila_id`) VALUES ('BG183RT', '3');
INSERT INTO `sziv`.`vozilo` (`id`, `tipvozila_id`) VALUES ('BG129RT', '4');
INSERT INTO `sziv`.`vozilo` (`id`, `tipvozila_id`) VALUES ('BG111RX', '3');
INSERT INTO `sziv`.`vozilo` (`id`, `tipvozila_id`) VALUES ('BG322OO', '5');

insert into korisnik values (NULL,1,'admin@email.com',1,'Admin', 1,'$2a$10$cGtOPvnrDnS8EJWU4I4CNuu19v4GX8cBzv5TbLfTZoT8kwf9nxxjy','Admin','admin');
insert into korisnik values (NULL,2,'user@email.com',1,'Ivan', 0,'$2a$10$j2J9l2Z5SiPkwKHMr/DG5ue4t0OdkKykIuiaOusCOz3senq8kZAp.','Simic','user');


