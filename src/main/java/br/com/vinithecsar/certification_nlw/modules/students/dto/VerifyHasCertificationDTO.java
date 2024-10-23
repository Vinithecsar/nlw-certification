package br.com.vinithecsar.certification_nlw.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa os dados necessários para verificar se um estudante possui uma certificação.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyHasCertificationDTO {

    /**
     * O email do estudante que se deseja verificar a certificação.
     */
    private String email;

    /**
     * A tecnologia relacionada à certificação que se deseja verificar.
     */
    private String technology;
}
