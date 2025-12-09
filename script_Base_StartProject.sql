-- Crear base de datos
CREATE DATABASE IF NOT EXISTS BD_inicio;
USE BD_inicio;

-- Tabla CLIENTE
CREATE TABLE CLIENTE (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    telefono_cliente VARCHAR(15) NOT NULL,
    email_cliente VARCHAR(100) NOT NULL,
    empresa_cliente VARCHAR(150) NOT NULL,
    rfc_cliente VARCHAR(13) NOT NULL
);

-- Tabla PROYECTO
CREATE TABLE PROYECTO (
    id_proyecto INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente_fk INT NOT NULL,
    nombre_proyecto VARCHAR(200) NOT NULL,
    descripcion_proyecto TEXT,
    complejidad_proyecto VARCHAR(50),
    fecha_inicio_proyecto DATE,
    fecha_fin_proyecto DATE,
    estado_proyecto VARCHAR(50),
    FOREIGN KEY (id_cliente_fk) REFERENCES CLIENTE(id_cliente)
);

-- Tabla PRESUPUESTO_DE_PROYECTO
CREATE TABLE PRESUPUESTO_DE_PROYECTO (
    id_presupuesto INT PRIMARY KEY AUTO_INCREMENT,
    id_proyecto_fk INT NOT NULL,
    total_presupuesto DECIMAL(10,2),
    fecha_elaboracion DATE,
    estado_presupuesto VARCHAR(50),
    FOREIGN KEY (id_proyecto_fk) REFERENCES PROYECTO(id_proyecto)
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
    FOREIGN KEY (id_presupuesto_fk) REFERENCES PRESUPUESTO_DE_PROYECTO(id_presupuesto)
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
    FOREIGN KEY (id_proyecto_fk) REFERENCES PROYECTO(id_proyecto)
);

-- Tabla SPRINTS_DE_PROYECTOS
CREATE TABLE SPRINTS_DE_PROYECTOS (
    id_sprint INT PRIMARY KEY AUTO_INCREMENT,
    id_proyecto_fk INT NOT NULL,
    nombre_sprint VARCHAR(100),
    fecha_inicio_sprint DATE,
    fecha_fin_sprint DATE,
    estado_sprint VARCHAR(50),
    FOREIGN KEY (id_proyecto_fk) REFERENCES PROYECTO(id_proyecto)
);

-- Tabla REQUERIMIENTOS_DE_PROYECTO
CREATE TABLE REQUERIMIENTOS_DE_PROYECTO (
    id_requerimiento INT PRIMARY KEY AUTO_INCREMENT,
    id_proyecto_fk INT NOT NULL,
    id_sprint_fk INT,
    descripcion_requerimiento TEXT,
    tipo_requerimiento VARCHAR(50),
    prioridad_requerimiento VARCHAR(50),
    horas_estimadas INT,
    FOREIGN KEY (id_proyecto_fk) REFERENCES PROYECTO(id_proyecto),
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
    FOREIGN KEY (id_proyecto_fk) REFERENCES PROYECTO(id_proyecto),
    FOREIGN KEY (id_sprint_fk) REFERENCES SPRINTS_DE_PROYECTOS(id_sprint)
);
