package br.com.vinithecsar.certification_nlw.modules.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para representar o objeto de criação de uma questão, sem suas alternativas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionCreateDTO {

    /**
     * Tecnologia relacionada à questão.
     */
    private String technology;

    /**
     * Descrição da questão.
     */
    private String description;
}
