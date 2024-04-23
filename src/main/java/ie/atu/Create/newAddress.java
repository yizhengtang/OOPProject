package ie.atu.Create;

import ie.atu.Interfaces.DisplayMsg;

public class newAddress implements DisplayMsg {
    private String address;
    private String postalcode;

    private String city;
    private String country;

    public newAddress(){}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toString(){
        return "Address: " + address + "\nPostal Code: " + postalcode + "\nCity: " + city + "\nCountry: " + country;
    }
}
