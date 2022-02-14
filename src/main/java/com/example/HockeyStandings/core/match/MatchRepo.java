package com.example.HockeyStandings.core.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepo extends JpaRepository<Match,Long> {
}
