package desafio.com.br.desafioandroid.service;

import desafio.com.br.desafioandroid.model.CityWeather;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface IService {

    @GET("weather")
    Observable<CityWeather> getWeather(@Query("id") Integer id,
                                       @Query("appid") String appId,
                                       @Query("lang") String language,
                                       @Query("units") String units);

}
