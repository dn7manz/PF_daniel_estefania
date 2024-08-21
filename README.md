# Proyecto final | Desarrollo Blockchain: herramientas y tecnologia | Daniel Estefania | Tokioschool

## Documentacion general

### Vacante 1: Configuración de una Red Privada Blockchain. 

#### 1. Introducción
En la primera vacante, el objetivo principal fue configurar una red privada blockchain utilizando OpenEthereum. Este proceso incluyó la preparación del entorno de desarrollo, la configuración de las herramientas necesarias y la ejecución de pruebas para garantizar la funcionalidad del sistema.

#### 2. Requisitos y Preparación
Se realizó un análisis inicial para identificar los requisitos técnicos necesarios. Posteriormente, se preparó el entorno de desarrollo utilizando Docker para gestionar los contenedores, y se generaron los directorios y cuentas esenciales para la ejecución del proyecto.

#### 3. Configuración y Desarrollo
Se desarrolló un script automatizado que abarcó todos los pasos clave para la configuración de la red. Los elementos principales del script incluyen:

- Generación de Directorios y Cuentas: Creación de las estructuras de directorios y las cuentas necesarias para el funcionamiento de la red blockchain.

- Generación de Docker-compose: Configuración del archivo docker-compose para orquestar los diferentes contenedores que forman la red.

- Generación de Config.toml: Generación y personalización del archivo config.toml que define los parámetros clave para la red blockchain.

- Ejecución del Script: Una vez configurado el script, se ejecutaron todos estos pasos de manera secuencial para iniciar la red y validar su configuración.

#### 4. Despliegue y Pruebas

- Levantamiento de Contenedores: Se desplegaron los contenedores definidos en el archivo docker-compose, simulando una red blockchain completa.

- Pruebas y Transacciones: Se realizaron pruebas de transacciones dentro de la red para asegurar la estabilidad y funcionalidad del sistema.

- Despliegue de SmartContract: Se desplegó un contrato inteligente en la red configurada, validando su correcta ejecución.
