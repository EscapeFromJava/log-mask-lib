package com.example.logmasklib.dto;

import com.example.logmasklib.mask.common.Mask;
import com.example.logmasklib.mask.common.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Integer userId;
    private Integer id;
    @Mask(Type.TEXT)
    private String title;
    private Boolean completed;

}
