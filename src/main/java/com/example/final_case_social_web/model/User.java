package com.example.final_case_social_web.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "userTable")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
//    @Length(min = 5, max = 10)
    private String password;

    @Column(nullable = false)
//    @Length(min = 5, max = 10)
    private String confirmPassword;

    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    private String fullName;

    @Column(unique = true, nullable = false)
    @Email(message = "Sai định dạng email")
    private String email;

    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$",message = "Không phải số điện thoại hợp lệ")
    private String phone;

    private String favorite;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "Phải là ngày trong quá khứ")
    private LocalDate dateOfBirth;

    private String avatar = "assets/images/defaultAva.png";

    private String cover ="assets/images/face-map_ccexpress.png";

    private String address;

//    @NotNull
//    private int status;
////    (1 là đang hoạt động,2 là block)

    public User() {
    }

    public User(Long id, String username, String password, String confirmPassword, boolean enabled, Set<Role> roles, String fullName, String email, LocalDate dateOfBirth, String avatar, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.enabled = enabled;
        this.roles = roles;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
        this.address = address;
    }

    public User(String username, String password, String confirmPassword, boolean enabled, Set<Role> roles, String fullName, String email, LocalDate dateOfBirth, String avatar, String address) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.enabled = enabled;
        this.roles = roles;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
        this.address = address;
    }

    public User(Long id, String username, String password, String confirmPassword, boolean enabled, Set<Role> roles, String fullName, String email, String phone, String favorite, LocalDate dateOfBirth, String avatar, String cover, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.enabled = enabled;
        this.roles = roles;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.favorite = favorite;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
        this.cover = cover;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
