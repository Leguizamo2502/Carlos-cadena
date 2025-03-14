CREATE TABLE Usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    identificacion INT NOT NULL,
    correo VARCHAR(80) NOT NULL,
);

CREATE TABLE Autor (
    id_autor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL
);

CREATE TABLE Libro (
    id_libro INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL
    description TEXT NOT NULL
);

CREATE TABLE Editorial (
    id_editorial INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Libro_Editorial (
    id_libro_editorial INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_libro INT NOT NULL,
    id_editorial INT NOT NULL,
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro),
    FOREIGN KEY (id_editorial) REFERENCES Editorial(id_editorial)
);

CREATE TABLE Categoria (
    id_categoria INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Libro_Categoria (
    id_libro_categoria INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_libro INT NOT NULL,
    id_categoria INT NOT NULL,
   
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro),
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

CREATE TABLE Libro_Autor (
    id_libro_autor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_libro INT NOT NULL,
    id_autor INT NOT NULL,
    
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro),
    FOREIGN KEY (id_autor) REFERENCES Autor(id_autor)
);

CREATE TABLE Prestamo (
    id_prestamo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Detalle (
    id_detalle INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    id_libro INT NOT NULL,
    id_prestamo INT NOT NULL,
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro),
    FOREIGN KEY (id_prestamo) REFERENCES Prestamo(id_prestamo)
);

