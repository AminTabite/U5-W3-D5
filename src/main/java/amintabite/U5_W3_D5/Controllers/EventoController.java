package amintabite.U5_W3_D5.Controllers;

import amintabite.U5_W3_D5.Entities.Evento;
import amintabite.U5_W3_D5.Entities.Utente;
import amintabite.U5_W3_D5.Exceptions.ValidationsException;
import amintabite.U5_W3_D5.Payloads.EventoPayload;
import amintabite.U5_W3_D5.Services.EventoService;
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
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;



    @GetMapping
    public Page<Evento> getEventi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "dataEvento") String sortBy
    ) {
        return eventoService.findAll(page, size, sortBy);
    }


    @GetMapping("/{eventoId}")
    public Evento getEventoById(@PathVariable UUID eventoId) {
        return eventoService.findById(eventoId);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento createEvento(@RequestBody @Validated EventoPayload payload,
                               BindingResult validationResult,
                               @AuthenticationPrincipal Utente currentUser) {
        if (validationResult.hasErrors()) {
            throw new ValidationsException(validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList());
        }
        return eventoService.saveEvento(payload, currentUser);
    }


    @PutMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Evento updateEvento(@PathVariable UUID eventoId,
                               @RequestBody @Validated EventoPayload payload,
                               BindingResult validationResult,
                               @AuthenticationPrincipal Utente currentUser) {
        if (validationResult.hasErrors()) {
            throw new ValidationsException(validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList());
        }
        return eventoService.updateEvento(eventoId, payload, currentUser);
    }


    @DeleteMapping("/{eventoId}")

    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvento(@PathVariable UUID eventoId,
                             @AuthenticationPrincipal Utente currentUser) {
        eventoService.deleteEvento(eventoId, currentUser);
    }
}
