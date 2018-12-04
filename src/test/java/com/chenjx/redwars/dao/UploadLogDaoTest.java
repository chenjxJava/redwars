package com.chenjx.redwars.dao;

import com.chenjx.redwars.domain.UploadLog;
import com.chenjx.redwars.service.UploadLogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadLogDaoTest {

    @Autowired
    private UploadLogDao uploadLogDao;


    @Test
//    @Transactional
    public void saveUploadLog() throws Exception {
        UploadLog uploadLog = new UploadLog();
        uploadLog.setName("哈哈哈");
        uploadLog.setPath("path1");
        uploadLogDao.saveUploadLog(uploadLog);
        System.out.println(uploadLog);
        Assert.assertNotNull(uploadLog);
        Assert.assertNotNull(uploadLog.getId());
    }

    @Test
    public void findById() throws Exception {
        UploadLog byId = uploadLogDao.findById(1L);
        System.out.println(byId);
    }

    @Test
    public void findAllUploadLog() throws Exception {
        List<UploadLog> allUploadLog = uploadLogDao.findAllUploadLog();
        Assert.assertNotNull(allUploadLog);
        System.out.println(allUploadLog);
    }

    @Test
    public void deleteUploadLog() throws Exception {
        uploadLogDao.deleteUploadLog(2L);
    }

}