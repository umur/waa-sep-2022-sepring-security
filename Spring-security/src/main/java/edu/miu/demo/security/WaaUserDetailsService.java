package edu.miu.demo.security;

import edu.miu.demo.model.User;
import edu.miu.demo.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("userDetailsService")
@Transactional
@RequiredArgsConstructor
public class WaaUserDetailsService implements UserDetailsService {

    private final UsersRepo repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);
        if (user.isPresent()) {
            return new WaaUserDetails(user.get());
        }
        throw new UsernameNotFoundException("User not found");
    }

}
