package com.xiexin.service;

import com.xiexin.bean.Master;
import com.xiexin.bean.MasterExample;
import com.xiexin.dao.MasterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MasterService")
public class MasterServiceImpl implements MasterService {
	@Autowired(required = false)
	private MasterDAO MasterDAO;
	public long countByExample(MasterExample example){
    	return MasterDAO.countByExample(example);
    }

	public int deleteByExample(MasterExample example){
    	return MasterDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return MasterDAO.deleteByPrimaryKey(id);
    }

	public int insert(Master record){
    	return MasterDAO.insert(record);
    }

	public int insertSelective(Master record){
    	return MasterDAO.insertSelective(record);
    }

	public List<Master> selectByExample(MasterExample example){
    	return MasterDAO.selectByExample(example);
    }

	public Master selectByPrimaryKey(Integer id){
    	return MasterDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(Master record, MasterExample example){
    	return MasterDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(Master record, MasterExample example){
    	return MasterDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(Master record){
    	return MasterDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Master record){
    	return MasterDAO.updateByPrimaryKey(record);
    }


}
