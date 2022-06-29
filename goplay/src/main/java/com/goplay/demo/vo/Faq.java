package com.goplay.demo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="faq")
@AllArgsConstructor
@NoArgsConstructor
public class Faq {
	@Id
	private	int	faqNo	;
	private	String	faqTitle	;
	private	String	faqContent	;

}
