package com.lbc.calculator.controller;


import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbc.calculator.domain.LessonData;
import com.lbc.calculator.domain.Problem;
import com.lbc.calculator.service.ProblemService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
	private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

	@Inject
	ProblemService service;
	
	
	@PostMapping("/getproblem")
	public ResponseEntity<Problem> getProblem(@RequestBody Problem pr) {
		ResponseEntity<Problem> entity = null;
		Problem problem = null;
		try {
			
			problem = service.getProblem(pr.problemNumber);
			entity = new ResponseEntity<Problem>(problem, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<Problem>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	@PostMapping("/getlesson")
	public ResponseEntity<LessonData> getLesson(@RequestBody LessonData ld) {
		ResponseEntity<LessonData> entity = null;
		LessonData lessonData;
		try {
			lessonData = service.getLesson(ld);
			entity = new ResponseEntity<LessonData>(lessonData, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<LessonData>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@GetMapping("/getproblemcount")
	public ResponseEntity<Integer> getCount() {
		ResponseEntity<Integer> entity = null;
		int count =0;
		try {
			count = service.getProblemCount();
			entity = new ResponseEntity<Integer>(count, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	
}
	
	
	
	
	
	
	
