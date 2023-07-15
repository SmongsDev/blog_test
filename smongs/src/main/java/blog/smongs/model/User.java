package blog.smongs.model;

import java.sql.Timestamp;

import javax.persistence.*;

// import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// @DynamicInsert // insert시에 null인 필드는 제외 
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // @ColumnDefault("'user'")
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum 써야 좋음

    private String oauth; // kakao, google 로그인

    @CreationTimestamp // 시간이 자동 입력
    private Timestamp createDate;
}
