package exercise.dto;

import exercise.model.Contact;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ContactDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public ContactDTO(Contact contact) {
        id = contact.getId();
        firstName = contact.getFirstName();
        lastName = contact.getLastName();
        phone = contact.getPhone();
        createdAt = contact.getCreatedAt();
        updatedAt = contact.getUpdatedAt();
    }
}
