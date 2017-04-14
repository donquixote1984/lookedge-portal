package lookedge.data;

import lookedge.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao  extends JpaRepository<Account, String> {
    Account findOneByEmail(String email);
}
