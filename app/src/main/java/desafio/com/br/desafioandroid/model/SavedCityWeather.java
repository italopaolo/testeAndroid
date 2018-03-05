package desafio.com.br.desafioandroid.model;


import java.util.ArrayList;

public class SavedCityWeather {

    private ArrayList<CityWeather> saved = new ArrayList<>();

    public ArrayList<CityWeather> getSaved() {
        return saved;
    }

    public void setSaved(ArrayList<CityWeather> saved) {
        this.saved = saved;
    }
}
