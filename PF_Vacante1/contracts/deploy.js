const Web3 = require('web3');
const fs = require('fs');
const path = require('path');
const readlineSync = require('readline-sync');
const fetch = require('node-fetch');


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

