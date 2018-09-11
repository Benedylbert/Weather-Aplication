package sample.weatherServiceImpl;
import javafx.embed.swing.SwingFXUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;;
import sample.weatherApp.WeatherObject;
import sample.weatherService.Weather;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class CurrentWeather implements Weather {
    public WeatherObject getWeather(String getCity) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String restResult = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q="+getCity+"&appid=846c2da22cb466363dc0150972cada60", String.class);
        JSONObject jsonObject = new JSONObject(restResult);

        WeatherObject weatherObject = new WeatherObject();

        weatherObject.setRestIcon(jsonObject.getJSONArray("weather").getJSONObject(0).get("icon").toString());
        URL url = new URL("http://openweathermap.org/img/w/"+weatherObject.getRestIcon()+".png");
        BufferedImage bufferedImage = ImageIO.read(url);
        weatherObject.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        weatherObject.setRestWeather(jsonObject.getJSONArray("weather").getJSONObject(0).get("description").toString());
        weatherObject.setRestHumidity(jsonObject.getJSONObject("main").get("humidity").toString());
        weatherObject.setRestPressure( jsonObject.getJSONObject("main").get("pressure").toString());
        weatherObject.setRestTemp(jsonObject.getJSONObject("main").get("temp").toString());
        weatherObject.setRestMaxTemp(jsonObject.getJSONObject("main").get("temp_max").toString());
        weatherObject.setRestMinTemp(jsonObject.getJSONObject("main").get("temp_min").toString());
        weatherObject.setRestClouds(jsonObject.getJSONObject("clouds").get("all").toString());
        weatherObject.setRestWindSpeed(jsonObject.getJSONObject("wind").get("speed").toString());

        return weatherObject;
    }

    public List<WeatherObject[]> getFiveDayWeather(String getCity, String getDay) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String restResult = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/forecast?q="+getCity+",us&mode=json&appid=846c2da22cb466363dc0150972cada60", String.class);
        JSONObject jsonObject = new JSONObject(restResult);

        List<WeatherObject[]> fiveDays = new ArrayList<>();
        WeatherObject[] oneDay = new WeatherObject[8];
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        int i=0;
        for(Object object: jsonArray){
            if(object instanceof JSONObject){
                WeatherObject weatherObject = new WeatherObject();
                weatherObject.setRestTemp(((JSONObject) object).getJSONObject("main").get("temp").toString());
                weatherObject.setRestWeather(((JSONObject) object).getJSONArray("weather").getJSONObject(0).get("description").toString());
                weatherObject.setRestPressure(((JSONObject) object).getJSONObject("main").get("pressure").toString());
                weatherObject.setRestWindSpeed(((JSONObject) object).getJSONObject("wind").get("speed").toString());
                oneDay[i]=weatherObject;
                i++;
                if(i==8){
                    fiveDays.add(oneDay);
                    i=0;
                    oneDay = new WeatherObject[8];
                }
            }
        }

        return fiveDays;
    }

    @Override
    public int checkDay(int sizeFiveDays, String getDay)  {

        Calendar calendarData = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data = dateFormat.format(calendarData.getTime());
        for(int i=0;i<sizeFiveDays;i++){
            if (getDay.equals(data)) {
                return i;

            }else{
                calendarData.add(Calendar.DAY_OF_YEAR, 1);
                data = dateFormat.format(calendarData.getTime());
            }
        }

        return 0;
    }

}
