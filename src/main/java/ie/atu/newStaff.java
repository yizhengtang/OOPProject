package ie.atu;

public class newStaff extends Person implements DisplayMsg {
    private int AssignedStore;

    public newStaff(){
        super();
        AssignedStore = 0;
    }

    public void setAssignedStore(int AssignedStore){
        this.AssignedStore = AssignedStore;
    }

    public int getAssignedStore(int assignedStore){
        return AssignedStore;
    }

    @Override
    public String toString(){
        String displayAdded = "New Staff added!\n" + super.toString() + "\nAssigned to Store ID: " + AssignedStore;
        return displayAdded;
    }
}
