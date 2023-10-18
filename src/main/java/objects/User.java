package objects;

import com.google.gson.annotations.Expose;
import utils.DateTimeUtils;
import utils.PropertiesUtils;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class User {

    //@Expose
    private String username;
    //@Expose
    private String password;
    //@Expose
    private String email;

    private String firstName;

    private String lastName;
    //@Expose
    private String about;
    //@Expose
    private String secretQuestion;
    //@Expose
    private String secretAnswer;
    //@Expose
    private Long createdAt;
    //@Expose
    private Integer heroCount;
    //@Expose
    private List<Hero> heroes;

    public User(String username, String password, String email, String firstName, String lastName, String about, String secretQuestion, String secretAnswer, Date createdAt, List<Hero> heroes) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setAbout(about);
        setSecretQuestion(secretQuestion);
        setSecretAnswer(secretAnswer);
        setCreatedAt(createdAt);
        setHeroes(heroes);
        setHeroCount(this.heroes.size());
    }

    public User(String username, String password, String email, String firstName, String lastName, String about, String secretQuestion, String secretAnswer) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setAbout(about);
        setSecretQuestion(secretQuestion);
        setSecretAnswer(secretAnswer);
        setCreatedAt(null);
        setHeroes(null);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public Date getCreatedAt() {
        if (this.createdAt == null) {
            return null;
        }
        return new Date(createdAt);
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        if(createdAt == null) {
            this.createdAt = null;
        } else {
            this.createdAt = createdAt.getTime();
        }
    }

    public Integer getHeroCount() {
        return heroCount;
    }

    private void setHeroCount(int heroCount) {
        this.heroCount = heroCount;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
        if (heroes == null) {
            setHeroCount(0);
        } else {
            setHeroCount(heroes.size());
        }
    }

    public static User createNewUniqueUser(String sUsername) {
        String username = sUsername.toLowerCase() + DateTimeUtils.getDateTimeStamp();
        String password = PropertiesUtils.getDefaultPassword();
        String email = username + "@mail.com";
        String firstName = "Name";
        String lastName = "Surname";
        String secretQuestion = "Question?";
        String secretAnswer = "Answer";
        String about = "About Me Text";
        return new User (username, password, email, firstName, lastName, about, secretQuestion, secretAnswer);
    }

    @Override
    public String toString() {
        return "User {"
                + "Username: " + getUsername() + ", "
                + "Password: " + getPassword() + ", "
                + "Email: " + getEmail() + ", "
                + "First Name: " + getFirstName() + ", "
                + "Last Name: " + getLastName() + ", "
                + "About: " + getAbout() + ", "
                + "Secret Question: " + getSecretQuestion() + ", "
                + "Secret Answer: " + getSecretAnswer() + ", "
                + "Created at: " + getCreatedAt() + ", "
                + "Hero Count: " + getHeroCount() + ", "
                + "Heroes: " + getHeroes() + "}";
    }
}
