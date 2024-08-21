import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.gas.DefaultGasProvider;

import java.util.Scanner;
import java.util.List;
import contracts.Contrato_sol_DAppEducativa;

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
        Scanner scanner = new Scanner(System.in); // Interacción con el usuario

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

    private static String iniciarSesion(Scanner scanner) {
        try {
            // Iniciar sesión de un alumno
            System.out.println("Ingrese su dirección:");
            String direccionUsuario = scanner.nextLine();
            System.out.println("Ingrese su Gmail:");
            String email = scanner.nextLine();
            System.out.println("Ingrese su contraseña:");
            String pass = scanner.nextLine();

            // Llamar al método iniciarSesion del contrato
            String resultado = contract.iniciarSesion(direccionUsuario, email, pass).send();

            if (resultado.equals("0x")) {
                System.out.println("Credenciales incorrectas o usuario no encontrado.");
                return null;
            } else {
                System.out.println("Inicio de sesión correcto");
                return direccionUsuario;
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            return null;
        }
    }

}
