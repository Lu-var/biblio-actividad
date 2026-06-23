# API

## libros (puerto 8081)

| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/libros | Listar todos los libros |
| GET | /api/libros/{id} | Obtener libro por ID |
| POST | /api/libros | Crear un libro |
| PUT | /api/libros/{id} | Actualizar un libro |
| DELETE | /api/libros/{id} | Eliminar un libro |
| PATCH | /api/libros/{id}/disponibilidad | Cambiar disponibilidad |

## usuarios (puerto 8082)

| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/usuarios | Listar todos los usuarios |
| GET | /api/usuarios/{id} | Obtener usuario por ID |
| POST | /api/usuarios | Crear un usuario |
| PUT | /api/usuarios/{id} | Actualizar un usuario |
| DELETE | /api/usuarios/{id} | Eliminar un usuario |

## prestamos (puerto 8083)

| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/prestamos | Listar todos los préstamos |
| GET | /api/prestamos/{id} | Obtener préstamo por ID |
| POST | /api/prestamos | Crear un préstamo |
| PUT | /api/prestamos/{id}/devolucion | Registrar devolución |
| GET | /api/prestamos/usuario/{usuarioId} | Préstamos por usuario |
