package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.TreeSet;


@Mapper
public interface AnagramsMapper {
    @Select("with context as (\n" +
            "    select word, id, array(select distinct unnest(regexp_split_to_array(word, '')) x order by x) as charset from dict\n" +
            ")\n" +
            "select word, id from context where charset=(select charset from context where word=#{strCurrentLine} limit 1) and length(word) = length(#{strCurrentLine})")
    TreeSet<String> showAll(String strCurrentLine);
    @Select("Copy dict(word) from 'C:\\Users\\andrei.lisa\\IdeaProjects\\qwe\\folder\\file.txt'")
    void add();



}
