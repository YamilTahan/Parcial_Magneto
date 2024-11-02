## API REST para la Identificación de Mutantes

1. **Implementación de la API REST**:
   Esta API está diseñada para determinar si un individuo humano tiene características mutantes basándose en su secuencia de ADN.

2. **Despliegue de la API**:
   [Visita la API en Render](https://parcial-magneto-51xr.onrender.com/swagger-ui/index.html)

3. **Acceso al Servicio**:
   Puedes verificar si un ser humano es mutante a través del siguiente endpoint:
     ```
     POST http://localhost:8080/mutant
     ```

4. **Formato de la Solicitud**:
   La solicitud debe ser enviada en formato JSON con la siguiente estructura:
     ```json
     {
     "dna": ["AGTCGA", "CAGTGC", "GTATAT", "AGGAAA", "TCACCA", "TACACT"]
     }
     ```

5. **Posibles Respuestas**:
   Si el ADN corresponde a un mutante, recibirás un código HTTP **200 OK**.
   Si el ADN es humano, se devolverá un código HTTP **403 Forbidden**.

---

## Conexión con Base de Datos y Estadísticas

1. **Base de Datos H2**:
   Se ha integrado una base de datos H2 para almacenar los ADN validados, solo pudiendo registrar cada uno una vez.

2. **Endpoint de Estadísticas**:
   Hay un servicio adicional que proporciona estadísticas sobre las verificaciones de ADN:
     ```
     GET http://localhost:8080/stats
     ```

3. **Formato de Respuesta para Estadísticas**:
   La respuesta será un JSON que sigue esta estructura:
     ```json
     {
       "count_mutant_dna": 4,
       "count_human_dna": 1,
       "ratio": 4
     }
     ```

4. **Escalabilidad**:
   La API ha sido diseñada para manejar una amplia variedad de tráfico, con un rango de solicitudes que va de 100 a 1 millón por segundo.

5. **Pruebas Automatizadas**:
   Se han llevado a cabo pruebas automáticas que aseguran una cobertura de código superior al **80%**.

---

## Resultados de Pruebas

**Resultados de JMeter**:
  Los datos obtenidos de las pruebas de carga realizadas con JMeter están disponibles en el PDF.

  **Resultados de JaCoCo**:
  Los resultados de las pruebas automatizadas y la cobertura de código generada por JaCoCo también se pueden encontrar en el PDF.
