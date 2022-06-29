package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goplay.demo.service.FaqService;
import com.goplay.demo.vo.Faq;

import lombok.Setter;

@RestController
@Setter
public class FaqController {
	@Autowired
	private FaqService fs;
	
	@GetMapping("/listFaq")
	public List<Faq> listMember(){
		return fs.findAll();
	}
}
