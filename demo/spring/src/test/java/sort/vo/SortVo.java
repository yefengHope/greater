package sort.vo;

import java.util.Comparator;

/**
 * Created by 韩峰 on 2017/11/30.
 */
public class SortVo implements Cloneable {

    private String name;
    private String name1;
    private String name2;
    private String name3;
    private String name4;
    private String name5;

    private Integer num;

    @Override
    protected SortVo clone() throws CloneNotSupportedException {
        return (SortVo) super.clone();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getName5() {
        return name5;
    }

    public void setName5(String name5) {
        this.name5 = name5;
    }

}
