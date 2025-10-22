import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final MusicRepository musicRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            //  Create an artist
            User artist = new User(null, "artist@example.com", "password", Role.ARTIST, Status.ACTIVE, null);
            userRepository.save(artist);

            //  Create a listener
            User listener = new User(null, "listener@example.com", "password", Role.LISTENER, Status.ACTIVE, null);
            userRepository.save(listener);

            //  Create Albums for the artist
            Album album1 = new Album(null, "Rocking Hits", Genre.ROCK, artist);
            Album album2 = new Album(null, "Rap Vibes", Genre.RAP, artist);
            albumRepository.save(album1);
            albumRepository.save(album2);

            //  Add Music to albums
            Music song1 = new Music(null, "Rock Anthem", Genre.ROCK, "Artist Name", album1, 0);
            Music song2 = new Music(null, "Metal Storm", Genre.METAL, "Artist Name", album1, 0);
            Music song3 = new Music(null, "Rap Flow", Genre.RAP, "Artist Name", album2, 0);
            musicRepository.save(song1);
            musicRepository.save(song2);
            musicRepository.save(song3);

            System.out.println(" Seed data initialized successfully!");
        };
    }
}
