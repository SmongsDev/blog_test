const gitToken = "ghu_sTbYlcMWzF6wacXFp4uNf0v4Q0mn89165k8j";

let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{ // this를 바인딩하기 위해서
            this.save();
        });
        $("#btn-delete").on("click", ()=>{
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{
            this.update();
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
    }
}

index.init();