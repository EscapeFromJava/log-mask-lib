package com.example.logmasklib.dto;

import com.example.logmasklib.mask.common.Mask;
import com.example.logmasklib.mask.common.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    @JsonProperty("FirstName")
    private String firstName;

    @Mask(Type.LAST_NAME)
    @JsonProperty("LastName")
    private String lastName;

    @Mask(Type.EMAIL)
    @JsonProperty("Email")
    private String email;

    @Mask(Type.PASSPORT)
    @JsonProperty("PasportNum")
    private String pasportNum;

}
