package demo.paperContext.domain.model;

import demo.common.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
class BlankQuiz implements ValueObject<BlankQuiz> {

    private final String id;
    private String question;
    private String referenceAnswer;
    private final int score;

    @Override
    public boolean sameValueAs(BlankQuiz other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlankQuiz blankQuiz = (BlankQuiz) o;

        if (score != blankQuiz.score) return false;
        if (!Objects.equals(id, blankQuiz.id)) return false;
        if (!Objects.equals(question, blankQuiz.question)) return false;
        return Objects.equals(referenceAnswer, blankQuiz.referenceAnswer);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (referenceAnswer != null ? referenceAnswer.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }
}
