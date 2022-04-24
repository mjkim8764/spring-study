//package controller;
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//import spring.RegisterRequest;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class RegisterRequestValidator implements Validator {
//
//    public static final String emailRegExp =
//            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
//            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//    private Pattern pattern;
//
//    public RegisterRequestValidator() {
//        pattern = Pattern.compile(emailRegExp);
//    }
//
//    /**
//     * aClass 객체가 RegisterRequest 클래스로 타입 변환이 가능한지 확인
//     */
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return RegisterRequest.class.isAssignableFrom(aClass);
//    }
//
//
//    /**
//     * validate() 메서드는 보통 아래와 같이 구현한다.
//     *  1. 검사 대상 객체의 특정 프로퍼티나 상태가 올바른지 검사.
//     *  2. 올바르지 않다면 Errors 의 rejectValue() 메서드를 이용해서 에러 코드 저장
//     *
//     *  여기서 지정한 에러 코드를 이용해서 에러 메시지를 JSP 에서 출력하도록 하면 됨.
//     */
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        RegisterRequest regReq = (RegisterRequest) target;
//
//        // String#trim() : String 앞뒤의 공백 제거 (가운데 있는 공백은 제거해주지 않음)
//        if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
//            errors.rejectValue("email", "required");
//        } else {
//            Matcher matcher = pattern.matcher(regReq.getEmail());
//            if(!matcher.matches()) {
//                errors.rejectValue("email", "bad");
//            }
//        }
//
//        /**
//         * ValidationUtils 클래스의 메서드들을 실행할 때 검사 대상 객체인 target 을 파라미터로
//         * 전달하지 않았는데 어떻게 target 의 "name", "password", "confirmPassword" 등의 프로퍼티 값을 검사할까?
//         *
//         * 이를 위해 RegisterController#handelStep3 메서드에서
//         * 커맨드 객체 파라미터 (RegisterRequest) 뒤에 Errors 타입 파라미터를 위치시킨다.
//         * 그러면 스프링 MVC 는 handleStep3() 메서드를 호출할 때 커맨드 객체와 연결된 Errors 객체를 생성해서 파라미터로 전달하고,
//         * 이 Errors 객체는 커맨드 객체의 특정 프로퍼티 값을 구할 수 있는 getFieldValue() 메서드를 제공한다.
//         */
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
//        ValidationUtils.rejectIfEmpty(errors, "password", "required");
//        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
//
//        if(!regReq.getPassword().isEmpty()) {
//            if(!regReq.isPasswordEqualToConfirmPassword()) {
//                errors.rejectValue("confirmPassword", "nomatch");
//            }
//        }
//
//    }
//}
