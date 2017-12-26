package il.stqa.jav.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ilya on 12/26/17
 */
public class Collections {
  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python","PHP"};
    List<String> languages = Arrays.asList(langs);
//    for (int i = 0; i < langs.length; i++){
//      languages.add(langs[i]);
//    }
    for (String l : languages) {
      System.out.println("I wanna learn " + l);
    }
  }
}
