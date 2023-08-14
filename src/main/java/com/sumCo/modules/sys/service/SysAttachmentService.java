package com.sumCo.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.sumCo.modules.sys.entity.SysAttachment;

/**
 * @author oplus
 * @Description: TODO()
 * @date 2017-7-10 17:01
 */
public interface SysAttachmentService {


    List<SysAttachment> queryList(Map<String, Object> map);


    int queryTotal(Map<String, Object> map);

    SysAttachment queryObject(Long id);

    void deleteBatch(Long[] ids);

    void save(SysAttachment sysAttachment);

}
