package com.utils;

import com.dao.RegistrationBean;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by nikita on 05.02.2017.
 */
@Component
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationBean.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationBean registrationBean = (RegistrationBean)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","label.validate.nameEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName","label.validate.lastNameEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","label.validate.emailEmpty");
        if(!EmailValidator.getInstance().isValid(registrationBean.getEmail())){
            errors.reject("email", "Email address is not valid.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","label.validate.passwordEmpty");
    }
}
