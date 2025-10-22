@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
public class MusicController {
    private final MusicRepository musicRepository;

    @GetMapping("/search")
    public List<Music> search(@RequestParam String keyword) {
        return musicRepository.searchByKeyword(keyword);
    }

    @GetMapping("/{id}")
    public Music getMusic(@PathVariable Long id) {
        Music music = musicRepository.findById(id).orElseThrow();
        music.setPlayCount(music.getPlayCount() + 1);
        return musicRepository.save(music);
    }
}
@GetMapping("/{id}")
public Music getMusic(@PathVariable Long id) {
    Music music = musicRepository.findById(id).orElseThrow();
    music.setPlayCount(music.getPlayCount() + 1);
    return musicRepository.save(music);
}