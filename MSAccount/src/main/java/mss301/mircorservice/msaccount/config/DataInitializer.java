package mss301.mircorservice.msaccount.config;

import mss301.mircorservice.msaccount.model.SystemAccount;
import mss301.mircorservice.msaccount.repostiory.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class DataInitializer implements CommandLineRunner {


    private final AccountRepository accountRepository;

    public DataInitializer(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) {
        try {
            System.out.println("DataInitializer: Checking if accounts exist...");
            long count = accountRepository.count();
            System.out.println("DataInitializer: Found " + count + " existing accounts");

            if (count == 0) {
                System.out.println("DataInitializer: Creating initial accounts...");
                List<SystemAccount> accounts = Arrays.asList(
                        create( "adminsys", "admin@blindboxes.vn", "@2", 1, 1),
                        create( "johndoe", "john@blindboxes.vn", "@2", 4, 1),
                        create( "modmichel", "michel@blindboxes.vn", "@2", 2, 1),
                        create( "vanvan", "vanavan@blindboxes.vn", "@2", 4, 0),
                        create( "devops", "dev@blindboxes.vn", "@2", 3, 1)
                );
                accountRepository.saveAll(accounts);
                System.out.println("DataInitializer: Successfully created " + accounts.size() + " accounts");
            }
        } catch (Exception e) {
            System.err.println("DataInitializer ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // nhận isActive as 1/0 và chuyển sang boolean; accountId as int để phù hợp với setAccountId(int)
    private SystemAccount create(String username, String email, String rawPassword, int role, int isActiveInt) {
        SystemAccount a = new SystemAccount();
        a.setUsername(username);
        a.setEmail(email);
        a.setPassword(rawPassword);
        a.setRole(role);
        a.setActive(isActiveInt == 1);
        return a;
    }
}
