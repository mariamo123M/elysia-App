public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
@Query("SELECT u FROM User u WHERE u.role = 'ARTIST' AND u.genre = :genre AND u.id <> :artistId")
List<User> findSimilarArtists(@Param("genre") Genre genre, @Param("artistId") Long artistId);