package com.goplay.demo.vo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="address_district")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "seq_address_district",
		sequenceName = "seq_address_district",
		initialValue = 1,
		allocationSize = 1
)
public class AddressDistrict {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	adNo	;
	private	String	adName	;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ac_no", insertable = true, updatable = true)
	@JsonBackReference
	private	AddressCity address_city;
}
