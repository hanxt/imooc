/**
 * 字典详情对话框（可用于添加和修改对话框）
 */
var DictionaryInfoDlg = {
    dictionaryInfoData: {},// 表单数据
    validateFields: {
        dicCode: {
            validators: {
                notEmpty: {
                    message: '字典编码不能为空'
                }
            }
        },// 表单元素校验规则
        dicClass: {
            validators: {
                notEmpty: {
                    message: '类名不能为空'
                }
            }
        }, // 表单元素校验规则
        dicProperty: {
            validators: {
                notEmpty: {
                    message: '属性名不能为空'
                }
            }
        },// 表单元素校验规则
        dicValue: {
            validators: {
                notEmpty: {
                    message: '数据库存值不能为空'
                }
            }
        },// 表单元素校验规则
        dicName: {
            validators: {
                notEmpty: {
                    message: '字面名称不能为空'
                }
            }
        }
    }
};



/**
 * 收集数据
 */
DictionaryInfoDlg.collectData = function () {
    this.set('id').set('dicCode').set('dicClass').set('dicProperty').set('dicValue').set('dicName').
    set('remark');
};


/**
 * 验证校验
 */
DictionaryInfoDlg.validate = function () {
    $('#dictionaryInfoForm').data("bootstrapValidator").resetForm();
    $('#dictionaryInfoForm').bootstrapValidator('validate');
    return $("#dictionaryInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加字典
 */
DictionaryInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    $.ajax({
        url: Aexit.ctxPath + "/dictionary/add",
        data: JSON.stringify(this.dictionaryInfoData),
        type: "post",
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        success:function (data) {
            if(data.code == 0) {
                window.parent.DictionaryTable.table.refresh();
                DictionaryInfoDlg.close();
            } else {
                Aexit.error(data.message);
            }
        }
    })
};

/**
 * 提交修改
 */
DictionaryInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    $.ajax({
        type: 'PUT',
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        url: Aexit.ctxPath + "/dictionary/edit",
        data: JSON.stringify(this.dictionaryInfoData),
        success: function (data) {
            if (data.code == 0) {
                Aexit.success("修改成功!");
                if (window.parent.DictionaryTable != undefined) {
                    window.parent.DictionaryTable.table.refresh();
                    DictionaryInfoDlg.close();
                }
            } else {
                Aexit.error("修改失败!" + data.message + "!");
            }
        }
    });
};


$(function () {
    Aexit.initValidator("dictionaryInfoForm", DictionaryInfoDlg.validateFields);

});

/**
 * 清除数据
 */
DictionaryInfoDlg.clearData = function () {
    this.dictionaryInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DictionaryInfoDlg.set = function (key, val) {
    this.dictionaryInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DictionaryInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
DictionaryInfoDlg.close = function () {
    parent.layer.close(window.parent.DictionaryTable.layerIndex);
};


