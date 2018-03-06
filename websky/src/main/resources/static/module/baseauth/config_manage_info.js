/**
 * 系统配置详情对话框（可用于添加和修改对话框）
 */
var ConfigInfoDlg = {
    configInfoData: {},  //表单数据
    validateFields: {
        configGroup: {
            validators: {
                notEmpty: {
                    message: '分组不能为空'
                },
                stringLength: {
                    max: 16,
                    message: '分组过长请重新输入！'
                }
            }

        },
        configKey: {
            validators: {
                notEmpty: {
                    message: '键不能为空'
                },
                stringLength: {
                    max: 16,
                    message: '键过长请重新输入！'
                }
            }
        },
        configValue: {
            validators: {
                notEmpty: {
                    message: '值不能为空'
                },
                stringLength: {
                    max: 128,
                    message: '值过长请重新输入！'
                }
            }
        },
        description: {
            validators: {
                notEmpty: {
                    message: '描述不能为空'
                },
                stringLength: {
                    max: 512,
                    message: '描述过长请重新输入！'
                }
            }
        }//表单元素校验规则
    }
};



/**
 * 收集数据
 */
ConfigInfoDlg.collectData = function () {
    this.set('id').set('configGroup').set('configKey').set('configValue').set('description');
};


/**
 * 验证校验
 */
ConfigInfoDlg.validate = function () {
    $('#configInfoForm').data("bootstrapValidator").resetForm();
    $('#configInfoForm').bootstrapValidator('validate');
    return $("#configInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
ConfigInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    $.ajax({
        url: Aexit.ctxPath + "/config/add",
        data: JSON.stringify(this.configInfoData),
        type: "post",
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        success:function (data) {
            if(data.code == 0) {
                window.parent.ConfigTable.table.refresh();
                ConfigInfoDlg.close();
            } else {
                Aexit.error(data.message);
            }
        }
    })
};

/**
 * 提交修改
 */
ConfigInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    $.ajax({
        type: 'PUT',
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        url: Aexit.ctxPath + "/config/edit",
        data: JSON.stringify(this.configInfoData),
        success: function (data) {
            if (data.code == 0) {
                Aexit.success("修改成功!");
                if (window.parent.ConfigTable != undefined) {
                    window.parent.ConfigTable.table.refresh();
                    ConfigInfoDlg.close();
                }
            } else {
                Aexit.error("修改失败!" + data.message + "!");
            }
        }
    });
};


$(function () {
    Aexit.initValidator("configInfoForm", ConfigInfoDlg.validateFields);

});

/**
 * 清除数据
 */
ConfigInfoDlg.clearData = function () {
    this.configInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConfigInfoDlg.set = function (key, val) {
    this.configInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConfigInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
ConfigInfoDlg.close = function () {
    parent.layer.close(window.parent.ConfigTable.layerIndex);
};


