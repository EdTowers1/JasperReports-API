# API de Generación de QR y Reportes

## 📋 Descripción

API REST desarrollada con Spring Boot para la generación de códigos QR. La funcionalidad de reportes con JasperReports está disponible pero requiere configuración de base de datos.

## 🚀 Características Actuales

### ✅ Activas (Sin BD requerida)

- **Generación de Códigos QR**: Convierte texto o URLs en códigos QR
- **Health Check**: Endpoint para verificar el estado de la API

### 🔒 Deshabilitadas (Requieren configuración de BD)

- Reportes de clientes con JasperReports
- Repositorios y entidades JPA

## 🛠️ Tecnologías

- Java 17
- Spring Boot 3.5.3
- ZXing (Generación de QR)
- JasperReports 6.21.0
- Lombok
- Maven

## 📦 Instalación y Ejecución

### Compilar el proyecto

```bash
mvnw.cmd clean install -DskipTests
```

### Ejecutar la aplicación

```bash
mvnw.cmd spring-boot:run
```

O desde VS Code: Run > Start Debugging

La aplicación se iniciará en: `http://localhost:8080`

## 🎯 Endpoints Disponibles

### 1. Health Check

**GET** `/api/health`

Verifica el estado de la API.

**Respuesta:**

```json
{
  "status": "UP",
  "message": "API funcionando correctamente",
  "timestamp": "2025-10-08T12:00:00",
  "features": ["QR Code Generation", "Reports (disabled - no DB)"]
}
```

### 2. Generar Código QR (tamaño por defecto)

**GET** `/api/qr/generate?content={texto}`

Genera un código QR de 300x300 píxeles.

**Parámetros:**

- `content` (requerido): Texto o URL que se codificará en el QR

**Ejemplo:**

```
GET http://localhost:8080/api/qr/generate?content=Hola Mundo
GET http://localhost:8080/api/qr/generate?content=https://github.com
```

**Respuesta:** Imagen PNG del código QR

### 3. Generar Código QR (tamaño personalizado)

**GET** `/api/qr/generate/custom?content={texto}&width={ancho}&height={alto}`

Genera un código QR con dimensiones personalizadas.

**Parámetros:**

- `content` (requerido): Texto o URL que se codificará en el QR
- `width` (opcional, default: 300): Ancho en píxeles
- `height` (opcional, default: 300): Alto en píxeles

**Ejemplo:**

```
GET http://localhost:8080/api/qr/generate/custom?content=https://example.com&width=500&height=500
```

**Respuesta:** Imagen PNG del código QR

### 4. Generar Código QR (método POST)

**POST** `/api/qr/generate`

Genera un código QR a partir de contenido en el body (útil para textos largos).

**Body (text/plain):**

```
Este es un texto largo que quiero codificar en un código QR
```

**Respuesta:** Imagen PNG del código QR

## 🧪 Probar con curl (PowerShell)

### Health Check

```powershell
curl http://localhost:8080/api/health
```

### Generar QR y guardar como imagen

```powershell
curl "http://localhost:8080/api/qr/generate?content=Hola+Mundo" -o qr-code.png
```

### Generar QR con tamaño personalizado

```powershell
curl "http://localhost:8080/api/qr/generate/custom?content=https://github.com&width=500&height=500" -o qr-large.png
```

### Generar QR con POST

```powershell
curl -X POST http://localhost:8080/api/qr/generate -H "Content-Type: text/plain" -d "Texto largo para QR" -o qr-post.png
```

## 🔧 Configuración

### Sin Base de Datos (Configuración Actual)

El proyecto está configurado para ejecutarse **sin conexión a base de datos**. Las siguientes dependencias han sido **removidas** del `pom.xml`:

- `spring-boot-starter-data-jpa`
- `mysql-connector-j`
- `postgresql`

### Habilitar Base de Datos (Opcional)

Si deseas habilitar la funcionalidad de reportes con base de datos:

1. **Agregar dependencias en `pom.xml`:**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

2. **Configurar `application.properties`:**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_datos
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. **Descomentar las clases relacionadas con BD:**
   - `ClienteRepository.java`
   - `ClienteService.java`
   - `ReportService.java`
   - `ReportController.java`
   - `Cliente.java` (modelo)

## 📁 Estructura del Proyecto

```
src/main/java/com/apiReport/demo/
├── controller/
│   ├── GenerateQrController.java    ✅ Activo
│   ├── HealthController.java        ✅ Activo
│   └── ReportController.java        🔒 Deshabilitado
├── service/
│   ├── GenerateQrService.java       ✅ Activo
│   ├── ClienteService.java          🔒 Deshabilitado
│   └── ReportService.java           🔒 Deshabilitado
├── repository/
│   └── ClienteRepository.java       🔒 Deshabilitado
├── model/
│   └── Cliente.java                 🔒 Deshabilitado
├── dto/
│   └── ReportResponse.java          ✅ Activo
├── exception/
│   ├── GlobalExceptionHandler.java  ✅ Activo
│   ├── ReportException.java         ✅ Activo
│   └── ReportNotFoundException.java ✅ Activo
└── ApiReportApplication.java        ✅ Activo
```

## 🐛 Solución de Problemas

### Error: "Failed to configure a DataSource"

**Solución:** Ya está resuelto. El proyecto ya no requiere base de datos.

### Error: "Cannot access com.apiReport.demo.model.Cliente"

**Solución:** Las clases que usan BD están comentadas. Si necesitas habilitarlas, sigue los pasos en "Habilitar Base de Datos".

## 📝 Notas Adicionales

- Los archivos `.jrxml` para JasperReports se encuentran en `src/main/resources/reports/`
- La aplicación usa Lombok, asegúrate de tener el plugin instalado en tu IDE
- DevTools está habilitado para hot-reload durante el desarrollo

## 👤 Autor

API desarrollada para generación de códigos QR y reportes con Spring Boot.
