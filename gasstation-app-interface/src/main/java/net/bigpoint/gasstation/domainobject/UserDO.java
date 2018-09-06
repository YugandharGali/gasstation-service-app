package net.bigpoint.gasstation.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = { "username", "accessToken" }))
public class UserDO {

	@Id
	@GeneratedValue
	private Long userid;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(nullable = false)
	@NotNull(message = "Username can not be null!")
	private String username;

	@Column(nullable = false)
	@NotNull(message = "Password can not be null!")
	private String password;

	@Column(nullable = false)
	@NotNull(message = "Access Token can not be null!")
	private String accessToken;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime expireDate;

	@Column(nullable = false)
	private Boolean expired = false;

	private UserDO() {
	}

	public UserDO(String username, String password, String accessToken, ZonedDateTime expireDate) {
		this.username = username;
		this.password = password;
		this.accessToken = accessToken;
		this.expireDate = expireDate;
		this.expired = Boolean.FALSE;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public ZonedDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(ZonedDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

}
