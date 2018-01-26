/**
 * 初始化订单业务详情对话框
 */
var ShopInfoDlg = {
    shopInfoData : {}
};

/**
 * 清除数据
 */
ShopInfoDlg.clearData = function() {
    this.shopInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShopInfoDlg.set = function(key, val) {
    this.shopInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShopInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ShopInfoDlg.close = function() {
    parent.layer.close(window.parent.Shop.layerIndex);
}

/**
 * 收集数据
 */
ShopInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
ShopInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/shop/add", function(data){
        Feng.success("添加成功!");
        window.parent.Shop.table.refresh();
        ShopInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shopInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ShopInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/shop/update", function(data){
        Feng.success("修改成功!");
        window.parent.Shop.table.refresh();
        ShopInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shopInfoData);
    ajax.start();
}

$(function() {

});
