package ie.atu;

public class newCustomer extends Person implements DisplayMsg {

    public newCustomer(){
        super();
    }

    @Override
    public String toString(){
        return "New Customer added!\n" + super.toString();
    }
}
