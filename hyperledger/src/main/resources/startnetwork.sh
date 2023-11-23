#!/bin/bash

export PATH=/usr/bin:$PATH:$HOME/.rd/bin
CHANNEL_NAME=${1}
echo $PATH
echo - CHANNEL_NAME: ${CHANNEL_NAME}
cd /Users/npokhriy/fabricpoc/fabric-samples/test-network/
echo "Script executed from: ${PWD}"

./network.sh up createChannel -c ${CHANNEL_NAME} -ca -s couchdb

#./network.sh up

#echo $(docker run --rm hyperledger/fabric-tools:latest peer version | sed -ne 's/^ Version: //p')