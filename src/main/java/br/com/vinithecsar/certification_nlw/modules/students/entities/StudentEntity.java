package br.com.vinithecsar.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Entidade que representa um estudante.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "students")
@Builder
public class StudentEntity {

    /**
     * Identificador único do estudante.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Endereço de e-mail do estudante, deve ser único e não pode ser nulo.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Lista de certificações associadas a este estudante.
     */
    @OneToMany(mappedBy = "studentEntity")
    @JsonBackReference
    private List<CertificationStudentEntity> certificationStudentEntity;

    /**
     * Data e hora em que o estudante foi criado.
     * Esse campo é automaticamente preenchido no momento da inserção.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;
}
