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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
	public ModelAndView list(@ModelAttribute("userData") User userData) {

		ModelAndView mav = new ModelAndView("usersList", "user", new User());
		mav.addObject("buttonValue", "Get" );
		mav.addObject("action","/admin/usersList");
		mav.addObject("method","Post");
		loadDbData(mav);
		mav.addObject("Role", "/admin");

		/*userData.getRoles()
				.stream()
				.map(Role :: getRoleId)
				.forEach(role -> System.out.println(role+ ", "));*/

		List<User> usersList =
				userRepository.findAllByRoles(userData.getRoles());

		mav.addObject("usersList", usersList);

		return mav;
	}


	@GetMapping("/admin/user/edit/{userId}/{operation}")
	public ModelAndView findById(@PathVariable("userId") long userId,
			@PathVariable("operation") String operation) {

		ModelAndView mav = new ModelAndView("user", "user", new User());

		System.out.println(userId);

		Optional<User> userData = userRepository.findById(userId);

		if(userData.isPresent()) {
			User user = userData.get();

			System.out.println(user.getUsername()+", "+ user.getUserDesc());

			mav.addObject("user", user);

			/*user.getRoles()
					.stream()
					.collect(Collectors.toMap(Role::getRoleId, Role::getRoleName))
					.forEach((k,v) -> System.out.println(k+" : "+v));

			Map<Long, String> selectedRoles = user.getRoles()
										.stream()
										.collect(Collectors.toMap(Role::getRoleId, Role::getRoleName));

			mav.addObject("selectedRoles", selectedRoles);*/
		}




		mav.addObject("buttonValue", operation.toUpperCase());
		mav.addObject("action","/admin/user/" + operation); // operation as Update /
		// Delete
		mav.addObject("method","post");
		mav.addObject("Role", "/admin");

		loadDbData(mav);



		/*mav.addObject("roles",
				StreamSupport.stream(roleRepository.findAll().spliterator(), false)
						.collect(Collectors.toMap(Role::getRoleId, Role::getRoleName)));*/

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
	}

	// Delete //
	@PostMapping("/student/delete")
	public ModelAndView delete(@ModelAttribute("studentData") StudentForm studentData)  {

		ModelAndView mav = new ModelAndView("studentAdd", "studentData",
				new StudentForm());

		int result = studentJdbcService.deleteStudentData(studentData);

		if(result > 0) {

			mav.addObject("message", "Student Information deleted successfully");

			mav.addObject("buttonValue","Save");
			mav.addObject("action",Role + "/student/add");

		} else {

			mav = new ModelAndView("studentAdd", "studentData",
					studentData);
			mav.addObject("message", "Students Information is not deleted");

			mav.addObject("buttonValue","Delete");
			mav.addObject("action",Role + "/student/delete");
		}

		loadDbData(dbDataUtils, mav);

		mav.addObject("Role", Role);
		return mav;
	}*/

}
