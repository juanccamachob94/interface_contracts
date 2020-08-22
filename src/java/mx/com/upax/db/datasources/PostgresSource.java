package mx.com.db.upax.datasources;

import mx.com.upax.utilities.NewHibernateUtil;
import java.math.BigDecimal;
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

  public Object getObject(String query) throws ExceptionFatal {
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
          throw new ExceptionFatal(
            "Error al consultar el objeto en el motor postgres. " + e.getMessage()
          );
      }
  }

  public List getCollection(String query) throws ExceptionFatal {
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
          throw new ExceptionFatal(
            "Error al consultar la lista de datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  public List getCollection(String query, int n) throws ExceptionFatal {
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
          throw new ExceptionFatal(
            "Error al consultar la lista de datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  public void insert(Object obj) throws ExceptionFatal {
      try {
          List l = new ArrayList();
          l.add(obj);
          insertar(l);
      } catch (ExceptionFatal e) {
          throw e;
      } catch (Exception e) {
          throw new ExceptionFatal(
            "Error al insertar los datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  public void insert(List objs) throws ExceptionFatal {
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
          throw new ExceptionFatal(
            "Error al insertar los datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  public void save(Object obj) throws ExceptionFatal {
      throw new ExceptionFatal("Error al guardar los datos en el motor postgres.");
  }

  public void update(String query) throws ExceptionFatal {
      Session session = NewHibernateUtil.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();
      try {
          session.createQuery(query).executeUpdate();
          transaction.commit();
          session.close();
      } catch (Exception e) {
          if (transaction != null) transaction.rollback();
          session.close();
          throw new ExceptionFatal(
            "Error al actualizar los datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  public void update(Object obj) throws ExceptionFatal {
      try {
          List l = new ArrayList();
          l.add(obj);
          actualizar(l);
      } catch (Exception e) {
          throw new ExceptionFatal(
            "Error al actualizar los datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  public void update(List objs) throws ExceptionFatal {
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
          throw new ExceptionFatal(
            "Error al actualizar los datos en el motor postgres. " + e.getMessage()
          );
      }
  }

  public void delete(String query) throws ExceptionFatal {
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    try {
        session.createQuery(query).executeUpdate();
        transaction.commit();
        session.close();
    } catch (Exception e) {
        throw new ExceptionFatal("Error al eliminar los datos en el motor postgres.");
    }
  }

  public void delete(Object obj) throws ExceptionFatal {
    try {
        List l = new ArrayList();
        l.add(obj);
        eliminar(l);
    } catch (Exception e) {
        throw new ExceptionFatal(
          "Error al eliminar los datos en el motor postgres. " + e.getMessage()
        );
    }
  }

  public void delete(List objs) throws ExceptionFatal {
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
        throw new ExceptionFatal(
          "Error al eliminar los datos en el motor postgres. " + e.getMessage()
        );
    }
  }
}
