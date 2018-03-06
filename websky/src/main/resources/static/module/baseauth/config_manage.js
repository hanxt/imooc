/**
 * 系统管理--系统配置的单例对象
 */
var ConfigTable = {
    id: "configTable",//表格id
    seItem: null,	//被选中的条目
    table: null,    //bootstrap-table对象
    layerIndex: -1 //弹出层标记
};

/**
 * 初始化表格的列
 */
ConfigTable.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '分组', field: 'configGroup', align: 'center', valign: 'middle', sortable: true},
        {title: '键', field: 'configKey', align: 'center', valign: 'middle', sortable: true},
        {title: '值', field: 'configValue', align: 'center', valign: 'middle', sortable: true},
        {title: '描述', field: 'description', align: 'center', valign: 'middle', sortable: true}];
    return columns;
};


/**
 * 点击添加系统配置信息时
 */
ConfigTable.openAddInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统配置信息',
        area: ['800px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/config/viewadd'
    });
    this.layerIndex = index;

};


/**
 * 检查是否选中
 */
ConfigTable.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Aexit.info("请先选中表格中的某一记录！");
        return false;
    } else {
        ConfigTable.seItem = selected[0];
        return true;
    }
};

/**
 * 删除配置信息
 */
ConfigTable.delConfig = function () {
    if (this.check()) {
        var operation = function(){
            var id = ConfigTable.seItem.id;

            $.post(Aexit.ctxPath + '/config/delete/' + id,
                {_method: "DELETE"},
                function(data){
                    if(data.code == 0) {
                        Aexit.success("删除成功!");
                        ConfigTable.table.refresh();
                    } else {
                        Aexit.error("删除失败!" + data.message + "!");
                    }
            });
        };

        Aexit.confirm("是否删除这条系统配置信息?",operation);
    }
};


/**
 * 点击修改按钮时
 * @param configid configid
 */
ConfigTable.openEditConfig= function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑管理员',
            area: ['800px', '300px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Aexit.ctxPath + '/config/viewedit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};


$(function () {
    var defaultColunms = ConfigTable.initColumn();
    var table = new BSTable(ConfigTable.id, "/config/list", defaultColunms,"GET");
    table.setPaginationType("client");
    ConfigTable.table = table.init();

});

