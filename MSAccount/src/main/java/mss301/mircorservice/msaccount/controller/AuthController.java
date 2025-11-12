package mss301.mircorservice.msaccount.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mss301.mircorservice.msaccount.model.SystemAccount;
import mss301.mircorservice.msaccount.request.LoginRequest;
import mss301.mircorservice.msaccount.respone.LoginResponse;
import mss301.mircorservice.msaccount.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login successful"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<SystemAccount> account = accountService.login(
                request.getEmail(),
                request.getPassword()
        );

        if (account.isPresent()) {
            String token = accountService.generateToken(account.get());

            LoginResponse response = new LoginResponse();
            response.setToken(token);
            response.setEmail(account.get().getEmail());
            response.setUsername(account.get().getUsername());
            response.setRole(account.get().getRole());

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
