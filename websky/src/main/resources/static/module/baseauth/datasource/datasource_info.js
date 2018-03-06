/**
 * 初始化数据源详情对话框
 */
var DatasourceInfoDlg = {
    datasourceInfoData : {},
    validateFields: {//表单元素校验规则
        dbFlag: {
            validators: {
                notEmpty: {
                    message: '唯一标志不能为空'
                }
            }
        },
        dbName: {
            validators: {
                notEmpty: {
                    message: '数据库名称不能为空'
                }
            }
        },
        dbType: {
            validators: {
                notEmpty: {
                    message: '数据库类型不能为空'
                }
            }
        },
        conIp: {
            validators: {
                notEmpty: {
                    message: '链接ip不能为空'
                }
            }
        },
        conPort: {
            validators: {
                notEmpty: {
                    message: '连接端口不能为空'
                }
            }
        }
    }
};

$(function () {
    Aexit.initValidator("datasourceInfoForm", DatasourceInfoDlg.validateFields);
});

/**
 * 验证校验
 */
DatasourceInfoDlg.validate = function () {
    $('#datasourceInfoForm').data("bootstrapValidator").resetForm();
    $('#datasourceInfoForm').bootstrapValidator('validate');
    return $("#datasourceInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
DatasourceInfoDlg.clearData = function() {
    this.datasourceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DatasourceInfoDlg.set = function(key, val) {
    this.datasourceInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DatasourceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DatasourceInfoDlg.close = function() {
    parent.layer.close(window.parent.Datasource.layerIndex);
}

/**
 * 收集数据
 */
DatasourceInfoDlg.collectData = function() {
    this.set('id').set('dbFlag').set('dbName').set('dbType').set('dbRw').set('conUrl').set('conIp').set('conPort').set('conUsername').set('conPassword').set('conTimeout').set('poolMaxtotal').set('poolMaxidle').set('poolMinidle');
}

/**
 * 提交添加
 */
DatasourceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    $.ajax({
        url: Aexit.ctxPath + "/datasource/add",
        data: JSON.stringify(this.datasourceInfoData),
        type: "post",
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        success:function (data) {
            if(data.code == 0) {
                window.parent.Datasource.table.refresh();
                DatasourceInfoDlg.close();
            } else {
                Aexit.error(data.message);
            }
        }
    })
}

/**
 * 提交修改
 */
DatasourceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

       //提交信息
       $.ajax({
           url: Aexit.ctxPath + "/datasource/update",
           data: JSON.stringify(this.datasourceInfoData),
           type: "PUT",
           contentType: "application/json", //必须有
           dataType: "json", //表示返回值类型，不必须
           success:function (data) {
               if(data.code == 0) {
                   window.parent.Datasource.table.refresh();
                   DatasourceInfoDlg.close();
               } else {
                   Aexit.error(data.message);
               }
           }
       })
}

$(function() {

});
