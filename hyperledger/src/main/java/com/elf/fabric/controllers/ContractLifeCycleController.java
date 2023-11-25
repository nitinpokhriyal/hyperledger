package com.elf.fabric.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class ContractLifeCycleController {
	
	private static final Logger logger = LoggerFactory.getLogger(ContractLifeCycleController.class);

	
	@GetMapping(path = {"/packageContract"})
	public void packageChainCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String contractName) {
		
	}
	@GetMapping(path = {"/installContract"})
	public void installChainCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String contractName) {
		
	}
	
	@GetMapping(path = {"/approveContract"})
	public void approveChainCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String contractName) {
		
	}
	@GetMapping(path = {"/commitContract"})
	public void commitChainCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String contractName) {
		
	}
	
	@GetMapping(path = {"/initContract"})
	public void initChainCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String contractName) {
		
	}
	
	@GetMapping(path = {"/queryContract"})
	public void queryChainCode(HttpServletRequest request,HttpServletResponse response) {
		String packageId="realestatetransfer:03e82c351ffa6998fd7e51c14f1d3ebf285b8fadb64ea81187faec31af330707";
		queryPackage("localhost", "7050", "realestatetransfer", "HomeTransfer",
			  "1.0",
			  packageId,
			  "1", "lifecycle_setup_Channel_Commit.sh"
			  ,"localhost","7051","localhost","9051","lifecycle_setup_Org1.sh",
			  "lifecycle_setup_Org2.sh");
	}
	
	 public  void packageChainCode(String packageName){
        logger.info("Started network");
        try {
        	File dir=new File("/Users/npokhriy/Documents/GitHub/hyperledger/hyperledger/src/main/resources/");
        	System.out.println(dir.getAbsolutePath());
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("sh contractpackage.sh "+packageName,null,dir);
           // process.wait(20000);
            String result = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);
            logger.info("result "+ result);
            result = IOUtils.toString(process.getErrorStream(), StandardCharsets.UTF_8);
            if(result.length() >0) logger.error("error "+ result);
            logger.info("running file completed");
		}  catch (IOException e) {
			  logger.error("IO Exception while running startnetwork: ",e); }
		catch(Exception e){
            logger.error("Exception while running startnetwork: ",e);
        }
    }
 
	
	 public  String installPackage(String orgName,String packageName,String portNumber,String envFileName){
        logger.info("installing package");
        try {
        	File dir=new File("src/main/resources/");
        	System.out.println(dir.getAbsolutePath());
            Runtime runtime = Runtime.getRuntime();
            String packageId=null;
            Process process = runtime.exec("sh contractinstall.sh "+packageName +" " +orgName +" " +portNumber +" "+envFileName,null,dir);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
                String s;
                while ((s = reader.readLine()) != null) {
                  System.out.println("Script output: " + s);
                  if(s.indexOf("Package ID: ")>-1){
                	
                    packageId= s.substring(s.indexOf("Package ID: ")+12,s.indexOf(","));
                  }
                }
			
            logger.info("running file completed and got packageId " +packageId);
          return  packageId;
		}  catch (IOException e) {
			  logger.error("IO Exception while running startnetwork: ",e); }
		catch(Exception e){
            logger.error("Exception while running startnetwork: ",e);
        }
        return null;
    }
	 
	 
	 public  void approvePackage(String hostName,String portNumber,String channelName,String contractName,String version,String packageId,
			 String sequence,String envFile,String queryPort){
	        logger.info("installing package");
	        try {
	        	
	        	//write a method to verify packageID
	        	File dir=new File("src/main/resources/");
	        	System.out.println(dir.getAbsolutePath());
	            Runtime runtime = Runtime.getRuntime();
	           
	            Process process = runtime.exec("sh contractapprove.sh   " +hostName + " " +portNumber +" "
	            		+channelName +" " + contractName + " " +version + " " +packageId +" " + sequence +" " +envFile +" " + 
	            		queryPort,null,dir);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    process.getInputStream()));
	                String s;
	                while ((s = reader.readLine()) != null) {
	                  System.out.println("Script output: " + s);
	                  if(s.indexOf("Package ID: ")>-1){
	                	
	                    packageId= s.substring(s.indexOf("Package ID: ")+12,s.indexOf(","));
	                  }
	                }
				
	            logger.info("running file completed and got packageId " +packageId);
			}  catch (IOException e) {
				  logger.error("IO Exception while running startnetwork: ",e); }
			catch(Exception e){
	            logger.error("Exception while running startnetwork: ",e);
	        }
	    }

	 
	 
	 public  void commitPackage(String hostName,String portNumber,String channelName,String contractName,String version,String packageId,
			 String sequence,String envFile,String approverHost1,String approverPort1,String approverHost2,String approverPort2,
			 String orgEnvFile,String org2EnvFile){
	        logger.info("installing package");
	        try {
	        	
	        	//write a method to verify packageID
	        	File dir=new File("src/main/resources/");
	        	System.out.println(dir.getAbsolutePath());
	            Runtime runtime = Runtime.getRuntime();
	           
	            Process process = runtime.exec("sh contractcommit.sh   " +hostName + " " +portNumber +" "
	            		+channelName +" " + contractName + " " +version + " " +packageId +" " + sequence +" " +envFile
	            		+" " + approverHost1 +" " +approverPort1 + " "+ approverHost2 +" " +approverPort2  
	            		+" " +orgEnvFile +" "+ org2EnvFile,null,dir);
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    process.getInputStream()));
	                String s;
	                while ((s = reader.readLine()) != null) {
	                  System.out.println("Script output: " + s);
	                  
	                }
				
	            logger.info("running file completed and got packageId " +packageId);
			}  catch (IOException e) {
				  logger.error("IO Exception while running startnetwork: ",e); }
			catch(Exception e){
	            logger.error("Exception while running startnetwork: ",e);
	        }
	    }
	 
	 public  void queryPackage(String hostName,String portNumber,String channelName,String contractName,String version,String packageId,
			 String sequence,String envFile,String approverHost1,String approverPort1,String approverHost2,String approverPort2,
			 String orgEnvFile,String org2EnvFile){
	        logger.info("installing package");
	        try {
	        	
	        	//write a method to verify packageID
	        	File dir=new File("/Users/npokhriy/fabricpoc/fabric-samples/fabcar/hyperledger/hyperledger/src/main/resources/");
	        	System.out.println(dir.getAbsolutePath());
	            Runtime runtime = Runtime.getRuntime();
	           
	            Process process = runtime.exec("sh contractquery.sh HomeTransfer Org1 7050 lifecycle_setup_Channel_commit.sh",null,dir);
	            String result = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);
	            logger.info("result "+ result);
	            result = IOUtils.toString(process.getErrorStream(), StandardCharsets.UTF_8);
	            if(result.length() >0) logger.error("error "+ result);
	            logger.info("running file completed");
			
				
	         //   logger.info("running file completed and got packageId " +packageId);
			}  catch (IOException e) {
				  logger.error("IO Exception while running startnetwork: ",e); }
			catch(Exception e){
	            logger.error("Exception while running startnetwork: ",e);
	        }
	    }
	 
	 
	 public static void main(String[] args) {
		 ContractLifeCycleController cont=new ContractLifeCycleController();
		// cont.packageChainCode("realestatetransfer");
		//String packageId=cont.installPackage("Org1", "realestatetransfer", "7051", "lifecycle_setup_Org1.sh");
		//System.out.println(packageId);
		 // cont.installPackage("Org2", "realestatetransfer", "9051", "lifecycle_setup_Org2.sh");
		 String packageId="realestatetransfer:03e82c351ffa6998fd7e51c14f1d3ebf285b8fadb64ea81187faec31af330707";
			
			 /* cont.approvePackage("localhost", "7050", "realestatetransfer",
			  "HomeTransfer", "1.0",packageId, "1", "lifecycle_setup_Org1.sh","7051");*/
			  
			/* cont.approvePackage("localhost", "7050", "realestatetransfer",
					  "HomeTransfer", "1.0",packageId, "1", "lifecycle_setup_Org2.sh","9051");*/
			  //cpmmit package 
			/*  cont.commitPackage("localhost", "7050",
			  "realestatetransfer", "HomeTransfer", "1.0", packageId, "1",
			  "lifecycle_setup_Channel_commit.sh"
			  ,"localhost","7051","localhost","9051","lifecycle_setup_Org1.sh",
			  "lifecycle_setup_Org2.sh");*/
			 
			
			  cont.queryPackage("localhost", "7050", "realestatetransfer", "HomeTransfer",
			  "1.0",
			  packageId,
			  "1", "lifecycle_setup_Channel_Commit.sh"
			  ,"localhost","7051","localhost","9051","lifecycle_setup_Org1.sh",
			  "lifecycle_setup_Org2.sh");
			  
		 
		  
		 //realestatetransfer_1:ab376bab99fb0d8b77de715df5029a8f3c45c5b137a7406a29ed8fb0e937b911
	 }
	
}
