package br.com.vinithecsar.certification_nlw.modules.alternatives.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import br.com.vinithecsar.certification_nlw.modules.questions.entities.QuestionEntity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade que representa uma alternativa de uma questão no banco de dados.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "alternatives")
public class AlternativesEntity {

    /**
     * Identificador único da alternativa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Descrição textual da alternativa.
     */
    private String description;

    /**
     * Indica se a alternativa está correta.
     */
    private boolean isCorrect;

    /**
     * Questão à qual esta alternativa pertence.
     */
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    /**
     * Data e hora de criação da alternativa.
     * Esse campo é automaticamente preenchido no momento da inserção.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;
}
