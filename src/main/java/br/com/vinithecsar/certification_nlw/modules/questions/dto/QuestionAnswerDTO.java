package br.com.vinithecsar.certification_nlw.modules.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) para representar a resposta de uma questão.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerDTO {

    /**
     * Identificador único da questão.
     */
    private UUID questionId;

    /**
     * Identificador único da alternativa selecionada.
     */
    private UUID alternativeId;

    /**
     * Indica se a resposta está correta.
     */
    private boolean isCorrect;
}
