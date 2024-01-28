let index = {
   parentId: 0,
    init: function(){
        $("#btn-save").on("click",()=>{
            this.save();
        });
        $("#btn-delete").on("click",()=>{
                    this.deleteById();
                });
        $("#btn-update").on("click",()=>{
                            this.update();
                        });
        $("#btn-reply-save").on("click",()=>{
                                   this.replySave();
                               });
        $("button[id^='button-form-reply']").on("click",function() {
                     var newParentId = $(this).prevAll("input[type='hidden']").first().val();
                      if (index.parentId !== newParentId) {
                                     // 새로운 parentId가 이전과 다를 때만 실행
                                     index.parentId = newParentId;


                    console.log("showReplyForm - parentId:", index.parentId);


                    $("#reply-form").show(); // 대댓글 폼을 나타나게 함
console.log("before-showReplyForm - parentId:", index.parentId);
                }
                });
        $("#btn-reply-sub-save").on("click",()=>{
                                           index.replySubSave();
                                       });

    },

    save: function(){

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };


        $.ajax({

            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            //성공
            alert("글쓰기가 완료되었습니다.");
            location.href="/";
        }).fail(function(error){
            //실패
            alert(JSON.stringify(error));
        });
    },

     deleteById: function(){
            let id = $("#id").text();

            $.ajax({
                type: "DELETE",
                url: "/api/board/" + id,
                dataType: "json"
            }).done(function(resp){
                //성공
                alert("삭제가 완료되었습니다.");
                location.href="/";
            }).fail(function(error){
                //실패
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

                    type: "PUT",
                    url: "/api/board/" + id,
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json"
                }).done(function(resp){
                    //성공
                    alert("글수정이 완료되었습니다.");
                    location.href="/";
                }).fail(function(error){
                    //실패
                    alert(JSON.stringify(error));
                });
            },

            replySave: function(){

                    let data = {
                        userId: $("#userId").val(),
                        boardId: $("#boardId").val(),
                        content: $("#reply-content").val()
                    };




                    $.ajax({

                        type: "POST",
                        url: `/api/board/${data.boardId}/reply`,
                        data: JSON.stringify(data),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json"
                    }).done(function(resp){
                        //성공
                        alert("댓글작성이 완료되었습니다.");
                        location.href=`/board/${data.boardId}`;
                    }).fail(function(error){
                        //실패
                        alert(JSON.stringify(error));
                    });
                },


//일단 form에서는 값이 있는데 이 함수로 넘어오면서 값이 정의돼지않은것 같다.

            replySubSave: function(){
console.log("replySubSave - parent-reply-id:", index.parentId);
                                    let data = {
                                        userId: $("#sub-userId").val(),
                                        boardId: $("#sub-boardId").val(),
                                        parentId: index.parentId,
                                        content: $("#reply-sub-content").val()
                                        };
                                        console.log("Inside replySubSave - Before setting parentId:", index.parentId);


                                    $.ajax({

                                        type: "POST",
                                        url: `/api/board/${data.boardId}/reply/sub`,
                                        data: JSON.stringify(data),
                                        contentType: "application/json; charset=utf-8",
                                        dataType: "json"
                                    }).done(function(resp){
                                        //성공
                                        alert("대댓글작성이 완료되었습니다.");
                                        location.href=`/board/${data.boardId}`;
                                    }).fail(function(error){
                                        //실패
                                        alert(JSON.stringify(error));
                                    });
                                },


}

index.init();