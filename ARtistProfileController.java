@RestController
@RequestMapping("/artist/profile")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ARTIST')")
public class ArtistProfileController {

    private final RecommendationService recommendationService;

    @GetMapping("/{artistId}/similar")
    public ResponseEntity<List<SimilarArtistDTO>> getSimilarArtists(@PathVariable Long artistId) {
        List<User> artists = recommendationService.getSimilarArtists(artistId);
        List<SimilarArtistDTO> dtos = artists.stream()
                .map(a -> new SimilarArtistDTO(a.getId(), a.getEmail(), a.getGenre()))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    public record SimilarArtistDTO(Long id, String email, Genre genre) {}
}

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
