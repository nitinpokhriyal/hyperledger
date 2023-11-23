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

@RestController
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
	
	 public static void packageChainCode(String channelName){
	        logger.info("Started network");
	        try {
	        	File dir=new File("src/main/resources/");
	        	System.out.println(dir.getAbsolutePath());
	            Runtime runtime = Runtime.getRuntime();
	            Process process = runtime.exec("sh contractapproval.sh "+channelName,null,dir);
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
	 
	
	 public static void main(String[] args){
	        logger.info("Started network");
	        try {
	        	File dir=new File("src/main/resources/");
	        	System.out.println(dir.getAbsolutePath());
	            Runtime runtime = Runtime.getRuntime();
	            String packageId=null;
	            Process process = runtime.exec("sh contractinstall.sh realestatetransfer",null,dir);
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
	 
	 
	
}
