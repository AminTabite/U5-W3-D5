package amintabite.U5_W3_D5.Services;



import amintabite.U5_W3_D5.Entities.Evento;
import amintabite.U5_W3_D5.Entities.Prenotazione;
import amintabite.U5_W3_D5.Entities.Utente;
import amintabite.U5_W3_D5.Exceptions.NotFoundException;
import amintabite.U5_W3_D5.Repositories.EventoRepository;
import amintabite.U5_W3_D5.Repositories.PrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoRepository eventoRepository;


    public Page<Prenotazione> findAll(int page, int size, String sortBy) {
        if (size > 20) size = 20;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return prenotazioneRepository.findAll(pageable);

    }




    public Prenotazione savePrenotazione(UUID eventoId, Utente user) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato con id: " + eventoId));

        if (evento.getNPosti() <= 0) {
            throw new IllegalStateException("Nessun posto disponibile per questo evento!");
        }
        evento.setNPosti(evento.getNPosti() - 1);
        eventoRepository.save(evento);


        Prenotazione prenotazione = new Prenotazione(LocalDate.now(), user, evento);


        Prenotazione saved = prenotazioneRepository.save(prenotazione);

        log.info("Prenotazione salvata correttamente! Utente: {}, Evento: {}", user.getEmail(), evento.getTitolo());
        return saved;
    }

    public Prenotazione findById(UUID prenotazioneId) {
        return prenotazioneRepository.findById(prenotazioneId)
                .orElseThrow(() -> new NotFoundException("Prenotazione non trovata"));
    }

    public void deletePrenotazione(UUID prenotazioneId) {
        Prenotazione found = this.findById(prenotazioneId);

        Evento evento = found.getEvento();

        evento.setNPosti(evento.getNPosti() + 1);

        eventoRepository.save(evento);

        prenotazioneRepository.delete(found);



        log.info("Prenotazione  eliminata Posto liberato", prenotazioneId, evento.getTitolo());
    }



    public Page<Prenotazione> findByUser(Utente user, int page, int size, String sortBy) {
        if (size > 20) size = 20;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return prenotazioneRepository.findByUser(user, pageable);
    }


}
