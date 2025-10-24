package amintabite.U5_W3_D5.Entities;


import amintabite.U5_W3_D5.Ruolo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Utente {

    @Id
    @GeneratedValue
    UUID utenteId;
    String nome;
    String cognome;
    String email;
    String password;
    Ruolo ruolo;
    @OneToMany(mappedBy = "user")
    private List<Prenotazione> prenotazioni;

    @OneToMany(mappedBy = "organizzatore")
    private List<Evento>  eventi;

    public Utente(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }
}
