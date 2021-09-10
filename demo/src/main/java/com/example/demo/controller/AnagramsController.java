package com.example.demo.controller;

import com.example.demo.mapper.AnagramsMapper;
import com.example.demo.model.Anagram;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.TreeSet;

@Api
@RestController
@RequestMapping("/anagrams")
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AnagramsController {
    @Autowired
    private AnagramsMapper anagramsMapper;


    @RequestMapping(value = "/getAnagrams", method = RequestMethod.GET)
    public void getAnagrams(@RequestParam("path") String path) {
        anagramsMapper.createFunction();

        anagramsMapper.createTable();

        anagramsMapper.add(new File(path));

        log.info(anagramsMapper.showAll().toString()
                .replaceAll("\\[", " ")
                .replaceAll("\\]", " ")
                .replaceAll(",", " "));

    }
}




