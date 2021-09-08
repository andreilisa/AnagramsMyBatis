package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anagram {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "word")
    private String word;

}
