package mx.com.upax.db.datasources;

import mx.com.upax.utilities.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PostgresSource implements DataSource {
  private static PostgresSource instance;

  private PostgresSource() {
  }

  public static DataSource getInstance() {
    if(instance == null) instance = new PostgresSource();
    return instance;
  }

  @Override
  public Object getObject(String query) throws Exception {
      Session session = NewHibernateUtil.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();
      try {
          Object obj = session.createQuery(query).uniqueResult();
          transaction.commit();
          session.close();
          return obj;
      } catch (Exception e) {
          if (transaction != null) transaction.rollback();
          session.close();
          throw new Exception(
            "Error al consultar el objeto en el motor postgres. " + e.getMessage()
          );
      }
  }

  @Override
  public List getCollection(String query) throws Exception {
      Session session = NewHibernateUtil.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();
      List lista;
      try {
          lista = session.createQuery(query).list();
          transaction.commit();
          session.close();
          return lista;
      } catch (Exception e) {
          if (transaction != null) transaction.rollback();
          session.close();
          throw new Exception(
            "Error al consultar la lista de datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  @Override
  public List getCollection(String query, int n) throws Exception {
      Session session = NewHibernateUtil.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();
      List lista;
      try {
          lista = session.createQuery(query).setMaxResults(n).list();
          transaction.commit();
          session.close();
          return lista;
      } catch (Exception e) {
          if (transaction != null) transaction.rollback();
          session.close();
          throw new Exception(
            "Error al consultar la lista de datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  @Override
  public void insert(Object obj) throws Exception {
      try {
          List l = new ArrayList();
          l.add(obj);
          insert(l);
      } catch(Exception e) {
          throw new Exception(
            "Error al insertar los datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  @Override
  public void insert(List objs) throws Exception {
      Session session = NewHibernateUtil.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();
      try {
          int t = objs.size();
          for (int i = 0; i < t; i++)
              session.save(objs.get(i));
          transaction.commit();
          session.close();
      } catch (Exception e) {
          if (transaction != null) transaction.rollback();
          session.close();
          throw new Exception(
            "Error al insertar los datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  @Override
  public void save(Object obj) throws Exception {
      throw new Exception("Error al guardar los datos en el motor postgres.");
  }

  @Override
  public void update(String query) throws Exception {
      Session session = NewHibernateUtil.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();
      try {
          session.createQuery(query).executeUpdate();
          transaction.commit();
          session.close();
      } catch (Exception e) {
          if (transaction != null) transaction.rollback();
          session.close();
          throw new Exception(
            "Error al actualizar los datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  @Override
  public void update(Object obj) throws Exception {
      try {
          List l = new ArrayList();
          l.add(obj);
          update(l);
      } catch (Exception e) {
          throw new Exception(
            "Error al actualizar los datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  @Override
  public void update(List objs) throws Exception {
      Session session = NewHibernateUtil.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();
      try {
          int t = objs.size();
          for (int i = 0; i < t; i++) session.update(objs.get(i));
          transaction.commit();
          session.close();
      } catch (Exception e) {
          if (transaction != null) transaction.rollback();
          session.close();
          throw new Exception(
            "Error al actualizar los datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  @Override
  public void delete(String query) throws Exception {
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    try {
        session.createQuery(query).executeUpdate();
        transaction.commit();
        session.close();
    } catch (Exception e) {
        throw new Exception("Error al eliminar los datos en el motor postgres.");
    }
  }

  @Override
  public void delete(Object obj) throws Exception {
    try {
        List l = new ArrayList();
        l.add(obj);
        delete(l);
    } catch (Exception e) {
        throw new Exception(
          "Error al eliminar los datos en el motor postgres. " + e.getMessage()
        );
    }
  }

  @Override
  public void delete(List objs) throws Exception {
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    try {
        int t = objs.size();
        for (int i = 0; i < t; i++)
            session.delete(objs.get(i));
        transaction.commit();
        session.close();
    } catch (Exception e) {
        if (transaction != null) transaction.rollback();
        session.close();
        throw new Exception(
          "Error al eliminar los datos en el motor postgres. " + e.getMessage()
        );
    }
  }
}
