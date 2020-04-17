package demo.answerSheetContext.domain.model;

import demo.common.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
class Answer implements Entity<Answer> {

    private final String id;
    private String answer;
    private int score;

    public void submit(String answer, int score) {
        this.answer = answer;
        this.score = score;
    }

    @Override
    public boolean sameIdentityAs(Answer other) {
        return id.equals(other.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer1 = (Answer) o;

        if (score != answer1.score) return false;
        if (!Objects.equals(id, answer1.id)) return false;
        return Objects.equals(answer, answer1.answer);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }
}
