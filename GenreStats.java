@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private int playCount;
}