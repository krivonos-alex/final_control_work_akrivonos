package ru.mail.krivonos.al.finalcontrolwork.service.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BusinessCardDTO {

    private Long id;
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    private String fullName;
    @NotNull
    @Size(max = 20)
    private String workingTelephone;
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkingTelephone() {
        return workingTelephone;
    }

    public void setWorkingTelephone(String workingTelephone) {
        this.workingTelephone = workingTelephone;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
