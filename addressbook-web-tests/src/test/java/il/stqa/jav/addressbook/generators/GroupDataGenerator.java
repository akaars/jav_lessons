package il.stqa.jav.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import il.stqa.jav.addressbook.model.GroupForm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ilya on 1/9/18
 */
public class GroupDataGenerator {
  @Parameter(names = "-c", description = "Groups count")
  public int count;

  @Parameter(names = "-f", description = "Target file name")
  public String file;

  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    try {
      JCommander.newBuilder().addObject(generator).build().parse(args);
    } catch (ParameterException ex) {
      ex.usage();
      return;
    }

//    int count = Integer.parseInt(args[0]);
//    File file = new File(args[1]);
    generator.run();
  }

  private void run() throws IOException {
    List<GroupForm> groups = generateGroup(count);
    save(groups, new File(file));
  }

  private void save(List<GroupForm> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (GroupForm group: groups) {
      writer.write(String.format("%s;%s;%s\n", group.getGroupName(), group.getHeader(), group.getFooter()));
    }
    writer.close();
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
