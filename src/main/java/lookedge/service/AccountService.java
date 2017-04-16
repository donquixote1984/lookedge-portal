package lookedge.service;

import lookedge.data.AccountDao;
import lookedge.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService  implements UserDetailsService {

    @Autowired
    private AccountDao accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    protected void initialize() {
        //save(new Account("user", "demo", "ROLE_USER"));
        //save(new Account("admin", "admin", "ROLE_ADMIN"));
    }

    @Transactional
    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findOneByEmail(username);
        if(account == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return createUser(account);
    }

    public Boolean checkUserExists(String username){
        Account account = accountRepository.findOneByEmail(username);
        if(account != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public void signin(Account account) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(account));
    }

    private Authentication authenticate(Account account) {
        return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));
    }

    private User createUser(Account account) {
        return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
    }

    private GrantedAuthority createAuthority(Account account) {
        return new SimpleGrantedAuthority(account.getRole());
    }
}
