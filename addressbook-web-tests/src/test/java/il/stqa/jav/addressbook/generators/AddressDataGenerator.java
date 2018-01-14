package il.stqa.jav.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import il.stqa.jav.addressbook.model.AddressForm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class AddressDataGenerator {

  @Parameter(names = "-c", description = "Addresses count")
  public int count;

  @Parameter(names = "-f", required=true, description = "Target file name")
  public String file;


  public static void main(String[] args) throws IOException {
    AddressDataGenerator generator = new AddressDataGenerator();
    try {
      JCommander.newBuilder().addObject(generator).build().parse(args);
    } catch (ParameterException ex) {
      ex.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<AddressForm> addrs = generateAddress(count);
    saveAsJson(addrs, new File(file));
    }

  private void saveAsJson(List<AddressForm> addrs, File file) throws IOException {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
            .create();
    String json = gson.toJson(addrs);
    try(Writer writer = new FileWriter(file)){
      writer.write(json);
    }
  }

  private List<AddressForm> generateAddress(int count) {
    List<AddressForm> addrs = new ArrayList<AddressForm>();
    for (int i=1; i<=count; i++){
      addrs.add(new AddressForm().withFirstName(String.format("FirstName%s", i))
              .withSecondName(String.format("SecondName%s", i)).withEmail(String.format("firstEmail%s@test.com", i))
              .witheMail2(String.format("secondEmail%s@test.com", i)).witheMail3(String.format("thirdEmail%s@test.com", i))
      .withHomePhone(String.format("+11 22 33345%s", i)).withMobile(String.format("+2222222-%s", i))
      .withWork(String.format("03344 %s", i)));
    }
    return addrs;
  }
}
