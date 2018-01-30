package il.stqa.jav.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import il.stqa.jav.mantis.model.Issue;
import il.stqa.jav.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ilya on 1/30/18
 */
public class SoapHelper {
  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.asList(projects).stream().map((p)->new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
              .getMantisConnectPort(new URL(app.getProperty("web.soap")));
  }

  public Issue addIssue(Issue issue) throws RemoteException, MalformedURLException, ServiceException{
    MantisConnectPortType mc = getMantisConnect();
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
    IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
    return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                    .withName(createdIssueData.getProject().getName()));
  }

  public String getIssueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    return mc.mc_issue_get("administrator", "root",
            BigInteger.valueOf(issueId)).getStatus().getName();
  }

  public boolean isIssueFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String status = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId)).getStatus().getName();
    String resolution = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId)).getResolution().getName();
    if (status.equals("resolved") && resolution.equals("fixed")){
      return true;
    }
    return false;
//    return mc.mc_issue_checkin("administrator", "root", BigInteger.valueOf(issueId), "", true);
  }
}
