package com.cn.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.modle.Person;

    public class JsonService
    {
        public Person getPerson()
        {
            Person person = new Person(1, "xiaoluo", "广州");
            return person;
        }
        
        public List<Person> getPersons()
        {
            List<Person> persons = new ArrayList<Person>();
            Person person = new Person(1, "xiaoluo", "广州");
            Person person2 = new Person(2, "android", "上海");
            persons.add(person);
            persons.add(person2);
            return persons;
        }
        
        public List<String> getString()
        {
            List<String> list = new ArrayList<String>();
            list.add("广州");
            list.add("上海");
            list.add("北京");
            return list;
        }
        
        public List<Map<String, String>> getMapList()
        {
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            Map<String, String> map1 = new HashMap<String, String>();
            map1.put("id", "001");
            map1.put("name", "xiaoluo");
            map1.put("age", "20");
            Map<String, String> map2 = new HashMap<String, String>();
            map2.put("id", "002");
            map2.put("name", "android");
            map2.put("age", "33");
            list.add(map1);
            list.add(map2);
            return list;
        }
    }
