package com.teremok.app.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasswdRequest {
	private String oldPass;
	private String newPass;
	private String confirmPass;
}
