package com.gpch.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;



@Data
@Document(collection = "reservations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	
	
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	
	@NotBlank
	@Size(max=100)
	private String firstName;
	
	@NotBlank
	@Size(max=100)
	private String lastName;
	
	@NotBlank
	@Size(max=100)
	private String mobile;
	
	
	@NotBlank
	@Size(max=100)
	@Indexed(unique=true)
	private String emailId;
	
	@NotBlank
	@Size(max=100)
	private String state;
	
	@NotBlank
	@Size(max=100)
	private String city;
	
	LocalDateTime createddate = LocalDateTime.now();
	//LocalDateTime createddate = localDateTime.toLocalTime();
	//private Date createddate =  new Date();

	public Reservation(long id, @NotBlank @Size(max = 100) String firstName, @NotBlank @Size(max = 100) String lastName,
			@NotBlank @Size(max = 100) String mobile, @NotBlank @Size(max = 100) String emailId,
			@NotBlank @Size(max = 100) String state, @NotBlank @Size(max = 100) String city) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.emailId = emailId;
		this.state = state; 
		this.city = city;
	}

	

	
	
	
}
