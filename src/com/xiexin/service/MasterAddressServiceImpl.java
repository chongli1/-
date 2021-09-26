package com.xiexin.service;

import com.xiexin.bean.MasterAddress;
import com.xiexin.bean.MasterAddressExample;
import com.xiexin.dao.MasterAddressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MasterAddressService")
public class MasterAddressServiceImpl implements MasterAddressService {
	@Autowired(required = false)
	private MasterAddressDAO MasterAddressDAO;
	public long countByExample(MasterAddressExample example){
    	return MasterAddressDAO.countByExample(example);
    }

	public int deleteByExample(MasterAddressExample example){
    	return MasterAddressDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return MasterAddressDAO.deleteByPrimaryKey(id);
    }

	public int insert(MasterAddress record){
    	return MasterAddressDAO.insert(record);
    }

	public int insertSelective(MasterAddress record){
    	return MasterAddressDAO.insertSelective(record);
    }

	public List<MasterAddress> selectByExample(MasterAddressExample example){
    	return MasterAddressDAO.selectByExample(example);
    }

	public MasterAddress selectByPrimaryKey(Integer id){
    	return MasterAddressDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(MasterAddress record, MasterAddressExample example){
    	return MasterAddressDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(MasterAddress record, MasterAddressExample example){
    	return MasterAddressDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(MasterAddress record){
    	return MasterAddressDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(MasterAddress record){
    	return MasterAddressDAO.updateByPrimaryKey(record);
    }


}
