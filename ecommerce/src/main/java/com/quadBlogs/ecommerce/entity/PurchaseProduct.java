package com.quadBlogs.ecommerce.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PurchaseProduct {
	
	@Id
	@Column(name="pp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ppid;
	
	
	private int quantity;
	private boolean purchasable;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="p_id")
	private Product product;
	
}
