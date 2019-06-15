package data.personal.coursefinalversionfour;

public class UserConfig {
    private int id;
    private int age;
    private String name;
    private String email;

    public UserConfig() {
    }

    public UserConfig(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public UserConfig(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

}