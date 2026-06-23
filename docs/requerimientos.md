# Requerimientos

## Caso de negocio

Sistema de gestión bibliotecaria que permite administrar el catálogo de libros, los usuarios registrados y los préstamos realizados. El sistema expone una API REST unificada a través de un API Gateway, con descubrimiento de servicios mediante Eureka.

## Entidades

| Entidad | Descripción | Atributos |
|---|---|---|
| Libro | Recurso físico de la biblioteca | id, titulo, autor, isbn (único), disponible |
| Usuario | Persona registrada para solicitar préstamos | id, nombre, email (único), telefono |
| Prestamo | Registro de un libro prestado a un usuario | id, libroId, usuarioId, fechaPrestamo, fechaDevolucion |

## Requerimientos funcionales

| ID | Descripción | Microservicio |
|---|---|---|
| RF-01 | Listar todos los libros del catálogo | libros |
| RF-02 | Obtener un libro por su ID | libros |
| RF-03 | Agregar un nuevo libro al catálogo | libros |
| RF-04 | Actualizar datos de un libro | libros |
| RF-05 | Eliminar un libro del catálogo | libros |
| RF-06 | Consultar si un libro está disponible | libros |
| RF-07 | Cambiar la disponibilidad de un libro | libros |
| RF-08 | Listar todos los usuarios registrados | usuarios |
| RF-09 | Obtener un usuario por su ID | usuarios |
| RF-10 | Registrar un nuevo usuario | usuarios |
| RF-11 | Actualizar datos de un usuario | usuarios |
| RF-12 | Eliminar un usuario | usuarios |
| RF-13 | Listar todos los préstamos | prestamos |
| RF-14 | Obtener un préstamo por su ID | prestamos |
| RF-15 | Crear un préstamo (validando disponibilidad del libro) | prestamos |
| RF-16 | Registrar la devolución de un libro | prestamos |
| RF-17 | Listar préstamos de un usuario específico | prestamos |

## Reglas de negocio

| ID | Regla |
|---|---|
| RN-01 | Un libro no puede prestarse si su estado es `disponible = false` |
| RN-02 | Al crear un préstamo, el libro pasa a `disponible = false` |
| RN-03 | Al registrar una devolución, el libro vuelve a `disponible = true` |
| RN-04 | Un usuario debe existir para poder registrar un préstamo a su nombre |
| RN-05 | Un libro debe existir para poder registar un préstamo |
| RN-06 | El ISBN de un libro debe ser único en el catálogo |
| RN-07 | El email de un usuario debe ser único en el sistema |
