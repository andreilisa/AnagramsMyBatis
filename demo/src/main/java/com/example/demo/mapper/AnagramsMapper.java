package com.example.demo.mapper;

import java.io.File;
import java.util.List;
import java.util.TreeSet;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface AnagramsMapper {

    @Select("CREATE OR REPLACE FUNCTION sort1(x TEXT) returns TEXT AS $$\n" +
            "SELECT string_agg(c,'') as s\n" +
            "from (select unnest(regexp_split_to_array($1, '')) as c\n" +
            "      order by c) as t;\n" +
            "\n" +
            "$$ LANGUAGE sql immutable ;")
    void createFunction();

    @Select("CREATE TABLE IF NOT EXISTS table_test (\n" +
            "\n" +
            "                                          word varchar(20),\n" +
            "                                          wordSort varchar(20) GENERATED ALWAYS AS (sort1(word) ) STORED\n" +
            ");")
    void createTable();

    @Insert("Copy table_test(word) from '${path}'")
    void add(File path);

    @Select(" SELECT distinct ARRAY_AGG(word), wordSort FROM table_test GROUP BY wordSort HAVING COUNT(word) > 1;")
    TreeSet<String> showAll();


}
