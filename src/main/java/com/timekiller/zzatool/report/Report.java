@Entity
@Table(name = "report")
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq_generator")
    private Long id;

    @Column(nullable = false)
    private Long quiz_id;

    @Column(nullable = false)
    private String report_reason;

    @Column(nullable = false)
    private Timestamp report_date;
}