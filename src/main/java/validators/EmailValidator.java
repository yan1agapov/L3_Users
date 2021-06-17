package validators;

import exceptions.IncorrectEmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

 public class EmailValidator {
     static String regex = "\\w+@\\w+\\.[a-z][a-z|.]+";

     public static boolean isEmail(String email) {
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(email);
         return matcher.find();
     }
 }
