#!/bin/bash

PACKAGE_NAME=${1}
ORG_NAME=${2}
PORT_NUMBER=${3}
ENV_FILE=${4}

echo PACKAGE_NAME ${PACKAGE_NAME} , ORG_NAME ${ORG_NAME} , PORT_NUMBER ${PORT_NUMBER} ENV_FILE ${ENV_FILE}
export PATH=/usr/bin:$PATH:$HOME/.rd/bin:$HOME/fabricpoc/fabric-samples/test-network/../bin
echo "Script executed from: ${PWD}"
source ./${ENV_FILE}
echo $CORE_PEER_TLS_ROOTCERT_FILE

cd /Users/npokhriy/fabricpoc/fabric-samples/test-network/
echo "Script executed from: ${PWD}"


echo PACKAGE_NAME $HOME/fabricpoc/fabric-samples/test-network/${PACKAGE_NAME}.tar.gz

echo now instaling package
echo peer lifecycle chaincode install ${PACKAGE_NAME}.tar.gz --peerAddresses localhost:${PORT_NUMBER} --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE  

peer lifecycle chaincode install ${PACKAGE_NAME}.tar.gz --peerAddresses localhost:${PORT_NUMBER} --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE  

echo query package

peer lifecycle chaincode queryinstalled --peerAddresses localhost:${PORT_NUMBER} --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE