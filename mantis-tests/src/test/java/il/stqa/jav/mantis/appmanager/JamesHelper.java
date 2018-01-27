package il.stqa.jav.mantis.appmanager;

import il.stqa.jav.mantis.model.MailMessage;
import org.apache.commons.net.telnet.TelnetClient;

import javax.mail.Session;
import javax.mail.Store;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class JamesHelper {
  private ApplicationManager app;
  private TelnetClient telnet;
  private InputStream in;
  private PrintStream out;

  private Session mailSession;
  private Store store;
  private String mailserver;

  public JamesHelper (ApplicationManager app) {
    this.app = app;
    telnet = new TelnetClient();
    mailSession = Session.getDefaultInstance(System.getProperties());
  }

  public void createUser(String user, String password) {
    initTelnetSession();
    write("adduser " + user + " " + password);
    String result = readUntil("User " + name + " added");
    closeTelnetSession();
  }

  private void closeTelnetSession() {
    write("quit");
  }

  private void initTelnetSession() {
    mailserver = app.getProperty("mailserver.host");
    int port = Integer.parseInt(app.getProperty("mailserver.port"));
    String login = app.getProperty("mailserver.adminlogin");
    String password = app.getProperty("mailserver.adminpass");
    try {
      telnet.connect(mailserver, port);
      in = telnet.getInputStream();
      out = new PrintStream(telnet.getOutputStream());
    }catch (Exception e){
      e.printStackTrace();
    }
    readUntil("Login id:");
    write("");
    readUntil("Password");
    write("");
  }

  private void write(String value) {
    try{
      out.println(value);
      out.flush();
      System.out.println(value);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  private String readUntil(String pattern) {
    try {
      char lastChar = pattern.charAt(pattern.length() - 1);
      StringBuffer sb = new StringBuffer();
      char ch = (char) in.read();
      while (true) {
        System.out.print(char);
        sb.append(char);
        if(ch == lastChar){
          if (sb.toString().endsWith(pattern)){
            return sb.toString();
          }
        }
        ch = (char) in.read();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<MailMessage> waitForMail(int count, int timeout) {
    return null;
  }
}
