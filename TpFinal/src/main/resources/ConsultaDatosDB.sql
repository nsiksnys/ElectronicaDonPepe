-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 26-11-2013 a las 15:36:12
-- Versión del servidor: 5.5.32
-- Versión de PHP: 5.3.10-1ubuntu3.8

--SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
--SET time_zone = "+00:00";

--
-- Base de datos: `test`
--

-----------------------------------------------------------


--
-- Volcado de datos para la tabla `Vendedor`
--
INSERT INTO `Vendedor` (`id`, `activo`, `apellido`, `nombre`) VALUES
(1, b'1', 'Diaz', 'Andrea'),
(2, b'1', 'Noble', 'Donna'),
(3, b'1', 'Dent', 'Arthur'),
(4, b'1', 'Perez', 'Juan');

--
-- Volcado de datos para la tabla `RolUsuario`
--
INSERT INTO `RolUsuario` (`id`, `nombre`) VALUES
(1, 'RRHH'),
(2, 'Vendedor'),
(3, 'Administrador');

--
-- Volcado de datos para la tabla `Usuario`
--
INSERT INTO `Usuario` (`id`, `activo`, `password`, `username`, `rol_id`, `vendedor_id`) VALUES
(1, b'1', 'rrhh', 'RRHH', 1, NULL),
(2, b'1', 'vendedor', 'ArthurDent', 2, 3),
(3, b'1', 'vendedor', 'DonnaNoble', 2, 2),
(4, b'1', 'admin', 'Admin', 3, NULL),
(5, b'1', 'vendedor', 'AndreaDiaz', 2, 1),
(6, b'0', 'vendedor', 'JuanPerez', 2, 4);

--
-- Volcado de datos para la tabla `Producto`
--
INSERT INTO `Producto` (`id`, `nombre`, `precioUnitario`) VALUES
(1, 'Doctor Who: Complete Series 4 (2008)', 13),
(2, 'Doctor Who: Complete Specials (2009)', 11.25),
(3, 'Doctor Who: Complete Series 5 (2010)', 12.5),
(4, 'Doctor Who: Complete Series 6 (2011)', 13.75),
(5, 'Doctor Who: Complete Series 7 (2012)', 33.94),
(6, 'Doctor Who: The Ace Adventures (1987)', 10.99),
(7, 'Wallander (Branagh): Series 3', 8),
(8, 'Wallander (Branagh): Series 1 & 2 Box Set', 11.33),
(9, 'Wallander (Lassgard): Original Films 1-6', 10),
(10, 'Arne Dahl: Complete Series 1', 14.5),
(11, 'Rachmaninov: The Ampico Piano Recordings', 9.99),
(12, 'Turner in his Time', 18.2),
(13, 'Inspector Morse: The Complete Series 1-12', 39.99),
(14, 'Karajan: The Complete EMI Recordings Vol. 1', 109.01),
(15, 'Karajan: The Complete EMI Recordings Vol. 2', 119.48);

--
-- Volcado de datos para la tabla `Venta`
--
INSERT INTO `Venta` (`id`, `fecha`, `importe`, `vendedor_id`) VALUES
(1, '2014-06-26 05:58:30', 137.68, 1),
(2, '2014-07-17 19:43:27', 218.02, 1),
(3, '2013-03-08 23:55:49', 167.51, 3),
(4, '2013-12-09 08:17:22', 109.01, 1),
(5, '2014-06-09 03:20:45', 22.24, 1),
(6, '2013-03-30 12:48:49', 73.97, 3),
(7, '2013-03-09 14:14:37', 24.74, 3),
(8, '2012-08-01 20:40:16', 166.79, 2),
(9, '2014-02-05 09:23:11', 51.9, 3),
(10, '2013-10-31 23:09:54', 32.5, 2),
(11, '2013-11-24 15:45:14', 13, 3),
(12, '2013-11-24 16:18:25', 23, 3),
(13, '2013-11-24 16:50:44', 26.2, 1),
(14, '2014-06-29 18:36:48', 22.99, 1),
(15, '2014-06-29 18:39:39', 109.01, 1);

--
-- Volcado de datos para la tabla `Venta_Producto`
--
INSERT INTO `Venta_Producto` (`Venta_id`, `productos_id`) VALUES
(10, 7),
(5, 2),
(8, 4),
(7, 4),
(6, 11),
(3, 14),
(3, 8),
(9, 2),
(9, 8),
(3, 10),
(5, 6),
(9, 11),
(2, 14),
(3, 9),
(9, 7),
(1, 12),
(9, 8),
(10, 9),
(8, 9),
(8, 5),
(3, 2),
(3, 8),
(6, 1),
(6, 6),
(6, 13),
(1, 15),
(2, 14),
(7, 6),
(8, 14),
(10, 10),
(4, 14),
(11, 1),
(12, 1),
(12, 9),
(13, 7),
(13, 12),
(14, 1),
(14, 11),
(15, 14);

--
-- Volcado de datos para la tabla `Campania`
--

INSERT INTO `Campania` (`id`, `activo`, `fechaCreacion`, `producto_id`) VALUES
(1, b'1', '2014-05-18 20:54:31', 7),
(2, b'1', '2014-05-18 20:54:38', 8),
(3, b'1', '2014-05-18 20:54:42', 9);

--
-- Volcado de datos para la tabla `Comision_Producto_Monto`
--
INSERT INTO `Comision_Producto_Monto` (`id`, `monto`, `producto_id`) VALUES
(1, 2.1, 5),
(2, 1, 1),
(3, 3.5, 13),
(4, 6, 9),
(5, 1.99, 15),
(6, 4.3, 7);

--
-- Volcado de datos para la tabla `Comision_Venta_Monto`
--
INSERT INTO `Comision_Venta_Monto` (`id`, `max`, `min`, `monto`) VALUES
(1, 5, 1, 200),
(2, 10, 6, 400),
(3, 11, 15, 700),
(4, 0, 15, 1000); -- que el maximo sea cero significa que no hay tope (ie, max=infinito)

--
-- Volcado de datos para la tabla `Premio_Monto`
--
INSERT INTO `Premio_Monto` (`id`, `campania`, `monto`) VALUES
(1, b'1', 1000),
(2, b'0', 2000);

--
-- Volcado de datos para la tabla `Adicional`
--

INSERT INTO `Adicional` (`id`, `fechaCreacion`, `fechaDesde`, `fechaHasta`, `vendedor_id`) VALUES
(1, '2013-12-20 20:17:34', '2013-01-01 00:00:00', '2013-11-22 00:00:00', 2),
(2, '2013-12-20 20:17:34', '2013-01-01 00:00:00', '2013-11-22 00:00:00', 3);

--
-- Volcado de datos para la tabla `ComisionProducto`
--

INSERT INTO `ComisionProducto` (`id`, `fechaCreacion`, `fechaDesde`, `fechaHasta`, `importe`, `unidades`, `vendedor_id`, `producto_id`) VALUES
(2, '2013-12-20 20:17:34', '2013-01-01 00:00:00', '2013-11-22 00:00:00', 6, 1, 2, 9),
(3, '2013-12-20 20:17:34', '2013-01-01 00:00:00', '2013-11-22 00:00:00', 4.3, 1, 2, 7),
(5, '2013-12-20 20:17:34', '2013-01-01 00:00:00', '2013-11-22 00:00:00', 6, 1, 3, 9),
(6, '2013-12-20 20:17:34', '2013-01-01 00:00:00', '2013-11-22 00:00:00', 1, 1, 3, 1),
(7, '2013-12-20 20:17:34', '2013-01-01 00:00:00', '2013-11-22 00:00:00', 3.5, 1, 3, 13);

--
-- Volcado de datos para la tabla `Adicional_ComisionProducto`
--

INSERT INTO `Adicional_ComisionProducto` (`Adicional_id`, `comisionesProducto_id`) VALUES
(1, 2),
(1, 3),
(2, 5),
(2, 6),
(2, 7);

--
-- Volcado de datos para la tabla `ComisionVenta`
--

INSERT INTO `ComisionVenta` (`id`, `fechaCreacion`, `fechaDesde`, `fechaHasta`, `importe`, `unidades`, `vendedor_id`) VALUES
(1, '2013-12-20 20:17:34', '2013-01-01 00:00:00', '2013-11-22 00:00:00', 200, 1, 2),
(4, '2013-12-20 20:17:34', '2013-01-01 00:00:00', '2013-11-22 00:00:00', 200, 3, 3);

--
-- Volcado de datos para la tabla `ComisionVenta_Venta`
--

INSERT INTO `ComisionVenta_Venta` (`ComisionVenta_id`, `elementos_id`) VALUES
(1, 10),
(4, 3),
(4, 6),
(4, 7);
