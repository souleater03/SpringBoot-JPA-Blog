let index = {
    init: function(){
        $("#btn-save").on("click",()=>{
            this.save();
        }); //on함수 : click이라는 파라미터가 실행되면 뭘할래? 라는것.

    },

    save: function(){
        //alert('user의 save함수 호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };

        //ajax호출시 default가 비동기 호출,가입시 100초가 걸린다고 해도 다 처리됀다.
        $.ajax({
            //회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProc", //컨트롤러 필요하다!
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=utf-8", //body data가 어떤 타입인지?
            dataType: "json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열인데 (생긴게 json이라면) =>javascript 오브젝트로 변경해줌
        }).done(function(resp){
            //성공
            alert("회원가입이 완료되었습니다.");
            location.href="/";
        }).fail(function(error){
            //실패
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
    },
}

index.init();