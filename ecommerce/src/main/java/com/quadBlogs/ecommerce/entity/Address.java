package com.quadBlogs.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "a_id")
	private long aId;
	
	@NotEmpty
	@Column(name = "receiver_name")
	private String receiverName;
	
	@NotEmpty
	@Column(name = "street_name")
	private String streetName;
	
	@NotEmpty
	@Column(name = "apartment_number")
	private String apartmentNumber;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String state;
	
	@NotEmpty(message = "Cannot be empty and length should be six")
	@Size(max = 6, min = 6)
	@Column(name = "pin_code")
	private String pinCode;
	
	@NotEmpty
	@Size(max = 10, min = 10)
	@Column(name = "contact_number")
	private String contactNumber;
}
