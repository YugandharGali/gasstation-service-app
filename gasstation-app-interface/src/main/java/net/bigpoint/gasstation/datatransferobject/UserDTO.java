package net.bigpoint.gasstation.datatransferobject;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
	@JsonIgnore
	private Long id;

	@NotNull(message = "Username can not be null!")
	private String username;

	@NotNull(message = "Password can not be null!")
	private String password;

	private String accessToken;

	private String expireDate;

	private String expired;

	public UserDTO(Long id, String username, String password, String accessToken, ZonedDateTime zonedDateTime,
			Boolean expired) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.accessToken = accessToken;
		this.expireDate = zonedDateTime.toString();
		this.expired = expired ? "Y" : "N";
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
