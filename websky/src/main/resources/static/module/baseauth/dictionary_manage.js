/**
 * 系统管理--字典管理的单例对象
 */
var DictionaryTable = {
    id: "dictionaryTable",//表格id
    seItem: null,	//被选中的条目
    table: null,    //bootstrap-table对象
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DictionaryTable.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '字典编码', field: 'dicCode', align: 'center', valign: 'middle', sortable: true},
        {title: '类名', field: 'dicClass', align: 'center', valign: 'middle', sortable: true},
        {title: '属性名', field: 'dicProperty', align: 'center', valign: 'middle', sortable: true},
        {title: '数据库存值', field: 'dicValue', align: 'center', valign: 'middle', sortable: true},
        {title: '字面名称', field: 'dicName', align: 'center', valign: 'middle', sortable: true},
        {title: '说明', field: 'remark', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};


/**
 * 点击添加字典时
 */
DictionaryTable.openAddRole = function () {
    var index = layer.open({
        type: 2,
        title: '添加角色',
        area: ['800px', '350px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Aexit.ctxPath + '/dictionary/viewadd'
    });
    this.layerIndex = index;

};


/**
 * 检查是否选中
 */
DictionaryTable.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Aexit.info("请先选中表格中的某一记录！");
        return false;
    } else {
        DictionaryTable.seItem = selected[0];
        return true;
    }
};

/**
 * 删除字典
 */
DictionaryTable.delDictionary = function () {
    if (this.check()) {
        var operation = function(){
            var id = DictionaryTable.seItem.id;

            $.post(Aexit.ctxPath + "/dictionary/delete/" +id ,
                {_method: "DELETE"},
                function(data){
                    if(data.code == 0) {
                        Aexit.success("删除成功!");
                        DictionaryTable.table.refresh();
                    } else {
                        Aexit.error("删除失败!" + data.message + "!");
                    }
            });
        };

        Aexit.confirm("是否删除该字典" + DictionaryTable.seItem.dicClass + "?",operation);
    }
};


/**
 * 点击修改按钮时
 * @param id 字典id
 */
DictionaryTable.openEditRole = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑字典',
            area: ['800px', '350px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Aexit.ctxPath + '/dictionary/viewedit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 重置
 */
DictionaryTable.resetSearch = function () {
    $("#dicClass").val("");
    $("#dicValue").val("");
    DictionaryTable.search();
};

/**
 * 搜索
 */
DictionaryTable.search = function () {
    var queryData = {};
    queryData['dicClass'] = $("#dicClass").val();
    queryData['dicValue'] = $("#dicValue").val();

    DictionaryTable.table.refresh({query: queryData});
};


$(function () {
    var defaultColunms = DictionaryTable.initColumn();
    var table = new BSTable(DictionaryTable.id, "/dictionary/list", defaultColunms,"GET");
    table.setPaginationType("client");
    DictionaryTable.table = table.init();

});
