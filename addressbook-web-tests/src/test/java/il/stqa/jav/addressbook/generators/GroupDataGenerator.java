package il.stqa.jav.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import il.stqa.jav.addressbook.model.GroupForm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 1/9/18
 */
public class GroupDataGenerator {
  @Parameter(names = "-c", description = "Groups count")
  public int count;

  @Parameter(names = "-f", description = "Target file name")
  public String file;

  @Parameter(names = "-d", description = "Target file format")
  public String format;

  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    try {
      JCommander.newBuilder().addObject(generator).build().parse(args);
    } catch (ParameterException ex) {
      ex.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<GroupForm> groups = generateGroup(count);
    if (format.equals("csv")){
      saveAsCsv(groups, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(groups, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(groups, new File(file));
    } else {
      System.out.println("Unsupported file format" + format);
      return;
    }
  }

  private void saveAsJson(List<GroupForm> groups, File file) throws IOException {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
            .create();
    String json = gson.toJson(groups);
    try(Writer writer = new FileWriter(file)){
      writer.write(json);
    }
  }

  private void saveAsXml(List<GroupForm> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupForm.class);
    String xml = xstream.toXML(groups);
    try(Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<GroupForm> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try(Writer writer = new FileWriter(file)){
      for (GroupForm group: groups) {
        writer.write(String.format("%s;%s;%s\n", group.getGroupName(), group.getHeader(), group.getFooter()));
      }
    }
  }

  private List<GroupForm> generateGroup(int count) {
    List<GroupForm> groups = new ArrayList<GroupForm>();
    for (int i=0; i<count; i++) {
      groups.add(new GroupForm().withName(String.format("test %s", i)).withHeader(String.format("header %s", i)).
              withFooter(String.format("footer %s", i)));
    }
    return groups;
  }
}
