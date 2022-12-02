-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-12-2022 a las 01:34:58
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdboleteria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `butaca`
--

CREATE TABLE `butaca` (
  `idButaca` int(11) NOT NULL,
  `idSala` int(11) NOT NULL,
  `fila` int(11) NOT NULL,
  `columna` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `butaca`
--

INSERT INTO `butaca` (`idButaca`, `idSala`, `fila`, `columna`, `estado`) VALUES
(1, 1, 0, 0, 1),
(2, 1, 0, 1, 1),
(3, 1, 0, 2, 1),
(4, 1, 1, 0, 1),
(5, 1, 1, 1, 1),
(6, 1, 1, 2, 1),
(7, 2, 0, 0, 1),
(8, 2, 0, 1, 1),
(9, 2, 0, 2, 1),
(10, 2, 1, 0, 1),
(11, 2, 1, 1, 1),
(12, 2, 1, 2, 1),
(13, 4, 0, 0, 1),
(14, 4, 0, 1, 1),
(15, 4, 0, 2, 1),
(16, 4, 1, 0, 1),
(17, 4, 1, 1, 1),
(18, 4, 1, 2, 1),
(19, 5, 0, 0, 1),
(20, 5, 0, 1, 1),
(21, 5, 0, 2, 1),
(22, 5, 1, 0, 1),
(23, 5, 1, 1, 1),
(24, 5, 1, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `dni`, `nombre`, `apellido`, `estado`) VALUES
(1, 38391142, 'Melina', 'Aguilo', 1),
(2, 20922455, 'Alejandro', 'Attié', 1),
(3, 36645232, 'Victoria', 'Castillo', 1),
(4, 32724451, 'Santiago Ezequiel', 'Fernandez', 1),
(5, 16876878, 'Pascual ', 'Furchi', 1),
(6, 35718438, 'Denise ', 'Montero', 1),
(7, 33419458, 'Gustavo', 'Troiano', 1),
(8, 37699303, 'Leandro', 'Sanabria', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `idPelicula` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `genero` varchar(25) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pelicula`
--

INSERT INTO `pelicula` (`idPelicula`, `nombre`, `genero`, `estado`) VALUES
(1, 'Black Panther: Wakanda Forever', 'Acción', 1),
(2, 'Black Adam', 'Acción', 1),
(3, 'La reencarnación', 'Terror', 1),
(4, 'Lyle, Lyle, Crocodile', 'Comedia', 1),
(5, 'Dog. Un Viaje Salvaje', 'Comedia', 1),
(6, 'Corazones malheridos', 'Romance', 1),
(7, 'Osa polar', 'Documental', 1),
(8, 'Sonic 2', 'Ciencia ficción', 1),
(9, 'La ciudad perdida', 'Aventura', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyeccion`
--

CREATE TABLE `proyeccion` (
  `idProyeccion` int(11) NOT NULL,
  `idSala` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL,
  `fechaProyeccion` date NOT NULL,
  `horarioInicio` time NOT NULL,
  `horarioFin` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proyeccion`
--

INSERT INTO `proyeccion` (`idProyeccion`, `idSala`, `idPelicula`, `fechaProyeccion`, `horarioInicio`, `horarioFin`) VALUES
(1, 1, 4, '2022-12-02', '16:00:00', '19:30:00'),
(2, 1, 5, '2022-12-02', '20:00:00', '23:30:00'),
(3, 2, 2, '2022-12-04', '20:00:00', '23:30:00'),
(4, 3, 1, '2022-12-06', '16:00:00', '19:30:00'),
(5, 4, 3, '2022-12-12', '20:00:00', '23:30:00'),
(6, 5, 8, '2022-11-28', '16:00:00', '19:30:00'),
(7, 5, 9, '2022-12-13', '20:00:00', '23:30:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `idSala` int(11) NOT NULL,
  `ubicacion` varchar(25) NOT NULL,
  `localidad` varchar(25) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`idSala`, `ubicacion`, `localidad`, `estado`) VALUES
(1, 'Sala 1', 'San Luis', 1),
(2, 'Sala 2', 'San luis', 1),
(3, 'Sala 3', 'San luis', 1),
(4, 'Sala 1', 'Villa Mercedes', 1),
(5, 'Sala 2', 'Villa Mercedes', 1),
(6, 'Sala 3', 'Villa Mercedes', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket`
--

CREATE TABLE `ticket` (
  `idTicket` int(11) NOT NULL,
  `idProyeccion` int(11) NOT NULL,
  `idButaca` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `fechaCompra` date NOT NULL,
  `horaCompra` time NOT NULL,
  `formaPago` varchar(25) NOT NULL,
  `monto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ticket`
--

INSERT INTO `ticket` (`idTicket`, `idProyeccion`, `idButaca`, `idCliente`, `fechaCompra`, `horaCompra`, `formaPago`, `monto`) VALUES
(1, 1, 1, 1, '2022-12-01', '15:00:00', 'Mercado pago', 800),
(2, 2, 2, 4, '2022-12-02', '15:30:00', 'Tarjeta Debito', 800),
(3, 1, 2, 1, '2022-12-01', '21:32:07', 'Mercado Pago', 800),
(4, 1, 5, 6, '2022-12-01', '21:32:43', 'Tarjeta Credito', 800),
(5, 5, 18, 3, '2022-12-01', '21:34:03', 'Tarjeta Credito', 800);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `butaca`
--
ALTER TABLE `butaca`
  ADD PRIMARY KEY (`idButaca`),
  ADD KEY `idSala` (`idSala`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`idPelicula`);

--
-- Indices de la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  ADD PRIMARY KEY (`idProyeccion`),
  ADD KEY `idSala` (`idSala`),
  ADD KEY `idPelicula` (`idPelicula`);

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`idSala`);

--
-- Indices de la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`idTicket`),
  ADD KEY `idProyeccion` (`idProyeccion`),
  ADD KEY `idButaca` (`idButaca`),
  ADD KEY `idCliente` (`idCliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `butaca`
--
ALTER TABLE `butaca`
  MODIFY `idButaca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `idPelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  MODIFY `idProyeccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `sala`
--
ALTER TABLE `sala`
  MODIFY `idSala` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `ticket`
--
ALTER TABLE `ticket`
  MODIFY `idTicket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `butaca`
--
ALTER TABLE `butaca`
  ADD CONSTRAINT `butaca_ibfk_1` FOREIGN KEY (`idSala`) REFERENCES `sala` (`idSala`);

--
-- Filtros para la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  ADD CONSTRAINT `proyeccion_ibfk_1` FOREIGN KEY (`idSala`) REFERENCES `sala` (`idSala`),
  ADD CONSTRAINT `proyeccion_ibfk_2` FOREIGN KEY (`idPelicula`) REFERENCES `pelicula` (`idPelicula`);

--
-- Filtros para la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`idProyeccion`) REFERENCES `proyeccion` (`idProyeccion`),
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`idButaca`) REFERENCES `butaca` (`idButaca`),
  ADD CONSTRAINT `ticket_ibfk_3` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
