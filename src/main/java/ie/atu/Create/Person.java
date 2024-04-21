package ie.atu.Create;

public class Person {
    private String username;
    private String email;
    private int password;

    private String address;


    public Person(){
    }

    public String getUsername(String userName) {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword(int passWord) {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        String displayAdded = ("Name: " + username + "\nEmail: " + email + "\nPassword: " + password);
        return displayAdded;
    }
}
