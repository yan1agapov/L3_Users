package validators;

import org.junit.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;



public class UserValidatorTest {
    Map<String, Boolean> emails = Map.of(
            "firstname_lastname@example.com", true,
            "email@subdomain.example.com", true,
            "email@example.co.jp", true,
            "plainaddress", false,
            "@example.com", false
    );
    Map<String, Boolean> names = Map.of(
            "Anna Maria", true,
            "John", true,
            "Andy  Scott ", false,
            " Dude  ", false,
            "I am Andy", false
    );
    Map<Integer, Boolean> ages = Map.of(
            150, true,
            13, true,
            8, true,
            0, false,
            151, false
    );
    @Test
    public void checkValidEmails(){
        for (Map.Entry<String, Boolean> email: emails.entrySet()){
            assertEquals(email.getValue(), UserValidator.isEmail(email.getKey()));
        }
    }

    @Test
    public void checkValidFirstNamesAndSecondNames(){
        for (Map.Entry<String, Boolean> name: names.entrySet()){
            assertEquals(name.getValue(),
                    UserValidator.isFirstNameOrSecondName(name.getKey()));
        }
    }

    @Test
    public void checkValidAges(){
        for (Map.Entry<Integer, Boolean> age: ages.entrySet()){
            assertEquals(age.getValue(),
                    UserValidator.isAge(age.getKey()));
        }
    }
}
