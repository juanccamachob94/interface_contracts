package mx.com.upax.db.datasources;

import java.util.List;

public interface DataSource {
  public void startsTransaction() throws Exception;
  public void endsTransaction() throws Exception;
  public Object getObject(String query) throws Exception;
  public List getCollection(String query) throws Exception;
  public void insert(Object object) throws Exception;
  public void insert(List list) throws Exception;
  public void update(String query) throws Exception;
  public void update(Object object) throws Exception;
  public void update(List list) throws Exception;
  public void delete(String query) throws Exception;
  public void delete(Object object) throws Exception;
  public void delete(List list) throws Exception;
}
