@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final MusicRepository musicRepository;

    @Scheduled(cron = "0 0 12 * * FRI")
    public void printWeeklyStats() {
        System.out.println("🎧 Weekly Music Stats:");
        List<Music> allMusic = musicRepository.findAll();
        for (Music music : allMusic) {
            System.out.printf("• %s by %s — %d listens%n",
                    music.getName(), music.getAuthor(), music.getPlayCount());
        }
    }
}