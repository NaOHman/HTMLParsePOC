/**
 * Created by jeffrey on 2/11/14.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

/**
 * this class exists for testing and running different parts of the menu framework
 * It should not be included in our app
 */
public class Puller {
   public static void main(String[] args){
       try{
           Document doc = Jsoup.connect("http://macalester.cafebonappetit.com/hungry/cafe-mac/").get();
           Week week = new Week(doc);
           System.out.println(week.getFoods().size());
           week.clean();
           System.out.println(week.getFoods().size());
       } catch (IOException e){

       }
   }
    private static int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }
}
