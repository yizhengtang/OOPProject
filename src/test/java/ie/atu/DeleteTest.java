package ie.atu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("Test delete started");
    }
    @BeforeEach
    void beforeEach(){
        Delete delete = new Delete();
    }
    @Test
    void testDeleteAccount_Success() {
    }
    @Test
    void testDeleteAccount_Fail() {

    }
    @AfterAll
    static void afterAll() {
        System.out.println("Test delete finished");
    }
}