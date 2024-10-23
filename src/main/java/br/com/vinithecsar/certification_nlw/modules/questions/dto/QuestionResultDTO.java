package br.com.vinithecsar.certification_nlw.modules.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import br.com.vinithecsar.certification_nlw.modules.alternatives.dto.AlternativesResultDTO;

/**
 * Data Transfer Object (DTO) para representar o resultado de uma questão,
 * incluindo suas alternativas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResultDTO {

    /**
     * Identificador único da questão.
     */
    private UUID id;

    /**
     * Tecnologia relacionada à questão.
     */
    private String technology;

    /**
     * Descrição da questão.
     */
    private String description;

    /**
     * Lista de alternativas associadas à questão.
     */
    private List<AlternativesResultDTO> alternatives;
}
