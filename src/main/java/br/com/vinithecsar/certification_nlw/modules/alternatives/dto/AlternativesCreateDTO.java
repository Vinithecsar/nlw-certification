package br.com.vinithecsar.certification_nlw.modules.alternatives.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) para representar o objeto de criação de uma alternativa de uma questão.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlternativesCreateDTO {

    /**
     * Identificador único da questão associada com a alternativa.
     */
    private UUID questionId;

    /**
     * Descrição da alternativa.
     */
    private String description;

    /**
     * Indica se a alternativa está correta.
     */
    private boolean isCorrect;
}
