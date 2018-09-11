package sample.weatherApp;
import javafx.scene.image.Image;

public class WeatherObject {
    private String restWeather;
    private String restHumidity;
    private String restPressure;
    private String restTemp;
    private String restMaxTemp;
    private String restMinTemp;
    private Image image;
    private String restIcon;
    private String restWindSpeed;
    private String restClouds;


    public WeatherObject(){}

    public WeatherObject(String restWeather, String restHumidity, String restPressure, String restTemp, String restMaxTemp, String restMinTemp, Image image, String restIcon, String restWindSpeed, String restClouds) throws Exception{
        this.restWeather = restWeather;
        this.restHumidity = restHumidity;
        this.restPressure = restPressure;
        this.restTemp = restTemp;
        this.restMaxTemp = restMaxTemp;
        this.restMinTemp = restMinTemp;
        this.image = image;
        this.restIcon = restIcon;
    }

    public String getRestWeather() {
        return restWeather;
    }

    public void setRestWeather(String restWeather) {
        this.restWeather = restWeather;
    }

    public String getRestIcon() {
        return restIcon;
    }

    public void setRestIcon(String restIcon) {
        this.restIcon = restIcon;
    }

    public String getRestTemp() {
        return restTemp;
    }

    public void setRestTemp(String restTemp) {
        this.restTemp = restTemp;
    }

    public String getRestHumidity() {
        return restHumidity;
    }

    public void setRestHumidity(String restHumidity) {
        this.restHumidity = restHumidity;
    }

    public String getRestPressure() {
        return restPressure;
    }

    public void setRestPressure(String restPressure) {
        this.restPressure = restPressure;
    }

    public String getRestMaxTemp() {
        return restMaxTemp;
    }

    public void setRestMaxTemp(String restMaxTemp) {
        this.restMaxTemp = restMaxTemp;
    }

    public String getRestMinTemp() {
        return restMinTemp;
    }

    public void setRestMinTemp(String restMinTemp) {
        this.restMinTemp = restMinTemp;
    }

    public Image getImage() throws Exception {
        return image;
    }

    public void setImage(Image image) throws Exception{
        this.image = image;
    }

    public String getRestWindSpeed() {
        return restWindSpeed;
    }

    public void setRestWindSpeed(String restWindSpeed) {
        this.restWindSpeed = restWindSpeed;
    }

    public String getRestClouds() {
        return restClouds;
    }

    public void setRestClouds(String restClouds) {
        this.restClouds = restClouds;
    }
}
