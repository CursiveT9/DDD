import ActiveRecord.Driver;
import ActiveRecord.DriverRepository;
import TransactionScript.ScheduleTransactionScript;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DriverRepository repository = new DriverRepository();
        Driver driver = new Driver(1, "Иван Петров", true);
        repository.save(driver);

        ScheduleTransactionScript transactionScript = new ScheduleTransactionScript(repository);

        transactionScript.addWorkShiftToDriver(1, LocalDateTime.of(2024, 4, 1, 2, 0), LocalDateTime.of(2024, 4, 1, 4, 0));
        transactionScript.addWorkShiftToDriver(1, LocalDateTime.of(2024, 4, 1, 6, 0), LocalDateTime.of(2024, 4, 2, 18, 0));

        List<?> shifts = transactionScript.getShiftsForDriverOnDate(1, LocalDateTime.of(2024, 4, 1, 0, 0));
        System.out.println("Смены на 2024-04-01: " + shifts);

        boolean available = transactionScript.checkDriverAvailability(1, LocalDateTime.of(2024, 4, 1, 12, 0));
        System.out.println("Доступность водителя в 12:00: " + available);

        available = transactionScript.checkDriverAvailability(1, LocalDateTime.of(2024, 4, 1, 5, 0));
        System.out.println("Доступность водителя в 05:00: " + available);
    }
}
