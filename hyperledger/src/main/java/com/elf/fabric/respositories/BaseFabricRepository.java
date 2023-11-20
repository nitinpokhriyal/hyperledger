package com.elf.fabric.respositories;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.elf.fabric.model.Car;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.springframework.stereotype.Repository;


@Repository
public class BaseFabricRepository {
	
	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}
	static Map<String,Contract> contractMap=new HashMap<String,Contract>();
	
	public String getAllCar() throws Exception{
		Contract contract=getContract("mychannel","fabcar");
		String carQueryResult=null;
		byte[] result;
		result = contract.evaluateTransaction("queryAllCars");
		carQueryResult=new String(result);
		return carQueryResult;
	}

	
	public String createCar(String carName,String make,String model,String color,String owner) throws Exception{
		Contract contract=getContract("mychannel","fabcar");
		byte[] result=contract.submitTransaction("createCar", carName, make, model, color, owner);
		//System.out.println("queriing cars"+ new String(contract.evaluateTransaction("queryCar", carName)));
		return new String(result);
	}
	
	public String getCarByKey(String carName) throws Exception{
		Contract contract=getContract("mychannel","fabcar");
		byte[]  result=contract.evaluateTransaction("queryCar", carName);
		
		return new String(result);
	}
	
	public String updateCarOwner(String key,String owner) throws Exception{
		Contract contract=getContract("mychannel","fabcar");
		byte[]  result=contract.submitTransaction("changeCarOwner", key, owner);
		
		return new String(result);
	}
	

	
	public String createNewCar(Car car) {
		return null;
	}

	private Contract getContract(String channelName,String contractName) throws Exception{
		if(contractMap.get(contractName)!=null){
			System.out.print("using existing object.......");
			return contractMap.get(contractName);
		}
		System.out.print("getting new  contract.......");
		Path walletPath = Paths.get("../../../fabricpoc/fabric-samples/fabcar/hyperledger/hyperledger/wallet");
		Wallet wallet = Wallets.newFileSystemWallet(walletPath);
		System.out.print("walletPath :" +walletPath.toAbsolutePath());
		// load a CCP
		Path networkConfigPath = Paths.get("../../../fabricpoc/fabric-samples/","test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");
		System.out.println("networkConfigPath :" +networkConfigPath.toAbsolutePath());
		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);

		Contract contract=null;
		// create a gateway connection
		try {
				Gateway gateway = builder.connect();
			// get the network and contract
			  Network network = gateway.getNetwork(channelName);
			  contract = network.getContract(contractName);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return contract;
	}
	
	//private String prettyJson(final byte[] json) {
//		return new String(json, StandardCharsets.UTF_8);
//	}

	
}
