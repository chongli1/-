package com.xiexin.service;

import com.xiexin.bean.Syslog;
import com.xiexin.bean.SyslogExample;
import com.xiexin.dao.SyslogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SyslogService")
public class SyslogServiceImpl implements SyslogService {
	@Autowired(required = false)
	private SyslogDAO SyslogDAO;
	public long countByExample(SyslogExample example){
    	return SyslogDAO.countByExample(example);
    }

	public int deleteByExample(SyslogExample example){
    	return SyslogDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return SyslogDAO.deleteByPrimaryKey(id);
    }

	public int insert(Syslog record){
    	return SyslogDAO.insert(record);
    }

	public int insertSelective(Syslog record){
    	return SyslogDAO.insertSelective(record);
    }

	public List<Syslog> selectByExample(SyslogExample example){
    	return SyslogDAO.selectByExample(example);
    }

	public Syslog selectByPrimaryKey(Integer id){
    	return SyslogDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(Syslog record, SyslogExample example){
    	return SyslogDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(Syslog record, SyslogExample example){
    	return SyslogDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(Syslog record){
    	return SyslogDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Syslog record){
    	return SyslogDAO.updateByPrimaryKey(record);
    }


}
