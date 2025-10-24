package amintabite.U5_W3_D5.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class Prenotazione {

    @Id
    @GeneratedValue
    UUID PrenotazioneId;

    LocalDate dataprenotazione;
    @ManyToOne
    @JoinColumn(name = "users")
    private Utente user;

    @ManyToOne
    @JoinColumn(name = "evento")
    private Evento evento;

    public Prenotazione( LocalDate dataprenotazione, Utente user, Evento evento) {
        this.dataprenotazione = dataprenotazione;
        this.user = user;
        this.evento = evento;
    }
}
