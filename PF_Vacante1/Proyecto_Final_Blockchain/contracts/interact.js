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




