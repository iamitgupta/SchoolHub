package amit.schoolhub;

public class Student {

    private String st_username,st_name,st_mobile;

    public Student(String st_username, String st_name, String st_mobile) {
        this.st_username = st_username;
        this.st_name = st_name;
        this.st_mobile = st_mobile;
    }

    public String getUsername() {
        return st_username;
    }

    public void setUsername(int id) {
        this.st_username = st_username;
    }

    public String getName() {
        return st_name;
    }

    public void setName(String name) {
        this.st_name = st_name;
    }
    public String getMobile() {
        return st_mobile;
    }

    public void setMobile(String name) {
        this.st_mobile = st_mobile;
    }



}

