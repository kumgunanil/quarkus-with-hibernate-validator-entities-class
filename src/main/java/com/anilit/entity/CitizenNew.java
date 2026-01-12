package com.anilit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CitizenNew {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotBlank(message = "Name can not be blank")
    @Length(min = 2, max = 50, message = "Name should be between 10 char to 50 char max")
    String name;

    @Pattern(regexp = "[MF]", message = "Value can be either M or F")
    String gender;

    @Length(min = 5, max = 12, message = "Aadhar number must be 12 char")
    String aadharNo;

    @Min(value = 2, message = "Min age should be 2 yr")
    @Max(value = 90, message = "Max age should be 90 yr")
    int age;
}
