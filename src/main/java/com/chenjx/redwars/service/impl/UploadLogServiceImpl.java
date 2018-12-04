package com.chenjx.redwars.service.impl;

import com.chenjx.redwars.dao.UploadLogDao;
import com.chenjx.redwars.domain.UploadLog;
import com.chenjx.redwars.service.UploadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadLogServiceImpl implements UploadLogService {

    @Autowired
    private UploadLogDao uploadLogDao;

    @Override
    public List<UploadLog> findAllUploadLog() {
        return uploadLogDao.findAllUploadLog();
    }

    @Override
    public UploadLog findById(Long id) {
        return uploadLogDao.findById(id);
    }

    @Override
    public Long saveUploadLog(UploadLog uploadLog) {
        return uploadLogDao.saveUploadLog(uploadLog);
    }

    @Override
    public Long deleteUploadLog(Long id) {
        return uploadLogDao.deleteUploadLog(id);
    }
}
