package amintabite.U5_W3_D5.Services;

import amintabite.U5_W3_D5.Entities.Evento;
import amintabite.U5_W3_D5.Entities.Utente;
import amintabite.U5_W3_D5.Exceptions.NotFoundException;
import amintabite.U5_W3_D5.Payloads.EventoPayload;
import amintabite.U5_W3_D5.Repositories.EventoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Evento saveEvento(EventoPayload payload, Utente organizzatore) {
        Evento evento = new Evento(
                payload.nPosti(),
                payload.titolo(),
                payload.dataEvento(),
                payload.descrizione(),
                payload.luogo(),
                List.of(),
                organizzatore
        );

        Evento saved = eventoRepository.save(evento);
        log.info("Evento  salvato correttamente dall'organizzatore", saved.getTitolo(), organizzatore.getEmail());
        return saved;
    }

    public Evento findById(UUID eventoId) {
        return eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));
    }

    public Evento updateEvento(UUID eventoId, EventoPayload payload) {
        Evento found = findById(eventoId);
        found.setNPosti(payload.nPosti());
        found.setTitolo(payload.titolo());
        found.setDataEvento(payload.dataEvento());
        found.setDescrizione(payload.descrizione());
        found.setLuogo(payload.luogo());
        return eventoRepository.save(found);
    }

    public void deleteEvento(UUID eventoId) {
        Evento found = findById(eventoId);
        eventoRepository.delete(found);
        log.info("Evento  eliminato correttamente", eventoId);
    }
}