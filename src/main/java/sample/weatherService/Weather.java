package sample.weatherService;

import sample.weatherApp.WeatherObject;

import java.util.List;

public interface Weather {
    WeatherObject getWeather(String getCity) throws Exception;
    List<WeatherObject[]> getFiveDayWeather(String getCity, String getDay) throws Exception;
    int checkDay(int sizeFiveDays,  String getDay);


}
