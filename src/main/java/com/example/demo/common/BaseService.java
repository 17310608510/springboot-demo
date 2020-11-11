/**
 * 
 */
package com.example.demo.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.MyPage;


public interface BaseService<T> {
	
	public T getOneById(Long id);
	
	
	public List<T> findAll();
	
	
	
	public List<T> findAll(Sort sort);
	
	
	<S extends T> List<S> findAll(Example<S> example, Sort sort);
	
	
	<S extends T> Page<S> findAll(Example<S> example, Pageable pageable);
	
	
	Page<T> findAll(Pageable pageable);	
	
	
	<S extends T> long getCount(Example<S> example);
	
	/**
	 * 插入T
	 * @param map
	 * @return
	 */
	<S extends T> S save(S entity);
	
	/**
	 * 插入list<T>
	 * @param map
	 * @return
	 */
	<S extends T> List<S> saveAll(Iterable<S> entities);
	
	/**
	 * 删除T
	 * @param map
	 * @return
	 */
	public void delete(T t);
	
	/**
	 * 更新T
	 */
	public void update(T t);

	/**
	 * 
	 */
	public void deleteById(Long id);
	
	/**
	 * 全部都有，不用的写null
	 * @param selectSql
	 * @param fromSql
	 * @param whereSql
	 * @param orderBy
	 * @param page
	 * @param groupBy
	 * @return currentPage 当前�? ，pageSize 每页有几�? �? totalSize 总条数，  totalPage 总页�?,  resultList 数据列表 List<Map<String,Object>>
	 */
	public Map<String, Object> getMapBySql(String selectSql, String fromSql,String whereSql,LinkedHashMap<String, String> orderBy,MyPage page,String... groupBy);
	
	/**
	 * 有groupBy
	 * @param groupBy
	 * @return
	 * 王强
	 */
	List getListBySqlGroupBy(String selectSql, String fromSql, String whereSql, String... groupBy);
	/**
	 * 基本的查�?
	 * @param selectSql 
	 * @param fromSql
	 * @param whereSql
	 * @return list
	 * 王强
	 */
	List getListBySql(String selectSql, String fromSql, String whereSql);

	
//	List getListBySql(String selectSql, String fromSql, String whereSql,
//			LinkedHashMap<String, String> orderBy, String[] groupBy);
	/**
	 * 有orderBy
	 * @param orderBy
	 * @return
	 * 王强
	 */
	List getListBySqlOrderBy(String selectSql, String fromSql,
			LinkedHashMap<String, String> orderBy, String whereSql);
	
	/**
	 * count(*)
	 * @param fromSql
	 * @param whereSql
	 * @return int
	 * 王强
	 */
	int countBySql(String fromSql, String whereSql);
	
	/**
	 * sum(fileId)
	 * @param fromSql
	 * @param whereSql
	 * @param field
	 * @return long
	 * 王强
	 */
	long sumBySql(String fromSql, String whereSql,String field);
	
	
	void myUpdateBySql(String sql);
	/**
	 * 修改selectSql
	 * <p>Title: getMapBySql</p>
	 * <p>Description: </p>
	 * @param selectMap
	 * @param fromSql
	 * @param whereSql
	 * @param orderBy
	 * @param page
	 * @param groupBy
	 * @return
	 * 2019年11月5日 上午11:11:56
	 * author 王强
	 */
	public Map<String, Object> getMapBySql(LinkedHashMap<String,String> selectMap, String fromSql,String whereSql,LinkedHashMap<String, String> orderBy,MyPage page,String... groupBy);
	
	/**
	 * 有groupBy
	 * @param groupBy
	 * @return
	 * 王强
	 */
	List getListBySqlGroupBy(LinkedHashMap<String,String> selectMap, String fromSql, String whereSql, String... groupBy);
	/**
	 * 基本的查询
	 * @param selectSql 
	 * @param fromSql
	 * @param whereSql
	 * @return list
	 * 王强
	 */
	List getListBySql(LinkedHashMap<String,String> selectMap, String fromSql, String whereSql);


	/**
	 * 有orderBy
	 * @param orderBy
	 * @return
	 * 王强
	 */
	List getListBySqlOrderBy(LinkedHashMap<String,String> selectMap, String fromSql,
			LinkedHashMap<String, String> orderBy, String whereSql);
	
}
