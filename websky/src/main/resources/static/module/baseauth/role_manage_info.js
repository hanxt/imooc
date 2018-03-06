/**
 * 角色详情对话框（可用于添加和修改对话框）
 */
var RoleInfoDlg = {
    roleInfoData: {},  //表单数据
    validateFields: {
        roleId: {
            validators: {
                notEmpty: {
                    message: '角色ID不能为空'
                }
            }
        },
        roleName: {
            validators: {
                notEmpty: {
                    message: '角色名称不能为空'
                }
            }
        }//表单元素校验规则
    }
};



/**
 * 收集数据
 */
RoleInfoDlg.collectData = function () {
    this.set('id').set('roleId').set('roleName').set('remarks');
};


/**
 * 验证校验
 */
RoleInfoDlg.validate = function () {
    $('#roleInfoForm').data("bootstrapValidator").resetForm();
    $('#roleInfoForm').bootstrapValidator('validate');
    return $("#roleInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
RoleInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    $.ajax({
        url: Aexit.ctxPath + "/role/add",
        data: JSON.stringify(this.roleInfoData),
        type: "post",
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        success:function (data) {
            if(data.code == 0) {
                window.parent.RolerTable.table.refresh();
                RoleInfoDlg.close();
            } else {
                Aexit.error(data.message);
            }
        }
    })
};

/**
 * 提交修改
 */
RoleInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    $.ajax({
        type: 'PUT',
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        url: Aexit.ctxPath + "/role/edit",
        data: JSON.stringify(this.roleInfoData),
        success: function (data) {
            if (data.code == 0) {
                Aexit.success("修改成功!");
                if (window.parent.RolerTable != undefined) {
                    window.parent.RolerTable.table.refresh();
                    RoleInfoDlg.close();
                }
            } else {
                Aexit.error("修改失败!" + data.message + "!");
            }
        }
    });
};


$(function () {
    Aexit.initValidator("roleInfoForm", RoleInfoDlg.validateFields);
    var roleId = $('#roleId').val();
    loadZtree(roleId);//通过roleid获取menuids

});

/**
 * 清除数据
 */
RoleInfoDlg.clearData = function () {
    this.roleInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoleInfoDlg.set = function (key, val) {
    this.roleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoleInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
RoleInfoDlg.close = function () {
    parent.layer.close(window.parent.RolerTable.layerIndex);
};

/**
 *  父菜单ztree配置
 */
var setting = {
    data:{
        key:{
            name: "text"
        }
    },
    check: {
        enable: true,
        chkStyle: "checkbox",
        radioType: "all"
    }

};

/**
 *  初始化弹出层父菜单ztree
 *  (这里要显示所有权限，该用户的权限回显时，被自动选中)
 */
function loadZtree(roleId){
    jQuery.ajax({
        type:"post",
        url:Aexit.ctxPath + "/role/role_assign_menu",
        data:{"roleId":roleId},
        dataType:"json",
        success:function(zNodes){
            jQuery.fn.zTree.init($("#roleMenuZtree"), setting, zNodes);
            var treeObj=$.fn.zTree.getZTreeObj("roleMenuZtree");
            var nodes=treeObj.getCheckedNodes(true);
            for(var i = 0;i < nodes.length; i ++){
                treeObj.expandNode(nodes[i], true, false, false);
            }
        }
    })
}


/**
 *  弹出层组织机构ztree页面单选按钮选中事件
 */
function menuTreeCheck (event, treeId, treeNode) {

    //获取当前被勾选的节点的集合
    var treeObj=$.fn.zTree.getZTreeObj("roleMenuZtree");
    var nodes=treeObj.getCheckedNodes(true);
    var menuidArray="";
    for(var i = 0;i < nodes.length; i ++){
        menuidArray += nodes[i].id + ",";
    }
    var menuIds = menuidArray.substring(0,menuidArray.length-1);//截取尾字符
    RoleInfoDlg.menuIds = menuIds;
}

/**
 *  点击确定提交权限分配
 */
RoleInfoDlg.okSubmit = function () {
    //弹出层组织机构ztree页面单选按钮选中事件
    menuTreeCheck();
    //提交信息
    $.ajax({
        url: Aexit.ctxPath + "/role/set_menus",
        data:{
            roleId: $('#roleId').val(),
            menuIds: RoleInfoDlg.menuIds,
        },
        type: "POST",
        /*contentType: "application/json", //必须有*/
        dataType: "json", //表示返回值类型，不必须
        success:function (data) {
            if(data.code == 0) {
                Aexit.success("权限分配成功!");
                RoleInfoDlg.close();
            } else {
                Aexit.error("权限分配失败!" + data.message + "!");
            }
        }
    });

}
