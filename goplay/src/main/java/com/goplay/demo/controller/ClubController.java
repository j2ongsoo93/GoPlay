package com.goplay.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.goplay.demo.service.ClubService;

import lombok.Setter;

@RestController
@Setter
public class ClubController {
	@Autowired
	private ClubService cs;

}
