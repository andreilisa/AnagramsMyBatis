package com.example.demo.controller;

import com.example.demo.mapper.AnagramsMapper;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    private String strCurrentLine;

    @GetMapping("/all-anagrams")
    public void anagrams() {
        try {
            BufferedReader objReader = new BufferedReader(new FileReader("C:\\Users\\andrei.lisa\\IdeaProjects\\qwe\\folder\\file1.txt"));

            while ((strCurrentLine = objReader.readLine()) != null)

                if (new TreeSet<>(anagramsMapper.showAll(strCurrentLine)).size() > 1)
                    log.info(new TreeSet<>(anagramsMapper.showAll(strCurrentLine)).toString().replaceAll("\\[", " ")
                            .replaceAll(",", " ")
                            .replaceAll("]", " "));


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
