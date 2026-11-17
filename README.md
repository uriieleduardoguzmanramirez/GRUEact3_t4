GRUEact3_t4 · CRUD en Spring Boot con relaciones (JPA + MySQL)

Materia: Programación Web · Docente: Adelina Martínez
Actividad 3: CRUD en Spring Boot y relaciones (sin frontend separado)
Autor(es): Uriel Eduardo Guzman Ramirez

Descripción

Aplicación web en Spring Boot que implementa un CRUD completo de Productos conectado a una base de datos MySQL real. Cada producto pertenece a una Categoría, por lo que al consultar un producto se muestra el nombre de su categoría y no solo su ID.

Tecnologías
Java 21 · Spring Boot (Maven)
Spring Web · Spring Data JPA · Thymeleaf
MySQL (driver oficial)

Relación: Producto muchos a uno Categoria (@ManyToOne) y Categoria uno a muchos Producto (@OneToMany). La llave foránea es categoria_id en la tabla producto.

Ajusta los nombres de campos si tu código usa otros.

Estructura del proyecto
src/main/java/.../
├── entity/       Producto, Categoria
├── repository/   ProductoRepository, CategoriaRepository (JpaRepository)
├── service/      Lógica de negocio
└── controller/   Endpoints del CRUD y vistas
src/main/resources/
├── templates/    Vistas Thymeleaf
└── application.properties
Configuración


URL en el VPS: http://52.159.103.236:8087/productos

Endpoints / Rutas del CRUD
Operación	Método	Ruta
Listar	GET	/productos
Crear	POST	/productos (ajustar)
Actualizar	POST/PUT	/productos/{id} (ajustar)
Eliminar	POST/DELETE	/productos/{id} (ajustar)



