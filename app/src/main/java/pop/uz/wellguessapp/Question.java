package pop.uz.wellguessapp;

import android.widget.CheckBox;

public class Question {

    private int id, image ;
    private String  correctAnswer;
    private CheckBox checkBox1, checkBox2, checkBox3;

    public CheckBox getCheckBox1() {
        return checkBox1;
    }

    public CheckBox getCheckBox2() {
        return checkBox2;
    }

    public CheckBox getCheckBox3() {
        return checkBox3;
    }

    public Question(CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3) {
        this.checkBox1 = checkBox1;
        this.checkBox2 = checkBox2;
        this.checkBox3 = checkBox3;
    }

    public Question(int id, int image, String correctAnswer) {
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
