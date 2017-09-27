package springdata1.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import springdata1.dao.UserRepository;
import springdata1.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
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
	
	@Modifying
	@Transactional
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
	
	@GetMapping("/emailAndName")
	public @ResponseBody Iterable<User> findByAndSort(@RequestParam String name, @RequestParam String email){
		
		return userRepository.findByAndSort(name, new Sort("name"));
	}
}
