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
            url: "/api/user",
            headers: {"X-Github-Token": "ghu_wQd42mcv7t8KQICZF2KzXz3XYM9e9337r9KU"},
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            console.log(resp);
            // location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();