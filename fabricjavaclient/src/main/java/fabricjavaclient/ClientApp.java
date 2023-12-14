package fabricjavaclient;

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
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallets.newFileSystemWallet(walletPath);
		// load a CCP
		Path networkConfigPath = Paths.get("/Users", "npokhriy","fabricpoc","fabric-samples", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "appUser1").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("samplechannel");
			Contract contract = network.getContract("HomeTransfer");

			byte[] result;

//			contract.submitTransaction("addNewHome", "4", "Home4", "54678", "Grey", "78909");

			result = contract.evaluateTransaction("queryHomeById", "4");
			System.out.println(new String(result));

//			contract.submitTransaction("changeHomeOwnership", "4", "Joe");

//			result = contract.evaluateTransaction("queryHomeById", "1");
//			System.out.println(new String(result));
		}
	}
	
}
