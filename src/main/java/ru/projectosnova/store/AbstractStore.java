package ru.projectosnova.store;

import ru.projectosnova.config.ConfigStorage;

public abstract class AbstractStore {
    private ConfigStorage config;

    public AbstractStore(ConfigStorage config) {
        this.config = config;
    }

    abstract public String create(String objectTypeName, String json, String params)throws Exception;

    /*
	//CRUD operations
	abstract public String create(String objectTypeName,String json)throws Exception;
	abstract public String create(String objectTypeName, String json, String params)throws Exception;

	abstract public String read(String unid,String params)throws Exception;
	abstract public String read(String unid)throws Exception;

	abstract public boolean update(String unid, String json)throws Exception;
	abstract public boolean update(String unid, String json, boolean replaceAllItems)throws Exception;
	abstract public boolean update(String unid, String json, String params)throws Exception;
	abstract public boolean update(String unid, String json, boolean replaceAllItems, String params)throws Exception;

	abstract public boolean delete(String unid, String params)throws Exception;
	abstract public boolean delete(String unid)throws Exception;

	//Collection operations
	abstract public String getCollection(String collection, String params) throws Exception;
	abstract public String searchByKey(String collection, String key, boolean exactMatch, String params)throws Exception;
 */

}
