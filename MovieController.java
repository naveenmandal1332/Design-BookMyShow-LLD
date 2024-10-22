import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {

  Map<City, List<Movie>> totalMovieInCity;
  List<Movie> totalMovie;

  MovieController() {
    totalMovieInCity = new HashMap<>();
    totalMovie = new ArrayList<>();
  }

  void addMovie(City city, Movie movie) {
    totalMovie.add(movie);
    List<Movie> movies = totalMovieInCity.getOrDefault(city, new ArrayList<>());
    movies.add(movie);
    totalMovieInCity.put(city, movies);
  }

  Movie getMovieByName(String movieName) {

    for (Movie currMovie : totalMovie) {
      if (currMovie.getMovieName().equals(movieName)) {
        return currMovie;
      }
    }

    return null;
  }

  List<Movie> getMovieByCity(City city) {
    List<Movie> movie = totalMovieInCity.get(city);
    System.out.println(city + " " + movie.size());
    return totalMovieInCity.get(city);
  }
}