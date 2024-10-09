package com.example.ii3510_2425_smallproject_g1_sfct;

public class QuizQuestion {
    private String question;
    private String[] answers;
    private int correctAnswerIndex;

    public QuizQuestion(String question, String[] answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isCorrectAnswer(int selectedAnswerIndex) {
        return selectedAnswerIndex == correctAnswerIndex;
    }
}
