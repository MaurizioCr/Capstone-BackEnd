package capstone.mauriziocrispino.MaurizioCrispino.Repositories;

import capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
