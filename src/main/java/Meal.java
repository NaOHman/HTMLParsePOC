
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jeffrey on 2/11/14.
 * a Meal represents all a meal in cafe mac, it's broken up into different stations
 */

public class Meal {
    private Long mealId; //DB only

    private Set<Station> stations;

    private Date date;

    private MealType mealType; //DB only
    /**
     * constructor that creates a meal
     * @param mealData HTML Element containing the data needed to make a meal
     */
    public Meal(Element mealData, MealType mealType){
        this.mealType = mealType;
        stations = new HashSet<Station>();
        Elements items = mealData.select("tbody > *");
        Station currentStation = new Station("", this);
        for (Element item : items){
            if (item.hasClass("menu-station")) {
                String stationName = item.getElementsByTag("td").first().text();
                currentStation = new Station(stationName, this);
                stations.add(currentStation);
            } else if (item.hasClass("price-")) {
                Food food = new Food(item);
                currentStation.addFood(food);
                food.addStation(currentStation);
            }
        }
    }

    public Meal(){
        stations = new HashSet<Station>();
    }

    public void setStations(Set<Station> stations){
        this.stations = stations;
    }

    public void setDate(Date date, MealType mealType){
        this.date = date;
        this.mealType = mealType;
    }

    public Set<Station> getStations(){
        return stations;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return mealId;
    }

    public void setId(Long id) {
        this.mealId = mealId;
    }

    public String toString(){
        String meal = "-" + mealType.toString() +"\n";
        for(Station station : stations)
            meal += station.toString();
        return meal;
    }

    public boolean sameMeal(Meal other){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(this.date).equals(fmt.format(other.date))
                && (this.mealType == other.mealType);
    }
}
