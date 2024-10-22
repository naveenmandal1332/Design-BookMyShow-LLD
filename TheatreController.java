import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {

  Map<City, List<Theatre>> cityWiseTheatre;
  List<Theatre> totalTheatres;

  TheatreController() {
    cityWiseTheatre = new HashMap<>();
    totalTheatres = new ArrayList<>();
  }

  public void addTheatres(City city, Theatre theatre) {

    totalTheatres.add(theatre);

    List<Theatre> theatreInCity = new ArrayList<>();
    if (cityWiseTheatre.containsKey(city)) {
      theatreInCity = cityWiseTheatre.get(city);
    }

    theatreInCity.add(theatre);
    cityWiseTheatre.put(city, theatreInCity);

  }

  public Map<Theatre, List<Show>> getAllShow(Movie movie, City city) {

    Map<Theatre, List<Show>> totalShowInCity = new HashMap<>();
    List<Theatre> totalTheatreInCity = cityWiseTheatre.get(city);

    for (Theatre theatre : totalTheatreInCity) {

      List<Show> theatreVsShow = new ArrayList<>();
      List<Show> show = theatre.getShows();

      for (Show currShow : show) {
        if (currShow.movie.getMovieId() == movie.getMovieId()) {
          theatreVsShow.add(currShow);
        }
      }

      if (!theatreVsShow.isEmpty())
        totalShowInCity.put(theatre, theatreVsShow);
    }

    return totalShowInCity;
  }
}