package hyperledger.fabric.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import hyperledger.fabric.model.Car;
import hyperledger.fabric.respositories.BaseFabricRepository;

@RestController
public class CarController {
	
	@Autowired
	BaseFabricRepository baseFabricRepository;
	

    @GetMapping(path = {"/getAllCars"})
    public ResponseEntity<?> getAllCars(HttpServletRequest httpRequest) throws Exception {
    	System.out.println("getting cars..");
    	String cars=baseFabricRepository.getAllCar();
    	JSONArray jsonArray=new JSONArray(cars);
    	List<Car> carList=new ArrayList<Car>();
    	for(int i=0;i<jsonArray.length();i++) {
    		Object jsonObject=jsonArray.get(i);
    		JSONObject jsonObject1=new JSONObject(jsonObject.toString());
    		
    		String key =jsonObject1.get("key").toString();
    		Car car=new Car();
    		JSONObject recordObject=jsonObject1.getJSONObject("record");
    		car.setName(key);
    		car.setOwner(recordObject.get("owner").toString());
    		car.setMake(recordObject.get("make").toString());
    		car.setModel(recordObject.get("model").toString());
    		car.setColor(recordObject.get("color").toString());
    		carList.add(car);
    	}

        ResponseEntity<String> responseEntity = new ResponseEntity<>(getJsonString(carList), HttpStatus.OK);
        return responseEntity;
    }
    
    @PostMapping(path = {"/createCar"})
    public ResponseEntity<?> createCar(HttpServletRequest httpRequest,@RequestParam(required = true) String carName,
    		@RequestParam(required = true) String make,
    		@RequestParam(required = true) String model,
    		@RequestParam(required = true) String color,
    		@RequestParam(required = true) String owner) throws Exception {
    	String car=baseFabricRepository.createCar(carName,make,model,color,owner);
    	ResponseEntity<String> responseEntity = new ResponseEntity<>(car, HttpStatus.OK);
        return responseEntity;
    }
    
    @GetMapping(path = {"/getCarByKey"})
    public ResponseEntity<?> getCarByKey(HttpServletRequest httpRequest,@RequestParam(required = true) String keyName) throws Exception {
    	String car=baseFabricRepository.getCarByKey(keyName);
    	
    	ResponseEntity<String> responseEntity = new ResponseEntity<>(car, HttpStatus.OK);
        return responseEntity;
    }
    
    @PostMapping(path = {"/updateOwner"})
    public ResponseEntity<?> updateOwner(HttpServletRequest httpRequest,@RequestParam(required = true) String keyName,@RequestParam(required = true) String owner) throws Exception {
    	String car=baseFabricRepository.updateCarOwner(keyName,owner);
    	ResponseEntity<String> responseEntity = new ResponseEntity<>(car, HttpStatus.OK);
        return responseEntity;
    }
    
    private String getJsonString(List<?> carList){
        String result = "[]";
        if(carList != null){
            try {
            	ObjectMapper objectMapper = new ObjectMapper();
                result = objectMapper.writeValueAsString(carList);
            }catch (Exception e){
               
            }
        }
        return result;
    }

}
