package org.pcl.springlongkuang.Service.Impl;

import org.pcl.springlongkuang.Mapper.ExceptionMapper;
import org.pcl.springlongkuang.Model.Exception;
import org.pcl.springlongkuang.Service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Autowired
    private ExceptionMapper exceptionMapper;
    @Override
    public boolean AddException(List<Exception> exceptions) {
        for (Exception v:exceptions) {
            v.setCreatedAt(new Date());
            System.out.println("AddException  setCreatedAt ===="+v.getCreatedAt());
            if (exceptionMapper.insertSelective(v)==0){
                return false;
            }
        }
        return true;
    }
}
