package mss301.mircorservice.msaccount.repostiory;

import mss301.mircorservice.msaccount.model.SystemAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<SystemAccount, Integer> {
    Optional<SystemAccount> findByEmailAndIsActive(String email, boolean isActive);
    Optional<SystemAccount> findByEmail(String email);
}
