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
4. **Pruebas de Transacciones**: Se realizan transacciones de prueba a través de los nodos para asegurar que las transacciones se procesan correctamente tanto por los nodos validadores como por los no validadores.
5. **Despliegue de Smart Contracts**: A través de la herramienta Remix, se despliega un contrato inteligente de prueba para validar la configuración de la red. Este contrato se utiliza para realizar transacciones y verificar que los nodos están funcionando correctamente.

## 2. Requisitos y Preparación

### Entorno de Desarrollo
Para llevar a cabo este proyecto, se utilizaron diversas herramientas y software que facilitan la creación y gestión de una red blockchain privada. A continuación, se detallan las herramientas utilizadas:

1. **Docker**: Docker es una plataforma de código abierto que automatiza el despliegue de aplicaciones dentro de contenedores de software, proporcionando una capa adicional de abstracción y automatización de virtualización a nivel de sistema operativo. Los contenedores son livianos y contienen todo lo necesario para ejecutar la aplicación: código, runtime, herramientas del sistema, librerías y configuraciones. Esto permite que la aplicación se ejecute de manera uniforme y consistente en cualquier entorno, facilitando el desarrollo, la prueba y la implementación de aplicaciones en múltiples plataformas.

   - **Componentes Clave de Docker**:
     - **Docker Engine**: Es el motor de Docker que crea y ejecuta los contenedores.
     - **Docker Hub**: Es un registro de contenedores públicos donde los usuarios pueden compartir y descargar imágenes de Docker.
     - **Docker Compose**: Es una herramienta para definir y ejecutar aplicaciones multi-contenedor mediante archivos YAML.

2. **OpenEthereum**: OpenEthereum, anteriormente conocido como Parity Ethereum, es un cliente de Ethereum de alta eficiencia y velocidad, desarrollado en el lenguaje de programación Rust. Está diseñado para ser utilizado tanto por desarrolladores como por empresas que buscan integrar tecnologías blockchain en sus sistemas. OpenEthereum es conocido por su rendimiento y la capacidad de ejecutar nodos de forma rápida y eficiente.

   - **Características de OpenEthereum**:
     - **Alto Rendimiento**: Procesa transacciones a alta velocidad.
     - **Modularidad**: Su arquitectura modular permite agregar y modificar funcionalidades con facilidad.
     - **Seguridad**: Incluye características avanzadas de seguridad y control de acceso.
     - **Compatibilidad**: Es compatible con múltiples redes de Ethereum, incluidas mainnet, testnets y redes privadas.

3. **Solidity**: Lenguaje de programación para escribir contratos inteligentes en la blockchain de Ethereum. Se utilizó para desarrollar los contratos inteligentes de prueba.

   **Compilación de Solidity usando solc**:
   Para compilar contratos inteligentes escritos en Solidity, se utiliza el compilador `solc`, que convierte el código de Solidity en bytecode y en formato ABI necesarios para su futurp despliegue.
   
5. **Remix**: Entorno de desarrollo integrado (IDE) para escribir, compilar y desplegar contratos inteligentes escritos en Solidity. Remix se usó para compilar y desplegar el contrato inteligente en la red Ethereum privada.

6. **Node.js y NPM**: Se utilizaron para ejecutar scripts de automatización y gestionar dependencias adicionales necesarias para la interacción con la blockchain.

## 3. Configuración y Desarrollo

### Script GenerateAll.sh
Para configurar todo lo necesario para poner a funcionar la red blockchain privada con OpenEthereum, he generado un script de shell llamado [Generate_all.sh](Generate_all.sh) para crear las cuentas, crear el bloque genesis, el docker-compose y los archivos de configuracion de cada nodo.

#### Generar direcotrios y cuentas.
La primera funcion que hay en el shell, se llama generate_keys, la que al ejecutarse, se crean los direcotrios inciales, uno por cada nodo y dentro de cada nodo la carpeta config y keys. Despues de generar los direcotrios, se generan unos archivos llamados password.pwd, uno por cada nodo y contendra la pass para desbloquear las cuentas que se crearan. Lo siguiente que hace la funcion es generar una cuenta por cada nodo utilizando las password geeradas anteriormente y por ultimo muestra por pantalla la informacion de la cuentas creadas.

```sh
generate_keys() {
#!/bin/bash

#Generar directorios
mkdir -p "node_validator/config"
mkdir -p "node_validator/keys/Proyecto_final"

mkdir -p "node_non_validator_1/config"
mkdir -p "node_non_validator_1/keys/Proyecto_final"

mkdir -p "node_non_validator_2/config"
mkdir -p "node_non_validator_2/keys/Proyecto_final"

mkdir -p "node_rpc/config"
mkdir -p "node_rpc/keys/Proyecto_final"

#Generar archivos password.pwd
cat > ./node_validator/keys/Proyecto_final/password.pwd <<EOF
validator
EOF

cat > ./node_non_validator_1/keys/Proyecto_final/password.pwd <<EOF
nonvalidator1
EOF

cat > ./node_non_validator_2/keys/Proyecto_final/password.pwd <<EOF
nonvalidator2
EOF

cat > ./node_rpc/keys/Proyecto_final/password.pwd <<EOF
rpc
EOF


# Generar cuentas y guardar las claves en los directorios respectivos
account_node_validator=$(geth account new --password ./node_validator/keys/Proyecto_final/password.pwd --keystore ./node_validator/keys/Proyecto_final)
account_node_non_validator_1=$(geth account new --password ./node_non_validator_1/keys/Proyecto_final/password.pwd --keystore ./node_non_validator_1/keys/Proyecto_final)
account_node_non_validator_2=$(geth account new --password ./node_non_validator_2/keys/Proyecto_final/password.pwd --keystore ./node_non_validator_2/keys/Proyecto_final)
account_node_rpc=$(geth account new --password ./node_rpc/keys/Proyecto_final/password.pwd --keystore ./node_rpc/keys/Proyecto_final)

echo "Cuentas generadas y guardadas en los directorios correspondientes."

#Mostrar informacion por consola de las cuentas generadas.
echo "node_validator: $account_node_validator"
echo "node_non_validator_1: $account_node_non_validator_1"
echo "node_non_validator_2: $account_node_non_validator_2"
echo "node_rpc: $account_node_rpc"
```
hola

