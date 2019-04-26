package in.education.student.user;


import in.education.student.model.Role;
import in.education.student.model.User;
import in.education.student.model.repository.RoleRepository;
import in.education.student.model.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class UserController {

	private UserService userService;
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	UserController(UserService userService, UserRepository userRepository,
			RoleRepository roleRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Value("${save}")
	String save;

	@Value("${change}")
	String change;

	private void loadDbData(ModelAndView mav) {

		mav.addObject("roles",
				StreamSupport.stream(roleRepository.findAll().spliterator(), false)
						.collect(Collectors.toMap(Role::getRoleId, Role::getRoleName)));
	}

	@GetMapping("/admin/user/add")
	public ModelAndView add() {

		ModelAndView mav = new ModelAndView("user", "user", new User());
		mav.addObject("buttonValue", save );
		mav.addObject("action","/admin/user/add");
		mav.addObject("method","Post");
		loadDbData(mav);

		return mav;
	}

	@PostMapping("/admin/user/add")
	public ModelAndView add(@ModelAttribute User user) {

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		User savedUser = userRepository.save(user);
		ModelAndView mav = new ModelAndView("user", "user", savedUser);

		// Check for Roles or not
		/*if(user.getRoles() == null){
			user.setRoles(Collections.emptyList());
		}*/

		if( !(savedUser.getUserId() > 0) ) {

			mav.addObject("message", "User Information is not inserted");
		} else {
			user.setPassword("");
			mav.addObject("user", user);
			mav.addObject("message", "User Information inserted successfully");
		}

		mav.addObject("buttonValue", save );
		mav.addObject("action","/admin/user/add");
		mav.addObject("method","Get");
		loadDbData(mav);

		return mav;
	}

	@GetMapping("/changePassword")
	public ModelAndView change() {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ModelAndView mav = new ModelAndView("changePassword", "user", user);
		mav.addObject("buttonValue", change );
		mav.addObject("action","/changePassword");
		mav.addObject("method","Post");

		return mav;
	}

	@PostMapping("/changePassword")
	public ModelAndView change(@ModelAttribute User user,
			@RequestParam String newPassword) {

		ModelAndView mav = new ModelAndView("changePassword", "user", user);
		mav.addObject("buttonValue", change );
		mav.addObject("action","/changePassword");
		mav.addObject("message", "Problem in Updating Password");
		mav.addObject("method","Post");

		if(StringUtils.isNotEmpty(newPassword)) {
			User LoggedInUser =
					(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Optional<User> userOptional = userService.changePassword(LoggedInUser.getUsername(),
					user.getPassword(), newPassword);

			if(userOptional.isPresent()) {
				if(userOptional.get().getUserId() > 0) {
					mav.addObject("message", "Password Updated");
				}
			}
		}
		return mav;
	}

	@GetMapping("/admin/usersList")
	public ModelAndView list() {

		ModelAndView mav = new ModelAndView("usersList", "user", new User());

		mav.addObject("buttonValue", "Get" );
		mav.addObject("action","/admin/usersList");
		mav.addObject("method","post");
		loadDbData(mav);

		return mav;
	}

	@PostMapping("/admin/usersList")
	public ModelAndView list(@ModelAttribute("user") User userData) {

		ModelAndView mav = new ModelAndView("usersList", "user", new User());
		mav.addObject("buttonValue", "Get" );
		mav.addObject("action","/admin/usersList");
		mav.addObject("method","Post");
		loadDbData(mav);
		mav.addObject("Role", "/admin");

		List<User> usersList =
				userRepository.findAllByRoles(userData.getRoles());

		mav.addObject("usersList", usersList);

		return mav;
	}


	@GetMapping("/admin/user/edit/{userId}/{operation}")
	public ModelAndView findByUserId(@PathVariable("userId") long userId,
			@PathVariable("operation") String operation) {

		ModelAndView mav = new ModelAndView("user", "user", new User());

		Optional<User> userData = userRepository.findById(userId);

		if(userData.isPresent()) {
			User user = userData.get();

			mav.addObject("user", user);

			List<Long> selectedRoleIds = user.getRoles()
					.stream()
					.map(Role::getRoleId)
					.distinct()
					.collect(Collectors.toList());
			mav.addObject("selectedRoleIds", selectedRoleIds);

//			System.out.println(selectedRoles);
		}

		mav.addObject("buttonValue", operation.toUpperCase());
		mav.addObject("action","/admin/user/" + operation); // operation as Update /
		// Delete
		mav.addObject("method","post");
		mav.addObject("Role", "/admin");

		loadDbData(mav);
		return mav;
	}

	@PostMapping("/admin/user/delete")
	public ModelAndView delete(@ModelAttribute("user") User user)  {

		Optional<User> userOptional = userRepository.findById(user.getUserId());



//		userRepository.delete(user);

		/*if(result > 0) {

			mav.addObject("message", "User Information deleted successfully");

			mav.addObject("buttonValue","Save");
			mav.addObject("action","/admin/user/add");

		} else {

			mav = new ModelAndView("user", "user", user);
			mav.addObject("message", "User Information is not deleted");

			mav.addObject("buttonValue","Delete");
			mav.addObject("action","/admin/user/delete");
		}*/

		ModelAndView mav = new ModelAndView("usersList", "user", user);

		mav.addObject("buttonValue", "Get" );
		mav.addObject("action","/admin/usersList");
		mav.addObject("method","Post");
		mav.addObject("Role", "/admin");
		loadDbData(mav);

		return mav;
	}

	// Get //

	/*// Update //
	@PostMapping("/student/update")
	public ModelAndView update(@ModelAttribute("studentData") StudentForm studentData)  {

		ModelAndView mav = new ModelAndView("studentList", "studentData", studentData);

		loadDbData(dbDataUtils, mav);

		int result = studentJdbcService.updateStudentData(studentData);

		if(result > 0) {

			mav.addObject("message", "Student Information updated successfully");
			mav.addObject("studentList", studentJdbcService.getSpecifiedStudentsData(studentData));

		} else {
			mav.setViewName("studentAdd");
			mav.addObject("message", "Students Information is not updated");
		}

		mav.addObject("Role", Role);
		return mav;
	}*/

	// Delete //


}



/*Map<Long, String> selectedRoles = user.getRoles()
					.stream()
					.collect(Collectors.toMap(Role::getRoleId, Role::getRoleName, (a1,
							a2) -> a1, LinkedHashMap::new));
			mav.addObject("selectedRoles", selectedRoles);*/

/*mav.addObject("roles",
				StreamSupport.stream(roleRepository.findAll().spliterator(), false)
						.collect(Collectors.toMap(Role::getRoleId, Role::getRoleName)));*/

/*user.getRoles()
					.stream()
					.collect(Collectors.toMap(Role::getRoleId, Role::getRoleName))
					.forEach((k,v) -> System.out.println(k+" : "+v));

			Map<Long, String> selectedRoles = user.getRoles()
										.stream()
										.collect(Collectors.toMap(Role::getRoleId, Role::getRoleName));*/

/*userData.getRoles()
				.stream()
				.map(Role :: getRoleId)
				.forEach(role -> System.out.println(role+ ", "));*/