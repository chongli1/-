package com.xiexin.service;

import com.xiexin.bean.Coupon;
import com.xiexin.bean.CouponExample;
import com.xiexin.dao.CouponDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CouponService")
public class CouponServiceImpl implements CouponService {
	@Autowired(required = false)
	private CouponDAO CouponDAO;
	public long countByExample(CouponExample example){
    	return CouponDAO.countByExample(example);
    }

	public int deleteByExample(CouponExample example){
    	return CouponDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return CouponDAO.deleteByPrimaryKey(id);
    }

	public int insert(Coupon record){
    	return CouponDAO.insert(record);
    }

	public int insertSelective(Coupon record){
    	return CouponDAO.insertSelective(record);
    }

	public List<Coupon> selectByExample(CouponExample example){
    	return CouponDAO.selectByExample(example);
    }

	public Coupon selectByPrimaryKey(Integer id){
    	return CouponDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(Coupon record, CouponExample example){
    	return CouponDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(Coupon record, CouponExample example){
    	return CouponDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(Coupon record){
    	return CouponDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Coupon record){
    	return CouponDAO.updateByPrimaryKey(record);
    }


}
