package com.myfvn.core.internal.persist.service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import net.foreworld.java.daos.DaoFactory;
import net.foreworld.utils.rcp.core.internal.persist.service.DatabaseEvent;
import net.foreworld.utils.rcp.core.internal.persist.service.IDatabaseListener;
import net.foreworld.utils.rcp.core.persist.events.UserAdapter;
import net.foreworld.utils.rcp.core.persist.events.UserEvent;
import net.foreworld.utils.rcp.core.persist.service.PersistenceException;
import net.foreworld.utils.rcp.core.utils.AbstractLongOperationMonitor;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.myfvn.core.Activator;
import com.myfvn.core.persist.event.UserEventManager;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class DBManager {
	final Logger _logger = Logger.getLogger(DBManager.class.getName());

	private static DBManager _INSTANCE;

	private static final String DB_NAME = "db.db3";//$NON-NLS-1$

	private List<IDatabaseListener> _databaseListeners = new CopyOnWriteArrayList<IDatabaseListener>();

	public static DBManager getDefault() {
		if (_INSTANCE == null)
			_INSTANCE = new DBManager();
		return _INSTANCE;
	}

	private DBManager() {
	}

	public void startup(AbstractLongOperationMonitor $monitor, boolean $emergency, boolean $forRestore) throws PersistenceException {

		if (!$forRestore) {

		}

		if (!this.checkDatabaseExists()) {
			this.createDatabase();
		}

		/** 配置数据库 */
		this.configureDB();

		this.startupDBHelper();
	}

	private void configureDB() {

		ClassLoader __oldLoader = Thread.currentThread().getContextClassLoader();

		try {
			Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());

			Reader __reader = null;
			try {
				__reader = Resources.getResourceAsReader("net/foreworld/java/config/sqlmap-access.xml");//$NON-NLS-1$
			} catch (IOException $ex) {
				throw new PersistenceException(Messages.DBManager_SQLMAP_RESOURCE_IS_NOT_EXISTS);
			}

			Properties __props = new Properties();
			__props.setProperty("driver", "org.sqlite.JDBC");//$NON-NLS-1$
			__props.setProperty("url", "jdbc:sqlite:" + this.getDBFilePath());//$NON-NLS-1$ //$NON-NLS-2$
			__props.setProperty("username", "");//$NON-NLS-1$ //$NON-NLS-2$
			__props.setProperty("password", "");//$NON-NLS-1$ //$NON-NLS-2$

			DaoFactory.getInstance().setSqlMap(SqlMapClientBuilder.buildSqlMapClient(__reader, __props));

		} catch (Exception $ex) {
			$ex.printStackTrace();
			this._logger.warning($ex.getMessage());
		} finally {
			Thread.currentThread().setContextClassLoader(__oldLoader);
		}
	}

	/**
	 * 判断数据库文件是否存在<br/>
	 * 
	 * 结果：存在返回是，不存在返回否
	 * 
	 * @return
	 */
	public boolean checkDatabaseExists() {
		File __file = new File(this.getDBFilePath());
		return __file.exists();
	}

	/**
	 * 创建数据库
	 * 
	 * @throws PersistenceException
	 */
	private void createDatabase() throws PersistenceException {
		this._logger.info("现有数据库找不到或不可用, 创建新数据库");//$NON-NLS-1$

	}

	/**
	 * 数据库关闭
	 * 
	 * @throws PersistenceException
	 */
	public void shutdown() throws PersistenceException {
		this._logger.info("db close");//$NON-NLS-1$

		/** fireDatabaseEvent */
		this.fireDatabaseEvent(new DatabaseEvent(), false);
	}

	private void fireDatabaseEvent(DatabaseEvent $event, boolean $Opened) {
		for (IDatabaseListener __listener : this._databaseListeners) {
			if ($Opened) {
				__listener.databaseOpened($event);
			} else {
				__listener.databaseClosed($event);
			}
		}
	}

	/**
	 * 获取数据库文件路径
	 * 
	 * @return
	 */
	public final String getDBFilePath() {
		return Activator.getDefault().getStateLocation().toOSString() + File.separator + DB_NAME;
	}

	private UserAdapter _userAdapter = null;

	private void startupDBHelper() {
		DBHelper.getDefault().init();

		this._userAdapter = new UserAdapter() {
			public void loginSuccess(UserEvent $event) {
				DBHelper.getDefault().loadRestAll();
			}
		};
		UserEventManager.getDefault().addUserEventListener(this._userAdapter);
	}

	public void addDatabaseListener(IDatabaseListener $listener) {
		_databaseListeners.add($listener);
	}

	public void removeDatabaseListener(IDatabaseListener $listener) {
		_databaseListeners.remove($listener);
	}
}
