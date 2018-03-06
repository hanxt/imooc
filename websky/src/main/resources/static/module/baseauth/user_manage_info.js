/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var UserInfoDlg = {
    orgId : -1,
    orgName : "",
    userInfoData: {},  //表单数据
    validateFields: {
        userId: {
            validators: {
                notEmpty: {
                    message: '账户不能为空'
                }
            }
        },
        userName: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        orgName: {
            validators: {
                notEmpty: {
                    message: '组织不能为空'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'rePassword',
                    message: '两次密码不一致'
                }
            }
        },
        rePassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'password',
                    message: '两次密码不一致'
                }
            }
        }  //表单元素校验规则
    }
};



/**
 * 收集数据
 */
UserInfoDlg.collectData = function () {
    this.set('id').set('userId').set('userName').set('sex').set('password')
        .set('email').set('phone').set('orgId');
};

/**
 * 验证两个密码是否一致
 */
UserInfoDlg.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("rePassword");
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/**
 * 验证校验
 */
UserInfoDlg.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
UserInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    if (!this.validatePwd()) {
        Aexit.error("两次密码输入不一致");
        return;
    }

    //提交信息
    $.ajax({
        url: Aexit.ctxPath + "/user/add",
        data: JSON.stringify(this.userInfoData),
        type: "post",
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        success:function (data) {
            if(data.code == 0) {
                window.parent.UserTable.table.refresh();
                UserInfoDlg.close();
            } else {
                Aexit.error(data.message);
            }
        }
    })
};

/**
 * 提交修改
 */
UserInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    $.ajax({
        type: 'PUT',
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        url: Aexit.ctxPath + "/user/edit_by_tableid",
        data: JSON.stringify(this.userInfoData),
        success: function (data) {
            if (data.code == 0) {
                Aexit.success("修改成功!");
                if (window.parent.UserTable != undefined) {
                    window.parent.UserTable.table.refresh();
                    UserInfoDlg.close();
                }
            } else {
                Aexit.error("修改失败!" + data.message + "!");
            }
        }
    });
};

/**
 * 修改密码
 */
/*UserInfoDlg.chPwd = function () {
    var ajax = new $ax(Feng.ctxPath + "/mgr/changePwd", function (data) {
        Feng.success("修改成功!");
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set("oldPwd");
    ajax.set("newPwd");
    ajax.set("rePwd");
    ajax.start();

};*/



$(function () {
    Aexit.initValidator("userInfoForm", UserInfoDlg.validateFields);
    orgZtreeInit();//初始化组织机构
});

/**
 * 清除数据
 */
UserInfoDlg.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.set = function (key, val) {
    this.userInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function () {
    parent.layer.close(window.parent.UserTable.layerIndex);
};

/**
 * 打开组织机构ztree页面
 */
UserInfoDlg.openOrgZtree = function(){
    var index = layer.open({
        type: 2,
        title: '组织机构',
        area: ['600px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/user/open_org_ztree',
        btn: ['确定','关闭'],
        yes: function(index){
            //当点击‘确定’按钮的时候，获取弹出层返回的值
            var res = window["layui-layer-iframe" + index].callbackParam();
            //打印返回的值，看是否有我们想返回的值。
            console.log(res);
            //最后关闭弹出层
            layer.close(index);
        }
    });
};

/**
 * 弹出层关闭回调
 */
function callbackParam(){
    parent.document.getElementById("orgName").value = UserInfoDlg.orgName;
    parent.document.getElementById("orgId").value = UserInfoDlg.orgId;
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
    UserInfoDlg.orgId = treeNode.id;
    UserInfoDlg.orgName = treeNode.text;
}