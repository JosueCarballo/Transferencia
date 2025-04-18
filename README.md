Pasos para usar la aplicación

1. Descargar el repositorio y abrir el proyecto en tu IDE(IntelliJ IDEA)
2. Configura la base de datos utilizando XAMPP e iniciando servicios
3. Crear una base de datos llamada "bancodb"
4. Ejecutar la aplicación buscando la clase "SistemaBancarioApplication"
5. Abre tu navegador y ve a: http://localhost:8080/swagger-ui.html
   
Aquí verás todos los servicios disponibles

- Ver todas las cuentas: GET /api/operaciones/cuentas
- Obtener una cuenta por ID: GET /api/operaciones/cuentas/{id}
- Hacer transferencia: POST /api/operaciones
- Crear cuenta: POST /api/operaciones/cuentas

![UML Transferencia](https://github.com/user-attachments/assets/28905c20-7af2-4a3d-8e10-114b6add5862)
