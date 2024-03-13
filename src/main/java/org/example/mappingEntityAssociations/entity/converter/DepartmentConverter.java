package org.example.mappingEntityAssociations.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.mappingEntityAssociations.entity.Department;


import java.util.Optional;

@Converter(autoApply = true)
public class DepartmentConverter implements AttributeConverter<Department, String> {

    @Override
    public String convertToDatabaseColumn(Department department) {
        return Optional.ofNullable(department)
                .map(dep-> dep.getDepartmentName())
                .orElse(null);
    }

    @Override
    public Department convertToEntityAttribute(String departmentName) {
        return Optional.ofNullable(departmentName)
                .map(name -> Department.builder().departmentName(name).build())
                .orElse(null);
    }
}
