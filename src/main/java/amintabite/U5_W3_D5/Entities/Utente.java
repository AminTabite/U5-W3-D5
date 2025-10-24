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
        this.ruolo = Ruolo.ClIENTE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Questo metodo vogliamo che restituisca una lista di Authorities, cioè dei ruoli dell'utente
        // SimpleGrantedAuthority è una classe che implementa GrantedAuthority e ci serve per convertire il ruolo dell'utente
        // che nel nostro caso è un enum in un oggetto utilizzabile dai meccanismi di Spring Security
        return List.of(new SimpleGrantedAuthority(this.role.name()));

}
