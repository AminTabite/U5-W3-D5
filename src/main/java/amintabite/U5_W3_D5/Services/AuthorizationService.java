package amintabite.U5_W3_D5.Services;

import amintabite.U5_W3_D5.Entities.Utente;
import amintabite.U5_W3_D5.Exceptions.UnauthorizedException;
import amintabite.U5_W3_D5.Payloads.TokenPayload;
import amintabite.U5_W3_D5.Security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
@Autowired
    private UtenteService utenteService;
@Autowired
    private JWTTools jwtTools;

public String CheckCredentialAndDoToken(TokenPayload body){

    Utente found= this.utenteService.findByEmail(body.email());
    if(found.getPassword().equals(body.password())){
        return jwtTools.createToken(found);
    } else { throw  new UnauthorizedException("credenziali errate");
    }

}


}
