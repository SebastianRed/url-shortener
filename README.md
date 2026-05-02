<div align="center">

  <h1>🔗 URL Shortener</h1>

  <p>
    <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk" alt="Java" />
    <img src="https://img.shields.io/badge/Spring%20Boot-3.5.5-6DB33F?style=for-the-badge&logo=spring-boot" alt="Spring Boot" />
    <img src="https://img.shields.io/badge/MySQL-8+-4479A1?style=for-the-badge&logo=mysql" alt="MySQL" />
  </p>

  <strong>Aplicación web para acortar URLs, redirigir enlaces y consultar estadísticas de uso con Spring Boot, Thymeleaf y JPA.</strong>
</div>

---

## 🚀 Funcionalidades

En este repositorio encontrarás una implementación completa de un acortador de URLs con backend y vista web:

**✂️ Acortamiento de enlaces**: recibe una URL original y genera un código corto alfanumérico de 6 caracteres.

**↪️ Redirección automática**: al visitar la URL corta, la aplicación redirige al enlace original.

**📊 Estadísticas por enlace**: consulta cantidad de accesos, fecha de creación y metadatos del enlace acortado.

**🖥️ Interfaz web**: incluye vistas Thymeleaf para interactuar con la API desde el navegador.

**✅ Validación y manejo de errores**: controla entradas inválidas y respuestas de error centralizadas.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 17
* **Framework Backend:** Spring Boot 3
* **Motor de plantillas:** Thymeleaf
* **Persistencia:** Spring Data JPA
* **Base de datos:** MySQL
* **Validaciones:** Jakarta Validation
* **Build Tool:** Maven

---

## 📡 Endpoints Principales

### `POST /shorten`

Crea una URL corta a partir de una URL original.

```json
{
  "url": "https://ejemplo.com/articulo/muy/largo"
}
```

### `GET /{shortUrl}`

Redirige a la URL original e incrementa el contador de accesos.

### `GET /stats/{shortUrl}`

Devuelve estadísticas y metadatos de la URL corta.

### `GET /`

Sirve la vista principal `index.html`.

### `GET /index2`

Sirve una segunda versión visual de la interfaz.

---

## 🗂️ Estructura del Proyecto

```text
src/
├── main/
│   ├── java/cl/sebastianrojo/url_shortener/
│   │   ├── UrlShortenerApplication.java        # Punto de entrada Spring Boot
│   │   ├── controller/
│   │   │   ├── UrlController.java              # Endpoints REST para acortar, redirigir y consultar stats
│   │   │   └── ViewController.java             # Controlador MVC para las vistas web
│   │   ├── dto/
│   │   │   ├── ShortenRequest.java             # Request de entrada con validaciones
│   │   │   └── UrlResponse.java                # Respuesta con datos del enlace
│   │   ├── entity/
│   │   │   └── Url.java                        # Entidad JPA persistida en MySQL
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java     # Manejo global de errores
│   │   │   └── UrlNotFoundException.java       # Excepción para códigos inexistentes
│   │   ├── repository/
│   │   │   └── UrlRepository.java              # Acceso a datos con JPA
│   │   └── service/
│   │       └── UrlService.java                 # Lógica de negocio del acortador
│   └── resources/
│       ├── application.yml                     # Configuración del proyecto
│       └── templates/
│           ├── index.html                      # Vista principal
│           └── index2.html                     # Variante alternativa de interfaz
└── test/
    └── java/cl/sebastianrojo/url_shortener/
        └── UrlShortenerApplicationTests.java   # Test base del contexto Spring
```

---

## 💡 Flujo General

1. El usuario envía una URL al endpoint `POST /shorten`.
2. El servicio revisa si esa URL ya existe en base de datos.
3. Si no existe, genera un código aleatorio Base62 de 6 caracteres y lo persiste.
4. Cuando alguien visita `/{shortUrl}`, se resuelve la URL original y se incrementa el contador de accesos.
5. Las estadísticas se consultan mediante `GET /stats/{shortUrl}`.

---

## 📌 Notas

* Si una URL ya fue registrada antes, el servicio reutiliza el mismo código corto.
* El contador de accesos se incrementa cada vez que se usa la ruta de redirección.
* El proyecto incluye dos vistas HTML para experimentar con distintas interfaces del mismo backend.
