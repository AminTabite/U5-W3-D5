package amintabite.U5_W3_D5.Payloads;

import amintabite.U5_W3_D5.Entities.Utente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record EventoPayload(
        @NotBlank
        String titolo,
        @NotBlank
        LocalDate dataEvento,
        @NotBlank
        String descrizione,
        @NotBlank
        String luogo,
        @Positive
        int nPosti
){
}
