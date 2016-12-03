
Date.prototype.format = function(format)//格式化日期数据
{
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) 
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) 
        if (new RegExp("(" + k + ")").test(format)) 
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};
/*var num=0;
 var max=100;
 setInterval(function(){
 num++;
 document.getElementById("test").innerHTML=new Date().format('yyyy-MM-dd hh:mm:ss');
 if(num==max){
 alert("timeout");
 }
 },1000);*/
//跨浏览器兼容的添加事件
//跨浏览器添加事件绑定
function addEvent(obj, type, fn){
    if (typeof obj.addEventListener != 'undefined') {
        obj.addEventListener(type, fn, false);
    }
    else {
        //创建一个存放事件的哈希表(散列表)
        if (!obj.events) 
            obj.events = {};
        //第一次执行时执行
        if (!obj.events[type]) {
            //创建一个存放事件处理函数的数组
            obj.events[type] = [];
            //把第一次的事件处理函数先储存到第一个位置上
            if (obj['on' + type]) 
                obj.events[type][0] = fn;
        }
        else {
            //同一个注册函数进行屏蔽，不添加到计数器中
            if (addEvent.equal(obj.events[type], fn)) 
                return false;
        }
        //从第二次开始我们用事件计数器来存储
        obj.events[type][addEvent.ID++] = fn;
        //执行事件处理函数
        obj['on' + type] = addEvent.exec;
    }
}

//为每个事件分配一个计数器
addEvent.ID = 1;

//执行事件处理函数
addEvent.exec = function(event){
    var e = event || addEvent.fixEvent(window.event);
    var es = this.events[e.type];
    for (var i in es) {
        es[i].call(this, e);
    }
};

//同一个注册函数进行屏蔽
addEvent.equal = function(es, fn){
    for (var i in es) {
        if (es[i] == fn) 
            return true;
    }
    return false;
};

//把IE常用的Event对象配对到W3C中去
addEvent.fixEvent = function(event){
    event.preventDefault = addEvent.fixEvent.preventDefault;
    event.stopPropagation = addEvent.fixEvent.stopPropagation;
    event.target = event.srcElement;
    return event;
};

//IE阻止默认行为
addEvent.fixEvent.preventDefault = function(){
    this.returnValue = false;
};

//IE取消冒泡
addEvent.fixEvent.stopPropagation = function(){
    this.cancelBubble = true;
};


//跨浏览器删除事件
function removeEvent(obj, type, fn){
    if (typeof obj.removeEventListener != 'undefined') {
        obj.removeEventListener(type, fn, false);
    }
    else {
        if (obj.events) {
            for (var i in obj.events[type]) {
                if (obj.events[type][i] == fn) {
                    delete obj.events[type][i];
                }
            }
        }
    }
}

/*得到屏幕大小,兼容浏览器*/
function getScreenSize(){
    if (typeof window.innerHeight != "undefined") {
        return {
            width: window.innerWidth,
            height: window.innerHeight
        };
    }
    else {
        return {
            width: document.documentElement.clientWidth,
            height: document.body.scrollTop + document.body.clientHeight
        };
    }
};


/*取消浏览器默认行为*/
function preDef(event){
    event = event || window.event;
    if (typeof event.preventDefault != "undefined") {//W3C
        event.preventDefault();
    }
    else {
        event.returnValue = false;//IE
    }
};


//使每次获取的base都是最新的
var $ = function(){
    return new base();
};

function base(){
    this.elements = [];
    
    this.getId = function(id){
        this.elements.push(document.getElementById(id));
        return this;
    };
    
    this.getName = function(name){
        var names = document.getElementsByName(name);
        for (var i = 0; i < names.length; i++) {
            this.elements.push(names[i]);
        };
        return this;
    };
    
    this.getTagName = function(tagName){
        var names = document.getElementsByTagName(tagName);
        for (var i = 0; i < names.length; i++) {
            this.elements.push(names[i]);
        };
        return this;
    };
    
    this.getClass = function(className, id){
        var node = null;
        if (arguments.length == 2) {
            node = document.getElementById();
        }
        else {
            node = document;
        }
        var clazz = node.getElementsByClassName(className);
        for (var i = 0; i < clazz.length; i++) {
            this.elements.push(clazz[i]);
        }
        return this;
    };
}

//给对象增加方法,是在他的原型上增加,该方法是给获取到的标签增加css样式
base.prototype.css = function(attr, value){
    for (var i = 0; i < this.elements.length; i++) {
        if (arguments.length == 1) {
            if (typeof window.getComputedStyle != "undefined") {
                return window.getComputedStyle(this.elements[i], null)[attr];
            }
            else 
                if (typeof this.elements[i] != "undefined") {
                    return this.elements[i].currentStyle(attr);
                }
        }
        this.elements[i].style[attr] = value;
    };
    return this;
};

//将innerHTML中的内容替换成指定内容
base.prototype.html = function(str){
    for (var i = 0; i < this.elements.length; i++) {
        if (arguments.length == 0) {
            return this.elements[i].innerHTML;
        }
        this.elements[i].innerHTML = str;
    };
    return this;
};

//点击事件
base.prototype.click = function(fn){
    for (var i = 0; i < this.elements.length; i++) {
        addEvent(this.elements[i], "click", fn);
    };
    return this;
};

//添加class
base.prototype.addClass = function(className){
    for (var i = 0; i < this.elements.length; i++) {
        if (!this.elements[i].className.match(new RegExp("(\\s|^)" + className + "(\\s|$)"))) {
            this.elements[i].className += " " + className;
        }
    }
    return this;
};

//移除class中的指定数据
base.prototype.removeClass = function(className){
    for (var i = 0; i < this.elements.length; i++) {
        if (this.elements[i].className.match(new RegExp("(\\s|^)" + className + "\\s|$"))) {
            this.elements[i].className = this.elements[i].className.replace(new RegExp("(\\s|^)" + className + "(\\s|$)"), "");
        }
    }
    return this;
};

//鼠标移入移除动画方法,需要配合hide和show使用
base.prototype.hover = function(over, out){
    for (var i = 0; i < this.elements.length; i++) {
        addEvent(this.elements[i], "mouseover", over);
        addEvent(this.elements[i], "mouseout", out);
    }
    return this;
};

//使指定的元素显现
base.prototype.show = function(){
    for (var i = 0; i < this.elements.length; i++) {
        this.elements[i].style.display = "block";
    }
    return this;
};

//使指定的元素隐藏
base.prototype.hide = function(){
    for (var i = 0; i < this.elements.length; i++) {
        this.elements[i].style.display = "none";
    }
    return this;
};

//使指定的元素始终位于浏览器中央
base.prototype.alwaysCenter = function(){
    for (var i = 0; i < this.elements.length; i++) {
        width = this.elements[i].offsetWidth;
        height = this.elements[i].offsetHeight;
        var left = (document.documentElement.clientWidth - width) / 2;
        var top = (document.body.scrollTop + document.body.clientHeight - height) / 2;
		
        this.elements[i].style.top = top + "px";
        this.elements[i].style.left = left + "px";
    }
    
    return this;
};
//屏幕大小变化时,窗口仍在可见范围内
base.prototype.resize = function(fn){
    for (var i = 0; i < this.elements.length; i++) {
        var ele = this.elements[i];
        addEvent(window, "resize", function(){
            fn();
            if (ele.offsetLeft > getScreenSize().width - ele.offsetWidth) {
                ele.style.left = (getScreenSize().width - ele.offsetWidth) + "px";
            }
            if (ele.offsetTop > getScreenSize().height - ele.offsetHeight) {
                ele.style.top = (getScreenSize().height - ele.offsetHeight) + "px";
            }
        });
    }
    return this;
};

/*锁屏,css中要设置background:#000;
 z-index:9998;   值越高,会浮现在上层
 filter:alpha(opacity=30);  ie支持,透明度
 opacity:0.3;               其他浏览器支持 */
base.prototype.lock = function(){
    for (var i = 0; i < this.elements.length; i++) {
        var left = getScreenSize().width + "px";
        var top = getScreenSize().height + "px";
        this.elements[i].style.width = left;
        this.elements[i].style.height = top;
        this.elements[i].style.display = "block";
        
        document.documentElement.style.overflow = "hidden"; //隐藏滚动条
        addevent(window, "scroll", lockScroll);
    }
    
    return this;
};


//滚动条锁定
function lockScroll(){
    document.documentElement.scrollTop = 0;
    document.body.scrollTop = 0;
};

//解除锁屏
base.prototype.unlock = function(){
    for (var i = 0; i < this.elements.length; i++) {
        this.elements[i].style.display = "none";
        document.documentElement.style.overflow = "auto"; //恢复滚动条
        removeEvent(window, "scroll", lockScroll);//恢复滚动条锁定
    }
    return this;
};

//使窗口可以拖拽
base.prototype.drag = function(tagName){
    for (var i = 0; i < this.elements.length; i++) {
        addEvent(this.elements[i], "mousedown", function(e){
            if (trim(this.innerHTML).length == 0) {
                e.preventDefault();
            }
            var width = e.clientX - this.offsetLeft;
            var height = e.clientY - this.offsetTop;
            _this = this;
            if (e.target.tagName == tagName) {
                addEvent(document, "mousemove", move);
                addEvent(document, "mouseup", up);
            }
            else {
                removeEvent(document, "mousemove", move);
                removeEvent(document, "mouseup", up);
            }
            
            
            function move(e){
                var top = e.clientY - height;
                var left = e.clientX - width;
                /*将窗口限制在屏幕可见范围内*/
                if (top <= 0) {
                    top = 0;
                }
                else 
                    if (top >= (getScreenSize().height - _this.offsetHeight)) {
                        top = getScreenSize().height - _this.offsetHeight;
                    }
                if (left <= 0) {
                    left = 0;
                }
                else 
                    if (left >= (getScreenSize().width - _this.offsetWidth)) {
                        left = getScreenSize().width - _this.offsetWidth;
                    }
                _this.style.top = top + "px";
                _this.style.left = left + "px";
                
                /*解决IE当鼠标出浏览器外时,窗口会继续移动*/
                if (typeof _this.setCapture != "undefined") {
                    _this.setCapture();
                }
            };
            
            function up(){
                removeEvent(document, "mousemove", move);
                removeEvent(document, "mouseup", up);
                if (typeof _this.releaseCapture != "undefined") {
                    _this.releaseCapture();
                };
                            };
                    });
    }
    return this;
};

function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
};


























