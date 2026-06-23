# API

## libros (puerto 8081) — `@RequestMapping("/api/libros")`

| Método | Ruta | Descripción | Cuerpo Request | Respuesta |
|---|---|---|---|---|
| GET | `/api/libros` | Listar todos los libros | — | `LibroResponse[]` |
| GET | `/api/libros/{id}` | Obtener libro por ID | — | `LibroResponse` |
| POST | `/api/libros` | Crear un libro | `LibroRequest` | `LibroResponse` (201) |
| PUT | `/api/libros/{id}` | Actualizar un libro | `LibroRequest` | `LibroResponse` |
| DELETE | `/api/libros/{id}` | Eliminar un libro | — | 204 No Content |
| PATCH | `/api/libros/{id}/disponibilidad` | Cambiar disponibilidad | — | `LibroResponse` |

### Ejemplos

```
GET /api/libros
→ [{"id":1,"titulo":"Cien años de soledad","autor":"Gabriel García Márquez","isbn":"978-84-376-0494-7","disponible":true}]

POST /api/libros
Body: {"titulo":"Rayuela","autor":"Julio Cortázar","isbn":"978-84-376-0494-8"}
→ {"id":6,"titulo":"Rayuela","autor":"Julio Cortázar","isbn":"978-84-376-0494-8","disponible":true}
```

---

## usuarios (puerto 8082) — `@RequestMapping("/api/usuarios")`

| Método | Ruta | Descripción | Cuerpo Request | Respuesta |
|---|---|---|---|---|
| GET | `/api/usuarios` | Listar todos los usuarios | — | `UsuarioResponse[]` |
| GET | `/api/usuarios/{id}` | Obtener usuario por ID | — | `UsuarioResponse` |
| POST | `/api/usuarios` | Crear un usuario | `UsuarioRequest` | `UsuarioResponse` (201) |
| PUT | `/api/usuarios/{id}` | Actualizar un usuario | `UsuarioRequest` | `UsuarioResponse` |
| DELETE | `/api/usuarios/{id}` | Eliminar un usuario | — | 204 No Content |

### Ejemplos

```
GET /api/usuarios/1
→ {"id":1,"nombre":"María González","email":"maria.gonzalez@email.com","telefono":"912345678"}

POST /api/usuarios
Body: {"nombre":"Carlos Muñoz","email":"carlos.munoz@email.com","telefono":"945678901"}
→ {"id":4,"nombre":"Carlos Muñoz","email":"carlos.munoz@email.com","telefono":"945678901"}
```

---

## prestamos (puerto 8083) — `@RequestMapping("/api/prestamos")`

| Método | Ruta | Descripción | Cuerpo Request | Respuesta |
|---|---|---|---|---|
| GET | `/api/prestamos` | Listar todos los préstamos | — | `PrestamoResponse[]` |
| GET | `/api/prestamos/{id}` | Obtener préstamo por ID | — | `PrestamoResponse` |
| POST | `/api/prestamos` | Crear un préstamo | `PrestamoRequest` | `PrestamoResponse` (201) |
| PUT | `/api/prestamos/{id}/devolucion` | Registrar devolución | — | `PrestamoResponse` |
| GET | `/api/prestamos/usuario/{usuarioId}` | Préstamos por usuario | — | `PrestamoResponse[]` |

### Ejemplos

```
GET /api/prestamos
→ [{"id":1,"libroId":1,"usuarioId":1,"fechaPrestamo":"2026-06-15","fechaDevolucion":null,"libroTitulo":"Cien años de soledad","usuarioNombre":"María González"}]

POST /api/prestamos
Body: {"libroId":2,"usuarioId":3}
→ {"id":3,"libroId":2,"usuarioId":3,"fechaPrestamo":"2026-06-23","fechaDevolucion":null,"libroTitulo":"El principito","usuarioNombre":"Ana Martínez"}

PUT /api/prestamos/1/devolucion
→ {"id":1,"libroId":1,"usuarioId":1,"fechaPrestamo":"2026-06-15","fechaDevolucion":"2026-06-23","libroTitulo":"Cien años de soledad","usuarioNombre":"María González"}
```
