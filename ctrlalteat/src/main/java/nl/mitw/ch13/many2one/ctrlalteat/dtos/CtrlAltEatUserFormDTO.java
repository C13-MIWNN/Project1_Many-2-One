package nl.mitw.ch13.many2one.ctrlalteat.dtos;

/**
 * Author Linda Munsterman
 * purpose for the class
 **/

public class CtrlAltEatUserFormDTO {

    private String name;

    private String password;
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
