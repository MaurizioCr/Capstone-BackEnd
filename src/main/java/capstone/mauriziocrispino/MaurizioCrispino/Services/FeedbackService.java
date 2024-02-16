package capstone.mauriziocrispino.MaurizioCrispino.Services;

import capstone.mauriziocrispino.MaurizioCrispino.DTO.FeedbackDTO;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Utente;
import capstone.mauriziocrispino.MaurizioCrispino.Exceptions.NotFoundException;
import capstone.mauriziocrispino.MaurizioCrispino.Exceptions.UnauthorizedException;
import capstone.mauriziocrispino.MaurizioCrispino.Repositories.FeedbackRepository;
import capstone.mauriziocrispino.MaurizioCrispino.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private UtenteService utenteService;

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback save(FeedbackDTO body) {
        // Verifico che l'utente sia autenticato
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new UnauthorizedException("Utente non autenticato");
        }

        // Ottengo l'utente corrente dal contesto di sicurezza
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Cerco l'utente per nome utente
        Utente utente = utenteService.findByUsername(userDetails.getUsername());

        if (utente == null) {
            throw new NotFoundException("Utente non trovato");
        }

        capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback newFeedback = new capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback();
        newFeedback.setFeedback(body.feedback());
        newFeedback.setVotoFeedback(body.votoFeedback());
        newFeedback.setUtente(utente);
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
