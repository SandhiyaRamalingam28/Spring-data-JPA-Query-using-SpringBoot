package com.example.springJPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springJPA.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	// JPQL with designation Parameters
	@Query("select e from User e where e.designation=:designation")
	List<User> UserDesignation(@Param("designation") String designation);

	// JPQL with named Parameters
	@Query("select e from User e where e.firstName= :firstname")
	List<User> FirstNameDetails(@Param("firstname") String firstName);

	// JPQL with index Parameters
	@Query("select e from User e where e.lastName=?1 and e.firstName=?2")
	List<User> NameWithIndex(String lastName, String firstName);

	// Native SQL with named parameters
	@Query(value = "select * from User where designation=:designation", nativeQuery = true)
	List<User> DesignationDetails(@Param("designation") String designation);

	// Native SQL with named parameters
	@Query(value = "select * from User where last_name=:last_name", nativeQuery = true)
	List<User> LastNameDetails(@Param("last_name") String lastName);

	// Native SQL with index Parameters
	@Query(value = "select * from User e where e.first_name=?1 and e.last_name=?2", nativeQuery = true)
	List<User> NamesWithIndex(String firstName, String lastName);

}
