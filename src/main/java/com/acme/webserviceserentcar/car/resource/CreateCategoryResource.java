package com.acme.webserviceserentcar.car.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CreateCategoryResource {
    @NotNull
    @NotBlank
    @Size(max = 30)
    private String name;
}