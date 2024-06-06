package com.dev.explore_blog.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = ("category"), cascade = CascadeType.ALL, orphanRemoval = true)
    List<Post>  posts;

}
