package net.codeJava.atozEntity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "userSession")
public class UserSessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId",nullable = false)
    private Long usersId;

    @Column(name = "loginTime",nullable = false)
    private Date loginTime;

    @Column(name = "logoutTime")
    private Date logoutTime;

    private boolean isLogin;
}
