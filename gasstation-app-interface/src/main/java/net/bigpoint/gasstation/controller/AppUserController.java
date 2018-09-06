package net.bigpoint.gasstation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bigpoint.assessment.gasstation.exceptions.ConstraintsViolationException;
import net.bigpoint.gasstation.controller.mapper.UserMapper;
import net.bigpoint.gasstation.datatransferobject.UserDTO;
import net.bigpoint.gasstation.domainobject.UserDO;
import net.bigpoint.gasstation.service.driver.UserService;

@RestController
@RequestMapping("v1/user")
public class AppUserController {

	private final UserService userService;

	@Autowired
	public AppUserController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/getAccessToken/{username}/{password}")
	public UserDTO getAccessToken(@Valid @PathVariable String username, @PathVariable String password) {
		UserDO userDO = userService.getAccessToken(username, password);
		return UserMapper.makeUserDTO(userDO);
	}

	@GetMapping("/generateAccessToken/{username}/{password}")
	public UserDTO generateAccessToken(@Valid @PathVariable String username, @PathVariable String password)
			throws ConstraintsViolationException {
		UserDO userDO = userService.generateAccessToken(username, password);
		return UserMapper.makeUserDTO(userDO);
	}
}
