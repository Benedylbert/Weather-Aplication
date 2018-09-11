package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import sample.weatherApp.WeatherObject;
import sample.weatherServiceImpl.CurrentWeather;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class Controller implements Initializable {

    @FXML
    TextField GetMiasto;
    @FXML
    DatePicker GetData;
    @FXML
    TextField SetMiasto;
    @FXML
    ImageView Weather;
    @FXML
    TextField CurrentLocalData;
    @FXML
    TextField Temp;
    @FXML
    TextField Humidity;
    @FXML
    TextField Pressure;
    @FXML
    TextField OutWeather;
    @FXML
    ImageView CurrentWeatherImage;
    @FXML
    Button Search;
    @FXML
    TextField ChosenDay;
    @FXML
    TextArea FiveDayOutWeather;
    @FXML
    TextField FiveDayOutCity;
    @FXML
    TextField OutTempMax;
    @FXML
    TextField OutTempMin;
    @FXML
    TextField OutWindSpeed;
    @FXML
    TextField OutCloud;
    @FXML
    TextArea FiveDaysOutPressure;
    @FXML
    TextArea FiveDaysOutWindSpeed;




    public void handleButtonAction(ActionEvent event) throws Exception {
        autoCompletionBinding = TextFields.bindAutoCompletion(GetMiasto, possibleWordSet);
        learnWord(GetMiasto.getText());


        if (GetData.getValue() != null) { try{getOneForFiveDayWeather();
        }catch (Exception e){GetMiasto.setText("Brak miasta w bazie danych");
            clearFiveDayWeather();
            clearOneDayWeather();}
        } else {
            try{getOneDayWeather();
            }catch (Exception e1) {
                GetMiasto.setText("Brak miasta w bazie danych");
                clearOneDayWeather();
            }
        }
    }
    Set<String> possibleWordSet = new HashSet<>();
    private AutoCompletionBinding<String> autoCompletionBinding;


    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("C:\\Users\\Konrad\\IdeaProjects\\WeatherApp\\Images\\pogoda.png");
        Image image = new Image(file.toURI().toString());
        Weather.setImage(image);
        autoCompletionBinding = TextFields.bindAutoCompletion(GetMiasto, possibleWordSet);


    }
    private void learnWord(String txt){
        possibleWordSet.add(txt);
        if(autoCompletionBinding==null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(GetMiasto, possibleWordSet);

    }
    Calendar calendarData = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = dateFormat.format(calendarData.getTime());

    private void getOneDayWeather() throws  Exception{
            String text = GetMiasto.getText();
            SetMiasto.setText(text);
            WeatherObject weatherObject = new CurrentWeather().getWeather(GetMiasto.getText());
            OutWeather.setText(weatherObject.getRestWeather());
            Temp.setText(weatherObject.getRestTemp() + "K");
            Humidity.setText(weatherObject.getRestHumidity() + "%");
            Pressure.setText(weatherObject.getRestPressure() + " hPa");
            CurrentLocalData.setText(dateString);
            CurrentWeatherImage.setImage(weatherObject.getImage());
            OutTempMax.setText(weatherObject.getRestMaxTemp() + "K");
            OutTempMin.setText(weatherObject.getRestMinTemp() + "K");
            OutCloud.setText(weatherObject.getRestClouds() + "%");
            OutWindSpeed.setText(weatherObject.getRestWindSpeed() + "m/s");
        }
        public void clearOneDayWeather(){
            OutWeather.setText("");
            Temp.setText("");
            Humidity.setText("");
            Pressure.setText("");
            CurrentLocalData.setText("");
            SetMiasto.setText("");
            OutTempMax.setText("");
            OutTempMin.setText("");
            OutCloud.setText("");
            OutWindSpeed.setText("");
            CurrentWeatherImage.setImage(null);
        }

    private void getOneForFiveDayWeather() throws Exception {
        String date = GetData.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ChosenDay.setText(date);
        String weatherOut = "";
        String windOut = "";
        String pressureOut = "";
        double hour = 0.00;
        List<WeatherObject[]> weatherList = new CurrentWeather().getFiveDayWeather(GetMiasto.getText(), date);
        int getNumberOfDay = new CurrentWeather().checkDay(weatherList.size(), date);
        WeatherObject[] getOneDay = weatherList.get(getNumberOfDay);
        for (WeatherObject object : getOneDay) {
            weatherOut += ("Godzina: " + hour + "0" + ", Temp: " + object.getRestTemp() + " -  " + object.getRestWeather() + "\n");
            windOut += ("Godzina: " + hour + "0" + ":  " + object.getRestWindSpeed() + " m/s\n");
            pressureOut += ("Godzina: " + hour + "0" + ":  " + object.getRestPressure() + " hPa\n");

            hour += 3.00;
        }
        if (date.equals(dateString)) {
            FiveDayOutCity.setText(GetMiasto.getText());
            FiveDayOutWeather.setText(weatherOut);
            FiveDaysOutPressure.setText(pressureOut);
            FiveDaysOutWindSpeed.setText(windOut);
            String text = GetMiasto.getText();
            SetMiasto.setText(text);
            WeatherObject weatherObject = new CurrentWeather().getWeather(GetMiasto.getText());
            OutWeather.setText(weatherObject.getRestWeather());
            Temp.setText(weatherObject.getRestTemp() + "K");
            Humidity.setText(weatherObject.getRestHumidity() + "%");
            Pressure.setText(weatherObject.getRestPressure() + " hPa");
            CurrentLocalData.setText(dateString);
            CurrentWeatherImage.setImage(weatherObject.getImage());
            OutTempMax.setText(weatherObject.getRestMaxTemp() + "K");
            OutTempMin.setText(weatherObject.getRestMinTemp() + "K");
            OutCloud.setText(weatherObject.getRestClouds() + "%");
            OutWindSpeed.setText(weatherObject.getRestWindSpeed() + "m/s");

        } else {
            FiveDayOutCity.setText(GetMiasto.getText());
            FiveDayOutWeather.setText(weatherOut);
            FiveDaysOutPressure.setText(pressureOut);
            FiveDaysOutWindSpeed.setText(windOut);
            clearOneDayWeather();
        }
    }
    public void clearFiveDayWeather(){
        FiveDayOutCity.setText("");
        FiveDayOutWeather.setText("");
        FiveDaysOutPressure.setText("");
        FiveDaysOutWindSpeed.setText("");
        ChosenDay.setText("");
    }
}