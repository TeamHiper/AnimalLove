package com.animal.AnimalLove.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class LongListConverter implements AttributeConverter<List<Long>, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        try{
            return mapper.writeValueAsString(attribute);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, List.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
