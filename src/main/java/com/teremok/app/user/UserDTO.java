package com.teremok.app.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String role;
	private String specie;

	public static UserDTO fromUser(User user) {
		return new UserDTO(
			user.getId(),
			user.getFirstname(),
			user.getLastname(),
			user.getEmail(),
			user.getRole().toString(),
			user.getSpecie().getName()
		);
	}
}
