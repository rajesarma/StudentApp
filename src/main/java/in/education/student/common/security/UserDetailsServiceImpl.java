package in.education.student.common.security;

import in.education.student.model.User;
import in.education.student.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

//	JDBC Code
	/*@Autowired
	AuthenticationRepository authenticationRepository;*/

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		//	JDBC Code
		/*User user = null;
		try {
			user = authenticationRepository.authenticateUser(userName);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		if (user != null) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())) ;
			return new CustomUserDetails(user);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}*/


		Optional<User> userOptional = userRepository.findByUserName(userName);
		if(userOptional.isPresent()){
			User user = userOptional.get();
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())) ;
			return new CustomUserDetails(user);
		} else {
			throw new UsernameNotFoundException("user not found" + userName);
		}
	}

	private static class CustomUserDetails extends User implements UserDetails{

		// Any login attempt after 20 consecutive failed login attempts will return 401
		private static final int MAX_FAILED_LOGIN_ATTEMPTS = 20;
		private static final long serialVersionUID = 6020417971232843532L;

		public CustomUserDetails(User user) {
			super(user);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {

			String[] roles = {};

			if(this.getRoles() != null){

				// Prepend ROLE_ to every role in DB
				roles = this.getRoles()
						.stream()
						.map(m -> "ROLE_" + m).toArray(String[]::new);
			}

			/*return getRoles()
				.stream()
				.map(role-> new SimpleGrantedAuthority("ROLE_"+role.getRoleName()))
				.collect(Collectors.toList());*/

			return AuthorityUtils.createAuthorityList(roles);
		}

		@Override
		public String getUsername() {
			return null;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {

			Long prevFailedAttempts = this.getFailedLoginAttempts();
			return prevFailedAttempts == null || prevFailedAttempts <= MAX_FAILED_LOGIN_ATTEMPTS;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return this.getDisabled() == null || !this.getDisabled();
		}
	}
}

/*Optional<User> optionalUser = usersRepository.findByFirstName(userName);
		return Optional.ofNullable(optionalUser).orElseThrow(()->new UsernameNotFoundException("Username Not Found"))
				.map(UserDetailsImpl::new).get();*/

//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		/*final org.springframework.security.core.userdetails.User.UserBuilder userBuilder
				= org.springframework.security.core.userdetails.User.builder().passwordEncoder(encoder::encode);*/

		/*org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
				org.springframework.security.core.userdetails.User.withUserDetails(new CustomUserDetails(user));*/

		/*builder = org.springframework.security.core.userdetails.User.withUsername(userName);
			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
			builder.roles(user.getRoles());

			System.out.println(user.getRoles());

			return builder.build();*/

//		Iterable<User> users = userRepository.findAll();
		/*Optional<User> userOptional =
				userRepository.findById(userName.toLowerCase(Locale.US));*/