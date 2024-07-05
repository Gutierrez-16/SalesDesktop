select*from usuarios
go
use db_riga
CREATE TABLE usuarios (
  id int IDENTITY PRIMARY KEY NOT NULL,
  usuario VARCHAR(10) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  clave VARCHAR(50) NOT NULL,
  caja_id INT NOT NULL,
  rol VARCHAR(20) NOT NULL,
  estado VARCHAR(10) DEFAULT 'Activo',
  FOREIGN KEY (caja_id) REFERENCES cajas(id)
);

-- Insertar datos en la tabla cajas
INSERT INTO cajas (nombre) VALUES 
('Caja 1');

-- Insertar datos en la tabla usuarios
INSERT INTO usuarios (usuario, nombre, clave, caja_id, rol) VALUES
('admin', 'Administrador', '123456', 1, 'Admin')

INSERT INTO usuarios (usuario, nombre, clave, caja_id, rol) VALUES
('admin', 'Administrador', '123456', 1, 'Admin')

UPDATE usuarios
SET clave = 'admin'
WHERE id = 1;


SELECT u.*, c.nombre AS caja FROM usuarios u 
INNER JOIN cajas c ON u.caja_id = c.id ORDER BY u.estado ASC


CREATE TABLE clientes (
  id int IDENTITY PRIMARY KEY NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  telefono VARCHAR(15) NOT NULL,
  direccion TEXT NOT NULL,
  estado VARCHAR(10) DEFAULT 'Activo'
);

INSERT INTO clientes (nombre, telefono, direccion, estado)
VALUES
('Juan Pérez', '1234567890', 'Av. Principal 123', 'Activo'),
('María González', '9876543210', 'Calle Las Flores 456', 'Activo'),
('Pedro López', '0987654321', 'Jr. Independencia 789', 'Activo');


SELECT * FROM clientes

CREATE TABLE categorias (
  id int IDENTITY PRIMARY KEY NOT NULL,
  categoria VARCHAR(50) NOT NULL,
  estado VARCHAR(10) DEFAULT 'Activo'
);
INSERT INTO categorias (categoria, estado)
VALUES
('Tecnología', 'Activo'),
('Moda', 'Activo'),
('Deportes', 'Activo'),
('Belleza', 'Activo'),
('Hogar', 'Activo');


select *from proveedor

CREATE TABLE medidas (
  id int IDENTITY PRIMARY KEY NOT NULL,
  medida VARCHAR(20) NOT NULL,
  nombre_corto VARCHAR(5) NOT NULL,
  estado VARCHAR(10) DEFAULT 'Activo'
);

INSERT INTO medidas (medida, nombre_corto, estado)
VALUES
('Unidades', 'UNI', 'Activo'),
('Kilogramos', 'KG', 'Activo'),
('Litros', 'L', 'Activo'),
('Metros', 'M', 'Activo');



select * from medidas

CREATE TABLE proveedor (
  id int IDENTITY PRIMARY KEY NOT NULL,
  ruc VARCHAR(20) NOT NULL,
  proveedor VARCHAR(200) NOT NULL,
  telefono VARCHAR(15) NOT NULL,
  direccion TEXT NOT NULL,
  estado VARCHAR(10) DEFAULT 'Activo'
);

-- Actualizar la tabla proveedor en una sola línea
UPDATE proveedor SET ruc = '20987654321', proveedor = 'Ripley', telefono = '056-987654', direccion = 'Av. Los Incas ', estado = 'Inactivo' WHERE id = 1;


-- Tabla proveedor
INSERT INTO proveedor (ruc, proveedor, telefono, direccion) VALUES
('20123456789', 'Distribuidora El Sol', '056-123456', 'Av. Grau 123'),
('20234567890', 'Importadora La Luna', '056-234567', 'Calle Lima 456'),
('20345678901', 'Comercializadora El Mar', '056-345678', 'Jr. Cusco 789'),
('20456789012', 'Mayorista El Sur', '056-456789', 'Av. San Martín 101'),
('20567890123', 'Industrias El Norte', '056-567890', 'Calle Arequipa 202'),
('20678901234', 'Productos El Este', '056-678901', 'Jr. Tacna 303'),
('20789012345', 'Servicios El Oeste', '056-789012', 'Av. Ayacucho 404'),
('20890123456', 'Almacenes El Centro', '056-890123', 'Calle Piura 505'),
('20901234567', 'Mercantil El Oriente', '056-901234', 'Jr. Loreto 606'),
('21012345678', 'Inversiones El Occidente', '056-012345', 'Av. Libertadores 707');

-- Tabla medidas
INSERT INTO medidas (medida, nombre_corto) VALUES
('Kilogramo', 'Kg'),
('Gramo', 'g'),
('Litro', 'L'),
('Mililitro', 'mL'),
('Unidad', 'U'),
('Docena', 'Dz'),
('Ciento', 'C'),
('Metro', 'm'),
('Centímetro', 'cm'),
('Milímetro', 'mm');

-- Tabla categorias
INSERT INTO categorias (categoria) VALUES
('Abarrotes'),
('Bebidas'),
('Lácteos'),
('Frutas'),
('Verduras'),
('Carnes'),
('Panadería'),
('Repostería'),
('Librería'),
('Papelería');

select * from productos


CREATE TABLE productos(
  id int IDENTITY PRIMARY KEY NOT NULL,
  codigo VARCHAR(20) NOT NULL,
  descripcion VARCHAR(250) NOT NULL,
  cantidad INT NOT NULL DEFAULT '0',
  precio_compra DECIMAL(10,2) NOT NULL,
  precio_venta DECIMAL(10,2) NOT NULL,
  proveedor_id INT NOT NULL,
  categoria_id INT NOT NULL,
  medida_id INT NOT NULL,
  estado VARCHAR(10) DEFAULT 'Activo',
  FOREIGN KEY (categoria_id) REFERENCES categorias(id),
  FOREIGN KEY (proveedor_id) REFERENCES proveedor(id),
  FOREIGN KEY (medida_id) REFERENCES medidas(id)
);

-- Insert 10 rows into productos table
INSERT INTO productos (codigo, descripcion, precio_compra, precio_venta, proveedor_id, categoria_id, medida_id) VALUES

('P003', 'Leche evaporada', 1.50, 2.00, 2, 3, 1),
('P004', 'Yogurt natural', 2.00, 2.50, 2, 3, 5),
('P005', 'Manzana roja', 0.50, 0.80, 3, 4, 1),
('P006', 'Plátano de seda', 0.40, 0.60, 3, 4, 1),
('P007', 'Pollo entero', 10.00, 12.00, 4, 5, 3),
('P008', 'Jamón de pavo', 8.00, 10.00, 4, 5, 9),
('P009', 'Pan francés', 0.10, 0.20, 5, 6, 1),
('P010', 'Torta de chocolate', 15.00, 20.00, 5, 6, 9),
('P011', 'Detergente líquido', 5.00, 7.00, 6, 7, 5),
('P012', 'Jabón de tocador', 1.00, 1.50, 6, 8, 1),
('P013', 'Cuaderno de 100 hojas', 2.00, 3.00, 7, 9, 1),
('P014', 'Lápiz HB', 0.20, 0.30, 7, 9, 1),
('P015', 'Borrador blanco', 0.10, 0.20, 7, 9, 1),
('P016', 'Regla de 30 cm', 0.50, 0.80, 7, 9, 8),
('P017', 'Novela de aventuras', 10.00, 15.00, 8, 10, 1),
('P018', 'Revista de actualidad', 5.00, 7.00, 8, 10, 1),
('P019', 'Agua mineral', 1.00, 1.50, 9, 2, 5),
('P020', 'Gaseosa de cola', 1.50, 2.00, 9, 2, 5),
('P021', 'Aceite vegetal', 4.00, 5.00, 1, 1, 5),
('P022', 'Sal yodada', 0.50, 0.80, 1, 1, 4),
('P023', 'Mantequilla', 3.00, 4.00, 2, 3, 1),
('P024', 'Queso fresco', 5.00, 6.00, 2, 3, 3),
('P025', 'Naranja', 0.20, 0.30, 3, 4, 1),
('P026', 'Zanahoria', 0.30, 0.50, 3, 4, 3),
('P027', 'Carne de res', 15.00, 18.00, 4, 5, 3),
('P028', 'Salchicha', 6.00, 8.00, 4, 5, 9),
('P029', 'Galleta de vainilla', 2.00, 3.00, 5, 6, 9),
('P030', 'Jugo de frutas', 3.00, 4.00, 5, 6, 5),
('P031', 'Papel higiénico', 4.00, 5.00, 6, 7, 9),
('P032', 'Shampoo', 6.00, 8.00, 6, 8, 5),
('P033', 'Cuaderno de 200 hojas', 4.00, 5.00, 7, 9, 1),
('P034', 'Lápiz de color', 3.00, 4.00, 7, 9, 2),
('P035', 'Tijera escolar', 2.00, 3.00, 7, 9, 1),
('P036', 'Compás', 1.00, 2.00, 7, 9, 1),
('P037', 'Biografía de un personaje histórico', 12.00, 15.00, 8, 10, 1),
('P038', 'Revista de deportes', 4.00, 6.00, 8, 10, 1),
('P039', 'Cerveza', 2.00, 3.00, 9, 2, 5),
('P040', 'Refresco de naranja', 1.50, 2.00, 9, 2, 5);


select * from productos


SELECT p.*, pr.id, pr.proveedor, m.id, m.medida, c.id, c.categoria FROM productos p
INNER JOIN proveedor pr ON p.proveedor_id = pr.id
INNER JOIN medidas m ON p.medida_id = m.id
INNER JOIN categorias c ON p.categoria_id = c.id WHERE p.id = 1


CREATE TABLE compras(
  id int IDENTITY PRIMARY KEY NOT NULL,
  proveedor_id INT NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  fecha DATE DEFAULT GETDATE() NOT NULL,
  FOREIGN KEY (proveedor_id) REFERENCES proveedor(id)
);
INSERT INTO compras (proveedor_id, total)
VALUES
(1, 100.50),
(2, 150.24),
(3, 75.00);



SELECT * FROM compras

CREATE TABLE detalle_compra(
  id int IDENTITY PRIMARY KEY NOT NULL,
  compra_id INT NOT NULL,
  producto_id INT NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  cantidad INT NOT NULL,
  subtotal DECIMAL(10,2) NOT NULL,
  fecha DATE DEFAULT GETDATE() NOT NULL,
  FOREIGN KEY (compra_id) REFERENCES compras(id),
  FOREIGN KEY (producto_id) REFERENCES productos(id)
);

INSERT INTO detalle_compra (compra_id, producto_id, precio, cantidad, subtotal)
VALUES
(1,1, 10.50, 5, 52.50),
(2,2, 25.25, 4, 101.00),
(3,3, 25.00, 3, 75.00);

SELECT * FROM detalle_compra

SELECT c.*, d.*, p.id, p.descripcion
FROM compras c
INNER JOIN detalle_compra d ON d.compra_id = c.id
INNER JOIN productos p ON p.id = d.producto_id
WHERE c.id = 1


CREATE TABLE configuracion (
  id int IDENTITY PRIMARY KEY NOT NULL,
  ruc VARCHAR(10) NOT NULL,
  nombre VARCHAR(150) NOT NULL,
  telefono VARCHAR(15) NOT NULL,
  direccion VARCHAR(150) NOT NULL,
  mensaje VARCHAR(150) NOT NULL
);

INSERT INTO configuracion (ruc, nombre, telefono, direccion, mensaje)
VALUES ('1234567890', 'Bodega Riga', '985 454 343', 'Calle Principal, No. 123', 'Bienvenido a nuestra Bodega Riga un ambiente acojedor');

SELECT * FROM configuracion

SELECT p.*, c.id, c.proveedor_id FROM proveedor p 
INNER JOIN compras c ON p.id = c.proveedor_id

SELECT p.proveedor, p.telefono, p.direccion, c.fecha, c.total FROM compras c 
INNER JOIN proveedor p ON c.proveedor_id = p.id WHERE c.id = 1

SELECT d.precio, d.cantidad, d.subtotal, p.descripcion FROM compras c
INNER JOIN detalle_compra d ON c.id = d.compra_id
INNER JOIN productos p ON d.producto_id = p.id
WHERE c.id =1;

CREATE TABLE ventas (
  id int IDENTITY PRIMARY KEY NOT NULL,
  cliente_id INT NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  fecha DATE DEFAULT GETDATE() NOT NULL,
  usuario_id INT NOT NULL,
  FOREIGN KEY (cliente_id) REFERENCES clientes(id),
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
INSERT INTO ventas (cliente_id, total, usuario_id) VALUES
(1, 120.50, 1),



CREATE TABLE detalle_ventas(
  id int IDENTITY PRIMARY KEY NOT NULL,
  venta_id INT NOT NULL,
  producto_id INT NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  cantidad INT NOT NULL,
  subtotal DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (venta_id) REFERENCES ventas(id),
  FOREIGN KEY (producto_id) REFERENCES productos(id)
);

select*from  ventas
UPDATE productos SET cantidad = 10 WHERE id = 1
SELECT MAX(id) AS id FROM productos


SELECT d.precio, d.cantidad, d.subtotal, p.descripcion FROM ventas v 
INNER JOIN detalle_ventas d ON v.id = d.venta_id 
INNER JOIN productos p ON d.producto_id = p.id 
WHERE v.id = 1

SELECT c.nombre, c.telefono, c.direccion, v.total, v.fecha FROM ventas v 
INNER JOIN clientes c ON v.cliente_id = c.id WHERE v.id = 1

CREATE TABLE cajas (
  id int IDENTITY PRIMARY KEY NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  estado VARCHAR(10) DEFAULT 'Activo'
);

select * from detalle_cajas

CREATE TABLE detalle_cajas( 
id int IDENTITY PRIMARY KEY NOT NULL, 
fecha_apertura DATE DEFAULT GETDATE() NOT NULL, 
fecha_cierre DATE NULL ,
monto_inicial DECIMAL(10,2) NOT NULL, 
monto_final DECIMAL(10,2) NULL ,
total_ventas INT NULL ,
estado INT DEFAULT '1',
usuario_id INT NOT NULL, 
FOREIGN KEY (usuario_id) REFERENCES usuarios(id) 
);

INSERT INTO detalle_cajas (fecha_cierre, monto_inicial, monto_final, total_ventas, usuario_id) VALUES 
(NULL, 100.00, NULL, NULL, 1);

select * from detalle_cajas

SELECT v.*, c.nombre FROM ventas v INNER JOIN clientes c ON v.cliente_id = c.id ORDER BY v.id DESC

SELECT d.*, u.nombre FROM detalle_cajas d INNER JOIN usuarios u ON d.usuario_id = u.id  ORDER BY d.fecha_apertura DESC

SELECT SUM (total) AS monto_total FROM ventas WHERE usuario_id = 1
