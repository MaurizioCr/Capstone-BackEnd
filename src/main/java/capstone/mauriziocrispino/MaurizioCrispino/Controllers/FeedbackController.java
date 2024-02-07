package capstone.mauriziocrispino.MaurizioCrispino.Controllers;

import capstone.mauriziocrispino.MaurizioCrispino.DTO.FeedbackDTO;
import capstone.mauriziocrispino.MaurizioCrispino.DTO.FeedbackResponseDTO;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Feedback;
import capstone.mauriziocrispino.MaurizioCrispino.Exceptions.BadRequestException;
import capstone.mauriziocrispino.MaurizioCrispino.Services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public Page<Feedback> getFeedbacks(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String orderBy){
        return feedbackService.getFeedbacks(page, size, orderBy);
    }
    @GetMapping("/{id}")
    public Feedback findById(@PathVariable long id){
        return feedbackService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResponseDTO createFeedback(@RequestBody @Validated FeedbackDTO newUserPayload, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors().stream().map(err -> err.getDefaultMessage()).toList().toString());
        }
        Feedback newFeedack = feedbackService.save(newUserPayload);
        return new FeedbackResponseDTO(newFeedack.getId());

    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Feedback findByIdAndUpdate(@PathVariable long id, @RequestBody Feedback updateUserPayload){
        return feedbackService.findbyIdAndUpdate(id,updateUserPayload);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable long id){
        feedbackService.findByIdAndDelete(id);
    }

}
