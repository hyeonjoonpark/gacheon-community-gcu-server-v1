package org.hyeonjoon.gcu.domain.auth.dto.request;

import java.time.LocalDate;

public record SignUpRequest(
        String department,
        LocalDate enteredYear,
        String name
) {

}
