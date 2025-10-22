@RestController
@RequestMapping("/admin/albums")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminAlbumController {
    private final AlbumRepository albumRepository;

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAlbum(@PathVariable Long id, @RequestBody Album updated) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));
        album.setName(updated.getName());
        album.setGenre(updated.getGenre());
        albumRepository.save(album);
        return ResponseEntity.ok("Album updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id) {
        albumRepository.deleteById(id);
        return ResponseEntity.ok("Album deleted");
    }
}