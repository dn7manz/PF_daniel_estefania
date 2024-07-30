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
La primera funcion que hay en el shell, se llama generate_keys, la que al ejecutarse, se crean los direcotrios inciales, uno por cada nodo y dentro de cada nodo la carpeta config y keys. Despues de generar los direcotrios, se generan unos archivos llamados password.pwd, uno por cada nodo y contendra la pass para desbloquear las cuentas que se crearan. Lo siguiente que hace la funcion es generar una cuenta por cada nodo utilizando las password geeradas anteriormente y por ultimo muestra por pantalla la informacion de la cuentas creadas. A continuacion el codigo de la funcion:

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
#### Generar archivo Genesis
La siguiente funcion es para generar el archivo Genesis, el archivo genesis es un archivo de configuración fundamental en las redes de blockchain. Define las reglas y parámetros iniciales para una nueva red de blockchain. Esencialmente, establece el estado inicial de la red al momento de su creación. A continuacion se analiza cada parte del codigo:

```sh
generate_genesis(){
mkdir -p genesis/
cat > ./genesis/genesis.json <<EOF
{
  "name": "Proyecto_final",
  "engine": {
    "authorityRound": {
      "params": {
        "stepDuration": "5",
        "validators": {
          "list": [
            "0x0000000000000000000000000000"
          ]
        }
      }
    }
  },
```
Este fragmento del archivo genesis configura una red blockchain personalizada con un nombre específico y establece el algoritmo de consenso como Authority Round. Configura la duración de los bloques y define los validadores autorizados que pueden producir bloques en la red. Hay multiples opciones en estas etiquetas que puedas establecer el funciomaniento de la red, en la parte de [Engine] en vez de authorityRound, podria ser instabul o clique, que son direfentes consensos. en parte de [params] podriamos establecer los validadores maximos o minimos que puede tener una red con "maxValidators" o "minValidators", tambien para establecer cada cuanto tiempo genera bloques, en mi caso es 5, definido en "stepDuration". Una vez que obtengamos la direccion de la cuenta del nodo validador habra que volver a esta parte y estabelcer la direccion en la lista para que pueda firmar y hacer consenso.

```sh
"genesis": {
    "seal": {
      "authorityRound": {
        "step": "0x0",
        "signature": "0x0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
      }
    },
    "difficulty": "0x20000",
    "gasLimit": "0x165A0BC00"
  },
  "accounts": {
    "0x00000000001": {
      "balance": "100000000000000000000000000000"
    },
    "0x00000000002": {
      "balance": "100000000000000000000000000000"
    },
    "0x00000000003": {
      "balance": "100000000000000000000000000000"
    },
    "0x00000000004": {
      "balance": "100000000000000000000000000000"  
    } 
  }
}
```
Este fragmento del archivo genesis define la configuración del consenso, los parámetros iniciales para el minado (o límites del bloque), y establece saldos iniciales para cuentas específicas. Cuando se ejecute esta funcion, deberemos de volver para estabelecer las direcciones de las cuentas de cada nodo.

#### Generar Docker-compose
La sieguiente funcion se encarga simplemente de crear el archivo docker-compose, neecsario para iniciar y configurar cada nodo. A continunacion se muestra el codigo del archivo con comentarios que explican cada linea.

```yml
# Generar docker-compose.yml
generate_docker_compose() {
cat > docker-compose.yml <<EOF
version: '3'  # Versión del formato de archivo Docker Compose

services:
  node_validator:  # Servicio para un nodo validador
    user: root  # Ejecutar el contenedor como el usuario root
    image: openethereum/openethereum:latest  # Imagen Docker a utilizar, la más reciente de OpenEthereum
    volumes:
      - node_validator:/root/.local/share/openethereum  # Monta un volumen llamado 'node_validator' para persistencia de datos
      - ./genesis:/root/.local/share/openethereum/genesis  # Monta el directorio local 'genesis' en la ruta del contenedor
      - ./node_validator/keys:/root/.local/share/openethereum/keys  # Monta el directorio local 'keys' con las claves
      - ./node_validator/keys/Proyecto_final/password.pwd:/root/.local/share/openethereum/keys/Proyecto_final/password.pwd:ro  # Monta el archivo de contraseña con permisos de solo lectura
      - ./node_validator/config:/root/.local/share/openethereum/config  # Monta el directorio local 'config' con la configuración del nodo
    ports:
      - "30300:30300"  # Mapea el puerto 30300 del contenedor al puerto 30300 del host, para comunicación entre nodos
      - "8548:8545"  # Mapea el puerto 8545 del contenedor al puerto 8548 del host, para la interfaz JSON-RPC
    environment:
      - NODE_TYPE=validator  # Variable de entorno que define este nodo como un validador
    networks:
      ethereum_net:  # Conecta el contenedor a la red 'ethereum_net'
        ipv4_address: 172.20.0.2  # Asigna una dirección IP estática a este contenedor
    command:
      --config /root/.local/share/openethereum/config/config.toml  # Ruta al archivo de configuración del nodo
      --chain /root/.local/share/openethereum/genesis/genesis.json  # Ruta al archivo de génesis de la cadena
      --engine-signer 0x00000000000000001  # Dirección del nodo validador para la firma de bloques

  node_non_validator_1:  # Servicio para un nodo no validador (nodo de red)
    user: root  # Ejecutar el contenedor como el usuario root
    image: openethereum/openethereum:latest  # Imagen Docker a utilizar
    volumes:
      - node_non_validator_1:/root/.local/share/openethereum  # Monta un volumen llamado 'node_non_validator_1'
      - ./genesis:/root/.local/share/openethereum/genesis  # Monta el directorio local 'genesis'
      - ./node_non_validator_1/keys:/root/.local/share/openethereum/keys  # Monta el directorio local 'keys'
      - ./node_non_validator_1/keys/Proyecto_final/password.pwd:/root/.local/share/openethereum/keys/Proyecto_final/password.pwd:ro  # Monta el archivo de contraseña con permisos de solo lectura
      - ./node_non_validator_1/config:/root/.local/share/openethereum/config  # Monta el directorio local 'config'
    ports:
      - "30301:30300"  # Mapea el puerto 30300 del contenedor al puerto 30301 del host
      - "8547:8545"  # Mapea el puerto 8545 del contenedor al puerto 8547 del host
    environment:
      - NODE_TYPE=non_validator  # Variable de entorno que define este nodo como no validador
    networks:
      ethereum_net:  # Conecta el contenedor a la red 'ethereum_net'
        ipv4_address: 172.20.0.3  # Asigna una dirección IP estática a este contenedor
    command:
      --config /root/.local/share/openethereum/config/config.toml  # Ruta al archivo de configuración del nodo
      --chain /root/.local/share/openethereum/genesis/genesis.json  # Ruta al archivo de génesis de la cadena

  node_non_validator_2:  # Otro nodo no validador
    user: root  # Ejecutar el contenedor como el usuario root
    image: openethereum/openethereum:latest  # Imagen Docker a utilizar
    volumes:
      - node_non_validator_2:/root/.local/share/openethereum  # Monta un volumen llamado 'node_non_validator_2'
      - ./genesis:/root/.local/share/openethereum/genesis  # Monta el directorio local 'genesis'
      - ./node_non_validator_2/keys:/root/.local/share/openethereum/keys  # Monta el directorio local 'keys'
      - ./node_non_validator_2/keys/Proyecto_final/password.pwd:/root/.local/share/openethereum/keys/Proyecto_final/password.pwd:ro  # Monta el archivo de contraseña con permisos de solo lectura
      - ./node_non_validator_2/config:/root/.local/share/openethereum/config  # Monta el directorio local 'config'
    ports:
      - "30302:30300"  # Mapea el puerto 30300 del contenedor al puerto 30302 del host
      - "8546:8545"  # Mapea el puerto 8545 del contenedor al puerto 8546 del host
    environment:
      - NODE_TYPE=non_validator  # Variable de entorno que define este nodo como no validador
    networks:
      ethereum_net:  # Conecta el contenedor a la red 'ethereum_net'
        ipv4_address: 172.20.0.4  # Asigna una dirección IP estática a este contenedor
    command:
      --config /root/.local/share/openethereum/config/config.toml  # Ruta al archivo de configuración del nodo
      --chain /root/.local/share/openethereum/genesis/genesis.json  # Ruta al archivo de génesis de la cadena

  node_rpc:  # Servicio para un nodo RPC
    user: root  # Ejecutar el contenedor como el usuario root
    image: openethereum/openethereum:latest  # Imagen Docker a utilizar
    volumes:
      - node_rpc:/root/.local/share/openethereum  # Monta un volumen llamado 'node_rpc'
      - ./genesis:/root/.local/share/openethereum/genesis  # Monta el directorio local 'genesis'
      - ./node_rpc/keys:/root/.local/share/openethereum/keys  # Monta el directorio local 'keys'
      - ./node_rpc/keys/Proyecto_final/password.pwd:/root/.local/share/openethereum/keys/Proyecto_final/password.pwd:ro  # Monta el archivo de contraseña con permisos de solo lectura
      - ./node_rpc/config:/root/.local/share/openethereum/config  # Monta el directorio local 'config'
    ports:
      - "8545:8545"  # Mapea el puerto 8545 del contenedor al puerto 8545 del host, para la interfaz JSON-RPC
    environment:
      - NODE_TYPE=rpc  # Variable de entorno que define este nodo como RPC
    networks:
      ethereum_net:  # Conecta el contenedor a la red 'ethereum_net'
        ipv4_address: 172.20.0.5  # Asigna una dirección IP estática a este contenedor
    command:
      --config /root/.local/share/openethereum/config/config.toml  # Ruta al archivo de configuración del nodo
      --chain /root/.local/share/openethereum/genesis/genesis.json  # Ruta al archivo de génesis de la cadena

volumes:
  node_validator:  # Define un volumen para persistir datos del nodo validador
  node_non_validator_1:  # Define un volumen para persistir datos del primer nodo no validador
  node_non_validator_2:  # Define un volumen para persistir datos del segundo nodo no validador
  node_rpc:  # Define un volumen para persistir datos del nodo RPC

networks:
  ethereum_net:  # Define una red personalizada para los contenedores
    driver: bridge  # Utiliza el controlador de red 'bridge' para la red personalizada
    ipam:
      config:
      - subnet: 172.20.0.0/16  # Define el rango de subred para la red personalizada
EOF
}
```
Este archivo docker-compose.yml configura cuatro servicios diferentes para una red Ethereum privada usando OpenEthereum:
   - node_validator: Nodo que actúa como validador en la red.
   - node_non_validator_1: Nodo que no actúa como validador.
   - node_non_validator_2: Otro nodo que no actúa como validador.
   - node_rpc: Nodo que proporciona acceso RPC a la red.

Cada nodo tiene su propio conjunto de volúmenes para almacenar datos y claves, y está configurado para comunicarse a través de puertos específicos.

La red ethereum_net asegura que todos los nodos pueden comunicarse entre sí a través de una red interna.

**Resumen de las diferencias**

- Roles y Funciones:
   - Nodo Validador: Responsable de validar y firmar bloques.
   - Nodos No Validadores: Participan en la red pero no validan bloques.
   - Nodo RPC: Dedicado a servir solicitudes RPC.
- Puertos:
   - Los nodos validadores y no validadores usan puertos P2P (30300, 30301, 30302).
   - Los nodos tienen diferentes puertos RPC (8545, 8546, 8547, 8548).
- Volúmenes:
   - Cada nodo tiene su propio volumen de datos para almacenar datos específicos del nodo.
   - Los nodos cargan archivos de configuración específicos a su rol y propósito.
- Comandos:
   - Los nodos validadores incluyen la opción --engine-signer para firmar bloques.
   - Los nodos no validadores y el nodo RPC no incluyen esta opción


#### Generar los config.toml

El archivo config.toml es un archivo de configuración utilizado por OpenEthereum (anteriormente conocido como Parity) para definir cómo debe comportarse un nodo en la red Ethereum. Este archivo está escrito en formato TOML (Tom's Obvious, Minimal Language), que es un formato de configuración simple y legible para humanos. A continuacion muestro los ficheros de configuracion de cada nodo.

**node_validator**

```sh
generate_config_node_validator() {
cat > ./node_validator/config/config.toml <<EOF
[network]
port = 30303
discovery = true
nat = "extip:172.20.0.2"

[rpc]
interface = "0.0.0.0"
port = 8545
cors = ["*"]
apis = ["web3", "eth", "personal", "net"]

[account]
password = ["/root/.local/share/openethereum/keys/password.pwd"]

[mining]
engine_signer = "<address_of_node1>"
reseal_on_txs = "all"
min_gas_price = 0
EOF
}
```
la etiqueta network Configura los parámetros de red del nodo.
   - port: 30303 - El puerto en el que el nodo escuchará las conexiones entrantes de otros nodos de la red. Este puerto es utilizado para la comunicación P2P entre nodos.
   - discovery: true - Activa el descubrimiento de pares (peers). Esto permite que el nodo descubra otros nodos en la red y establezca conexiones con ellos.
   - nat: "extip:172.20.0.2" - Configura la traducción de direcciones de red (NAT). En este caso, se está indicando la dirección IP externa del nodo para el descubrimiento y la comunicación.
   
la etiqueta rpc: Configura el servidor RPC (Remote Procedure Call) del nodo.
   - interface: "0.0.0.0" - Especifica en qué interfaces de red el RPC escuchará solicitudes. 0.0.0.0 significa que escuchará en todas las interfaces disponibles.
   - port: 8545 - El puerto en el que el RPC estará disponible. Este puerto es utilizado para las solicitudes RPC.
   - cors: "*" - Permite solicitudes de cualquier origen, lo que es útil para pruebas, pero puede ser inseguro para entornos de producción.
   - apis: "web3", "eth", "personal", "net" - Habilita los módulos de API especificados:
      - web3: API para interactuar con la capa Web3.
      - eth: API para interactuar con la capa Ethereum.
      - personal: API para interactuar con la capa personal (manejo de cuentas, envío de transacciones, etc.).
      - net: API para interactuar con la capa de red (información de la red, conexiones, etc.).

la etiqueta account: Configura los parámetros relacionados con la cuenta del nodo.
   - password: "/root/.local/share/openethereum/keys/Proyecto_final/password.pwd" - Ruta al archivo que contiene la contraseña de la cuenta del nodo. Este archivo es utilizado para desbloquear la cuenta al iniciar el nodo.

la etiqueta mining: Configura los parámetros relacionados con la minería en el nodo.
   - engine_signer: "<address_of_node1>" - La dirección de la cuenta que firmará los bloques. En el caso de un nodo validador, esta es la cuenta que se utilizará para el consenso, Es importante introducir la dirrecion del nodo validador en cuanto se creen las cuentas.
   - reseal_on_txs: "all" - Indica que el nodo debe volver a sellar (minar) un bloque cuando se reciban transacciones. Esto asegura que los bloques se mantengan actualizados con las transacciones más recientes.
   - min_gas_price: 0 - El precio mínimo del gas que el nodo aceptará para las transacciones. Establecerlo en 0 puede hacer que el nodo acepte transacciones con cualquier precio de gas, lo que puede ser útil para pruebas.

**node_non_validator_1 y node_non_validator_2**

```sh
generate_config_node_non_validator_1() {
cat > ./node_non_validator_1/config/config.toml <<EOF
[network]
port = 30303
discovery = true
nat = "extip:172.20.0.3"

[rpc]
interface = "0.0.0.0"
port = 8545
cors = ["*"]
apis = ["web3", "eth", "personal", "net"]

[account]
password = ["/root/.local/share/openethereum/keys/Proyecto_final/password.pwd"]

EOF
}
```
en estos nodos solo hace falta de especificar la etiqueta de network rpc y account, como no son validadores no haria falta la etiqueta mining

**node_rpc**

```sh
generate_config_node_rpc() {
cat > ./node_rpc/config/config.toml <<EOF
[network]
port = 30303
discovery = true
nat = "extip:172.20.0.5"

[rpc]
interface = "0.0.0.0"
port = 8545
cors = ["*"]
apis = ["web3", "eth", "personal", "net"]
server_threads = 4

[account]
password = ["/root/.local/share/openethereum/keys/password.pwd"]

[ipc]
disable = true

[websockets]
disable = true

EOF
}
```
la etiqueta ipc: Configura la comunicación IPC (Inter-Process Communication) del nodo.
   - disable: true - Desactiva la comunicación IPC. IPC permite que el nodo se comunique con otros procesos en la misma máquina a través de un socket. Desactivar IPC puede ser útil si no se necesita esta comunicación o si se prefiere utilizar otros métodos de comunicación.

la etiqueta websockets: Configura la comunicación a través de WebSockets.
   - disable: true - Desactiva el soporte para WebSockets. WebSockets permiten una comunicación bidireccional en tiempo real entre el nodo y los clientes. Desactivar WebSockets puede reducir la carga en el nodo si no se requiere esta funcionalidad.

**Resumen de las diferencias**

Puertos de Red ([network]): Cada nodo tiene un puerto diferente para las conexiones P2P:
   - node_validator: 30303
   - node_non_validator_1: 30301
   - node_non_validator_2: 30302
   - node_rpc: 30303 (coincide con el puerto del validador, pero no afecta el RPC)

Puertos RPC ([rpc]): Cada nodo utiliza un puerto diferente para el RPC:
   - node_validator: 8548
   - node_non_validator_1: 8547
   - node_non_validator_2: 8546
   - node_rpc: 8545
     
Minado ([mining]):
   - Solo el node_validator tiene configuración de minería ([mining]), ya que actúa como el validador que firma y mina bloques.
     
IPC y WebSockets ([ipc] y [websockets]):
   - Todos los nodos desactivan IPC y WebSockets excepto el node_rpc, que no tiene configuraciones especiales para estos dos parámetros.
     
Hilos del Servidor RPC (server_threads):
   - Solo los nodos que no son validadores (node_non_validator_1, node_non_validator_2, y node_rpc) configuran el número de hilos para el servidor RPC.

Este conjunto de configuraciones permite que cada nodo desempeñe su papel específico en la red Ethereum, ya sea como validador, nodo no validador, o nodo RPC.


#### Ejecucion del Script

Una vez se ha revisado las funciones del script, al final del archivo se llaman a las funciones como se puede ver a continunacion:

```sh
# Ejecutar funciones
generate_keys
generate_genesis
generate_config_node_validator
generate_config_node_non_validator_1
generate_config_node_non_validator_2
generate_config_node_rpc
generate_docker_compose

echo "Configuración completada. Se ha generado docker-compose.yml, los pares de claves y las configuraciones"

```
desde la consola, en la ubicacion raiz del proyecto, ejecuto el shell de la siguiente manera, dando resultado lo siguiente:

```bash
./Generate_all.sh
```
![image](https://github.com/user-attachments/assets/1256a5fb-20c4-4ed9-b2ca-e52304782e80)

![image](https://github.com/user-attachments/assets/86c8741c-15f1-4949-83b5-a5cf5b259565)

Una vez ejecutado el script sh por la pantalla de la terminal, apareceran las direcciones de cada cuenta, es muy importante volver a los archivos que necesitan estas direcciones para que posterioremente en el despliegue todo funcione bien.

[node_validator/config/config.toml](node_validator/config/config.toml)
```toml
[mining]
engine_signer = "0x66942BfBa08A29372eabeB4E992cB7Bdaff0c773"
reseal_on_txs = "all"
min_gas_price = 0
```

[docker-compose.yml](docker-compose.yml)
```yaml
 command:
      --config /root/.local/share/openethereum/config/config.toml
      --chain /root/.local/share/openethereum/genesis/genesis.json
      --engine-signer 0x66942BfBa08A29372eabeB4E992cB7Bdaff0c773
```

[genesis/genesis.json](genesis/genesis.json)
```json
{
  "name": "Proyecto_final",
  "engine": {
    "authorityRound": {
      "params": {
        "stepDuration": "5",
        "validators": {
          "list": [
            "0x66942BfBa08A29372eabeB4E992cB7Bdaff0c773"
          ]
        }
      }
    }
  },
  "params": {
    "maximumExtraDataSize": "0x20",
    "minGasLimit": "0x3938700",
    "networkID": "0x2323",
    "gasLimitBoundDivisor": "0x400"
  },
  "genesis": {
    "seal": {
      "authorityRound": {
        "step": "0x0",
        "signature": "0x0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
      }
    },
    "difficulty": "0x20000",
    "gasLimit": "0x165A0BC00"
  },
  "accounts": {
    "0x66942BfBa08A29372eabeB4E992cB7Bdaff0c773": {
      "balance": "100000000000000000000000000000"
    },
    "0x30548f20CD7d19e646a4f7f0bcbB6Fbee3DA8892": {
      "balance": "100000000000000000000000000000"
    },
    "0x75fd84F9f2f4c2958cDB9373375d73A9e4148eb6": {
      "balance": "100000000000000000000000000000"
    },
    "0xFabe57f285B456385270F5ADc8E4fEdc33dA0Bb5": {
      "balance": "100000000000000000000000000000"  
    } 
  }
}
```

## 4. Despliegue y pruebas.

### Levantamiento de contenedores

Una vez que todo ha sido creado y configurado gracias al script [Generate_All.sh](Generate_All.sh), es posible levantar los dockers y ponerlos en funcionamiento a traves del fichero [docker-compose.yml](docker-compose.yml). Para esto, a traves de la terminal, ejecutarermos el siguiente comando:

```sh
docker-compose up -d
```
Con el siguiente comando levantamos todos los nodos necesarios para que funcione la red y con el parametro "-d" lo ejecutamos en modo demonio.

![image](https://github.com/user-attachments/assets/40100394-4a66-4643-b2be-be64cd91b102)

A continuacion a traves de la aplicacion de Escritorio de Docker, revisaremos los logs de cada nodo para comprobar que los nodos se esten ejecutando, sincronizados y generando bloques.

**node_validator**

![image](https://github.com/user-attachments/assets/801745db-b45c-406a-8f62-4200c227e2b0)

**node_non_validator_1**

![image](https://github.com/user-attachments/assets/55b57a9c-57f3-440c-b736-37d49167c6fc)

**node_non_validator_2**

![image](https://github.com/user-attachments/assets/79314026-1acd-418d-9f9d-c2c0f6f96cd2)

**node_rpc**

![image](https://github.com/user-attachments/assets/7ad8a748-2886-4473-85f6-77575c20acea)

Aqui se puede observar que los nodos se estan ejecutando correctamente, podemos ver que el engine es "AuthorityRound", en la siguiente linea, se puede observar el public enode,  tambien se puede ver que tienen emparejado 3 peers lo que indica que los otros nodos tambien se esta ejcutando bien y el ultimo indicativo de que todo va correctamente es que estan generando bloques.

### Puebras y transacciones

Ahora que los nodos ya estan levantados sin errores, sincronizados, generando bloques, es momento de interactuar con la red para verificar su funcionamiento de la siguiente manera:

#### Comandos curl método POST

   - ¿Qué es curl?
      - curl es una herramienta de línea de comandos utilizada para transferir datos desde o hacia un servidor, utilizando uno de los muchos protocolos soportados, como HTTP, HTTPS, FTP, y más. Es muy útil para realizar solicitudes HTTP desde la terminal y probar APIs.

   - Método POST en HTTP
      - El método POST en HTTP se utiliza para enviar datos al servidor para crear o actualizar un recurso. En el contexto de blockchain, se puede utilizar para enviar transacciones, interactuar con contratos inteligentes, o consultar el estado de la red.

#### Obtener cuentas.

Para obtener las cuentas de los nodos en una red Ethereum privada utilizando curl, se utiliza el método JSON-RPC eth_accounts. Este método devuelve una lista de todas las cuentas gestionadas por el nodo al que se envía la solicitud. A continuacion se muestra un ejemplo de varios comandos a los distintos nodos de la red.

![image](https://github.com/user-attachments/assets/eee6ab68-0ee7-4567-a8cf-cfd45c110ae5)

**Explicación del Comando**
- curl -X POST: Indica que estamos realizando una solicitud POST.
- --data '{"jsonrpc":"2.0","method":"eth_accounts","params":[],"id":1}': Este es el cuerpo de la solicitud JSON-RPC:
   - "jsonrpc": "2.0": Especifica la versión del protocolo JSON-RPC.
   - "method": "eth_accounts": Especifica el método que queremos invocar, en este caso, eth_accounts.
   - "params": []: Una lista vacía de parámetros, ya que eth_accounts no requiere parámetros.
   - "id": 1: Un identificador de la solicitud, que puede ser cualquier número. Es útil para hacer un seguimiento de las solicitudes cuando se realizan múltiples solicitudes a la vez.
- localhost:854?: La URL del nodo Ethereum al que se envía la solicitud. Aqui hay que reemplazar localhost y el puerto del nodo al que se quiere hacer la consulta.

Como resultado del comando obtenemos las direcciones que estan alojadas en los nodos.

#### Obtener balances

Para obtener el balance de una cuenta específica en un nodo Ethereum utilizando curl, se usa el método JSON-RPC eth_getBalance. Este método devuelve la cantidad de Wei (la unidad más pequeña de Ether) que tiene la cuenta.

![image](https://github.com/user-attachments/assets/ce3fb99f-61d0-4fbe-82a2-f8d6e89ed52b)

**Explicación del Comando**
- curl -X POST: Indica que estamos realizando una solicitud POST.
- --data '{"jsonrpc":"2.0","method":"eth_getBalance","params":["0xYourAddress", "latest"],"id":1}': Este es el cuerpo de la solicitud JSON-RPC:
   - "jsonrpc": "2.0": Especifica la versión del protocolo JSON-RPC.
   - "method": "eth_getBalance": Especifica el método que queremos invocar, en este caso, eth_getBalance.
   - "params": ["0xAddress", "latest"]: Los parámetros del método. El primer parámetro es la dirección de la cuenta cuyo balance queremos obtener y el segundo parámetro es el número del bloque (usualmente "latest" para obtener el balance más reciente).
   - "id": 1: Un identificador de la solicitud, que puede ser cualquier número. Es útil para hacer un seguimiento de las solicitudes cuando se realizan múltiples solicitudes a la vez.

Como resultado obtenemos el balances de las cuentas en WEI.

#### Transacciones

Para realizar una transaccion de ethers de una cuenta a otra cuenta, utilizando curl, se usa el metodo personal_sendTransaction. Este método es parte de la API JSON-RPC de Ethereum y es utilizado para enviar transacciones firmadas desde una cuenta gestionada por un nodo Ethereum, proporcionando una forma segura y sencilla de interactuar con contratos inteligentes o transferir Ether.

![image](https://github.com/user-attachments/assets/1b1bf700-d87a-4223-a248-9d8848862f9a)

**Explicación del Comando**
- curl -X POST: Indica que estamos realizando una solicitud HTTP POST, lo cual es necesario para enviar datos al servidor.
   - "jsonrpc": "2.0": Define la versión del protocolo JSON-RPC que se está utilizando.
   - "method": "personal_sendTransaction": Especifica el método de la API que se está llamando, en este caso, personal_sendTransaction.
   - "params": Lista de parámetros que se envían al método:
      - "from": La dirección de la cuenta desde la cual se envía la transacción.
      - "to": La dirección de la cuenta de destino.
      - "value": La cantidad de Ether a enviar, expresada en Wei.
      - "gas": El límite de gas para la transacción, que establece el máximo de unidades de gas que se puede gastar.
      - "gasPrice": El precio del gas por unidad, especificado en Wei.
      - "data": Datos adicionales a enviar, usualmente utilizado para interactuar con contratos inteligentes.
   - "YourAccountPassword": La contraseña de la cuenta remitente, utilizada para desbloquear la cuenta y firmar la transacción.
   - "id": 1: Un identificador único para la solicitud, utilizado para hacer un seguimiento de la misma. Puede ser cualquier número o cadena.

Como se puede ver una vez es ejecutado el comando, como resultado obtengo el hash de la transaccion. Para comprobar que esta transaccion se incluyo en un bloque debemos de ir a los logs del nodo desde el cual se realizo la operacion para ver si se ha minado la transaccion.

![image](https://github.com/user-attachments/assets/b75f6d39-ad75-44ed-8c4c-d689d7d57632)

En esta imagen se ve el nodo ha firmado una transaccion, la cual el hash coincide con el de la operacion realizada, ademas el siguiente bloque se observa que hay 1 transaccion (1 txs).

#### Prueba roles

Para comprobar que cada nodo realemnte estan haciendo sus roles correcatamente, vamos a apagar el nodo validador y posteriormente realaizar una transaccion desde otro nodo, para comprobar como se comportan los nodos.

Lo primero sera parar el nodo validador.

![image](https://github.com/user-attachments/assets/1909b304-8068-4c3b-ac4b-8b1999f693f4)

Cuando el nodo este apagado ejecutamos una transaccion desde algun el nodo no validador.

![image](https://github.com/user-attachments/assets/eb2324c7-d1ff-4ebf-83e2-e229d1ff28b3)

Como resultado obtenemos un hash, pero esto no significa que se haya minado he incluido en un bloque, si nos vamos a los logs del nodo del cual se ha hecho la operacion, veremos que el nodo reporta recurrentemente que no esta firmando y que no esta preparando bloques.

![image](https://github.com/user-attachments/assets/0261cefa-e5f0-4725-b0fc-fe7bb0536e53)

Ahora una vez lanzada la operacion, viendo que el nodo no puedo procesar la transaccion. Encendemos el nodo validador y podemos observar que posteriormente el nodo mina la transaccion y se adjunta en un bloque como vemos en la siguientes imagenes.

![image](https://github.com/user-attachments/assets/84754e13-a7da-42ba-b58a-8ab030372aa8)

![image](https://github.com/user-attachments/assets/ffed289e-f1fd-4d8e-8e35-05bbe6898578)

Despues de esta prueba podemos confirmar que cada nodo actua bajo su rol correctamente ya que solo si el nodo validador esta encendido, se minan transacciones y se generan bloques.

### Despliegue de SmartContract

Una vez que los nodos de la red privada de Ethereum están correctamente levantados y se han realizado pruebas básicas de transacciones, el siguiente paso es desplegar un contrato inteligente. En este proyecto, el contrato inteligente elegido es un sistema de votación simple escrito en Solidity.

#### Codigo Solidity

A continuacion se muestra el codigo del [SmartContract](contracts/Contrato.sol) que consigue sistema de votacion simple con funciones para votar y obtener los resultados.

```solidity
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract SimpleVoting {
    // Almacena los nombres de los candidatos u opciones de votación
    string[] public names;
    // Almacena el número de votos recibidos por cada opción
    uint256[] public votes;

    // Función para registrar un voto para una opción específica
    function vote(string memory name) public {
        // Variable booleana para verificar si la opción ya existe
        bool found = false;
        // Bucle para recorrer todas las opciones existentes
        for (uint256 i = 0; i < names.length; i++) {
            // Compara el nombre dado con los nombres existentes usando keccak256 para evitar problemas de comparación de strings
            if (keccak256(abi.encodePacked(names[i])) == keccak256(abi.encodePacked(name))) {
                // Incrementa el contador de votos de la opción encontrada
                votes[i]++;
                // Marca la opción como encontrada
                found = true;
                // Termina el bucle porque ya se encontró la opción
                break;
            }
        }
        // Si la opción no se encontró, se agrega como una nueva opción
        if (!found) {
            names.push(name); // Agrega el nuevo nombre al arreglo de nombres
            votes.push(1); // Inicializa su contador de votos a 1
        }
    }

    // Función para obtener los resultados de la votación
    function getResults() public view returns (string[] memory, uint256[] memory) {
        // Devuelve los arreglos de nombres y votos
        return (names, votes);
    }
}
```
Licencia y Versión del Compilador.
- // SPDX-License-Identifier: MIT: Indica que el contrato está bajo la licencia MIT.
- pragma solidity ^0.8.0;: Define la versión mínima de Solidity requerida para compilar el contrato.
Variables de Estado.
- string[] public names;: Arreglo que almacena los nombres de los candidatos u opciones de votación.
- uint256[] public votes;: Arreglo que guarda el número de votos recibidos por cada opción.
Funciones.
- Función vote: Esta función permite a los usuarios votar por una opción específica. Si la opción ya existe, incrementa su contador de votos. Si no, añade una nueva entrada.
- Función getResults: Retorna los nombres y los votos correspondientes a cada opción, permitiendo la consulta de los resultados de la votación.

#### Compilacion

Una vez diseñado la logica del [SmartContract](contracts/Contrato.sol) para compilarlo, nos dirigeremos a la ruta donde se ubica el contrato en una consola y procederemos a compilar con la herramineta solc.
Antes hay que intalar la herramienta solc con el siguiente comando:
```sh
npm install -g solc
```
ahora con la heramineta instalada, para proceder con la compilacion ejecuto el siguiente comando:
```sh
solc --abi --bin Contrato.sol -o ./build
```
Este comando sirve para obtener los datos binarios y los datos ABI reuqeridos para desplegar el contrato en la red en la carpeta build.

#### Despliegar y interactuar con Web3js

Para desplegar el [SmartContract](contracts/Contrato.sol) con web3.js, he creado un script de JavaScript, que se conecta al nodo que especifiquemos y despliega el contrato, desde la cuenta del nodo al que se conecta. A continuacion se muestra el codigo del [script](contracts/deploy.js) con comentarios explicando cada bloque.

```js
const Web3 = require('web3');
const fs = require('fs');
const path = require('path');
const readlineSync = require('readline-sync');
const fetch = require('node-fetch');

// Conecta a tu nodo Ethereum
const web3 = new Web3(new Web3.providers.HttpProvider('http://localhost:8545'));
console.log('Se ha conectado con el node RPC');


// Lee el ABI y el bytecode del contrato
const contractABI = JSON.parse(fs.readFileSync(path.resolve(__dirname, 'build', 'Contrato_sol_SimpleVoting.abi'), 'utf-8'));
const contractBytecode = fs.readFileSync(path.resolve(__dirname, 'build', 'Contrato_sol_SimpleVoting.bin'), 'utf-8');

// Función para pedir la contraseña de la cuenta
function askPassword() {
    return readlineSync.question('Introduce la contraseña de la cuenta: ', { hideEchoBack: true });
}

async function deployContract() {
    try {
        // Obtén las cuentas disponibles
        const accounts = await web3.eth.personal.getAccounts();
        if (accounts.length === 0) {
            console.log('No hay cuentas disponibles en el nodo.');
            return;
        }

        const account = accounts[0];
	console.log('Cuenta seleccionada:', account)
        const password = askPassword();

        // Crear una instancia del contrato
        const contract = new web3.eth.Contract(contractABI);

        // Definir los parámetros del despliegue
        const deployParameters = {
            data: '0x' + contractBytecode,
            arguments: [] // Añade argumentos del constructor si es necesario
        };

        // Construir el objeto de transacción
        const tx = {
            from: account,
            data: contract.deploy(deployParameters).encodeABI(),
            gas: '0x4c4b40',
            gasPrice: '0x4A817C800' // 20 Gwei
        };

       // Enviar la transacción usando personal_sendTransaction
        const txHash = await web3.eth.personal.sendTransaction(tx, password);
        console.log('Contrato desplegado, esperando la confirmación...');
        console.log('Hash de la transacción:', txHash);

    } catch (error) {
        console.error('Error al desplegar el contrato:', error.message);
    }
}

// Ejecuta la función de despliegue
deployContract();
```
Importación de librerías
- Web3: Importa la biblioteca Web3.js para interactuar con la blockchain de Ethereum.
- fs: Utilizado para leer archivos desde el sistema de archivos.
- path: Módulo para trabajar con rutas de archivos.
- readlineSync: Librería para manejar la entrada del usuario desde la consola.
- fetch: Librería para realizar solicitudes HTTP, aunque no se usa directamente en este script.
Conexión al nodo Ethereum
- Se conecta a un nodo de Ethereum en la URL especificada (http://localhost:8545).
Lectura de ABI y bytecode del contrato
- ABI (Application Binary Interface): Define cómo interactuar con el contrato. Se lee desde un archivo JSON.
- Bytecode: El código compilado del contrato, leído desde un archivo binario.
Función askPassword
- Solicita la contraseña de la cuenta del usuario, ocultando la entrada para seguridad.
Función deployContract
- Obtención de cuentas: Recupera las cuentas disponibles en el nodo. Usa la primera cuenta para desplegar el contrato.
- Instancia del contrato: Crea una instancia del contrato usando el ABI.
- Parámetros de despliegue: Configura los parámetros para el despliegue, incluyendo el bytecode y los argumentos del constructor (si los hay).
- Objeto de transacción: Define los detalles de la transacción, como la dirección de la cuenta, el bytecode del contrato, el gas, y el precio del gas.
- Envía la transacción: Utiliza web3.eth.personal.sendTransaction para enviar la transacción, solicitando la contraseña del usuario.
Ejecución del despliegue
- Llama a deployContract para iniciar el proceso de despliegue del contrato.
Este script automatiza el proceso de despliegue de un contrato inteligente en la blockchain de Ethereum, permitiendo interactuar con el contrato a través de una interfaz de línea de comandos. Para ejcutar este script, lo haremos con el siguiente comando:

```sh
node deploy.js
```
La consola muestra lo siguiente:
![image](https://github.com/user-attachments/assets/87c7fd49-5c09-4afd-ad81-0b4bbb9949aa)

Despues de desplegar el contrato exitosamente, utilizare el hash de transaccion para obtener todos los datos del contrato con un comando curl con el metodo eth_getTransactionReceipt, estos datos seran necesario recopilarlos para despues interactuar con el contrato.

![image](https://github.com/user-attachments/assets/a219eab8-0b55-4cde-9627-a6121953355a)

Una vez tenga los datos, utilizare otro script para interactuar con el contrato utilizando sus funciones, para votar desde distintas cuentas y obtener los resultados y establecer un ganador. El codigo con comentarios del script es el siguiente:

```js
// Importa las librerías necesarias
const Web3 = require('web3');
const fs = require('fs');
const path = require('path');
const readlineSync = require('readline-sync');

// Función principal para seleccionar la acción
async function main() {

        // Función para pedir la URL del nodo
    function askNodeURL() {
        return readlineSync.question('Introduce la URL del nodo (por ejemplo, http://localhost:8545): ');
    }
        // Función para pedir la contraseña de la cuenta
    function askPassword() {
        return readlineSync.question('Introduce la contraseña de la cuenta: ', { hideEchoBack: true });
    }
        // Función para pedir argumentos para las funciones del contrato
	function askArguments(prompt) {
        return readlineSync.question(prompt);
	}
    	// Conexión al nodo seleccionado
	const nodeURL = askNodeURL();
	const web3 = new Web3(new Web3.providers.HttpProvider(nodeURL));

	// Dirección del contrato desplegado
	const contractAddress = '0x45215e29a04ba0ee4aef76eda537a43bb47e1eb9';

	// ABI del contrato
	const contractABI = JSON.parse(fs.readFileSync(path.resolve(__dirname, 'build', 'Contrato_sol_SimpleVoting.abi'), 'utf-8'));

	// Instancia del contrato usando web3.eth.Contract
	const contract = new web3.eth.Contract(contractABI, contractAddress);

    // Función para votar por una opción
	async function vote(account, password) {
    const name = askArguments('Introduce el nombre al que deseas votar: ');
        try {
            // Construir el objeto de transacción
            const tx = {
                from: account,
                to: contractAddress,
                data: contract.methods.vote(name).encodeABI()
            };
            // Enviar la transacción usando personal_sendTransaction
            const receipt = await web3.eth.personal.sendTransaction(tx, password);
            console.log('Voto registrado:', receipt);
        } catch (error) {
            console.error('Error al votar:', error.message);
        }
	}
        // Función para obtener los resultados de los votos
    async function getResults() {
    try {
        // Establecer opciones para la llamada
        const options = {
            to: contractAddress,
            data: contract.methods.getResults().encodeABI(),
            gas: 3000000,
            gasPrice: '20000000000' // 20 Gwei
        };

        // Hacer la llamada al contrato
        const result = await web3.eth.call(options);

        // Decodificar el resultado
        const decodedResult = web3.eth.abi.decodeParameters(
            ['string[]', 'uint256[]'], 
            result
        );

        console.log('Resultados de la votación:', decodedResult);
    } catch (error) {
        console.error('Error al obtener los resultados:', error.message);
    }
	}

        // Inicia la ejecución del programa
      	const accounts = await web3.eth.personal.getAccounts();
        if (accounts.length === 0) {
            console.log('No hay cuentas disponibles en el nodo.');
            return;
        }

        const account = accounts[0];
        console.log(`Cuenta seleccionada: ${account}`);
        const password = askPassword();

        while (true) {
        console.log(`
        [1] Votar
        [2] Obtener resultados
        [0] CANCEL
        `);
        const choice = askArguments('Elige una acción [1...2 / 0]: ');

        switch (choice) {
            case '1':
                await vote(account, password);
                break;
            case '2':
                await getResults();
                break;
            case '0':
                console.log('Cancelando...');
                return; // Salir del bucle y terminar el programa
            default:
                console.log('Opción inválida. Inténtalo de nuevo.');
        }
    }
}

// Llama a la función principal para iniciar el programa
main();
```
Importación de librerías
- Web3: Importa la biblioteca Web3.js para interactuar con la blockchain de Ethereum.
- fs: Importa el módulo del sistema de archivos para leer archivos.
- path: Módulo para trabajar con rutas de archivo.
- readlineSync: Librería para manejar la entrada de usuario en la consola.
Funciones auxiliares
- askNodeURL(): Solicita al usuario la URL del nodo de Ethereum al que se conectará.
- askPassword(): Solicita al usuario la contraseña de la cuenta, oculta la entrada para seguridad.
- askArguments(prompt): Solicita argumentos al usuario, como nombres o números, para interactuar con el contrato.
Funciones de contrato
- vote(account, password): Función para votar por una opción. Construye y envía una transacción al contrato.
- getResults(): Función para obtener y mostrar los resultados de la votación. Llama a una función de contrato y decodifica el resultado.
Ejecución del programa
- Obtiene las cuentas disponibles en el nodo y selecciona la primera.
- Solicita al usuario la contraseña y ofrece un menú para elegir entre votar o obtener resultados.
- Ejecuta la acción seleccionada por el usuario.
Este script proporciona una interfaz de línea de comandos para interactuar con un contrato de votación en la blockchain de Ethereum, permitiendo a los usuarios votar y consultar los resultados utilizando Web3.js.
































