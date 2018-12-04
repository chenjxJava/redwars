package com.chenjx.redwars.service.impl;

import com.chenjx.redwars.dao.UploadLogDao;
import com.chenjx.redwars.domain.UploadLog;
import com.chenjx.redwars.service.UploadLogService;
import com.chenjx.redwars.utils.PageBean;
import com.github.pagehelper.PageHelper;
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

    @Override
    public List<UploadLog> findUploadLogsByPage(int currentPage, int pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<UploadLog> list = uploadLogDao.findAllUploadLog();
        int countNums = list.size();
        PageBean<UploadLog> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(list);
        return pageData.getItems();
    }
}
