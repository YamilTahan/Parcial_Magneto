## Nivel 2: API REST para Detección de Mutantes

1. **Crear la API REST**:
   - La API está diseñada para detectar si un humano es mutante mediante una secuencia de ADN.

2. **Hostear la API**:
   - La API se encuentra hospedada en Render, un servicio de cloud computing gratuito.
   - [Deploy en Render](https://parcial-magneto-9xi7.onrender.com/swagger-ui/index.html)


3. **Endpoint del Servicio**:
   - El servicio para verificar si un humano es mutante se puede acceder en el siguiente endpoint:
     ```
     POST http://localhost:8080/mutant
     ```

4. **Formato de la Solicitud**:
   - La solicitud debe enviarse en formato JSON con el siguiente formato:
     ```json
     {
       "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
     }
     ```

5. **Respuestas**:
   - Si el ADN es de un mutante, se devuelve un código HTTP **200 OK**.
   - Si el ADN pertenece a un humano, se devuelve un código HTTP **403 Forbidden**.

---

## Nivel 3: Integración de Base de Datos y Estadísticas

1. **Integración de H2**:
   - Se ha anexado una base de datos H2 para almacenar los ADN verificados.
   - Solo se permite un registro por ADN.

2. **Endpoint de Estadísticas**:
   - Un servicio adicional para exponer estadísticas de verificaciones de ADN:
     ```
     GET http://localhost:8080/stats
     ```

3. **Formato de Respuesta de Estadísticas**:
   - La respuesta devuelve un JSON con el siguiente formato:
     ```json
     {
       "count_mutant_dna": 4,
       "count_human_dna": 1,
       "ratio": 4
     }
     ```

4. **Requisitos de Escalabilidad**:
   - La API está diseñada para manejar fluctuaciones agresivas de tráfico, con un rango estimado de entre 100 y 1 millón de peticiones por segundo.

5. **Pruebas Automáticas**:
   - Se implementaron pruebas automáticas con una cobertura de código superior al **80%**.

---

## Resultados de Pruebas

- **Resultados de JMeter**:
  - Los resultados obtenidos de las pruebas de carga con JMeter se encuentran en el PDF

- **Resultados de JaCoCo**:
  - Los resultados de las pruebas automáticas y la cobertura de código con JaCoCo se encuentran en el PDF

