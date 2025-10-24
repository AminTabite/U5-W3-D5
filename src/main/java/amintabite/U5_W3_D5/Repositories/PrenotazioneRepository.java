package amintabite.U5_W3_D5.Repositories;

import amintabite.U5_W3_D5.Entities.Prenotazione;
import amintabite.U5_W3_D5.Entities.Utente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

    public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
        Page<Prenotazione> findByUser(Utente user, Pageable pageable);
    }


