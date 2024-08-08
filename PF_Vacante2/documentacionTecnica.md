# Proyecto Final: Desarrollo de una Dapp

## 1. Introduccion

### Contexto y Objetivo

El objetivo principal de este proyecto es demostrar la capacidad para desarrollar y desplegar un Smart Contract en una blockchain pública, específicamente en la red Ethereum. Este proyecto forma parte del proceso de candidatura para la vacante en la compañía Blockchain TS, que requiere habilidades avanzadas en el desarrollo de contratos inteligentes y en la gestión de datos en una blockchain. 

Se ha decidido presentar este proyecto como parte de la candidatura a la oferta de empleo de Blockchain TS. La vacante se centra en el desarrollo de un sistema basado en blockchain para gestionar la inscripción de estudiantes en diversos cursos ofrecidos.

El objetivo del proyecto es desarrollar un Smart Contract que permita registrar la inscripción de estudiantes en cuatro cursos específicos ofrecidos por el centro de estudios Tokio School y interactuar con el contrato con programa de Java. Los pasos a seguir incluyen:

1. Utilizar la red Ethereum para desplegar la blockchain donde se ejecutará el Smart Contract.

2. Crear un contrato inteligente que minimo tenga funciones para:
   - Permitir registrar a los estudiantes en uno de los cuatro cursos ofrecidos.
   - Ofrecer la capacidad de consultar la lista de estudiantes matriculados por curso.

3. Desplegar el contrato inteligente en la red Ethereum seleccionada.

4. Desarollo de una Dapp en Java que interactue con el contrato.

5. Realizar transacciones y consultas para verificar que el Smart Contract funciona correctamente y cumple con los requisitos del proyecto.

Este proyecto tiene como finalidad validar la capacidad técnica para el desarrollo de soluciones blockchain efectivas y funcionales en un entorno real.

## 2. Requisitos y Preparación

Para llevar a cabo este proyecto, se utilizara diversos software y herramientas que facilitarna el despligue de la red blockchain y el desarollo de la Dapp.

- **Ganache**: Es una herramienta de Truffle Suite que permite crear y ejecutar una blockchain Ethereum local en tu máquina. Es una herramienta esencial para el desarrollo y la prueba de contratos inteligentes y aplicaciones descentralizadas (dApps). Ganache proporciona una red blockchain privada con las siguientes características:

  - **Interfaz de Usuario**: Ofrece una interfaz gráfica intuitiva para gestionar y monitorizar las transacciones, bloques y contratos en la blockchain local.
  - **Simulación de Transacciones**: Permite realizar transacciones y ejecutar contratos inteligentes de manera rápida y segura sin necesidad de interactuar con la red Ethereum pública o de prueba.
  - **Creación de Cuentas**: Genera automáticamente varias cuentas Ethereum preconfiguradas con fondos para realizar pruebas.
  - **Control de Gas y Minado**: Permite ajustar la cantidad de gas disponible para las transacciones y controlar el proceso de minado, facilitando las pruebas de contratos inteligentes.

- **Remix IDE**: Entorno de desarrollo integrado (IDE) para escribir, compilar y desplegar contratos inteligentes escritos en Solidity. Remix se usará para compilar y desplegar el contrato inteligente en la red Ethereum de Ganache una manera simple.

- **solcjs**: Es la versión de línea de comandos basada en JavaScript del compilador Solidity, utilizado para compilar contratos inteligentes escritos en Solidity. Esta herramienta convierte el código fuente en bytecode ejecutable en la blockchain y genera la interfaz de binario de aplicación (ABI), necesaria para interactuar con el contrato desde aplicaciones externas.

  - En el proyecto, utilicé olcjs para compilar el contrato inteligente y obtener el archivo binario y el archivo ABI, que son cruciales para permitir que la aplicación Java, utilizando Web3j, pueda comunicarse con el contrato desplegado.
  - Para instalar la herramienta ejecutaremos el siguiente comando:
  ```sh
  npm install -g solc
  ```
- **Web3j**: es una biblioteca Java para interactuar con la blockchain de Ethereum. Proporciona una API simple y potente para la comunicación con nodos de Ethereum, permitiendo la integración de contratos inteligentes y el envío de transacciones desde aplicaciones Java.

  - En el proyecto, utilicé Web3j para generar el archivo Java necesario para interactuar con el contrato inteligente desplegado. A partir del archivo binario y el ABI obtenidos mediante solcjs, Web3j facilitó la creación de una clase Java que permite invocar métodos del contrato y manejar sus eventos. Así, Web3j actuó como puente entre la aplicación Java y la blockchain, permitiendo la ejecución de operaciones sobre el contrato inteligente de manera eficiente y segura.
  - Para instalar la biblioteca ejecutaremos el siguiente comando:
  ```sh
  curl -L get.web3j.io | sh && 
  source ~/.web3j/source.sh
  ```
- **Eclipse IDE**: Es un entorno de desarrollo integrado (IDE) de código abierto utilizado para el desarrollo de software en varios lenguajes de programación, incluyendo Java. Ofrece una amplia gama de herramientas y características, como un editor de código avanzado, depurador, y capacidades de gestión de proyectos, que facilitan la programación y el mantenimiento del código.

 - En este proyecto, utilicé **Eclipse IDE** para desarrollar y ejecutar el archivo Java que interactúa con el contrato inteligente desplegado. Eclipse proporcionó un entorno de desarrollo eficiente para escribir, depurar y probar el código Java, facilitando la integración con **Web3j** y la ejecución de operaciones sobre la blockchain de Ethereum.








