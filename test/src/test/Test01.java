package test;

public class Test01 {
    public static void main(String[] args) {

    }
}
class Emp {
    //非静态的成员变量。
    static int count = 0;//计数器
    String name;

    //构造代码块
    {
        count++;
    }

    public Emp(String name) {
        this.name = name;
    }
}

