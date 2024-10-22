import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {

  MovieController movieController;
  TheatreController theatreController;

  BookMyShow() {
    movieController = new MovieController();
    theatreController = new TheatreController();
  }

  public static void main(String[] args) {
    BookMyShow bookMyShow = new BookMyShow();

    bookMyShow.intialize();

    bookMyShow.createBooking(City.Delhi, "RRR");
  }

  private void createBooking(City city, String movieName) {

    // Fetch All The Movie:
    List<Movie> totalMovie = movieController.getMovieByCity(city);

    // Select The Movie:
    Movie interestedMovie = null;
    for (Movie currMovie : totalMovie) {
      if (currMovie.getMovieName().equals(movieName)) {
        interestedMovie = currMovie;
        break;
      }
    }

    // Get All Shows in the given city:
    Map<Theatre, List<Show>> showsTheatreWise = theatreController.getAllShow(interestedMovie, city);

    // Select the particular shows user interested in:
    Map.Entry<Theatre, List<Show>> entry = showsTheatreWise.entrySet().iterator().next();
    List<Show> runningShows = entry.getValue();

    // Check if runningShows is empty
    if (runningShows == null || runningShows.isEmpty()) {
      System.out.println("No running shows available in this theatre.");
    } else {
      System.out.println("Running Shows: " + runningShows.size());
    }

    Show interestedShow = runningShows.get(0);

    // Select The Seat:
    int bookingSeatId = 50;

    // Book Seat:
    bookSeat(interestedShow, bookingSeatId);
  }

  private synchronized void bookSeat(Show interestedShow, int bookingSeatId) {

    List<Integer> bookedSeatIds = interestedShow.getBookedSeatIds();
    if (!bookedSeatIds.contains(bookingSeatId)) {
      bookedSeatIds.add(bookingSeatId);

      // Payment:
      Booking booking = new Booking();
      List<Seat> myBookedSeats = new ArrayList<>();
      for (Seat seat : interestedShow.getScreen().getSeats()) {
        if (seat.getSeatId() == bookingSeatId) {
          myBookedSeats.add(seat);
          // break;
        }
      }

      booking.setBookedSeats(myBookedSeats);
      booking.setShow(interestedShow);

    } else {
      // throw exception
      System.out.println("seat already booked, try again");
      return;
    }

    System.out.println("BOOKING SUCCESSFUL");
  }

  private void intialize() {

    // Create Movie:
    createMovie();

    // Create Theatre:
    createTheatre();
  }

  private void createTheatre() {

    Movie rrr = movieController.getMovieByName("RRR");
    Movie streeTwo = movieController.getMovieByName("Stree Two");

    // INOX - First Theatre
    List<Screen> screen = createScreen();

    Theatre inox = new Theatre();
    inox.setTheatreId(1);
    inox.setAddress("Delhi se hai bc!");
    inox.setCity(City.Delhi);
    inox.setScreen(screen);

    List<Show> show = new ArrayList<>();
    Show inoxMorningShow = createShow(1, rrr, inox.getScreen().get(0), 8);
    Show inoxEveningShow = createShow(2, streeTwo, inox.getScreen().get(0), 14);
    show.add(inoxMorningShow);
    show.add(inoxEveningShow);
    inox.setShows(show);

    // PVR - Second Theatre
    Theatre pvr = new Theatre();
    pvr.setTheatreId(2);
    pvr.setAddress("Bangalore hai ghar mera!");
    pvr.setCity(City.Bangalore);
    pvr.setScreen(screen);

    List<Show> pvrShow = new ArrayList<>();
    Show pvrMorningShow = createShow(3, rrr, inox.getScreen().get(0), 10);
    Show pvrEveningShow = createShow(4, streeTwo, inox.getScreen().get(0), 16);
    pvrShow.add(pvrMorningShow);
    pvrShow.add(pvrEveningShow);
    inox.setShows(pvrShow);

    theatreController.addTheatres(City.Delhi, inox);
    theatreController.addTheatres(City.Bangalore, pvr);

  }

  private Show createShow(int showId, Movie movie, Screen screen, int showStartTime) {
    Show show = new Show();

    show.setShowId(showId);
    show.setMovie(movie);
    show.setScreen(screen);
    show.setShowStartTime(showStartTime);

    return show;
  }

  private List<Screen> createScreen() {
    List<Screen> totalScreen = new ArrayList<>();

    Screen firstScreen = new Screen();
    firstScreen.setScreenId(1);
    firstScreen.setSeats(createSeat());

    totalScreen.add(firstScreen);
    return totalScreen;
  }

  private List<Seat> createSeat() {
    List<Seat> totalSeat = new ArrayList<>();

    // SILVER:
    for (int i = 0; i < 40; i++) {
      Seat seat = new Seat();
      seat.setSeatId(i);
      seat.setRow(1);
      seat.setPrize(300);
      seat.setSeatCategory(SeatCategory.SILVER);

      totalSeat.add(seat);
    }

    // Gold:
    for (int i = 40; i < 80; i++) {
      Seat seat = new Seat();
      seat.setSeatId(i);
      seat.setRow(2);
      seat.setPrize(500);
      seat.setSeatCategory(SeatCategory.GOLD);

      totalSeat.add(seat);
    }

    // Platinum:
    for (int i = 80; i < 100; i++) {
      Seat seat = new Seat();
      seat.setSeatId(i);
      seat.setRow(3);
      seat.setPrize(1000);
      seat.setSeatCategory(SeatCategory.PLATINUM);

      totalSeat.add(seat);
    }

    return totalSeat;
  }

  private void createMovie() {

    Movie rrr = new Movie();
    rrr.setMovieId(1);
    rrr.setMovieName("RRR");
    rrr.setMovieDuration(180);

    Movie streeTwo = new Movie();
    streeTwo.setMovieId(2);
    streeTwo.setMovieName("Stree Two");
    streeTwo.setMovieDuration(120);

    movieController.addMovie(City.Delhi, streeTwo);
    movieController.addMovie(City.Delhi, rrr);

    movieController.addMovie(City.Bangalore, streeTwo);
    movieController.addMovie(City.Bangalore, rrr);

  }
}