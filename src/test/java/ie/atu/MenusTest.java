package ie.atu;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MenusTest {
    Menus Menu;
    @Test
    //Test the LogInMenu
    void testMenu1(){
        Menu = new Menus();
        String testMenu = ("(1)\t Sign in as User\n(2)\t Sign in as Staff\n(3)\t Sign up new User\n\n(0)\t Exit");
        assertEquals(testMenu, Menu.LogInMenu());
        System.out.println("Test menu 1 complete");
    }

    @Test
    //Test the customerMenu
    void testMenu2(){
        Menu = new Menus();
        String testMenu = ("(1)\t View Product\n(2)\t View Stores\n(3)\t Edit Personal Info\n(4)\t Delete Account!\n\n(0)\t Logout");
        assertEquals(testMenu, Menu.customerMenu());
        System.out.println("Test menu 2 complete");
    }

    @Test
    //Test the staffMenu
    void testMenu3(){
        Menu = new Menus();
        String testMenu = ("(1)\t Product\n(2)\t Store\n(3)\t Staff\n(4)\t View customer\n(5)\t Delete Account!\n\n(0)\t Logout");
        assertEquals(testMenu, Menu.staffMenu());
        System.out.println("Test menu 3 complete");
    }

    @Test
    //Test the selectionMenu
    void testMenu4(){
        Menu = new Menus();
        String testMenu = ("(1)\t Create new\n(2)\t Read from existing\n(3)\t Edit existing\n(4)\t Delete from existing\n\n(0)\t Exit");
        assertEquals(testMenu, Menu.selectionMenu());
        System.out.println("Test menu 4 complete");
    }

    @Test
    void testMenu5(){
        Menu = new Menus();
        String testMenu = ("(1)\t Create new\n(2)\t Read from existing\n(3)\t Edit existing\n(4)\t Delete from existing\n(5)\t Delete inventory\n\n(0)\t Exit");
        assertEquals(testMenu,Menu.productMenu());
    }
}
