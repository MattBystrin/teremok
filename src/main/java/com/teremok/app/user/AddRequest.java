package com.teremok.app.user;

import com.teremok.app.auth.RegisterRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddRequest extends RegisterRequest{
	private Role role;
}
