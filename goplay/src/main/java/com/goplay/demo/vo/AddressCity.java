package com.goplay.demo.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="address_city")
@AllArgsConstructor
@NoArgsConstructor
public class AddressCity {
	@Id
	private	int	acNo	;
	private	String	acName	;
	
	@OneToMany(mappedBy = "address_city", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<AddressDistrict> address_district;
}
