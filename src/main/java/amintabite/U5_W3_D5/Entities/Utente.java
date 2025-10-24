package amintabite.U5_W3_D5.Entities;


import amintabite.U5_W3_D5.Ruolo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Utente implements UserDetails {

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

       return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
   }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
