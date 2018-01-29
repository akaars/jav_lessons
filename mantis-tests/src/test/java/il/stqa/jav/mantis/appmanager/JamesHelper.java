package il.stqa.jav.mantis.appmanager;

import il.stqa.jav.mantis.model.MailMessage;
import org.apache.commons.net.telnet.TelnetClient;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    String result = readUntil("User " + user + " added");
    closeTelnetSession();
  }

  public void deleteUser(String user, String password) {
    initTelnetSession();
    write("deluser " + user);
    String result = readUntil("User " + user + " deleted");
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
    write(login);
    readUntil("Password:");
    write(password);
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
        System.out.print(ch);
        sb.append(ch);
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

  public List<MailMessage> waitForMail(int count, long timeout, String username, String password)
          throws MessagingException, IOException {
    long start = System.currentTimeMillis();
    while (System.currentTimeMillis() < start + timeout) {
      List<MailMessage> allMail = getAllMail(username, password);
      if(allMail.size() > 0) {
        return allMail;
      }
      try {
        Thread.sleep(1000);
      }catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail received");
  }

  private Folder openInbox(String username, String password) throws MessagingException {
    store = mailSession.getStore("pop3");
    store.connect(mailserver, 1110, username, password);
    Folder folder = store.getDefaultFolder().getFolder("INBOX");
    folder.open(Folder.READ_WRITE);
    return folder;
  }

  private void closeFolder(Folder folder) throws MessagingException {
    folder.close(true);
    store.close();
  }

  public List<MailMessage> getAllMail(String username, String password) throws MessagingException {
    Folder inbox = openInbox(username, password);
    List<MailMessage> messages = Arrays.stream(inbox.getMessages())
            .map(JamesHelper::toModelMail).collect(Collectors.toList());
    closeFolder(inbox);
    return messages;
  }

  private static MailMessage toModelMail(Message m) {
    try{
      return new MailMessage(m.getAllRecipients()[0].toString(), (String)m.getContent());
    }catch (MessagingException e) {
      e.printStackTrace();
      return null;
    }catch (IOException e){
      e.printStackTrace();
      return null;
    }
  }

  public void drainEmail(String username, String password) throws MessagingException {
    Folder inbox = openInbox(username, password);
    for (Message message : inbox.getMessages()){
      message.setFlag(Flags.Flag.DELETED, true);
    }
    closeFolder(inbox);
  }

  public boolean verifyUserExist(String username) {
    initTelnetSession();
    write("listusers");
    String result = readUntil("Existing accounts");
    return result.contains(username);
  }

  public void deleteUser(String username){
    initTelnetSession();
    write("deluser " + username);
    readUntil("User ");
  }
}
