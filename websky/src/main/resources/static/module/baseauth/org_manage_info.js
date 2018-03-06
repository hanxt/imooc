/**
 * 菜单详情对话框（可用于添加和修改对话框）
 */
var OrgInfoDlg = {
    orgInfoData: {},  //表单数据
    validateFields: {//表单元素校验规则
        orgName: {
            validators: {
                notEmpty: {
                    message: '组织名称不能为空'
                }
            }
        },
        orgPid: {
            validators: {
                notEmpty: {
                    message: '父节点名称不能为空'
                }
            }
        },
        address: {
            validators: {
                notEmpty: {
                    message: '组织地址不能为空'
                }
            }
        }/*,
        level: {
            validators: {
                notEmpty: {
                    message: '组织层级不能为空'
                }
            }
        }*/
    }
};



/**
 * 收集数据
 */
OrgInfoDlg.collectData = function () {
    this.set('orgName').set('orgPid').set('address').set('id');
};

/**
 * 验证校验
 */
OrgInfoDlg.validate = function () {
    $('#orgInfoForm').data("bootstrapValidator").resetForm();
    $('#orgInfoForm').bootstrapValidator('validate');
    return $("#orgInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加组织
 */
OrgInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    $.ajax({
        url: Aexit.ctxPath + "/org/add",
        data: JSON.stringify(this.orgInfoData),
        type: "post",
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        success:function (data) {
            if(data.code == 0) {
                window.parent.OrgTable.table.refresh();
                OrgInfoDlg.close();
            } else {
                Aexit.error(data.message);
            }
        }
    })
};

/**
 * 提交修改
 */
OrgInfoDlg.editSubmit = function () {
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    $.ajax({
        type: 'PUT',
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        url: Aexit.ctxPath + "/org/edit",
        data: JSON.stringify(this.orgInfoData),
        success: function (data) {
            if (data.code == 0) {
                Aexit.success("修改成功!");
                if (window.parent.OrgTable != undefined) {
                    window.parent.OrgTable.table.refresh();
                    OrgInfoDlg.close();
                }
            } else {
                Aexit.error("修改失败!" + data.message + "!");
            }
        }
    });
};

$(function () {
    Aexit.initValidator("orgInfoForm", OrgInfoDlg.validateFields);
    orgZtreeInit();//初始化组织机构
});

/**
 * 清除数据
 */
OrgInfoDlg.clearData = function () {
    this.orgInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrgInfoDlg.set = function (key, val) {
    this.orgInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrgInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
OrgInfoDlg.close = function () {
    parent.layer.close(window.parent.OrgTable.layerIndex);
};


/**
 * 打开组织机构ztree页面
 */
OrgInfoDlg.openOrgZtree = function(){
    var index = layer.open({
        type: 2,
        title: '组织机构',
        area: ['600px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/org/open_org_ztree',
        btn: ['确定','关闭'],
        yes: function(index){
            //当点击‘确定’按钮的时候，获取弹出层返回的值
            var res = window["layui-layer-iframe" + index].callbackParam();
            //打印返回的值，看是否有我们想返回的值。
            /*console.log(res);*/
            //最后关闭弹出层
            layer.close(index);
        }
    });
};

/**
 * 弹出层关闭回调
 */
function callbackParam(){
    parent.document.getElementById("orgFatherName").value = OrgInfoDlg.orgFatherName;
    parent.document.getElementById("orgPid").value = OrgInfoDlg.orgPid;

}

/**
 *  组织机构ztree配置
 */
var setting = {
    data:{
        key:{
            name: "text"
        }
    },
    callback: {
        onCheck:orgTreeCheck
    },
    check: {
        enable: true,
        chkStyle: "radio",
        radioType: "all"
    }
};

/**
 *  初始化弹出层组织机构ztree
 */
function orgZtreeInit(){
    jQuery.ajax({
        url:Aexit.ctxPath + "/org/ztree",
        dataType:'json',
        type:'GET',
        success:function(zNodes){
            jQuery.fn.zTree.init($("#orgZtree"), setting, zNodes);
        }
    });
}

/**
 *  弹出层组织机构ztree页面单选按钮选中事件
 */
function orgTreeCheck (event, treeId, treeNode) {
    OrgInfoDlg.orgPid = treeNode.id;
    OrgInfoDlg.orgFatherName = treeNode.text;

}
