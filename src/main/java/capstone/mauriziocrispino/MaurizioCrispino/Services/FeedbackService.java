package capstone.mauriziocrispino.MaurizioCrispino.Services;

import capstone.mauriziocrispino.MaurizioCrispino.DTO.FeedbackDTO;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Utente;
import capstone.mauriziocrispino.MaurizioCrispino.Exceptions.NotFoundException;
import capstone.mauriziocrispino.MaurizioCrispino.Repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Page<Feedback> getFeedbacks(int page, int size, String orderBy){
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return feedbackRepository.findAll(pageable);
    }

    public capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback save(FeedbackDTO body){
        capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback newFeedback = new capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback();
        newFeedback.setFeedback(body.feedback());
        newFeedback.setVotoFeedback(body.votoFeedback());
        return feedbackRepository.save(newFeedback);
    }

    public capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback findById(long id){
        return feedbackRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public void findByIdAndDelete(long id){
        capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback found = this.findById(id);
        feedbackRepository.delete(found);
    }

    public capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback findbyIdAndUpdate(long id, capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback body){
        capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback found = this.findById(id);
        found.setFeedback(body.getFeedback());
        found.setVotoFeedback(body.getVotoFeedback());
        return feedbackRepository.save(found);

    }
}
