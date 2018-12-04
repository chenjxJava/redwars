package com.chenjx.redwars.service;

import com.chenjx.redwars.domain.UploadLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UploadLogService {

    List<UploadLog> findAllUploadLog();

    UploadLog findById(Long id);

    Long saveUploadLog(UploadLog uploadLog);

    Long deleteUploadLog(Long id);

}
