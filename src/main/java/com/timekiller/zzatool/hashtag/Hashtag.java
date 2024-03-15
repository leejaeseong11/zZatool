@Entity
@Table(name = "hashtag")
public class Member{
    @id
    private String tag_content;

    @ManyToMany
    @JoinColumn(name="test_id")
    @NotNull
    private List<Test> test;
}