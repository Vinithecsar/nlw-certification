package br.com.vinithecsar.certification_nlw.modules.questions.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import br.com.vinithecsar.certification_nlw.modules.alternatives.entities.AlternativesEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Entidade que representa uma questão no banco de dados.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "questions")
public class QuestionEntity {

    /**
     * Identificador único da questão.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Tecnologia associada à questão.
     */
    @Column(length = 50)
    private String technology;

    /**
     * Descrição ou enunciado da questão.
     */
    private String description;

    /**
     * Lista de alternativas da questão.
     * A relação é mapeada com base no campo 'id' da entidade AlternativesEntity.
     */
    @OneToMany
    @JoinColumn(name = "question_id")
    private List<AlternativesEntity> alternatives;

    /**
     * Data e hora de criação da questão.
     * Esse campo é automaticamente preenchido no momento da inserção.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;
}
