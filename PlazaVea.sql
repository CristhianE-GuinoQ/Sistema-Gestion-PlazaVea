-- =========================================================
-- TRABAJO FINAL
-- Sistema de Ventas - Empresa tipo PLAZA VEA
--
-- Autor: Cristhian Enrique Guiño Quispe
--
-- DESCRIPCIÓN:
-- Proyecto desarrollado utilizando JAVA y base de datos ORACLE.
-- El sistema simula el funcionamiento de ventas de un supermercado
-- tipo PLAZA VEA, permitiendo la gestión de usuarios, clientes,
-- productos, ventas y detalle de ventas.
--
-- El objetivo del sistema es registrar y administrar las operaciones
-- de venta, controlando la información de los productos, clientes
-- y transacciones realizadas dentro del sistema.
--
-- Tecnologías utilizadas:
-- - Java
-- - Oracle Database
-- - JDBC (Conexión a base de datos)
-- =========================================================



-- =====================================================
-- TABLA USUARIO
-- Almacena los usuarios que pueden acceder al sistema
-- =====================================================

CREATE TABLE Usuario (
    id_usuario NUMBER(10) PRIMARY KEY,
    username VARCHAR2(50) NOT NULL,
    password_hash VARCHAR2(200) NOT NULL,
    nombres VARCHAR2(100) NOT NULL,
    apellidos VARCHAR2(100) NOT NULL,
    rol VARCHAR2(20) NOT NULL,
    estado CHAR(1) NOT NULL,
    fecha_registro DATE NOT NULL,
    CONSTRAINT uq_usuario_username UNIQUE (username)
);


-- =====================================================
-- TABLA CLIENTE
-- Información de los clientes del sistema
-- =====================================================

CREATE TABLE Cliente (
    id_cliente NUMBER(10) PRIMARY KEY,
    dni VARCHAR2(8) NOT NULL,
    nombres VARCHAR2(100) NOT NULL,
    apellidos VARCHAR2(100) NOT NULL,
    telefono VARCHAR2(15),
    email VARCHAR2(150),
    fecha_registro DATE NOT NULL,
    estado CHAR(1) NOT NULL,
    CONSTRAINT uq_cliente_dni UNIQUE (dni)
);


-- =====================================================
-- TABLA PRODUCTO
-- Productos disponibles para la venta
-- =====================================================

CREATE TABLE Producto (
    id_producto NUMBER(10) PRIMARY KEY,
    nombre VARCHAR2(150) NOT NULL,
    marca VARCHAR2(100) NOT NULL,
    precio NUMBER(10,2) NOT NULL,
    stock NUMBER(10) NOT NULL,
    estado CHAR(1) NOT NULL
);


-- =====================================================
-- TABLA VENTA
-- Registra cada venta realizada
-- =====================================================

CREATE TABLE Venta (
    id_venta NUMBER(10) PRIMARY KEY,
    fecha_hora DATE NOT NULL,
    total NUMBER(10,2) NOT NULL,
    id_cliente NUMBER(10) NOT NULL,
    id_usuario NUMBER(10) NOT NULL,
    CONSTRAINT fk_venta_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES Cliente(id_cliente),
    CONSTRAINT fk_venta_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES Usuario(id_usuario)
);


-- =====================================================
-- TABLA DETALLE_VENTA
-- Productos incluidos en cada venta
-- =====================================================

CREATE TABLE Detalle_venta (
    id_detalle NUMBER(10) PRIMARY KEY,
    id_venta NUMBER(10) NOT NULL,
    id_producto NUMBER(10) NOT NULL,
    cantidad NUMBER(10),
    precio_unitario NUMBER(10,2),
    subtotal NUMBER(10,2),
    CONSTRAINT fk_detalle_venta
        FOREIGN KEY (id_venta)
        REFERENCES Venta(id_venta),
    CONSTRAINT fk_detalle_producto
        FOREIGN KEY (id_producto)
        REFERENCES Producto(id_producto)
);


-- =====================================================
-- SECUENCIA PARA GENERAR ID DE VENTAS AUTOMÁTICAMENTE
-- =====================================================

CREATE SEQUENCE SEQ_VENTA
START WITH 1
INCREMENT BY 1;


-- =====================================================
-- TRIGGER PARA GENERAR ID_VENTA AUTOMÁTICO
-- =====================================================

CREATE OR REPLACE TRIGGER TRG_VENTA_ID
BEFORE INSERT ON VENTA
FOR EACH ROW
BEGIN
  SELECT SEQ_VENTA.NEXTVAL
  INTO :NEW.ID_VENTA
  FROM DUAL;
END;
/

-- =====================================================
-- SECUENCIA PARA GENERAR ID_DETALLE AUTOMÁTICAMENTE
-- =====================================================

CREATE SEQUENCE SEQ_DETALLE
START WITH 1
INCREMENT BY 1;

-- =====================================================
-- TRIGGER PARA GENERAR ID_DETALLE AUTOMÁTICO
-- =====================================================

CREATE OR REPLACE TRIGGER TRG_DETALLE_ID
BEFORE INSERT ON DETALLE_VENTA
FOR EACH ROW
BEGIN
  SELECT SEQ_DETALLE.NEXTVAL
  INTO :NEW.ID_DETALLE
  FROM DUAL;
END;
/

-- =====================================================
-- DATOS DE USUARIO
-- =====================================================

INSERT INTO Usuario
(id_usuario, username, password_hash, nombres, apellidos, rol, estado, fecha_registro)
VALUES
(1,'admin','admin123','Cristhian','Guino','ADMINISTRADOR','A',SYSDATE);

INSERT INTO Usuario
(id_usuario, username, password_hash, nombres, apellidos, rol, estado, fecha_registro)
VALUES
(2,'vendedor1','vendedor123','Luis','Ramirez','VENDEDOR','A',SYSDATE);

INSERT INTO Usuario
(id_usuario, username, password_hash, nombres, apellidos, rol, estado, fecha_registro)
VALUES
(3,'cajero1','cajero123','Maria','Lopez','CAJERO','A',SYSDATE);

INSERT INTO Usuario
(id_usuario, username, password_hash, nombres, apellidos, rol, estado, fecha_registro)
VALUES
(4,'supervisor','supervisor123','Jorge','Castro','SUPERVISOR','A',SYSDATE);


-- =====================================================
-- CLIENTES
-- =====================================================

INSERT INTO Cliente VALUES (1,'12345678','Juan','Perez','999888777','juan@gmail.com',SYSDATE,'A');
INSERT INTO Cliente VALUES (2,'87654321','Maria','Gomez','988777666','maria@gmail.com',SYSDATE,'A');
INSERT INTO Cliente VALUES (3,'45678912','Luis','Torres','977666555','luis@gmail.com',SYSDATE,'A');
INSERT INTO Cliente VALUES (4,'74125896','Ana','Lopez','966555444','ana@gmail.com',SYSDATE,'A');
INSERT INTO Cliente VALUES (5,'85236974','Pedro','Ramirez','955444333','pedro@gmail.com',SYSDATE,'A');
INSERT INTO Cliente VALUES (6,'96325874','Carlos','Rojas','944333222','carlos@gmail.com',SYSDATE,'A');
INSERT INTO Cliente VALUES (7,'15975348','Lucia','Fernandez','933222111','lucia@gmail.com',SYSDATE,'A');
INSERT INTO Cliente VALUES (8,'78945612','Jose','Martinez','922111000','jose@gmail.com',SYSDATE,'A');
INSERT INTO Cliente VALUES (9,'25874136','Rosa','Castro','911000999','rosa@gmail.com',SYSDATE,'A');
INSERT INTO Cliente VALUES (10,'14725836','Diego','Vargas','900999888','diego@gmail.com',SYSDATE,'A');


-- =====================================================
-- PRODUCTOS
-- =====================================================

INSERT INTO Producto VALUES (1,'Arroz','Costeño',5.50,100,'A');
INSERT INTO Producto VALUES (2,'Leche','Gloria',4.20,100,'A');
INSERT INTO Producto VALUES (3,'Azucar','Rubia',3.80,100,'A');
INSERT INTO Producto VALUES (4,'Aceite','Primor',9.50,100,'A');
INSERT INTO Producto VALUES (5,'Fideos','Don Vittorio',2.90,100,'A');
INSERT INTO Producto VALUES (6,'Galletas','Oreo',3.50,100,'A');
INSERT INTO Producto VALUES (7,'Cereal','Nestle',12.00,100,'A');
INSERT INTO Producto VALUES (8,'Yogurt','Gloria',2.80,100,'A');
INSERT INTO Producto VALUES (9,'Chocolate','Sublime',2.50,100,'A');
INSERT INTO Producto VALUES (10,'Agua','San Luis',1.50,200,'A');
INSERT INTO Producto VALUES (11,'Pan','Bimbo',4.00,80,'A');
INSERT INTO Producto VALUES (12,'Mantequilla','Laive',6.20,60,'A');
INSERT INTO Producto VALUES (13,'Jugo','Frugos',3.10,90,'A');


-- =====================================================
-- VENTAS
-- =====================================================

INSERT INTO Venta (fecha_hora,total,id_cliente,id_usuario)
VALUES (SYSDATE,11.00,1,1);

INSERT INTO Venta (fecha_hora,total,id_cliente,id_usuario)
VALUES (SYSDATE,8.40,2,1);

INSERT INTO Venta (fecha_hora,total,id_cliente,id_usuario)
VALUES (SYSDATE,9.50,3,1);

INSERT INTO Venta (fecha_hora,total,id_cliente,id_usuario)
VALUES (SYSDATE,5.80,4,1);

INSERT INTO Venta (fecha_hora,total,id_cliente,id_usuario)
VALUES (SYSDATE,12.00,5,1);

INSERT INTO Venta (fecha_hora,total,id_cliente,id_usuario)
VALUES (SYSDATE,7.50,6,1);


-- =====================================================
-- DETALLE DE VENTAS
-- =====================================================

INSERT INTO Detalle_venta VALUES (1,1,1,2,5.50,11.00);
INSERT INTO Detalle_venta VALUES (2,2,2,2,4.20,8.40);
INSERT INTO Detalle_venta VALUES (3,3,4,1,9.50,9.50);
INSERT INTO Detalle_venta VALUES (4,4,5,2,2.90,5.80);
INSERT INTO Detalle_venta VALUES (5,5,7,1,12.00,12.00);
INSERT INTO Detalle_venta VALUES (6,6,9,3,2.50,7.50);


-- =====================================================
-- RESTRICCIONES PARA CONTROL DE DATOS (SISTEMA PLAZA VEA)
-- Estas restricciones evitan datos inválidos en el sistema
-- =====================================================

-- El precio del producto debe ser mayor que 0
ALTER TABLE Producto
ADD CONSTRAINT chk_precio_producto
CHECK (precio > 0);

-- El stock del producto no puede ser negativo
ALTER TABLE Producto
ADD CONSTRAINT chk_stock_producto
CHECK (stock >= 0);

-- El estado del producto solo puede ser A (Activo) o I (Inactivo)
ALTER TABLE Producto
ADD CONSTRAINT chk_estado_producto
CHECK (estado IN ('A','I'));

-- La cantidad vendida debe ser mayor que 0
ALTER TABLE Detalle_venta
ADD CONSTRAINT chk_cantidad
CHECK (cantidad > 0);

-- =====================================================
-- CONFIRMAR CAMBIOS
-- =====================================================

COMMIT;


-- =====================================================
-- CONSULTAS DE VERIFICACIÓN
-- =====================================================

SELECT * FROM Usuario;
SELECT * FROM Cliente;
SELECT * FROM Producto;
SELECT * FROM Venta;
SELECT * FROM Detalle_venta;


