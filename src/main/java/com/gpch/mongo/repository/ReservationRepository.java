package com.gpch.mongo.repository;


import com.gpch.mongo.model.Reservation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	
	
	public List<Reservation> findByfirstName(String firstName);
	public List<Reservation> findBylastName(String lastName);
	public List<Reservation> findBymobile(String mobile);
	public List<Reservation> findByemailId(String emailId);
	
	//@Query(value="select * from Reservation u where u.firstName like %:name%", nativeQuery=true)
	//List<Reservation> findByfirstName(@Param("name") String firstName);
	
	@Query("select reservation r where r._id = :id")
	public  Reservation findbyIdUser(@Param("id") long id);
}
