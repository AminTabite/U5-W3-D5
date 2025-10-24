package amintabite.U5_W3_D5.Controllers;

import amintabite.U5_W3_D5.Entities.Evento;
import amintabite.U5_W3_D5.Entities.Prenotazione;
import amintabite.U5_W3_D5.Entities.Utente;
import amintabite.U5_W3_D5.Exceptions.ValidationsException;
import amintabite.U5_W3_D5.Services.EventoService;
import amintabite.U5_W3_D5.Services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private EventoService eventoService;

    // 🔹 Lista paginata di tutte le prenotazioni (solo organizzatori)
    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Page<Prenotazione> getAllPrenotazioni(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "dataprenotazione") String sortBy
    ) {
        return prenotazioneService.findAll(page, size, sortBy);
    }

    // 🔹 Lista paginata delle prenotazioni dell'utente loggato
    @GetMapping("/me")
    public Page<Prenotazione> getMyPrenotazioni(
            @AuthenticationPrincipal Utente currentUser,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "dataprenotazione") String sortBy
    ) {
        return prenotazioneService.findByUser(currentUser, page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione createPrenotazione(
            @RequestParam UUID eventoId,
            @AuthenticationPrincipal Utente currentUser
    ) {
        // Chiama direttamente il service passando entrambi i parametri
        return prenotazioneService.savePrenotazione(eventoId, currentUser);
    }



    @GetMapping("/{id}")
    public Prenotazione getPrenotazioneById(@PathVariable UUID id) {
        return prenotazioneService.findById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable UUID id) {
        prenotazioneService.deletePrenotazione(id);
    }
}
