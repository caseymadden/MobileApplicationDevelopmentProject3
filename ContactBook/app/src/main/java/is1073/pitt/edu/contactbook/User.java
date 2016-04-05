package is1073.pitt.edu.contactbook;

public class User {
    private String userID;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phoneNumber;
    private String email;

    public User(String userID, String firstName, String lastName, String address1, String address2, String city, String state, String zip, String country, String phoneNumber, String email){
        this.setUserID(userID);
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setAddress1(address1);
        this.setAddress2(address2);
        this.setCity(city);
        this.setState(state);
        this.setZip(zip);
        this.setCountry(country);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
    }


    public String getUserID() { return userID; }

    public void setUserID(String userID) { this.userID = userID; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress1() { return address1; }

    public void setAddress1(String address1) {this.address1 = address1; }

    public String getAddress2() { return address2; }

    public void setAddress2(String address2) {this.address2 = address2; }

    public String getCity(){ return city; }

    public void setCity(String city){ this.city = city; }

    public String getState(){ return state; }

    public void setState(String state){ this.state = state; }

    public String getZip(){ return zip; }

    public void setZip(String zip){ this.zip = zip; }

    public String getCountry(){ return country; }

    public void setCountry(String country){ this.country = country; }

    public String getPhoneNumber(){ return phoneNumber; }

    public void setPhoneNumber(String phoneNumber){ this.phoneNumber = phoneNumber; }

    public String getEmail(){ return email; }

    public void setEmail(String email){ this.email = email; }




    @Override
    public String toString(){
        return this.lastName + ", " + this.firstName;
    }
}