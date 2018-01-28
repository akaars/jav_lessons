package il.stqa.jav.mantis.appmanager;

import il.stqa.jav.mantis.model.MantisUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Random;

public class DbHelper {
  private final SessionFactory sessionFactory;
  private ApplicationManager app;

  public DbHelper(ApplicationManager app) {
      // A SessionFactory is set up once for an application!
    this.app = app;
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

  public List<MantisUser> users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<MantisUser> result = session.createQuery( "from MantisUser" ).list();
    session.getTransaction().commit();
    session.close();
    return result;
  }

  public MantisUser randomUser(List<MantisUser> listOfUsers){
    Random rand = new Random();
    MantisUser user = listOfUsers.get(rand.nextInt(listOfUsers.size()));
    return user;
  }
}

