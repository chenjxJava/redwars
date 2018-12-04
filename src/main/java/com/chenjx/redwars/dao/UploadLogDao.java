package com.chenjx.redwars.dao;

import com.chenjx.redwars.domain.UploadLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UploadLogDao {

    List<UploadLog> findAllUploadLog();

    UploadLog findById(@Param("id") Long id);

    Long saveUploadLog(UploadLog uploadLog);

    Long deleteUploadLog(@Param("id") Long id);

}
