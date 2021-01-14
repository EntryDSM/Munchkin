package kr.hs.entrydsm.score.infrastructure.springdatajpa;

import kr.hs.entrydsm.score.domain.entity.Score;
import kr.hs.entrydsm.score.domain.repository.ScoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreManager extends JpaRepository<Score, Integer>, ScoreRepository {
}
