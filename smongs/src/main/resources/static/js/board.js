const gitToken = "ghu_dz4UG5uON1JKXv5fhqlJZWdtWTZM6x2TF25A";

let index = {
    init: function(){
        const _this = this;
        
        $("#btn-save").on("click", ()=>{ // this를 바인딩하기 위해서
            this.save();
        });
        $("#btn-delete").on("click", ()=>{
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{
            this.update();
        });
        $("#btn-reply-save").on("click", ()=>{
            this.replySave();
        });
    },

    save: function(){
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        // ajax 호출시 default가 비동기 호출
        $.ajax({
            type: "post",
            url: "/api/board",
            headers: {"X-Github-Token": gitToken},
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    deleteById: function(){
        let id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            headers: {"X-Github-Token": gitToken},
            dataType: "json"
        }).done(function(resp, xhr){
            alert("삭제가 완료되었습니다.");
            switch(xhr.status){
                case 201:
                    alert('uploaded!');
                    break;
                case 404:
                    alert('not found');
                    break;
            }
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    
    update: function(){
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "put",
            url: "/api/board/"+id,
            headers: {"X-Github-Token": gitToken},
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    replySave: function(){
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        };
        console.log(data);
        // ajax 호출시 default가 비동기 호출
        $.ajax({
            type: "post",
            url: `/api/board/${data.boardId}/reply`,
            headers: {"X-Github-Token": gitToken},
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("댓글 작성이 완료되었습니다.");
            location.href = `/board/${data.boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    replyDelete: function(boardId, replyId){
        $.ajax({
            type: "delete",
            url: `/api/board/${boardId}/reply/${replyId}`,
            headers: {"X-Github-Token": gitToken},
            dataType: "json"
        }).done(function(resp){
            alert("댓글 삭제 성공하었습니다.");
            location.href = `/board/${boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    replyUpdate: function(){
        let data = {
            userId: $("#userId").val(),
            replyId: $('#replyId').val(),
            boardId: $('#boardId').val(),
            content: $('#reply-content').val()
        };
        if (!data.comment || data.comment.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        }
        let con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            $.ajax({
                type: 'PUT',
                url: `/api/board/${data.boardId}/reply/${data.replyId}`,
                headers: {"X-Github-Token": gitToken},
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function () {
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
    
}
// 댓글 수정 팝업 open
function openReplyUpdatePopup(id) {

    $.ajax({
        url : `/api/board/${boardId}/reply/${replyId}`,
        type : 'get',
        dataType : 'json',
        async : false,
        success : function (response) {
            document.getElementById('modalWriter').value = response.writer;
            document.getElementById('modalContent').value = response.content;
            document.getElementById('commentUpdateBtn').setAttribute('onclick', `updateComment(${id})`);
            layerPop('commentUpdatePopup');
        },
        error : function (request, status, error) {
            console.log(error)
        }
    })
}


// 댓글 수정 팝업 close
function closeReplyUpdatePopup() {
    document.querySelectorAll('#modalContent, #modalWriter').forEach(element => element.value = '');
    document.getElementById('replyUpdateBtn').removeAttribute('onclick');
    layerPopClose('replyUpdatePopup');
}

index.init();