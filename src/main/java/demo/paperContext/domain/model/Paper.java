package demo.paperContext.domain.model;

import demo.common.Entity;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
class Paper implements Entity<Paper> {

    private final String id;
    private final ArrayList blankQuizzes;

    private Paper(String id, List<BlankQuiz> quizzes) {
        this.id = id;
        this.blankQuizzes = new ArrayList(quizzes);
    }

    public String getId() {
        return id;
    }

    public List<BlankQuiz> getBlankQuizzes() {
        return Collections.unmodifiableList(blankQuizzes);
    }

    public static Paper assemble(String id, List<BlankQuiz> quizzes) {
        return new Paper(id, quizzes);
    }

    public void reassemble(List<BlankQuiz> quizzes) {
        blankQuizzes.clear();
        blankQuizzes.addAll(quizzes);
    }

    @Override
    public boolean sameIdentityAs(Paper other) {
        return id.equals(other.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        return Objects.equals(blankQuizzes, paper.getBlankQuizzes());
    }

    @Override
    public int hashCode() {
        return blankQuizzes != null ? blankQuizzes.hashCode() : 0;
    }
}
