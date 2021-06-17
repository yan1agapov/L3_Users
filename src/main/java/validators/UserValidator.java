package validators;

import exceptions.IncorrectEmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

 public class UserValidator {

     static String emailRegex = "\\w+@\\w+\\.([a-z]+\\.*)+";
     static String nameRegex = "([A-Z][a-z]*\\s?)*[A-Z][a-z]*";
     static String ageRegex = "150|1[0-4]\\d|[1-9]\\d|[1-9]";

     public static boolean isEmail(String email) {
         Pattern emailValidatorPattern = Pattern.compile(emailRegex);
         Matcher emailMatcher = emailValidatorPattern.matcher(email);
         return emailMatcher.matches();
     }

     public static boolean isFirstNameOrSecondName(String name){
         Pattern nameValidatorPattern = Pattern.compile(nameRegex);
         Matcher nameMatcher = nameValidatorPattern.matcher(name);
         return nameMatcher.matches();
     }

     public static boolean isAge(int age){
         Pattern ageValidatorPattern = Pattern.compile(ageRegex);
         Matcher ageMatcher = ageValidatorPattern.matcher(Integer.toString(age));
         return ageMatcher.matches();
     }
 }
