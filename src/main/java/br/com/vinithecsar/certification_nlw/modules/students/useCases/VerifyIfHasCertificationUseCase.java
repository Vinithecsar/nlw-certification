package br.com.vinithecsar.certification_nlw.modules.students.useCases;

import br.com.vinithecsar.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertificationUseCase {

    public boolean execute(VerifyHasCertificationDTO dto) {
        return dto.getEmail().equals("teste@email.com") && dto.getTechnology().equals("Java");
    }
}
