USE BD_Clinica_Solar;

INSERT INTO empresa (nombre_comercial, razon_social, direccion, ruc, rubro, codigo_facturacion, logo)
VALUES (
    'Clínica Solar',
    'Clínica Solar S.A.C.',
    'Av. Salud 456 - Lima',
    '12345678901',
    'Servicios de Salud – Clínica privada',
    'C001',
    'logo_empresa.png'
);

INSERT INTO categoria (descripcion) VALUES
('Medicamentos'),
('Equipos Médicos'),
('Suministros de Oficina'),
('Limpieza'),
('Laboratorio'),
('Cirugía');

INSERT INTO producto (id_producto, nombre, descripcion, id_categoria) VALUES
-- Medicamentos
(1, 'Paracetamol', 'Tabletas de 500mg', 1),
(2, 'Ibuprofeno', 'Tabletas de 400mg', 1),
(3, 'Amoxicilina', 'Cápsulas de 500mg', 1),
(4, 'Omeprazol', 'Cápsulas de 20mg', 1),
(5, 'Loratadina', 'Tabletas de 10mg', 1),


-- Equipos médicos
(6, 'Tensiómetro digital', 'Medidor digital de presión arterial', 2),
(7, 'Estetoscopio profesional', 'Estetoscopio de uso médico', 2),
(8, 'Oxímetro de pulso', 'Medidor de saturación de oxígeno', 2),
(9, 'Termómetro infrarrojo', 'Termómetro sin contacto', 2),
(10, 'Balanza médica', 'Balanza para control de peso', 2),

-- Suministros de oficina
(11, 'Resmas de papel A4', 'Paquete de papel tamaño A4', 3),
(12, 'Bolígrafos azules', 'Bolígrafos de tinta azul', 3),
(13, 'Carpetas colgantes', 'Carpetas para archivo', 3),
(14, 'Tóner para impresora', 'Cartucho de tóner', 3),
(15, 'Cinta adhesiva', 'Cinta transparente para oficina', 3),

-- Limpieza
(16, 'Alcohol en gel 500ml', 'Gel antibacterial de 500ml', 4),
(17, 'Cloro industrial', 'Desinfectante líquido', 4),
(18, 'Detergente multiusos', 'Detergente para limpieza general', 4),
(19, 'Paños desinfectantes', 'Toallas húmedas desinfectantes', 4),
(20, 'Guantes de látex', 'Guantes desechables de látex', 4),

-- Laboratorio
(21, 'Tubos de ensayo', 'Tubos de vidrio para muestras', 5),
(22, 'Centrífuga de laboratorio', 'Equipo para separación de muestras', 5),
(23, 'Microscopio binocular', 'Microscopio de dos oculares', 5),
(24, 'Portaobjetos de vidrio', 'Láminas para muestras microscópicas', 5),
(25, 'Reactivos químicos básicos', 'Kit de reactivos para laboratorio', 5),

-- Cirugía
(26, 'Bisturí desechable', 'Bisturí de un solo uso', 6),
(27, 'Guantes quirúrgicos estériles', 'Guantes estériles para cirugía', 6),
(28, 'Campo quirúrgico estéril', 'Paño estéril para procedimientos', 6),
(29, 'Pinzas hemostáticas', 'Pinzas para control de sangrado', 6),
(30, 'Sutura absorbible', 'Hilo quirúrgico absorbible', 6);

INSERT INTO proveedor (id_proveedor, nombre, ruc, direccion, telefono) VALUES
(1, 'Distribuidora Médica Perú', '20123456789', 'Av. La Salud 123, Lima', '987654321'),
(2, 'Suministros Hospitalarios S.A.C.', '20456789123', 'Jr. Bienestar 456, Lima', '912345678'),
(3, 'Equipos y Servicios Médicos', '20345678912', 'Av. Tecnológica 789, Lima', '901234567'),
(4, 'Farmacias del Perú', '20987654321', 'Calle Botica 321, Lima', '998877665'),
(5, 'Grupo Clínico Integral', '20543219876', 'Pje. Clínico 654, Lima', '934567890'),
(6, 'Laboratorios Andes', '20654321987', 'Av. Investigación 987, Lima', '965432198');

INSERT INTO producto_proveedor (id_producto, id_proveedor, precio) VALUES
(1, 1, 89.66),
(1, 4, 21.33),
(1, 6, 179.45),

(2, 1, 64.51),
(2, 4, 110.09),
(2, 6, 154.65),

(3, 1, 58.32),
(3, 4, 73.45),
(3, 6, 97.18),

(4, 1, 43.80),
(4, 4, 61.27),
(4, 6, 82.44),

(5, 1, 92.00),
(5, 4, 50.65),
(5, 6, 77.20),

(6, 2, 155.25),
(6, 3, 137.80),
(6, 5, 190.12),

(7, 2, 88.41),
(7, 3, 119.75),
(7, 5, 173.69),

(8, 2, 91.20),
(8, 3, 142.33),
(8, 5, 109.95),

(9, 2, 111.00),
(9, 3, 128.54),
(9, 5, 160.23),

(10, 2, 145.90),
(10, 3, 132.77),
(10, 5, 175.88);

-- 🚀 **NUEVO BLOQUE → Agrega proveedores para productos del 11 al 30**
INSERT INTO producto_proveedor (id_producto, id_proveedor, precio) VALUES
(11, 1, 18.50),
(12, 1, 1.20),
(13, 1, 3.90),
(14, 1, 75.00),
(15, 1, 2.80),

(16, 1, 8.40),
(17, 1, 12.30),
(18, 1, 9.50),
(19, 1, 14.20),
(20, 1, 6.70),

(21, 1, 4.50),
(22, 1, 560.00),
(23, 1, 750.00),
(24, 1, 3.80),
(25, 1, 95.00),

(26, 1, 2.10),
(27, 1, 1.80),
(28, 1, 15.50),
(29, 1, 22.40),
(30, 1, 35.00);

INSERT INTO area_laboral (descripcion) VALUES
('Administración'),
('Medicina General'),
('Pediatría'),
('Cirugía'),
('Laboratorio'),
('Emergencias'),
('Contabilidad');

INSERT INTO cargo_empleado (descripcion) VALUES 
('Jefe de Área'),
('Médico'),
('Enfermero'),
('Técnico de Laboratorio'),
('Recepcionista'),
('Contador');

INSERT INTO rol (descripcion) VALUES
('Administrador'),
('Coordinador de Área'),
('Soporte ténico');

INSERT INTO tipo_solicitud (descripcion) VALUES
('Reabastecimiento'),
('Mantenimiento'),
('Contratación'),
('Capacitación'),
('Compra');

INSERT INTO empleado (nombres, apellidos, id_area, nombre_ciudad, direccion_empleado, telefono, id_cargo) VALUES

-- Administración (id_area = 1)
('Daniel', 'Bolaños', 1, 'Lima', 'Calle Oficina 200', '123456789', 1),         
('Alvaro', 'Trujillo', 1, 'Lima', 'Calle Oficina 200', '123456789', 1),

-- Medicina General (id_area = 2)
('Paola', 'Salazar', 2, 'Cusco', 'Av. Consulta 111', '978901234', 2),     -- Médico
('Jorge', 'Delgado', 2, 'Cusco', 'Calle Sanidad 222', '989012345', 2),
('Andrea', 'Campos', 2, 'Cusco', 'Jr. Popular 333', '990123456', 3),      -- Enfermero
('Kevin', 'Rosales', 2, 'Abancay', 'Av. Saludable 444', '901234567', 3),

-- Pediatría (id_area = 3)
('Ana', 'Martínez', 3, 'Lima', 'Av. Salud 123', '987654321', 2),
('Luis', 'Fernández', 3, 'Lima', 'Calle Esperanza 456', '912345678', 3),
('Carla', 'Gutiérrez', 3, 'Callao', 'Jr. Niño Jesús 789', '901234567', 3),
('Mario', 'Ramos', 3, 'San Isidro', 'Av. Infantil 321', '956781234', 2),

-- Cirugía (id_area = 4)
('Julia', 'Ortega', 4, 'Arequipa', 'Av. Hospital 202', '934567890', 2),
('Fernando', 'Zúñiga', 4, 'Arequipa', 'Calle Central 303', '945678901', 2),
('César', 'Morales', 4, 'Tacna', 'Jr. Cirujano 404', '956789012', 2),
('Diana', 'Reyes', 4, 'Moquegua', 'Av. Médica 505', '967890123', 3),

-- Laboratorio (id_area = 5)
('Nancy', 'Aguilar', 5, 'Trujillo', 'Calle Pruebas 101', '912345678', 4),  -- Técnico Lab
('Oscar', 'Vega', 5, 'Trujillo', 'Jr. Biología 202', '923456789', 4),
('Raúl', 'Chávez', 5, 'Piura', 'Av. Análisis 303', '934567890', 4),
('Milagros', 'Silva', 5, 'Chimbote', 'Calle Resultados 404', '945678901', 4),

-- Emergencias (id_area = 6)
('Sofía', 'Lopez', 6, 'Chiclayo', 'Av. Urgencias 121', '956789012', 3),    
('Renzo', 'Gómez', 6, 'Lambayeque', 'Calle 911 232', '967890123', 3),
('Lucero', 'Pineda', 6, 'Tumbes', 'Jr. Rápido 343', '978901234', 2),
('Bruno', 'Navarro', 6, 'Iquitos', 'Av. Emergencia 454', '989012345', 2),

-- Contabilidad (id_area = 7)
('Mari', 'Lopez', 7, 'Chiclayo', 'Av. Urgencias 121', '956789012', 1),    
('Renzo', 'Torres', 7, 'Lambayeque', 'Calle 911 232', '967890123', 6),
('Mateo', 'Bazan', 7, 'Tumbes', 'Jr. Rápido 343', '978901234', 6);


INSERT INTO solicitud (id_tipo_solicitud, id_empleado, id_area, fecha_emision, estado_solicitud) VALUES
(1, 1, 1, '2025-07-01', 'Aprobado'),
(1, 2, 1, '2025-07-02', 'Aprobado'),
(1, 23, 7, '2025-07-03', 'Aprobado'),
(1, 1, 1, '2025-07-04', 'Aprobado'),
(1, 23, 7, '2025-07-05', 'Aprobado');

-- Detalles para solicitud 1
INSERT INTO detalle_solicitud (id_solicitud, id_producto, cantidad, justificacion) VALUES
(1, 1, 100, 'Reabastecimiento para farmacia'),
(1, 6, 5, 'Equipos para consulta'),
(1, 11, 20, 'Suministros de oficina'),
(1, 16, 50, 'Medidas de higiene'),
(1, 26, 30, 'Material quirúrgico'),

-- Detalles para solicitud 2
(2, 2, 80, 'Reabastecimiento para farmacia'),
(2, 7, 10, 'Equipos para consulta'),
(2, 12, 30, 'Suministros de oficina'),
(2, 17, 40, 'Limpieza general'),
(2, 27, 100, 'Material quirúrgico'),

-- Detalles para solicitud 3
(3, 3, 60, 'Reabastecimiento para farmacia'),
(3, 8, 7, 'Equipos para consulta'),
(3, 13, 15, 'Suministros de oficina'),
(3, 18, 25, 'Limpieza'),
(3, 28, 40, 'Material quirúrgico'),

-- Detalles para solicitud 4
(4, 4, 70, 'Reabastecimiento para farmacia'),
(4, 9, 8, 'Equipos para consulta'),
(4, 14, 10, 'Suministros de oficina'),
(4, 19, 35, 'Limpieza'),
(4, 29, 20, 'Material quirúrgico'),

-- Detalles para solicitud 5
(5, 5, 90, 'Reabastecimiento para farmacia'),
(5, 10, 3, 'Equipos para consulta'),
(5, 15, 25, 'Suministros de oficina'),
(5, 20, 60, 'Material de protección'),
(5, 30, 50, 'Material quirúrgico');

INSERT INTO usuario (usuario, clave, id_empleado, id_rol) VALUES
('DKBT','1234',1,1),
('admin','1234',2,1),
('MEDG1','1234',3,2),
('MEDG2','1234',15,3);

INSERT INTO solicitud_compra (id_empleado, fecha_emision, estado_orden, observaciones, total_gasto)
VALUES (1, CURDATE(), 'Emitida', 'Compra agrupada de solicitudes 1 a 5', 0.00);

INSERT INTO detalle_solicitud_compra (id_solicitud_compra, id_solicitud)
SELECT LAST_INSERT_ID(), id_solicitud
FROM solicitud
WHERE id_solicitud BETWEEN 1 AND 5;

SELECT id_producto, SUM(cantidad) AS total_cantidad
FROM detalle_solicitud
WHERE id_solicitud BETWEEN 1 AND 5
GROUP BY id_producto;

INSERT INTO orden_compra (id_solicitud_compra, id_proveedor, total)
VALUES (1, 4, 0.00);  -- Actualizamos total luego

INSERT INTO detalle_orden_compra (id_orden_compra, id_producto, cantidad, precio_unitario, sub_total) VALUES
(1, 1, 100, 21.33, 2133.00),
(1, 2, 80, 110.09, 8807.20),
(1, 3, 60, 73.45, 4407.00),
(1, 4, 70, 61.27, 4288.90),
(1, 5, 90, 50.65, 4558.50);

-- Total de la orden (suma subtotales)
UPDATE orden_compra
SET total = 2133.00 + 8807.20 + 4407.00 + 4288.90 + 4558.50
WHERE id_orden_compra = 1;

-- Reflejar el total en solicitud_compra también
UPDATE solicitud_compra
SET total_gasto = 24194.60
WHERE id_solicitud_compra = 1;

