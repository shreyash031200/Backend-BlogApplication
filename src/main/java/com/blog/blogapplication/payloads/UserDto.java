package com.blog.blogapplication.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min=4, message = "Username must be min 4 Character !!")
    private String name;
    @Email(message = "email is not valid!!")
    private String email;
    @NotEmpty
    @Size(min =3, max=10, message = "password must be minimum 3 and  maximum 10 character !!")
    private String password;
    @NotNull
    private String about;
	public UserDto(int id, @NotEmpty @Size(min = 4, message = "Username must be min 4 Character !!") String name,
			@Email(message = "email is not valid!!") String email,
			@NotEmpty @Size(min = 3, max = 10, message = "password must be minimum 3 and  maximum 10 character !!") String password,
			@NotNull String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
    
    

}
