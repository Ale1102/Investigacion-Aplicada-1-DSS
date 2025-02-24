<?php

class Product {
    private $conn;
    private $table_name = "productos";

    public $id;
    public $nombre;
    public $descripcion;
    public $precio;
    public $creado_en;

    public function __construct($db) {
        $this->conn = $db;
    }

    // Crear un nuevo producto
    public function create() {
        $query = "INSERT INTO " . $this->table_name . " SET nombre=:nombre, descripcion=:descripcion, precio=:precio, creado_en=NOW()";
        $stmt = $this->conn->prepare($query);

        // Sanitizar datos
        $this->nombre = htmlspecialchars(strip_tags($this->nombre));
        $this->descripcion = htmlspecialchars(strip_tags($this->descripcion));
        $this->precio = htmlspecialchars(strip_tags($this->precio));

        // Enlazar parámetros
        $stmt->bindParam(":nombre", $this->nombre);
        $stmt->bindParam(":descripcion", $this->descripcion);
        $stmt->bindParam(":precio", $this->precio);

        // Ejecutar consulta
        try {
            if ($stmt->execute()) {
                return true;
            } else {
                return false;
            }
        } catch (PDOException $e) {
            error_log("Error en la base de datos: " . $e->getMessage());
            return false;
        }
    }

    // Obtener productos
    public function read($limit = 10, $offset = 0) {
        $query = "SELECT id, nombre, descripcion, precio FROM " . $this->table_name . " LIMIT :limit OFFSET :offset";
        $stmt = $this->conn->prepare($query);

        $stmt->bindParam(":limit", $limit, PDO::PARAM_INT);
        $stmt->bindParam(":offset", $offset, PDO::PARAM_INT);

        $stmt->execute();
        return $stmt;
    }

    // Actualizar un producto existente
    public function update() {
        $query = "UPDATE " . $this->table_name . " SET nombre = :nombre, descripcion = :descripcion, precio = :precio WHERE id = :id";
        $stmt = $this->conn->prepare($query);

        // Sanitizar datos
        $this->nombre = htmlspecialchars(strip_tags($this->nombre));
        $this->descripcion = htmlspecialchars(strip_tags($this->descripcion));
        $this->precio = htmlspecialchars(strip_tags($this->precio));
        $this->id = htmlspecialchars(strip_tags($this->id));

        // Enlazar parámetros
        $stmt->bindParam(":nombre", $this->nombre);
        $stmt->bindParam(":descripcion", $this->descripcion);
        $stmt->bindParam(":precio", $this->precio);
        $stmt->bindParam(":id", $this->id);

        // Ejecutar consulta
        try {
            if ($stmt->execute()) {
                return true;
            } else {
                return false;
            }
        } catch (PDOException $e) {
            error_log("Error en la base de datos: " . $e->getMessage());
            return false;
        }
    }

    // Eliminar un producto
    public function delete() {
        $query = "DELETE FROM " . $this->table_name . " WHERE id = :id";
        $stmt = $this->conn->prepare($query);

        // Sanitizar datos
        $this->id = htmlspecialchars(strip_tags($this->id));

        // Enlazar parámetros
        $stmt->bindParam(":id", $this->id);

        // Ejecutar consulta
        try {
            if ($stmt->execute()) {
                return true;
            } else {
                return false;
            }
        } catch (PDOException $e) {
            error_log("Error en la base de datos: " . $e->getMessage());
            return false;
        }
    }
}