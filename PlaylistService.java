@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    public List<Playlist> getUserPlaylists(Long userId) {
        return playlistRepository.findByUserId(userId);
    }

    public Playlist createPlaylist(Long userId, String name) {
        User user = userRepository.findById(userId).orElseThrow();
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setUser(user);
        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(Long playlistId, Long userId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow();
        if (!playlist.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can't delete others' playlists");
        }
        playlistRepository.delete(playlist);
    }

    public Playlist updatePlaylist(Long playlistId, Long userId, String newName) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow();
        if (!playlist.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can't update others' playlists");
        }
        playlist.setName(newName);
        return playlistRepository.save(playlist);
    }
}