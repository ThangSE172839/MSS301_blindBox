package mss301.mircorservice.msaccount.service;

import mss301.mircorservice.msaccount.model.SystemAccount;

import java.util.Optional;

public interface AccountService {
    Optional<SystemAccount> login (String email, String password);
    String generateToken(SystemAccount account);
}
