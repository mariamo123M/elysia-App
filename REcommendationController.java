@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/playlists")
    public Map<Genre, List<Music>> getSuggestedPlaylists(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserId(userDetails);
        return recommendationService.generateSuggestedPlaylists(userId);
    }

    private Long getUserId(UserDetails userDetails) {
        // implement logic to extract userId from email or token
        return 1L; // placeholder
    }
}