package com.stalesoft.welcome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "user_id")
	 private int id;
}
