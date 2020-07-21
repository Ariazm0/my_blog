package model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Ariazm
 * Date: 2020-07-21
 * Time: 18:14
 */
public class User {
    private String name;
    private int uerId;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUerId() {
        return uerId;
    }

    public void setUerId(int uerId) {
        this.uerId = uerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", uerId=" + uerId +
                ", password='" + password + '\'' +
                '}';
    }
}
