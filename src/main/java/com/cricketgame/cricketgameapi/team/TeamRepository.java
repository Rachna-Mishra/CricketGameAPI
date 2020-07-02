package com.cricketgame.cricketgameapi.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

}