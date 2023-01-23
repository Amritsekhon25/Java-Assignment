package com.speakingclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speakingclock.services.SpeakingClockService;

@RestController
@RequestMapping("/speakingCLock")
public class SpeakingClockController {
	

	
@Autowired
private SpeakingClockService speakingClockService;
	
	 @GetMapping(value = "/convertTime/{time}",produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getPointsByCustomerId(@PathVariable("time") String time){
		 
		String response= speakingClockService.convertTime(time);

	       
	        return new ResponseEntity<>(response,HttpStatus.OK);
	    }

	 
		
  
}
