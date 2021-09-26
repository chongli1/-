package com.xiexin.service;

import com.xiexin.bean.Customer;
import com.xiexin.bean.CustomerExample;
import com.xiexin.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService{
	@Autowired(required = false)
	private CustomerDAO CustomerDAO;
	public long countByExample(CustomerExample example){
    	return CustomerDAO.countByExample(example);
    }

	public int deleteByExample(CustomerExample example){
    	return CustomerDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return CustomerDAO.deleteByPrimaryKey(id);
    }

	public int insert(Customer record){
    	return CustomerDAO.insert(record);
    }

	public int insertSelective(Customer record){
    	return CustomerDAO.insertSelective(record);
    }

	public List<Customer> selectByExample(CustomerExample example){
    	return CustomerDAO.selectByExample(example);
    }

	public Customer selectByPrimaryKey(Integer id){
    	return CustomerDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(Customer record, CustomerExample example){
    	return CustomerDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(Customer record, CustomerExample example){
    	return CustomerDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(Customer record){
    	return CustomerDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Customer record){
    	return CustomerDAO.updateByPrimaryKey(record);
    }


}
