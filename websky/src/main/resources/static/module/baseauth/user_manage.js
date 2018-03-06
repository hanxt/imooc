/**
 * 系统管理--用户管理的单例对象
 */
var UserTable = {
    id: "userTable",//表格id
    seItem: null,	//被选中的条目
    table: null,    //bootstrap-table对象
    layerIndex: -1 //弹出层标记
};

/**
 * 初始化表格的列
 */
UserTable.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '账号', field: 'userId', align: 'center', valign: 'middle', sortable: true},
        {title: '姓名', field: 'userName', align: 'center', valign: 'middle', sortable: true},
        {title: '性别', field: 'sex.text', align: 'center', valign: 'middle', sortable: true},
        {title: '角色', field: 'roleNames', align: 'center', valign: 'middle', sortable: true},
        {title: '部门', field: 'orgName', align: 'center', valign: 'middle', sortable: true},
        {title: '邮箱', field: 'email', align: 'center', valign: 'middle', sortable: true},
        {title: '电话', field: 'phone', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status.text', align: 'center', valign: 'middle', sortable: true}];
    return columns;
};


/**
 * 点击添加用户时
 */
UserTable.openAddMgr = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户',
        area: ['900px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/user/viewadd'
    });
    this.layerIndex = index;

};


/**
 * 检查是否选中
 */
UserTable.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Aexit.info("请先选中表格中的某一记录！");
        return false;
    } else {
        UserTable.seItem = selected[0];
        return true;
    }
};

/**
 * 删除用户
 */
UserTable.delUser = function () {
    if (this.check()) {
        var operation = function(){
            var userId = UserTable.seItem.userId;

            $.post(Aexit.ctxPath + "/user/delete",
                {_method: "DELETE",userId:userId},
                function(data){
                    if(data.code == 0) {
                        Aexit.success("删除成功!");
                        UserTable.table.refresh();
                    } else {
                        Aexit.error("删除失败!" + data.message + "!");
                    }
            });
        };

        Aexit.confirm("是否删除用户" + UserTable.seItem.userId + "?",operation);
    }
};


/**
 * 冻结用户账户
 * @param userId
 */
UserTable.freezeAccount = function () {
    if (this.check()) {
        var userId = this.seItem.userId;

        $.ajax({
            type: 'PUT',
            dataType: 'json',
            url: Aexit.ctxPath + "/user/freeze",
            data: {userId: userId},
            success: function (data) {
                if (data.code == 0) {
                    Aexit.success("冻结成功!");
                    UserTable.table.refresh();
                } else {
                    Aexit.error("冻结失败!" + data.message + "!");
                }
            }
        });
    }
};

/**
 * 解除冻结用户账户
 * @param userId
 */
UserTable.unfreeze = function () {
    if (this.check()) {
        var userId = this.seItem.userId;

        $.ajax({
            type: 'PUT',
            dataType: 'json',
            url: Aexit.ctxPath + "/user/unfreeze",
            data: {userId: userId},
            success: function (data) {
                if (data.code == 0) {
                    Aexit.success("解除冻结成功!");
                    UserTable.table.refresh();
                } else {
                    Aexit.error("解除冻结失败!" + data.message + "!");
                }
            }
        });
    }
}


/**
 * 点击修改按钮时
 * @param userId 管理员id
 */
UserTable.openEditUser = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑管理员',
            area: ['900px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Aexit.ctxPath + '/user/viewedit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 重置密码
 */
UserTable.resetPwd = function () {
    if (this.check()) {
        var userId = this.seItem.userId;
        parent.layer.confirm('是否重置密码为111111？', {
            btn: ['确定', '取消'],
            shade: false //不显示遮罩
        }, function () {
            $.ajax({
                type: 'PUT',
                dataType: 'json',
                url: Aexit.ctxPath + "/user/reset",
                data: {userId: userId},
                success: function (data) {
                    if (data.code == 0) {
                        Aexit.success("重置密码成功!");
                        UserTable.table.refresh();
                    } else {
                        Aexit.error("重置密码失败!" + data.message + "!");
                    }
                }
            });
        });
    }
};

/**
 * 重置
 */
UserTable.resetSearch = function () {
    $("#userIdQ").val("");
    $("#userNameQ").val("");
    UserTable.search();
};

/**
 * 搜索
 */
UserTable.search = function () {
    var queryData = {};
    queryData['userId'] = $("#userIdQ").val();
    queryData['userName'] = $("#userNameQ").val();
    queryData['orgId'] = "";

    UserTable.table.refresh({query: queryData});
};

/**
 * 点击角色分配
 * @param
 */
UserTable.roleAssign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '角色分配',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Aexit.ctxPath + '/user/role_assign/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};



$(function () {
    var defaultColunms = UserTable.initColumn();
    var table = new BSTable(UserTable.id, "/user/list", defaultColunms,"GET");
    table.setPaginationType("client");
    UserTable.table = table.init();
    ztreeInit();
});





/**
  * table页面ztree配置
 */
var setting = {
    data:{
        key:{
            name: "text"
        }
    },
    callback: {
        onClick: zTreeOnClick
    }
};

/**
 * table页面初始化ztree
 */
function ztreeInit(){
    jQuery.ajax({
        url:Aexit.ctxPath + "/org/ztree",
        dataType:'json',
        type:'GET',
        success:function(zNodes){
            var treeObj = jQuery.fn.zTree.init($("#deptTree"), setting, zNodes);
            var nodes = treeObj.getNodes();
            if (nodes.length > 0) {//第一级目录展开
                treeObj.expandNode(nodes[0], true, false, false);
            }
        }
    });
}
/**
 * table页面ztree点击回调方法
 */
function zTreeOnClick(event, treeId, treeNode) {
    var queryData = {};
    queryData['userId'] = $("#userIdQ").val();
    queryData['userName'] = $("#userNameQ").val();
    queryData['orgId'] = treeNode.id;

    UserTable.table.refresh({query: queryData});
}
