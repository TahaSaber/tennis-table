package com.company.spring.tennis.table.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.spring.tennis.table.dal.entities.Round;

@Repository
public interface RoundRepositroy extends JpaRepository<Round, Integer> {

}
