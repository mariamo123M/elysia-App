public interface
AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtistId(Long artistId);
}

public interface MusicRepository extends JpaRepository<Music, Long> {
    List<Music> findByAlbumId(Long albumId);
}