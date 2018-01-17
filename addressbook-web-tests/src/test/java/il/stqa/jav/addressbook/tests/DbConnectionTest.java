package il.stqa.jav.addressbook.tests;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ilya on 1/17/18
 */
public class DbConnectionTest {
  @Test
  public void testDbConnection() {
    try{
      Connection con= DriverManager.getConnection("jdbc:mysql://192.168.56.101:3306/addressbook",
              "root","");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
