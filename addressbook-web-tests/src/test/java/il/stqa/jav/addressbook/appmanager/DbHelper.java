package il.stqa.jav.addressbook.appmanager;

import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper() {
      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

  public Addrs addrs() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<AddressForm> result = session.createQuery( "from AddressForm where deprecated='0000-00-00'" ).list();
    session.getTransaction().commit();
    session.close();
    return new Addrs(result);
  }
  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupForm> result = session.createQuery( "from GroupForm" ).list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  }

