package kr.hs.entrydsm.score.infrastructure.database;

import kr.hs.entrydsm.score.entity.Score;
import kr.hs.entrydsm.score.entity.ScoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepositoryManager extends JpaRepository<Score, Integer>, ScoreRepository {
}
