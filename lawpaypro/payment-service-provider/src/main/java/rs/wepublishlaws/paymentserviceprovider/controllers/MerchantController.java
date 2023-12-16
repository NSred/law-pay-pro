package rs.wepublishlaws.paymentserviceprovider.controllers;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.wepublishlaws.paymentserviceprovider.dto.LoginDTO;
import rs.wepublishlaws.paymentserviceprovider.dto.RegistrationDTO;
import rs.wepublishlaws.paymentserviceprovider.dto.UserTokenState;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;
import rs.wepublishlaws.paymentserviceprovider.security.util.TokenGenerator;
import rs.wepublishlaws.paymentserviceprovider.service.MerchantService;


@RestController
@RequestMapping("/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        if (merchantService.existsMerchantByMerchantId(registrationDTO.getMerchantId())) {
            return ResponseEntity.badRequest().body("Merchant id is already taken!");
        }
        String apiKey = merchantService.generateApiKey(
                registrationDTO.getMerchantId(),
                registrationDTO.getMerchantPassword()
        );
        return ResponseEntity.ok(apiKey);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody LoginDTO loginDTO, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getMerchantId(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Merchant merchant = merchantService.findMerchantByMerchantId(loginDTO.getMerchantId());
        String jwt = tokenGenerator.generate(merchant);
        int expiresIn = tokenGenerator.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

}
