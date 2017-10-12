/*
 * bootstrap 数据表格
 * Author：xtt
 * 2017.01.19
 */
/*
 *clickToSelect:设置为 true，当用户点击某一行时，则会选中/取消选中复选框,默认为false. 
 *onCheck:复选框
 */
;(function(){
	var table = function(ele,opt) {
		this.$element=ele,
		defaults = {
			url:'',
			clickToSelect:false,
			pagination:true,
			showColumns:true,
			clickToSelect:false,
			onCheck:setOnCheck,//复选
			onCheckAll:setOnCheckAll,//全选
			onUncheck:setOnUncheck,//取消复选
			onLoadSuccess:setOnLoadSuccess,//表格加载完
			onClickRow:setOnClickRow,//点击某一行
			onClickCell:setOnClickCell,//点击某一列
			param:{},	
		},
		this.options=$.extend({},defaults,opt);
	};
	table.prototype = {
			start:function() {
				var urls=this.options.url,
					clickToSelect=this.options.clickToSelect,
					paginations=this.options.pagination,
					onCheck=this.options.onCheck,
					onCheckAll=this.options.onCheckAll,
					onUncheck=this.options.onUncheck,
					onLoadSuccess=this.options.onLoadSuccess,
					onClickRow=this.options.onClickRow,
					onClickCell=this.options.onClickCell,
					paramopt=this.options.param;
				
				$(this.$element).bootstrapTable({
					contentType:'application/x-www-form-urlencoded',
					queryParamsType: "limit", 
					dataType:'json',
					method:'post',
					pagination: true,
					sidePagination:"server",
					clickToSelect:clickToSelect,
					url:urls,
					onCheck:onCheck,
					onCheckAll:onCheckAll,
					onUncheck:onUncheck,
					onLoadSuccess:onLoadSuccess,
					onClickRow:onClickRow,
					onClickCell:onClickCell,
					queryParams:setParams,	
				});
				
				function setParams(params){
					var current = (params.offset/params.limit)+1;
					paramopt.page = current;
					paramopt.rows = params.limit;
					paramopt.sort = params.sort;
					paramopt.order = params.order;
					console.log(paramopt);
					return paramopt;
				}
			}
	}
	$.fn.myBootstrap = function(options) {
		var Table= new table(this,options);
		return Table.start();
	}
})();
function setOnCheck() {}
function setOnCheckAll() {}
function setOnUncheck() {}
function setOnLoadSuccess() {}
function setOnClickRow() {}
function setOnClickCell() {}