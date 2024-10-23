package br.com.vinithecsar.certification_nlw.modules.questions.controllers;

import br.com.vinithecsar.certification_nlw.modules.alternatives.dto.AlternativesResultDTO;
import br.com.vinithecsar.certification_nlw.modules.alternatives.entities.AlternativesEntity;
import br.com.vinithecsar.certification_nlw.modules.questions.dto.QuestionCreateDTO;
import br.com.vinithecsar.certification_nlw.modules.questions.dto.QuestionResultDTO;
import br.com.vinithecsar.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.vinithecsar.certification_nlw.modules.questions.repositories.QuestionRepository;
import br.com.vinithecsar.certification_nlw.modules.questions.useCases.CreateQuestionUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador responsável por gerenciar as requisições relacionadas às questões.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CreateQuestionUseCase createQuestionUseCase;

    /**
     * Cria uma questão com os parâmetros passados no body da requisição.
     *
     * @param dto objeto que contém as informações necessárias para criar a questão.
     * @return a questão criada, do tipo {@link QuestionResultDTO}
     */
    @PostMapping()
    public QuestionResultDTO create(@RequestBody QuestionCreateDTO dto) {
        return this.createQuestionUseCase.execute(dto);
    }

    /**
     * Busca questões relacionadas a uma determinada tecnologia.
     *
     * @param technology O nome da tecnologia a ser buscada.
     * @return Uma lista de objetos {@link QuestionResultDTO} contendo as questões e suas alternativas.
     */
    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionRepository.findByTechnology(technology);

        var toMap = result.stream().map(question -> mapQuestionToDto(question)).collect(Collectors.toList());
        return toMap;
    }

    /**
     * Mapeia uma entidade de questão para o DTO correspondente.
     *
     * @param question A entidade {@link QuestionEntity} a ser mapeada.
     * @return Um objeto {@link QuestionResultDTO} correspondente à entidade.
     */
    static QuestionResultDTO mapQuestionToDto(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder()
                .id(question.getId())
                .technology(question.getTechnology())
                .description(question.getDescription()).build();

        List<AlternativesResultDTO> alternativesResultDTOS = question.getAlternatives().stream()
                .map(alternativesEntity -> mapAlternativaDTO(alternativesEntity))
                .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOS);
        return questionResultDTO;
    }

    /**
     * Mapeia uma entidade de alternativa para o DTO correspondente.
     *
     * @param alternativesEntity A entidade {@link AlternativesEntity} a ser mapeada.
     * @return Um objeto {@link AlternativesResultDTO} correspondente à entidade.
     */
    static AlternativesResultDTO mapAlternativaDTO(AlternativesEntity alternativesEntity) {
        return AlternativesResultDTO.builder()
                .id(alternativesEntity.getId())
                .description(alternativesEntity.getDescription()).build();
    }

}
