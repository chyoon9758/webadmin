/*<![CDATA[*/

function change_Status(str) {

    if (str == 'warnning'){
        $('#Transcoder_Status').attr('class', 'btn btn-warnning btn-icon-split');
        $('.status-text').text('warnning');
    }else if(str == 'error'){
        $('#Transcoder_Status').attr('class', 'btn btn-danger btn-icon-split');
        $('.status-text').text('error');
    }else if(str == 'fatal'){
        $('#Transcoder_Status').attr('class', 'btn btn-danger btn-icon-split');
        $('.status-text').text('fatal');
    }else if(str == 'trace'){
        $('#Transcoder_Status').attr('class', 'btn btn-success btn-icon-split');
        $('.status-text').text('trace');
    }else if(str == 'debug'){
        $('#Transcoder_Status').attr('class', 'btn btn-success btn-icon-split');
        $('.status-text').text('debug');
    }else if(str == 'info'){
        $('#Transcoder_Status').attr('class', 'btn btn-success btn-icon-split');
        $('.status-text').text('info');
    }

}


function Monitor_Log_Display(){
    if($('input:radio[id=Radio_Action]').is(':checked')){
        $('#Transcoder_Event').hide();
        $('#Action_Event').show();
    }else{
        $('#Action_Event').hide();
        $('#Transcoder_Event').show();
    }
}

function standBy_Button(){
    $('#Data_loading').show(function(){
        $.ajax({
            timeout: 120000,
            type: "put",
            url: "/transcode/standby",
            success: function(data, textStatus, jqXHR){
                $('#Data_loading').hide();
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                alert("StandBy");
                $('#content-wrapper').load('#content-wrapper');
            },
            error: function(data, textStatus, jqXHR){
                $('#Data_loading').hide();
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                alert("Standby error");
                $('#content-wrapper').load('#content-wrapper');
            }
        })
    });
}

function start_Button(){
    $('#Data_loading').show(function(){
        $.ajax({
            timeout: 120000,
            type: "put",
            url: "/transcode/start",
            success: function(){
                $('#Data_loading').hide();
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                alert("Start");
                $('#content-wrapper').load('#content-wrapper');
            },
            error: function(){
                $('#Data_loading').hide();
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                alert("Start error");
                $('#content-wrapper').load('#content-wrapper');
            }
        })
    });
}

function stop_Button(){
    $('#Data_loading').show(function(){
        $.ajax({
            timeout: 120000,
            type: "put",
            url: "/transcode/stop",
            success: function(){
                $('#Data_loading').hide();
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                alert("Stop");
                $('#content-wrapper').load('#content-wrapper');
            },
            error: function(){
                $('#Data_loading').hide();
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                alert("Stop error");
                $('#content-wrapper').load('#content-wrapper');
            }
        })
    });
}


/*]]>*/
