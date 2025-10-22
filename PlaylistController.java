@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping
    public List<Playlist> getMyPlaylists(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserId(userDetails);
        return playlistService.getUserPlaylists(userId);
    }

    @PostMapping
    public Playlist create(@AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam String name) {
        Long userId = getUserId(userDetails);
        return playlistService.createPlaylist(userId, name);
    }

    @PutMapping("/{id}")
    public Playlist update(@PathVariable Long id,
                           @AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam String name) {
        Long userId = getUserId(userDetails);
        return playlistService.updatePlaylist(id, userId, name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserId(userDetails);
        playlistService.deletePlaylist(id, userId);
        return ResponseEntity.ok("Deleted");
    }

    private Long getUserId(UserDetails userDetails) {
        // implement logic to extract userId from email or token
        return 1L; // placeholder
    }
}