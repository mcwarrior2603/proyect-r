CREATE TABLE CATEGORIA_PRODUCTOS(
	ID_CATEGORIA INT NOT NULL UNIQUE AUTO_INCREMENT,
    NOMBRE VARCHAR(255) NOT NULL,
    IMAGEN VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID_CATEGORIA)
);