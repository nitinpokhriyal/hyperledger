#!/bin/bash

CONTRACT_NAME=${1}
ORG_NAME=${2}
PORT_NUMBER=${3}
ENV_FILE=${4}

echo CONTRACT_NAME ${CONTRACT_NAME} , ORG_NAME ${ORG_NAME} , PORT_NUMBER ${PORT_NUMBER} ENV_FILE ${ENV_FILE}
export PATH=/usr/bin:$PATH:$HOME/.rd/bin:$HOME/fabricpoc/fabric-samples/test-network/../bin
echo "Script executed from: ${PWD}"
source ./${ENV_FILE}
echo $CORE_PEER_TLS_ROOTCERT_FILE

cd /Users/npokhriy/fabricpoc/fabric-samples/test-network/
echo "Script executed from: ${PWD}"

source ./lifecycle_setup_Org1.sh
echo peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C realestatetransfer -n HomeTransfer --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --isInit -c '{"Args":[]}'

#peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C realestatetransfer -n HomeTransfer --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --isInit -c '{"Args":[]}'
#echo quering another object
#echo peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C realestatetransfer -n HomeTransfer --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 -c '{"Args":["addNewHome", "2", "SecondHome","1000","John","1000"]}'

#peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C realestatetransfer -n HomeTransfer --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 -c '{"Args":["addNewHome", "2", "SecondHome","1000","John","1000"]}'
#echo chaincode INSERT OBJ
echo peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C realestatetransfer  -n HomeTransfer --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 -c '{"Args":["queryHomeById", "2"]}'

peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C realestatetransfer  -n HomeTransfer --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 -c '{"Args":["queryHomeById", "2"]}'
echo chaincode query complete