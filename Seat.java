public class Seat {

  int seatId;
  int row;
  SeatCategory seatCategory;
  int prize;

  public void setSeatId(int seatId) {
    this.seatId = seatId;
  }

  public int getSeatId() {
    return seatId;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getRow() {
    return row;
  }

  public void setSeatCategory(SeatCategory seatCategory) {
    this.seatCategory = seatCategory;
  }

  public SeatCategory getSeatCategory() {
    return seatCategory;
  }

  public void setPrize(int prize) {
    this.prize = prize;
  }

  public int getPrize() {
    return prize;
  }
}