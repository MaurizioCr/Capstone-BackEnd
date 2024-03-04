package capstone.mauriziocrispino.MaurizioCrispino.Services;

import capstone.mauriziocrispino.MaurizioCrispino.DTO.UtenteDTO;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Utente;
import capstone.mauriziocrispino.MaurizioCrispino.Enums.Role;
import capstone.mauriziocrispino.MaurizioCrispino.Exceptions.BadRequestException;
import capstone.mauriziocrispino.MaurizioCrispino.Exceptions.NotFoundException;
import capstone.mauriziocrispino.MaurizioCrispino.Repositories.UtenteRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    private PasswordEncoder bcrypt =  new BCryptPasswordEncoder(11);
    public Page<Utente> getUsers(int page, int size, String orderBy){
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente save(UtenteDTO body, MultipartFile avatarFile){
        utenteRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già in uso!");
        });
        Utente nuovoUtente = new Utente();
        nuovoUtente.setNome(body.nome());
        nuovoUtente.setCognome(body.cognome());
        nuovoUtente.setEmail(body.email());
        nuovoUtente.setUsername(body.username());
        nuovoUtente.setPassword(bcrypt.encode(body.password()));
        nuovoUtente.setRole(Role.USER);

        // Carica l'immagine su Cloudinary e imposta l'URL dell'avatar nel nuovo utente
        try {
            if (avatarFile != null && !avatarFile.isEmpty()) {
                String avatarUrl = uploadPicture(avatarFile);
                nuovoUtente.setAvatar(avatarUrl);
            } else {
                nuovoUtente.setAvatar("https://ui-avatars.com/api/?name=" + nuovoUtente.getNome() + "+" + nuovoUtente.getCognome());
            }
        } catch (IOException e) {
            // Gestisci l'errore di caricamento dell'immagine
            e.printStackTrace();
            throw new RuntimeException("Errore durante il caricamento dell'immagine.");
        }

        return utenteRepository.save(nuovoUtente);
    }

    public Utente findById(long id){
        return utenteRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public Utente findByUsername(String username){
        return utenteRepository.findByUsername(username).orElseThrow(()->new NotFoundException(username));
    }

    public void findByIdAndDelete(long id){
        Utente found = this.findById(id);
        utenteRepository.delete(found);
    }

    public Utente findbyIdAndUpdate(long id, Utente body) {
        Utente found = this.findById(id);
        if (body.getCognome() != null && !body.getCognome().isEmpty()) {
            found.setCognome(body.getCognome());
        }
        if (body.getNome() != null && !body.getNome().isEmpty()) {
            found.setNome(body.getNome());
        }
        if (body.getEmail() != null && !body.getEmail().isEmpty()) {
            found.setEmail(body.getEmail());
        }
        if (body.getUsername() != null && !body.getUsername().isEmpty()) {
            found.setUsername(body.getUsername());
        }

        if (body.getAvatar() != null && !body.getAvatar().isEmpty()) {
            found.setAvatar(body.getAvatar());
        }

        // Salva le modifiche nell'utente nel database
        return utenteRepository.save(found);
    }



    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }

    public String uploadPicture(MultipartFile file) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;
    }

    public void save(Utente utenteCorrente) {
    }
}