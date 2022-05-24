package al.edu.fti.universitymanagement.uniman.core.user.user.dto;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.BadRequestException;
import al.edu.fti.universitymanagement.uniman.core.user.user.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto extends BaseDto {

    private String name;
    private String email;
    private String lastName;
    private LocalDate birthday;
    private String idNumber;
    private Integer gender; // 0 - M , 1 - F
    private String picture;
    private Role role;
    private LocalDateTime registrationDate;
    @JsonIgnore
    private String password;
    private String microsoftAccessToken;

    public String getFullName(){
        if (name != null && lastName != null){
            return name + " " + lastName;
        }
        throw new BadRequestException("User does not have full name");
    }
}
