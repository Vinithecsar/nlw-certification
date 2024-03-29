package br.com.vinithecsar.certification_nlw.modules.questions.controllers;

import br.com.vinithecsar.certification_nlw.modules.questions.dto.AlternativesResultDTO;
import br.com.vinithecsar.certification_nlw.modules.questions.dto.QuestionResultDTO;
import br.com.vinithecsar.certification_nlw.modules.questions.entities.AlternativesEntity;
import br.com.vinithecsar.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.vinithecsar.certification_nlw.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionRepository.findByTechnology(technology);

        var toMap = result.stream().map(question -> mapQuestionToDto(question)).collect(Collectors.toList());
        return toMap;
    }

    static QuestionResultDTO mapQuestionToDto(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder()
                .id(question.getId())
                .technology(question.getTechnology())
                .description(question.getDescription()).build();

        List<AlternativesResultDTO> alternativesResultDTOS = question.getAlternatives().stream().map(alternativesEntity -> mapAlternativaDTO(alternativesEntity)).collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOS);
        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativaDTO(AlternativesEntity alternativesEntity) {
        return AlternativesResultDTO.builder()
                .id(alternativesEntity.getId())
                .description(alternativesEntity.getDescription()).build();
    }
}
