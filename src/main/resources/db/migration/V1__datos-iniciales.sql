use adgan;

INSERT INTO `adgan`.`owner` (`apellido`, `estado`, `username`, `contacto`, `correo`, `nombre`, `password`) VALUES ('Admin', '1', 'Admin', '314568', 'Admin@mail.com', 'Admin', '$2a$10$a3.0mUzYTrFNV3fwxlE4MOJtvV5LrpwYMViC9ZjhXbKhKt3vZv1py');
INSERT INTO `adgan`.`owner` (`apellido`, `estado`, `username`, `contacto`, `correo`, `nombre`, `password`) VALUES ('User', '1', 'User', '314320', 'User@mail.com', 'User', '$2a$10$a3.0mUzYTrFNV3fwxlE4MOJtvV5LrpwYMViC9ZjhXbKhKt3vZv1py');
INSERT INTO `adgan`.`owner` (`apellido`, `estado`, `username`, `contacto`, `correo`, `nombre`, `password`) VALUES ('Invited', '1', 'Invited', '316458', 'Invited@mail.com', 'Invited', '$2a$10$a3.0mUzYTrFNV3fwxlE4MOJtvV5LrpwYMViC9ZjhXbKhKt3vZv1py');
INSERT INTO `adgan`.`owner` (`apellido`, `estado`, `username`, `contacto`, `correo`, `nombre`, `password`) VALUES ('Invited', '1', 'Invited', '3116487', 'Invited@mail.com', 'Invited', '$2a$10$a3.0mUzYTrFNV3fwxlE4MOJtvV5LrpwYMViC9ZjhXbKhKt3vZv1py');

INSERT INTO `adgan`.`roles` (`name`) VALUES ('ADMIN');
INSERT INTO `adgan`.`roles` (`name`) VALUES ('USER');
INSERT INTO `adgan`.`roles` (`name`) VALUES ('INVITED');
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

