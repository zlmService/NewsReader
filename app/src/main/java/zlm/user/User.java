package zlm.user;

import java.util.List;

/**
 * Created by zhao on 2016/3/29.
 */
public class User {
    String date;
    List<Stories> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    @Override
    public String toString() {
        return "User{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                '}';
    }
}
