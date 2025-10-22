@RestController
@RequestMapping("/admin/music")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminMusicController {
    private final MusicRepository musicRepository;

    @GetMapping
    public List<Music> getAllMusic() {
        return musicRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMusic(@PathVariable Long id, @RequestBody Music updated) {
        Music music = musicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Music not found"));
        music.setName(updated.getName());
        music.setGenre(updated.getGenre());
        music.setAuthor(updated.getAuthor());
        musicRepository.save(music);
        return ResponseEntity.ok("Music updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMusic(@PathVariable Long id) {
        musicRepository.deleteById(id);
        return ResponseEntity.ok("Music deleted");
    }
}