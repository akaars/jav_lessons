package il.stqa.jav.mantis.tests;

import il.stqa.jav.mantis.model.MantisUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by ilya on 1/18/18
 */
public class HbConnectionTest {
  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }


  @Test
  public void testHbConnection(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<MantisUser> result = session.createQuery( "from MantisUser" ).list();
    session.getTransaction().commit();
    session.close();
    for ( MantisUser mantisUser : result ) {
      System.out.println(mantisUser);
    }
  }
}
