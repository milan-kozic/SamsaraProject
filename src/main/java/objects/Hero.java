package objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import data.HeroClass;
import utils.DateTimeUtils;

import java.util.Date;
import java.util.Random;

public class Hero {

    private static final String[] heroClasses = {HeroClass.WARRIOR, HeroClass.GUARDIAN, HeroClass.REVENANT, HeroClass.ENGINEER, HeroClass.THIEF, HeroClass.RANGER, HeroClass.ELEMENTALIST, HeroClass.NECROMANCER, HeroClass.MESMER};

    //@Expose
    @SerializedName("name")
    private String heroName;

    @SerializedName("type")
    private String heroClass;

    @SerializedName("level")
    private Integer heroLevel;

    //@Expose
    private String username;

    private Long createdAt;

    public Hero(String heroName, String heroClass, int heroLevel, String username, Date createdAt) {
        setHeroName(heroName);
        setHeroClass(heroClass);
        setHeroLevel(heroLevel);
        setUsername(username);
        setCreatedAt(createdAt);
    }

    public Hero(String heroName, String heroClass, int heroLevel, String username) {
        setHeroName(heroName);
        setHeroClass(heroClass);
        setHeroLevel(heroLevel);
        setUsername(username);
        setCreatedAt(null);
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public Integer getHeroLevel() {
        return heroLevel;
    }

    public void setHeroLevel(int heroLevel) {
        this.heroLevel = heroLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public static Hero createNewUniqueHero(User user, String sHeroName) {

        String heroName = sHeroName + DateTimeUtils.getDateTimeStamp();

        Random rand = new Random();
        int heroLevel = rand.nextInt(81);

        int indexClass = rand.nextInt(9);
        String heroClass = heroClasses[indexClass];

        String username = user.getUsername();

        return new Hero (heroName, heroClass, heroLevel, username);
    }

    @Override
    public String toString() {
        return "Hero {"
                + "Name: " + getHeroName() + ", "
                + "Class: " + getHeroClass() + ", "
                + "Level: " + getHeroLevel() + ", "
                + "Username: " + getUsername() + ", "
                + "Created at: " + getCreatedAt() + "}";

    }
}
