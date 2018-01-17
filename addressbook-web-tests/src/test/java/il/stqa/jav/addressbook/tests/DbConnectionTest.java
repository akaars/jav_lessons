package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
import org.testng.annotations.Test;

import java.sql.*;

/**
 * Created by ilya on 1/17/18
 */
public class DbConnectionTest {
  @Test
  public void testDbConnection() {
    Connection conn = null;
    Groups groups = new Groups();
    try{
      conn= DriverManager.getConnection("jdbc:mysql://192.168.56.101:3306/addressbook", "root",
              "test");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
      while(rs.next()) {
        groups.add(new GroupForm().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withFooter(rs.getString("group_footer")).withHeader(rs.getString("group_header")));
      }
      System.out.println("resuts is: " + groups);
      rs.close();
      st.close();
      conn.close();

    } catch (SQLException ex) {
      System.out.println("SQLException " + ex.getMessage());
      System.out.println("SQLState " + ex.getSQLState());
      System.out.println("Vendor error " + ex.getErrorCode());
    }

  }

}
