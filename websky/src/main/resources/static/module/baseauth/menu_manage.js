/**
 * 菜单管理的单例
 */
var Menu = {
    id: "menuTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle',width:'50px'},
        {title: '菜单名称', field: 'name', align: 'center', valign: 'middle', sortable: true,width:'17%'},
        {title: '请求地址', field: 'url', align: 'center', valign: 'middle', sortable: true,width:'15%'},
        {title: '层级', field: 'level', align: 'center', valign: 'middle', sortable: true},
        {title: '是否是叶子', field: 'isLeaf.text', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};



/**
 * 搜索
 */
Menu.search = function () {
    var queryData = {};

    queryData['menuName'] = $("#menuNameQ").val();
    queryData['level'] = $("#levelQ").val();

    Menu.table.refresh({query: queryData});
}

/**
 * 检查是否选中
 */
Menu.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Aexit.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Menu.seItem = selected[0];
        return true;
    }
};
/**
 * 重置
 */
Menu.resetSearch = function () {
    $("#menuNameQ").val("");
    $("#levelQ").val("");
    Menu.search();
};
/**
 * 图表菜单添加
 */
Menu.openAddChartMenu = function () {
    var index = layer.open({
        type: 2,
        title: '添加图表',
        area: ['800px', '260px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/menu/viewaddchart'
    });
    this.layerIndex = index;
}

/**
 * 菜单修改
 */
Menu.openEditMenu = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑菜单',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Aexit.ctxPath + '/menu/viewedit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 菜单添加
 */
Menu.openAddMenu = function () {
        var index = layer.open({
            type: 2,
            title: '添加菜单',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Aexit.ctxPath + '/menu/viewadd'
        });
        this.layerIndex = index;
};

/**
 * 菜单删除
 */
Menu.delMenu = function () {
    if (this.check()) {
        var operation = function(){
            var id = Menu.seItem.id;
            $.post(Aexit.ctxPath + '/menu/delete/' + id,
                {_method: "DELETE"},
                function(data){
                    if(data.code == 0) {
                        Aexit.success("删除成功!");
                        Menu.table.refresh();
                    } else {
                        Aexit.error("删除失败!" + data.message + "!");
                    }
                });
        };

        Aexit.confirm("是否删除该菜单及其子菜单?",operation);
    }
};

$(function () {
    var defaultColunms = Menu.initColumn();
    var table = new BSTreeTable(Menu.id, "/menu/list", defaultColunms,"GET");
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("menuPid");
    table.setExpandAll(false);
    table.init();
    Menu.table = table;
});

