package com.company.spring.tennis.table.dal.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.spring.tennis.table.dal.entities.RoundMatch;

@Repository
public interface RoundMatchRepositroy extends JpaRepository<RoundMatch, Integer> {

	@Query(value = "SELECT launch_time FROM round_match where first_player_score IS NULL && second_player_score IS NULL", nativeQuery = true)
	List<Date> getLaunchDates();

}
