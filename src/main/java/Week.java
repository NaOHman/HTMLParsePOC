/**
 * Created by jeffrey on 2/11/14.
 * the week class provides describes the menu for a week in cafe mac
 * it's composed of a list of days. Creating a weeks will automatically
 * parse the menu from cafe mac's website
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Week {

    private Day[] days = new Day[7];

    /**
     * creates a new weekly menu by scraping data from the Bon Appetit website
     */
    public Week(){
        try {
            Document doc = Jsoup.connect("http://macalester.cafebonappetit.com/hungry/cafe-mac/").get();
            Elements dayData = doc.getElementsByClass("eni-menu-day");
            makeDays(dayData);
        }
        //this means connection was probably lost
        catch(IOException e){
            System.out.println("No Connection");
        }
    }

    /**
     * helper method for the constructor that creates daily menus and stores them inside the weekly menu
     * @param dayData a collection of HTML elements with the data needed to create daily menus
     */
    public void makeDays(Elements dayData){
        // Days of week start with 0 on Sunday
        for (Element dayMenu : dayData){
            String dayOfWeek = dayMenu.className();
            dayOfWeek = dayOfWeek.substring(dayOfWeek.length() -1);
            days[Integer.parseInt(dayOfWeek)] = new Day(dayMenu);
        }
    }

    /**
     * @param day a day of the week represented as an int
     * @return the daily menu of that day
     */
    public Day getDay(Weekday day){
        return days[day.ordinal()];
    }

    /**
     * @return a human readable version of the Week's menu
     */
    public String toString(){
        String menu = "";
        for (Day day : days){
            menu = menu + day.toString() + "\n";
        }
        return menu;
    }
}
