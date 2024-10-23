package br.com.vinithecsar.certification_nlw.modules.students.useCases;

import br.com.vinithecsar.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import br.com.vinithecsar.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para verificar se um estudante já possui uma certificação em uma tecnologia específica.
 */
@Service
public class VerifyIfHasCertificationUseCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    /**
     * Executa a verificação se o estudante tem uma certificação em uma tecnologia específica.
     *
     * @param dto O objeto DTO contendo o e-mail do estudante e a tecnologia.
     * @return true se o estudante já possui a certificação, false caso contrário.
     */
    public boolean execute(VerifyHasCertificationDTO dto) {
        var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
        return !result.isEmpty();
    }
}
