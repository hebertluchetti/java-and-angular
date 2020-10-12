
package com.hebert.bloodbank.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "address", uniqueConstraints = {@UniqueConstraint(columnNames = {"zip","street", "number" }, name = "uk_address")})
public class Address extends GenericEntity  {
	private static final long serialVersionUID = 3444229741544878539L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "street", nullable = false)
	private String street;
	
	@Column(name = "number", nullable = false)
	private String number;
	
	@Column(name = "complement")
	private String complement;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "zip", nullable = false)
	private String zip;
	
	// Only address knows city, so there is no need to associate anything in City
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;

	public Address( String street, String number, String district, String zip, City city) {
		this.street = street;
		this.number = number;
		this.complement = "";
		this.district = district;
		this.zip = zip;
		this.city = city;
	}
			
}
