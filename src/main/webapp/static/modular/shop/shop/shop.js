/**
 * 订单业务管理初始化
 */
var Shop = {
    id: "ShopTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Shop.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Shop.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Shop.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加订单业务
 */
Shop.openAddShop = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单业务',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/shop/shop_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看订单业务详情
 */
Shop.openShopDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '订单业务详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/shop/shop_update/' + Shop.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除订单业务
 */
Shop.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/shop/delete", function (data) {
            Feng.success("删除成功!");
            Shop.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("shopId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询订单业务列表
 */
Shop.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Shop.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Shop.initColumn();
    var table = new BSTable(Shop.id, "/shop/list", defaultColunms);
    table.setPaginationType("client");
    Shop.table = table.init();
});
