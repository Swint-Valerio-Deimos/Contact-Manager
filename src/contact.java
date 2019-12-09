public class contact {

    private String name;
    private String phoneNumber;

    public contact(String name, String number) {
        this.name = name;
        this.phoneNumber = number;
    }

    public void setName (String name){
        this.name = name;
    }

    public void setPhoneNumber (String number){
        this.phoneNumber = number;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

}
