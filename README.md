# MPAZ PRO 3.º - Microservicios

Microservicios de la app MPAZ PRO 3.º (Escuela Escritora Marcela Paz). Son la fuente oficial de los
datos que la app Android consume con Retrofit.

**Integrantes:** Jairo Cid e Isidora Quinteros
**Asignatura:** DSY1105 - Desarrollo de Aplicaciones Móviles, Duoc UC

| Microservicio | Puerto | Base de datos | Responsabilidad |
|---|---|---|---|
| ms-contenidos | 8081 | `mpaz_contenidos` | Objetivos de aprendizaje y preguntas (desafíos y prueba final) |
| ms-resultados | 8082 | `mpaz_resultados` | Intentos de la prueba final y sus respuestas |

Tecnologías: Java 17, Spring Boot 3, Spring Data JPA, Bean Validation y PostgreSQL.
Al iniciar por primera vez, cada microservicio carga datos de ejemplo (los mismos que usa la app).

## Endpoints

### ms-contenidos (http://localhost:8081)

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/objetivos` | Lista los objetivos |
| GET | `/api/objetivos/{id}` | Obtiene un objetivo |
| POST | `/api/objetivos` | Crea un objetivo |
| PUT | `/api/objetivos/{id}` | Actualiza un objetivo (asignación, revisión UTP, validación del informe) |
| DELETE | `/api/objetivos/{id}` | Elimina un objetivo |
| GET | `/api/preguntas` | Lista las preguntas (filtro opcional `?objetivoId=1`) |
| GET | `/api/preguntas/{id}` | Obtiene una pregunta |
| POST | `/api/preguntas` | Crea una pregunta |
| PUT | `/api/preguntas/{id}` | Actualiza una pregunta |
| DELETE | `/api/preguntas/{id}` | Elimina una pregunta |

Ejemplo de pregunta (POST):
```json
{
  "objetivoId": 1,
  "tipo": "DESAFIO",
  "nivel": 1,
  "enunciado": "¿Cuál palabra es un sustantivo?",
  "opcionA": "Correr",
  "opcionB": "Casa",
  "opcionC": "Rápido",
  "correcta": 1
}
```

### ms-resultados (http://localhost:8082)

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/intentos` | Lista los intentos (filtros opcionales `?objetivoId=1&rut=25418732-1`) |
| GET | `/api/intentos/{id}` | Obtiene un intento con sus respuestas |
| POST | `/api/intentos` | Registra un intento (calcula correctas, total y fecha) |
| DELETE | `/api/intentos/{id}` | Elimina un intento |

Ejemplo de intento (POST):
```json
{
  "rutEstudiante": "25418732-1",
  "objetivoId": 1,
  "respuestas": [
    { "preguntaId": 4, "elegida": 0, "esCorrecta": true },
    { "preguntaId": 5, "elegida": 2, "esCorrecta": false }
  ]
}
```

Si un dato no cumple las validaciones se responde `400` con el detalle de cada campo; si el recurso
no existe, `404`. La colección `MPAZ-PRO-3.postman_collection.json` tiene todas las peticiones.

## Pasos para ejecutar

1. **Base de datos.** Crear las bases `mpaz_contenidos` y `mpaz_resultados` en PostgreSQL.
   - En la nube (Neon), sin instalar nada: copiar la cadena de conexión de cada base y definir las
     variables de entorno en la configuración de ejecución de IntelliJ, por ejemplo:
     `DB_URL=jdbc:postgresql://<host>/mpaz_contenidos?sslmode=require`, `DB_USER=<usuario>`, `DB_PASSWORD=<clave>`.
   - Local: por defecto se usa `localhost:5432` con usuario y clave `postgres`.
2. **Ejecutar.** Abrir `ms-contenidos` y `ms-resultados` en IntelliJ IDEA (trae Maven incluido) y
   ejecutar `MsContenidosApplication` y `MsResultadosApplication`. Con Maven instalado:
   `mvn spring-boot:run` dentro de cada carpeta.
3. **Respaldo sin PostgreSQL.** Si el computador no permite conectarse a PostgreSQL, agregar el
   argumento `--spring.profiles.active=h2` (base H2 en archivo, se guarda en la carpeta `data/`).
4. **Probar.** Abrir `http://localhost:8081/api/objetivos` en el navegador o importar la colección de Postman.
5. **App.** Con ambos microservicios encendidos, ejecutar la app en el emulador (usa `10.0.2.2`).

## Pruebas

Pruebas unitarias de los servicios con JUnit 5 y Mockito: `mvn test` dentro de cada carpeta
(o clic derecho en la carpeta `src/test` > Run Tests en IntelliJ).
