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



