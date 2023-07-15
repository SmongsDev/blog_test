const gitToken = "ghu_dz4UG5uON1JKXv5fhqlJZWdtWTZM6x2TF25A";

let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{ // this를 바인딩하기 위해서
            this.save();
        });
        $("#btn-update").on("click", ()=>{
            this.update();
        });
    },

    save: function(){
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        // ajax 호출시 default가 비동기 호출
        $.ajax({
            type: "post",
            url: "/auth/joinProc",
            headers: {"X-Github-Token": gitToken},
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            if(resp.status === 500){
                alert("회원가입에 실패하였습니다.");
            }
            else{
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    
    update: function(){
        let data = {
            id: $('#id').val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({
            type: "put",
            url: "/user",
            headers: {"X-Github-Token": gitToken},
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("회원 수정이 완료되었습니다.");
            // console.log(resp);
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();