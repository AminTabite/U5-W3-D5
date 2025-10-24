package amintabite.U5_W3_D5.Services;

import amintabite.U5_W3_D5.Entities.Utente;
import amintabite.U5_W3_D5.Exceptions.NotFoundException;
import amintabite.U5_W3_D5.Payloads.UtentePayload;
import amintabite.U5_W3_D5.Repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    public Page<Utente> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 5) pageSize = 5; // se vuoi limitare a 5
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }


    public Utente Saveutente(UtentePayload payload){

        Utente newUtente = new Utente(
                payload.nome(),
                payload.cognome(),
                payload.email(),
                payload.password()


        );
        Utente savedUtente = utenteRepository.save(newUtente);
        log.info("Utente " + savedUtente.getCognome() + " salvato correttamente!");
        return savedUtente;

    }


    public Utente findById(UUID utenteId){

        return utenteRepository.findById(utenteId)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

    }

    public Utente FindByIdAndUpdate(UUID utenteId, UtentePayload payload ){

        Utente found = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        found.setNome(payload.nome());
        found.setCognome(payload.cognome());
        found.setEmail(payload.email());
        found.setPassword(payload.password());

        return utenteRepository.save(found);

    }


    public void FindByIdAndDelete(UUID utenteId){

        Utente found = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        utenteRepository.delete(found);

        log.info("utente con ID " + utenteId + " eliminato correttamente.");
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con l'email " + email + " non è stato trovato"));
    }

}
