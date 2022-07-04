package com.goplay.demo.vo;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="faq")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "seq_faq",
		sequenceName = "seq_faq",
		initialValue = 1,
		allocationSize = 1
)
public class Faq {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	faqNo	;
	private	String	faqTitle	;
	private	String	faqContent	;

}
