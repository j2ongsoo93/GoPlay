package com.goplay.demo.vo;

import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="address_city")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "seq_address_city",
		sequenceName = "seq_address_city",
		allocationSize = 1
)
public class AddressCity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	acNo	;
	private	String	acName	;

	@OneToMany(mappedBy = "address_city")
	private List<AddressDistrict> address_district;
}
