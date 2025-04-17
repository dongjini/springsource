package com.example.jpa.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EntityListeners(value = AuditingEntityListener.class)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "studenttbl")
@Entity // == db table
public class Student {

    @Id
    @SequenceGenerator(name = "student_seq_gen", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq_gen")
    // @GeneratedValue // create sequence studenttbl_seq start with 1 increment by
    // 50
    private Long id; // id number(19,0) not null / primary key (id)

    // @Column(name = "sname", length = 100, nullable = false, unique = true)
    // @Column(name = "sname", columnDefinition = "varchar2(20) not null unique")
    @Column(length = 20, nullable = false)
    private String name; // name varchar2(255 char)

    // @Column(columnDefinition = "number(8, 0)")
    // private int grade; // grade number(10,0) int 는 10자리 long은 19 자리가 기본

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Grade grade;

    @Column(columnDefinition = "varchar2(1) CONSTRAINT chk_gender CHECK (gender IN ('M', 'F')) ")
    private String gender;

    // timestamp(6)
    @CreationTimestamp // insert
    private LocalDateTime cDateTime; // C_DATE_TIME

    @UpdateTimestamp // insert + 데이터 수정 할 때마다 시간이 자동으로 갱신
    private LocalDateTime uDateTime; // U_DATE_TIME

    @CreatedDate
    private LocalDateTime cDateTime2;
    @LastModifiedDate
    private LocalDateTime uDateTime2;

    // enum 정의
    // 상수 집합(상수 모아둔 것 선언 할 때 사용)
    public enum Grade {
        FRESHMAN, SOPHOMORE, JUNIOR, SENIOR
    }
}
