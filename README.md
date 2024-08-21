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
En esta etapa, el enfoque principal fue verificar y validar el entorno blockchain configurado en las fases anteriores, asegurando su funcionalidad y robustez. A continuación, se realizaron una serie de pruebas y se desplegó un contrato inteligente en la red privada, con el objetivo de garantizar que todos los componentes del sistema operaran de manera cohesiva y sin errores.

- Levantamiento de Contenedores: Se desplegaron los contenedores definidos en el archivo docker-compose, simulando una red blockchain completa.

- Pruebas y Transacciones: Se realizaron pruebas de transacciones dentro de la red para asegurar la estabilidad y funcionalidad del sistema.

- Despliegue de SmartContract: Se desplegó un contrato inteligente en la red configurada, validando su correcta ejecución.

### Vacante 2: Desarrollo de SmartContract e Integración con DApp 

#### 1. Introducción
En la segunda vacante, el objetivo fue desarrollar un contrato inteligente y realizar su despliegue, seguido de la integración de este con una aplicación descentralizada (DApp) desarrollada en Java. El enfoque principal fue garantizar la interacción fluida entre el contrato inteligente y la DApp.

#### 2. Requisitos y Preparación
Se identificaron los requisitos para el desarrollo del SmartContract y su posterior integración con una DApp. Esto incluyó la preparación del entorno Java y la configuración del proyecto para soportar la interacción con la blockchain.

#### 3. Desarrollo y Despliegue del SmartContract

- Explicación del Código: Se desarrolló un contrato inteligente, documentando y explicando cada parte del código para asegurar claridad en su implementación.

- Despliegue: El contrato fue desplegado en la red blockchain utilizando Ganache como proveedor de la red. Ganache permitió simular la blockchain en un entorno de desarrollo local, facilitando la realización de pruebas y la validación de las transacciones.

#### 4. Integración con DApp utilizando Java

En esta etapa, se realizó la integración del contrato inteligente con una aplicación descentralizada (DApp) desarrollada en Java. Esta integración fue fundamental para permitir la interacción entre la interfaz de usuario y la blockchain, facilitando las operaciones definidas en el contrato inteligente. A continuación, se detallan los pasos clave seguidos en este proceso:

- Preparación del proyecto Java: Se configuró un entorno de desarrollo en Java para interactuar con la red blockchain.
  
- Explicación de main.java: Se desarrolló y documentó el código principal encargado de conectar la aplicación con la blockchain y ejecutar las funciones del contrato inteligente. 

#### 5. Ejecución de la DApp

El objetivo principal de esta etapa fue verificar la funcionalidad de la DApp a través de la introducción de todos los datos (profesores, cursos, alumnos) directamente por consola. Esto permitió comprobar todas las funciones del sistema en los diferentes modos de operación: admin, alumno y profesor.

- Modo admin: En este modo, se introdujeron los datos necesarios para la gestión de la plataforma, como la creación de profesores y cursos.
  
- Modo alumno: Aquí se probaron las funcionalidades relacionadas con la inscripción y participación en los cursos.
  
- Modo profesor: Finalmente, se validaron las capacidades de los profesores para gestionar sus cursos y alumnos dentro de la DApp.
