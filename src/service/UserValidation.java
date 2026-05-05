package service;

public interface UserValidation {

    boolean isUsernameExists(String username);

    boolean isEmailExists(String email);

    boolean isMobileExists(String mobile);

    boolean isValidMobile(String mobile);

    boolean isValidEmail(String email);

    boolean isValidUsername(String username);
    boolean isNameAndLastName(String name);
}