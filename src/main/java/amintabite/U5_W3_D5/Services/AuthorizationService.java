package amintabite.U5_W3_D5.Services;

import amintabite.U5_W3_D5.Entities.Utente;
import amintabite.U5_W3_D5.Exceptions.UnauthorizedException;
import amintabite.U5_W3_D5.Payloads.LoginPayload;
import amintabite.U5_W3_D5.Payloads.TokenPayload;
import amintabite.U5_W3_D5.Security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String CheckCredentialAndDoToken(LoginPayload body) {

        Utente found = this.utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword()))
            {
                return jwtTools.createToken(found);
            } else{
                throw new UnauthorizedException("credenziali errate");
            }

        }


    }

