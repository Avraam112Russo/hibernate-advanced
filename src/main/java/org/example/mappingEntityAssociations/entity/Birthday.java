package org.example.mappingEntityAssociations.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Birthday {
    private LocalDate birthDate;
    public long getAge(){
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
