package net.bigpoint.gasstation.controller.mapper;

import net.bigpoint.gasstation.datatransferobject.UserDTO;
import net.bigpoint.gasstation.domainobject.UserDO;

public class UserMapper {

	public static UserDTO makeUserDTO(UserDO userDO) {
		return new UserDTO(userDO.getUserid(), userDO.getUsername(), userDO.getPassword(), userDO.getAccessToken(),
				userDO.getExpireDate(), userDO.getExpired());
	}

}
