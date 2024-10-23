package br.com.vinithecsar.certification_nlw.modules.students.dto;

import br.com.vinithecsar.certification_nlw.modules.questions.dto.QuestionAnswerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO que representa as respostas de um estudante para uma certificação.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCertificationAnswerDTO {

    /**
     * O email do estudante que está respondendo a certificação.
     */
    private String email;

    /**
     * A tecnologia relacionada à certificação.
     */
    private String technology;

    /**
     * A lista de respostas para as perguntas da certificação.
     */
    private List<QuestionAnswerDTO> questionAnswers;
}
