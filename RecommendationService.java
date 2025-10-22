@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicPlay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Music music;

    private int count; რამდენჯერ მოისმინა მომხმარებელმა
}
public interface MusicPlayRepository extends JpaRepository<MusicPlay, Long> {
    List<MusicPlay> findByUserId(Long userId);
}
@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final MusicPlayRepository musicPlayRepository;
    private final MusicRepository musicRepository;

    public Map<Genre, List<Music>> generateSuggestedPlaylists(Long userId) {

        // Step 1: User's listening history
        List<MusicPlay> userPlays = musicPlayRepository.findByUserId(userId);

        // Step 2: Count plays per genre
        Map<Genre, Integer> genreCount = new HashMap<>();
        for (MusicPlay mp : userPlays) {
            Genre genre = mp.getMusic().getGenre();
            genreCount.put(genre, genreCount.getOrDefault(genre, 0) + mp.getCount());
        }

        // Step 3: Top 3 genres
        List<Genre> topGenres = genreCount.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();

        // Step 4: Generate playlists per genre
        Map<Genre, List<Music>> suggestedPlaylists = new HashMap<>();
        for (Genre genre : topGenres) {
            List<Music> tracks = musicRepository.findByGenre(genre);
            suggestedPlaylists.put(genre, tracks);
        }

        return suggestedPlaylists;
    }
}
