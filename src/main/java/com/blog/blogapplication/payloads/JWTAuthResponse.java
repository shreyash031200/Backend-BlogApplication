package com.blog.blogapplication.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthResponse {

    private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
    
}
