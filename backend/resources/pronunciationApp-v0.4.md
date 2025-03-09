# PronunciationApp Backend v0.4

**Reference links**:

- [pronunciationApp model· GitHub](https://github.com/AlbertProfe/pronunciationApp/tree/backend-spring-boot/backend/resources/jpa/model)
- [pronunciationApp-v0.3.md GitHub](https://github.com/AlbertProfe/pronunciationApp/blob/backend-spring-boot/backend/resources/pronunciationApp-v0.3.md)
- [userBorrowBook sanbox repo](https://github.com/AlbertProfe/userBorrowBook)

## One-To-One

```java
@Entity
public class AppUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String aapUserName;
    private int age;
    private String email;
    private String password;
    private int totalScore;
    private boolean isActive;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private GameProgress gameProgress;
}


// ...

@Entity
public class GameProgress {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private int currentScore;

    public enum Stage {
        STAGE_01, STAGE_02, STAGE_03, STAGE_04, STAGE_05
    }

    @Enumerated(EnumType.STRING)
    private Stage currentStage;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPlayedDate;

    private int wordsLearned;

    @OneToOne
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;
}
```

### One-to-One Relationship in JPA: AppUser and GameProgress

**Bidirectional Relationship**

In this example, we have a <mark>bidirectional one-to-one relationship</mark> between `AppUser` and `GameProgress`. Here's a simple explanation:

1. **AppUser Entity:**
   
   java
   
   `@OneToOne(mappedBy = "user", cascade = CascadeType.ALL) private GameProgress gameProgress;`
   
   - This annotation indicates that each `AppUser` is associated with one `GameProgress`.
   
   - `mappedBy = "user"` specifies that `GameProgress` owns the relationship.
   
   - `cascade = CascadeType.ALL` means that operations on `AppUser` will cascade to the associated `GameProgress`.

2. **GameProgress Entity:**
   
   java
   
   `@OneToOne @JoinColumn(name = "user_id") private AppUser appUser;`
   
   - This annotation shows that each `GameProgress` is linked to one `AppUser`.
   
   - `@JoinColumn(name = "user_id")` creates a column in the `GameProgress` table to store the `AppUser`'s ID.

**Bidirectional vs Unidirectional**

Bidirectional (Current Setup):

- Both entities have a reference to each other.

- You can navigate from `AppUser` to `GameProgress` and vice versa.

- Useful when you need to access related data from both sides frequently.

Unidirectional (Alternative):

If we remove the `gameProgress` field from `AppUser`, it becomes unidirectional:

- Only `GameProgress` would have a reference to `AppUser`.

- You could navigate from `GameProgress` to `AppUser`, but not the other way around.

- Simpler, but limits navigation to one direction.

**Key Points**

- In a bidirectional relationship, one side must own the relationship (here, `GameProgress`).

- The non-owning side uses `mappedBy` to indicate the owning side's field name.

- Bidirectional relationships offer more flexibility but require careful management to maintain consistency.

## Many-To-Many

In JPA (Java Persistence API) with Spring Boot, many-to-many relationships between entities can be implemented in **two main ways**:

1. **Using `@ManyToMany` with `@JoinTable`:**
   
   - This approach can be configured as either **unidirectional** or **bidirectional**.
   
   - It relies on a **join table** managed by JPA through the `@JoinTable` annotation.
   
   - Example:
     
     ```java
     @ManyToMany
     @JoinTable(
         name = "student_course",
         joinColumns = @JoinColumn(name = "student_id"),
         inverseJoinColumns = @JoinColumn(name = "course_id")
     )
     private Set<Course> courses;
     ```
   
   - **Use case:** Ideal for simple relationships where the join table does not require additional attributes.

2. **Using an Intermediate Entity with `@OneToMany` and `@ManyToOne`:**
   
   - This approach involves creating an **intermediate entity** to represent the relationship explicitly.
   
   - The intermediate entity acts as an **assignment table** and connects the two main entities using `@OneToMany` and `@ManyToOne`.
   
   - Example:
     
     ```java
     @Entity
     public class StudentCourse {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;
     
         @ManyToOne
         @JoinColumn(name = "student_id")
         private Student student;
     
         @ManyToOne
         @JoinColumn(name = "course_id")
         private Course course;
     
         // Additional attributes (e.g., enrollment date, grade)
         private LocalDate enrolledDate;
     }
     ```
   
   - **Use case:** Necessary when the relationship requires additional attributes or when more control over the join table is needed.
