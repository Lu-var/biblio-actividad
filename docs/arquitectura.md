# Arquitectura

## Componentes

| Componente | Tecnología | Puerto | Descripción |
|---|---|---|---|
| eureka-server | Spring Cloud Netflix Eureka | 8761 | Registro y descubrimiento de servicios |
| api-gateway | Spring Cloud Gateway (WebMVC) | 8080 | Punto de entrada único, enruta por nombre lógico del servicio |
| libros | Spring Boot + JPA + H2 | 8081 | Catálogo de libros (CRUD + disponibilidad) |
| usuarios | Spring Boot + JPA + H2 | 8082 | Gestión de usuarios (CRUD) |
| prestamos | Spring Boot + JPA + H2 | 8083 | Gestión de préstamos con validación contra libros y usuarios |

## Flujo de comunicación

```
Cliente (Postman / curl)
       │
       ▼
  api-gateway (8080)
       │
       ├── consulta registro ──► eureka-server (8761)
       │
       ├── /api/libros/**      ──► libros (8081)
       ├── /api/usuarios/**    ──► usuarios (8082)
       └── /api/prestamos/**   ──► prestamos (8083)
                                      │
                                      ├── RestClient ──► libros
                                      └── RestClient ──► usuarios
```

## Orden de arranque

1. `eureka-server` (puerto 8761)
2. `libros`, `usuarios`, `prestamos` (cualquier orden, puertos 8081-8083)
3. `api-gateway` (puerto 8080)

Todo el tráfico entra por `http://localhost:8080/api/{servicio}/...`
