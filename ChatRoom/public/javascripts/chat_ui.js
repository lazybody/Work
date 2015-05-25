/**
 * Created by wanghui21 on 2015/4/28.
 */
function divEscapedContentElement(message) {
    return $('<div></div>').text(message);
}

function divSystemContentElement(message){
    return $('<div></div>').html('<i>'+message+'</i>');
}

function processUserInput(chatApp,socker) {
    var message = $('#send-message').val();
    var systemMessage;
    if(message.charat(0)=='/'){
        systemMessage = chatApp.processCommand(message)
    }
}