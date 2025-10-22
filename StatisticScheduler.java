import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatsScheduler {

    private final GenreStatsRepository genreStatsRepository;


    @Scheduled(cron = "0 0 12 * * FRI")
    public void printWeeklyStats() {
        System.out.println(" Weekly Genre Stats:");
        genreStatsRepository.findAll().forEach(stat ->
                System.out.println(stat.getGenre() + " — " + stat.getPlayCount() + " plays")
        );
    }
}
