/**
 * 系统管理--角色管理的单例对象
 */
var RolerTable = {
    id: "roleTable",//表格id
    seItem: null,	//被选中的条目
    table: null,    //bootstrap-table对象
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RolerTable.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '角色ID', field: 'roleId', align: 'center', valign: 'middle', sortable: true},
        {title: '角色名称', field: 'roleName', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remarks', align: 'center', valign: 'middle', sortable: true},
        {title: '操作', field: 'operate', align: 'center',formatter: operateFormatter}];
    return columns;
};

function operateFormatter(value, row, index) {
    return [
        '<button type="button" onclick="RolerTable.assignPermissions(\''+ row.roleId + '\')" class="btn btn-primary" style="margin-right:5px;">权限分配</button>'
    ].join('');
}

/**
 * 权限分配
 */
RolerTable.assignPermissions = function (roleId) {
    var index = layer.open({
        type: 2,
        title: '菜单信息',
        area: ['600px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/role/open_menu_ztree/' + roleId,
    });
    this.layerIndex = index;
}
/**
 * 点击添加用户时
 */
RolerTable.openAddRole = function () {
    var index = layer.open({
        type: 2,
        title: '添加角色',
        area: ['800px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/role/viewadd'
    });
    this.layerIndex = index;

};


/**
 * 检查是否选中
 */
RolerTable.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Aexit.info("请先选中表格中的某一记录！");
        return false;
    } else {
        RolerTable.seItem = selected[0];
        return true;
    }
};

/**
 * 删除角色
 */
RolerTable.delRole = function () {
    if (this.check()) {
        var operation = function(){
            var id = RolerTable.seItem.id;

            $.post(Aexit.ctxPath + "/role/delete/" +id ,
                {_method: "DELETE"},
                function(data){
                    if(data.code == 0) {
                        Aexit.success("删除成功!");
                        RolerTable.table.refresh();
                    } else {
                        Aexit.error("删除失败!" + data.message + "!");
                    }
            });
        };

        Aexit.confirm("是否删除该角色" + RolerTable.seItem.roleId + "?",operation);
    }
};


/**
 * 点击修改按钮时
 * @param userId 管理员id
 */
RolerTable.openEditRole = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑角色',
            area: ['800px', '300px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Aexit.ctxPath + '/role/viewedit/' + this.seItem.roleId
        });
        this.layerIndex = index;
    }
};


/**
 * 重置
 */
RolerTable.resetSearch = function () {
    $("#roleIdQ").val("");
    $("#roleNameQ").val("");
    RolerTable.search();
};

/**
 * 搜索
 */
RolerTable.search = function () {
    var queryData = {};
    queryData['roleId'] = $("#roleIdQ").val();
    queryData['roleName'] = $("#roleNameQ").val();

    RolerTable.table.refresh({query: queryData});
};


$(function () {
    var defaultColunms = RolerTable.initColumn();
    var table = new BSTable(RolerTable.id, "/role/list", defaultColunms,"GET");
    table.setPaginationType("client");
    RolerTable.table = table.init();
    /*var ztree = new $ZTree("deptTree", "/dept/tree");
    ztree.bindOnClick(MgrUser.onClickDept);
    ztree.init();*/
});
