#!/bin/bash

HOST_NAME=${1}
PORT_NUMBER=${2}
CHANNEL_NAME=${3}
CONTRACT_NAME=${4}
VERSION=${5}
PACKAGE_ID=${6}
SEQUENCE=${7}
ENV_FILE=${8}
QUERY_PORT=${9}

echo CONTRACT_NAME ${CONTRACT_NAME} , HOST_NAME ${HOST_NAME} , PORT_NUMBER ${PORT_NUMBER} ENV_FILE ${ENV_FILE}
export PATH=/usr/bin:$PATH:$HOME/.rd/bin:$HOME/fabricpoc/fabric-samples/test-network/../bin
echo "Script executed from: ${PWD}"
source ./${ENV_FILE}

cd /Users/npokhriy/fabricpoc/fabric-samples/test-network/
echo "Script executed from: ${PWD}"
echo peer lifecycle chaincode queryinstalled --peerAddresses localhost:${QUERY_PORT} --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:${QUERY_PORT} --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
echo now approving package
echo peer lifecycle chaincode approveformyorg -o ${HOST_NAME}:${PORT_NUMBER} --ordererTLSHostnameOverride orderer.example.com --tls --cafile $ORDERER_CA --channelID ${CHANNEL_NAME} --name ${CONTRACT_NAME} --version ${VERSION} --init-required --package-id ${PACKAGE_ID}  --sequence ${SEQUENCE}

peer lifecycle chaincode approveformyorg -o ${HOST_NAME}:${PORT_NUMBER} --ordererTLSHostnameOverride orderer.example.com --tls --cafile $ORDERER_CA --channelID ${CHANNEL_NAME} --name ${CONTRACT_NAME} --version ${VERSION} --init-required --package-id ${PACKAGE_ID}  --sequence ${SEQUENCE}

#peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls --cafile $ORDERER_CA --channelID realestatetransfer --name HomeTransfer --version 1.0 --init-required --package-id ${PACKAGE_ID}  --sequence 1 
echo approval complete

