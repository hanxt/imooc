/**
 * 菜单管理的单例
 */
var OrgTable = {
    id: "orgTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrgTable.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle',width:'50px'},
        {title: '组织名称', field: 'orgName', align: 'center', valign: 'middle', sortable: true,width:'17%'},
        {title: '地址', field: 'address', align: 'center', valign: 'middle', sortable: true,width:'15%'},
        {title: '层级', field: 'level', align: 'center', valign: 'middle', sortable: true},
        {title: '是否是叶子', field: 'isLeaf.text', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};



/**
 * 搜索
 */
OrgTable.search = function () {
    var queryData = {};

    queryData['orgName'] = $("#orgNameQ").val();
    queryData['level'] = $("#levelQ").val();

    OrgTable.table.refresh({query: queryData});
}

/**
 * 检查是否选中
 */
OrgTable.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Aexit.info("请选择您所要操作的节点！");
        return false;
    } else {
        OrgTable.seItem = selected[0];
        return true;
    }
};
/**
 * 重置
 */
OrgTable.resetSearch = function () {
    $("#orgNameQ").val("");
    $("#levelQ").val("");
    OrgTable.search();
};
/**
 * 组织添加
 */
OrgTable.openAddOrg = function () {
    var index = layer.open({
        type: 2,
        title: '添加组织',
        area: ['900px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/org/add'
    });
    this.layerIndex = index;
};
/**
 * 组织修改
 */
OrgTable.openEditOrg = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑组织',
            area: ['900px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Aexit.ctxPath + '/org/viewedit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 组织删除
 */
OrgTable.delOrg = function () {
    if (this.check()) {
        var operation = function(){
            var id = OrgTable.seItem.id;
            $.post(Aexit.ctxPath + '/org/delete/' + id,
                {_method: "DELETE"},
                function(data){
                    if(data.code == 0) {
                        Aexit.success("删除成功!");
                        OrgTable.table.refresh();
                    } else {
                        Aexit.error("删除失败!" + data.message + "!");
                    }
                });
        };

        Aexit.confirm("是否删除该组织及其子组织?",operation);
    }
};

$(function () {
    var defaultColunms = OrgTable.initColumn();
    var table = new BSTreeTable(OrgTable.id, "/org/list", defaultColunms,"GET");
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("orgPid");
    table.setExpandAll(false);
    table.init();
    OrgTable.table = table;
});