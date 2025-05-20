drop database if exists educativa;
DROP DATABASE IF EXISTS educativa;

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS educativa;
USE educativa;

-- Crear el usuario 'desarrollador' con permisos desde local y remoto

-- Tabla: Tutores
CREATE TABLE IF NOT EXISTS tutores (
    id_tutor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    dni VARCHAR(20),
    email VARCHAR(100),
    telefono VARCHAR(20),
    usuario VARCHAR(100) UNIQUE,
    contrasena VARCHAR(100),
    estado ENUM('activo', 'inactivo') DEFAULT 'activo'
);

-- Tabla: Profesores
CREATE TABLE IF NOT EXISTS profesores (
    id_profesor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    dni VARCHAR(20),
    email VARCHAR(100),
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    usuario VARCHAR(100) UNIQUE,
    contrasena VARCHAR(100),
    estado ENUM('activo', 'inactivo') DEFAULT 'activo'
);

-- Tabla: Cursos
CREATE TABLE IF NOT EXISTS cursos (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(255),
    id_profesor INT,
    estado ENUM('activo', 'inactivo') DEFAULT 'activo',
    FOREIGN KEY (id_profesor) REFERENCES profesores(id_profesor) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Tabla: Asignaturas
CREATE TABLE IF NOT EXISTS asignaturas (
    id_asignatura INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    id_profesor INT DEFAULT NULL,
    estado ENUM('activa', 'inactiva') DEFAULT 'activa',
    FOREIGN KEY (id_profesor) REFERENCES profesores(id_profesor) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Tabla: Cursos_Asignaturas (relación muchos a muchos)
CREATE TABLE IF NOT EXISTS cursos_asignaturas (
    id_curso INT,
    id_asignatura INT,
    PRIMARY KEY (id_curso, id_asignatura),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla: Extraescolares
CREATE TABLE IF NOT EXISTS extraescolares (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hora TIME NOT NULL,
    pista VARCHAR(100) NOT NULL,
    fecha_reserva DATE NOT NULL,
    UNIQUE KEY unq_reserva (hora, pista, fecha_reserva)
);

-- Tabla: Estudiantes
CREATE TABLE IF NOT EXISTS estudiantes (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    dni VARCHAR(20),
    fecha_nacimiento DATE,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(100),
    fecha_matricula DATE,
    id_tutor INT,
    usuario VARCHAR(100) UNIQUE,
    contrasena VARCHAR(100),
    estado ENUM('activo', 'inactivo') DEFAULT 'activo',
    FOREIGN KEY (id_tutor) REFERENCES tutores(id_tutor) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Tabla: Matriculas
CREATE TABLE IF NOT EXISTS matriculas (
    id_matricula INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT,
    id_curso INT,
    fecha_matricula DATE,
    estado ENUM('activo', 'inactivo') DEFAULT 'activo',
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla: HistorialAcademico
CREATE TABLE IF NOT EXISTS historialacademico (
    id_historial INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT NOT NULL,
    id_asignatura INT NOT NULL,
    nota_final DECIMAL(5,2),
    fecha_aprobacion DATE,
    comentarios TEXT,
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla: Asistencia
CREATE TABLE IF NOT EXISTS asistencia (
    id_asistencia INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT,
    id_curso INT,
    fecha DATE,
    justificado BOOLEAN,
    motivo_ausencia VARCHAR(255),
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabla: Eventos
CREATE TABLE IF NOT EXISTS eventos (
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(255),
    fecha_inicio DATE,
    fecha_fin DATE,
    ubicacion VARCHAR(255),
    tipo_evento ENUM('academico', 'deportivo', 'religioso') DEFAULT 'academico'
);

-- Tabla: Estudiantes_Eventos
CREATE TABLE IF NOT EXISTS estudiantes_eventos (
    id_estudiante INT,
    id_evento INT,
    comentario VARCHAR(255),
    confirmado BOOLEAN,
    PRIMARY KEY (id_estudiante, id_evento),
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_evento) REFERENCES eventos(id_evento) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla: Horarios
CREATE TABLE IF NOT EXISTS horarios (
    id_horario INT AUTO_INCREMENT PRIMARY KEY,
    id_asignatura INT,
    dia_semana ENUM('lunes', 'martes', 'miercoles', 'jueves', 'viernes', 'sabado', 'domingo') DEFAULT 'lunes',
    hora_inicio TIME,
    hora_fin TIME,
    id_profesor INT,
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id_asignatura) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_profesor) REFERENCES profesores(id_profesor) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabla: Becas
CREATE TABLE IF NOT EXISTS becas (
    id_beca INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT,
    tipo_beca ENUM('rendimiento', 'deportiva', 'economia', 'diversidad') DEFAULT 'rendimiento',
    monto DECIMAL(10,2),
    fecha_asignacion DATE,
    estado_beca ENUM('activo', 'inactivo') DEFAULT 'activo',
    comentarios VARCHAR(255),
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla: Convalidaciones
CREATE TABLE IF NOT EXISTS convalidaciones (
    id_convalidacion INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT,
    id_asignatura_original INT,
    fecha_convalidacion DATE,
    estado_convalidacion ENUM('Aprobada', 'Pendiente', 'Rechazada') DEFAULT 'Pendiente',
    comentarios VARCHAR(255),
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_asignatura_original) REFERENCES asignaturas(id_asignatura) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabla: Administradores
CREATE TABLE IF NOT EXISTS administradores (
    id_administrador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    dni VARCHAR(20),
    email VARCHAR(100),
    telefono VARCHAR(20),
    usuario VARCHAR(100) UNIQUE,
    contrasena VARCHAR(100),
    estado ENUM('activo', 'inactivo') DEFAULT 'activo'
);


-- Índices adicionales
CREATE INDEX idx_estudiante ON matriculas(id_estudiante);
CREATE INDEX idx_curso ON matriculas(id_curso);
CREATE INDEX idx_estudiante_historial ON historialacademico(id_estudiante);
CREATE INDEX idx_asignaturahistorial ON historialacademico(id_asignatura);
CREATE INDEX idx_estudiante_asistencia ON asistencia(id_estudiante);
CREATE INDEX idx_curso_asistencia ON asistencia(id_curso);
CREATE INDEX idx_estudiante_beca ON becas(id_estudiante);
CREATE INDEX idx_asignatura_profesor ON asignaturas(id_profesor);
CREATE INDEX idx_asignatura_curso ON cursos_asignaturas(id_curso);

-- insert tutores
INSERT INTO tutores (nombre, apellido, dni, email, telefono, usuario, contrasena, estado) 
VALUES 
('Juan', 'López Sánchez', '12345678', 'juan.perez@email.com', '5551234', 'Tutor', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('María', 'González', '23456789', 'maria.gonzalez@email.com', '5555678', 'mariagonzalez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Carlos', 'Molina', '34567890', 'carlos.molina@email.com', '5556789', 'carlosmolina', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Ana', 'Jiménez', '45678901', 'ana.jimenez@email.com', '5557890', 'anajimenez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Luis', 'Fernández', '56789012', 'luis.fernandez@email.com', '5558901', 'luisfernandez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Sofía', 'Martínez', '67890123', 'sofia.martinez@email.com', '5559012', 'sofia.martinez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Raúl', 'Sánchez', '78901234', 'raul.sanchez@email.com', '5550123', 'raulsanchez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Esther', 'Ramírez', '89012345', 'esther.ramirez@email.com', '5551235', 'estherramirez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Eduardo', 'Hernández', '90123456', 'eduardo.hernandez@email.com', '5552345', 'eduardohernandez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Teresa', 'Álvarez', '01234567', 'teresa.alvarez@email.com', '5553456', 'teresaalvarez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Pedro', 'López', '12346789', 'pedro.lopez@email.com', '5554567', 'pedrolopez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('José', 'García', '23457890', 'jose.garcia@email.com', '5557679', 'josegarcia', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Lucía', 'Díaz', '34568901', 'lucia.diaz@email.com', '5556781', 'luciadiaz', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Manuel', 'Gómez', '45679012', 'manuel.gomez@email.com', '5557891', 'manuelgomez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Claudia', 'Paredes', '56789123', 'claudia.paredes@email.com', '5558902', 'claudiaparedes', '1a1dc91c907325c69271ddf0c944bc72', 'activo');

-- insert profesores
INSERT INTO profesores (nombre, apellido, dni, email, telefono, direccion, usuario, contrasena, estado)
VALUES
('Carlos', 'Ramírez Carrasco', '34567890', 'carlos.ramirez@email.com', '5557890', 'Calle Falsa 123', 'Profe', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Laura', 'Martínez', '45678901', 'laura.martinez@email.com', '5550987', 'Avenida Siempre Viva 456', 'lauramartinez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Andrés', 'González', '56789012', 'andres.gonzalez@email.com', '5554456', 'Calle Ejemplo 789', 'andresgonzalez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Marta', 'López', '67890123', 'marta.lopez@email.com', '5554567', 'Avenida Libertad 321', 'martalopez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Luis', 'Sánchez', '78901234', 'luis.sanchez@email.com', '5555678', 'Calle Ficticia 100', 'luissanchez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('María', 'Hernández', '89012345', 'maria.hernandez@email.com', '5556789', 'Calle del Sol 11', 'mariahernandez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Raúl', 'Ramírez', '90123456', 'raul.ramirez@email.com', '5557890', 'Calle Real 22', 'raulramirez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('José', 'Díaz', '01234567', 'jose.diaz@email.com', '5558901', 'Calle Alta 33', 'josediaz', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Esther', 'Sánchez', '12345678', 'esther.sanchez@email.com', '5559012', 'Avenida Central 44', 'esthersanchez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Lucía', 'González', '23456789', 'lucia.gonzalez@email.com', '5550123', 'Calle Baja 55', 'luciagonzalez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('David', 'Martínez', '34567890', 'david.martinez@email.com', '5551234', 'Avenida del Norte 66', 'davidmartinez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Paula', 'Hernández', '45678901', 'paula.hernandez@email.com', '5552345', 'Calle de la Paz 77', 'paulahernandez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Víctor', 'Ramírez', '56789012', 'victor.ramirez@email.com', '5553456', 'Calle Larga 88', 'victorramirez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Sandra', 'Lopez', '67890123', 'sandra.lopez@email.com', '5554567', 'Calle Nueva 99', 'sandralopez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Antonio', 'Martínez', '78901234', 'antonio.martinez@email.com', '5555678', 'Calle Mayor 100', 'antoniomartinez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Isabel', 'Gutiérrez', '89023456', 'isabel.gutierrez@email.com', '5556781', 'Calle Gran 102', 'isabelgutierrez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Manuel', 'Pérez', '90134567', 'manuel.perez@email.com', '5557892', 'Avenida Sol 103', 'manuelperez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Marina', 'Torres', '01245678', 'marina.torres@email.com', '5558902', 'Calle Primavera 104', 'marinatorres', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('José Luis', 'Jiménez', '12356789', 'joseluis.jimenez@email.com', '5559013', 'Calle Litoral 105', 'joseluismartinez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Susana', 'Morales', '23467890', 'susana.morales@email.com', '5550124', 'Avenida Costa 106', 'susanamorales', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Miguel', 'Ruiz', '34578901', 'miguel.ruiz@email.com', '5551235', 'Calle Horizonte 107', 'miguelruiz', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('María José', 'Vázquez', '45689012', 'mariajose.vazquez@email.com', '5552346', 'Avenida Lomas 108', 'mariajosevazquez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Tomás', 'Fernández', '56790123', 'tomas.fernandez@email.com', '5513457', 'Calle Las Palmas 109', 'tomasfernandez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Patricia', 'Serrano', '67891234', 'patricia.serrano@email.com', '5514568', 'Calle Castilla 110', 'patriciaserrano', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Juan', 'Soto', '78902345', 'juan.soto@email.com', '5555679', 'Avenida Verde 111', 'juansoto', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Adriana', 'Gómez', '89013456', 'adriana.gomez@email.com', '5556780', 'Calle Alegría 112', 'adrianagomez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('José Antonio', 'Herrera', '90124567', 'joseantonio.herrera@email.com', '5557893', 'Calle Diamante 113', 'joseantonioherrera', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Alba', 'Méndez', '01235678', 'alba.mendez@email.com', '5558903', 'Calle Brillante 114', 'albamendez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Javier', 'García', '12346789', 'javier.garcia@email.com', '5559014', 'Avenida Estrella 115', 'javiergarcia', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Lourdes', 'Vega', '23457890', 'lourdes.vega@email.com', '5550125', 'Calle Solana 116', 'lourdesvega', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Felipe', 'Cruz', '34568901', 'felipe.cruz@email.com', '5551236', 'Calle del Río 117', 'felipecruz', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Sara', 'Rojas', '45679012', 'sara.rojas@email.com', '5552347', 'Avenida Mar 118', 'sararojas', '1a1dc91c907325c69271ddf0c944bc72', 'activo');


-- insert extraescolares
INSERT INTO extraescolares (hora, pista, fecha_reserva) VALUES
('16:00:00', 'Pista Fútbol', CURDATE()),
('17:00:00', 'Pista Baloncesto', CURDATE()),
('18:00:00', 'Aula Usos Múltiples', CURDATE()),
('16:00:00', 'Pista Fútbol', CURDATE() + INTERVAL 1 DAY),
('19:00:00', 'Santuario', CURDATE() + INTERVAL 1 DAY),
('20:00:00', 'Pista Baloncesto', CURDATE() + INTERVAL 2 DAY),
('21:00:00', 'Aula Usos Múltiples', CURDATE() + INTERVAL 2 DAY);

-- Insertar asignaturas 
INSERT INTO asignaturas (nombre, descripcion, id_profesor, estado)
VALUES
-- 1DAM - Desarrollo de Aplicaciones Multiplataforma
('Sistemas informáticos', 'Fundamentos de hardware, sistemas operativos y redes', 1, 'activa'),
('Bases de Datos', 'Diseño e implementación de bases de datos relacionales', 2, 'activa'),
('Programación', 'Fundamentos de programación estructurada y algoritmos', 3, 'activa'),
('Lenguajes de marcas y sistemas de gestión de información', 'XML, JSON y tecnologías web básicas', 4, 'activa'),
('Entornos de desarrollo', 'Herramientas y entornos para el desarrollo de software', 5, 'activa'),
('Formación y Orientación Laboral', 'Derecho laboral y orientación profesional', 6, 'activa'),

-- 2DAM - Desarrollo de Aplicaciones Multiplataforma
('Acceso a datos', 'Técnicas avanzadas de acceso y manipulación de datos', 7, 'activa'),
('Desarrollo de interfaces', 'Diseño e implementación de interfaces de usuario', 8, 'activa'),
('Programación multimedia y dispositivos móviles', 'Desarrollo para Android/iOS y multimedia', 9, 'activa'),
('Programación de servicios y procesos', 'Programación concurrente y servicios', 10, 'activa'),
('Sistemas de gestión empresarial', 'ERP, CRM y sistemas de información empresarial', 11, 'activa'),
('Empresa e iniciativa emprendedora', 'Creación y gestión de empresas', 13, 'activa'),

-- 1ASIR - Administración de Sistemas Informáticos en Red
('Implantación de sistemas operativos', 'Instalación y configuración de SO', 28, 'activa'),
('Planificación y administración de redes', 'Diseño y administración de redes', 29, 'activa'),
('Fundamentos de hardware', 'Componentes y mantenimiento de hardware', 30, 'activa'),
('Gestión de bases de datos', 'Administración de sistemas gestores de BD', 31, 'activa'),
('Lenguajes de marcas y sistemas de gestión de información', 'XML y tecnologías de información', 32, 'activa'),
('Formación y Orientación Laboral', 'Derecho laboral y orientación profesional', 1, 'activa'),

-- 2ASIR - Administración de Sistemas Informáticos en Red
('Administración de sistemas gestores de bases de datos', 'Administración avanzada de BD', 2, 'activa'),
('Implantación de aplicaciones web', 'Despliegue de aplicaciones web', 3, 'activa'),
('Administración de sistemas operativos', 'Administración avanzada de SO', 4, 'activa'),
('Servicios de red e Internet', 'Configuración de servicios de red', 5, 'activa'),
('Seguridad y alta disponibilidad', 'Seguridad informática y alta disponibilidad', 6, 'activa'),
('Empresa e iniciativa emprendedora', 'Creación y gestión de empresas', 8, 'activa'),

-- 1DAW - Desarrollo de Aplicaciones Web
('Sistemas informáticos', 'Fundamentos de hardware y sistemas operativos', 15, 'activa'),
('Bases de datos', 'Diseño e implementación de bases de datos', 16, 'activa'),
('Programación', 'Fundamentos de programación y algoritmos', 17, 'activa'),
('Lenguajes de marcas y sistemas de gestión de información', 'HTML, CSS, XML y tecnologías web', 18, 'activa'),
('Entornos de desarrollo', 'Herramientas para desarrollo web', 19, 'activa'),
('Formación y Orientación Laboral', 'Derecho laboral y orientación profesional', 20, 'activa'),

-- 2DAW - Desarrollo de Aplicaciones Web
('Desarrollo web en entorno cliente', 'JavaScript y frameworks front-end', 21, 'activa'),
('Desarrollo web en entorno servidor', 'PHP, Java EE, Node.js', 22, 'activa'),
('Despliegue de aplicaciones web', 'Despliegue y administración de aplicaciones web', 23, 'activa'),
('Diseño de interfaces web', 'Diseño UX/UI para web', 24, 'activa'),
('Proyecto de Desarrollo de Aplicaciones Web', 'Desarrollo de proyecto final', 25, 'activa'),
('Empresa e iniciativa emprendedora', 'Creación y gestión de empresas', 26, 'activa'),

-- 1SMR - Sistemas Microinformáticos y Redes
('Montaje y mantenimiento de equipos', 'Ensamblaje y mantenimiento hardware', 10, 'activa'),
('Sistemas operativos monopuesto', 'Instalación y uso de SO', 11, 'activa'),
('Aplicaciones ofimáticas', 'Suite ofimática y herramientas colaborativas', 12, 'activa'),
('Redes locales', 'Instalación y configuración de redes LAN', 13, 'activa'),
('Formación y Orientación Laboral', 'Derecho laboral y orientación profesional', 14, 'activa'),
('Inglés','Ingles aplicado a la tecnología',10,'activa'),

-- 2SMR - Sistemas Microinformáticos y Redes
('Sistemas operativos en red', 'Administración de SO en red', 15, 'activa'),
('Seguridad informática', 'Principios básicos de seguridad', 16, 'activa'),
('Servicios en red', 'Implementación de servicios de red', 17, 'activa'),
('Aplicaciones web', 'Instalación y configuración de aplicaciones web', 18, 'activa'),
('Empresa e iniciativa emprendedora', 'Creación y gestión de empresas', 19, 'activa'),
('Horas de libre configuración', 'Horas de libre configuración', 20, 'activa');

-- Insertar cursos con nombres de ciclos formativos de informática
INSERT INTO cursos (nombre, descripcion, id_profesor, estado)
VALUES
('1DAM - Desarrollo de Aplicaciones Multiplataforma', 'Curso de desarrollo de aplicaciones para dispositivos móviles y plataformas web', 1, 'activo'),
('2DAM - Desarrollo de Aplicaciones Multiplataforma', 'Curso avanzado en desarrollo de aplicaciones multiplataforma', 2, 'activo'),
('1ASIR - Administración de Sistemas Informáticos en Red', 'Curso de administración de redes y sistemas informáticos', 3, 'activo'),
('2ASIR - Administración de Sistemas Informáticos en Red', 'Curso avanzado de administración de redes y sistemas informáticos', 4, 'activo'),
('1DAW - Desarrollo de Aplicaciones Web', 'Curso de desarrollo de aplicaciones web front-end y back-end', 5, 'activo'),
('2DAW - Desarrollo de Aplicaciones Web', 'Curso avanzado de desarrollo de aplicaciones web', 6, 'activo'),
('1SMR - Sistemas Microinformáticos y Redes', 'Curso de instalación y mantenimiento de sistemas informáticos y redes', 7, 'activo'),
('2SMR - Sistemas Microinformáticos y Redes', 'Curso avanzado en mantenimiento de sistemas y redes informáticas', 8, 'activo');


-- Insertar relaciones entre cursos y asignaturas
INSERT INTO cursos_asignaturas (id_curso, id_asignatura) VALUES
(1, 1),  -- Sistemas informáticos
(1, 2),  -- Bases de Datos
(1, 3),  -- Programación
(1, 4),  -- Lenguajes de marcas y sistemas de gestión de información
(1, 5),  -- Entornos de desarrollo
(1, 6);  -- Formación y Orientación Laboral

-- 2DAM - Desarrollo de Aplicaciones Multiplataforma
INSERT INTO cursos_asignaturas (id_curso, id_asignatura) VALUES
(2, 7),  -- Acceso a datos
(2, 8),  -- Desarrollo de interfaces
(2, 9),  -- Programación multimedia y dispositivos móviles
(2, 10), -- Programación de servicios y procesos
(2, 11), -- Sistemas de gestión empresarial
(2, 12); -- Empresa e iniciativa emprendedora

-- 1ASIR - Administración de Sistemas Informáticos en Red
INSERT INTO cursos_asignaturas (id_curso, id_asignatura) VALUES
(3, 13), -- Implantación de sistemas operativos
(3, 14), -- Planificación y administración de redes
(3, 15), -- Fundamentos de hardware
(3, 16), -- Gestión de bases de datos
(3, 17), -- Lenguajes de marcas y sistemas de gestión de información
(3, 18); -- Formación y Orientación Laboral

-- 2ASIR - Administración de Sistemas Informáticos en Red
INSERT INTO cursos_asignaturas (id_curso, id_asignatura) VALUES
(4, 19), -- Administración de sistemas gestores de bases de datos
(4, 20), -- Implantación de aplicaciones web
(4, 21), -- Administración de sistemas operativos
(4, 22), -- Servicios de red e Internet
(4, 23), -- Seguridad y alta disponibilidad
(4, 24); -- Empresa e iniciativa emprendedora

-- 1DAW - Desarrollo de Aplicaciones Web
INSERT INTO cursos_asignaturas (id_curso, id_asignatura) VALUES
(5, 25), -- Sistemas informáticos
(5, 26), -- Bases de datos
(5, 27), -- Programación
(5, 28), -- Lenguajes de marcas y sistemas de gestión de información
(5, 29), -- Entornos de desarrollo
(5, 30); -- Formación y Orientación Laboral

-- 2DAW - Desarrollo de Aplicaciones Web
INSERT INTO cursos_asignaturas (id_curso, id_asignatura) VALUES
(6, 31), -- Desarrollo web en entorno cliente
(6, 32), -- Desarrollo web en entorno servidor
(6, 33), -- Despliegue de aplicaciones web
(6, 34), -- Diseño de interfaces web
(6, 35), -- Proyecto de Desarrollo de Aplicaciones Web
(6, 36); -- Empresa e iniciativa emprendedora

-- 1SMR - Sistemas Microinformáticos y Redes
INSERT INTO cursos_asignaturas (id_curso, id_asignatura) VALUES
(7, 37), -- Montaje y mantenimiento de equipos
(7, 38), -- Sistemas operativos monopuesto
(7, 39), -- Aplicaciones ofimáticas
(7, 40), -- Redes locales
(7, 41), -- Formación y Orientación Laboral
(7, 42); -- Inglés

-- 2SMR - Sistemas Microinformáticos y Redes
INSERT INTO cursos_asignaturas (id_curso, id_asignatura) VALUES
(8, 43), -- Sistemas operativos en red
(8, 44), -- Seguridad informática
(8, 45), -- Servicios en red
(8, 46), -- Aplicaciones web
(8, 47), -- Empresa e iniciativa emprendedora
(8, 48); -- Horas de libre configuración

-- insert estudiantes
INSERT INTO estudiantes (nombre, apellido, dni, fecha_nacimiento, direccion, telefono, email, fecha_matricula, id_tutor, usuario, contrasena, estado)
VALUES
('Pedro', 'López Sánchez', '56789012', '2005-03-10', 'Calle Ejemplo 789', '5552468', 'pedro.lopez@email.com', '2025-01-15', 1, 'Estudiante', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('María', 'López Sánchez', '12344323', '2004-02-08', 'Calle Ejemplo 789', '5552468', 'maria.lopez@email.com', '2025-01-15', 1, 'Estudiante2', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Ana', 'Sánchez', '67890123', '2006-07-20', 'Calle Ficticia 101', '5553579', 'ana.sanchez@email.com', '2025-01-20', 2, 'anasanchez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('José', 'Martín', '78901234', '2004-05-10', 'Calle Real 200', '5554680', 'jose.martin@email.com', '2025-01-10', 3, 'josemartin', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Luis', 'Pérez', '89012345', '2007-02-15', 'Calle del Sol 300', '5555791', 'luis.perez@email.com', '2025-01-12', 4, 'luisperez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Carla', 'Gómez', '90123456', '2005-09-22', 'Calle de la Paz 400', '5556802', 'carla.gomez@email.com', '2025-01-14', 5, 'carlagomez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('David', 'Suárez', '01234567', '2005-12-02', 'Calle Siempre Viva 500', '5557913', 'david.suarez@email.com', '2025-01-13', 6, 'davidsuarez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Estefanía', 'Vega', '12345678', '2006-04-12', 'Calle Blanca 600', '5559024', 'estefania.vega@email.com', '2025-01-16', 7, 'estefaniavega', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Javier', 'Sánchez', '23456789', '2007-08-19', 'Calle Real 700', '5550135', 'javier.sanchez@email.com', '2025-01-17', 8, 'javiersanchez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Sofía', 'Rodríguez', '34567890', '2004-11-03', 'Calle Larga 800', '5551246', 'sofia.rodriguez@email.com', '2025-01-18', 9, 'sofiarodriguez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Isabel', 'Castillo', '45678901', '2006-03-25', 'Calle Ancha 900', '5552357', 'isabel.castillo@email.com', '2025-01-19', 10, 'isabelcastillo', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Ricardo', 'Álvarez', '56789012', '2005-10-30', 'Calle Baja 1000', '5553468', 'ricardo.alvarez@email.com', '2025-01-20', 11, 'ricardoalvarez', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Mario', 'Martín', '67890123', '2006-05-04', 'Calle Sur 1100', '5554579', 'mario.martin@email.com', '2025-01-21', 12, 'mariomartin', '1a1dc91c907325c69271ddf0c944bc72', 'activo'),
('Juliana', 'Vásquez', '78901234', '2005-12-15', 'Calle Este 1200', '5555680', 'juliana.vasquez@email.com', '2025-01-22', 13, 'julianavasquez', '1a1dc91c907325c69271ddf0c944bc72', 'activo');

-- insert matriculas
INSERT INTO matriculas (id_estudiante, id_curso, fecha_matricula, estado)
VALUES
(1, 1, '2025-01-15', 'activo'),
(2, 2, '2025-01-20', 'activo'),
(3, 3, '2025-01-10', 'activo'),
(4, 4, '2025-01-12', 'activo'),
(5, 5, '2025-01-14', 'activo'),
(6, 6, '2025-01-13', 'activo'),
(7, 7, '2025-01-16', 'activo'),
(8, 8, '2025-01-17', 'activo'),
(9, 1, '2025-01-18', 'activo'),
(10, 1, '2025-01-19', 'activo'),
(11, 1, '2025-01-20', 'activo'),
(12, 1, '2025-01-21', 'activo'),
(13, 1, '2025-01-22', 'activo');

-- insert historialacademico
INSERT INTO historialacademico (id_estudiante, id_asignatura, nota_final, fecha_aprobacion, comentarios)
VALUES
-- estudiante 1
(1, 1, 8.5, '2024-06-15', 'Aprobado satisfactoriamente'),
(1, 2, 7.0, '2024-06-15', 'Buen desempeño'),
(1, 3, 9.0, '2024-06-15', 'Excelente participación'),
(1, 4, 6.5, '2024-06-15', 'Cumplió los objetivos'),
(1, 5, 8.0, '2024-06-15', 'Buen trabajo en equipo'),
(1, 6, 7.5, '2024-06-15', 'Aprobado'),
-- estudiante 9
(9, 1, 7.2, '2024-06-15', 'Cumplió los requisitos'),
(9, 2, 6.8, '2024-06-15', 'Satisfactorio'),
(9, 3, 8.9, '2024-06-15', 'Excelente desempeño'),
(9, 4, 7.0, '2024-06-15', 'Buen nivel'),
(9, 5, 8.1, '2024-06-15', 'Muy buen rendimiento'),
(9, 6, 6.5, '2024-06-15', 'Aprobado con observaciones'),
-- estudiante 10
(10, 1, 9.3, '2024-06-15', 'Sobresaliente'),
(10, 2, 8.7, '2024-06-15', 'Muy buen desempeño'),
(10, 3, 9.0, '2024-06-15', 'Participativo y responsable'),
(10, 4, 7.5, '2024-06-15', 'Cumplió satisfactoriamente'),
(10, 5, 8.6, '2024-06-15', 'Trabajo destacado'),
(10, 6, 9.1, '2024-06-15', 'Excelente resultado'),
-- estudiante 11
(11, 1, 6.5, '2024-06-15', 'Aprobado'),
(11, 2, 7.8, '2024-06-15', 'Buena actitud'),
(11, 3, 8.0, '2024-06-15', 'Participativo'),
(11, 4, 7.2, '2024-06-15', 'Buen progreso'),
(11, 5, 8.3, '2024-06-15', 'Trabajo consistente'),
(11, 6, 7.5, '2024-06-15', 'Cumplió expectativas'),
-- estudiante 12
(12, 1, 5.5, '2024-06-15', 'Aprobado justo'),
(12, 2, 6.0, '2024-06-15', 'Aceptable'),
(12, 3, 7.5, '2024-06-15', 'Superó los objetivos'),
(12, 4, 6.7, '2024-06-15', 'Esfuerzo notable'),
(12, 5, 7.0, '2024-06-15', 'Progreso positivo'),
(12, 6, 6.5, '2024-06-15', 'Satisfactorio'),
-- estudiante 13
(13, 1, 8.8, '2024-06-15', 'Excelente desempeño'),
(13, 2, 9.0, '2024-06-15', 'Muy participativo'),
(13, 3, 9.5, '2024-06-15', 'Resultado excepcional'),
(13, 4, 8.0, '2024-06-15', 'Cumplió todos los requisitos'),
(13, 5, 9.2, '2024-06-15', 'Gran trabajo en equipo'),
(13, 6, 8.7, '2024-06-15', 'Trabajo destacado'),
-- estudiantes varios
(2, 2, 7.9, '2025-06-15', 'Buenos avances'),
(3, 3, 9.2, '2025-06-20', 'Excelente en física'),
(4, 4, 6.8, '2025-06-22', 'Aprobado con esfuerzo'),
(5, 5, 7.4, '2025-06-25', 'Nota aceptable, requiere mejorar'),
(6, 6, 8.0, '2025-06-28', 'Bien en las prácticas'),
(7, 7, 7.6, '2025-06-30', 'Buen desempeño'),
(8, 8, 6.9, '2025-07-05', 'Falta más dedicación'),
(9, 9, 8.2, '2025-07-07', 'Buen rendimiento en geografía'),
(10, 10, 7.1, '2025-07-10', 'Requiere trabajar más en teoría'),
(11, 11, 9.0, '2025-07-12', 'Muy buenos resultados en el examen'),
(12, 12, 6.5, '2025-07-15', 'Aprobado pero necesita repasar más'),
(13, 13, 8.4, '2025-07-17', 'Muy buen rendimiento en música');

-- insert asistencia
INSERT INTO asistencia (id_estudiante, id_curso, fecha, justificado, motivo_ausencia)
VALUES
(1, 1, '2025-03-01', TRUE, 'Enfermedad'),
(2, 2, '2025-03-02', TRUE, 'Enfermedad'),
(3, 3, '2025-03-03', TRUE, 'Enfermedad'),
(4, 4, '2025-03-04', FALSE, NULL),
(5, 5, '2025-03-05', TRUE, 'Enfermedad'),
(6, 6, '2025-03-06', FALSE, NULL),
(7, 7, '2025-03-07', TRUE, 'Viaje escolar'),
(8, 8, '2025-03-08', TRUE, 'Viaje escolar'),
(9, 1, '2025-03-09', FALSE, NULL),
(10, 2, '2025-03-10', TRUE, 'Compromiso personal'),
(11, 1, '2025-03-11', TRUE, 'Compromiso personal'),
(12, 2, '2025-03-12', FALSE, NULL),
(13, 3, '2025-03-13', TRUE, 'Problemas familiares');

-- insert eventos
INSERT INTO eventos (nombre, descripcion, fecha_inicio, fecha_fin, ubicacion, tipo_evento)
VALUES
('Exposición de arte', 'Exposición de arte moderno de estudiantes', '2025-04-01', '2025-04-05', 'Sala de exposiciones', 'academico'),
('Torneo de Fútbol', 'Competencia de fútbol entre estudiantes', '2025-04-07', '2025-04-10', 'Campo deportivo', 'deportivo'),
('Concierto de Música', 'Concierto de la banda escolar', '2025-04-12', '2025-04-12', 'Auditorio principal', 'academico'),
('Jornada Deportiva', 'Competencia deportiva en varias disciplinas', '2025-04-15', '2025-04-15', 'Cancha deportiva', 'deportivo'),
('Fiesta Religiosa', 'Celebración religiosa anual', '2025-04-20', '2025-04-20', 'Iglesia local', 'religioso'),
('Torneo de Baloncesto', 'Competencia de baloncesto entre equipos de la escuela', '2025-04-22', '2025-04-25', 'Cancha de baloncesto', 'deportivo'),
('Muestra Cultural', 'Exposición cultural de los estudiantes', '2025-04-25', '2025-04-30', 'Teatro escolar', 'academico'),
('Maratón Escolar', 'Carrera de 5 km por la escuela', '2025-05-01', '2025-05-01', 'Parque escolar', 'deportivo'),
('Conferencia sobre Tecnología', 'Conferencia sobre avances tecnológicos', '2025-05-05', '2025-05-05', 'Aula magna', 'academico'),
('Viaje Escolar a la Playa', 'Excursión escolar a la playa para estudiantes de 5to año', '2025-05-10', '2025-05-12', 'Playa de Veracruz', 'deportivo'),
('Día de la Familia', 'Evento para las familias de los estudiantes', '2025-05-15', '2025-05-15', 'Parque central', 'deportivo'),
('Celebración de Navidad', 'Celebración navideña con actividades familiares', '2025-12-20', '2025-12-22', 'Aula de eventos', 'religioso'),
('Torneo de Ajedrez', 'Competencia de ajedrez entre los estudiantes', '2025-06-01', '2025-06-01', 'Salón de juegos', 'academico'),
('Semana del Medio Ambiente', 'Actividades de sensibilización sobre el medio ambiente', '2025-06-05', '2025-06-07', 'Escuela', 'academico'),
('Charla sobre Salud Mental', 'Charla sobre bienestar y salud mental para estudiantes', '2025-06-10', '2025-06-10', 'Auditorio', 'academico');

-- insert horarios
INSERT INTO horarios (id_asignatura, id_profesor, dia_semana, hora_inicio, hora_fin)
VALUES 
(1, 1, 'lunes', '15:30:00', '16:30:00'),
(1, 1, 'lunes', '16:30:00', '17:30:00'),
(2, 2, 'lunes', '17:30:00', '18:30:00'),
(2, 2, 'lunes', '19:00:00', '20:00:00'),
(3, 3, 'lunes', '20:00:00', '21:00:00'),
(4, 4, 'lunes', '21:00:00', '22:00:00'),
(4, 4, 'martes', '15:30:00', '16:30:00'),
(5, 5, 'martes', '16:30:00', '17:30:00'),
(6, 6, 'martes', '17:30:00', '18:30:00'),
(1, 1, 'martes', '19:00:00', '20:00:00'),
(6, 6, 'martes', '20:00:00', '21:00:00'),
(3, 3, 'martes', '21:00:00', '22:00:00'),
(6, 6, 'miercoles', '15:30:00', '16:30:00'),
(6, 6, 'miercoles', '16:30:00', '17:30:00'),
(6, 6, 'miercoles', '17:30:00', '18:30:00'),
(5, 5, 'miercoles', '19:00:00', '20:00:00'),
(5, 5, 'miercoles', '20:00:00', '21:00:00'),
(1, 1, 'miercoles', '21:00:00', '22:00:00'),
(3, 3, 'jueves', '15:30:00', '16:30:00'),
(1, 1, 'jueves', '16:30:00', '17:30:00'),
(1, 1, 'jueves', '17:30:00', '18:30:00'),
(4, 4, 'jueves', '19:00:00', '20:00:00'),
(5, 5, 'jueves', '20:00:00', '21:00:00'),
(5, 5, 'jueves', '21:00:00', '22:00:00'),
(1, 1, 'viernes', '15:30:00', '16:30:00'),
(1, 1, 'viernes', '16:30:00', '17:30:00'),
(1, 1, 'viernes', '17:30:00', '18:30:00'),
(2, 2, 'viernes', '19:00:00', '20:00:00'),
(2, 2, 'viernes', '20:00:00', '21:00:00'),
(4, 4, 'viernes', '21:00:00', '22:00:00');



-- insert becas
INSERT INTO becas (id_estudiante, tipo_beca, monto, fecha_asignacion, estado_beca, comentarios)
VALUES
(1, 'rendimiento', 1000.00, '2025-03-01', 'activo', 'Beca por excelencia académica'),
(2, 'rendimiento', 800.00, '2025-03-02', 'activo', 'Beca por buen desempeño en ciencias'),
(3, 'deportiva', 1200.00, '2025-03-03', 'activo', 'Beca por logros en deportes'),
(4, 'economia', 1500.00, '2025-03-04', 'activo', 'Beca económica para estudiantes de bajos recursos'),
(5, 'diversidad', 1000.00, '2025-03-05', 'activo', 'Beca por diversidad de habilidades'),
(6, 'rendimiento', 900.00, '2025-03-06', 'activo', 'Beca por alto rendimiento académico'),
(7, 'deportiva', 1100.00, '2025-03-07', 'activo', 'Beca por logros en atletismo'),
(8, 'economia', 1300.00, '2025-03-08', 'activo', 'Beca económica para estudiantes con necesidades'),
(9, 'rendimiento', 950.00, '2025-03-09', 'activo', 'Beca por rendimiento académico en matemáticas'),
(10, 'diversidad', 1050.00, '2025-03-10', 'activo', 'Beca por contribución a la diversidad cultural'),
(11, 'deportiva', 1250.00, '2025-03-11', 'activo', 'Beca por desempeño en fútbol'),
(12, 'economia', 1350.00, '2025-03-12', 'activo', 'Beca económica para estudiantes en riesgo social'),
(13, 'rendimiento', 850.00, '2025-03-13', 'activo', 'Beca por desempeño destacado en ciencias sociales');

-- insert convalidaciones (usando id_asignatura_original)
INSERT INTO convalidaciones (id_estudiante, id_asignatura_original, fecha_convalidacion, estado_convalidacion, comentarios)
VALUES
(1, 1, '2025-03-01', 'Aprobada', 'Curso convalidado por materia similar en otro instituto'),
(2, 2, '2025-03-02', 'Pendiente', 'Pendiente de revisión de contenido con el profesorado'),
(3, 3, '2025-03-03', 'Rechazada', 'Contenido no cubre el programa de la escuela'),
(4, 4, '2025-03-04', 'Aprobada', 'Convalidación aceptada después de la revisión'),
(5, 5, '2025-03-05', 'Pendiente', 'Falta entregar documentos de respaldo'),
(6, 6, '2025-03-06', 'Aprobada', 'Aprobada tras verificar las equivalencias de contenidos'),
(7, 7, '2025-03-07', 'Rechazada', 'No es compatible con el programa académico'),
(8, 8, '2025-03-08', 'Aprobada', 'Convalidación aceptada por coincidencia de temario'),
(9, 1, '2025-03-09', 'Pendiente', 'Esperando la documentación necesaria'),
(10, 1, '2025-03-10', 'Aprobada', 'Confirmada por el coordinador académico'),
(11, 1, '2025-03-11', 'Pendiente', 'Pendiente la revisión de la comisión educativa'),
(12, 1, '2025-03-12', 'Aprobada', 'Convalidación de materia por otro curso aprobado'),
(13, 1, '2025-03-13', 'Rechazada', 'No cumple con los criterios para convalidación');


INSERT INTO administradores (nombre, apellido, dni, email, telefono, usuario, contrasena, estado)
VALUES 
('Juan', 'Pérez Pérez', '12345678A', 'juan.perez@example.com', '600123456', 'Admin', '1a1dc91c907325c69271ddf0c944bc72', 'activo');
-- Fin del script