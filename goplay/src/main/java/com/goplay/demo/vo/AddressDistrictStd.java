package com.goplay.demo.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name="address_district_Std")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "seq_address_district_std",
		sequenceName = "seq_address_district_std",
		initialValue = 1,
		allocationSize = 1
)
public class AddressDistrictStd {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	adNo	;
	private	String	adName	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ac_no", insertable = true, updatable = true)
	@JsonBackReference
	private	AddressCityStd address_city_std;
}
