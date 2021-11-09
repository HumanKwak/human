/**
 * reply.js
 * 댓글 구현 클로저를 이용한 모듈
 */
console.log("reply module");

var replyService = (function(){ 
    function add(reply,callback,error){
        console.log("reply.add()");
        $.ajax({
            type : "post",
            url : "/replies/new",
            data : JSON.stringify(reply),
            contentType : "application/json; charset=utf-8",
            success : function(data){
                if(callback)
                callback(data);
            },
            error:function(xhr, stat, er){
                if(error){
                    error(er);
                }
            }
        })
    }

    function getList(param,callback,error){
        console.log("reply.getList()");
        var bno= param.bno; //고정값
        var amount=param.amount || 10;
        var lastRno=param.lastRno || 0;
        var url= '/replies/' + bno + "/" + amount + "/" +   lastRno;

        $.ajax({
            type: 'get',
            dataType : 'json',
            url : url,
            success : function(data){
                if(callback)
                callback(data);
            }
        })
    }
    function remove(rno, callback, error){
        console.log("reply.remove()");
        var url = '/replies/' + rno;

        $.ajax(url, {
            type : "delete"
        }).done(function(data){
                if(callback)
                callback(data);
            })
        }
        // $.ajax(url,{
        //     type : "delete",
        //     success : function(data){
        //         if(callback)
        //         callback(data);
        //     }
        // })
        function modify(reply, callback, error){
            console.log("reply.modify()")
            var url = '/replies/' + reply.rno;
            $.ajax(url,{
                type : "put",
                data : JSON.stringify(reply),
                contentType : "application/json; charset=utf-8",
                success : function(data){
                    if(callback)
                    callback(data);
                }
            })
        }
         function get(rno, callback, error){
            console.log("reply.get()");
            var url = '/replies/' + rno;
            $.getJSON(url,function(data){
                if(callback)
                callback(data);
            });
        }
        function displayTime(timevalue){
            return moment().diff(moment(timevalue),'days') < 1 ? moment(timevalue).format('HH:mm:ss') : moment(timevalue).format('YY/MM/DD');
        }
        return{
            add:add,
            getList:getList,
            remove:remove,
            modify:modify,
            get:get,
            displayTime:displayTime
        } 
    
    })();
   
