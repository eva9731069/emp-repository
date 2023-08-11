package com.sumCo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.sumCo.modules.sys.dao.SysDeptDao;
import com.sumCo.modules.sys.entity.SysDept;
import com.sumCo.modules.sys.service.SysDeptService;

@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {

	@Autowired
	private SysDeptDao sysDeptDao;
	
	@Override
	public SysDept queryObject(Long id){
		return sysDeptDao.queryObject(id);
	}
	
	@Override
	public List<SysDept> queryList(Map<String, Object> map){
		return sysDeptDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysDeptDao.queryTotal(map);
	}
	
	@Override
	public void save(SysDept sysDept){
		sysDeptDao.save(sysDept);
	}
	
	@Override
	public void update(SysDept sysDept){
		sysDeptDao.update(sysDept);
	}
	
	@Override
	public void delete(Long id){
		sysDeptDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysDeptDao.deleteBatch(ids);
	}
	
}
