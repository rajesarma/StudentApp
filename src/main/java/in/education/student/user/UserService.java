package in.education.student.user;

import in.education.student.model.User;
import in.education.student.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	Optional<User> changePassword(String username, String password, String newPassword) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		Optional<User> userOptional =
				userRepository.findByUsername(username.toLowerCase(Locale.US));

		if(!userOptional.isPresent()) {
			return Optional.empty();
		} else {
			User user = userOptional.get();
			if(encoder.matches(password, user.getPassword())) {

				user.setPassword(encoder.encode(newPassword));
				user.setLastPasswordChange(new Date());
				User savedUser = userRepository.save(user);

				return Optional.of(savedUser);
			} else {
				return Optional.empty();
			}
		}
	}

}
