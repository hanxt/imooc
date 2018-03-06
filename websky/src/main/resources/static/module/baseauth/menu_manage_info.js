/**
 * 菜单详情对话框（可用于添加和修改对话框）
 */
var MenuInfoDlg = {
    menuInfoData: {},  //表单数据
    validateFields: {//表单元素校验规则
        menuName: {
            validators: {
                notEmpty: {
                    message: '菜单名称不能为空'
                }
            }
        },
        chartName: {
            validators: {
                notEmpty: {
                    message: '图表名称不能为空'
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: '菜单名称不能为空'
                }
            }
        },
        menuPid: {
            validators: {
                notEmpty: {
                    message: '父菜单ID不能为空'
                }
            }
        },
        level: {
            validators: {
                notEmpty: {
                    message: '菜单层级不能为空'
                }
            }
        }
    }
};



/**
 * 收集数据
 */
MenuInfoDlg.collectData = function () {
    this.set('menuName').set('chartName').set('name').set('menuPid').set('url').set('level').set('id');
};

/**
 * 验证校验
 */
MenuInfoDlg.validate = function () {
    $('#menuInfoForm').data("bootstrapValidator").resetForm();
    $('#menuInfoForm').bootstrapValidator('validate');
    return $("#menuInfoForm").data('bootstrapValidator').isValid();
};


MenuInfoDlg.addChartSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    $.ajax({
        url: Aexit.ctxPath + "/u_report/addchart",
        data: this.menuInfoData,
        type: "post",
        dataType: "json", //表示返回值类型，不必须
        success:function (data) {
            if(data.code == 0) {
                window.parent.Menu.table.refresh();
                MenuInfoDlg.close();
            } else {
                Aexit.error(data.message);
            }
        }
    })
};


/**
 * 提交添加菜单
 */
MenuInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    $.ajax({
        url: Aexit.ctxPath + "/menu/add",
        data: JSON.stringify(this.menuInfoData),
        type: "post",
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        success:function (data) {
            if(data.code == 0) {
                window.parent.Menu.table.refresh();
                MenuInfoDlg.close();
            } else {
                Aexit.error(data.message);
            }
        }
    })
};

/**
 * 提交修改
 */
MenuInfoDlg.editSubmit = function () {
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    $.ajax({
        type: 'PUT',
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        url: Aexit.ctxPath + "/menu/edit",
        data: JSON.stringify(this.menuInfoData),
        success: function (data) {
            if (data.code == 0) {
                Aexit.success("修改成功!");
                if (window.parent.Menu != undefined) {
                    window.parent.Menu.table.refresh();
                    MenuInfoDlg.close();
                }
            } else {
                Aexit.error("修改失败!" + data.message + "!");
            }
        }
    });
};

$(function () {
    Aexit.initValidator("menuInfoForm", MenuInfoDlg.validateFields);
    menuZtreeInit();//初始化菜单树
});

/**
 * 清除数据
 */
MenuInfoDlg.clearData = function () {
    this.menuInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MenuInfoDlg.set = function (key, val) {
    this.menuInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MenuInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
MenuInfoDlg.close = function () {
    parent.layer.close(window.parent.Menu.layerIndex);
};

/**
 * 打开父菜单ztree页面
 */
MenuInfoDlg.openMenuZtree = function(){
    var index = layer.open({
        type: 2,
        title: '菜单信息',
        area: ['600px', '360px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/menu/open_menu_ztree',
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
    parent.document.getElementById("menuName").value = MenuInfoDlg.menuName;
    parent.document.getElementById("menuPid").value = MenuInfoDlg.menuPid;
}

/**
 *  父菜单ztree配置
 */
var setting = {
    data:{
        key:{
            name: "text"
        }
    },
    callback: {
        onCheck:menuTreeCheck
    },
    check: {
        enable: true,
        chkStyle: "radio",
        radioType: "all"
    }

};

/**
 *  初始化弹出层父菜单ztree
 */
function menuZtreeInit(){
    jQuery.ajax({
        url:Aexit.ctxPath + "/menu/ztree",
        dataType:'json',
        type:'GET',
        success:function(zNodes){
            jQuery.fn.zTree.init($("#menuZtree"), setting, zNodes);
        }
    });
}

/**
 *  弹出层组织机构ztree页面单选按钮选中事件
 */
function menuTreeCheck (event, treeId, treeNode) {
    MenuInfoDlg.menuPid = treeNode.id;
    MenuInfoDlg.menuName = treeNode.text;
}