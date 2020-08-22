package mx.com.upax.db.datasources;

import java.util.List;

public interface DataSource {
  public Object getObject(String query) throws Exception;
  public List getCollection(String query) throws Exception;
  public List getCollection(String query, int n) throws Exception;
  public void insert(Object obj) throws Exception;
  public void insert(List objs) throws Exception;
  public void save(Object object) throws Exception;
  public void update(String query) throws Exception;
  public void update(Object obj) throws Exception;
  public void update(List objs) throws Exception;
  public void delete(String query) throws Exception;
  public void delete(Object obj) throws Exception;
  public void delete(List objs) throws Exception;
}
