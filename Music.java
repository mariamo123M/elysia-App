@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String author;

    private int playCount = 0;

    @ManyToOne
    private Album album;
}