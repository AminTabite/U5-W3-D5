package amintabite.U5_W3_D5.Repositories;

import amintabite.U5_W3_D5.Entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EventoRepository extends JpaRepository< Evento, UUID> {
}
