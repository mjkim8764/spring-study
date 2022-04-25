//package controller;
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//public class LoginCommandValidator implements Validator {
//
//    // 커맨드 객체 자체의 검증
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return LoginCommand.class.isAssignableFrom(aClass);
//    }
//
//    // 각 변수 값 검증
//    @Override
//    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
//        ValidationUtils.rejectIfEmpty(errors, "password", "required");
//    }
//
//}
