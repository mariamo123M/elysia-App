@Service
@RequiredArgsConstructor
public class ArtistService {
    private final AlbumRepository albumRepository;
    private final MusicRepository musicRepository;
    private final UserRepository userRepository;

    public Album createAlbum(Long artistId, String name, Genre genre) {
        User artist = userRepository.findById(artistId).orElseThrow();
        if (artist.getRole() != Role.ARTIST) {
            throw new RuntimeException("Only artists can create albums");
        }

        Album album = new Album();
        album.setName(name);
        album.setGenre(genre);
        album.setArtist(artist);
        return albumRepository.save(album);
    }

    public Music uploadMusic(Long albumId, String name, Genre genre, String author) {
        Album album = albumRepository.findById(albumId).orElseThrow();

        Music music = new Music();
        music.setName(name);
        music.setGenre(genre);
        music.setAuthor(author);
        music.setAlbum(album);
        return musicRepository.save(music);
    }
}