
# Documentación Técnica

## Introducción

En este documento, se describen los procedimientos para realizar transacciones en una red blockchain, verificar su inclusión en bloques y comprobar los roles de los nodos. Además, se detallan los pasos para realizar votaciones utilizando contratos inteligentes y verificar los resultados.

## Realización de una Transacción

A continuación, se detalla cómo enviar una transacción utilizando el método `personal_sendTransaction` de la API JSON-RPC.

### Parámetros de la Transacción

- **jsonrpc**: Define la versión del protocolo JSON-RPC que se está utilizando.
- **method**: `personal_sendTransaction` - Especifica el método de la API que se está llamando.
- **params**: Lista de parámetros que se envían al método:
  - **from**: Dirección de la cuenta desde la cual se envía la transacción.
  - **to**: Dirección de la cuenta de destino.
  - **value**: Cantidad de Ether a enviar, expresada en Wei.
  - **gas**: Límite de gas para la transacción, que establece el máximo de unidades de gas que se puede gastar.
  - **gasPrice**: Precio del gas por unidad, especificado en Wei.
  - **data**: Datos adicionales a enviar, generalmente utilizados para interactuar con contratos inteligentes.
  - **password**: Contraseña de la cuenta remitente, utilizada para desbloquear la cuenta y firmar la transacción.
- **id**: Un identificador único para la solicitud, utilizado para hacer un seguimiento de la misma. Puede ser cualquier número o cadena.

### Verificación de la Transacción

Una vez ejecutado el comando, se obtiene el hash de la transacción como resultado. Para comprobar que esta transacción se incluyó en un bloque, es necesario revisar los registros del nodo desde el cual se realizó la operación para verificar si la transacción fue minada.

![Verificación de la Transacción](https://github.com/user-attachments/assets/b75f6d39-ad75-44ed-8c4c-d689d7d57632)

En la imagen se puede observar que el nodo ha firmado una transacción y que el hash coincide con el de la operación realizada. Además, en el siguiente bloque, se observa que hay 1 transacción (1 txs).

## Prueba de Roles de los Nodos

Para comprobar que cada nodo está realizando sus roles correctamente, se procederá a apagar el nodo validador y posteriormente realizar una transacción desde otro nodo para observar el comportamiento.

### Apagado del Nodo Validador

Lo primero será detener el nodo validador.

![Apagado del Nodo Validador](https://github.com/user-attachments/assets/1909b304-8068-4c3b-ac4b-8b1999f693f4)

Una vez que el nodo esté apagado, se ejecutará una transacción desde otro nodo para verificar el comportamiento de la red.

## Proceso de Votación

El siguiente paso es realizar una votación utilizando cuentas alojadas en diferentes nodos. Se muestra el proceso para cada nodo participante:

### Votación por Nodo

#### Nodo RPC

![Votación Nodo RPC - Parte 1](https://github.com/user-attachments/assets/5672bb3a-6b38-4993-86a6-a25e2ee67fa1)
![Votación Nodo RPC - Parte 2](https://github.com/user-attachments/assets/ef20264a-0117-4aac-8584-0af7dce01cbc)

La cuenta alojada en el nodo RPC ha votado por Daniel y Aaron satisfactoriamente.

#### Nodo No Validador 1

![Votación Nodo No Validador 1 - Parte 1](https://github.com/user-attachments/assets/cec2b1c3-ea88-4a8e-b586-2a3e556fd817)
![Votación Nodo No Validador 1 - Parte 2](https://github.com/user-attachments/assets/09c981d5-efd2-4e81-9ca4-c527bac5ec43)

La cuenta alojada en el nodo no validador 1 ha votado por Daniel y Sergio satisfactoriamente.

#### Nodo No Validador 2

![Votación Nodo No Validador 2 - Parte 1](https://github.com/user-attachments/assets/81029109-f9c4-46c6-a9d9-e822b3f2d0f1)
![Votación Nodo No Validador 2 - Parte 2](https://github.com/user-attachments/assets/9670e6ff-b45a-493e-88e1-b3760fe19234)

La cuenta alojada en el nodo no validador 2 ha votado por Daniel y Aaron satisfactoriamente.

#### Nodo Validador

![Votación Nodo Validador - Parte 1](https://github.com/user-attachments/assets/77b17f92-d249-481e-9dff-de8696acdd9c)
![Votación Nodo Validador - Parte 2](https://github.com/user-attachments/assets/90232154-56d3-4aee-bfeb-ec071d90ddd7)

La cuenta alojada en el nodo validador ha votado por Daniel y Sergio satisfactoriamente.

### Obtención de Resultados

Una vez que se han registrado los votos, el siguiente paso es calcular y obtener los resultados. Para ello, se invoca la función `getResults` del contrato inteligente. Esta función devuelve una lista de candidatos junto con el número de votos que cada uno ha recibido, permitiendo verificar de manera pública y transparente los resultados de la votación.

![Resultados de la Votación](https://github.com/user-attachments/assets/91e44d1a-741a-4492-8d7e-33b263a3601d)

El resultado de llamar a la función muestra por consola el recuento de todos los votos recogidos hasta el momento, determinando al ganador, que fue Daniel.

## Conclusiones

Este proceso asegura que cada voto es registrado y contabilizado de forma segura y precisa, ofreciendo una solución de votación digital confiable y accesible.
