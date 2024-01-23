package com.viimeiset.koiranvaatekauppa.domain;

import jakarta.validation.constraints.*;

public class SignupForm {
	
	  @NotEmpty
	    @Size(min=5, max=30)
	    private String username = "";

	    @NotEmpty
	    @Size(min=7, max=30)
	    private String password = "";

	    @NotEmpty(message = " ")
	    @Size(min=7, max=30)
	    private String passwordCheck = "";

	    @NotEmpty
	    private String role = " ";
	    
	    @NotEmpty(message = "Etunimi ei voi olla tyhjä")
	    private String etunimi= " ";
	    
	    @NotEmpty(message = "Sukunimi ei voi olla tyhjä")
	    private String sukunimi= " ";
	    
	    @NotEmpty(message = "Sähköposti ei voi olla tyhjä")
	    private String sahkoposti= " ";

		public String getSahkoposti() {
			return sahkoposti;
		}

		public void setSahkoposti(String sahkoposti) {
			this.sahkoposti = sahkoposti;
		}

		public String getEtunimi() {
			return etunimi;
		}

		public void setEtunimi(String etunimi) {
			this.etunimi = etunimi;
		}

		public String getSukunimi() {
			return sukunimi;
		}

		public void setSukunimi(String sukunimi) {
			this.sukunimi = sukunimi;
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
