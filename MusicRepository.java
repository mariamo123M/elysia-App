public interface MusicRepository extends JpaRepository<Music, Long> {
    @Query("SELECT m FROM Music m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(m.author) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Music> searchByKeyword(@Param("keyword") String keyword);
}