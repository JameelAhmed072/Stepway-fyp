//package com.example.Stepway.Domain;
//
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
//
//
//@Entity
//public class Certification {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    private Date dateEarned;
//
//    @OneToMany
//    @JoinColumn(name = "userId", referencedColumnName = "id")
//    private User userId;
//}
