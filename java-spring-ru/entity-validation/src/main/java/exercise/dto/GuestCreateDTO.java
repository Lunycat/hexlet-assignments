package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class GuestCreateDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Size(min = 12, max = 14)
    @Pattern(regexp = "\\+[0-9]+")
    private String phoneNumber;

    @Size(min = 4, max = 4)
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;
}
