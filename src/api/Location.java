package api;

import java.io.Serializable;
import java.security.SecureRandomParameters;

public class Location implements Serializable {
    private String address;
    private String town;
    private String postCode;


    public Location(String address, String town, String postCode) {
        this.address = address;
        this.town = town;
        this.postCode = postCode;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress() {
        this.address = address;
    }


    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }


    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }


}
