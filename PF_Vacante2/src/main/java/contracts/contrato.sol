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

    // Funciones de Escritura

    function agregarCurso(string memory _nombreCurso, address _profesor) public soloOwner {
        require(cursos[_nombreCurso].profesor == address(0), "Curso ya registrado");
        require(esProfesor[_profesor], "El profesor no esta registrado");

        cursos[_nombreCurso] = Curso({
            nombreCurso: _nombreCurso,
            profesor: _profesor
        });
        nombresCursos.push(_nombreCurso); // Agregar el nombre del curso a la lista
    }

    function agregarProfesor(address _cuenta, string memory _nombre) public soloOwner {
        require(!profesores[_cuenta].registrado, "Profesor ya registrado");

        profesores[_cuenta] = Profesor({
            nombre: _nombre,
            registrado: true
        });

        esProfesor[_cuenta] = true; // Marcar la cuenta como profesor
        cuentasProfesores.push(_cuenta);
    }

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

    // Funciones de Lectura

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
    }

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

    function esProfesorRegistrado(address _cuenta) public view returns (bool) {
        return esProfesor[_cuenta];
    }
}
