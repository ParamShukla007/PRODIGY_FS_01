package com.example.authentication.user_authentication.entities;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Column;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;
    @NotBlank(message = "Name is required !!")
    private String name;
    @Column(unique = true)
    @NotBlank(message = "Email is required !!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please enter a valid email address")
    private String email;
    @NotBlank(message = "Password is required !!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Password must be at least 8 characters long and contain both letters and numbers")
    private String password;
    @Column(length = 10)
    @NotBlank(message = "Phone number is required !!")
    @Pattern(regexp = "^[0-9]{10}$", message = "Please enter a valid phone number")
    private String phone_no;
    private String role;
    @Column(length = 500)
    private String about;

    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
    public String getPhone_no() {
        return phone_no;
    }
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public User() {
    }
    public User(int user_id, @NotBlank(message = "Name is required !!") String name,
            @NotBlank(message = "Email is required !!") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please enter a valid email address") String email,
            @NotBlank(message = "Password is required !!") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Password must be at least 8 characters long and contain both letters and numbers") String password,
            @NotBlank(message = "Phone number is required !!") @Pattern(regexp = "^[0-9]{10}$", message = "Please enter a valid phone number") String phone_no,
            String role, String about) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_no = phone_no;
        this.role = role;
        this.about = about;
    }
    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", name=" + name + ", email=" + email + ", password=" + password
                + ", phone_no=" + phone_no + ", role=" + role + ", about=" + about + "]";
    }
}

