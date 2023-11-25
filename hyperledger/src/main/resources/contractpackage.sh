#!/bin/bash

PACKAGE_NAME=${1}



export PATH=/usr/bin:$PATH:$HOME/.rd/bin:$HOME/fabricpoc/fabric-samples/test-network/../bin
echo "Script executed from: ${PWD}"
source ./lifecycle_setup_Org1.sh

cd /Users/npokhriy/fabricpoc/fabric-samples/test-network/
echo "Script executed from: ${PWD}"

echo PACKAGE_NAME ${PACKAGE_NAME}
echo PACKAGE_NAME $HOME/fabricpoc/fabric-samples/test-network/${PACKAGE_NAME}.tar.gz
#peer lifecycle chaincode package $HOME/fabricpoc/fabric-samples/test-network/${PACKAGE_NAME}.tar.gz --path /Users/npokhriy/eclipse-workspace/realestatetransfer/build/install/realestatetransfer --lang java --label realestatetransfer_1
echo peer lifecycle chaincode package ${PACKAGE_NAME}.tar.gz --path $HOME/eclipse-workspace/realestatetransfer/build/install/realestatetransfer --lang java --label ${PACKAGE_NAME}
peer lifecycle chaincode package ${PACKAGE_NAME}.tar.gz --path $HOME/eclipse-workspace/realestatetransfer/build/install/realestatetransfer --lang java --label ${PACKAGE_NAME}

echo "packaging complete"