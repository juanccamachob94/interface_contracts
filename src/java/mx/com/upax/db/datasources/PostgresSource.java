package mx.com.upax.db.datasources;

import java.util.List;
import mx.com.upax.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PostgresSource implements DataSource {
  private static PostgresSource instance;

  private Transaction transaction;
  private Session session;
  private boolean transactionStarted;
  private Boolean uniqTransaction;

  private PostgresSource() {
    this.transactionStarted = false;
    this.uniqTransaction = null;
    this.transaction = null;
    this.session = null;
  }

  public static DataSource getInstance() {
  if(instance == null) instance = new PostgresSource();
  return instance;
  }

  @Override
  public void startsTransaction() throws Exception {
    this.transactionStarted = true;
    this.uniqTransaction = false;
    this.launchTransaction();
  }

  private void launchTransaction() throws Exception {
    try {
      this.session = HibernateUtil.getSessionFactory().openSession();
      this.transaction = this.session.beginTransaction();
    }catch(Exception e) {
      throw new Exception("No se puede iniciar la transacción. " + e.getMessage());
    }
  }

  @Override
  public void endsTransaction() throws Exception {
    try {
      this.transactionStarted = false;
      this.uniqTransaction = null;
      this.transaction.commit();
      this.session.close();
    }catch(Exception e) {
      throw new Exception("No se puede finalizar la transacción. " + e.getMessage());
    }
  }

  private void validateCurrentTransaction() throws Exception {
    if(!this.transactionStarted || uniqTransaction())
    throw new Exception("No ha iniciado la transacción correctamente");
  }

  private boolean uniqTransaction() {
    if(this.uniqTransaction == null) return true;
    return this.uniqTransaction;
  }

  @Override
  public Object getObject(String query) throws Exception {
    try {
      if(this.uniqTransaction()) {
        Object o;
        this.launchTransaction();
        o = this.session.createQuery(query).uniqueResult();
        this.endsTransaction();
        return o;
      }

      this.validateCurrentTransaction();
      return this.session.createQuery(query).uniqueResult();
    }catch(Exception e) {
      endsTransaction();
      throw new Exception("Error fatal al consultar el objeto. " + e.getMessage());
    }
  }

  @Override
  public List getCollection(String query) throws Exception {
    try {
      if(this.uniqTransaction()) {
        List l;
        this.launchTransaction();
        l = this.session.createQuery(query).list();
        this.endsTransaction();
        return l;
      }
      this.validateCurrentTransaction();
      return this.session.createQuery(query).list();
    }catch(Exception e) {
      endsTransaction();
      throw new Exception("Error fatal al consultar la lista. " + e.getMessage());
    }
  }

  @Override
  public void insert(Object object) throws Exception {
    try {
      if(this.uniqTransaction()) {
        this.launchTransaction();
        this.session.save(object);
        this.endsTransaction();
      }else {
        this.validateCurrentTransaction();
        this.session.save(object);
      }
    }catch(Exception e) {

      endsTransaction();
      throw new Exception("Error fatal al insertar el objeto " + e.getMessage());
    }
  }

  @Override
  public void insert(List list) throws Exception {
    try {
      if(this.uniqTransaction()) {
        this.launchTransaction();
        int t = list.size();
        for(int i = 0; i < t; i++)
          this.session.save(list.get(i));
        this.endsTransaction();
      }else {
        this.validateCurrentTransaction();
        int t = list.size();
        for(int i = 0; i < t; i++)
          this.session.save(list.get(i));
      }
    }catch(Exception e) {
      endsTransaction();
      throw new Exception("Error fatal al insertar la lista. " + e.getMessage());
    }
  }

  @Override
  public void update(String query) throws Exception {
    try {
      if(this.uniqTransaction()) {
        this.launchTransaction();
        this.session.createQuery(query).executeUpdate();
        this.endsTransaction();
      }else {
        this.validateCurrentTransaction();
        this.session.createQuery(query).executeUpdate();
      }
    }catch(Exception e) {
      endsTransaction();
      throw new Exception("Error fatal al actualizar el objeto. " + e.getMessage());
    }
  }

  @Override
  public void update(Object object) throws Exception {
    try {
      if(this.uniqTransaction()) {
        this.launchTransaction();
        this.session.update(object);
        this.endsTransaction();
      }else {
        this.validateCurrentTransaction();
        this.session.update(object);
      }
    }catch(Exception e) {
      endsTransaction();
      throw new Exception("Error fatal al actualizar el objeto. " + e.getMessage());
    }
  }

  @Override
  public void update(List list) throws Exception {
    try {
      if(this.uniqTransaction()) {
        this.launchTransaction();
        int t = list.size();
        for(int i = 0; i < t; i++)
          this.session.update(list.get(i));
        this.endsTransaction();
      }else {
        this.validateCurrentTransaction();
        int t = list.size();
        for(int i = 0; i < t; i++)
          this.session.update(list.get(i));
      }
    }catch(Exception e) {
      endsTransaction();
      throw new Exception("Error fatal al actualizar la lista. " + e.getMessage());
    }
  }

  @Override
  public void delete(String query) throws Exception {
    try {
      if(this.uniqTransaction()) {
        this.launchTransaction();
        this.session.createQuery(query).executeUpdate();
        this.endsTransaction();
      }else {
        this.validateCurrentTransaction();
        this.session.createQuery(query).executeUpdate();
      }
    }catch(Exception e) {
      endsTransaction();
      throw new Exception("Error fatal al eliminar el objeto. " + e.getMessage());
    }
  }

  @Override
  public void delete(Object object) throws Exception {
    try {
      if(this.uniqTransaction()) {
        this.launchTransaction();
        this.session.delete(object);
        this.endsTransaction();
      }else {
        this.validateCurrentTransaction();
        this.session.delete(object);
      }
    }catch(Exception e) {
      endsTransaction();
      throw new Exception("Error fatal al actualizar el objeto. " + e.getMessage());
    }
  }

  @Override
  public void delete(List list) throws Exception {
    try {
      if(this.uniqTransaction()) {
        this.launchTransaction();
        int t = list.size();
        for(int i = 0; i < t; i++)
          this.session.delete(list.get(i));
        this.endsTransaction();
      } else {
        this.validateCurrentTransaction();
        int t = list.size();
        for(int i = 0; i < t; i++)
          this.session.delete(list.get(i));
      }
    }catch(Exception e) {
      endsTransaction();
      throw new Exception("Error fatal al eliminar la lista. " + e.getMessage());
    }
  }
}
