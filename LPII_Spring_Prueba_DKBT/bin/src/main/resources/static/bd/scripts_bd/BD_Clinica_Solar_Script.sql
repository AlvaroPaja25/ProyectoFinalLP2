DROP DATABASE IF EXISTS BD_Clinica_Solar;
CREATE DATABASE BD_Clinica_Solar;
USE BD_Clinica_Solar;

CREATE TABLE empresa (
    id 						INT PRIMARY KEY AUTO_INCREMENT,
    nombre_comercial 		VARCHAR(100) NOT NULL,
    razon_social 			VARCHAR(150) NOT NULL,
    rubro					VARCHAR(100) NOT NULL,
    direccion 				VARCHAR(200),
    ruc 					VARCHAR(11) NOT NULL UNIQUE,
    codigo_facturacion 		VARCHAR(20),
    logo 					VARCHAR(255)
);

CREATE TABLE rol (
    id_rol 					INT AUTO_INCREMENT PRIMARY KEY,
    descripcion 			VARCHAR(100)
);

CREATE TABLE area_laboral (
    id_area 				INT AUTO_INCREMENT PRIMARY KEY,
    descripcion 			VARCHAR(100)
);

CREATE TABLE cargo_empleado (
    id_cargo 				INT AUTO_INCREMENT PRIMARY KEY,
    descripcion 			VARCHAR(100)
);

CREATE TABLE empleado (
    id_empleado 			BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres 				VARCHAR(100),
    apellidos 				VARCHAR(100),
    id_area 				INT,
    nombre_ciudad 			VARCHAR(100),
    direccion_empleado 		VARCHAR(100),
    telefono 				VARCHAR(100),
    id_cargo				INT,
    estado 					ENUM('Activo', 'Inactivo') DEFAULT 'Activo',
    CONSTRAINT fk_empleado_area FOREIGN KEY (id_area) REFERENCES area_laboral(id_area),
    CONSTRAINT fk_empleado_cargo FOREIGN KEY (id_cargo) REFERENCES cargo_empleado(id_cargo)
);

CREATE TABLE usuario (
    id_usuario 				BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario 				VARCHAR(50),
    clave 					VARCHAR(50),
    id_empleado 			BIGINT,
    id_rol 					INT,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol),
    CONSTRAINT fk_usuario_empleado FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado)
);

CREATE TABLE categoria (
    id_categoria 			INT AUTO_INCREMENT PRIMARY KEY,
    descripcion 			VARCHAR(100) NOT NULL
);

CREATE TABLE producto (
    id_producto				BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre 					VARCHAR(100) NOT NULL,
    descripcion 			TEXT,
    id_categoria 			INT NOT NULL,
    estado					ENUM('Vigente', 'Descontinuado') DEFAULT 'Vigente',
    CONSTRAINT fk_producto_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE proveedor (
    id_proveedor 			BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre 					VARCHAR(100) NOT NULL,
    ruc 					VARCHAR(20),
    direccion 				VARCHAR(150),
    telefono 				VARCHAR(20),
	estado 					ENUM('Activo', 'Inactivo') DEFAULT 'Activo'
);

CREATE TABLE producto_proveedor (
    id_producto 			BIGINT NOT NULL,
    id_proveedor 			BIGINT NOT NULL,
    precio 					DECIMAL(10,2) NOT NULL,
    estado					ENUM('Habilitado', 'Deshabilitado') DEFAULT 'Habilitado',
    PRIMARY KEY (id_producto, id_proveedor),
    CONSTRAINT fk_pp_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    CONSTRAINT fk_pp_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE tipo_solicitud (
    id_tipo_solicitud 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion 			VARCHAR(100)
);

CREATE TABLE solicitud (
    id_solicitud 			BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_solicitud 		BIGINT NOT NULL,
    id_empleado 			BIGINT NOT NULL,
    id_area 				INT NOT NULL,
    fecha_emision 			DATE,
    estado_solicitud 		ENUM('Pendiente','Recibida','Rechazado','Aprobado') DEFAULT 'Pendiente',
    CONSTRAINT fk_solicitud_tipo FOREIGN KEY (id_tipo_solicitud) REFERENCES tipo_solicitud(id_tipo_solicitud),
    CONSTRAINT fk_solicitud_area FOREIGN KEY (id_area) REFERENCES area_laboral(id_area),
    CONSTRAINT fk_solicitud_empleado FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado)
);

CREATE TABLE detalle_solicitud (
    id_detalle_solicitud 	BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_solicitud 			BIGINT NOT NULL,
    id_producto				BIGINT,
    justificacion			VARCHAR(200),
    cantidad 				INT,
    CONSTRAINT fk_detalle_solicitud FOREIGN KEY (id_solicitud) REFERENCES solicitud(id_solicitud),
    CONSTRAINT fk_detalle_solicitud_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);
CREATE TABLE solicitud_compra (
  id_solicitud_compra   	BIGINT AUTO_INCREMENT PRIMARY KEY,
  id_empleado       		BIGINT NOT NULL,
  fecha_emision      		DATE NOT NULL,
  estado_orden      		ENUM('Emitida', 'Aprobado', 'Rechazado') DEFAULT 'Emitida',
  observaciones     		VARCHAR(200),
  total_gasto       		DECIMAL(10,2),
  CONSTRAINT fk_soc_empleado FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado)
);

CREATE TABLE detalle_solicitud_compra (
  id_solicitud_compra     	BIGINT NOT NULL,
  id_solicitud 				BIGINT NOT NULL,
  PRIMARY KEY (id_solicitud_compra, id_solicitud),
  CONSTRAINT fk_dsoc_solicitud FOREIGN KEY (id_solicitud) REFERENCES solicitud(id_solicitud),
  CONSTRAINT fk_dsoc_soc FOREIGN KEY (id_solicitud_compra) REFERENCES solicitud_compra(id_solicitud_compra)
);

CREATE TABLE orden_compra (
  id_orden_compra     		BIGINT AUTO_INCREMENT PRIMARY KEY,
  id_solicitud_compra   	BIGINT NOT NULL,
  id_proveedor     			BIGINT NOT NULL,
  total       		 		DECIMAL(10,2) NOT NULL,
  CONSTRAINT fk_oc_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor),
  CONSTRAINT fk_oc_soc FOREIGN KEY (id_solicitud_compra) REFERENCES solicitud_compra(id_solicitud_compra)
);

CREATE TABLE detalle_orden_compra(
  id_orden_compra    		BIGINT NOT NULL,
  id_producto       		BIGINT NOT NULL,  
  cantidad        			INT NOT NULL,
  precio_unitario     		DECIMAL(10,2) NOT NULL,
  sub_total       			DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id_orden_compra, id_producto),
  CONSTRAINT fk_doc_orden_compra FOREIGN KEY (id_orden_compra) REFERENCES orden_compra(id_orden_compra),
  CONSTRAINT fk_doc_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);