/**
 * bootstrap公共类
 * Created by 韩峰 on 2016/11/1.
 */

var bootStarp = {};

bootStarp.defaultconfig = {
    method: 'post',
    contentType: "application/x-www-form-urlencoded",
    toolbar: '#toolbar',      //工具按钮用哪个容器
    striped: true,          //是否显示行间隔色
    cache: false,           //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,       //是否显示分页（*）
    sortable: true,         //是否启用排序
    sortOrder: "desc",      //排序方式
    pageNumber:1,           //初始化加载第一页，默认第一页
    pageSize: 10,           //每页的记录行数（*）
    pageList: [10, 25, 50],     //可供选择的每页的行数（*）
    url: "/user/all_page_list.html",  //这个接口需要处理bootstrap table传递的固定参数
    queryParamsType:'',         //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                                // 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
    queryParams: bootstrapTableInitQueryParams,   //前端调用服务时，会默认传递上边提到的参数，如果需要添加自定义参数，可以自定义一个函数返回请求参数
    sidePagination: "server",       //分页方式：client客户端分页，server服务端分页（*）
    search: true,                   //是否显示表格搜索,服务端搜索需设置sidePagination: "server",，反之
    strictSearch: false,            //设置为 true启用 全匹配搜索，否则为模糊搜索
    searchOnEnterKey: true,        //回车搜索
    searchText:"",                   //初始化搜索文字
    showColumns: true,          //是否显示所有的列
    showRefresh: true,          //是否显示刷新按钮
    minimumCountColumns: 2,     //最少允许的列数
    clickToSelect: true,        //是否启用点击选中行
    columns:[]                  //列名json
    /*样例:
    columns: [{
     idField:'id',
     checkbox:true,
     align: 'center'
     },
     {
     field: 'name',  //字段名,对应实体属性
     title: '名称',   //显示的名字
     align: 'center'  //对齐方式
     }, {
     field: 'loginNum',
     title: '登录账号',
     align: 'center'
     }]*/
};

/**
 * 默认查询参数
 * @param params
 * @returns {{limit: *, pageNumber: *, searchText: *, sortOrder: *, sortName: *}}
 */
bootstrapTableInitQueryParams = function (params){
    /*params 为bootStarp自带参数1111*/
    return{
        limit: params.pageSize,             //分页大小,不知道为什么pageSize获取失败
        pageNumber:params.pageNumber,       //当前页码
        searchText:params.searchText,       //搜索文本
        sortOrder:params.sortOrder,         //排序列名
        sortName:params.sortName,           //自带搜索框获取的文本
    }
}

/**
 * 自定义行样式
 * @param row       行数据
 * @param index     行下标
 * 使用方法:
 * $('#userId').bootstrapTable({
 *     rowStyle:rowStyle
 * });
 * @returns {*}
 */
function rowStyle(row, index) {
    var classes = ['active', 'success', 'info', 'warning', 'danger']; //预定义class name
    if (index % 2 === 0 && index / 2 < classes.length) {
        return {
            classes: classes[index / 2]
        };
    }
    return {};
}


//------------------ 格式化列 ------------------
/**
 * 格式化1
 * 调用方式 : js方式:列参数formatter:"nameFormatter" html方式:data-formatter = "nameFormatter"
 * @param value
 * @returns {string}
 */
function nameFormatter(value) {
    return '<a href="https://github.com/wenzhixin/' + value + '">' + value + '</a>';
}
/**
 * 格式化2
 * 调用方式 : js方式:列参数formatter:"nameFormatter" html方式:data-formatter = "nameFormatter"
 * @param value
 * @param row
 * @param index
 * @returns {*}
 */
function runningFormatter(value, row, index) {
    return index;
}
