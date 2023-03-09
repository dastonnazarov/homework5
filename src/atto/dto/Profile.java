package atto.dto;

import atto.enums.ProfileRole;
import atto.enums.ProfileStatus;

import java.time.LocalDate;


public class Profile {
    private Integer id;
    private String name;
    private String surname;
    private String phone;//unique number
    private String password;
    private String card_number;
    private LocalDate create_date;
    private ProfileStatus status;
    private ProfileRole role;


    public Profile(Integer id, String name, String surname, String phone, String password, String card_number, LocalDate create_date, ProfileStatus status, ProfileRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.password = password;
        this.card_number = card_number;
        this.create_date = create_date;
        this.status = status;
        this.role = role;
    }

    public Profile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public LocalDate getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }

    public ProfileStatus getStatus() {
        return status;
    }

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }

    public ProfileRole getRole() {
        return role;
    }

    public void setRole(ProfileRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", create_date=" + create_date +
                ", status='" + status + '\'' +
                ", role=" + role +
                '}';
    }
}
