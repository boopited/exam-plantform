package demo.examinationContext;

import java.util.Objects;

public class BlankQuiz {

    private final String question;
    private final String referenceAnswer;
    private final int score;

    public BlankQuiz(String question, String answer, int score) {
        this.question = question;
        this.referenceAnswer = answer;
        this.score = score;
    }

    public String getQuestion() {
        return question;
    }

    public String getReferenceAnswer() {
        return referenceAnswer;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlankQuiz blankQuiz = (BlankQuiz) o;

        if (score != blankQuiz.score) return false;
        if (!Objects.equals(question, blankQuiz.question)) return false;
        return Objects.equals(referenceAnswer, blankQuiz.referenceAnswer);
    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (referenceAnswer != null ? referenceAnswer.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }
}
