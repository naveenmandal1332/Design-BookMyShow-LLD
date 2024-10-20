import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {

  Map<City, List<Movie>> totalMovieInCity;
  List<Movie> totalMovie;

  MovieController() {
    this.totalMovieInCity = new HashMap<>();
    this.totalMovie = new ArrayList<>();
  }

  public void addMovie(City city, Movie movie) {
    totalMovie.add(movie);

    List<Movie> movieInCity = new ArrayList<>();
    if (totalMovieInCity.containsKey(city)) {
      movieInCity = totalMovieInCity.get(city);
    }

    totalMovieInCity.put(city, movieInCity);
  }

  public Movie getMovieByName(String movieName) {

    for (Movie currMovie : totalMovie) {
      if (currMovie.getMovieName().equals(movieName)) {
        return currMovie;
      }
    }

    return null;
  }

  public List<Movie> getMovieByCity(City city) {
    return totalMovieInCity.get(city);
  }
}