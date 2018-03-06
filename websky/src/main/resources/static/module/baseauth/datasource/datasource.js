/**
 * 数据源管理初始化
 */
var Datasource = {
    id: "DatasourceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Datasource.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title:'标志', field:'dbFlag', align: 'center', valign: 'middle', sortable: true},
{title:'数据库名称', field:'dbName', align: 'center', valign: 'middle', sortable: true},
{title:'数据库类型', field:'dbType', align: 'center', valign: 'middle', sortable: true},
{title:'数据库读写类型', field:'dbRw', align: 'center', valign: 'middle', sortable: true},
{title:'链接url', field:'conUrl', align: 'center', valign: 'middle', sortable: true},
{title:'链接ip', field:'conIp', align: 'center', valign: 'middle', sortable: true},
{title:'连接端口', field:'conPort', align: 'center', valign: 'middle', sortable: true},
{title:'用户名', field:'conUsername', align: 'center', valign: 'middle', sortable: true},
{title:'密码', field:'conPassword', align: 'center', valign: 'middle', sortable: true},
{title:'链接超时时间设置', field:'conTimeout', align: 'center', valign: 'middle', sortable: true},
{title:'连接池最大容量', field:'poolMaxtotal', align: 'center', valign: 'middle', sortable: true},
{title:'链接池内最大空闲链接数量', field:'poolMaxidle', align: 'center', valign: 'middle', sortable: true},
{title:'连接池内最小空闲链接数量', field:'poolMinidle', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Datasource.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Aexit.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Datasource.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据源
 */
Datasource.openAddDatasource = function () {
    var index = layer.open({
        type: 2,
        title: '添加数据源',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/datasource/datasource_add'
    });
    this.layerIndex = index;
};

/**
 * 打开编辑数据源
 */
Datasource.openEditDatasource = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑数据源',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Aexit.ctxPath + '/datasource/datasource_update/' + Datasource.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据源
 */
Datasource.remove = function () {
    if (this.check()) {
        var operation = function(){
                var id = Datasource.seItem.id;

                $.post(Aexit.ctxPath + "/datasource/delete/" +id ,
                    {_method: "DELETE"},
                    function(data){
                        if(data.code == 0) {
                            Aexit.success("删除成功!");
                            Datasource.table.refresh();
                        } else {
                            Aexit.error("删除失败!" + data.message + "!");
                        }
                });
        };

        Aexit.confirm("是否删除数据源" + Datasource.seItem.id + "?",operation);
    }
};

/**
 * 查询数据源列表
 */
Datasource.search = function () {
    var queryData = {};
    queryData['dbName'] = $("#dbNameQ").val();
    queryData['dbType'] = $("#dbTypeQ").val();
    Datasource.table.refresh({query: queryData});
};

/**
 * 重置
 */
Datasource.resetSearch = function () {
    $("#dbNameQ").val("");
    $("#dbTypeQ").val("");
   Datasource.search();
};

$(function () {
    var defaultColunms = Datasource.initColumn();
    var table = new BSTable(Datasource.id, "/datasource/list", defaultColunms);
    table.setPaginationType("client");
    Datasource.table = table.init();
});
