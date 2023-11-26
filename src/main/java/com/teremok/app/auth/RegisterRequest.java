package com.teremok.app.auth;

import com.teremok.app.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	private String firstname;
	private String lastname;
	private String email;
	private String pass;
	private Role role;
}
