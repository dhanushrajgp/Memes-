package com.crio.starter.exchange;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class MemeDto {
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Url cannot be empty")
    private String url;

    @NotEmpty(message = "Caption cannot be empty")
    private String caption;

}


