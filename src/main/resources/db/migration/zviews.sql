USE adgan;

DROP VIEW IF EXISTS `dead_cattles`;
CREATE VIEW `dead_cattles` AS
	select `c`.`id_cattle` AS `idCattle`,`c`.`estado` AS `estado`,`c`.`nombre` AS `nombre`,`c`.`fecha_nacimiento` AS `fechaNacimiento`,
	`c`.`id_owner` AS `idOwner`,`o`.`nombre` AS `nombreOwner`,`o`.`apellido` AS `apellido`,`o`.`contacto` AS `contacto`,`o`.`correo` AS `correo`
from ((`cattle` `c`
	left join `owner` `o` on((`c`.`id_owner` = `o`.`id_owner`)))
	left join `sale_cattle` `sc` on((`c`.`id_cattle` = `sc`.`id_cattle`)))
where ((`c`.`estado` = 0)
	and (`sc`.`id_cattle` is null));


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


DROP VIEW IF EXISTS `sales_cattles`;
CREATE VIEW `sales_cattles` AS
select `s`.`id_sale` AS `idSale`,`s`.`estado` AS `estado`,`s`.`fecha_venta` AS `fechaVenta`,`s`.`precio_kilo` AS `precioKilo`,
	`s`.`valor_bascula` AS `valorBascula`,`s`.`valor_camion` AS `valorCamion`,`sc`.`peso` AS `peso`,`sc`.`total` AS `total`,
	`sc`.`total_neto` AS `totalNeto`,`c`.`nombre` AS `nombre` 
from ((`sale` `s`
	left join `sale_cattle` `sc` on((`s`.`id_sale` = `sc`.`id_sale`)))
	left join `cattle` `c` on((`sc`.`id_cattle` = `c`.`id_cattle`)))
order by `s`.`id_sale`;


DROP VIEW IF EXISTS `view_all_cattle`;
CREATE VIEW `view_all_cattle` AS
select `c`.`id_cattle` AS `idCattle`,`c`.`estado` AS `estado`,`c`.`nombre` AS `nombre`,`c`.`fecha_nacimiento` AS `fechaNacimiento`,
	`c`.`id_owner` AS `idOwner`,`o`.`nombre` AS `nombreOwner`,`o`.`apellido` AS `apellido`,`o`.`contacto` AS `contacto`,`o`.`correo` AS `correo`
from (`cattle` `c`
	left join `owner` `o` on((`c`.`id_owner` = `o`.`id_owner`)));


DROP VIEW IF EXISTS `view_cattles`;
CREATE VIEW `view_cattles` AS
select `c`.`id_cattle` AS `idCattle`,`c`.`estado` AS `estado`,`c`.`nombre` AS `nombre`,`c`.`fecha_nacimiento` AS `fechaNacimiento`,
	`c`.`id_owner` AS `idOwner`,`o`.`nombre` AS `nombreOwner`,`o`.`apellido` AS `apellido`,`o`.`contacto` AS `contacto`,`o`.`correo` AS `correo`
from (`cattle` `c`
	left join `owner` `o` on((`c`.`id_owner` = `o`.`id_owner`)))
where (`c`.`estado` = 1);