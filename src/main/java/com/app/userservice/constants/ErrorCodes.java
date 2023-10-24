package com.app.userservice.constants;

public class ErrorCodes {
    public static final String USER_EMAIL_EXISTS = "USER_EMAIL_EXISTS:User with the email ID already exists";
    public static final String NOT_FOUND = "NOT_FOUND:Item not found";

    public static final String TO_MANY_SESSIONS = "TO_MANY_SESSIONS:Too many sessions opened, please close some sessions";

    public static final String INCORRECT_PASSWORD = "INCORRECT_PASSWORD: Please login with correct credentials";

    public static final String DUPLICATE_ROLE = "DUPLICATE_ROLE: A role with same name already exists in the system, please create a different role";
}
