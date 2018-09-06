package net.bigpoint.gasstation.service;

import javax.persistence.EntityNotFoundException;

import net.bigpoint.assessment.gasstation.exceptions.ConstraintsViolationException;
import net.bigpoint.gasstation.domainobject.UserDO;

public interface UserService {

	UserDO getAccessToken(String username, String password) throws EntityNotFoundException;

	UserDO generateAccessToken(String username, String password) throws ConstraintsViolationException;

	boolean validateAccessToken(String accessToken) throws EntityNotFoundException;
}
