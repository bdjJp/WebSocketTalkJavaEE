/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
               
            var socket;
            
            $(document).ready(
            function(){
                
                socket = new WebSocket("ws://192.168.2.15:8080/WebsocketTalkJavaEE/endpoint");
                
                 //「送信」ボタン押下時
                $("#send").click(
                function(){
                    var name = $("#name").val();
                    var message = $("#message").val();
                    if(!name || !message) {
                        alert("未入力の項目があります。確認してください。");
                        return;
                    }
                    var data    = {name:name, message:message};
                    var json    = JSON.stringify(data);
                    socket.send(json); 
                    $("#message").val(''); 
                });
                
                //endpointでsendObjectされた値をここで取得します。
                socket.onmessage = function(message) {
                   readDataString(message.data);
                };
                
                //変数から値を取り出して文章中に配置します。   
                 var readDataString = function(data){
                    var res;
                    try {
                       var json = JSON.parse(data);
                           res  = "<br><br>" + json.message + "<br><b>[" + json.name + "]</b> ";
                    } catch(e) {
                        res = data;
                    }
                  $("#log").append(res);
                 };
            });      