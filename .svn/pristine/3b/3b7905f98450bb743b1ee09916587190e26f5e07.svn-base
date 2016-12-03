//当前
var cur_id = "", cur_expand = "";
var flag = 0, sflag = 0;
//-------- 菜单点击事件 -------
function openSubMenu(id) {
    var targetSubMenu, targetelement;
    var strbuf;
    var el = $(id);
    if (!el)
        return;
    //-------- 如果点击了展开或收缩按钮---------
    targetSubMenu = el.id + "Menu";//子菜单的ID
    targetElement = $(targetSubMenu);

    //前一个折叠的。
    var expandUL = $(cur_expand + "Menu");
    var expandLink = $(cur_expand);

    if (targetElement.style.display == "none")//子菜单是隐藏的状态，进行打开操作
    {
        //把上一次打开的进行折叠
        if (expandUL && expandLink) {
            expandLink.className = "";
            expandUL.style.display = 'none';
        }
        //然后把点击传进来的ID赋值给Current
        cur_expand = el.id;

        //设置为active状态
        el.className = "active";
        targetElement.style.display = '';
        menu_flag = 0;
        $(el.id + "Img").src = "../img/turnRight.png";
    }
    else {//子菜单是打开的状态，进行关闭操作
        el.className = "";
        targetElement.style.display = "none";
        menu_flag = 1;
        $(el.id + "Img").src = "../img/turnDown.png";
        //得到所有的a标签
        var links = document.getElementsByTagName("a");
        for (i = 0; i < links.length; i++) {
            el = links[i];
            if (el.parentNode.className.toUpperCase() == "L1"
                && el.className == "active") {
                menu_flag = 0;
                break;
            }
        }
    }
}
function openPage(address) {
    $("rightSource").src = address;
}



//ags.endWith("abc");
String.prototype.endWith = function (str) {
    var reg = new RegExp(str + "$");
    return reg.test(this);
}
var $ = function (id) {
    return document.getElementById(id);
};
//$('rightSource').load(function () { //方法2
//    var iframeHeight = $(this).contents().height();
//    $(this).height(iframeHeight + 'px');
//});