package pop.uz.wellguessapp.questions;

public class QuestionEntryText {
    private int id, image;
    private String correctAnswer;

    public QuestionEntryText(int id, int image, String correctAnswer) {
        this.id = id;
        this.image = image;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
