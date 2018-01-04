package testEnum;

/**
 * Week
 *
 * @author guxiang
 * @date 2017/6/24
 */
public class Week {
    public static final  Week MON = new Week("周一");
    public static final  Week TUE= new Week("周二");
    public static final  Week WED = new Week("周三");

    private String name;

    /*私有构造*/
    private Week(String name) {
        this.name = name;
    }

    public String getName(){
        return  name;
    }

    /*
    ===========================================================
    测试main方法*/
    public static void main(String[] args) {
        Week mon = Week.MON;
        System.out.println(mon.getName());
    }
}
