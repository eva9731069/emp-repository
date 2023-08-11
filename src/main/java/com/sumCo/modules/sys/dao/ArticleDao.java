package com.sumCo.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sumCo.modules.sys.dao.BaseDao;
import com.sumCo.modules.sys.entity.Article;

@Mapper
public interface ArticleDao extends BaseDao<Article> {
	
}
