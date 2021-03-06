package com.authentication.serviceimpl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.authentication.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(int i, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = i;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	/*
	 * Think of a GrantedAuthority as being a "permission" or a "right". Those
	 * "permissions" are (normally) expressed as strings (with the getAuthority()
	 * method). Those strings let you identify the permissions and let your voters
	 * decide if they grant access to something.
	 */
	/*
	 * Roles (as they are used in many examples) are just "permissions" with a
	 * naming convention that says that a role is a GrantedAuthority that starts
	 * with the prefix ROLE_. There's nothing more. A role is just a
	 * GrantedAuthority - a "permission" - a "right".
	 */	
	/*
	 * But still: a role is just an authority with a special ROLE_ prefix. So in
	 * Spring security 3 @PreAuthorize("hasRole('ROLE_XYZ')") is the same
	 * as @PreAuthorize("hasAuthority('ROLE_XYZ')") and in Spring security
	 * 4 @PreAuthorize("hasRole('XYZ')") is the same
	 * as @PreAuthorize("hasAuthority('ROLE_XYZ')").
	 */
	public static UserDetailsImpl build(User user) {
		

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(),new ArrayList<GrantedAuthority>());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
