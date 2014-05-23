package cn.newcapec.function.digitalcampus.common.service;

import java.util.List;

import cn.newcapec.function.digitalcampus.common.SoftGod;
import cn.newcapec.function.digitalcampus.common.model.SoftEntityModel;
import cn.newcapec.function.digitalcampus.common.model.listener.RemoveRollback;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.EditStateEnum;

public interface SoftTopService extends SoftGod {

	
	<T extends SoftEntityModel> String saveEntity(T entity,EditStateEnum rightNow,String[] withoutFields);
	
	
	<T extends SoftEntityModel> String updateEntity(T entity,EditStateEnum rightNow,String[] withoutFields);
	
	
	<T extends SoftEntityModel> String saveOrUpdateEntity(T entity,EditStateEnum rightNow,String[] withoutFields);
	
	
	<T extends SoftEntityModel> void saveOrUpdateAllEntity(List<T> entities,EditStateEnum rightNow);
	
	
	<T extends SoftEntityModel> void removeEntity(T entity,EditStateEnum rightNow ,RemoveRollback rollback);
	
	
	<T extends SoftEntityModel> Integer removeEntity(Class<T> clazz,String ids,EditStateEnum rightNow,RemoveRollback rollback);
	
	
	<T extends SoftEntityModel> T get(Class<T> clazz,String id);
	
	
	<T extends SoftEntityModel> T get(Class<T> clazz,String id,String[] fields);
	
	
}
