# Proyecto Final: Configuración de Red Blockchain Privada

## 1. Introducción

### Contexto y Objetivo
El objetivo principal de este proyecto es demostrar la capacidad técnica para gestionar una red blockchain privada utilizando OpenEthereum en un entorno Dockerizado. Este proyecto se presenta como parte del proceso de candidatura a una posición en la compañía Blockchain TS, que requiere habilidades en la creación y gestión de redes blockchain, así como en el despliegue y uso de contratos inteligentes.

El propósito del proyecto incluye:
- Generar pares de claves criptográficas automáticamente.
- Configurar y desplegar una red privada de Ethereum utilizando OpenEthereum con Docker.
- Realizar transacciones de prueba a través de nodos validadores y no validadores para verificar la configuración de la red.

### Descripción del Proyecto
El proyecto implica varios componentes clave:

1. **Generación de Pares de Claves**: Se crea un script que genera automáticamente pares de claves criptográficas para los nodos de la red. Estas claves se utilizan para identificar y autenticar a los nodos dentro de la red privada de Ethereum.

2. **Configuración de la Red Dockerizada**: Utilizando Docker Compose, se configura una red privada de Ethereum con cuatro nodos:
   - Un nodo validador.
   - Dos nodos no validadores.
   - Un nodo RPC para interactuar con la red.

3. **Inicialización del Bloque Génesis**: Se crea un archivo de configuración génesis que define el estado inicial de la blockchain privada, incluyendo la distribución de ether y las reglas de consenso.

4. **Despliegue de Smart Contracts**: A través de la herramienta Remix, se despliega un contrato inteligente de prueba para validar la configuración de la red. Este contrato se utiliza para realizar transacciones y verificar que los nodos están funcionando correctamente.

5. **Pruebas de Transacciones**: Se realizan transacciones de prueba a través de los nodos para asegurar que las transacciones se procesan correctamente tanto por los nodos validadores como por los no validadores.

Este proyecto no solo prueba la capacidad técnica para manejar una red blockchain privada, sino que también demuestra la habilidad para documentar y presentar el trabajo de manera clara y profesional.

