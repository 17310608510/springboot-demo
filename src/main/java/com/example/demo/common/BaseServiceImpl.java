/**
 * 
 */
package com.example.demo.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.demo.entity.MyPage;
import com.example.demo.util.StringUtil;


public class BaseServiceImpl<T> implements BaseService<T>{
	
	private Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

	@Autowired
	public JpaRepository<T, Long> jpaRepository;
	

	@PersistenceContext
    EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see org.cfw.base.base.BaseService#getOneById(java.lang.Integer)
	 */
	@Override
	public T getOneById(Long id) {
		// TODO Auto-generated method stub
		Optional<T> optional = jpaRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.cfw.base.base.BaseService#findAll()
	 */
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return jpaRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see org.cfw.base.base.BaseService#findAll(org.springframework.data.domain.Sort)
	 */
	@Override
	public List<T> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return jpaRepository.findAll(sort);
	}

	/* (non-Javadoc)
	 * @see org.cfw.base.base.BaseService#findAll(org.springframework.data.domain.Example, org.springframework.data.domain.Sort)
	 */
	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return null;
		// TODO Auto-generated method stub
		//return jpaRepository.findAll(example, sort);
	}

	/* (non-Javadoc)
	 * @see org.cfw.base.base.BaseService#findAll(org.springframework.data.domain.Example, org.springframework.data.domain.Pageable)
	 */
	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return jpaRepository.findAll(example, pageable);
	}

	/* (non-Javadoc)
	 * @see org.cfw.base.base.BaseService#findAll(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<T> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return jpaRepository.findAll(pageable);
	}


	/* (non-Javadoc)
	 * @see org.cfw.base.base.BaseService#getCount(org.springframework.data.domain.Example)
	 */
	@Override
	public <S extends T> long getCount(Example<S> example) {
		// TODO Auto-generated method stub
		return jpaRepository.count(example);
	}

	/* (non-Javadoc)
	 * @see org.cfw.base.base.BaseService#save(java.lang.Object)
	 */
	@Override
	public <S extends T> S save(S entity) {
		// TODO Auto-generated method stub
		return jpaRepository.save(entity);
	}
	
	@Override
	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return jpaRepository.saveAll(entities);
	}
	
	/* (non-Javadoc)
	 * @see org.cfw.base.base.BaseService#delete(java.lang.Object)
	 */
	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		jpaRepository.delete(t);
	}

	/* (non-Javadoc)
	 * @see org.cfw.base.base.BaseService#update(java.lang.Object)
	 */
	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		jpaRepository.save(t);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		jpaRepository.deleteById(id);
	}
	

	/**
	 * 获取sql
	 * @param selectSql
	 * @param fromSql
	 * @param whereSql
	 * @param orderBy
	 * @param page
	 * @param groupBy
	 * @return
	 */
	private String getSql(String selectSql, String fromSql,String whereSql , LinkedHashMap<String, String> orderBy,MyPage page,String... groupBy) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT "+selectSql +" FROM " +fromSql +" ");
	
		if(!StringUtil.isEmpty(whereSql)) {
			buffer.append(" WHERE 1 = 1 AND "+whereSql +" ");
		}
		
		if(groupBy!=null&&groupBy.length>0){
			buffer.append(" GROUP BY ");
			for(int i=0; i<groupBy.length;i++) {
				if(i<groupBy.length-1) {
					buffer.append(groupBy[i]+",");
				}else {
					buffer.append(groupBy[i]);
				}
			}
		}
		
		if(orderBy!=null){
			buffer.append(" ORDER BY ");
			for(String key :orderBy.keySet()) {
				buffer.append(" "+key+" "+orderBy.get(key) +",");
			}
			buffer.deleteCharAt(buffer.length()-1);
		}
		
		if(page!=null) {
			int currentPage = page.getCurrentPage() >= 0 ? page.getCurrentPage() : 0;
			buffer.append(" limit "+currentPage* page.getPageSize()+", " + page.getPageSize());
		}
		//log.info("===sql=="+buffer.toString());
		return buffer.toString();
	}
		
	@Override
	public List getListBySql(String selectSql, String fromSql,String whereSql){
		return (List) getMapBySql(selectSql, fromSql, whereSql, null, null, null).get("resultList");
	}
	
//	@Override
//	public List getListBySqls(String selectSql, String fromSql,String whereSql){
//		return (List) getMapBySqls(selectSql, fromSql, whereSql, null, null, null).get("resultList");
//	}
	@Override
	public List getListBySqlGroupBy(String selectSql, String fromSql,String whereSql,String... groupBy){
		return (List) getMapBySql(selectSql, fromSql, whereSql, null, null, groupBy).get("resultList");
	}
//	@Override
//	public List getListBySql(String selectSql, String fromSql,String whereSql , LinkedHashMap<String, String> orderBy,String... groupBy){
//		return (List) getMapBySql(selectSql, fromSql, whereSql, orderBy, null, groupBy).get("resultList");
//	}
	@Override
	public List getListBySqlOrderBy(String selectSql, String fromSql,LinkedHashMap<String, String> orderBy,String whereSql){
		return (List) getMapBySql(selectSql, fromSql, whereSql, orderBy, null, null).get("resultList");
	}
	
	@Override
	public Map<String, Object> getMapBySql(String selectSql, String fromSql,String whereSql,LinkedHashMap<String, String> orderBy,MyPage page,String... groupBy){
		Map<String, Object> remap = new HashMap<>();
		//获取sql
		if(StringUtil.isEmpty(selectSql)||StringUtil.isEmpty(fromSql)) {
			return null;
		}
		String sql = getSql(selectSql, fromSql, whereSql, orderBy, page, groupBy);
		//
		//System.out.println(sql);
		if(StringUtil.isEmpty(sql)) {
			return null;
		}
		Query query = entityManager.createNativeQuery(sql);
		List resultList = query.getResultList();
		if(fromSql.trim().equals("*")) {
			remap.put("resultList", resultList);
		}else {
			List<Map<String, Object>> resultListToList = getResultListToList(selectSql, resultList);
			remap.put("resultList", resultListToList);
		}
		//remap.put("resultList", resultListToList);
		if(page!=null) {
			remap.put("currentPage", page.getCurrentPage());
			remap.put("pageSize", page.getPageSize());
			int countBySql = countBySql(selectSql, fromSql, whereSql, orderBy, page, groupBy);
//			if(resultList!=null&&resultList.size()>0) {
//				countBySql = countBySql(selectSql, fromSql, whereSql, orderBy, page, groupBy);
//			}
			if((resultList==null||resultList.size()==0)&&countBySql>0) {//有值但是没有查到，就默认查上一页
				if(page.getCurrentPage()>0) {
					page.setCurrentPage(page.getCurrentPage()-1);
					return getMapBySql(selectSql, fromSql, whereSql, orderBy, page, groupBy);
				}
			}
			remap.put("totalSize", countBySql);
			if(page.getPageSize()==0) {
				remap.put("totalPage", 0);
			}else {
				remap.put("totalPage", countBySql%page.getPageSize()>0?countBySql/page.getPageSize()+1:countBySql/page.getPageSize());
			}
		}
		
		return remap;
	}
	
	private String getCountSql(String selectSql,String fromSql, String whereSql, LinkedHashMap<String, String> orderBy, MyPage page,
			String[] groupBy) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT "+selectSql +" FROM " +fromSql +" ");
	
		if(!StringUtil.isEmpty(whereSql)) {
			buffer.append(" WHERE 1 = 1 AND "+whereSql +" ");
		}
		
		if(groupBy!=null&&groupBy.length>0){
			buffer.append(" GROUP BY ");
			for(int i=0; i<groupBy.length;i++) {
				if(i<groupBy.length-1) {
					buffer.append(groupBy[i]+",");
				}else {
					buffer.append(groupBy[i]);
				}
			}
		}
		
		if(orderBy!=null){
			buffer.append(" ORDER BY ");
			for(String key :orderBy.keySet()) {
				buffer.append(" "+key+" "+orderBy.get(key) +",");
			}
			buffer.deleteCharAt(buffer.length()-1);
		}
		
		String string = buffer.toString();
		return string;
	}
	
	private int countBySql(String selectSql,String fromSql, String whereSql, LinkedHashMap<String, String> orderBy, MyPage page,
			String[] groupBy) {
		
		String countSql = getCountSql(selectSql, fromSql, whereSql, orderBy, page, groupBy);
		
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT count(1) FROM (" +countSql +") countTable");
		
		log.info("SQL语句?" + buffer.toString());
		Query query = entityManager.createNativeQuery(buffer.toString());
		List resultList = query.getResultList();
		
		if(resultList!=null&&resultList.size()>0&& resultList.get(0)!=null) {
			return ((BigInteger) resultList.get(0)).intValue();
		}else {
			return 0;
		}
	}

	@Override
	public int countBySql(String fromSql,String whereSql) {
		if(StringUtil.isEmpty(fromSql)) {
			return 0;
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT count(1) FROM " +fromSql +" ");
		if(!StringUtil.isEmpty(whereSql)) {
			buffer.append(" WHERE 1 = 1 AND "+whereSql +" ");
		}
		log.info("SQL语句�?" + buffer.toString());
		Query query = entityManager.createNativeQuery(buffer.toString());
		List resultList = query.getResultList();
		
		if(resultList!=null&&resultList.size()>0&& resultList.get(0)!=null) {
			return ((BigInteger) resultList.get(0)).intValue();
		}else {
			return 0;
		}
	}
	
	private List<Map<String, Object>> getResultListToList(String selectSql,List resultList) {
		List<String> lists = StringUtil.changeStringToString(selectSql, ",");
		int listsLenth = lists.size();
		List<String> keys = getKeys(lists);
		
		
		List<Map<String, Object>> reList =new ArrayList<>();
		
		if(resultList != null&&resultList.size()>0) {
			for(int i=0;i<resultList.size();i++) {
				Map<String, Object> detailMap = new HashMap<>();
				if(listsLenth ==1) {
					Object obj = resultList.get(i);
					if(obj!=null) {
						detailMap.put(keys.get(0), obj);
					}
				}else {
					Object[] obj = (Object[]) resultList.get(i);
					if(obj!=null&&obj.length>0) {
						for(int index = 0;index<obj.length;index++) {
							detailMap.put(keys.get(index), obj[index]);
						}
					}
				}
				
				
				reList.add(detailMap);
			}
		}
		
		return reList;
		
	}

	private List<String> getKeys(List<String> lists) {
		List<String> keyList = new ArrayList<>();
		if(lists != null) {
			for(int i=0;i<lists.size();i++) {
				String key = "";
				String str = lists.get(i).trim();
				
				if(str.trim().contains(" ")) {
					List<String> changeStringToString = null;
					if(str.trim().contains(" as ")) {
						changeStringToString = StringUtil.changeStringToString(str," as ");
					}else if(str.trim().contains(" AS ")) {
						changeStringToString = StringUtil.changeStringToString(str, " AS ");
					}else if(str.trim().contains(" As ")) {
						changeStringToString = StringUtil.changeStringToString(str, " As ");
					}else if(str.trim().contains(" aS ")) {
						changeStringToString = StringUtil.changeStringToString(str, " aS ");
					}else{
						changeStringToString = StringUtil.changeStringToString(str, " ");
					}
					
					if(changeStringToString!=null&&changeStringToString.size()>0&&changeStringToString.size()==2) {
						key = changeStringToString.get(changeStringToString.size()-1);
					}else{
						key = str;
					}
				}else {
					key = str;
				}
				keyList.add(key);
			}
		}
		return keyList;
		
		
	}

	@Override
	public long sumBySql(String fromSql, String whereSql,String field) {
		if(StringUtil.isEmpty(fromSql)) {
			return 0l;
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT sum("+field+") FROM " +fromSql +" ");
		if(!StringUtil.isEmpty(whereSql)) {
			buffer.append(" WHERE 1 = 1 AND "+whereSql +" ");
		}
		
		Query query = entityManager.createNativeQuery(buffer.toString());
		List resultList = query.getResultList();
		
		if(resultList!=null&&resultList.size()>0&&resultList.get(0)!=null) {
			return ((BigDecimal)resultList.get(0)).longValue();
		}else {
			return 0;
		}
		
	}
	@Override
	@Transactional
    @Modifying
	public void myUpdateBySql(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public Map<String, Object> getMapBySql(LinkedHashMap<String,String> selectMap, String fromSql,String whereSql,LinkedHashMap<String, String> orderBy,MyPage page,String... groupBy){
		Map<String, Object> remap = new HashMap<>();
		//获取sql
		StringBuffer countSql = new StringBuffer();
		List<String> keyList = new ArrayList<>();
		String sql = getSql(keyList,countSql,selectMap, fromSql, whereSql, orderBy, page, groupBy);
	
		if(StringUtil.isEmpty(sql)) {
			return null;
		}
		Query query = entityManager.createNativeQuery(sql);
		List resultList = query.getResultList();
		if(fromSql.trim().equals("*")) {
			remap.put("resultList", resultList);
		}else {
			List<Map<String, Object>> resultListToList = getResultListToList(keyList, resultList);
			remap.put("resultList", resultListToList);
		}
		//remap.put("resultList", resultListToList);
		if(page!=null) {
			remap.put("currentPage", page.getCurrentPage());
			remap.put("pageSize", page.getPageSize());
			
			int countBySql = 0;
			Query countQuery = entityManager.createNativeQuery(countSql.toString());
			List countResultList = countQuery.getResultList();
			if(countResultList!=null&&countResultList.size()>0&& countResultList.get(0)!=null) {
				countBySql = ((BigInteger) countResultList.get(0)).intValue();
			}
			
//			if((resultList==null||resultList.size()==0)&&countBySql>0) {//有值但是没有查到，就默认查上一页
//				if(page.getCurrentPage()>0) {
//					page.setCurrentPage(page.getCurrentPage()-1);
//					return getMapBySql(selectMap,fromSql,whereSql,orderBy,page,groupBy);
//				}
//			}
			remap.put("totalSize", countBySql);
			if(page.getPageSize()==0) {
				remap.put("totalPage", 0);
			}else {
				remap.put("totalPage", countBySql%page.getPageSize()>0?countBySql/page.getPageSize()+1:countBySql/page.getPageSize());
			}
		}
		
		return remap;
	}
	
	private String getSql(List<String> keyList,StringBuffer countSql,LinkedHashMap<String,String> selectMap, String fromSql,String whereSql , LinkedHashMap<String, String> orderBy,MyPage page,String... groupBy) {
		List<String> selectSql = new ArrayList<>();
		for(String key : selectMap.keySet()) {
			selectSql.add(key +" AS " + selectMap.get(key));
			keyList.add(selectMap.get(key));
		}
		String listStringToString = StringUtil.listStringToString(selectSql, ",", false, false);
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "+listStringToString +" FROM " +fromSql +" ");
		if(!StringUtil.isEmpty(whereSql)) {
			sql.append(" WHERE 1 = 1 AND "+whereSql +" ");
		}
		if(groupBy!=null&&groupBy.length>0){
			sql.append(" GROUP BY ");
			for(int i=0; i<groupBy.length;i++) {
				if(i<groupBy.length-1) {
					sql.append(groupBy[i]+",");
				}else {
					sql.append(groupBy[i]);
				}
			}
		}
		if(orderBy!=null){
			sql.append(" ORDER BY ");
			for(String key :orderBy.keySet()) {
				sql.append(" "+key+" "+orderBy.get(key) +",");
			}
			sql.deleteCharAt(sql.length()-1);
		}
		if(page!=null) {
			countSql.append("SELECT count(*) from (").append(sql).append(") zzz");
			int currentPage = page.getCurrentPage() >= 0 ? page.getCurrentPage() : 0;
			sql.append(" limit "+currentPage* page.getPageSize()+", " + page.getPageSize());
		}
		return sql.toString();
	}
	
	
	private List<Map<String, Object>> getResultListToList(List<String> keyList,List resultList) {
		int listsLenth = keyList.size();
		List<Map<String, Object>> reList =new ArrayList<>();
		if(resultList != null&&resultList.size()>0) {
			for(int i=0;i<resultList.size();i++) {
				LinkedHashMap<String, Object> detailMap = new LinkedHashMap<>();
				if(listsLenth ==1) {
					Object obj = resultList.get(i);
					if(obj!=null) {
						detailMap.put(keyList.get(0), obj);
					}
				}else {
					Object[] obj = (Object[]) resultList.get(i);
					if(obj!=null&&obj.length>0) {
						for(int index = 0;index<obj.length;index++) {
							detailMap.put(keyList.get(index), obj[index]);
						}
					}
				}
				reList.add(detailMap);
			}
		}
		return reList;
		
	}

	@Override
	public List getListBySqlGroupBy(LinkedHashMap<String, String> selectMap, String fromSql, String whereSql,
			String... groupBy) {
		return (List) getMapBySql(selectMap, fromSql, whereSql, null, null, groupBy).get("resultList");
	}

	@Override
	public List getListBySql(LinkedHashMap<String, String> selectMap, String fromSql, String whereSql) {
		return (List) getMapBySql(selectMap, fromSql, whereSql, null, null, null).get("resultList");
	}

	@Override
	public List getListBySqlOrderBy(LinkedHashMap<String, String> selectMap, String fromSql,
			LinkedHashMap<String, String> orderBy, String whereSql) {
		return (List) getMapBySql(selectMap, fromSql, whereSql, orderBy, null, null).get("resultList");
	}

	
	
}
