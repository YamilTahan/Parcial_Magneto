# API REST para la Detección de Mutantes

## Descripción
API REST diseñada para determinar si un individuo es mutante a partir de una secuencia de ADN.

## Nivel 1: API Principal

### 1. Implementación de la API REST
# Esta API está orientada a identificar mutantes utilizando secuencias de ADN.

### 2. Alojamiento de la API
# La API está disponible en Render
# [Acceder en Render](https://parcial-magneto-51xr.onrender.com/swagger-ui/index.html)

### 3. Endpoint del Servicio
echo "POST http://localhost:8080/mutant"

### 4. Formato de Solicitud
cat << EOF
{
  "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
EOF

### 5. Posibles Respuestas
echo "200 OK: ADN corresponde a un mutante."
echo "403 Forbidden: ADN corresponde a un humano."

---

## Nivel 2: Integración de Base de Datos y Estadísticas

### 1. Integración de H2
# Base de datos H2 para almacenar los ADN validados (se permite un único registro por ADN).

### 2. Endpoint de Estadísticas
echo "GET http://localhost:8080/stats"

### 3. Formato de Respuesta para Estadísticas
cat << EOF
{
  "count_mutant_dna": 4,
  "count_human_dna": 1,
  "ratio": 4
}
EOF

### 4. Requisitos de Escalabilidad
# Capacidad para manejar entre 100 y 1 millón de solicitudes por segundo.

### 5. Pruebas Automatizadas
# Se alcanzó una cobertura de código superior al 80%.

---

## Resultados de Pruebas

### 1. Resultados de JMeter
# Los resultados de las pruebas de carga se encuentran en el PDF correspondiente.

### 2. Resultados de JaCoCo
# Los resultados de las pruebas automatizadas y la cobertura de código están disponibles en el PDF adjunto.
