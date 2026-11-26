import java.time.LocalDate;

/** One day = date + entries; also a node of the doubly linked list of days. */
public class Day {
    LocalDate date;
    EntryList entries = new EntryList();
    Day previous;
    Day next;

    Day(LocalDate date) {
        this.date = date;
    }
}