package com.xiexin.service;

import com.xiexin.bean.Orders;
import com.xiexin.bean.OrdersExample;
import com.xiexin.dao.OrdersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService{
	@Autowired(required = false)
	private OrdersDAO OrdersDAO;
	public long countByExample(OrdersExample example){
    	return OrdersDAO.countByExample(example);
    }

	public int deleteByExample(OrdersExample example){
    	return OrdersDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return OrdersDAO.deleteByPrimaryKey(id);
    }

	public int insert(Orders record){
    	return OrdersDAO.insert(record);
    }

	public int insertSelective(Orders record){
    	return OrdersDAO.insertSelective(record);
    }

	public List<Orders> selectByExample(OrdersExample example){
    	return OrdersDAO.selectByExample(example);
    }

	public Orders selectByPrimaryKey(Integer id){
    	return OrdersDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(Orders record, OrdersExample example){
    	return OrdersDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(Orders record, OrdersExample example){
    	return OrdersDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(Orders record){
    	return OrdersDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Orders record){
    	return OrdersDAO.updateByPrimaryKey(record);
    }


}
