package org.hyeonjoon.gcu.domain.auth;

import java.time.LocalDate;

public record SignUpRequest(
        String department,
        LocalDate enteredYear,
        String name
) {

}
