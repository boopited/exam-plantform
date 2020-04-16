package demo.examinationContext.domain.model;

import demo.common.ValueObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
class Paper implements ValueObject<Paper> {

    private ArrayList blankQuizzes = new ArrayList();

    public Paper(List<BlankQuiz> quizzes) {
        blankQuizzes.addAll(quizzes);
    }

    public List<BlankQuiz> getBlankQuizzes() {
        return Collections.unmodifiableList(blankQuizzes);
    }

    @Override
    public boolean sameValueAs(Paper other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        return Objects.equals(blankQuizzes, paper.blankQuizzes);
    }

    @Override
    public int hashCode() {
        return blankQuizzes != null ? blankQuizzes.hashCode() : 0;
    }
}
