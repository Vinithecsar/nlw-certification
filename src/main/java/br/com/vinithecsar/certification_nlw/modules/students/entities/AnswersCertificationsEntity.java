package br.com.vinithecsar.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade que representa as respostas dadas pelos estudantes em relação às certificações.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answers_certifications_students")
@Builder
public class AnswersCertificationsEntity {

    /**
     * Identificador único da resposta da certificação.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Identificador da certificação associada a esta resposta.
     */
    @Column(name = "certification_id")
    private UUID certificationId;

    /**
     * Referência à entidade de certificação do estudante associada a esta resposta.
     */
    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    @JsonBackReference
    private CertificationStudentEntity certificationStudentEntity;

    /**
     * Identificador do estudante que deu a resposta.
     */
    @Column(name = "student_id")
    private UUID studentId;

    /**
     * Referência à entidade do estudante associada a esta resposta.
     */
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    /**
     * Identificador da pergunta associada a esta resposta.
     */
    @Column(name = "question_id")
    private UUID questionId;

    /**
     * Identificador da alternativa escolhida como resposta.
     */
    @Column(name = "answer_id")
    private UUID answerId;

    /**
     * Indica se a resposta dada está correta.
     */
    @Column(name = "is_correct")
    private boolean isCorrect;

    /**
     * Data e hora em que a resposta foi criada.
     * Esse campo é automaticamente preenchido no momento da inserção.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;
}
