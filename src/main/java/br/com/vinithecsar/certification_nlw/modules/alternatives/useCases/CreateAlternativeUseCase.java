package br.com.vinithecsar.certification_nlw.modules.alternatives.useCases;

import br.com.vinithecsar.certification_nlw.modules.alternatives.dto.AlternativesCreateDTO;
import br.com.vinithecsar.certification_nlw.modules.alternatives.dto.AlternativesResultDTO;
import br.com.vinithecsar.certification_nlw.modules.alternatives.entities.AlternativesEntity;
import br.com.vinithecsar.certification_nlw.modules.alternatives.repositories.AlternativesRepository;
import br.com.vinithecsar.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.vinithecsar.certification_nlw.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço responsável pela criação de alternativas.
 * Esta classe contém a lógica para receber dados de criação de alternativas,
 * convertê-los para a entidade correspondente e salvá-los no repositório.
 */
@Service
public class CreateAlternativeUseCase {

    @Autowired
    private AlternativesRepository alternativesRepository;

    @Autowired
    private QuestionRepository questionRepository;

    /**
     * Executa a criação de uma nova alternativa.
     *
     * @param dto objeto contendo os dados da alternativa a ser criada.
     * @return um objeto {@link AlternativesResultDTO} contendo os dados da alternativa criada.
     * @throws Exception se ocorrer um erro durante a criação da alternativa.
     */
    public AlternativesResultDTO execute(AlternativesCreateDTO dto) throws Exception {
        AlternativesEntity alternativeEntity = convertToEntity(dto);
        AlternativesEntity savedAlternative = alternativesRepository.save(alternativeEntity);
        return convertToResultDTO(savedAlternative);
    }

    /**
     * Converte um objeto {@link AlternativesCreateDTO} para uma entidade {@link AlternativesEntity}.
     *
     * @param dto objeto contendo os dados da alternativa a ser convertida.
     * @return uma entidade {@link AlternativesEntity} correspondente.
     * @throws Exception se a questão associada não for encontrada.
     */
    private AlternativesEntity convertToEntity(AlternativesCreateDTO dto) throws Exception {
        QuestionEntity question = questionRepository.findById(dto.getQuestionId())
                .orElseThrow(() -> new Exception("Questão não encontrada"));

        AlternativesEntity entity = new AlternativesEntity();
        entity.setDescription(dto.getDescription());
        entity.setCorrect(dto.isCorrect());
        entity.setQuestion(question);

        return entity;
    }

    /**
     * Converte uma entidade {@link AlternativesEntity} para um objeto {@link AlternativesResultDTO}.
     *
     * @param entity a entidade a ser convertida.
     * @return um objeto {@link AlternativesResultDTO} correspondente à entidade.
     */
    private AlternativesResultDTO convertToResultDTO(AlternativesEntity entity) {
        AlternativesResultDTO dto = new AlternativesResultDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
