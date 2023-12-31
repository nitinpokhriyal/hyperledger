/*
SPDX-License-Identifier: Apache-2.0
*/

package com.elf.fabric.fabcar;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;

public class ClientApp {

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
		System.setProperty("CORE_PEER_MSPCONFIGPATH","/Users/npokhriy/fabricpoc/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp");
		System.setProperty("ORDERER_CA","/Users/npokhriy/fabricpoc/fabric-samples/test-network/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem");

	}

	public static void main(String[] args) throws Exception {
		
		// Load a file system based wallet for managing identities.
		String version = System.getProperty("java.version");
		System.out.println(version);
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallets.newFileSystemWallet(walletPath);
		// load a CCP
		Path networkConfigPath = Paths.get("..","..", "..", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("realestatetransfer");
			Contract contract = network.getContract("HomeTransfer");

			byte[] result;

			/*
			 * result = contract.evaluateTransaction("queryAllCars"); System.out.println(new
			 * String(result));
			 * 
			 * contract.submitTransaction("createCar", "CAR12", "VW11", "Polo11", "Grey11",
			 * "Mary11");
			 * 
			 * result = contract.evaluateTransaction("queryCar", "CAR10");
			 * System.out.println(new String(result));
			 * 
			 * contract.submitTransaction("changeCarOwner", "CAR10", "Archie");
			 * 
			 * result = contract.evaluateTransaction("queryCar", "CAR10");
			 * System.out.println(new String(result) +"________________");
			 * 
			 * result = contract.evaluateTransaction("queryAllCars"); System.out.println(new
			 * String(result));
			 */
			//contract.submitTransaction("addNewHome", "4", "Home4", "54678", "Grey", "78909");

			result = contract.evaluateTransaction("queryHomeById", "2");
			System.out.println(new String(result));
		}
	}

}
