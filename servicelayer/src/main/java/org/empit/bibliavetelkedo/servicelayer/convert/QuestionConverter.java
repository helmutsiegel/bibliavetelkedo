package org.empit.bibliavetelkedo.servicelayer.convert;

import org.empit.bibliavetelkedo.dal.entity.QuestionBE;
import org.empit.bibliavetelkedo.servicelayer.dto.QuestionDTO;

public class QuestionConverter {

    public static QuestionDTO fromBEToDTO(QuestionBE questionBE) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(questionBE.getId());
        questionDTO.setQuestion(questionBE.getQuestion());
        questionDTO.setAnswerA(questionBE.getAnswerA());
        questionDTO.setAnswerB(questionBE.getAnswerB());
        questionDTO.setAnswerC(questionBE.getAnswerC());
        questionDTO.setAnswerD(questionBE.getAnswerD());
        questionDTO.setCanHalf(true);
        questionDTO.setCanFault(true);
        questionDTO.setCanNext(true);
        return questionDTO;
    }
}
