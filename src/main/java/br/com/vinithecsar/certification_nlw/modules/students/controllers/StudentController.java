package br.com.vinithecsar.certification_nlw.modules.students.controllers;

import br.com.vinithecsar.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import br.com.vinithecsar.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        if (this.verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO)) {
            return "Usuário já fez a prova";
        }
        return "Usuário pode fazer a prova";
    }
}