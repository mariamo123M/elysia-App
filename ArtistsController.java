@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ARTIST')")
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping("/albums")
    public Album createAlbum(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestParam String name,
                             @RequestParam Genre genre) {
        Long artistId = getUserId(userDetails);
        return artistService.createAlbum(artistId, name, genre);
    }

    @PostMapping("/music")
    public Music uploadMusic(@RequestParam Long albumId,
                             @RequestParam String name,
                             @RequestParam Genre genre,
                             @RequestParam String author) {
        return artistService.uploadMusic(albumId, name, genre, author);
    }

    private Long getUserId(UserDetails userDetails) {
        // implement logic to extract userId from email or token
        return 1L; // placeholder
    }
}