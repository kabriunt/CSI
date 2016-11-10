-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 27-10-2016 a las 01:00:37
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `elrond`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `id` int(10) NOT NULL,
  `nombre` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `nombre`) VALUES
(1, 'Administrador'),
(2, 'Miembro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(10) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `email`, `password`) VALUES
(1, 'Eduardo Mesa Orcero', 'emesaorcero@gmail.com', 'eduardo'),
(2, 'Daniel Cabrera López', 'cabriunt@gmail.com', 'daniel'),
(3, 'Pablo de la Torre', 'pablo.delatorre@uca.es', 'dios'),
(4, 'Carlos Tocino', 'carlostoci@gmail.com', 'chicovicioso'),
(5, 'Pepe', 'pepe@gmail.com', 'pepito');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
