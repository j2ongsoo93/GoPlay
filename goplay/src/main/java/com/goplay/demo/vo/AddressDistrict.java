package com.goplay.demo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="address_district")
@AllArgsConstructor
@NoArgsConstructor
public class AddressDistrict {
	@Id
	private	int	adNo	;
	private	String	adName	;
	
	@ManyToOne
	@JoinColumn(name = "ac_no", insertable = true, updatable = true)
	@JsonBackReference
	private	AddressCity address_city;
}
