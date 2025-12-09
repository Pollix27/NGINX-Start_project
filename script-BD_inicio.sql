-- Script completo para crear y poblar la base de datos
DROP DATABASE IF EXISTS BD_inicio;
CREATE DATABASE BD_inicio;
USE BD_inicio;

-- Tabla CLIENTES
CREATE TABLE CLIENTES (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    telefono_cliente VARCHAR(15) NOT NULL,
    email_cliente VARCHAR(100) NOT NULL,
    empresa_cliente VARCHAR(150) NOT NULL,
    rfc_cliente VARCHAR(13) NOT NULL
);

-- Tabla PROYECTOS
CREATE TABLE PROYECTOS (
    id_proyecto INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente_fk INT NOT NULL,
    nombre_proyecto VARCHAR(200) NOT NULL,
    descripcion_proyecto TEXT,
    complejidad_proyecto VARCHAR(50),
    fecha_inicio_proyecto DATE,
    fecha_fin_proyecto DATE,
    estado_proyecto VARCHAR(50),
    FOREIGN KEY (id_cliente_fk) REFERENCES CLIENTES(id_cliente)
);

-- Tabla PRESUPUESTOS_DE_PROYECTOS
CREATE TABLE PRESUPUESTOS_DE_PROYECTOS (
    id_presupuesto INT PRIMARY KEY AUTO_INCREMENT,
    id_proyecto_fk INT NOT NULL,
    total_presupuesto DECIMAL(10,2),
    fecha_elaboracion DATE,
    estado_presupuesto VARCHAR(50),
    FOREIGN KEY (id_proyecto_fk) REFERENCES PROYECTOS(id_proyecto)
);

-- Tabla DETALLES_DE_PRESUPUESTOS
CREATE TABLE DETALLES_DE_PRESUPUESTOS (
    id_detalle_presupuesto INT PRIMARY KEY AUTO_INCREMENT,
    id_presupuesto_fk INT NOT NULL,
    metodo_de_pago VARCHAR(50),
    monto_agregado DECIMAL(10,2),
    total_adicional DECIMAL(10,2),
    subtotal DECIMAL(10,2),
    iva DECIMAL(10,2),
    FOREIGN KEY (id_presupuesto_fk) REFERENCES PRESUPUESTOS_DE_PROYECTOS(id_presupuesto)
);

-- Tabla SERVICIOS_ADICIONALES
CREATE TABLE SERVICIOS_ADICIONALES (
    id_servicio_adicional INT PRIMARY KEY AUTO_INCREMENT,
    id_detalle_presupuesto_fk INT NOT NULL,
    descripcion_servicio_adicional TEXT,
    monto_servicio_adicional DECIMAL(10,2),
    FOREIGN KEY (id_detalle_presupuesto_fk) REFERENCES DETALLES_DE_PRESUPUESTOS(id_detalle_presupuesto)
);

-- Tabla RECURSOS
CREATE TABLE RECURSOS (
    id_recurso INT PRIMARY KEY AUTO_INCREMENT,
    id_proyecto_fk INT NOT NULL,
    cantidad_colaboradores INT,
    horas_estimadas INT,
    horas_adicionales INT,
    tarifa_hora DECIMAL(10,2),
    FOREIGN KEY (id_proyecto_fk) REFERENCES PROYECTOS(id_proyecto)
);

-- Tabla SPRINTS_DE_PROYECTOS
CREATE TABLE SPRINTS_DE_PROYECTOS (
    id_sprint INT PRIMARY KEY AUTO_INCREMENT,
    id_proyecto_fk INT NOT NULL,
    nombre_sprint VARCHAR(100),
    fecha_inicio_sprint DATE,
    fecha_fin_sprint DATE,
    estado_sprint VARCHAR(50),
    FOREIGN KEY (id_proyecto_fk) REFERENCES PROYECTOS(id_proyecto)
);

-- Tabla REQUERIMIENTOS_DE_PROYECTOS
CREATE TABLE REQUERIMIENTOS_DE_PROYECTOS (
    id_requerimiento INT PRIMARY KEY AUTO_INCREMENT,
    id_proyecto_fk INT NOT NULL,
    id_sprint_fk INT,
    descripcion_requerimiento TEXT,
    tipo_requerimiento VARCHAR(50),
    prioridad_requerimiento VARCHAR(50),
    horas_estimadas INT,
    FOREIGN KEY (id_proyecto_fk) REFERENCES PROYECTOS(id_proyecto),
    FOREIGN KEY (id_sprint_fk) REFERENCES SPRINTS_DE_PROYECTOS(id_sprint)
);

-- Tabla COLABORADORES
CREATE TABLE COLABORADORES (
    id_colaborador INT PRIMARY KEY AUTO_INCREMENT,
    id_proyecto_fk INT NOT NULL,
    id_sprint_fk INT,
    nombre_colaborador VARCHAR(100),
    email_colaborador VARCHAR(100),
    rol_colaborador VARCHAR(50),
    FOREIGN KEY (id_proyecto_fk) REFERENCES PROYECTOS(id_proyecto),
    FOREIGN KEY (id_sprint_fk) REFERENCES SPRINTS_DE_PROYECTOS(id_sprint)
);

-- ========== DATOS DE PRUEBA ==========

-- Insertar Clientes
INSERT INTO CLIENTES (telefono_cliente, email_cliente, empresa_cliente, rfc_cliente) VALUES
('5551234567', 'contacto@techsolutions.com', 'Tech Solutions SA de CV', 'TSO120515ABC'),
('5559876543', 'info@innovasoft.mx', 'Innova Soft', 'INS180920XYZ'),
('5556547890', 'ventas@digitalcorp.com', 'Digital Corp', 'DIC210315DEF');

-- Insertar Proyectos
INSERT INTO PROYECTOS (id_cliente_fk, nombre_proyecto, descripcion_proyecto, complejidad_proyecto, fecha_inicio_proyecto, fecha_fin_proyecto, estado_proyecto) VALUES
(1, 'Sistema de Inventario', 'Desarrollo de sistema web para control de inventario en tiempo real', 'Alta', '2024-01-15', '2024-06-30', 'En Progreso'),
(1, 'App Móvil de Ventas', 'Aplicación móvil para gestión de ventas en campo', 'Media', '2024-02-01', '2024-05-15', 'Planificación'),
(2, 'Portal de Clientes', 'Portal web para autogestión de clientes', 'Media', '2024-01-20', '2024-04-20', 'En Progreso'),
(3, 'E-commerce Corporativo', 'Tienda en línea con integración de pagos y envíos', 'Alta', '2024-03-01', '2024-08-31', 'Planificación');

-- Insertar Presupuestos
INSERT INTO PRESUPUESTOS_DE_PROYECTOS (id_proyecto_fk, total_presupuesto, fecha_elaboracion, estado_presupuesto) VALUES
(1, 450000.00, '2024-01-10', 'Aprobado'),
(2, 280000.00, '2024-01-25', 'Pendiente'),
(3, 320000.00, '2024-01-15', 'Aprobado'),
(4, 650000.00, '2024-02-25', 'Borrador');

-- Insertar Recursos
INSERT INTO RECURSOS (id_proyecto_fk, cantidad_colaboradores, horas_estimadas, horas_adicionales, tarifa_hora) VALUES
(1, 5, 800, 50, 450.00),
(2, 3, 500, 30, 500.00),
(3, 4, 600, 40, 480.00),
(4, 6, 1000, 80, 520.00);

-- Insertar Detalles de Presupuestos
INSERT INTO DETALLES_DE_PRESUPUESTOS (id_presupuesto_fk, metodo_de_pago, monto_agregado, total_adicional, subtotal, iva) VALUES
(1, 'Transferencia', 400000.00, 50000.00, 450000.00, 72000.00),
(2, 'Tarjeta', 250000.00, 30000.00, 280000.00, 44800.00),
(3, 'Efectivo', 300000.00, 20000.00, 320000.00, 51200.00),
(4, 'Transferencia', 600000.00, 50000.00, 650000.00, 104000.00);

-- Insertar Servicios Adicionales
INSERT INTO SERVICIOS_ADICIONALES (id_detalle_presupuesto_fk, descripcion_servicio_adicional, monto_servicio_adicional) VALUES
(1, 'Hosting y dominio por 1 año', 15000.00),
(1, 'Capacitación de usuarios', 35000.00),
(2, 'Soporte técnico 3 meses', 30000.00),
(3, 'Certificado SSL', 5000.00),
(3, 'Mantenimiento mensual', 15000.00),
(4, 'Integración con API de pagos', 30000.00);

-- Insertar Sprints
INSERT INTO SPRINTS_DE_PROYECTOS (id_proyecto_fk, nombre_sprint, fecha_inicio_sprint, fecha_fin_sprint, estado_sprint) VALUES
(1, 'Sprint 1 - Análisis', '2024-01-15', '2024-02-15', 'Completado'),
(1, 'Sprint 2 - Desarrollo Backend', '2024-02-16', '2024-03-31', 'En Progreso'),
(1, 'Sprint 3 - Desarrollo Frontend', '2024-04-01', '2024-05-15', 'Planificación'),
(2, 'Sprint 1 - Diseño UI/UX', '2024-02-01', '2024-02-28', 'Planificación'),
(3, 'Sprint 1 - Arquitectura', '2024-01-20', '2024-02-20', 'Completado'),
(3, 'Sprint 2 - Implementación', '2024-02-21', '2024-04-20', 'En Progreso');

-- Insertar Requerimientos
INSERT INTO REQUERIMIENTOS_DE_PROYECTOS (id_proyecto_fk, id_sprint_fk, descripcion_requerimiento, tipo_requerimiento, prioridad_requerimiento, horas_estimadas) VALUES
(1, 1, 'Definir modelo de datos para inventario', 'Análisis', 'Alta', 40),
(1, 1, 'Crear diagramas de arquitectura', 'Análisis', 'Alta', 30),
(1, 2, 'Implementar API REST para productos', 'Desarrollo', 'Alta', 80),
(1, 2, 'Crear base de datos MySQL', 'Desarrollo', 'Alta', 50),
(2, 4, 'Diseñar pantallas de la app móvil', 'Diseño', 'Alta', 60),
(2, 4, 'Crear prototipos interactivos', 'Diseño', 'Media', 40),
(3, 5, 'Definir arquitectura del portal', 'Análisis', 'Alta', 35),
(3, 6, 'Desarrollar módulo de autenticación', 'Desarrollo', 'Alta', 70),
(3, 6, 'Implementar dashboard de usuario', 'Desarrollo', 'Media', 90);

-- Insertar Colaboradores
INSERT INTO COLABORADORES (id_proyecto_fk, id_sprint_fk, nombre_colaborador, email_colaborador, rol_colaborador) VALUES
(1, 1, 'Juan Pérez', 'juan.perez@email.com', 'Analista de Sistemas'),
(1, 2, 'María García', 'maria.garcia@email.com', 'Desarrollador Backend'),
(1, 2, 'Carlos López', 'carlos.lopez@email.com', 'Desarrollador Backend'),
(1, 3, 'Ana Martínez', 'ana.martinez@email.com', 'Desarrollador Frontend'),
(2, 4, 'Luis Rodríguez', 'luis.rodriguez@email.com', 'Diseñador UI/UX'),
(2, 4, 'Sofia Hernández', 'sofia.hernandez@email.com', 'Diseñador UI/UX'),
(3, 5, 'Pedro Sánchez', 'pedro.sanchez@email.com', 'Arquitecto de Software'),
(3, 6, 'Laura Torres', 'laura.torres@email.com', 'Desarrollador Full Stack'),
(3, 6, 'Diego Ramírez', 'diego.ramirez@email.com', 'Desarrollador Full Stack');

-- Verificar datos insertados
SELECT 'Clientes' as Tabla, COUNT(*) as Total FROM CLIENTES
UNION ALL SELECT 'Proyectos', COUNT(*) FROM PROYECTOS
UNION ALL SELECT 'Presupuestos', COUNT(*) FROM PRESUPUESTOS_DE_PROYECTOS
UNION ALL SELECT 'Recursos', COUNT(*) FROM RECURSOS
UNION ALL SELECT 'Detalles', COUNT(*) FROM DETALLES_DE_PRESUPUESTOS
UNION ALL SELECT 'Servicios Adicionales', COUNT(*) FROM SERVICIOS_ADICIONALES
UNION ALL SELECT 'Sprints', COUNT(*) FROM SPRINTS_DE_PROYECTOS
UNION ALL SELECT 'Requerimientos', COUNT(*) FROM REQUERIMIENTOS_DE_PROYECTOS
UNION ALL SELECT 'Colaboradores', COUNT(*) FROM COLABORADORES;
