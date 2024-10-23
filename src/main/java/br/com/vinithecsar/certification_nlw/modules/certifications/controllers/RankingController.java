package br.com.vinithecsar.certification_nlw.modules.certifications.controllers;

import br.com.vinithecsar.certification_nlw.modules.certifications.useCases.Top10RankingUseCase;
import br.com.vinithecsar.certification_nlw.modules.students.entities.CertificationStudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador responsável por gerar o ranking de top 10 dos usuários que responderam as questões.
 */
@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private Top10RankingUseCase top10RankingUseCase;

    /**
     * Retorna uma lista com os usuários no top 10 de questões respondidas.
     *
     * @return Uma lista de estudantes (até 10) com as melhores notas do tipo {@link CertificationStudentEntity}.
     */
    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10() {
        return top10RankingUseCase.execute();
    }
}
