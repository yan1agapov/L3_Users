package validators;

import org.junit.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {
    @Test
    public void checkValidEmails(){
        assertEquals(true,EmailValidator.isEmail("firstname_lastname@example.com"));
    }
}
