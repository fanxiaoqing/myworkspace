 // -------------------------------------导航下拉-----------------------------------------
function fnNav(){
    var navs = document.getElementsByClassName("navs");

    var changespeed = 25;
    var changedelay = 30;
    /* 循环绑定所有元素的鼠标事件 */
    for(var i = 0; i < navs.length; i++) {
        navs[i].onmouseover = function () {
            var U = this.getElementsByTagName("ul")[0];
            if(U != undefined) {
              startMove(U,{height:50*(U.children.length)-1},5);
            }
          }
        navs[i].onmouseout = function () {
            var U = this.getElementsByTagName("ul")[0];
            if(U != undefined) {
              startMove(U,{height:0},6);
            }
        }
    }
}

fnNav();

// ----------------------------banner------------------------------ 

function fnBanner(carousel,imgList,btn,prev,next) {
  var oDiv=document.getElementById(carousel);
  var oUl=document.getElementsByClassName(imgList)[0];
  var aLi=oUl.getElementsByTagName('li');
  var oBtnUl=document.getElementsByClassName(btn)[0];
  var oBtnPrev=document.getElementById(prev);
  var oBtnNext=document.getElementById(next);

  var nowZIndex=1;
  var now=0;
  var sub=true;

  for(var i=0;i<aLi.length;i++){
    var child_Li=document.createElement("li");
    oBtnUl.appendChild(child_Li);
  }

  var aBtnLi=oBtnUl.getElementsByTagName('li');

  aBtnLi[0].className="indexOn";

  for(var i=0;i<aBtnLi.length;i++){
    aBtnLi[i].index=i;

    aBtnLi[i].onmouseover=function () {
        clearInterval(bTimer);
    }

    aBtnLi[i].onclick=function(){
      var num=this.index;
      tab(num,false);
    }
  }

  aLi[0].style.zIndex=nowZIndex+1;

  function tab(num,sub) {
    
    for(var i=0;i<aLi.length;i++){
        startMove(aLi[i],{opacity:0},10);
    }

    if(sub){
        now--;
    }
    else if(num||num==0){
        now=num;
    }
    else{
        now++;
    }

    if(now<0){
        now=aLi.length-1;
    }
    else if (now>aLi.length-1){
      now=0;
    }
    
    for(var i=0;i<aBtnLi.length;i++){
      aBtnLi[i].className="";
    }

    aBtnLi[now].className="indexOn";

    aLi[now].style.zIndex=nowZIndex++;

    startMove(aLi[now],{opacity:100},10);

  }

  var bTimer=setInterval(tab,3500);

  oBtnNext.onclick=function(){
    tab();
  }

  oBtnPrev.onclick=function(){
    tab(false,sub);
  }

  oBtnPrev.onmouseover=oBtnNext.onmouseover=oUl.onmouseover=function () {
    clearInterval(bTimer);
  }
  oUl.onmouseout=function () {
    bTimer=setInterval(tab,3500);
  }
}
// ----------------------新闻滚动--------------------

function fnNewsList(){
    var oNotices=document.getElementsByClassName('news_box')[0];
    var oUl=oNotices.getElementsByTagName('ul')[0];
    var aLi=oUl.getElementsByTagName('li');

    var now=0;

    if(aLi.length<=5){
      return false;
    }
    oUl.innerHTML+=oUl.innerHTML;

    function move(){
        
        now++;

        oTop=-now*40;

        startMove(oUl,{top:oTop},6,function(){
          if(now>=aLi.length-5){
            now=aLi.length/2-5;
            oUl.style.top=-(now*40)+'px';
          }
        });
      }

    var timer2=setInterval(move,3000);

    oUl.onmouseover=function(){
         clearInterval(timer2);
     }

    oUl.onmouseout=function(){
        if(aLi.length<=5){
          return false;
        }

        timer2=setInterval(move,3000);
    }
  }

// 案例

function fnCase(){
  var oDiv=document.getElementsByClassName('case_box')[0];
  var oUl=oDiv.getElementsByTagName('ul')[0];
  var aLi=oUl.getElementsByTagName('li');
  var CasePrev=document.getElementById('case_prev');
  var CaseNext=document.getElementById('case_next');
  var sub=true;

  oUl.innerHTML+=oUl.innerHTML;

  oUl.style.width=(aLi[0].offsetWidth+12)*aLi.length+'px';
  var now=0;
  var oLeft=aLi[0].offsetWidth+10;

  function move(sub){

    if(sub){
      now--;
    }else{
      now++;
      if(now>=aLi.length/2+1){
        now=1;
        oUl.style.left=0;
      }
    }

    if(now<=0){
      now=aLi.length-5;
      oUl.style.left=-oLeft*now+'px';
      now--;
    }

    startMove(oUl,{left:-oLeft*now},10);
  }

  var timer=setInterval(move,3000)

  CaseNext.onclick=function(){
    move();
  }

  CasePrev.onclick=function(){
    move(sub);
  }

  CasePrev.onmouseover=CaseNext.onmouseover=oDiv.onmouseover=function () {
    clearInterval(timer);
  }
  CasePrev.onmouseout=CaseNext.onmouseout=oDiv.onmouseout=function () {
    timer=setInterval(move,3000);
  }

  for(var i=0;i<aLi.length;i++){
    aLi[i].onmouseover=function () {
      var aDiv=this.getElementsByTagName('div')[0];
      startMove(aDiv,{top:0,opacity:100},5);
    }
    aLi[i].onmouseout=function(){
      var aDiv=this.getElementsByTagName('div')[0];
      startMove(aDiv,{top:192,opacity:0},5);
    }
  }
}

// -------------------二维码----------------

function toTop(){
  var timer = null;
  var oTopBox=document.getElementById('top_btn');
  var oSide=document.getElementById('side');

  oTopBox.onclick = function(){
    cancelAnimationFrame(timer);
    timer = requestAnimationFrame(function fn(){
      var oTop = document.body.scrollTop || document.documentElement.scrollTop;
      if(oTop > 0){
        document.body.scrollTop = document.documentElement.scrollTop = parseInt(oTop - oTop/10);
        timer = requestAnimationFrame(fn);
        startMove(oSide,{height:121},5);
        // oSide.style.height=61+'px';
      }else{
        cancelAnimationFrame(timer);
      }
    });
  }
  
  var scrollFunc=function(){
    var oTop = document.body.scrollTop || document.documentElement.scrollTop;

    // alert(oTop);

    if(oTop>300){
        // alert(oSide.offsetHeight);
        startMove(oSide,{height:180},5);
    }else{
        startMove(oSide,{height:121},5);
    }
  }

  // 注册事件
  if(document.addEventListener){ 
    document.addEventListener('DOMMouseScroll',scrollFunc,false); 
  }//W3C 
  window.onmousewheel=document.onmousewheel=scrollFunc;//IE/Opera/Chrome 
}

toTop();

// 微信二维码

function fnErwm(){ 
  $('#wx_btn').mouseover(function(){
    $("#wx_erwm").css("display","block");
  });

  $('#wx_btn').mouseout(function(){
    $("#wx_erwm").css("display","none");
  });
}

  fnErwm();


var caution=false
function setCookie(name,value,expires,path,domain,secure) 
{
 var curCookie=name+"="+escape(value) +
 ((expires)?";expires="+expires.toGMTString() : "") +
 ((path)?"; path=" + path : "") +
 ((domain)? "; domain=" + domain : "") +
 ((secure)?";secure" : "")
 if(!caution||(name + "=" + escape(value)).length <= 4000)
 {
 document.cookie = curCookie
 }
 else if(confirm("Cookie exceeds 4KB and will be cut!"))
 {
 document.cookie = curCookie
 }
}
function getCookie(name) 
{
 var prefix = name + "="
 var cookieStartIndex = document.cookie.indexOf(prefix)
 if (cookieStartIndex == -1)
 {
 return null
 }    
 var cookieEndIndex=document.cookie.indexOf(";",cookieStartIndex+prefix.length)
 if(cookieEndIndex == -1)
 {
 cookieEndIndex = document.cookie.length
 }
 return unescape(document.cookie.substring(cookieStartIndex+prefix.length,cookieEndIndex))
}
function deleteCookie(name, path, domain) 
{
 if(getCookie(name)) 
 {
 document.cookie = name + "=" + 
 ((path) ? "; path=" + path : "") +
 ((domain) ? "; domain=" + domain : "") +
 "; expires=Thu, 01-Jan-70 00:00:01 GMT"
 }
}
function fixDate(date) 
{
 var base=new Date(0)
 var skew=base.getTime()
 if(skew>0)
 {
 date.setTime(date.getTime()-skew)
 }    
}
var now=new Date()
fixDate(now)
now.setTime(now.getTime()+365 * 24 * 60 * 60 * 1000)
var visits = getCookie("counter")
if(!visits)
{
 visits=1;
}  
else
{
 visits=parseInt(visits)+1;
}  
setCookie("counter", visits, now);
var pv=document.getElementById("pv").innerHTML=visits;
