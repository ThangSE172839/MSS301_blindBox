package mss301.mircorservice.msaccount.service;

import mss301.mircorservice.msaccount.model.SystemAccount;
import mss301.mircorservice.msaccount.repostiory.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class  AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtService jwtService;


    @Override
    public Optional<SystemAccount> login(String email, String password) {
        Optional<SystemAccount> account = accountRepository.findByEmailAndIsActive(email, true);

        if (account.isPresent() && account.get().getPassword().equals(password)) {
            return account;
        }

        return Optional.empty();
    }

    @Override
    public String generateToken(SystemAccount account) {
        return jwtService.generateToken(
                account.getEmail(),
                account.getRole(),
                account.getUsername()
        );

    }
}