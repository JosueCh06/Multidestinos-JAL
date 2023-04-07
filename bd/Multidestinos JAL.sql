CREATE DATABASE  IF NOT EXISTS `proyecto_registroempleados` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `proyecto_registroempleados`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: proyecto_registroempleados
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cab_boleta`
--

DROP TABLE IF EXISTS `cab_boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cab_boleta` (
  `num_boleta` char(5) NOT NULL,
  `fecha` date DEFAULT NULL,
  `idEmpleado` int DEFAULT NULL,
  `idCliente` int DEFAULT NULL,
  `total_bol` double DEFAULT NULL,
  PRIMARY KEY (`num_boleta`),
  KEY `idEmpleado_idx` (`idEmpleado`),
  KEY `idCliente_idx` (`idCliente`),
  CONSTRAINT `idCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `idEmpleado` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cab_boleta`
--

LOCK TABLES `cab_boleta` WRITE;
/*!40000 ALTER TABLE `cab_boleta` DISABLE KEYS */;
INSERT INTO `cab_boleta` VALUES ('B0001','2020-12-06',4,3,35),('B0002','2020-12-06',3,1,61),('B0003','2020-12-06',3,3,35),('B0004','2020-12-06',4,1,36),('B0005','2020-12-06',4,1,51),('B0006','2020-12-06',4,3,25),('B0007','2020-12-06',4,2,25),('B0008','2020-12-06',1,2,36),('B0009','2020-12-06',3,1,25);
/*!40000 ALTER TABLE `cab_boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo` (
  `idCargo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCargo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,'Administrador(a)'),(2,'Mecanico'),(3,'Conductor');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_vehiculo`
--

DROP TABLE IF EXISTS `categoria_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_vehiculo` (
  `idCategoria_Vehiculo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCategoria_Vehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_vehiculo`
--

LOCK TABLES `categoria_vehiculo` WRITE;
/*!40000 ALTER TABLE `categoria_vehiculo` DISABLE KEYS */;
INSERT INTO `categoria_vehiculo` VALUES (1,'Camioneta'),(2,'Taxi');
/*!40000 ALTER TABLE `categoria_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `DNI` char(8) DEFAULT NULL,
  `celular` char(9) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Lina','Pinasco','95163248','956321478','lin@gmail.com'),(2,'Paolo','Pilares','96587412','986532147','papilares@gmail.com'),(3,'Anibal','Junial','98700352','919023987','anijun@gmail.com'),(4,'Junior','Carvajal','95632100','951248700','junjal@gmail.com'),(5,'Oscar','Saavedra','10523648','900325481','oscar_vedra@gmail.com'),(6,'Alexa','Pinos','12365498','986500745','pinoxa@gmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `det_boleta`
--

DROP TABLE IF EXISTS `det_boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `det_boleta` (
  `num_boleta` char(5) NOT NULL,
  `lugarPartida` varchar(45) DEFAULT NULL,
  `lugarLlegada` varchar(45) DEFAULT NULL,
  `importe` double DEFAULT NULL,
  PRIMARY KEY (`num_boleta`),
  CONSTRAINT `num_boleta` FOREIGN KEY (`num_boleta`) REFERENCES `cab_boleta` (`num_boleta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `det_boleta`
--

LOCK TABLES `det_boleta` WRITE;
/*!40000 ALTER TABLE `det_boleta` DISABLE KEYS */;
INSERT INTO `det_boleta` VALUES ('B0001','Parque Universitaria','Plaza Norte',35),('B0002','Parque de Las Aguas','Puente Nuevo',61),('B0003','Av. Abancay 693','Av. Aviacion 689',35),('B0004','Av. Abancay 256','Plaza Norte',36),('B0005','Av. Abancay 258','Cibertec Independencia',51),('B0006','Las Flores 589','Canto Grande 369',25),('B0007','Av. Abancay 369','Las Flores Paradero 4',25),('B0008','Las Flores Paradero 5','Plaza Norte',36),('B0009','Las Flores Paradero 10','Metro S.J.L',25);
/*!40000 ALTER TABLE `det_boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `idEmpleado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `DNI` char(8) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `sueldo` double DEFAULT NULL,
  `Sede_idSede` int NOT NULL,
  `Cargo_idCargo` int NOT NULL,
  `Turno_idTurno` int NOT NULL,
  PRIMARY KEY (`idEmpleado`),
  KEY `fk_Empleado_Sede_idx` (`Sede_idSede`),
  KEY `fk_Empleado_Cargo1_idx` (`Cargo_idCargo`),
  KEY `fk_Empleado_Turno1_idx` (`Turno_idTurno`),
  CONSTRAINT `fk_Empleado_Cargo1` FOREIGN KEY (`Cargo_idCargo`) REFERENCES `cargo` (`idCargo`),
  CONSTRAINT `fk_Empleado_Sede` FOREIGN KEY (`Sede_idSede`) REFERENCES `sede` (`idSede`),
  CONSTRAINT `fk_Empleado_Turno1` FOREIGN KEY (`Turno_idTurno`) REFERENCES `turno` (`idTurno`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,'Benjamin','Perez','12345678','benjamin_p@gmail.com','978456123','2000-02-10',1900,1,3,2),(2,'Vanessa','Arias','23456789','ariasvan@gmail.com','927856984','2001-03-11',2000,2,1,1),(3,'Raul','Chavez','34567891','ch_raul@gmail.com','953687452','2000-04-12',2100,3,3,3),(4,'Wilmer','Linares','45678912','wLinares@gmail.com','936521487','2000-05-09',2200,2,3,1),(5,'Ana','Inga','56789123','IngaAna@gmail.com','963258741','1999-12-08',2000,1,1,2),(6,'Joel','Valdemar','67891234','ValdJoel@gmail.com','978564123','2001-11-09',1900,1,2,3),(7,'Caleb','Mercedez','78912345','calebmercedez@gmail.com','986574123','2000-12-12',2000,1,2,1),(8,'Jhonny','Fuentes','89123456','funtesJhonny@gmail.com','998745632','1999-12-07',2100,3,3,2),(9,'Cristobal','Torres','91234567','Torres123@gmail.com','963258741','1999-12-08',2100,2,2,3),(10,'Susana','Suarez','10203044','Suarez789@gmail.com','963528741','2000-09-12',2000,1,1,2),(11,'Paola','Contreras','20334567','paolaContreras@gmail.com','963258745','1999-12-01',1900,2,1,1),(12,'Luis','Gonzales','33665589','luis_gon@gmail.com','985632145','2000-11-02',2000,3,2,3),(14,'Esteban','Dido','10102569','dido_es@gmail.com','919896001','2020-11-30',1900,1,3,2),(15,'Fernando','Lucter','10856973','luc_nando@gmail.com','979863235','2020-11-30',2000,3,3,3);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sede`
--

DROP TABLE IF EXISTS `sede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sede` (
  `idSede` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSede`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sede`
--

LOCK TABLES `sede` WRITE;
/*!40000 ALTER TABLE `sede` DISABLE KEYS */;
INSERT INTO `sede` VALUES (1,'Callao'),(2,'San Miguel'),(3,'Chorrillos');
/*!40000 ALTER TABLE `sede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turno`
--

DROP TABLE IF EXISTS `turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turno` (
  `idTurno` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `hora_ingreso` time DEFAULT NULL,
  `hora_salida` time DEFAULT NULL,
  PRIMARY KEY (`idTurno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno`
--

LOCK TABLES `turno` WRITE;
/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
INSERT INTO `turno` VALUES (1,'Mañana','05:00:00','13:00:00'),(2,'Tarde','13:00:00','21:00:00'),(3,'Noche','21:00:00','05:00:00');
/*!40000 ALTER TABLE `turno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL,
  `contraseña` varchar(45) DEFAULT NULL,
  `Empleado_idEmpleado` int NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_Usuario_Empleado1_idx` (`Empleado_idEmpleado`),
  CONSTRAINT `fk_Usuario_Empleado1` FOREIGN KEY (`Empleado_idEmpleado`) REFERENCES `empleado` (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'123',2),(2,'123',5),(3,'123',10);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `nro_placa` char(7) NOT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `modelo` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `Categoria_Vehiculo_idCategoria_Vehiculo` int NOT NULL,
  `Empleado_idEmpleado` int NOT NULL,
  PRIMARY KEY (`nro_placa`),
  KEY `fk_Vehiculo_Categoria_Vehiculo1_idx` (`Categoria_Vehiculo_idCategoria_Vehiculo`),
  KEY `fk_Vehiculo_Empleado1_idx` (`Empleado_idEmpleado`),
  CONSTRAINT `fk_Vehiculo_Categoria_Vehiculo1` FOREIGN KEY (`Categoria_Vehiculo_idCategoria_Vehiculo`) REFERENCES `categoria_vehiculo` (`idCategoria_Vehiculo`),
  CONSTRAINT `fk_Vehiculo_Empleado1` FOREIGN KEY (`Empleado_idEmpleado`) REFERENCES `empleado` (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES ('D1L-702','daihatsu','terios long','negro',2400,2,8),('OPR-589','toyota','yaris','blanco',2500,1,1),('YEP-162','daihatsu','terios long','negro',2400,2,4),('ZEP-835','toyota','yaris','blanco',2500,1,3);
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'proyecto_registroempleados'
--

--
-- Dumping routines for database 'proyecto_registroempleados'
--
/*!50003 DROP PROCEDURE IF EXISTS `SP_CARGO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_CARGO`()
BEGIN
Select * from cargo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_CLIENTE` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_CLIENTE`()
BEGIN
select idCliente,
	   concat(nombre,' ',apellido)
from cliente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_CLIENTEXID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_CLIENTEXID`(cid int)
BEGIN
select c.idCliente,
	   concat(c.nombre,' ',c.apellido) as Cliente,
       c.DNI,
       c.celular,
       c.correo
from cliente as c
where c.idCliente = cid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_EMPLEADOXCARGO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_EMPLEADOXCARGO`(ecargo int)
BEGIN
Select e.idEmpleado,
       concat(e.nombre,' ',e.apellido) as 'Empleado(a)',
       e.DNI,
       e.correo,
       e.celular,
       e.fecha_inicio,
       e.sueldo,
       s.nombre as 'Sede',
       c.nombre as 'Cargo',
       concat(t.nombre,':',t.hora_ingreso,'-',t.hora_salida) as Turno
from empleado as e
inner join sede as s on e.Sede_idSede = s.idSede
inner join cargo as c on e.Cargo_idCargo = c.idCargo
inner join turno as t on e.Turno_idTurno = t.idTurno
where e.Cargo_idCargo = ecargo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_BOLETA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_LISTAR_BOLETA`(fecha1 varchar(20), fecha2 varchar(20))
BEGIN
Select cb.num_boleta,
	   cb.fecha,
       db.lugarPartida,
       db.lugarLlegada,
       cb.total_bol,
       concat(e.nombre,' ',e.apellido) as 'Conductor',
       concat(c.nombre,' ',c.apellido) as 'Cliente'
from cab_boleta as cb
inner join det_boleta as db on cb.num_boleta = db.num_boleta
inner join empleado as e on cb.idEmpleado = e.idEmpleado
inner join cliente as c on cb.idCliente = c.idCliente
where cb.fecha between fecha1 and fecha2;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_CLIENTE` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_LISTAR_CLIENTE`()
BEGIN
Select * from cliente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_CONDUCTOR` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_LISTAR_CONDUCTOR`()
BEGIN
select e.idEmpleado,
	   concat(e.nombre,' ',e.apellido) as Conductor
from empleado as e
WHERE e.idEmpleado in (select Empleado_idEmpleado from vehiculo) and e.Cargo_idCargo = 3;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_CONDUCTORES_SIN_VEHICULO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_LISTAR_CONDUCTORES_SIN_VEHICULO`()
BEGIN
select e.idEmpleado,
	   concat(e.nombre,' ',e.apellido) as Conductor
from empleado as e
WHERE e.Cargo_idCargo = 3;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_EMPLEADO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_LISTAR_EMPLEADO`()
BEGIN
select e.idEmpleado,
	   e.nombre,
       e.apellido,
       e.DNI,
       e.correo,
       e.celular,
       e.fecha_inicio,
       e.sueldo,
       s.nombre,
       c.nombre,
       t.nombre
from empleado as e 
inner join sede as s on e.Sede_idSede = s.idSede
inner join cargo as c on e.Cargo_idCargo = c.idCargo
inner join turno as t on e.Turno_idTurno = t.idTurno
order by e.idEmpleado asc;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_VEHICULO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_LISTAR_VEHICULO`()
BEGIN
select v.nro_placa,
	   v.marca,
       v.modelo,
       v.color,
       v.precio,
       cv.nombre as Categoria_vehiculo,
       concat(e.nombre,' ',e.apellido) as Conductor
from vehiculo as v
inner join categoria_vehiculo as cv on v.Categoria_Vehiculo_idCategoria_Vehiculo = cv.idCategoria_Vehiculo
inner join empleado as e on v.Empleado_idEmpleado = e.idEmpleado;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_SEDE` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_SEDE`()
BEGIN
select * from sede;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_TIPO_VEHICULO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_TIPO_VEHICULO`()
BEGIN
Select * from categoria_vehiculo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_TURNO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_TURNO`()
BEGIN
select * from turno;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_VALIDAR_INGRESO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_VALIDAR_INGRESO`(usr int, pass varchar(45))
BEGIN
Select idEmpleado, 
	   nombre, 
       apellido,
       idUsuario, 
       contraseña 
from usuario as u
inner join empleado as e on u.Empleado_idEmpleado = e.idEmpleado
where u.idUsuario = usr and u.contraseña = pass;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_VEHICULOXTIPO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_VEHICULOXTIPO`(vtipo int)
BEGIN
Select v.nro_placa,
	   v.marca,
       v.modelo,
       v.color,
       v.precio,
       cv.nombre,
       concat(e.nombre,' ',e.apellido) as 'Nombre Completo'
from vehiculo as v
inner join empleado as e on v.Empleado_idEmpleado = e.idEmpleado
inner join categoria_vehiculo as cv on v.Categoria_Vehiculo_idCategoria_Vehiculo = cv.idCategoria_Vehiculo
where v.Categoria_Vehiculo_idCategoria_Vehiculo = vtipo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-06 23:20:02
