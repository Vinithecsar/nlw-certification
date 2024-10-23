package br.com.vinithecsar.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
 * Entidade que representa as certificações dos estudantes.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certifications")
@Builder
public class CertificationStudentEntity {

    /**
     * Identificador único da certificação.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Tecnologia relacionada à certificação.
     */
    @Column(length = 100)
    private String technology;

    /**
     * Nota obtida pelo estudante na certificação.
     */
    @Column(length = 10)
    private int grade;

    /**
     * Identificador do estudante associado a esta certificação.
     */
    @Column(name = "student_id")
    private UUID studentId;

    /**
     * Referência à entidade do estudante associada a esta certificação.
     */
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    /**
     * Lista de respostas de certificação associadas a esta certificação.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_certification_id", insertable = false, updatable = false)
    @JsonManagedReference
    List<AnswersCertificationsEntity> answersCertificationsEntities;

    /**
     * Data e hora em que a certificação foi criada.
     * Esse campo é automaticamente preenchido no momento da inserção.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;
}
