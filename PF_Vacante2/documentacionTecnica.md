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
 
## 3. Desarrollo y despliegue del SmartContract.

Para este proyecto, he desarrollado un Smart Contract utilizando Solidity que tiene como objetivo registrar y gestionar la matricula de alumnos en diferentes cursos. Para que este sistema pueda funcionar correctamente he desarollado multiples funciones de escritura y lectura.

### Explicacion del codigo.

A continuacion explicare por bloques de codigo el funcionamiento de cada sentencia del [SmartContract](./src/main/java/contracts/contrato.sol). 

#### Preparacion del contrato. 

 
```solidity
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract DAppEducativa {
    struct Usuario {
        string correo;
        bytes32 hashedPassword;
        string cursoMatriculado;
        string cursoMatriculado2;
        string cursoMatriculado3;
        bool registrado;
        string nombre;
    }

    struct Profesor {
        string nombre;
        bool registrado;
    }

    struct Curso {
        string nombreCurso;
        address profesor;
    }
```
Este bloque define las estructuras de datos fundamentales que se utilizarán para gestionar la información de los usuarios, profesores y cursos dentro de la plataforma educativa. Estas estructuras permiten organizar y acceder fácilmente a la información necesaria para la lógica de la aplicación.

- **Licencia y version de solidity.**
  
   - **Licencia SPDX**: Especifica la licencia bajo la cual se distribuye el contrato. Aquí se usa la licencia MIT, una de las más permisivas y populares en proyectos de código abierto.
   - **Versión de Solidity**: Define que el contrato está escrito para ser compilado con versiones de Solidity 0.8.0 o superiores.
     
- **Definicion del contrato.**
  
   - **contract DAppEducativa**: Define un nuevo contrato inteligente llamado DAppEducativa, que es el núcleo del proyecto. Este contrato gestionará la lógica para registrar usuarios, profesores y cursos en una plataforma educativa descentralizada.
     
- **Estructuras de datos.**
  
   - **Usuario**: Define la estructura para almacenar la información relacionada con cada usuario (alumno) en la plataforma.
      - **correo**: Guarda la dirección de correo electrónico del usuario.
      - **hashedPassword**: Almacena la contraseña del usuario en formato hash para mayor seguridad.
      - **cursoMatriculado, cursoMatriculado2, cursoMatriculado3**: Almacenan los nombres de los cursos en los que el usuario está matriculado. Se permiten hasta tres cursos.
      - **registrado**: Un valor booleano que indica si el usuario ya ha sido registrado en la plataforma.
      - **nombre**: Guarda el nombre completo del usuario.
   - **Profesor**: Define la estructura para almacenar la información sobre los profesores.
      - **nombre**: Guarda el nombre del profesor.
      - **registrado**: Indica si el profesor está registrado en la plataforma.
   - Curso: Define la estructura para almacenar los datos de un curso.
      - **nombreCurso**: Almacena el nombre del curso.
      - **profesor**: Guarda la dirección Ethereum del profesor responsable del curso.
        
```solidity
mapping(address => Usuario) public usuarios;
    mapping(address => Profesor) public profesores;
    mapping(string => Curso) public cursos;
    mapping(address => bool) public esProfesor;

    address[] public cuentasRegistradas;  
    address[] public cuentasProfesores;
    string[] public nombresCursos;


    address public owner;

    constructor() {
        owner = msg.sender; // El creador del contrato es el propietario inicial
    }

    modifier soloOwner() {
        require(msg.sender == owner, "Solo el propietario puede ejecutar esta funcion");
        _;
    }

    modifier soloProfesor() {
        require(esProfesor[msg.sender], "Solo profesores pueden ejecutar esta funcion");
        _;
    }
```
Este bloque establece la infraestructura necesaria para gestionar usuarios, profesores y cursos en la plataforma educativa descentralizada. Los mapeos permiten almacenar y acceder a los datos relevantes, mientras que los modificadores y el constructor aseguran que solo personas con los permisos adecuados puedan realizar ciertas acciones en el contrato.

- **Mapeos**.
  
   - **usuarios**: Mapea direcciones de Ethereum (address) a la estructura Usuario. Esto permite asociar una dirección de Ethereum con un usuario registrado en la plataforma.
   - **profesores**: Similar al anterior, este mapeo asocia una dirección de Ethereum con la estructura Profesor, lo que facilita la gestión de profesores registrados.
   - **cursos**: Mapea los nombres de los cursos (string) a la estructura Curso, permitiendo una fácil consulta de información sobre un curso en particular.
   - **esProfesor**: Este mapeo asocia una dirección de Ethereum con un valor booleano que indica si la dirección corresponde a un profesor registrado. Esto es útil para verificar permisos.
     
- **Listas de Direcciones y Cursos.**
  
   - **cuentasRegistradas**: Una lista que almacena todas las direcciones de usuarios que se han registrado en la plataforma.
   - **cuentasProfesores**: Almacena las direcciones de los profesores registrados, permitiendo listarlos fácilmente.
   - **nombresCursos**: Almacena los nombres de todos los cursos disponibles en la plataforma.
   - **owner**: Guarda la dirección del propietario del contrato, que es quien despliega el contrato en la blockchain. Este rol tiene privilegios especiales en ciertas funciones.
     
- **Constructor.**
  
   - **constructor**: El constructor se ejecuta una vez, en el momento en que se despliega el contrato. Aquí, se establece que el propietario del contrato (owner) es la dirección que lo despliega (msg.sender).
     
- **Modificadores.**
  
   - **soloOwner**: Este modificador restringe el acceso a ciertas funciones del contrato, asegurando que solo el propietario pueda ejecutarlas. Si alguien que no es el propietario intenta ejecutar una función con este modificador, la ejecución falla y muestra un mensaje de error.
   - **soloProfesor**: Este modificador restringe el acceso a funciones que solo los profesores deben poder ejecutar. Verifica que la dirección que intenta ejecutar la función esté registrada como profesor en el mapeo esProfesor.

#### Funciones de Escritura. 

```solidity
function agregarProfesor(address _cuenta, string memory _nombre) public soloOwner {
        require(!profesores[_cuenta].registrado, "Profesor ya registrado");
        
        profesores[_cuenta] = Profesor({
            nombre: _nombre,
            registrado: true
        });

        esProfesor[_cuenta] = true; // Marcar la cuenta como profesor
        cuentasProfesores.push(_cuenta);
    }
```
La función agregarProfesor permite al propietario del contrato registrar nuevas cuentas de profesores en la plataforma educativa. Antes de registrar un nuevo profesor, la función verifica que la cuenta no esté ya registrada. Una vez que un profesor es agregado, su cuenta se marca como válida y se incluye en la lista de cuentas de profesores.

- **Parámetros de Entrada**:

   - **address _cuenta**: La dirección de Ethereum que representa la cuenta del profesor.
   - **string memory _nombre**: El nombre del profesor.
     
- **Modificador soloOwner**: Esta función está protegida por el modificador soloOwner, lo que significa que solo el propietario del contrato puede ejecutarla. Esto asegura que solo el administrador principal de la plataforma pueda agregar nuevos profesores.

- **Lógica de la Función.**

   - **require(!profesores[_cuenta].registrado, "Profesor ya registrado");**: Esta línea verifica si la dirección de Ethereum proporcionada ya está registrada como profesor. Si el profesor ya está registrado, la función lanza un error con el mensaje "Profesor ya registrado".
   - Si la verificación anterior es exitosa, se crea una nueva instancia de la estructura Profesor, asociada a la dirección de Ethereum _cuenta, y se guarda en el mapeo profesores. Esto incluye el nombre del profesor y un indicador de que está registrado.
   - Finalmente, la dirección del nuevo profesor se agrega a la lista cuentasProfesores. Esta lista puede utilizarse para consultar todas las cuentas de profesores registradas en la plataforma.


```solidity
function agregarCurso(string memory _nombreCurso, address _profesor) public soloOwner {
        require(cursos[_nombreCurso].profesor == address(0), "Curso ya registrado");
        require(esProfesor[_profesor], "El profesor no esta registrado");

        cursos[_nombreCurso] = Curso({
            nombreCurso: _nombreCurso,
            profesor: _profesor
        });
        nombresCursos.push(_nombreCurso); // Agregar el nombre del curso a la lista

    }
```
 La función agregarCurso permite al propietario del contrato añadir nuevos cursos a la plataforma educativa. Antes de registrar un curso, la función realiza verificaciones clave para asegurar que el curso no esté ya registrado y que el profesor asignado esté registrado como tal. Una vez superadas estas verificaciones, se registra el curso y se actualiza la lista de cursos disponibles.

- **Parametros de entrada.**

   - **string memory _nombreCurso**: El nombre del curso que se desea agregar.
   - **address _profesor**: La dirección de Ethereum del profesor que impartirá el curso.
     
- **Modificador soloOwner**: Esta función está protegida por el modificador soloOwner, lo que significa que solo el propietario del contrato puede ejecutarla. Esto asegura que solo la persona con los permisos adecuados pueda agregar nuevos cursos a la plataforma.
  
- **Logica de la funcion.**
  
   - **require(cursos[_nombreCurso].profesor == address(0), "Curso ya registrado");**: Esta línea verifica si el curso que se intenta agregar ya está registrado en el contrato. Si el curso ya existe (es decir, si tiene un profesor asignado), la función lanza un error con el mensaje "Curso ya registrado".
   - **require(esProfesor[_profesor], "El profesor no esta registrado");**: Antes de asignar un curso a un profesor, la función verifica que la dirección proporcionada corresponde a un profesor registrado en la plataforma. Si no es así, se lanza un error con el mensaje "El profesor no está registrado".
   - Si las verificaciones anteriores son exitosas, se crea un nuevo curso y se asocia con el profesor correspondiente. Esto se hace mediante la asignación de una nueva instancia de la estructura Curso al mapeo cursos, utilizando el nombre del curso como clave.
   - Finalmente, el nombre del nuevo curso se agrega a la lista nombresCursos. Esto facilita la consulta de todos los cursos registrados en la plataforma.

```solidity
function registrarUsuario(address _cuenta, string memory _correo, string memory _password, string memory _curso, string memory _nombre) public {
        require(!usuarios[_cuenta].registrado, "Usuario ya registrado");

        usuarios[_cuenta] = Usuario({
            correo: _correo,
            hashedPassword: keccak256(abi.encodePacked(_password)),
            nombre: _nombre,
            cursoMatriculado: _curso,
            cursoMatriculado2: "",
            cursoMatriculado3: "",
            registrado: true
        });

        cuentasRegistradas.push(_cuenta);
    }
```

La función registrarUsuario realiza una validación para evitar duplicados, cifra de forma segura la contraseña y registra toda la información relevante del usuario, incluida su matrícula en un curso inicial. Además, mantiene un registro actualizado de todas las cuentas registradas, lo que permite un seguimiento eficiente de los usuarios dentro del sistema.

- **Parámetros de entrada.**
   
  - **_cuenta**: La dirección de Ethereum que identifica al usuario.
  - **_correo**: El correo electrónico del usuario.
  - **_password**: La contraseña del usuario, que será cifrada antes de almacenarse.
  - **_curso**: El curso en el que el usuario se está matriculando inicialmente.
  - **_nombre**: El nombre del usuario.

- **Visibilidad**: La función es pública, por lo que cualquier cuenta puede llamarla.

- **Logica de la funcion.**
  
   - **require(!usuarios[_cuenta].registrado, "Usuario ya registrado");**: Antes de registrar al usuario, se verifica si la cuenta asociada ya está registrada. Si el usuario ya existe, la función se detiene y muestra el mensaje "Usuario ya registrado".
   - **Registro del Usuario**:
      - Se crea un nuevo usuario utilizando la estructura Usuario.
      - La contraseña del usuario se almacena de manera segura utilizando el algoritmo de hash keccak256 aplicado al valor de la contraseña cifrada con abi.encodePacked.
      - Se registra el curso inicial en el que se matricula el usuario (cursoMatriculado).
      - Los campos cursoMatriculado2 y cursoMatriculado3 se dejan vacíos (""), preparados para futuros cursos si el usuario decide matricularse en más de uno.
      - El campo registrado se establece en true, indicando que el registro se ha completado.
   - **Actualización del Registro de Cuentas**: La dirección del nuevo usuario se añade a la lista cuentasRegistradas, lo que facilita la gestión de todos los usuarios registrados en la DApp.

```solidity
function matricularseEnOtroCurso(address _cuenta, string memory _nuevoCurso) public {
        require(usuarios[_cuenta].registrado, "Usuario no registrado");

        Usuario storage usuario = usuarios[_cuenta];

        if (bytes(usuario.cursoMatriculado).length == 0) {
            usuario.cursoMatriculado = _nuevoCurso;
        } else if (bytes(usuario.cursoMatriculado2).length == 0) {
            usuario.cursoMatriculado2 = _nuevoCurso;
        } else if (bytes(usuario.cursoMatriculado3).length == 0) {
            usuario.cursoMatriculado3 = _nuevoCurso;
        } else {
            revert("El usuario ya esta matriculado en 3 cursos.");
        }
    }
```
La función matricularseEnOtroCurso permite que un usuario registrado se matricule en hasta tres cursos diferentes. Si hay espacio disponible en los registros de cursos (cursoMatriculado, cursoMatriculado2, cursoMatriculado3), el nuevo curso se añade al primer campo vacío disponible. Si el usuario ya está matriculado en tres cursos, la función previene la matriculación en cursos adicionales, garantizando así que no se supere el límite de tres cursos por usuario.

- **Parámetros de entrada.**

   - **_cuenta**: La dirección de Ethereum que identifica al usuario.
   - **_nuevoCurso**: El nombre del nuevo curso en el que el usuario desea matricularse. 

- **Visibilidad**: La función es pública, lo que permite que cualquier usuario registrado pueda llamarla.

- **Logica de la funcion.**
   - **Usuario storage usuario = usuarios[_cuenta];**:  Se verifica que la cuenta esté registrada en el sistema. Si el usuario no está registrado, la función se detiene y lanza el mensaje "Usuario no registrado".
   - Se obtiene una referencia mutable al objeto Usuario asociado a la dirección _cuenta. Esto permite modificar los datos del usuario dentro de la función.
   - Asignación del Nuevo Curso:
      - La función evalúa en qué campos de cursos matriculados (cursoMatriculado, cursoMatriculado2, cursoMatriculado3) hay espacio disponible para matricular al usuario en el nuevo curso.
      - Se verifica el primer campo vacío de los tres posibles, y se asigna el nuevo curso a dicho campo.
      - Si el usuario ya está matriculado en tres cursos (todos los campos están llenos), la función lanza una excepción con el mensaje "El usuario ya está matriculado en 3 cursos." y revierte la transacción.

#### Funciones de Lectura. 

    
```solidity
function obtenerNombresCursos() public view returns (string[] memory) {
        return nombresCursos;
    }

    function obtenerCursosMatriculados(
        address _cuenta
    ) public view returns (string memory, string memory, string memory) {
        Usuario storage usuario = usuarios[_cuenta];
        return (
            usuario.cursoMatriculado, 
            usuario.cursoMatriculado2, 
            usuario.cursoMatriculado3
        );
    }

    function obtenerUsuariosRegistrados() public view soloProfesor returns (Usuario[] memory) {
        Usuario[] memory usuariosRegistrados = new Usuario[](cuentasRegistradas.length);
        for (uint i = 0; i < cuentasRegistradas.length; i++) {
            usuariosRegistrados[i] = usuarios[cuentasRegistradas[i]];
        }
        return usuariosRegistrados;
    }

    function obtenerUsuariosPorCurso(string memory _curso) public view returns (address[] memory) {
        uint256 count = 0;
        for (uint256 i = 0; i < cuentasRegistradas.length; i++) {
            if (keccak256(abi.encodePacked(usuarios[cuentasRegistradas[i]].cursoMatriculado)) == keccak256(abi.encodePacked(_curso))) {
                count++;
            }
        }

        address[] memory resultado = new address[](count);
        uint256 index = 0;
        for (uint256 i = 0; i < cuentasRegistradas.length; i++) {
            if (keccak256(abi.encodePacked(usuarios[cuentasRegistradas[i]].cursoMatriculado)) == keccak256(abi.encodePacked(_curso))) {
                resultado[index] = cuentasRegistradas[i];
                index++;
            }
        }

        return resultado;
    }

    function obtenerCuentasProfesores() public view returns (address[] memory, string[] memory) {
        string[] memory nombres = new string[](cuentasProfesores.length);
        for (uint i = 0; i < cuentasProfesores.length; i++) {
            nombres[i] = profesores[cuentasProfesores[i]].nombre;
        }
        return (cuentasProfesores, nombres);
    }function obtenerNombresCursos() public view returns (string[] memory) {
        return nombresCursos;
    }

    function obtenerCursosMatriculados(
        address _cuenta
    ) public view returns (string memory, string memory, string memory) {
        Usuario storage usuario = usuarios[_cuenta];
        return (
            usuario.cursoMatriculado, 
            usuario.cursoMatriculado2, 
            usuario.cursoMatriculado3
        );
    }

    function obtenerUsuariosRegistrados() public view soloProfesor returns (Usuario[] memory) {
        Usuario[] memory usuariosRegistrados = new Usuario[](cuentasRegistradas.length);
        for (uint i = 0; i < cuentasRegistradas.length; i++) {
            usuariosRegistrados[i] = usuarios[cuentasRegistradas[i]];
        }
        return usuariosRegistrados;
    }

    function obtenerUsuariosPorCurso(string memory _curso) public view returns (address[] memory) {
        uint256 count = 0;
        for (uint256 i = 0; i < cuentasRegistradas.length; i++) {
            if (keccak256(abi.encodePacked(usuarios[cuentasRegistradas[i]].cursoMatriculado)) == keccak256(abi.encodePacked(_curso))) {
                count++;
            }
        }

        address[] memory resultado = new address[](count);
        uint256 index = 0;
        for (uint256 i = 0; i < cuentasRegistradas.length; i++) {
            if (keccak256(abi.encodePacked(usuarios[cuentasRegistradas[i]].cursoMatriculado)) == keccak256(abi.encodePacked(_curso))) {
                resultado[index] = cuentasRegistradas[i];
                index++;
            }
        }

        return resultado;
    }

    function obtenerCuentasProfesores() public view returns (address[] memory, string[] memory) {
        string[] memory nombres = new string[](cuentasProfesores.length);
        for (uint i = 0; i < cuentasProfesores.length; i++) {
            nombres[i] = profesores[cuentasProfesores[i]].nombre;
        }
        return (cuentasProfesores, nombres);
    }
```
Estas funciones de lectura permiten a los usuarios y administradores de la DApp consultar información clave sobre cursos, usuarios y profesores, facilitando la gestión y la interacción con el sistema. 

- **obtenerNombresCursos().**
  
   - **Propósito**: Esta función permite consultar todos los nombres de los cursos que han sido registrados en el contrato inteligente.
   - **Funcionamiento**: La función simplemente devuelve el array nombresCursos, que almacena los nombres de los cursos. Este array se llena en la función agregarCurso cuando se añade un nuevo curso.
   - **Acceso**: Es una función pública y se puede llamar desde cualquier lugar. No requiere permisos especiales.

- **obtenerCursosMatriculados(address _cuenta).**

   - **Propósito**: Permite consultar los cursos en los que un usuario específico está matriculado.
   - **Funcionamiento**: La función toma la dirección del usuario _cuenta y accede al struct Usuario correspondiente en el mapping usuarios. Devuelve los tres campos que almacenan los cursos matriculados del usuario (cursoMatriculado, cursoMatriculado2, cursoMatriculado3).
   - **Acceso**: Es una función pública, accesible por cualquier persona.

- **obtenerUsuariosRegistrados().**
  
   - **Propósito**: Devuelve un array con toda la información de los usuarios registrados en la plataforma.
   - **Funcionamiento**: Crea un array dinámico de Usuario con el tamaño de cuentasRegistradas, que es una lista de todas las direcciones de los usuarios registrados. Itera a través de cuentasRegistradas y llena el array usuariosRegistrados con la información correspondiente de cada usuario. Devuelve este array de Usuario.
   - **Acceso**: Solo los profesores pueden ejecutar esta función debido al modificador soloProfesor.
 
- **obtenerUsuariosPorCurso(string memory _curso).**

   - **Propósito**: Permite obtener una lista de direcciones de usuarios que están matriculados en un curso específico.
   - **Funcionamiento**: Primero, cuenta cuántos usuarios están matriculados en el curso especificado (_curso). Esto se hace iterando sobre cuentasRegistradas y comparando el curso matriculado del usuario con el curso buscado utilizando keccak256 para la comparación de strings. Luego, crea un array resultado del tamaño exacto necesario y vuelve a iterar sobre cuentasRegistradas para llenar este array con las direcciones de los usuarios matriculados en el curso. Finalmente devuelve el array de direcciones.
   - **Acceso**: Es una función pública y se puede llamar desde cualquier lugar.
 
- **obtenerCuentasProfesores().**

   - Propósito: Devuelve las direcciones de los profesores y sus nombres asociados.
   - Funcionamiento: Crea un array nombres del mismo tamaño que cuentasProfesores y lo llena con los nombres de los profesores utilizando el mapping profesores. Devuelve dos arrays: el primero contiene las direcciones de los profesores y el segundo contiene los nombres correspondientes.
   - Acceso: Es una función pública, accesible por cualquier persona. 

```solidity
function iniciarSesion(
    address _cuenta, 
    string memory _correo, 
    string memory _password
) public view returns (string memory) {
    require(
        keccak256(abi.encodePacked(_correo)) == keccak256(abi.encodePacked(usuarios[_cuenta].correo)), 
        "Correo incorrecto"
    );
    require(
        keccak256(abi.encodePacked(_password)) == usuarios[_cuenta].hashedPassword, 
        "pass incorrecta"
    );

    return 'true';
}
```
La función iniciarSesion verifica las credenciales de un usuario (correo y contraseña) comparando los hashes proporcionados con los almacenados en el contrato. Si ambos coinciden, devuelve 'true'; si no, emite un mensaje de error específico. 

- **Propósito**: Permite a los usuarios iniciar sesión en la plataforma verificando sus credenciales (correo electrónico y contraseña).
- **Cómo Funciona**:
   - Verificación del Correo: La función primero compara el hash del correo electrónico proporcionado (_correo) con el hash almacenado en el struct Usuario asociado a la dirección _cuenta. La comparación se realiza utilizando la función keccak256 para asegurar que la comparación sea segura.
   - Verificación de la Contraseña: Luego, compara el hash de la contraseña proporcionada (_password) con el hash almacenado en el struct Usuario. Esta comparación también se realiza con keccak256.
   - Respuesta: Si ambas verificaciones son correctas, la función devuelve 'true', indicando que las credenciales son correctas. Si alguna de las verificaciones falla, se emite un mensaje de error específico ("Correo incorrecto" o "pass incorrecta"), y la función no devolverá 'true'.
- **Acceso**: Es una función pública, por lo que puede ser llamada por cualquier usuario para intentar iniciar sesión.
- **Seguridad**:
   - Hashing de Contraseñas: Las contraseñas se almacenan como hashes en lugar de texto plano, lo cual es una buena práctica para la seguridad. Utilizar 
   - keccak256 proporciona una forma de comparar contraseñas sin exponerlas directamente.
- **Requerimientos**: La función garantiza que tanto el correo como la contraseña sean verificados correctamente para una autenticación adecuada.

### Despliegues

A continuación, documentaré el proceso de despliegue de la red blockchain utilizando Ganache, que proporciona un entorno local de Ethereum para pruebas, y el despliegue del contrato inteligente en esta red mediante Remix con capturas de pantalla.

#### Despliegue de la red Blockchain.

Lo primero que voy a hacer es abrir el aplicativo de Ganache para crear la red, dandole a "new workspace" como vemos en la siguiente imagen. 

![image](https://github.com/user-attachments/assets/e9a03f6a-9805-4457-99eb-90386419f06b) 

Despues nos dejara elegir las opciones de configuracion de la red, como el nombre del workspace, el hostname y su puerto, las cuentas que habran en la red y sus balances, el precio y el limite del gas. En mi caso el hostname es la direccion localhost y el puerto es 7545 y configure para que se crearan 20 cuentas.

Una vez creado el workspace Ganache muestra las direcciones disponibles en el apartado de accounts, tambien es posible revisar los bloques miandos y las transacciones que se crearan mas adelante, como se puede ver en la siguiente imagen. 

![image](https://github.com/user-attachments/assets/662a940c-62c9-4ec4-b951-68993c0d9c01) 

#### Despliegue del contrato

Una vez que la red esta desplegada, desde Remix IDE compilaremos y desplegaremos el contrato. Primero me conectare a mi red desde la pestaña lateral de deploy & run transactions, con la direccion de mi nodo, como se ve en las siguientes imagenes.

![image](https://github.com/user-attachments/assets/ffd88a6e-114f-455a-903a-4e761a775a3b) 
![image](https://github.com/user-attachments/assets/ceca0394-8ca6-49d5-b412-e56c9c60e095)

Ahora con la red conectada, me aparecen todas las direcciones que tiene el nodo, entonces compilare el contrato y lo desplegare en la red.

![image](https://github.com/user-attachments/assets/3cd4d3d0-53ad-426e-9cb6-f135d7ff6573)

Al desplegar el contrato, a traves de la consola de Remix, podemos observar los datos del contrato, como en la imagen anterior. Para comprobar que se haya desplegado correcatmente, ahora iremos a Ganache para demostrar que sea creado el primer bloque con la creacion del contrato.

![image](https://github.com/user-attachments/assets/d4e8fd4e-ff54-420c-91c7-62e35e840b67) 

## 4. Integración con DApp utilizando Java. 

En este punto del proyecto, describiré cómo voy a realizar la integración del Smart Contract en una aplicación Java, para permitir la interacción con las funciones del contrato. 

### Preparacion Java. 

Primero, utilizaré el compilador de Solidity, solcjs, para generar los archivos bin y abi del contrato inteligente desplegado. Estos archivos serán esenciales para que herramientas como Web3j puedan generar clases Java que interactúen con el contrato. El comando es el siguiente:
```sh
solcjs --bin --abi -o ./build contrato.sol
```
Este comando producirá dos archivos en la carpeta build que contendrán el bytecode del contrato y su interfaz respectivamente. 

Una vez teniendo los archivos bin y abi, utilizaré la herramienta web3j para crear una clase Java que me permitirá interactuar con el contrato inteligente directamente desde Java con el siguiente comando.
```sh
web3j generate solidity -b ./build/contrato_DappEducativa.bin -a ./build/contrato_DappEducativa.abi -o ./src/main/java -p contracts
```
Este comando generará una clase Java dentro del paquete especificado, la cual incluirá métodos para todas las funciones del contrato inteligente, permitiéndome invocarlas desde la aplicación Java.

Ahora describiré cómo voy a crear un nuevo proyecto en Eclipse utilizando Maven para gestionar las dependencias necesarias, incluyendo web3j.

- Creación del Proyecto Maven en Eclipse
   - Abrir Eclipse: Inicio Eclipse y selecciono el espacio de trabajo donde se encuentran el contrato y sus archivos generados.
   - Nuevo Proyecto Maven: Voy a File > New > Other..., luego selecciono Maven Project y hago clic en Next.
   - Arquetipo de Maven: Selecciono el arquetipo maven-archetype-quickstart que proporciona una estructura básica para un proyecto Java y hago clic en Next
   - Esto creará una estructura básica de proyecto con una carpeta src que contendrá los directorios main/java y test/java.

- Configuración del archivo pom.xml
   - Una vez que el proyecto está creado, voy a configurar el archivo pom.xml en la raiz del directorio para incluir las dependencias necesarias para web3j.        - Abro el archivo pom.xml y agrego las siguientes dependencias:
   ```xml
   <dependencies>
    <dependency>
        <groupId>org.web3j</groupId>
        <artifactId>core</artifactId>
        <version>4.8.7</version>
    </dependency>
   </dependencies>
   ```
   - Este fragmento de código añade la dependencia principal de web3j al proyecto, lo que permitirá interactuar con la red Ethereum y con el Smart Contract desde Java.

### Explicacion main.java. 

Finalmente, crearé un archivo Main.java donde implementaré un sistema interactivo en Java que permitirá a diferentes tipos de usuarios (admin, profesor, alumno) interactuar con el contrato.

Modo Admin: El administrador podrá agregar nuevos cursos y profesores, y gestionar otras configuraciones de la DApp.
Modo Profesor: Los profesores podrán consultar y gestionar los usuarios matriculados en sus cursos.
Modo Alumno: Los alumnos podrán registrarse, iniciar sesión y matricularse en diferentes cursos.
Voy a implementar la lógica de interacción utilizando bucles para presentar menús y opciones según el rol del usuario. Cada acción invocará las funciones del contrato inteligente a través de los métodos generados en el archivo Java por web3j. 

A continuacion explicare bloque a bloque el contenido del main.java.

#### Calls funciones de escritura. 

```java
 private static void registrarProfesor(Scanner scanner) throws Exception {
    	try {
            System.out.println("Ingrese el nombre del profesor:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese la direccion del profesor:");
            String profesorDireccion = scanner.nextLine();

            // Realizar la transacción y obtener el hash
            TransactionReceipt receipt = contract.agregarProfesor(profesorDireccion, nombre).send();
            System.out.println("Profesor matriculado con éxito. Hash de la transacción: " + receipt.getTransactionHash());
        } catch (Exception e) {
            System.out.println("Error al registrar el profesor: " + e.getMessage());
        }
    }
```
registrarProfesor permite agregar nuevos profesores al sistema, interactuando directamente con el contrato inteligente para realizar el registro de manera segura y transparente en la blockchain. 

- Solicitar Datos del Profesor:

   -Se solicita al usuario que ingrese el nombre del profesor y su dirección (dirección de la cuenta de Ethereum). Estos datos se leen mediante el objeto Scanner pasado como parámetro al método.
  
- Interacción y Transacción con el Contrato Inteligente:

   - Se utiliza el contrato inteligente Contrato_sol_DAppEducativa, que ya está cargado y conectado a la red Ethereum, para llamar al método agregarProfesor.
   - Este método del contrato recibe la dirección del profesor y su nombre como parámetros.
   - Se ejecuta la transacción llamando a .send() sobre el resultado del método agregarProfesor, lo que envía la transacción a la red Ethereum.
   - Al completar la transacción, se obtiene un objeto TransactionReceipt, que contiene detalles de la transacción.
   - Imprime por pantalla el hash de la operacion
 
```java
private static void registrarCurso(Scanner scanner) throws Exception {
    	try {
            System.out.println("Ingrese el nombre del curso:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese la direccion del profesor:");
            String profesor = scanner.nextLine();

            // Realizar la transacción y obtener el hash
            TransactionReceipt receipt = contract.agregarCurso(nombre, profesor).send();
            System.out.println("Curso creado con éxito. Hash de la transacción: " + receipt.getTransactionHash());
        } catch (Exception e) {
            System.out.println("Error al registrar el curso: " + e.getMessage());
        }
    }
```
registrarCurso facilita la creación de nuevos cursos dentro de la aplicación, asegurando que cada curso se registre adecuadamente en la blockchain a través de transacciones seguras y auditables. Esto permite un control efectivo sobre los cursos disponibles y los profesores asignados a ellos dentro del sistema. 

- Entrada de Datos del Curso:

   -El usuario es solicitado a ingresar el nombre del curso y la dirección del profesor encargado. Estos datos se recopilan utilizando un objeto Scanner pasado como argumento al método.
  
- Interacción y Transacción con el Contrato Inteligente:

   - Utiliza la instancia del contrato inteligente Contrato_sol_DAppEducativa para llamar al método agregarCurso.
   - El método agregarCurso del contrato inteligente recibe dos parámetros: el nombre del curso y la dirección del profesor.
   - Se ejecuta la transacción en la red Ethereum usando .send(), lo que envía la solicitud de creación de curso al contrato inteligente.
   - Al completar la transacción, se obtiene un objeto TransactionReceipt, que contiene detalles de la transacción.
   - Imprime por pantalla el hash de la operacion.
 
```java
private static void matricularse(Scanner scanner) throws Exception {
    	try {
            System.out.println("Ingrese su dirección:");
            String alumnoDireccion = scanner.nextLine();
            System.out.println("Introduce la clave clave privada");
            String privateKey = scanner.nextLine();
            credentials = Credentials.create(privateKey);
            contract = Contrato_sol_DAppEducativa.load(
                direccionContrato, 
                web3j, 
                credentials, 
                new DefaultGasProvider()
            );

            // Obtener y mostrar la lista de cursos disponibles
            List<String> nombresCursos = contract.obtenerNombresCursos().send();
            System.out.println("Cursos disponibles:");
            for (int i = 0; i < nombresCursos.size(); i++) {
                System.out.println((i + 1) + ". " + nombresCursos.get(i));
            }

            // Pedir al usuario que seleccione un curso
            System.out.println("Seleccione un curso por número:");
            int cursoSeleccionado = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (cursoSeleccionado > 0 && cursoSeleccionado <= nombresCursos.size()) {
                String curso = nombresCursos.get(cursoSeleccionado - 1);

                // Pedir los detalles del usuario
                System.out.println("Ingrese su nombre:");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese su Gmail:");
                String email = scanner.nextLine();
                System.out.println("Ingrese su clave:");
                String pass = scanner.nextLine();

                // Registrar al usuario en el contrato
                TransactionReceipt receipt = contract.registrarUsuario(alumnoDireccion, email, pass, curso, nombre).send();
                System.out.println("Usuario matriculado con éxito. Hash de la transacción: " + receipt.getTransactionHash());
            } else {
                System.out.println("Selección de curso no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error al matricularse: " + e.getMessage());
        }
    }
```
La función matricularse permite a un alumno registrarse y matricularse en un curso disponible a través del contrato inteligente. 

- Autenticación y Carga del Contrato Inteligente:

   - El proceso comienza solicitando al alumno que ingrese su dirección y clave privada. Estos datos son esenciales para autenticar al usuario y permitir que realice transacciones en la blockchain.
   - Con la clave privada proporcionada, se crean las credenciales del usuario utilizando Credentials.create(privateKey).
   - A continuación, el contrato Contrato_sol_DAppEducativa se carga utilizando la dirección del contrato, el objeto Web3j, las credenciales del alumno, y un proveedor de gas por defecto (DefaultGasProvider). Esto establece una conexión con el contrato inteligente desplegado, permitiendo que el alumno interactúe con él.
     
- Entrada de Datos del Alumno:

   - Selección de Curso:
      - Se llama al método obtenerNombresCursos del contrato inteligente para obtener una lista de todos los cursos disponibles, que se muestran al usuario en una lista numerada.
      - El alumno selecciona un curso ingresando el número correspondiente, y el programa verifica que la selección sea válida. Si la selección es válida, se extrae el nombre del curso de la lista.
   - Detalles del Alumno:
      - Se solicita al usuario que ingrese su nombre, correo electrónico (Gmail) y una clave personal. Estos datos son necesarios para registrar al usuario en el contrato inteligente.
        
- Registrar al Usuario:

   - Se llama al método registrarUsuario del contrato inteligente para registrar al alumno, proporcionando su dirección, email, clave, curso seleccionado y nombre.
   - La transacción resultante se envía a la blockchain, y se recibe un TransactionReceipt, que incluye el hash de la transacción.
 
```java
private static void matricularEnOtroCurso(Scanner scanner) {
        // Verificar si el contrato está inicializado
        if (contract == null) {
            System.out.println("El contrato no está inicializado.");
            return;
        }

        try {
            // Verificar que la dirección no esté vacía
            if (direccionUsuario.isEmpty()) {
                System.out.println("La dirección no puede estar vacía.");
                return;
            }

            // Verificar si el usuario está registrado
            Tuple7<String, byte[], String, String, String, Boolean, String> usuario = contract.usuarios(direccionUsuario).send();
            boolean registrado = usuario.component4() != null;  // Obtener el estado de registro (booleano)

            if (registrado) {
                // Obtener y mostrar la lista de cursos disponibles
                List<String> nombresCursos = contract.obtenerNombresCursos().send();
                System.out.println("Cursos disponibles:");
                for (int i = 0; i < nombresCursos.size(); i++) {
                    System.out.println((i + 1) + ". " + nombresCursos.get(i));
                }

                // Pedir al usuario que seleccione un curso
                System.out.println("Seleccione un curso por número:");
                if (scanner.hasNextInt()) {
                    int cursoSeleccionado = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    if (cursoSeleccionado > 0 && cursoSeleccionado <= nombresCursos.size()) {
                        String curso = nombresCursos.get(cursoSeleccionado - 1);

                        // Matricular al usuario en el nuevo curso
                        TransactionReceipt receipt = contract.matricularseEnOtroCurso(direccionUsuario, curso).send();
                        System.out.println("Usuario matriculado con éxito en el curso " + curso + ". Hash de la transacción: " + receipt.getTransactionHash());
                    } else {
                        System.out.println("Selección de curso no válida.");
                    }
                } else {
                    System.out.println("Entrada no válida. Debe ingresar un número.");
                    scanner.next(); // Consumir la entrada no válida
                }
            } else {
                System.out.println("La dirección ingresada no está registrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al matricular en otro curso: " + e.getMessage());
        }
    }
```

La función matricularEnOtroCurso permite que un usuario registrado en el sistema se matricule en un curso adicional, verificando previamente que esté registrado y que el contrato esté correctamente inicializado. A continuación se detalla el proceso:

- Inicialización del Contrato y Validación de Dirección:

   - La función comienza verificando si el contrato Contrato_sol_DAppEducativa está inicializado. Si no lo está, muestra un mensaje de error y termina la ejecución. Esta verificación asegura que cualquier operación sobre el contrato no cause un fallo por referencia nula.
   - Luego, se verifica que la variable direccionUsuario no esté vacía. Esta variable debe contener la dirección del usuario que se desea matricular. Si está vacía, se muestra un mensaje de error indicando que no se puede proceder con una dirección vacía.

- Verificación del Registro del Usuario:

   -Utilizando la función usuarios del contrato, se obtienen los detalles del usuario asociados a direccionUsuario. La función devuelve una tupla de siete componentes que incluye información sobre el usuario.
   - Se verifica el estado de registro del usuario comprobando si el campo de registro (component4 de la tupla) no es nulo. Si el usuario está registrado, el flujo continúa; si no, se informa al usuario que su dirección no está registrada y se termina el proceso.
     
- Selección de Curso y Matrícula:

   - Se llama al método obtenerNombresCursos del contrato para obtener una lista de los cursos disponibles. Esta lista se imprime para que el usuario pueda seleccionar un curso en el que desea matricularse.
   - Se solicita al usuario que ingrese el número correspondiente al curso deseado. El programa verifica que la entrada sea un número válido y que corresponda a un curso existente.
   - Si la selección es válida, el usuario es matriculado en el nuevo curso mediante el método matricularseEnOtroCurso del contrato. Se envía una transacción a la blockchain, y se muestra el hash de la transacción para confirmar el éxito del registro.
     
#### Calls funciones de lectura.

```java
private static void obtenerProfesores() {
        try {
            // Obtenemos la tupla de cuentas y nombres de profesores del contrato
            Tuple2<List<String>, List<String>> profesoresTuple = contract.obtenerCuentasProfesores().send();
            List<String> direccionesProfesores = profesoresTuple.component1();
            List<String> nombresProfesores = profesoresTuple.component2();

            System.out.println("Profesores registrados:");
            for (int i = 0; i < direccionesProfesores.size(); i++) {
                String direccion = direccionesProfesores.get(i);
                String nombre = nombresProfesores.get(i);
                System.out.println("Dirección: " + direccion + ", Nombre: " + nombre);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los profesores: " + e.getMessage());
        }
    }
```
La función obtenerProfesores() es una función que permite recuperar y mostrar la lista de profesores registrados en el contrato inteligente.

- Obtención de datos:

   - Se realiza una llamada al contrato inteligente para obtener una tupla (Tuple2) que contiene dos listas:
      - direccionesProfesores: Una lista de direcciones de los profesores registrados.
      - nombresProfesores: Una lista de nombres correspondientes a esas direcciones.
        
- Mostrar información:

   - Se recorre la lista de direcciones de profesores (direccionesProfesores) y, para cada dirección, se muestra tanto la dirección como el nombre del profesor en la consola.

```java
private static void obtenerUsuariosPorCurso(Scanner scanner) {
        try {
            // Obtener y mostrar la lista de cursos
            List<String> nombresCursos = contract.obtenerNombresCursos().send();
            System.out.println("Cursos disponibles:");
            for (int i = 0; i < nombresCursos.size(); i++) {
                System.out.println((i + 1) + ". " + nombresCursos.get(i));
            }

            // Pedir al usuario que seleccione un curso
            System.out.println("Seleccione un curso por número:");
            int cursoSeleccionado = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (cursoSeleccionado > 0 && cursoSeleccionado <= nombresCursos.size()) {
                String nombreCurso = nombresCursos.get(cursoSeleccionado - 1);

                // Obtener los usuarios matriculados en el curso seleccionado
                List<String> alumnos = contract.obtenerUsuariosPorCurso(nombreCurso).send();
                System.out.println("Alumnos matriculados en el curso " + nombreCurso + ":");

                // Mostrar los detalles de cada alumno
                for (String alumno : alumnos) {
                    Tuple7<String, byte[], String, String, String, Boolean, String> userTuple = contract.usuarios(alumno).send();
                    String nombre = userTuple.component1(); // Asumiendo que el nombre es el primer componente
                    System.out.println("Dirección: " + alumno + ", Nombre: " + nombre);
                }
            } else {
                System.out.println("Selección no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los alumnos por curso: " + e.getMessage());
        }
    }
```

La función obtenerUsuariosPorCurso(Scanner scanner) permite al usuario listar todos los estudiantes matriculados en un curso específico.

- Obtención de la lista de cursos:

   - La función llama al contrato inteligente para obtener una lista de nombres de cursos disponibles mediante obtenerNombresCursos().
   - Luego, se muestran estos cursos al usuario en la consola.
 
- Selección del curso por parte del usuario:

   - Se solicita al usuario que seleccione un curso ingresando el número correspondiente.
   - Se valida que el número ingresado esté dentro del rango válido de cursos.
     
- Recuperación de usuarios matriculados:

   - Una vez seleccionado el curso, se llama a la función obtenerUsuariosPorCurso(nombreCurso) del contrato inteligente para obtener las direcciones de los usuarios matriculados en ese curso.
   - Por cada dirección de usuario obtenida, se recuperan los detalles del usuario, específicamente el nombre.
     
- Mostrar detalles de los usuarios:

   - La dirección y el nombre de cada usuario matriculado en el curso seleccionado se muestran en la consola.

```java
 private static void listarCursosDeAlumno(Scanner scanner) {
        // Listar cursos en los que el alumno está matriculado
        try {
            Tuple3<String, String, String> cursos = contract.obtenerCursosMatriculados(direccionUsuario).send();
            System.out.println("Cursos en los que el alumno está matriculado:");
            if (!cursos.component1().isEmpty()) {
                System.out.println(cursos.component1());
            }
            if (!cursos.component2().isEmpty()) {
                System.out.println(cursos.component2());
            }
            if (!cursos.component3().isEmpty()) {
                System.out.println(cursos.component3());
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los cursos del alumno: " + e.getMessage());
        }
    }
```
La función listarCursosDeAlumno(Scanner scanner) permite al alumno ver una lista de los cursos en los que está matriculado.

- Obtención de cursos matriculados:

   - La función utiliza la dirección del usuario almacenada en direccionUsuario para llamar a la función obtenerCursosMatriculados(direccionUsuario) del contrato inteligente.
   - Esta llamada devuelve una tupla (Tuple3) con hasta tres cursos en los que el alumno está matriculado.
     
- Visualización de los cursos:

   - La función verifica cada componente de la tupla para comprobar si no están vacíos y, si contienen un curso, los imprime en la consola.
   - Cada componente de la tupla representa un curso en el que el alumno está matriculado, y si un componente está vacío, significa que no hay un curso asociado en esa posición.

#### Manejo de modos.

```java
private static void manejarModoAdmin(Scanner scanner) throws Exception {
        // Manejo del modo Admin
    	try {
            System.out.println("Introduce la clave privada del dueño del contrato");
            String privateKeyAdmin = scanner.nextLine();
            credentials = Credentials.create(privateKeyAdmin);
            contract = Contrato_sol_DAppEducativa.load(
                direccionContrato, 
                web3j, 
                credentials, 
                new DefaultGasProvider()
            );
        } catch (Exception e) {
            System.out.println("Error al cargar el contrato: " + e.getMessage());
            return;
        }

        while (true) {
            // Menú del modo Admin
            System.out.println("1. Agregar Profesor");
            System.out.println("2. Agregar Curso");
            System.out.println("3. Salir");
            int optionAdmin = scanner.nextInt();
            scanner.nextLine();

            switch (optionAdmin) {
                case 1:
                    registrarProfesor(scanner);
                    break;
                case 2:
                    registrarCurso(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del modo Admin");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
```
El método manejarModoAdmin permite al administrador gestionar profesores y cursos en el sistema, asegurándose de que las acciones sólo se puedan realizar con las credenciales adecuadas y que cualquier error en la carga del contrato sea manejado adecuadamente. 

- Inicialización del Contrato:

   - Entrada de Clave Privada: Solicita al usuario que introduzca la clave privada del administrador del contrato. Esta clave se utiliza para crear un objeto de Credentials, que es necesario para interactuar con la red Ethereum.
   - Cargar el Contrato: Utiliza la clave privada para cargar el contrato inteligente desde la dirección especificada (direccionContrato). Se crea una instancia del contrato Contrato_sol_DAppEducativa usando web3j, credentials, y un DefaultGasProvider para gestionar las transacciones.
     
- Manejo del Menú del Administrador:

   - Menú Principal: Presenta un menú con tres opciones:
   - Agregar Profesor: Llama a registrarProfesor, que es la función que maneja la lógica para registrar un nuevo profesor.
   - Agregar Curso: Llama a registrarCurso, que maneja la lógica para registrar un nuevo curso en el contrato inteligente.
   - Salir: Termina el modo administrador y vuelve al menú principal de la aplicación.
   - Opción no válida: Si se ingresa una opción no válida, el programa informa al usuario y reinicia el menú del modo administrador.

```java
private static void manejarModoProfesor(Scanner scanner) throws Exception {
        // Manejo del modo Profesor
    	try {
            System.out.println("Introduce una clave privada de profesor");
            String privateKeyProfesor = scanner.nextLine();
            credentials = Credentials.create(privateKeyProfesor);
            contract = Contrato_sol_DAppEducativa.load(
                direccionContrato, 
                web3j, 
                credentials, 
                new DefaultGasProvider()
            );
        } catch (Exception e) {
            System.out.println("Error al cargar el contrato: " + e.getMessage());
            return;
        }

        while (true) {
            // Menú del modo Profesor
            System.out.println("1. Mostrar usuarios por curso");
            System.out.println("2. Mostrar todos los profesores");
            System.out.println("3. Salir");
            int optionProfesor = scanner.nextInt();
            scanner.nextLine();

            switch (optionProfesor) {
                case 1:
                    obtenerUsuariosPorCurso(scanner);
                    break;
                case 2:
                    obtenerProfesores();
                    break;
                case 3:
                    System.out.println("Saliendo del modo Profesor");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
```

el método manejarModoProfesor permite a los profesores interactuar con el contrato inteligente para obtener información relevante, como los usuarios matriculados en sus cursos o los datos de otros profesores registrados, asegurando que estas operaciones solo se realicen con las credenciales correctas. 

- Inicialización del Contrato para el Profesor:

   - Entrada de Clave Privada: Solicita al usuario que introduzca la clave privada del profesor. Esta clave se utiliza para crear un objeto Credentials específico para el profesor, que es necesario para realizar transacciones y leer datos de la red Ethereum.
   - Cargar el Contrato: Utiliza las credenciales del profesor para cargar el contrato inteligente desde la dirección especificada (direccionContrato). Si ocurre un error al cargar el contrato, se captura y muestra un mensaje de error, y la función termina.
     
- Manejo del Menú del Profesor:

   - Menú Principal: Presenta un menú con tres opciones:
Mostrar Usuarios por Curso: Llama a la función obtenerUsuariosPorCurso, que permite al profesor ver los usuarios matriculados en un curso específico.
   - Mostrar Todos los Profesores: Llama a la función obtenerProfesores, que muestra una lista de todos los profesores registrados en el sistema.
   - Salir: Termina el modo profesor y regresa al menú principal de la aplicación.
   - Opción no válida: Si se ingresa una opción no válida, el programa informa al usuario y reinicia el menú del modo profesor.
 

```java
private static void manejarModoAlumno(Scanner scanner) throws Exception {
        // Manejo del modo Alumno
        while (true) {
            System.out.println("1. Matricularse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            int optionAlumno = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (optionAlumno) {
                case 1:
                    matricularse(scanner);
                    break;
                case 2:
                	try {
                        System.out.println("Introduce tu clave privada");
                        String privateKeyAlum = scanner.nextLine();
                        credentials = Credentials.create(privateKeyAlum);
                        contract = Contrato_sol_DAppEducativa.load(
                            direccionContrato, 
                            web3j, 
                            credentials, 
                            new DefaultGasProvider()
                        );
                    } catch (Exception e) {
                        System.out.println("Error al cargar el contrato: " + e.getMessage());
                        break;
                    }
                    direccionUsuario = iniciarSesion(scanner);

                    if (direccionUsuario != null) {
                        manejarSesionAlumno(scanner);
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del modo Alumno");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
```
manejarModoAlumno permite a los alumnos interactuar con el contrato inteligente para matricularse, iniciar sesión y realizar otras acciones específicas, asegurando que solo puedan hacerlo aquellos con las credenciales correctas.

- Menú del Alumno:

   - Opciones Disponibles: Al ingresar al modo Alumno, se presenta un menú con tres opciones:
   - Matricularse: Permite al alumno registrarse en el sistema, llamando a la función matricularse.
   - Iniciar Sesión: Permite al alumno iniciar sesión en el sistema, utilizando su clave privada para interactuar con el contrato inteligente.
   - Salir: Sale del modo Alumno y regresa al menú principal de la aplicación. 

- Manejo de la Opción de Iniciar Sesión:

   - Solicitar Clave Privada: Si el alumno elige iniciar sesión, se le solicita que introduzca su clave privada. Esta clave se utiliza para crear un objeto Credentials para el alumno.
   - Cargar el Contrato: Con las credenciales del alumno, se carga el contrato inteligente utilizando la dirección previamente definida (direccionContrato). Si ocurre un error al cargar el contrato, se muestra un mensaje de error y se vuelve al menú del alumno.
   - Iniciar Sesión: Si el contrato se carga correctamente, se llama a la función iniciarSesion(scanner) para verificar las credenciales del alumno. Si la verificación es exitosa, se procede a manejar la sesión del alumno a través del método manejarSesionAlumno.

```java
private static void manejarSesionAlumno(Scanner scanner) throws Exception {
        // Menú de acciones para el alumno después de iniciar sesión
        boolean sessionActive = true;

        while (sessionActive) {
            System.out.println("1. Matricularse en otro curso");
            System.out.println("2. Listar cursos matriculados");
            System.out.println("3. Cerrar sesión");

            int optionAlumnoLog = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (optionAlumnoLog) {
                case 1:
                    matricularEnOtroCurso(scanner);
                    break;
                case 2:
                    listarCursosDeAlumno(scanner);
                    break;
                case 3:
                    System.out.println("Cerrando sesión...");
                    sessionActive = false; // Salir del bucle de sesión
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
```
manejarSesionAlumno proporciona un entorno controlado donde el alumno puede realizar acciones específicas relacionadas con su perfil académico después de haber iniciado sesión correctamente. 

- Menú de Sesión del Alumno:

   - Opciones Disponibles: Una vez que el alumno ha iniciado sesión, se le presenta un menú con tres opciones principales:
   - Matricularse en otro curso: Permite al alumno inscribirse en un curso adicional, llamando a la función matricularEnOtroCurso.
   - Listar cursos matriculados: Muestra al alumno todos los cursos en los que está actualmente matriculado, llamando a la función listarCursosDeAlumno.
   - Cerrar sesión: Finaliza la sesión del alumno y lo regresa al menú anterior.

#### Clase Main. 

```java
public class Main {
    private static final String direccionContrato = "0x6974d707e5F278CC766FAF6fE8C35f24B1cDb0CC"; // Dirección del contrato desplegado
    private static Web3j web3j;
    private static Credentials credentials;
    private static Contrato_sol_DAppEducativa contract;
    private static String direccionUsuario; // Variable global para almacenar la dirección del usuario

    public static void main(String[] args) throws Exception {
        // Conectar a la red Ethereum
    	try {
            web3j = Web3j.build(new HttpService("http://localhost:7545"));
        } catch (Exception e) {
            System.out.println("Error al conectar con la red Ethereum: " + e.getMessage());
            return;
        } 
```

Este fragmento establece la base para la interacción entre una aplicación Java y un contrato inteligente desplegado en la blockchain de Ethereum. La clase Main es la encargada de gestionar toda la lógica de la aplicación.

En primer lugar, se define una constante direccionContrato que almacena la dirección del contrato inteligente desplegado en la red Ethereum. Esta dirección es crucial porque permite a la aplicación Java identificar y comunicarse con el contrato específico que se desea manejar.

Luego, se definen algunas variables clave:

- web3j es la instancia de Web3j, una librería que permite la comunicación entre la aplicación Java y la red Ethereum.
- credentials se utilizará para gestionar las credenciales del usuario que interactuará con el contrato, permitiendo la firma de transacciones.
- contract es una instancia generada a partir del ABI del contrato, que facilitará la llamada a sus funciones directamente desde Java.
- direccionUsuario es una variable global que se utilizará para almacenar la dirección del usuario actual que interactúa con la aplicación.

El método main es donde se inicia la conexión con la red Ethereum. Se utiliza la librería Web3j para conectarse a un nodo Ethereum, en este caso un nodo local que está funcionando en http://localhost:7545, que es la URL del nodo desplegado en Ganache. 

Si ocurre un error durante el proceso de conexión, como que el nodo no esté disponible o que la URL esté mal configurada, se captura la excepción y se muestra un mensaje de error en la consola, terminando la ejecución del programa. Este manejo de errores es importante para evitar que la aplicación falle inesperadamente sin dar información al usuario sobre lo que ha sucedido. 

```java
while (true) {
            // Menú principal
            System.out.println("Seleccione una opción:");
            System.out.println("1. Modo Admin");
            System.out.println("2. Modo Profesor");
            System.out.println("3. Modo Alumno");
            System.out.println("4. Salir");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    manejarModoAdmin(scanner);
                    break;
                case 2:
                    manejarModoProfesor(scanner);
                    break;
                case 3:
                    manejarModoAlumno(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo de la aplicación");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
```

Este fragmento de código implementa un bucle principal (while (true)) que mantiene la aplicación en ejecución hasta que el usuario decida salir. Aquí se presenta un menú principal que ofrece al usuario cuatro opciones:

- Modo Admin: Permite al usuario entrar en el modo administrador, donde se pueden realizar funciones específicas para la gestión del sistema.
- Modo Profesor: Permite al usuario acceder a funciones reservadas para los profesores.
- Modo Alumno: Permite al usuario interactuar con las funciones diseñadas para los estudiantes.
- Salir: Termina la ejecución de la aplicación.
  
Dentro del bucle, el usuario introduce su elección (option), y el programa utiliza una estructura switch para llamar a la función correspondiente dependiendo de la opción seleccionada. Las funciones manejarModoAdmin, manejarModoProfesor y manejarModoAlumno gestionan las respectivas lógicas de negocio para cada uno de los modos.




  










 


































  



 









