# 🌐 API REST – E-Management

Esta es la API REST oficial del sistema **E-Management**, una solución de gestión integral para espacios de coworking.

La API expone los servicios necesarios para conectar los tres pilares del sistema:

- 🖥️ Aplicación web de administración
- 📱 Aplicación móvil para socios
- 🧠 Base de datos central y lógica de negocio

---

## 🔧 ¿Qué ofrece esta API?

### Funcionalidades principales:

- 🔐 **Autenticación** con tokens JWT
- 👥 Gestión de usuarios (admins y socios)
- 📆 Gestión de eventos e inscripciones
- 💳 Control de cuotas y pagos
- 💡 Control domótico del espacio (encender/apagar dispositivos)
- 📈 Consulta de información personal o administrativa

La API sigue los principios REST y responde en formato JSON.

---

## 🧑‍💼 Manual para administradores

Esta API está pensada para ser usada por interfaces web o móviles. Como administrador, puedes interactuar con ella a través de un **panel web** o herramientas como Postman (solo si tienes permisos avanzados).

### 🔐 1. Iniciar sesión

**POST** `/auth/login`

```json
{
  "email": "admin@ejemplo.com",
  "password": "********"
}
```

📥 Respuesta: token JWT

---

### 👤 2. Consultar tu perfil

**GET** `/usuarios/me`  
→ Requiere `Authorization: Bearer <token>`

---

### 👥 3. Gestión de usuarios

- `GET /socios` – Lista todos los socios
- `POST /socios` – Crea uno nuevo
- `PUT /socios/{id}` – Actualiza un socio
- `DELETE /socios/{id}` – Elimina un socio

---

### 📅 4. Gestión de eventos

- `GET /eventos` – Lista de eventos activos
- `POST /eventos/{id}/asistir` – Inscripción a evento

---

### 💳 5. Cuotas

- `GET /cuotas` – Consulta tus cuotas personales (móvil)
- `GET /socios/{id}/cuotas` – (Opcional) consulta de cuotas por usuario

---

### 💡 6. Domótica (solo administradores)

- `POST /api/domotica/encender`
- `POST /api/domotica/apagar`

Estas rutas permiten interactuar con dispositivos del coworking (luces, puertas, etc.)

---

## ⚙️ Requisitos técnicos

- Backend en Java + Spring Boot
- Base de datos: PostgreSQL o MySQL
- JWT + Seguridad con roles

---

## 🚀 Cómo iniciar (modo desarrollador)

1. Clona este repositorio
2. Configura el archivo `application.properties` con los datos de tu base
3. Ejecuta el backend (`./mvnw spring-boot:run`)
4. Accede desde `localhost:8080`

---

## 📬 Contacto

Para soporte o reportes, contacta al responsable técnico del coworking o al desarrollador del sistema.
