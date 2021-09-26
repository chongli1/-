package com.xiexin.service;

import com.xiexin.bean.Customerservice;
import com.xiexin.bean.CustomerserviceExample;
import com.xiexin.dao.CustomerserviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CustomerserviceService")
public class CustomerserviceServiceImpl implements CustomerserviceService{
	@Autowired(required = false)
	private CustomerserviceDAO CustomerserviceDAO;
	public long countByExample(CustomerserviceExample example){
    	return CustomerserviceDAO.countByExample(example);
    }

	public int deleteByExample(CustomerserviceExample example){
    	return CustomerserviceDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return CustomerserviceDAO.deleteByPrimaryKey(id);
    }

	public int insert(Customerservice record){
    	return CustomerserviceDAO.insert(record);
    }

	public int insertSelective(Customerservice record){
    	return CustomerserviceDAO.insertSelective(record);
    }

	public List<Customerservice> selectByExample(CustomerserviceExample example){
    	return CustomerserviceDAO.selectByExample(example);
    }

	public Customerservice selectByPrimaryKey(Integer id){
    	return CustomerserviceDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(Customerservice record, CustomerserviceExample example){
    	return CustomerserviceDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(Customerservice record, CustomerserviceExample example){
    	return CustomerserviceDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(Customerservice record){
    	return CustomerserviceDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Customerservice record){
    	return CustomerserviceDAO.updateByPrimaryKey(record);
    }


}
