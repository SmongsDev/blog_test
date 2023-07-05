const gitToken = "ghu_S0PEqsQLDk0PgXSDDooMsdzBk7LGAf1DhIFr";

let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{ // this를 바인딩하기 위해서
            this.save();
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
            alert("회원가입이 완료되었습니다.");
            // console.log(resp);
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();