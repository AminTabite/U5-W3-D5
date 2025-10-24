package amintabite.U5_W3_D5.Controllers;

import amintabite.U5_W3_D5.Entities.Utente;
import amintabite.U5_W3_D5.Exceptions.ValidationsException;
import amintabite.U5_W3_D5.Payloads.LoginPayload;
import amintabite.U5_W3_D5.Payloads.TokenPayload;
import amintabite.U5_W3_D5.Payloads.UtentePayload;
import amintabite.U5_W3_D5.Services.AuthorizationService;
import amintabite.U5_W3_D5.Services.UtenteService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
@Autowired
    private AuthorizationService authorizationService;
@Autowired
    private UtenteService utenteService;

@PostMapping("/login")
    public TokenPayload login(@RequestBody LoginPayload body){
    return new TokenPayload(authorizationService.CheckCredentialAndDoToken(body));
}


@PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente createUtente(@RequestBody @Validated UtentePayload payload, BindingResult validationResult){
    if (validationResult.hasErrors()) {

        throw new ValidationsException(validationResult.getFieldErrors()
                .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
    }
    return this.utenteService.Saveutente(payload);


}



}
