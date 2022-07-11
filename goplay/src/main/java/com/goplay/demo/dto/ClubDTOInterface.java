package com.goplay.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//문제의 원인은 필드가 없는 클래스를 Serialize하려고 할 때 어노테이션 
//spring.jackson.serialization.FAIL_ON_EMPTY_BEANS=false
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public interface ClubDTOInterface {
	int	cNo()	;
	String id();
	String	cName()	;
	String	cType()	;
	String	cLoc1()	;
	String	cLoc2()	;
	String	cImg()	;
	String	cIntro();
	String	cStat()	;
	
}
