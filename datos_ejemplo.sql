-- Script para insertar datos de ejemplo en BD_inicio
USE BD_inicio;

-- Insertar Clientes
INSERT INTO CLIENTES (empresa_cliente, rfc_cliente, email_cliente, telefono_cliente) VALUES
('Tech Solutions SA', 'TSO1234567A1', 'contacto@techsolutions.com', '5512345678'),
('Innovatech Corp', 'INN9876543B2', 'info@innovatech.com', '5587654321'),
('Digital Systems', 'DIG5555555C3', 'ventas@digitalsys.com', '5598765432');

-- Insertar Proyectos
INSERT INTO PROYECTOS (id_cliente_fk, nombre_proyecto, descripcion_proyecto, complejidad_proyecto, fecha_inicio_proyecto, fecha_fin_proyecto, estado_proyecto) VALUES
(1, 'Sistema de Gestión Empresarial', 'ERP completo para gestión de recursos', 'Alta', '2024-01-15', '2024-12-31', 'En Progreso'),
(1, 'App Móvil de Ventas', 'Aplicación móvil para fuerza de ventas', 'Media', '2024-02-01', '2024-06-30', 'En Progreso'),
(2, 'Portal Web Corporativo', 'Sitio web institucional con CMS', 'Baja', '2024-03-01', '2024-05-15', 'Completado'),
(3, 'Sistema de Inventarios', 'Control de almacén y productos', 'Media', '2024-01-20', '2024-08-30', 'En Progreso');

-- Insertar Sprints
INSERT INTO SPRINTS (id_proyecto_fk, nombre_sprint, estado_sprint, fecha_inicio_sprint, fecha_fin_sprint) VALUES
(1, 'Sprint 1 - Módulo Usuarios', 'Completado', '2024-01-15', '2024-02-15'),
(1, 'Sprint 2 - Módulo Inventario', 'En Progreso', '2024-02-16', '2024-03-31'),
(2, 'Sprint 1 - Diseño UI/UX', 'Completado', '2024-02-01', '2024-02-28'),
(4, 'Sprint 1 - Base de Datos', 'Completado', '2024-01-20', '2024-02-20');

-- Insertar Requisitos
INSERT INTO REQUISITOS (id_proyecto_fk, id_sprint_fk, descripcion_requisitos, prioridad_requisitos) VALUES
(1, 1, 'Implementar autenticación de usuarios con JWT', 'Alta'),
(1, 1, 'Crear módulo de roles y permisos', 'Alta'),
(1, 2, 'Desarrollar CRUD de productos', 'Media'),
(2, 3, 'Diseñar pantallas principales de la app', 'Alta'),
(3, NULL, 'Integrar CMS para gestión de contenido', 'Media'),
(4, 4, 'Diseñar modelo de base de datos', 'Alta');

-- Insertar Colaboradores
INSERT INTO COLABORADORES (id_proyecto_fk, id_sprint_fk, nombre_colaborador, email_colaborador, rol_colaborador, tarifa_hora_programador) VALUES
(1, 1, 'Juan Pérez García', 'juan.perez@dev.com', 'Backend Developer', 450.00),
(1, 2, 'María López Ruiz', 'maria.lopez@dev.com', 'Frontend Developer', 400.00),
(2, 3, 'Carlos Sánchez', 'carlos.sanchez@dev.com', 'UI/UX Designer', 380.00),
(3, NULL, 'Ana Martínez', 'ana.martinez@dev.com', 'Full Stack Developer', 500.00),
(4, 4, 'Luis Hernández', 'luis.hernandez@dev.com', 'Database Administrator', 420.00);

-- Insertar Presupuestos
INSERT INTO PRESUPUESTOS (id_proyecto_fk, id_requisitos_fk, id_colaborador_fk, subtotal, iva, total, fecha_elaboracion_presupuesto, estado_presupuesto, metodo_pago_presupuesto, servicios_adicionales_presupuesto, descripcion_presupuesto) VALUES
(1, 1, 1, 45000.00, 7200.00, 52200.00, '2024-01-10', 'Aprobado', 'Transferencia', 'Hosting y dominio por 1 año', 'Desarrollo módulo de autenticación'),
(1, 3, 2, 32000.00, 5120.00, 37120.00, '2024-02-10', 'En Revisión', 'Transferencia', 'Soporte técnico 3 meses', 'CRUD de productos con interfaz'),
(2, 4, 3, 28000.00, 4480.00, 32480.00, '2024-01-25', 'Aprobado', 'Cheque', 'Prototipo interactivo', 'Diseño completo de UI/UX'),
(3, 5, 4, 55000.00, 8800.00, 63800.00, '2024-02-20', 'Aprobado', 'Transferencia', 'Capacitación de usuarios', 'Portal web con CMS integrado'),
(4, 6, 5, 38000.00, 6080.00, 44080.00, '2024-01-15', 'Aprobado', 'Transferencia', 'Respaldos automáticos', 'Diseño e implementación de BD');
