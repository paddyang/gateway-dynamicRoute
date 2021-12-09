//获取url参数
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] === variable){return pair[1];}
    }
    return false;
}
//简单post请求。body={key:value}
function POST(url,body){
    $.ajax({
        url:url,
        type:"post",
        dataType:"json",
        data:JSON.stringify(body),
        headers: {'Content-Type': 'application/json'},
        success: function (data) {
            if (data.status===200) {
                alert("操作成功！");
                window.location.href = "/html/index.html";
            }else {
                alert(data.msg);
                window.location.href = "/html/login.html";
            }
        },
        error: function (data){
            alert("操作失败");
        }
    })
}
//简单get请求。body={key:value}
function GET(url,body){
    $.ajax({
        url:url,
        type:"get",
        dataType:"json",
        data:body,
        headers: {'Content-Type': 'application/json'},
        success: function (data) {
            if (data.status===200) {
                alert("操作成功！");
                window.location.href = "/html/index.html";
            }else {
                alert(data.msg);
                window.location.href = "/html/login.html";
            }
        },
        error: function (data){
            alert("操作失败");
        }
    })
}

