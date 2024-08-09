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




























  



 









