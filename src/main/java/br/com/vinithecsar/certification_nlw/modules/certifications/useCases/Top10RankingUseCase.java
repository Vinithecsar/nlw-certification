package br.com.vinithecsar.certification_nlw.modules.certifications.useCases;

import br.com.vinithecsar.certification_nlw.modules.students.entities.CertificationStudentEntity;
import br.com.vinithecsar.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe @Service responsável por obter o ranking dos 10 melhores estudantes
 * baseado na nota das certificações.
 */
@Service
public class Top10RankingUseCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    /**
     * Método que retorna a lista dos 10 estudantes com as maiores notas
     * em suas certificações, ordenados de forma decrescente.
     *
     * @return Uma lista de estudantes (até 10) com as melhores notas do tipo {@link CertificationStudentEntity}.
     */
    public List<CertificationStudentEntity> execute() {
        return this.certificationStudentRepository.findTop10ByOrderByGradeDesc();
    }
}
