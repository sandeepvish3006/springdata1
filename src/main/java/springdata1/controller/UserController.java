package springdata1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springdata1.dao.TestRepo;
import springdata1.dao.UserRepository;
import springdata1.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TestRepo testRepo;
	
	@RequestMapping("/add")
	public @ResponseBody String addUser(@RequestParam String name, @RequestParam String email) {
	
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		userRepository.save(user);

		return "saved";
	}

	@GetMapping("/getAll")
	public @ResponseBody Iterable<User> getAllUser() {
		return userRepository.findAll();
	}

	@GetMapping("/findByEmail")
	public @ResponseBody Iterable<User> getByEmail(@RequestParam String email) {
		return userRepository.findByEmail(email);
	}

	@GetMapping("/findByName")
	public @ResponseBody User findByName(@RequestParam String name) {
		return userRepository.findByName(name);
	}

	@GetMapping("/findByNameOrEmail")
	public @ResponseBody Iterable<User> findByNameOrEmail(@RequestParam String name, @RequestParam String email) {
		return userRepository.findByNameOrEmail(name, email);
	}
	
	@GetMapping("/updateByEmail")
	public @ResponseBody int updateByEmail(@RequestParam String name, @RequestParam String email){
		return userRepository.updateByEmail(name,email);
	}
	
	@GetMapping("deleteByEmail")
	public @ResponseBody void deleteByEmail(@RequestParam String email){
		
		userRepository.deleteByEmail(email);
	}
	
	@GetMapping("/emailAndName")
	public @ResponseBody Iterable<User> findByNameAndEmail(@RequestParam String name, @RequestParam String email){
		
		return userRepository.findByNameAndEmail(name,email);
	}
	
	@GetMapping("/email")
	public @ResponseBody Iterable<User> findByName1(@RequestParam String email){
		
		return testRepo.findByEmail(email);
	}
	
}
