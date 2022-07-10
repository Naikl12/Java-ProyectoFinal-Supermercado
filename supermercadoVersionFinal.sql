-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-07-2022 a las 22:58:57
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `supermercado`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `id` int(4) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `id_rubro` int(4) NOT NULL,
  `stock` int(4) NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`id`, `nombre`, `id_rubro`, `stock`, `precio`) VALUES
(1, 'coca cola', 1, 4, 200),
(2, 'fernet', 1, 16, 315),
(3, 'lavnadina', 2, 12, 55),
(4, 'Queso', 6, 40, 700),
(5, 'Mortadela', 6, 20, 600);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carritocompras`
--

CREATE TABLE `carritocompras` (
  `id` int(4) NOT NULL,
  `id_cliente` int(4) NOT NULL,
  `fecha` date NOT NULL,
  `id_articulo` int(4) NOT NULL,
  `cantidad` int(4) NOT NULL,
  `subtotal` double(8,2) NOT NULL,
  `id_fp` int(4) NOT NULL,
  `estado` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `carritocompras`
--

INSERT INTO `carritocompras` (`id`, `id_cliente`, `fecha`, `id_articulo`, `cantidad`, `subtotal`, `id_fp`, `estado`) VALUES
(1, 3, '2022-07-10', 1, 3, 600.00, 4, 'MOROSO'),
(2, 3, '2022-07-10', 5, 10, 6000.00, 4, 'MOROSO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formadepago`
--

CREATE TABLE `formadepago` (
  `id` int(2) NOT NULL,
  `descripcion` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `formadepago`
--

INSERT INTO `formadepago` (`id`, `descripcion`) VALUES
(1, 'Debito'),
(3, 'CreditoVisaMacro'),
(4, 'CreditoVisaNaranja'),
(5, 'CreditoVisaHipetecar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rubro`
--

CREATE TABLE `rubro` (
  `id` int(4) NOT NULL,
  `descripcion` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rubro`
--

INSERT INTO `rubro` (`id`, `descripcion`) VALUES
(1, 'bebidas'),
(2, 'Limpieza'),
(3, 'Indumentaria'),
(4, 'Golosinas'),
(5, 'Farmacia'),
(6, 'Comestibles');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(8) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `dni` int(8) NOT NULL,
  `domicilio` varchar(25) NOT NULL,
  `correo` varchar(25) NOT NULL,
  `pass` int(4) NOT NULL,
  `es_frecuente` tinyint(1) NOT NULL,
  `es_admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `dni`, `domicilio`, `correo`, `pass`, `es_frecuente`, `es_admin`) VALUES
(3, 'pedro', 'lopez', 2323, 'sucasa122', 'pedro@pedro', 123, 1, 1),
(4, 'maria', 'delBarrio', 232323, 'sucasa', 'maria@maria', 12323, 1, 1),
(6, 'juan', 'Serrano', 32323, 'sucasa222', 'juan@juan', 1234, 1, 2),
(7, 'enzo', 'valverde', 23243, 'micasa', 'enzo@enzo.com', 1212, 1, 2),
(8, 'cintia', 'lopez', 2712343, 'siempreviva123', 'cintia@gmail.com', 3434, 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `carritocompras`
--
ALTER TABLE `carritocompras`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `formadepago`
--
ALTER TABLE `formadepago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `rubro`
--
ALTER TABLE `rubro`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulos`
--
ALTER TABLE `articulos`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `carritocompras`
--
ALTER TABLE `carritocompras`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `formadepago`
--
ALTER TABLE `formadepago`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `rubro`
--
ALTER TABLE `rubro`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
