-- MySQL dump 10.13  Distrib 5.7.13, for Win64 (x86_64)
--
-- Host: localhost    Database: presto
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria_productos`
--

DROP TABLE IF EXISTS `categoria_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_productos` (
  `ID_CATEGORIA` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(255) NOT NULL,
  `IMAGEN` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_CATEGORIA`),
  UNIQUE KEY `ID_CATEGORIA` (`ID_CATEGORIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_productos`
--

LOCK TABLES `categoria_productos` WRITE;
/*!40000 ALTER TABLE `categoria_productos` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cortes_caja`
--

DROP TABLE IF EXISTS `cortes_caja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cortes_caja` (
  `ID_CORTE` int(11) NOT NULL AUTO_INCREMENT,
  `FECHA` date NOT NULL,
  `APERTURA` double NOT NULL,
  `TURNO` int(11) NOT NULL,
  PRIMARY KEY (`ID_CORTE`),
  UNIQUE KEY `ID_CORTE` (`ID_CORTE`),
  UNIQUE KEY `FECHA` (`FECHA`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cortes_caja`
--

LOCK TABLES `cortes_caja` WRITE;
/*!40000 ALTER TABLE `cortes_caja` DISABLE KEYS */;
/*!40000 ALTER TABLE `cortes_caja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devoluciones`
--

DROP TABLE IF EXISTS `devoluciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devoluciones` (
  `ID_DEVOLUCION` int(11) NOT NULL AUTO_INCREMENT,
  `FECHA` date NOT NULL,
  `ID_USUARIO` int(11) NOT NULL,
  `HORA` time NOT NULL,
  `TOTAL` float NOT NULL,
  `TURNO` int(11) NOT NULL,
  PRIMARY KEY (`ID_DEVOLUCION`),
  UNIQUE KEY `ID_DEVOLUCION` (`ID_DEVOLUCION`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devoluciones`
--

LOCK TABLES `devoluciones` WRITE;
/*!40000 ALTER TABLE `devoluciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `devoluciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egresos`
--

DROP TABLE IF EXISTS `egresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egresos` (
  `ID_EGRESO` int(11) NOT NULL AUTO_INCREMENT,
  `FECHA` date NOT NULL,
  `MONTO` float NOT NULL,
  `CONCEPTO` varchar(511) NOT NULL,
  `ID_USUARIO` int(11) NOT NULL,
  `TURNO` int(11) NOT NULL,
  PRIMARY KEY (`ID_EGRESO`),
  UNIQUE KEY `ID_EGRESO` (`ID_EGRESO`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egresos`
--

LOCK TABLES `egresos` WRITE;
/*!40000 ALTER TABLE `egresos` DISABLE KEYS */;
/*!40000 ALTER TABLE `egresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `ID_PRODUCTO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CATEGORIA` int(11) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `PRECIO` double NOT NULL,
  `IMAGEN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUCTO`),
  UNIQUE KEY `ID_PRODUCTO` (`ID_PRODUCTO`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos_devoluciones`
--

DROP TABLE IF EXISTS `productos_devoluciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos_devoluciones` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PRODUCTO` int(11) NOT NULL,
  `ID_DEVOLUCION` int(11) NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_devoluciones`
--

LOCK TABLES `productos_devoluciones` WRITE;
/*!40000 ALTER TABLE `productos_devoluciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos_devoluciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos_ventas`
--

DROP TABLE IF EXISTS `productos_ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos_ventas` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PRODUCTO` int(11) NOT NULL,
  `ID_VENTA` int(11) NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_ventas`
--

LOCK TABLES `productos_ventas` WRITE;
/*!40000 ALTER TABLE `productos_ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos_ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE_USUARIO` varchar(255) NOT NULL,
  `CONTRASEÑA` varchar(511) NOT NULL,
  `NIVEL_DE_ACCESO` int(11) NOT NULL,
  PRIMARY KEY (`ID_USUARIO`),
  UNIQUE KEY `ID_USUARIO` (`ID_USUARIO`),
  UNIQUE KEY `NOMBRE_USUARIO` (`NOMBRE_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `ID_VENTA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_USUARIO` int(11) NOT NULL,
  `FECHA` date NOT NULL,
  `HORA` time NOT NULL,
  `TOTAL` float NOT NULL,
  `TURNO` int(11) NOT NULL,
  PRIMARY KEY (`ID_VENTA`),
  UNIQUE KEY `ID_VENTA` (`ID_VENTA`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-03 14:23:28
