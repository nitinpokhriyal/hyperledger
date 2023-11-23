#!/bin/bash

CONTRACT_NAME=${1}



export PATH=/usr/bin:$PATH:$HOME/.rd/bin:$HOME/fabricpoc/fabric-samples/test-network/../bin
echo "Script executed from: ${PWD}"
source ./lifecycle_setup_Org1.sh

cd /Users/npokhriy/fabricpoc/fabric-samples/test-network/
echo "Script executed from: ${PWD}"

echo CONTRACT_NAME ${CONTRACT_NAME}
echo PACKAGE_NAME $HOME/fabricpoc/fabric-samples/test-network/${CONTRACT_NAME}.tar.gz

echo now instaling package

#peer lifecycle chaincode install ${CONTRACT_NAME}.tar.gz --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE  

echo query package

peer lifecycle chaincode queryinstalled --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE

