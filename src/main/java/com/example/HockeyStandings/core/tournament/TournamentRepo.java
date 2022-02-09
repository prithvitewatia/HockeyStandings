package com.example.HockeyStandings.core.tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament,Long> {
}
