package app.model;

import jakarta.validation.constraints.*;

public class SignupForm {
    @NotEmpty (message= " ")
    @Size(min=4, max=30, message = "Käyttäjänimessä on oltava vähintään neljä merkkiä.")
    private String username = "";

    @NotEmpty (message= " ")
    @Size(min=4, max=30, message = "Salasanassa on oltava vähintään neljä merkkiä.")
    private String password = "";

    @NotEmpty (message= " ")
    @Size(min=4, max=30, message = "Salasana ei täsmää.")
    private String passwordCheck = "";

    @NotEmpty
    private String role = "USER";

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

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
}
