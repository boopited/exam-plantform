package demo.examinationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paper {

    private ArrayList blankQuizzes = new ArrayList();

    public Paper(List<BlankQuiz> quizzes) {
        blankQuizzes.addAll(quizzes);
    }

    public List<BlankQuiz> getBlankQuizzes() {
        return blankQuizzes;
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
