package com.elf.fabric.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NetworkController {

	private static final Logger logger = LoggerFactory.getLogger(NetworkController.class);
	
	 @GetMapping(path = {"/startNetwork"})
	    public ResponseEntity<?> startNetwork(HttpServletRequest httpRequest,HttpServletResponse httpResponse,
	    		@RequestParam(required = false) String channelName) throws Exception {
		 
		 start(channelName);
	   return new ResponseEntity(HttpStatus.OK);
	 }
	 
	 public static void main(String[] args) {
		 NetworkController controller =new NetworkController();
		 controller.start("realestatetransfer");
	 }
	 
	 public static void start(String channelName){
	        logger.info("Started network");
	        try {
	        	File dir=new File("src/main/resources/");
	        	System.out.println(dir.getAbsolutePath());
	            Runtime runtime = Runtime.getRuntime();
	            Process process = runtime.exec("sh startnetwork.sh "+channelName,null,dir);
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
	 
	 
	 
}
