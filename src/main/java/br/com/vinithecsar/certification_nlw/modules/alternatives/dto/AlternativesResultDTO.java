package br.com.vinithecsar.certification_nlw.modules.alternatives.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) para representar as alternativas de uma questão.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlternativesResultDTO {

    /**
     * Identificador único da alternativa.
     */
    private UUID id;

    /**
     * Descrição da alternativa.
     */
    private String description;
}
