package com.sda.group.family_apps.user;


import org.passay.*;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {


   @Override
   public boolean isValid(String password, ConstraintValidatorContext context) {
      Properties props = new Properties();
       try {
           props.load(new FileInputStream("src/main/resources/messages/messages.properties"));
       } catch (IOException e) {
           e.printStackTrace();
       }
       MessageResolver resolver = new PropertiesMessageResolver(props);
      PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(

              // at least 8 characters
              new LengthRule(6, 30),

              // at least one upper-case character
              new CharacterRule(EnglishCharacterData.UpperCase, 1),

              // at least one lower-case character
              new CharacterRule(EnglishCharacterData.LowerCase, 1),

              // at least one digit character
              new CharacterRule(EnglishCharacterData.Digit, 1),

              // no whitespace
              new WhitespaceRule()

      ));

      RuleResult result = validator.validate(new PasswordData(password));

      if (result.isValid()) {
         return true;
      }

      List<String> messages = validator.getMessages(result);
      String messageTemplate = messages.stream().collect(Collectors.joining("\n"));
      context.buildConstraintViolationWithTemplate(messageTemplate)
              .addConstraintViolation()
              .disableDefaultConstraintViolation();
      return false;
   }
}