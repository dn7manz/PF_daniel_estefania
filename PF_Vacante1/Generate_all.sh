#!/bin/bash

# Función para generar un par de claves RSA
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
}

# Generar docker-compose.yml
generate_docker_compose() {
cat > docker-compose.yml <<EOF
version: '3'

services:
  node_validator:
    user: root
    image: openethereum/openethereum:latest
    volumes:
      - node_validator:/root/.local/share/openethereum
      - ./genesis:/root/.local/share/openethereum/genesis
      - ./node_validator/keys:/root/.local/share/openethereum/keys
      - ./node_validator/keys/Proyecto_final/password.pwd:/root/.local/share/openethereum/keys/Proyecto_final/password.pwd:ro
      - ./node_validator/config:/root/.local/share/openethereum/config
    ports:
      - "30300:30300"  # Puerto de escucha de nodos
      - "8548:8545"
    environment:
      - NODE_TYPE=validator
    networks:
      ethereum_net:
        ipv4_address: 172.20.0.2
    command:
      --config /root/.local/share/openethereum/config/config.toml
      --chain /root/.local/share/openethereum/genesis/genesis.json
      --engine-signer 0x00000000000000001
  node_non_validator_1:
    user: root
    image: openethereum/openethereum:latest
    volumes:
      - node_non_validator_1:/root/.local/share/openethereum
      - ./genesis:/root/.local/share/openethereum/genesis
      - ./node_non_validator_1/keys:/root/.local/share/openethereum/keys
      - ./node_non_validator_1/keys/Proyecto_final/password.pwd:/root/.local/share/openethereum/keys/Proyecto_final/password.pwd:ro
      - ./node_non_validator_1/config:/root/.local/share/openethereum/config
    ports:
      - "30301:30300"  # Puerto de escucha de nodos
      - "8547:8545"
    environment:
      - NODE_TYPE=non_validator
    networks:
      ethereum_net:
        ipv4_address: 172.20.0.3
    command:
      --config /root/.local/share/openethereum/config/config.toml
      --chain /root/.local/share/openethereum/genesis/genesis.json
  node_non_validator_2:
    user: root
    image: openethereum/openethereum:latest
    volumes:
      - node_non_validator_2:/root/.local/share/openethereum
      - ./genesis:/root/.local/share/openethereum/genesis
      - ./node_non_validator_2/keys:/root/.local/share/openethereum/keys
      - ./node_non_validator_2/keys/Proyecto_final/password.pwd:/root/.local/share/openethereum/keys/Proyecto_final/password.pwd:ro
      - ./node_non_validator_2/config:/root/.local/share/openethereum/config
    ports:
      - "30302:30300"  # Puerto de escucha de nodos
      - "8546:8545"
    environment:
      - NODE_TYPE=non_validator
    networks:
      ethereum_net:
        ipv4_address: 172.20.0.4
    command:
      --config /root/.local/share/openethereum/config/config.toml
      --chain /root/.local/share/openethereum/genesis/genesis.json
  node_rpc:
    user: root
    image: openethereum/openethereum:latest
    volumes:
      - node_rpc:/root/.local/share/openethereum
      - ./genesis:/root/.local/share/openethereum/genesis
      - ./node_rpc/keys:/root/.local/share/openethereum/keys
      - ./node_rpc/keys/Proyecto_final/password.pwd:/root/.local/share/openethereum/keys/Proyecto_final/password.pwd:ro
      - ./node_rpc/config:/root/.local/share/openethereum/config
    ports:
      - "8545:8545"  # Puerto RPC
    environment:
      - NODE_TYPE=rpc
    networks:
      ethereum_net:
        ipv4_address: 172.20.0.5
    command:
      --config /root/.local/share/openethereum/config/config.toml
      --chain /root/.local/share/openethereum/genesis/genesis.json
volumes:
  node_validator:
  node_non_validator_1:
  node_non_validator_2:
  node_rpc:
networks:
  ethereum_net:
    driver: bridge
    ipam:
      config:
      - subnet: 172.20.0.0/16
EOF
}

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
EOF

}

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
password = ["/root/.local/share/openethereum/keys/password.pwd"]

EOF
}

generate_config_node_non_validator_2() {
cat > ./node_non_validator_2/config/config.toml <<EOF
[network]
port = 30303
discovery = true
nat = "extip:172.20.0.4"

[rpc]
interface = "0.0.0.0"
port = 8545
cors = ["*"]
apis = ["web3", "eth", "personal", "net"]

[account]
password = ["/root/.local/share/openethereum/keys/password.pwd"]

EOF
}

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


# Ejecutar funciones
generate_keys
generate_genesis
generate_config_node_validator
generate_config_node_non_validator_1
generate_config_node_non_validator_2
generate_config_node_rpc
generate_docker_compose


echo "Configuración completada. Se ha generado docker-compose.yml, los pares de claves y las configuraciones"

