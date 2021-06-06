package com.company.spring.tennis.table.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.spring.tennis.table.dal.entities.Participant;

@Repository
public interface ParticipantRepositroy extends JpaRepository<Participant, Integer> {

	boolean existsByUserName(String userName);

	boolean existsByEmail(String email);

	Participant findByUserName(String userName);

	Participant findByEmail(String email);

}
