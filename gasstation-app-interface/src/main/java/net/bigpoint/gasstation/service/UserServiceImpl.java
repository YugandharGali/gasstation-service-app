package net.bigpoint.gasstation.service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Base64;

import javax.persistence.EntityNotFoundException;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import net.bigpoint.assessment.gasstation.exceptions.ConstraintsViolationException;
import net.bigpoint.gasstation.dataaccessobject.UserRepository;
import net.bigpoint.gasstation.domainobject.UserDO;

@Service
public class UserServiceImpl implements UserService {

	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	public UserServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDO getAccessToken(String username, String password) throws EntityNotFoundException {
		return findUserChecked(username, password);
	}

	private UserDO findUserChecked(String username, String password) {
		UserDO userDO = userRepository.findUser(username, password);
		if (userDO == null) {
			throw new EntityNotFoundException("Could not find User entity.");
		}
		return userDO;
	}

	@Override
	public UserDO generateAccessToken(String username, String password) throws ConstraintsViolationException {

		String accessToken = Base64.getEncoder().withoutPadding()
				.encodeToString((username + ":" + password).getBytes());
		ZonedDateTime dateCreated = ZonedDateTime.now().plusMinutes(30);
		UserDO user = new UserDO(username, password, accessToken, dateCreated);
		UserDO userRes;
		try {
			userRes = userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("Some constraints are thrown due to driver creation", e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return userRes;
	}

	@Override
	public boolean validateAccessToken(String accessToken) throws EntityNotFoundException {
		UserDO userDO = userRepository.findUserAccessToken(accessToken);
		if (userDO == null) {
			System.out.println("Could not find User entity.");
		} else {
			LocalDateTime currentDateTime = ZonedDateTime.now(). toLocalDateTime();
			LocalDateTime expireDateTime = userDO.getExpireDate().toLocalDateTime();
			boolean retVal = currentDateTime.isBefore(expireDateTime);
			System.out.println("currentDate :" + currentDateTime);
			System.out.println("expireDate :" + expireDateTime);
			System.out.println("expireDate > currentDate :"+ retVal);
			if(retVal) {
				return true;
			}else {
				// mark token as expired.
				UserDO user = userRepository.findOne(userDO.getUserid());
				user.setExpired(Boolean.TRUE);
				userRepository.save(user);
			}
			
		}
		return false;
	}

}
