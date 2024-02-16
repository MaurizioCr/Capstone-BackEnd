package capstone.mauriziocrispino.MaurizioCrispino.Controllers;

import capstone.mauriziocrispino.MaurizioCrispino.DTO.ResponseDTO;
import capstone.mauriziocrispino.MaurizioCrispino.DTO.UserLoginDTO;
import capstone.mauriziocrispino.MaurizioCrispino.DTO.UserLoginResponseDTO;
import capstone.mauriziocrispino.MaurizioCrispino.DTO.UtenteDTO;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Utente;
import capstone.mauriziocrispino.MaurizioCrispino.Exceptions.BadRequestException;
import capstone.mauriziocrispino.MaurizioCrispino.Services.AuthService;
import capstone.mauriziocrispino.MaurizioCrispino.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        Long id = utenteService.findByEmail(body.email()).getId();
        return new UserLoginResponseDTO(accessToken, id );
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createUser(@RequestBody @Validated UtenteDTO newUserPayload, BindingResult validation) {
        // Per completare la validazione devo in qualche maniera fare un controllo del tipo: se ci sono errori -> manda risposta con 400 Bad Request
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors().stream().toList().toString()); // L'eccezione arriverà agli error handlers tra i quali c'è quello che manderà la risposta con status code 400
        } else {
            System.out.println(newUserPayload);
            Utente newUser = utenteService.save(newUserPayload);

            return new ResponseDTO(newUser.getId());
        }
    }
}
