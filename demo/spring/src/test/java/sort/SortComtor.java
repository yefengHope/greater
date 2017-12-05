package sort;

import sort.vo.SortVo;

import java.util.Comparator;

/**
 * Created by 韩峰 on 2017/11/30.
 */
public class SortComtor implements Comparator<SortVo> {

    @Override
    public int compare(SortVo o1, SortVo o2) {
        return o1.getNum().compareTo(o2.getNum());
    }
}
