package com.example.demo.controller;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.example.demo.mapper.AnagramsMapper;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TreeSet;

@Api
@RestController
@RequestMapping("/anagrams")
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AnagramsController {
    TreeSet<String> treeSet = new TreeSet<>();
    @Autowired
    private AnagramsMapper anagramsMapper;
    private String strCurrentLine;

    @RequestMapping(value = "/getAnagrams", method = RequestMethod.GET)
    public File getFile(@RequestParam("path") String path) {
        try {
            anagramsMapper.add();
            BufferedReader objReader = new BufferedReader(new FileReader(path));
            while ((strCurrentLine = objReader.readLine()) != null) {
                treeSet = anagramsMapper.showAll(strCurrentLine);
                while (treeSet == null) {
                    treeSet.add(strCurrentLine);
                }

                if (treeSet.size() > 1)
                    log.info(treeSet.toString().replaceAll("\\[", " ")
                            .replaceAll(",", " ")
                            .replaceAll("]", " "));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(path);
    }
}
