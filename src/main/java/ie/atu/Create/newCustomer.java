package ie.atu.Create;

import ie.atu.DisplayMsg;

public class newCustomer extends Person implements DisplayMsg {

    public newCustomer(){
        super();
    }

    @Override
    public String toString(){
        return "New Customer added!\n" + super.toString();
    }
}
