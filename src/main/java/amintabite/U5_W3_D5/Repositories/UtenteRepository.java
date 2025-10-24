package amintabite.U5_W3_D5.Repositories;

import amintabite.U5_W3_D5.Entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtenteRepository extends JpaRepository< Utente ,UUID> {
    Optional<Utente> findByEmail(String email);
}
