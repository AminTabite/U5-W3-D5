package amintabite.U5_W3_D5.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UtentePayload(
        @NotBlank
        @Size(min = 3, max = 20, message = "nome deve avere almeno 3 caratteri e max 20")
        String nome,

        @NotBlank
        @Size(min = 3, max = 20, message = "cognome deve avere almeno 3 caratteri e max 20")
        String cognome,

        @NotBlank
        @Email(message = "L'indirizzo email inserito non è nel formato corretto!")
        String email,

        @NotBlank
        String password


) {
}
