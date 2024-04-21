package ie.atu;

public class Menus {

    public static String LogInMenu(){
        String loginMenu = ("(1)\t Sign in as User\n(2)\t Sign in as Staff\n(3)\t Sign up new User\n\n(0)\t Exit");
        return loginMenu;
    }

    public static String customerMenu(){
        String cMenu = ("(1)\t View Product\n(2)\t View Stores\n(3)\t Edit Personal Info\n(4)\t Delete Account!\n\n(0)\t Logout");
        return cMenu;
    }

    public static String staffMenu(){
        String sMenu = ("(1)\t Product\n(2)\t Store\n(3)\t Staff\n(4)\t View customer\n(5)\t Delete Account!\n\n(0)\t Logout");
        return sMenu;
    }

    public static String selectionMenu(){
        String selMenu = ("(1)\t Create new\n(2)\t Read from existing\n(3)\t Edit existing\n(4)\t Delete from existing\n\n(0)\t Exit");
        return selMenu;
    }
}
