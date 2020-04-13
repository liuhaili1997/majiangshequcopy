/**
 * create by liuhaili on 2020-4-10
 * */

function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if (!content) {
        alert("你的评论是空的哦，请您啊，知无不言言无不尽！！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
                "parentId": questionId,
                "content": content,
                "type": 0
            }),
        success: function (response) {
            if (response.code == 200) {
                /*$("#comment_section").hide()*/
                window.location.reload();
            } else {
                if (response.code == 3001){
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=f08ab0ddf1754b1b31de&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                        window.localStorage.setItem("closeable", true);
                    }
                } else {
                    alert(response.message)
                }
            }
            console.log(response)
        },
        dataType: "json",
        contentType: 'application/json'
    });

}