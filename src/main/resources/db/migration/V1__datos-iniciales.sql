use adgan;

INSERT INTO `adgan`.`owner` (`apellido`, `estado`, `username`, `contacto`, `correo`, `nombre`, `password`) VALUES ('Admin', '1', 'Admin', '314568', 'Admin@mail.com', 'Admin', '$2a$10$a3.0mUzYTrFNV3fwxlE4MOJtvV5LrpwYMViC9ZjhXbKhKt3vZv1py');
INSERT INTO `adgan`.`owner` (`apellido`, `estado`, `username`, `contacto`, `correo`, `nombre`, `password`) VALUES ('User', '1', 'User', '314320', 'User@mail.com', 'User', '$2a$10$a3.0mUzYTrFNV3fwxlE4MOJtvV5LrpwYMViC9ZjhXbKhKt3vZv1py');
INSERT INTO `adgan`.`owner` (`apellido`, `estado`, `username`, `contacto`, `correo`, `nombre`, `password`) VALUES ('Invited', '1', 'Invited', '316458', 'Invited@mail.com', 'Invited', '$2a$10$a3.0mUzYTrFNV3fwxlE4MOJtvV5LrpwYMViC9ZjhXbKhKt3vZv1py');
INSERT INTO `adgan`.`owner` (`apellido`, `estado`, `username`, `contacto`, `correo`, `nombre`, `password`) VALUES ('Invited', '1', 'Invited', '3116487', 'Invited@mail.com', 'Invited', '$2a$10$a3.0mUzYTrFNV3fwxlE4MOJtvV5LrpwYMViC9ZjhXbKhKt3vZv1py');

INSERT INTO `adgan`.`roles` (`name`) VALUES ('ADMIN');
INSERT INTO `adgan`.`roles` (`name`) VALUES ('USER');
INSERT INTO `adgan`.`roles` (`name`) VALUES ('INVITED');

INSERT INTO `adgan`.`owner_roles` (`owner_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `adgan`.`owner_roles` (`owner_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `adgan`.`owner_roles` (`owner_id`, `role_id`) VALUES ('3', '3');
INSERT INTO `adgan`.`owner_roles` (`owner_id`, `role_id`) VALUES ('4', '4');

INSERT INTO `adgan`.`cattle` (`estado`, `fecha_nacimiento`, `id_owner`, `nombre`) VALUES ('0', '2022-04-21', '1', 'Shack');
INSERT INTO `adgan`.`cattle` (`estado`, `fecha_nacimiento`, `id_owner`, `nombre`) VALUES ('0', '2022-06-23', '1', 'Mora');
INSERT INTO `adgan`.`cattle` (`estado`, `fecha_nacimiento`, `id_owner`, `nombre`) VALUES ('1', '2022-08-15', '1', 'Manchas');
INSERT INTO `adgan`.`cattle` (`estado`, `fecha_nacimiento`, `id_owner`, `nombre`) VALUES ('1', '2022-04-10', '1', 'Perla');
INSERT INTO `adgan`.`cattle` (`estado`, `fecha_nacimiento`, `id_owner`, `nombre`) VALUES ('1', '2022-04-13', '2', 'Roja');
INSERT INTO `adgan`.`cattle` (`estado`, `fecha_nacimiento`, `id_owner`, `nombre`) VALUES ('1', '2022-06-15', '2', 'Chamber');
INSERT INTO `adgan`.`cattle` (`estado`, `fecha_nacimiento`, `id_owner`, `nombre`) VALUES ('1', '2022-09-29', '3', 'Cocola');
INSERT INTO `adgan`.`cattle` (`estado`, `fecha_nacimiento`, `id_owner`, `nombre`) VALUES ('1', '2022-12-13', '3', 'Cachona');
INSERT INTO `adgan`.`cattle` (`estado`, `fecha_nacimiento`, `id_owner`, `nombre`) VALUES ('1', '2022-02-13', '4', 'Chapina');
INSERT INTO `adgan`.`cattle` (`estado`, `fecha_nacimiento`, `id_owner`, `nombre`) VALUES ('1', '2022-04-20', '4', 'Motoso');

INSERT INTO `adgan`.`sale` (`fecha_venta`, `estado`, `precio_kilo`, `valor_bascula`, `valor_camion`) VALUES ('2024-01-18', '1', '8000', '120000', '200000');

INSERT INTO `adgan`.`sale_cattle` (`id_sale_cattle`, `peso`, `total`, `total_neto`, `id_cattle`, `id_sale`) VALUES ('1', '450', '3600000', '3440000', '1', '1');
INSERT INTO `adgan`.`sale_cattle` (`id_sale_cattle`, `peso`, `total`, `total_neto`, `id_cattle`, `id_sale`) VALUES ('2', '450', '3600000', '3440000', '2', '1');

----------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------

DROP VIEW IF EXISTS `dead_cattles`;
CREATE VIEW `dead_cattles` AS
	select `c`.`id_cattle` AS `idCattle`,`c`.`estado` AS `estado`,`c`.`nombre` AS `nombre`,`c`.`fecha_nacimiento` AS `fechaNacimiento`,
	`c`.`id_owner` AS `idOwner`,`o`.`nombre` AS `nombreOwner`,`o`.`apellido` AS `apellido`,`o`.`contacto` AS `contacto`,`o`.`correo` AS `correo`
from ((`cattle` `c`
	left join `owner` `o` on((`c`.`id_owner` = `o`.`id_owner`)))
	left join `sale_cattle` `sc` on((`c`.`id_cattle` = `sc`.`id_cattle`)))
where ((`c`.`estado` = 0)
	and (`sc`.`id_cattle` is null));

----------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------

DROP VIEW IF EXISTS `resume_cattles`;
CREATE VIEW `resume_cattles` AS
	select `c`.`id_cattle` AS `idCattle`,`c`.`nombre` AS `nombre`,
	concat(timestampdiff(YEAR,`c`.`fecha_nacimiento`,curdate()),' años ',
	(timestampdiff(MONTH,`c`.`fecha_nacimiento`,curdate()) % 12),' meses') AS `edad`,
	(case `c`.`estado`
		when 1 then 'En Finca'
		when 0 then 'Fuera'
	end) AS `estado`,
	concat(`o`.`nombre`,' ',`o`.`apellido`) AS `owner`,`o`.`contacto` AS `contacto`
from (`owner` `o`
	left join `cattle` `c` on((`c`.`id_owner` = `o`.`id_owner`)))
where (`c`.`estado` = 1);

----------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------

DROP VIEW IF EXISTS `resume_sold`;
CREATE VIEW `resume_sold` AS
select `c`.`id_cattle` AS `idCattle`,`c`.`nombre` AS `nombre`,`s`.`fecha_venta` AS `fechaVenta`,
	concat(timestampdiff(YEAR,`c`.`fecha_nacimiento`,`s`.`fecha_venta`),' años ',
	(timestampdiff(MONTH,`c`.`fecha_nacimiento`,`s`.`fecha_venta`) % 12),' meses') AS `edad`,
	concat(`o`.`nombre`,' ',`o`.`apellido`) AS `owner`,`sc`.`peso` AS `peso`,`sc`.`total_neto` AS `totalNeto`
from (((`owner` `o`
	left join `cattle` `c` on((`c`.`id_owner` = `o`.`id_owner`)))
	join `sale_cattle` `sc` on((`c`.`id_cattle` = `sc`.`id_cattle`)))
	join `sale` `s` on((`sc`.`id_sale` = `s`.`id_sale`)))
where (`c`.`estado` = 0);

----------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------

DROP VIEW IF EXISTS `sales_cattles`;
CREATE VIEW `sales_cattles` AS
select `s`.`id_sale` AS `idSale`,`s`.`estado` AS `estado`,`s`.`fecha_venta` AS `fechaVenta`,`s`.`precio_kilo` AS `precioKilo`,
	`s`.`valor_bascula` AS `valorBascula`,`s`.`valor_camion` AS `valorCamion`,`sc`.`peso` AS `peso`,`sc`.`total` AS `total`,
	`sc`.`total_neto` AS `totalNeto`,`c`.`nombre` AS `nombre` 
from ((`sale` `s`
	left join `sale_cattle` `sc` on((`s`.`id_sale` = `sc`.`id_sale`)))
	left join `cattle` `c` on((`sc`.`id_cattle` = `c`.`id_cattle`)))
order by `s`.`id_sale`;

----------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------

DROP VIEW IF EXISTS `view_all_cattle`;
CREATE VIEW `view_all_cattle` AS
select `c`.`id_cattle` AS `idCattle`,`c`.`estado` AS `estado`,`c`.`nombre` AS `nombre`,`c`.`fecha_nacimiento` AS `fechaNacimiento`,
	`c`.`id_owner` AS `idOwner`,`o`.`nombre` AS `nombreOwner`,`o`.`apellido` AS `apellido`,`o`.`contacto` AS `contacto`,`o`.`correo` AS `correo`
from (`cattle` `c`
	left join `owner` `o` on((`c`.`id_owner` = `o`.`id_owner`)));

----------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------

DROP VIEW IF EXISTS `view_cattles`;
CREATE VIEW `view_cattles` AS
select `c`.`id_cattle` AS `idCattle`,`c`.`estado` AS `estado`,`c`.`nombre` AS `nombre`,`c`.`fecha_nacimiento` AS `fechaNacimiento`,
	`c`.`id_owner` AS `idOwner`,`o`.`nombre` AS `nombreOwner`,`o`.`apellido` AS `apellido`,`o`.`contacto` AS `contacto`,`o`.`correo` AS `correo`
from (`cattle` `c`
	left join `owner` `o` on((`c`.`id_owner` = `o`.`id_owner`)))
where (`c`.`estado` = 1);