package amintabite.U5_W3_D5.Controllers;


import amintabite.U5_W3_D5.Entities.Utente;
import amintabite.U5_W3_D5.Exceptions.ValidationsException;
import amintabite.U5_W3_D5.Payloads.UtentePayload;
import amintabite.U5_W3_D5.Services.UtenteService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;


    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Page<Utente> getUtenti(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "nome") String sortBy

    ){
        return utenteService.findAll(page, size, sortBy);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente createUtente(@RequestBody @Validated UtentePayload body, BindingResult validationResult){

        if (validationResult.hasErrors()) {

            throw new ValidationsException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }

        return this.utenteService.Saveutente(body);

    }

    @GetMapping("/{Utenteid}")

    public Utente getUtenteById(@PathVariable UUID Utenteid){
        return utenteService.findById(Utenteid);
    }


    @DeleteMapping("/{utenteid}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUtente(@PathVariable UUID utenteid){
        utenteService.FindByIdAndDelete(utenteid);

    }

    //endpoint per i clienti semplici

    @GetMapping("/me")
    public Utente getMyProfile(@AuthenticationPrincipal Utente currentUser) {
        return utenteService.findById(currentUser.getUtenteId());
    }


    @PutMapping("/me")
    public Utente updateMyProfile(@AuthenticationPrincipal Utente currentUser,
                                  @RequestBody @Validated UtentePayload payload,BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationsException(validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList());
        }
        return utenteService.FindByIdAndUpdate(currentUser.getUtenteId(), payload);
    }



    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyProfile(@AuthenticationPrincipal Utente currentUser) {
        utenteService.FindByIdAndDelete(currentUser.getUtenteId());
    }



}
