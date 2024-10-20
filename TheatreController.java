import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {

  Map<City, List<Theatre>> cityWiseTheatre;
  List<Theatre> totalTheatres;

  public TheatreController() {
    this.cityWiseTheatre = new HashMap<>();
    this.totalTheatres = new ArrayList<>();
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

  public Map<Theatre, List<Show>> getAllShow(Movie movieName, City city) {

    List<Theatre> totalTheatreInCity = cityWiseTheatre.get(city);
    Map<Theatre, List<Show>> totalShowInCity = new HashMap<>();

    for (int ind = 0; ind < totalTheatreInCity.size(); ind++) {
      Theatre theatre = totalTheatreInCity.get(ind);

      List<Show> theatreVsShow = new ArrayList<>();
      List<Show> show = theatre.getShows();

      for (Show currShow : show) {
        if (currShow.getMovie().getMovieId() == movieName.getMovieId()) {
          theatreVsShow.add(currShow);
        }
      }

      totalShowInCity.put(theatre, theatreVsShow);
    }

    return totalShowInCity;
  }
}