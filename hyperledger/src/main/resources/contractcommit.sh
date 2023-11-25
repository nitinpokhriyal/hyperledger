#!/bin/bash

HOST_NAME=${1}
PORT_NUMBER=${2}
CHANNEL_NAME=${3}
CONTRACT_NAME=${4}
VERSION=${5}
PACKAGE_ID=${6}
SEQUENCE=${7}
ENV_FILE=${8}
APPROVER_ORG1_HOST_NAME=${9}
APPROVER_ORG1_PORT_NUMBER=${10}
APPROVER_ORG2_HOST_NAME=${11}
APPROVER_ORG2_PORT_NUMBER=${12}
ORG1_ENV_FILE=${13}
ORG2_ENV_FILE=${14}

echo CONTRACT_NAME ${CONTRACT_NAME} , HOST_NAME ${HOST_NAME} , PORT_NUMBER ${PORT_NUMBER} ENV_FILE ${ENV_FILE} ORG1_ENV_FILE ${ORG1_ENV_FILE} ORG2_ENV_FILE ${ORG2_ENV_FILE} APPROVER_ORG1_HOST_NAME ${APPROVER_ORG1_HOST_NAME} APPROVER_ORG2_PORT_NUMBER ${APPROVER_ORG1_PORT_NUMBER} APPROVER_ORG2_HOST_NAME ${APPROVER_ORG2_HOST_NAME} APPROVER_ORG2_PORT_NUMBER ${APPROVER_ORG2_PORT_NUMBER}

export PATH=/usr/bin:$PATH:$HOME/.rd/bin:$HOME/fabricpoc/fabric-samples/test-network/../bin
echo "Script executed from: ${PWD}"

CURR_PATH=${PWD}

source ./${ORG1_ENV_FILE}
echo "Script executed from: ${PWD}"

echo now commit readiness package
echo peer lifecycle chaincode checkcommitreadiness --channelID ${CHANNEL_NAME} --name ${CONTRACT_NAME} --version ${VERSION} --sequence ${SEQUENCE} --output json --init-required

peer lifecycle chaincode checkcommitreadiness --channelID realestatetransfer --name HomeTransfer --version 1.0 --sequence 1 --output json --init-required
cd ${CURR_PATH}
echo "Script executed from: ${PWD}"
source ./${ORG2_ENV_FILE}
echo now commit readiness package
peer lifecycle chaincode checkcommitreadiness --channelID ${CHANNEL_NAME} --name ${CONTRACT_NAME} --version ${VERSION} --sequence ${SEQUENCE} --output json --init-required


source ./${ENV_FILE}
echo commiting package

echo peer lifecycle chaincode commit -o ${HOST_NAME}:${PORT_NUMBER} --ordererTLSHostnameOverride orderer.example.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA --channelID ${CHANNEL_NAME} --name ${CONTRACT_NAME} --peerAddresses ${APPROVER_ORG1_HOST_NAME}:${APPROVER_ORG1_PORT_NUMBER} --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses ${APPROVER_ORG2_HOST_NAME}:${APPROVER_ORG2_PORT_NUMBER} --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --version ${VERSION} --sequence ${SEQUENCE} --init-required

peer lifecycle chaincode commit -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA --channelID ${CHANNEL_NAME}  --name ${CONTRACT_NAME} --peerAddresses ${APPROVER_ORG1_HOST_NAME}:${APPROVER_ORG1_PORT_NUMBER} --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses ${APPROVER_ORG2_HOST_NAME}:${APPROVER_ORG2_PORT_NUMBER} --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --version ${VERSION} --sequence ${SEQUENCE} --init-required
echo commit check

echo peer lifecycle chaincode querycommitted --channelID ${CHANNEL_NAME} --name ${CONTRACT_NAME}

peer lifecycle chaincode querycommitted --channelID ${CHANNEL_NAME} --name ${CONTRACT_NAME}

echo commit complete

