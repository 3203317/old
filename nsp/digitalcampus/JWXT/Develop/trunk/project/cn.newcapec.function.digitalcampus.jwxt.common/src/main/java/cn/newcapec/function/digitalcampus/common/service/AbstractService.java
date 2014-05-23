package cn.newcapec.function.digitalcampus.common.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.newcapec.function.digitalcampus.common.dao.BaseDao;
import cn.newcapec.function.digitalcampus.common.dao.SoftDao;
import cn.newcapec.function.digitalcampus.common.model.SoftEntityModel;
import cn.newcapec.function.digitalcampus.common.model.listener.ModelPlusListener;
import cn.newcapec.function.digitalcampus.common.model.listener.RemoveListener;
import cn.newcapec.function.digitalcampus.common.model.listener.RemoveRollback;
import cn.newcapec.function.digitalcampus.common.model.listener.SaveOrUpdateListener;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.DateUtils;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.EditStateEnum;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.ModelUtil;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.PropertyUtils;

public abstract class AbstractService implements SoftService{

	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private BaseDao baseDao;
	@Override
	public SoftDao getDao(){
		return this.baseDao;
	}
	
	@Override
	public <T extends SoftEntityModel> T get(Class<T> clazz, String id,
			String[] fields) {
		return this.getDao().get(clazz, id, fields);
	}

	@Override
	public <T extends SoftEntityModel> T get(Class<T> clazz, String id) {
		return this.get(clazz, id, null);
	}

	@Override
	public <T extends SoftEntityModel> Integer removeEntity(Class<T> clazz,
			String ids, EditStateEnum rightNow,RemoveRollback rollback) {
		
		Integer in = this.getDao().remove4Ids(clazz, rightNow, ids);
		
		if(rollback != null){
			rollback.doRemove(ids);
		}
		return in;
	}

	@Override
	public <T extends SoftEntityModel> void removeEntity(T entity,
			EditStateEnum rightNow,RemoveRollback rollback) {
		if(entity instanceof RemoveListener){
			((RemoveListener)entity).onRemove(this);
		}
		String id = entity.getId();
		this.getDao().remove(entity, rightNow);
		
		if(rollback != null){
			rollback.doRemove(id);
		}
	}

	@Override
	public <T extends SoftEntityModel> String saveEntity(T entity,
			EditStateEnum rightNow, String[] withoutFields) {
		entity.setId(null);
		return this.saveOrUpdateEntity(entity, rightNow, withoutFields);
	}

	@Override
	public <T extends SoftEntityModel> void saveOrUpdateAllEntity(List<T> entities,
			EditStateEnum rightNow) {
		if(entities==null || entities.isEmpty()){
			return;
		}
		for(T entity : entities){
			if(entity instanceof SaveOrUpdateListener){
				((SaveOrUpdateListener)entity).onSaveOrUpdate(this);
			}
			if(entity.isSave()){
				ModelUtil.randomUUID(entity);
			}
			
			this.addModelPlus(entity);
		}
		this.getDao().saveOrUpdateEntity(entities, rightNow);
	}

	private void addModelPlus(SoftEntityModel entity){
		if(entity instanceof ModelPlusListener){
			try {
				PropertyUtils.setProperty(entity, "whsj", DateUtils.parse(DateUtils.DEFALUT_DATETIME_PATTERN));
				PropertyUtils.setProperty(entity, "cjr", "");
				PropertyUtils.setProperty(entity, "jlssdw", "");
				
				if(entity.isSave()){
					PropertyUtils.setProperty(entity, "cjsj", DateUtils.parse(DateUtils.DEFALUT_DATETIME_PATTERN));
				}
			} catch (Exception e) {
				log.info(entity+"放入ModelPlusListener相关值时出错，请检查该对象是否应该去掉ModelPlusListener实现。");
			}
			
		}
	}
	
	@Override
	public <T extends SoftEntityModel> String saveOrUpdateEntity(T entity,
			EditStateEnum rightNow, String[] withoutFields) {
		if(entity instanceof SaveOrUpdateListener){
			((SaveOrUpdateListener)entity).onSaveOrUpdate(this);
		}
		
		if(!entity.isSave()){
			T u = (T) this.get(entity.getClass(), entity.getId());
			
			PropertyUtils.copyTo(entity, u,withoutFields);
			
			entity = u;
			
		}else{
			ModelUtil.randomUUID(entity);
		}
		this.addModelPlus(entity);
		
		return this.getDao().saveOrUpdateEntity(entity, rightNow);
	}

	@Override
	public <T extends SoftEntityModel> String updateEntity(T entity,
			EditStateEnum rightNow, String[] withoutFields) {
		return this.saveOrUpdateEntity(entity, rightNow, withoutFields);
	}

	

	@Override
	public <T extends SoftEntityModel> Integer removeEntity(Class<T> clazz, String ids) {
		return this.removeEntity(clazz, ids, EditStateEnum.RIGHT_NOW_NO,null);
	}

	@Override
	public <T extends SoftEntityModel> void removeEntity(T entity) {
		this.removeEntity(entity, EditStateEnum.RIGHT_NOW_NO,null);
	}

	@Override
	public <T extends SoftEntityModel> String saveEntity(T entity,
			String[] withoutFields) {
		return this.saveEntity(entity, EditStateEnum.RIGHT_NOW_NO, withoutFields);
	}

	@Override
	public <T extends SoftEntityModel> void saveOrUpdateAllEntity(List<T> entities) {
		this.saveOrUpdateAllEntity(entities, EditStateEnum.RIGHT_NOW_NO);
	}

	@Override
	public <T extends SoftEntityModel> String saveOrUpdateEntity(T entity,
			String[] withoutFields) {
		return this.saveOrUpdateEntity(entity, EditStateEnum.RIGHT_NOW_NO, withoutFields);
	}

	@Override
	public <T extends SoftEntityModel> String updateEntity(T entity,
			String[] withoutFields) {
		return this.updateEntity(entity, EditStateEnum.RIGHT_NOW_NO, withoutFields);
	}
	
	
	
}
