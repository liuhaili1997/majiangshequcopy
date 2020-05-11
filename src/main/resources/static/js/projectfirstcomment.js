/**
 * create by liuhaili on 2020-4-10
 * */

/**
 * 此方法用于接受参数，然后显示回复的评论 一级
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    commentToTarget(questionId, 0, content);

}

/**
 * 将查询二级评论和回复问题的评论进行整合成一个方法  二级
 * type：1：表示评论 0：表示的是问题
 */
function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    commentToTarget(commentId, 1, content);
}


function commentToTarget(targetId, type, content) {
    if (!content) {
        alert("你的评论是空的哦，请您啊，知无不言言无不尽！！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                /*$("#comment_section").hide()*/
                window.location.reload();
            } else {
                if (response.code == 3001) {
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



/**
 * 动态的显示二级评论
 * */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comment = $("#comment-" + id);
    //获取二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折叠二级评论
        comment.removeClass("in");
        //折叠之后移除这个状态标志
        e.removeAttribute("data-collapse");
        //折叠之后的标签，不显示蓝色
        e.classList.remove("active")
    } else {
        var subCommentContainer = $("#comment-" + id);
        // 获取子元素的数量，大于1就不重新加载
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comment.addClass("in");
            //标记二级评论展开的状态
            e.setAttribute("data-collapse", "in");
            //展开二级目录之后的标签，icon显示蓝色
            e.classList.add("active");
        } else {
            //点击二级评论的时候先展示已有的评论
            $.getJSON("/comment/" + id, function (data) {
                console.log(data);
                $.each(data.data.reverse(), function (index,comment) {

                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded user-img",
                        "src": comment.user.avatar
                    }));

                    var userNameElement = $("<span/>", {
                        "class":"comment-user-sub",
                        html: comment.user.name
                    });
                    var timeElement = $("<span/>", {
                        "class": "time-comment",
                        html: "发布时间："
                    }).append($("<span/>", {
                        html: moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
                    }));
                    var contentElement = $("<div/>", {
                        html:comment.content
                    });
                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h6/>", {
                        "class": "media-heading"
                    })).append(userNameElement)
                        .append(timeElement)
                        .append(contentElement);


                    var mediaElement = $("<div/>", {
                        "class": "media initiator-body comment-img",
                    }).append(mediaLeftElement)
                        .append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-content",
                    }).append(mediaElement);
                    subCommentContainer.prepend(commentElement);
                });


                //展开二级评论
                comment.addClass("in");
                //标记二级评论展开的状态
                e.setAttribute("data-collapse", "in");
                //展开二级目录之后的标签，icon显示蓝色
                e.classList.add("active")

            });
        }
    }

}

/**
 * tag的实现点击就显示在输入框中
 */
function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    /*判断tag是否已经存在标签中了*/
    if (previous.indexOf(value) == -1) {
        //是否有原有的标签
        if (previous) {
            /*有tag，将新的添加在后面*/
            $("#tag").val(previous + ',' + value);
        } else {
            /*没有tag，将其作为第一个tag*/
            $("#tag").val(value)
        }
    }
}

/**
 * 点击标签框的时候就展示tag
 */
function showSelectTag() {
    $("#select-tag").show();
}

/**
* 回显照片
* */
//图片回显:
function previewImg(file) {
    $("#imgHidden").css("display", "block");
    var prevDiv = document.getElementById('preview');
    if (file.files && file.files[0]) {
        var reader = new FileReader();
        reader.onload = function(evt) {
            prevDiv.innerHTML = '<img style="width: 100px;height: 100px;" src="' + evt.target.result + '" />';
        }
        reader.readAsDataURL(file.files[0]);
    } else {
        prevDiv.innerHTML = '<div class="img" style="width: 100px;height:100px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' +
            file.value + '\'"></div>';
    }
}

/*register*/
/*下面的方法用于提示前端什么没有填写    注册界面*/
function registerVerify() {
    var errorInformation = $("#registerError").val();
    if (errorInformation) {
        alert(errorInformation);
        return;
    }
}
/*login*/
function loginVerify() {
    var errorInformation = $("#loginError").val();
    if (errorInformation) {
        alert(errorInformation);
        return;
    }
}
