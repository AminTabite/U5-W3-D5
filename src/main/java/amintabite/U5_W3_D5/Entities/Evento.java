package amintabite.U5_W3_D5.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Evento {
    @Id
    @GeneratedValue
    UUID eventoId;
    String titolo;
    LocalDate dataEvento;
    String descrizione;
    String luogo;
    int nPosti;

    @OneToMany(mappedBy = "evento")
    private List<Prenotazione> prenotazioni;
    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private Utente organizzatore;

    public Evento(int nPosti, String titolo, LocalDate dataEvento, String descrizione, String luogo, List<Prenotazione> prenotazioni, Utente organizzatore) {
        this.nPosti = nPosti;
        this.titolo = titolo;
        this.dataEvento = dataEvento;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.prenotazioni = prenotazioni;
        this.organizzatore = organizzatore;
    }
}
