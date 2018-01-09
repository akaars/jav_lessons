package il.stqa.jav.addressbook.generators;

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
  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<GroupForm> groups = generateGroup(count);
    save(groups, file);
  }

  private static void save(List<GroupForm> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (GroupForm group: groups) {
      writer.write(String.format("%s;%s;%s\n", group.getGroupName(), group.getHeader(), group.getFooter()));
    }
    writer.close();
  }

  private static List<GroupForm> generateGroup(int count) {
    List<GroupForm> groups = new ArrayList<GroupForm>();
    for (int i=0; i<count; i++) {
      groups.add(new GroupForm().withName(String.format("test %s", i)).withHeader(String.format("header %s", i)).
              withFooter(String.format("footer %s", i)));
    }
    return groups;
  }
}
