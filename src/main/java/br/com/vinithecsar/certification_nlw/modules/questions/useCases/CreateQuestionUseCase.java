package br.com.vinithecsar.certification_nlw.modules.questions.useCases;

import br.com.vinithecsar.certification_nlw.modules.questions.dto.QuestionCreateDTO;
import br.com.vinithecsar.certification_nlw.modules.questions.dto.QuestionResultDTO;
import br.com.vinithecsar.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.vinithecsar.certification_nlw.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço responsável pela criação de uma nova questão.
 * Esta classe contém a lógica de negócio necessária para processar a
 * criação de uma questão a partir de um DTO de entrada.
 */
@Service
public class CreateQuestionUseCase {

    @Autowired
    private QuestionRepository questionRepository;

    /**
     * Executa o caso de uso para criar uma nova questão.
     *
     * @param dto o objeto {@link QuestionCreateDTO} contendo os dados da questão a ser criada
     * @return um objeto {@link QuestionResultDTO} representando a questão criada, incluindo seu identificador e dados associados
     */
    public QuestionResultDTO execute(QuestionCreateDTO dto) {
      QuestionEntity questionEntity = convertToEntity(dto);
      QuestionEntity savedQuestion = questionRepository.save(questionEntity);
      return convertToResultDTO(savedQuestion);
    }

    /**
     * Converte um objeto {@link QuestionCreateDTO} em um objeto {@link QuestionEntity}.
     *
     * @param dto o objeto {@link QuestionCreateDTO} a ser convertido
     * @return um objeto {@link QuestionEntity} representando a questão
     */
    private QuestionEntity convertToEntity(QuestionCreateDTO dto) {
        QuestionEntity entity = new QuestionEntity();
        entity.setTechnology(dto.getTechnology());
        entity.setDescription(dto.getDescription());

        return entity;
    }

    /**
     * Converte um objeto {@link QuestionEntity} em um objeto {@link QuestionResultDTO}.
     *
     * @param entity o objeto {@link QuestionEntity} a ser convertido
     * @return um objeto {@link QuestionResultDTO} representando a questão
     */
    private QuestionResultDTO convertToResultDTO(QuestionEntity entity) {
        QuestionResultDTO dto = new QuestionResultDTO();
        dto.setId(entity.getId());
        dto.setTechnology(entity.getTechnology());
        dto.setDescription(entity.getDescription());

        return dto;
    }
}
