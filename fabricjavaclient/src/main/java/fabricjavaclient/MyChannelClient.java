package fabricjavaclient;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;

public class MyChannelClient {
	
	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
		        //export PATH=${PWD}/../bin:${PWD}:$PATH
				System.setProperty("FABRIC_CFG_PATH","/Users/npokhriy/fabricpoc/fabric-samples/config/");
				System.setProperty("CORE_PEER_TLS_ENABLED","true");
		        System.setProperty("CORE_PEER_LOCALMSPID","Org1MSP");
		        System.setProperty("CORE_PEER_TLS_ROOTCERT_FILE_ORG1","/Users/npokhriy/fabricpoc/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt");
		        System.setProperty("CORE_PEER_TLS_ROOTCERT_FILE_ORG2","/Users/npokhriy/fabricpoc/fabric-samples/test-network/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt");
		        System.setProperty("CORE_PEER_MSPCONFIGPATH","/Users/npokhriy/fabricpoc/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp");
		        System.setProperty("CORE_PEER_ADDRESS","localhost:7051");
				System.setProperty("ORDERER_CA","/Users/npokhriy/fabricpoc/fabric-samples/test-network/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem");
				System.setProperty("FABRIC_CA_HOME","/Users/npokhriy/fabricpoc/fabric-samples/test-network/");
	}

	
	public static void main(String[] args) throws Exception {
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallets.newFileSystemWallet(walletPath);
		// load a CCP
		Path networkConfigPath = Paths.get("/Users", "npokhriy","fabricpoc","fabric-samples", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "appUser1").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("basic");

			byte[] result;

		//	contract.submitTransaction("addNewHome", "4", "Home4", "54678", "Grey", "78909");

		//	result = contract.evaluateTransaction("queryHomeById", "4");
	//		System.out.println(new String(result));

	//		contract.submitTransaction("changeHomeOwnership", "4", "Joe");

			result = contract.evaluateTransaction("GetAllAssets");
			System.out.println(new String(result));
		}
	}
	
}
