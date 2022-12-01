package al.edu.fti.universitymanagement.base.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class RequestDto {
    private Integer pageNumber = 0; // default
    private Integer pageSize = 50; // default
}
