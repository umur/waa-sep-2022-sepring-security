package edu.miu.lab5.service.impl;

import edu.miu.lab5.entity.User;
import edu.miu.lab5.model.UserDetailsAdapter;
import edu.miu.lab5.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImplAdapter implements UserDetailsService {

	private final UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username)
							.orElseThrow(() -> new RuntimeException("Cannot find user: " + username));
		return new UserDetailsAdapter(user);
	}
}
