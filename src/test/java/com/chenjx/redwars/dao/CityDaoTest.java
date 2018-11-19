package com.chenjx.redwars.dao;

import com.chenjx.redwars.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityDaoTest {

    @Autowired
    private CityDao cityDao;

    @Test
    public void findByName() {
        City byName = cityDao.findByName("123");
        System.out.println(byName);
    }
}