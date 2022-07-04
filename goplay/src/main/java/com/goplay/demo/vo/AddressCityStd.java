package com.goplay.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="address_city_std")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "seq_address_city_std",
		sequenceName = "seq_address_city_std",
		allocationSize = 1
)
public class AddressCityStd {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	acNo	;
	private	String	acName	;
	
	@OneToMany(mappedBy = "address_city_std")
	private List<AddressDistrictStd> address_district_std;
}
